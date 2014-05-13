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
	     
           //数据库导出至Excel表格     准备设置excel工作表的标题   
	       String[] title={"起始日期","结束日期","日志类型","日志内容"};
	       try{   
	            // 获得开始时间   
	            long start = System.currentTimeMillis();   
	            // 输出的excel的路径   
	            String filePath = "F:\\testJXL.xls";   
	            // 创建Excel工作薄   
                WritableWorkbook wwb;   
	            // 新建立一个jxl文件,即在f盘下生成testJXL.xls   
	            OutputStream os = new FileOutputStream(filePath);   
	            wwb=Workbook.createWorkbook(os);    
	            // 添加第一个工作表并设置第一个Sheet的名字   
	            WritableSheet sheet = wwb.createSheet("工作日志", 0);   
	            Label label;   
	            for(int i=0;i<title.length;i++){   
	               // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z   
                   // 在Label对象的子对象中指明单元格的位置和内容   
	                label = new Label(i,0,title[i]);   
	               // 将定义好的单元格添加到工作表中   
	               sheet.addCell(label);   
                 }   
	           
	            // 下面是填充数据     保存数字到单元格，需要使用jxl.write.Number 	  必须使用其完整路径，否则会出现错误 
	            //定义显示日期的公共格式     如:yyyy-MM-dd hh:mm 
		           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
		           String newdate = sdf.format(new Date()); 
		        // 填充起始日期 
		           label = new Label(0,1,"10月18日");   
		           sheet.addCell(label);
		        // 填充结束日期 
		           label = new Label(1,1,"10月21日");   
		           sheet.addCell(label);  
		        // 填充日志类型   
		            label = new Label(2,1,"记事本");   
		            sheet.addCell(label);     
		        // 填充内容   
			        label = new Label(3,1,"内容");   
			        sheet.addCell(label); 
	           
			        
			   // 填充产品编号   
	           // jxl.write.Number number = new jxl.write.Number(0,1,20071001);   
	           // sheet.addCell(number);   
	             
	           // 定义对于显示金额的公共格式    jxl会自动实现四舍五入    如 2.456会被格式化为2.46,2.454会被格式化为2.45 
	            //jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#.##");   
                //jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(nf);   
	           // 填充产品价格   
	           //jxl.write.Number nb = new jxl.write.Number(2,1,2.45,wcf);   
	          // sheet.addCell(nb);   
	           // 填充产品数量   
	           //jxl.write.Number numb = new jxl.write.Number(3,1,200);   
	           //sheet.addCell(numb);   
	          
	           //显示布尔值 
               //jxl.write.Boolean bool = new jxl.write.Boolean(6,1,true);   
	           //sheet.addCell(bool);   
	          
	           //合并单元格 	 通过writablesheet.mergeCells(int x,int y,int m,int n);来实现的 
	           //表示将从第x+1列，y+1行到m+1列，n+1行合并 
	           //sheet.mergeCells(0,3,2,3);   
	           //label = new Label(0,3,"合并了三个单元格");   
	           //sheet.addCell(label);   
	             
	            //定义公共字体格式 	通过获取一个字体的样式来作为模板 	 首先通过web.getSheet(0)获得第一个sheet 
	            //然后取得第一个sheet的第二列，第一行也就是"产品名称"的字体   
	             //CellFormat cf = wwb.getSheet(0).getCell(1, 0).getCellFormat();   
	            //WritableCellFormat wc = new WritableCellFormat();   
	             // 设置居中   
	             //wc.setAlignment(Alignment.CENTRE);   
	             // 设置边框线   
	            // wc.setBorder(Border.ALL, BorderLineStyle.THIN);   
	             // 设置单元格的背景颜色   
	             //wc.setBackground(jxl.format.Colour.RED);   
	             //label = new Label(1,5,"字体",wc);   
	             //sheet.addCell(label);   
	            // 设置字体   
	            //jxl.write.WritableFont wfont = new jxl.write.WritableFont(WritableFont.createFont("隶书"),20);   
	            //WritableCellFormat font = new WritableCellFormat(wfont);   
                //label = new Label(2,6,"隶书",font);   
                //sheet.addCell(label);   
	            // 写入数据   
	            wwb.write();   
	            // 关闭文件   
                wwb.close();   
	             long end = System.currentTimeMillis();   
	        } catch (Exception e) {   
	             e.printStackTrace();
	         }   
      }
	
}

