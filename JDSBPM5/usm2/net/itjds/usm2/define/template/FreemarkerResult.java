/*
 * $Id: FreemarkerResult.java,v 1.1 2011/08/03 09:38:57 liyang Exp $
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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;



import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;


/**
 * <!-- START SNIPPET: description -->
 *
 * Renders a view using the Freemarker template engine.
 * <p>
 * The FreemarkarManager class configures the template loaders so that the
 * template location can be either
 * </p>
 *
 * <ul>
 *
 * <li>relative to the web root folder. eg <code>/WEB-INF/views/home.ftl</code>
 * </li>
 *
 * <li>a classpath resuorce. eg <code>com/company/web/views/home.ftl</code></li>
 *
 * </ul>
 *
 * <!-- END SNIPPET: description -->
 *
 * <b>This result type takes the following parameters:</b>
 *
 * <!-- START SNIPPET: params -->
 *
 * <ul>
 *
 * <li><b>location (default)</b> - the location of the template to process.</li>
 *
 * <li><b>parse</b> - true by default. If set to false, the location param will
 * not be parsed for Ognl expressions.</li>
 *
 * <li><b>contentType</b> - defaults to "text/html" unless specified.</li>
 *
 * </ul>
 *
 * <!-- END SNIPPET: params -->
 *
 * <b>Example:</b>
 *
 * <pre>
 * <!-- START SNIPPET: example -->
 *
 * &lt;result name="success" type="freemarker"&gt;foo.ftl&lt;/result&gt;
 *
 * <!-- END SNIPPET: example -->
 * </pre>
 */
public class FreemarkerResult {

    private static final long serialVersionUID = -3778230771704661631L;

   
    protected Configuration configuration;
    protected ObjectWrapper wrapper;
    protected FreemarkerManager freemarkerManager;
    private Writer writer;

    /*
     * Struts results are constructed for each result execution
     *
     * the current context is availible to subclasses via these protected fields
     */
    protected String location;
    private String pContentType = "text/html";

    public FreemarkerResult() {
        super();
        this.freemarkerManager=new FreemarkerManager();
    }
    

    public FreemarkerResult(String location) {
       
    }
    
    
    public void setFreemarkerManager(FreemarkerManager mgr) {
        this.freemarkerManager = mgr;
    }

    public void setContentType(String aContentType) {
        pContentType = aContentType;
    }

    /**
     * allow parameterization of the contentType
     * the default being text/html
     */
    public String getContentType() {
        return pContentType;
    }

    /**
     * Execute this result, using the specified template location.
     * <p/>
     * The template location has already been interoplated for any variable substitutions
     * <p/>
     * this method obtains the freemarker configuration and the object wrapper from the provided hooks.
     * It them implements the template processing workflow by calling the hooks for
     * preTemplateProcess and postTemplateProcess
     */
    public Writer doExecute(String location) throws IOException, TemplateException {
        this.location = location;
   
        this.configuration = getConfiguration();
        this.wrapper = getObjectWrapper();

        this.writer=new StringWriter();
      

        Template template = configuration.getTemplate(location);
        TemplateModel model = createModel();

        // Give subclasses a chance to hook into preprocessing
        if (preTemplateProcess(template, model)) {
            try {
                // Process the template
                template.process(model, writer);
            } finally {
                // Give subclasses a chance to hook into postprocessing
                postTemplateProcess(template, model);
            }
        }
        return writer;
    }

    /**
     * This method is called from {@link #doExecute(String, ActionInvocation)} to obtain the
     * FreeMarker configuration object that this result will use for template loading. This is a
     * hook that allows you to custom-configure the configuration object in a subclass, or to fetch
     * it from an IoC container.
     * <p/>
     * <b>
     * The default implementation obtains the configuration from the ConfigurationManager instance.
     * </b>
     */
    protected Configuration getConfiguration() throws TemplateException {
        return freemarkerManager.getConfiguration();
    }

    /**
     * This method is called from {@link #doExecute(String, ActionInvocation)}  to obtain the
     * FreeMarker object wrapper object that this result will use for adapting objects into template
     * models. This is a hook that allows you to custom-configure the wrapper object in a subclass.
     * <p/>
     * <b>
     * The default implementation returns {@link Configuration#getObjectWrapper()}
     * </b>
     */
    protected ObjectWrapper getObjectWrapper() {
        return configuration.getObjectWrapper();
    }


    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    /**
     * The default writer writes directly to the response writer.
     */
    public Writer getWriter() throws IOException {
        if(writer != null) {
            return writer;
        }
        return null;
    }

    /**
     * Build the instance of the ScopesHashModel, including JspTagLib support
     * <p/>
     * Objects added to the model are
     * <p/>
     * <ul>
     * <li>Application - servlet context attributes hash model
     * <li>JspTaglibs - jsp tag lib factory model
     * <li>Request - request attributes hash model
     * <li>Session - session attributes hash model
     * <li>request - the HttpServletRequst object for direct access
     * <li>response - the HttpServletResponse object for direct access
     * <li>stack - the OgnLValueStack instance for direct access
     * <li>ognl - the instance of the OgnlTool
     * <li>action - the action itself
     * <li>exception - optional : the JSP or Servlet exception as per the servlet spec (for JSP Exception pages)
     * <li>struts - instance of the StrutsUtil class
     * </ul>
     */
    protected TemplateModel createModel() throws TemplateModelException {
     
        ValueStack stack = ActionContext.getContext().getValueStack();
    	 HttpServletRequest request= ServletActionContext.getRequest();
       
        return freemarkerManager.buildTemplateModel(stack, request,   wrapper);
    }

  
    /**
     * the default implementation of postTemplateProcess applies the contentType parameter
     */
    protected void postTemplateProcess(Template template, TemplateModel data) throws IOException {
    }

    /**
     * Called before the execution is passed to template.process().
     * This is a generic hook you might use in subclasses to perform a specific
     * action before the template is processed. By default does nothing.
     * A typical action to perform here is to inject application-specific
     * objects into the model root
     *
     * @return true to process the template, false to suppress template processing.
     */
    protected boolean preTemplateProcess(Template template, TemplateModel model) throws IOException {
        Object attrContentType = template.getCustomAttribute("content_type");

        if (attrContentType != null) {
           
        } else {
            String contentType = getContentType();

            if (contentType == null) {
                contentType = "text/html";
            }

            String encoding = template.getEncoding();

            if (encoding != null) {
                contentType = contentType + "; charset=" + encoding;
            }

           
        }

        return true;
    }
}
