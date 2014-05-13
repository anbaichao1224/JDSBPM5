package com.kzxd.rcap.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import net.kzxd.dj.bean.FdtOaDj;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CommonExportExcel {
	public void export(OutputStream os,String xmodel,String bmid) throws Exception{
		try {
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("test", 0);
			String[] titleary = {"���","����","�ȼ�","���ı��","�Ǽ���","���ı���","���ĵ�λ","��������"};
			
			WritableFont wfc = new jxl.write.WritableFont(WritableFont.ARIAL,20, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.GREEN);
			WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(wfc);
			wcfFC.setBackground(jxl.format.Colour.RED);
			
			setWsTitle(titleary,ws);
			setWsContent(1,xmodel,ws,bmid);
			//д��Exel������
			wwb.write();
			//�ر�Excel����������
			wwb.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setWsTitle(String[] titleary,WritableSheet ws){
		for(int i=0;i<titleary.length;i++){
			Label lb1 = new Label(i,0,titleary[i]);
			try {
				ws.addCell(lb1);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*Label lb1 = new Label(0,0,"���ı���");
		Label lb2 = new Label(1,0,"��������");
		Label lb3 = new Label(2,0,"���ĵ�λ");
		Label lb4 = new Label(0,0,"�ܼ�");
		Label lb5 = new Label(1,0,"�����̶�");
		Label lb6 = new Label(2,0,"���");
		Label lb7 = new Label(2,0,"����ģ��");*/
	}
	
	
	public void setWsContent(int columncount,String xmlmodel,WritableSheet ws,String bmid) throws Exception{
	
	
	
		
	}
	
	
	/*public void writeExcel(){
		File f=new File("D:\\kk.xls");
		try {
			f.createNewFile();
			export(new FileOutputStream(f));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	
	public String formatDate(Date date){
		if(date!=null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(date);
		}
		return null;
	}
}
