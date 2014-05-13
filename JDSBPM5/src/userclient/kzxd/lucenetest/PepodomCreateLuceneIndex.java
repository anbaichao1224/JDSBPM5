package kzxd.lucenetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.apache.struts2.ServletActionContext;

public class PepodomCreateLuceneIndex {
	
	public File getIndexFile(){
		File indexPath = new File("e:\\search\\pepodom\\luceneIndex"); //索引文件路径
		//File indexPath = new File("e:\\index\\aa.txt");
		return indexPath;
	}
	//创建所引
	public IndexWriter cIndex(){
		
		File indexPath = getIndexFile(); //索引文件路径
		File[] textFiles  =  indexPath.listFiles();
		IndexWriter indexWriter = null;
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);  //建立一个标准分析器 
		
        try {
        	Directory indexDir = FSDirectory.open(indexPath);
        	if(textFiles.length>0){
        		indexWriter  =   new IndexWriter(indexDir, analyzer,false,MaxFieldLength.LIMITED);
    		}else{
    			indexWriter  =   new IndexWriter(indexDir, analyzer,true,MaxFieldLength.LIMITED);
    		}
        	
			
			
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//创建一个索引器
        
		return indexWriter;
	}
	
	public void addDoc(String uid,String rollid,String title,String filetype,String personid,
			   String starttime,String endtime,String ispass){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("GBK");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);  //建立一个标准分析器 
		//toTest(filePath);
		//File file = new File("e:\\index\\ww.txt");  

	    Document doc = new Document();
	    doc.add(new Field("uid",uid,Store.NO,Index.NOT_ANALYZED));  //ID
	    doc.add(new Field("rollid",rollid,Store.YES,Index.ANALYZED));  //档案ID
	    doc.add(new Field("title",title,Store.YES,Index.ANALYZED));  //档案ID
	    doc.add(new Field("filetype",filetype,Store.YES,Index.ANALYZED));  //档案标题
	    doc.add(new Field("personid",personid,Store.YES,Index.ANALYZED)); //主题词
	    doc.add(new Field("starttime",starttime,Store.YES,Index.NOT_ANALYZED));  //创建时间
	    doc.add(new Field("endtime",endtime,Store.YES,Index.NOT_ANALYZED));  //归档人
	    doc.add(new Field("ispass",ispass,Store.YES,Index.ANALYZED));  //归档人
	    //doc.add(new Field("content","测试测试1234就froaa",Store.YES,Index.ANALYZED));
		IndexWriter indexWirter = cIndex();
		try {
			indexWirter.addDocument(doc);
			indexWirter.close();
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private BufferedReader getReader(File file){
		//try {              
			BufferedReader reader = null;
			try {
				try {
					reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			return reader;
	//}
	}
	
	private String readFileContent(File file) {         
		try {   
			
			BufferedReader reader = null;
			
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			         
			StringBuffer content = new StringBuffer();             
			try {                 
				for(String line=null;(line = reader.readLine())!=null;){                      
					content.append(line).append("\n");                 
					}            
			 } catch (IOException e) {                                     
					e.printStackTrace();             
			 }              
			 		try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    return content.toString();   
				    
			 } catch (FileNotFoundException e) {                          
					e.printStackTrace();
			 }         
				return null;     
	}   
	
	//转txt
	public void toTest(String pdffile) {    
	     
        //XPDF 
		String filePath = "e:\\index\\ww.txt";
        String test = "c:\\xpdftest\\xpdf\\pdftotext.exe -nopgbrk -cfg c:\\xpdftest\\xpdf\\xpdfrc -enc GBK "+pdffile+" "+filePath;
        try{    
        	 Process p = Runtime.getRuntime().exec(test); 
        	 BufferedReader reader = null;
 			
				reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			         
			StringBuffer content = new StringBuffer();             
			try {                 
				for(String line=null;(line = reader.readLine())!=null;){                      
					content.append(line).append("\n");                 
					}            
			 } catch (IOException e1) {                                     
					e1.printStackTrace();             
			 }              
			 		try {
						reader.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    //return content.toString();          
        }catch(Exception e){    
            e.printStackTrace();    
        }    
          
    }    
}
