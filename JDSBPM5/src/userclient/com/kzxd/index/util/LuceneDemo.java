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
		
		/**       * ���� IndexSearcher        
		 * * �������������н��в�ѯ       * */   
		
		public List<RecordData> search(String queryString,String filePath,File indexPath,String rollid,String title,String dkeyword,String createtime,String overperson) throws Exception { 
			
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
			new File2Document().creatIndex(filePath,queryString,rollid,title,dkeyword,createtime,overperson);
			//��Ҫ�������ı�����ΪQuery         
			String[] fields = {"content"};         
			QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, fields, analyzer); //��ѯ������      
			Query query = queryParser.parse(queryString);          //��ѯ         
			IndexSearcher indexSearcher = new IndexSearcher(FSDirectory.open(indexPath));         
			Filter filter = null;          
			TopDocs topDocs = indexSearcher.search(query, filter, 10000);
			
			//topDocs ���Ƽ���          
			List<RecordData> datalist = new ArrayList<RecordData>();
			for(ScoreDoc scoreDoc:topDocs.scoreDocs){             
				int docSn = scoreDoc.doc;//�ĵ��ڲ����            
				Document doc = indexSearcher.doc(docSn);//�����ĵ����ȡ����Ӧ���ĵ�             
				File2Document.printDocumentInfo(doc);//��ӡ���ĵ���Ϣ          
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
