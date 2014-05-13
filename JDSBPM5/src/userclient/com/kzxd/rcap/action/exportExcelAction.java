package com.kzxd.rcap.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.kzxd.rcap.service.gzrzMsg;

public class exportExcelAction{
    
	private gzrzMsg rzMsg;
	
	public gzrzMsg getRzMsg() {
		return rzMsg;
	}
	public void setRzMsg(gzrzMsg rzMsg) {
		this.rzMsg = rzMsg;
	}
		
	public void exportExcel(){
	     
           //���ݿ⵼����Excel���     ׼������excel������ı���   
	       String[] title={"��ʼ����","��������","��־����","��־����"};
	       try{   
	            // ��ÿ�ʼʱ��   
	            long start = System.currentTimeMillis();   
	            // �����excel��·��   
	            String filePath = "F:\\testJXL.xls";   
	            // ����Excel������   
                WritableWorkbook wwb;   
	            // �½���һ��jxl�ļ�,����f��������testJXL.xls   
	            OutputStream os = new FileOutputStream(filePath);   
	            wwb=Workbook.createWorkbook(os);    
	            // ��ӵ�һ�����������õ�һ��Sheet������   
	            WritableSheet sheet = wwb.createSheet("������־", 0);   
	            Label label;   
	            for(int i=0;i<title.length;i++){   
	               // Label(x,y,z) ����Ԫ��ĵ�x+1�У���y+1��, ����z   
                   // ��Label������Ӷ�����ָ����Ԫ���λ�ú�����   
	                label = new Label(i,0,title[i]);   
	               // ������õĵ�Ԫ����ӵ���������   
	               sheet.addCell(label);   
                 }   
	           
	            // �������������     �������ֵ���Ԫ����Ҫʹ��jxl.write.Number 	  ����ʹ��������·�����������ִ��� 
	            //������ʾ���ڵĹ�����ʽ     ��:yyyy-MM-dd hh:mm 
		           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
		           String newdate = sdf.format(new Date()); 
		        // �����ʼ���� 
		           label = new Label(0,1,"10��18��");   
		           sheet.addCell(label);
		        // ���������� 
		           label = new Label(1,1,"10��21��");   
		           sheet.addCell(label);  
		        // �����־����   
		            label = new Label(2,1,"���±�");   
		            sheet.addCell(label);     
		        // �������   
			        label = new Label(3,1,"����");   
			        sheet.addCell(label); 
	           
			        
			   // ����Ʒ���   
	           // jxl.write.Number number = new jxl.write.Number(0,1,20071001);   
	           // sheet.addCell(number);   
	             
	           // ���������ʾ���Ĺ�����ʽ    jxl���Զ�ʵ����������    �� 2.456�ᱻ��ʽ��Ϊ2.46,2.454�ᱻ��ʽ��Ϊ2.45 
	            //jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#.##");   
                //jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(nf);   
	           // ����Ʒ�۸�   
	           //jxl.write.Number nb = new jxl.write.Number(2,1,2.45,wcf);   
	          // sheet.addCell(nb);   
	           // ����Ʒ����   
	           //jxl.write.Number numb = new jxl.write.Number(3,1,200);   
	           //sheet.addCell(numb);   
	          
	           //��ʾ����ֵ 
               //jxl.write.Boolean bool = new jxl.write.Boolean(6,1,true);   
	           //sheet.addCell(bool);   
	          
	           //�ϲ���Ԫ�� 	 ͨ��writablesheet.mergeCells(int x,int y,int m,int n);��ʵ�ֵ� 
	           //��ʾ���ӵ�x+1�У�y+1�е�m+1�У�n+1�кϲ� 
	           //sheet.mergeCells(0,3,2,3);   
	           //label = new Label(0,3,"�ϲ���������Ԫ��");   
	           //sheet.addCell(label);   
	             
	            //���幫�������ʽ 	ͨ����ȡһ���������ʽ����Ϊģ�� 	 ����ͨ��web.getSheet(0)��õ�һ��sheet 
	            //Ȼ��ȡ�õ�һ��sheet�ĵڶ��У���һ��Ҳ����"��Ʒ����"������   
	             //CellFormat cf = wwb.getSheet(0).getCell(1, 0).getCellFormat();   
	            //WritableCellFormat wc = new WritableCellFormat();   
	             // ���þ���   
	             //wc.setAlignment(Alignment.CENTRE);   
	             // ���ñ߿���   
	            // wc.setBorder(Border.ALL, BorderLineStyle.THIN);   
	             // ���õ�Ԫ��ı�����ɫ   
	             //wc.setBackground(jxl.format.Colour.RED);   
	             //label = new Label(1,5,"����",wc);   
	             //sheet.addCell(label);   
	            // ��������   
	            //jxl.write.WritableFont wfont = new jxl.write.WritableFont(WritableFont.createFont("����"),20);   
	            //WritableCellFormat font = new WritableCellFormat(wfont);   
                //label = new Label(2,6,"����",font);   
                //sheet.addCell(label);   
	            // д������   
	            wwb.write();   
	            // �ر��ļ�   
                wwb.close();   
	             long end = System.currentTimeMillis();   
	        } catch (Exception e) {   
	             e.printStackTrace();
	         }   
      }
	
}

