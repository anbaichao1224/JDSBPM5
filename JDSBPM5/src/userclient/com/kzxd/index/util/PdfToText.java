package com.kzxd.index.util;

public class PdfToText {
	 
    public void toTest(String pdffile,String filePath) {    
     
        //XPDF    
        String test = "c:\\xpdftest\\xpdf\\pdftotext.exe -nopgbrk -cfg c:\\xpdftest\\xpdf\\xpdfrc -enc UTF-8 "+pdffile+" "+filePath;
        try{    
           Runtime.getRuntime().exec(test); 
        }catch(Exception e){    
            e.printStackTrace();    
        }    
          
    }    
}
