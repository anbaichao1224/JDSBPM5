package com.kzxd.index.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class addindex {
	
	public void add(File indexPath,String filePath) throws Exception {          
		//String filePath = "e:\\index\\ww.txt";
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34); 
		File file = new File(filePath);          
		Document doc = new Document();          
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			//按照UTF-8编码方式将字节流转化为字符流
		    InputStreamReader isr;
			isr = new InputStreamReader(fis,"UTF-8");
			//从字符流中获取文本并进行缓冲
		    BufferedReader br= new BufferedReader(isr);
		    doc.add(new Field("name",file.getName(),Store.YES,Index.ANALYZED));  
			doc.add(new Field("content",br));//,Store.YES,Index.ANALYZED));//readFileContent()读取文件类容           readFileContent(file)   
			doc.add(new Field("size",String.valueOf(file.length()),Store.YES,Index.ANALYZED));//分词,文件大小(int)转换成String                 
			doc.add(new Field("path",file.getAbsolutePath(),Store.YES,Index.ANALYZED));//需要根据文件的路径来查询            
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		IndexWriter indexWriter = new IndexWriter(FSDirectory.open(indexPath), analyzer, false,MaxFieldLength.LIMITED);         
		indexWriter.addDocument(doc);          
		indexWriter.close();      
	}

}
