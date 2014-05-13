/**
 * $RCSfile: BPMInitServlet.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/08/03 09:39:18 $
 *
 * Copyright (C) 2003 itjds, Inc. All rights reserved.
 *
 * This software is the proprietary information of itjds, Inc.
 * Use is subject to license terms.
 */
package net.itjds.bpm.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.SwingUtilities;

import net.itjds.bpm.engine.WorkflowServer;

import net.itjds.common.org.service.UDPServer;
import net.itjds.common.util.TaskEngine;

import net.itjds.esb.util.ESBDateInit;


/**
 * <p>Title: bpm工作流管理系统</p>
 * <p>Description: Servlet</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 基督山网络</p>
 * @author wenzhang
 * @version 2.0
 */
public class BPMInitServlet extends HttpServlet {
	 private static final long DEFAULT_SESSIONCHECKINTERVAL = 1 * net.itjds.common.util.Constants.MINUTE; 
    public void init() throws ServletException {
        try {
        	//初始化工作流系统
            WorkflowServer.getInstance();
         	//初始化总线系统
            ESBDateInit.loadStaticAllData(true);
   
        } catch (Exception bpme) {
            bpme.printStackTrace();
        }
    }

     protected void performTask(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

}
