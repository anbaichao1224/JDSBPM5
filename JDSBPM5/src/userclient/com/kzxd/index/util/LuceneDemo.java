package com.kzxd.index.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.kzxd.index.entity.RecordData;
	
	public class LuceneDemo {      
		
		/**       * 搜索 IndexSearcher        
		 * * 用来在索引库中进行查询       * */   
		
		public List<RecordData> search(String queryString,String filePath,File indexPath,String rollid,String title,String dkeyword,String createtime,String overperson) throws Exception { 
			
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
			new File2Document().creatIndex(filePath,queryString,rollid,title,dkeyword,createtime,overperson);
			//把要搜索的文本解析为Query         
			String[] fields = {"content"};         
			QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, fields, analyzer); //查询解析器      
			Query query = queryParser.parse(queryString);          //查询         
			IndexSearcher indexSearcher = new IndexSearcher(FSDirectory.open(indexPath));         
			Filter filter = null;          
			TopDocs topDocs = indexSearcher.search(query, filter, 10000);
			
			//topDocs 类似集合          
			List<RecordData> datalist = new ArrayList<RecordData>();
			for(ScoreDoc scoreDoc:topDocs.scoreDocs){             
				int docSn = scoreDoc.doc;//文档内部编号            
				Document doc = indexSearcher.doc(docSn);//根据文档编号取出相应的文档             
				File2Document.printDocumentInfo(doc);//打印出文档信息          
				RecordData dbean = new RecordData();
				dbean.setTitle(doc.get("title"));
				dbean.setRollid(doc.get("rollid"));
				dbean.setTitle(doc.get("dkeyword"));
				dbean.setTitle(doc.get("createtime"));
				dbean.setTitle(doc.get("overperson"));
				datalist.add(dbean);
			} 
			return datalist;
	    }     
  }
