package com.kzxd.abortprocess.action;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

/**
 * @author pqcc
 */

public class test {
	private static Logger log = Logger.getLogger(test.class.getName());
	  private static int getPid() {  
		          RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
		          String name = runtime.getName(); // format: "pid@hostname"  
		          try {  
		              return Integer.parseInt(name.substring(0, name.indexOf('@')));  
		          } catch (Exception e) {  
		              return -1;  
		          }  
		      } 

	public static void main(String[] args) {
	/*	String command = "taskkill /f /im java.exe";  
		 try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} */
		Process process = null;
		try {
			/**
			 * tasklist 或 ipconfig 只要在cmd 模式下可运行都可以.
			 */
			process = Runtime.getRuntime().exec("cmd.exe   /c   tasklist");
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = " ";
			while ((line = input.readLine()) != null) {
				log.info(line);
				System.out.println(line);
			}
			int pid = getPid();  
		        System.out.println("pid: " + pid);  
		       // System.in.read(); 
		     // Runtime.getRuntime().exec("ntsd -c q -p "+pid);
		    //   tskill PID(process ID)
		        System.out.println("tskill "+pid);
		        Runtime.getRuntime().exec("cmd.exe /c taskkill /f /pid " + pid);    
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}