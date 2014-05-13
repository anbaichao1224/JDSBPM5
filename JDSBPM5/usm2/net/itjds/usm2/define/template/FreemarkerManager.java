/*
 * $Id: FreemarkerManager.java,v 1.1 2011/08/03 09:38:57 liyang Exp $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package net.itjds.usm2.define.template;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;


import javax.servlet.http.HttpServletRequest;




import net.itjds.fdt.define.designer.utils.HtmlFileUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.FileManager;
import com.opensymphony.xwork2.util.ValueStack;



import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;

import freemarker.ext.beans.BeansWrapper;


import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Static Configuration Manager for the FreemarkerResult's configuration
 *
 * <p/>
 *
 * Possible extension points are :-
 * <ul>
 *   <li>createConfiguration method</li>
 *   <li>loadSettings method</li>
 *   <li>getTemplateLoader method</li>
 *   <li>populateContext method</li>
 * </ul>
 *
 * <p/>
 * <b> createConfiguration method </b><br/>
 * Create a freemarker Configuration.
 * <p/>
 *
 * <b> loadSettings method </b><br/>
 * Load freemarker settings, default to freemarker.properties (if found in classpath)
 * <p/>
 *
 * <b> getTemplateLoader method</b><br/>
 * create a freemarker TemplateLoader that loads freemarker template in the following order :-
 * <ol>
 *   <li>path defined in ServletContext init parameter named 'templatePath' or 'TemplatePath' (must be an absolute path)</li>
 *   <li>webapp classpath</li>
 *   <li>struts's static folder (under [STRUT2_SOURCE]/org/apache/struts2/static/</li>
 * </ol>
 * <p/>
 *
 * <b> populateContext method</b><br/>
 * populate the created model.
 *
 */
public class FreemarkerManager {

    private static final Log log = LogFactory.getLog(FreemarkerManager.class);
    public static final String CONFIG_SERVLET_CONTEXT_KEY = "freemarker.Configuration";
    public static final String KEY_EXCEPTION = "exception";
    // coppied from freemarker servlet - so that there is no dependency on it
    public static final String KEY_APPLICATION = "Application";
    public static final String KEY_REQUEST_MODEL = "Request";
    public static final String KEY_SESSION_MODEL = "Session";
    public static final String KEY_JSP_TAGLIBS = "JspTaglibs";
    public static final String KEY_REQUEST_PARAMETER_MODEL = "Parameters";
    
    private String encoding="utf-8";
    private boolean altMapWrapper;
   
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
    
  
    public void setWrapperAltMap(String val) {
        altMapWrapper = "true".equals(val);
    }
      
   

    public final synchronized freemarker.template.Configuration getConfiguration() throws TemplateException {
        freemarker.template.Configuration config = (freemarker.template.Configuration) ActionContext.getContext().get(CONFIG_SERVLET_CONTEXT_KEY);

        if (config == null) {
            config = createConfiguration();
        }
        
        config.setWhitespaceStripping(true);

        return config;
    }

    protected ScopesHashModel buildScopesHashModel( HttpServletRequest request, ObjectWrapper wrapper, ValueStack stack) {
        ScopesHashModel model = new ScopesHashModel(wrapper,  request, stack);
        
        return model;
    }



    protected BeansWrapper getObjectWrapper() {
        return new StrutsBeanWrapper(altMapWrapper);
    }

    /**
     * The default template loader is a MultiTemplateLoader which includes
     * a ClassTemplateLoader and a WebappTemplateLoader (and a FileTemplateLoader depending on
     * the init-parameter 'TemplatePath').
     * <p/>
     * The ClassTemplateLoader will resolve fully qualified template includes
     * that begin with a slash. for example /com/company/template/common.ftl
     * <p/>
     * The WebappTemplateLoader attempts to resolve templates relative to the web root folder
     */
    protected TemplateLoader getTemplateLoader() {
        // construct a FileTemplateLoader for the init-param 'TemplatePath'
        FileTemplateLoader templatePathLoader = null;

        String templatePath="";
	
			//templatePath = JDSUtil.getJdsPath();
			  templatePath = HtmlFileUtil.getSrcTemplateAbsolutePath(
						"WEB-INF"+File.separator+"classes").substring(1);
		
         if (templatePath != null) {
            try {
                templatePathLoader = new FileTemplateLoader(new File(templatePath));
            } catch (IOException e) {
                log.error("Invalid template path specified: " + e.getMessage(), e);
            }
        }

        // presume that most apps will require the class and webapp template loader
        // if people wish to
        return templatePathLoader ;
    }

    /**
     * Create the instance of the freemarker Configuration object.
     * <p/>
     * this implementation
     * <ul>
     * <li>obtains the default configuration from Configuration.getDefaultConfiguration()
     * <li>sets up template loading from a ClassTemplateLoader and a WebappTemplateLoader
     * <li>sets up the object wrapper to be the BeansWrapper
     * <li>loads settings from the classpath file /freemarker.properties
     * </ul>
     *
     * @param servletContext
     */
    protected freemarker.template.Configuration createConfiguration() throws TemplateException {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration();

        configuration.setTemplateLoader(getTemplateLoader());
     

        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

        configuration.setObjectWrapper(getObjectWrapper());

        if (encoding != null) {
            configuration.setDefaultEncoding(encoding);
        }

        loadSettings( configuration);

        return configuration;
    }

    /**
     * Load the settings from the /freemarker.properties file on the classpath
     *
     * @see freemarker.template.Configuration#setSettings for the definition of valid settings
     */
    protected void loadSettings(freemarker.template.Configuration configuration) {
        try {
            InputStream in = FileManager.loadFile("freemarker.properties", FreemarkerManager.class);

            if (in != null) {
                Properties p = new Properties();
                p.load(in);
                configuration.setSettings(p);
            }
        } catch (IOException e) {
            log.error("Error while loading freemarker settings from /freemarker.properties", e);
        } catch (TemplateException e) {
            log.error("Error while loading freemarker settings from /freemarker.properties", e);
        }
    }

    public SimpleHash buildTemplateModel(ValueStack stack,  HttpServletRequest request,  ObjectWrapper wrapper) {
        ScopesHashModel model = buildScopesHashModel(request,wrapper, stack);

      
        return model;
    }
}
