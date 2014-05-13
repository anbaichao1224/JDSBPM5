package com.kzxd.index.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class File2Document {      
	
	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);     
	
	/**       * 建立索引 IndexWriter 增、删、改       * */     
	
	public void creatIndex(String filePath,String queryString,String rollid,
			String title,String dkeyword,String createtime,String overperson) throws Exception {          
		File indexPath = new File("e:\\search\\luceneIndex"); //索引文件路径
		File file = new File(filePath);
		Directory dir = FSDirectory.open(indexPath);
			Document doc = addDocument(indexPath,filePath,rollid,queryString,title,dkeyword,createtime,overperson); 
			IndexWriter indexWriter = new IndexWriter(FSDirectory.open(indexPath), analyzer, false,MaxFieldLength.LIMITED);         
			indexWriter.addDocument(doc);          
			indexWriter.close(); 
		}
	//增量索引
	public  Document addDocument(File indexPath,String filePath,String rollid,String queryString,String title,
			   String dkeyword,String createtime,String overperson){          
		
		    File file = new File(filePath);          
		    Document doc = new Document();
		    doc.add(new Field("rollid",rollid,Store.YES,Index.NOT_ANALYZED));  //档案ID
		    doc.add(new Field("title",title,Store.YES,Index.ANALYZED));  //档案标题
		    doc.add(new Field("keyword",queryString,Store.YES,Index.ANALYZED)); //关键字(查询)
		    doc.add(new Field("dkeyword",dkeyword,Store.YES,Index.NOT_ANALYZED)); //主题词
		    doc.add(new Field("createtime",createtime,Store.YES,Index.NOT_ANALYZED));  //创建时间
		    doc.add(new Field("overperson",overperson,Store.YES,Index.NOT_ANALYZED));  //归档人
			//doc.add(new Field("path",file.getAbsolutePath(),Store.YES,Index.NOT_ANALYZED)); 
			doc.add(new Field("content","测试123测一234",Store.YES,Index.ANALYZED));  //正文
	        return doc;//readFileContent(file)
	        
	   
	}
	
	
	/**  读取文件内容       * */     
	private static String readFileContent(File file) {         
		try {              
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}            
			StringBuffer content = new StringBuffer();             
			try {                 
				for(String line=null;(line = reader.readLine())!=null;){                      
					content.append(line).append("\n");                 
					}            
			 } catch (IOException e) {                                     
					e.printStackTrace();             
			 }              
				    return content.toString();         
			 } catch (FileNotFoundException e) {                          
					e.printStackTrace();          
			 }         
				return null;     
	}     
	/**  获取name属性值的两种方法       * 1.Filed field = doc.getFiled("name");     
	 *   *         field.stringValue();      
	 *    * 2.doc.get("name");      @param doc       * */    
	     
	public static void printDocumentInfo(Document doc){          
	    	  
	 }   
	        
	
}
