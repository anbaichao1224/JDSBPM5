package kzxd.lucenetest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kzxd.electronicfile.entity.RollPepodomBean;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.kzxd.index.entity.RecordData;
import com.kzxd.index.util.File2Document;

public class PepodomSearchDemo {

	
	public List<RollPepodomBean> serchMethod(String rollid,String filetype,String personid,String ispass){
		List<RollPepodomBean> datalist = new ArrayList<RollPepodomBean>();
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
		File indexFile = new PepodomCreateLuceneIndex().getIndexFile();
		try {
			if(indexFile.listFiles().length>0){
			IndexSearcher indexSearcher = new IndexSearcher(FSDirectory.open(indexFile));
			String[] fields = {"rollid","ispass","personid"}; 
			String[] key = {rollid,ispass,personid}; 
			BooleanClause.Occur[] flags=new BooleanClause.Occur[]{BooleanClause.Occur.MUST,BooleanClause.Occur.MUST,BooleanClause.Occur.MUST};   
			//MultiFieldQueryParser.parse(Version.LUCENE_34, fields,flags, analyzer);
			//QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, fields,analyzer); //查询解析器      
			//QueryParser queryParser = new QueryParser(Version.LUCENE_34, "content", analyzer);
			try {
				
				
				Query query = MultiFieldQueryParser.parse(Version.LUCENE_34,key, fields,flags, analyzer);
				Filter filter = null; 
				TopDocs topDocs = indexSearcher.search(query, filter, 10000);
				
				for(ScoreDoc scoreDoc:topDocs.scoreDocs){             
					int docSn = scoreDoc.doc;//文档内部编号            
					Document doc = indexSearcher.doc(docSn);//根据文档编号取出相应的文档             
					
					if(doc.get("filetype").equals(filetype)&&doc.get("rollid").equals(rollid)&&doc.get("personid").equals(personid)&&befordate(formatDate(doc.get("starttime")),formatDate(new Date()))&&afterdate(formatDate(doc.get("endtime")),formatDate(new Date()))){
						boolean flag = true;
						for(RollPepodomBean rpbean:datalist){
							if(rpbean.getRollid().equals(doc.get("rollid"))){
								flag = false;
								break;
							}
						}
						if(flag){
						RollPepodomBean rpbean = new RollPepodomBean();
						rpbean.setRollname(doc.get("title"));
						rpbean.setFiletype(doc.get("filetype"));
						rpbean.setRollid(doc.get("rollid"));
						rpbean.setUuid(doc.get("rollid"));
						rpbean.setStarttime(doc.get("starttime"));
						rpbean.setEndtime(doc.get("endtime"));
						rpbean.setApplicantid(doc.get("personid"));
						datalist.add(rpbean);
						}
					}
				} 
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}//查询
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return datalist;

	}
	
	public Date formatDate(String date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
			try {
				return format.parse(date);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		
	}
	
	public Date formatDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return formatDate(format.format(date));
	}
	
	public boolean befordate(Date date,Date adate){
		if(date.before(adate)||date.equals(adate)){
			return true;
		}
		return false;
	}
	
	public boolean afterdate(Date date,Date bdate){
		if(date.after(bdate)||date.equals(bdate)){
			return true;
		}
		return false;
	}
}
