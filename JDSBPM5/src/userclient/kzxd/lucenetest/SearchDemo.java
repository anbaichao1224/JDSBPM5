package kzxd.lucenetest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kzxd.electronicfile.entity.RollPepodomBean;

import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.base.PersonRole;

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
import org.apache.struts2.ServletActionContext;

import com.kzxd.index.entity.RecordData;
import com.kzxd.index.util.File2Document;

public class SearchDemo {

	
	public List<RollPepodomBean> serchMethod(String seartype,String querystr,String docvalue,String query1,String query2,String query3){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		
		List<RollPepodomBean> datalist = new ArrayList<RollPepodomBean>();
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_34);
		File indexFile = new CreateLuceneIndex().getIndexFile();
		try {
			if(indexFile.listFiles().length>0){
			IndexSearcher indexSearcher = new IndexSearcher(FSDirectory.open(indexFile));
			      
			//QueryParser queryParser = new QueryParser(Version.LUCENE_34, "content", analyzer);
			try {
				Query query = null;
				if(seartype.equals("4")){
					if(querystr!=null&&!querystr.equals("")){
						
						String[] fields = {"title"};
						QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_34, fields, analyzer); //查询解析器
						query = queryParser.parse(querystr);
					}else{
						return null;
					}
					
				}else{
					List<String> fieldlist = new ArrayList<String>();
					List<String> keylist = new ArrayList<String>();
					if(querystr!=null&&!querystr.equals("")){
						fieldlist.add("title");
						keylist.add(querystr);
					}
					if(query1!=null&&!query1.equals("")){
						fieldlist.add("query1");
						keylist.add(query1);
					}
					if(query2!=null&&!query2.equals("")){
						fieldlist.add("query2");
						keylist.add(query2);
					}
					if(query3!=null&&!query3.equals("")){
						fieldlist.add("query3");
						keylist.add(query3);
					}
					if(docvalue!=null&&!docvalue.equals("")){
						fieldlist.add("content");
						keylist.add(docvalue);
					}
					String[] fields = (String[])fieldlist.toArray(new String[0]);
					String[] keys = (String[])keylist.toArray(new String[0]);
					BooleanClause.Occur[] flags = new BooleanClause.Occur[fields.length];
					for(int i=0;i<fields.length;i++){
						flags[i] = BooleanClause.Occur.MUST;
					}
					query = MultiFieldQueryParser.parse(Version.LUCENE_34,keys, fields,flags, analyzer);
				}
				//String[] fields = {"title","dkeyword","content"}; 
				Person person = null;
				try {
					person = (Person)OrgManagerFactory.getOrgManager().getPersonByID(personId);
				} catch (PersonNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean isadmin = false;
				List<PersonRole> rlist = person.getRoleList();
				for(PersonRole pr:rlist){
					if(pr.getName().equals("档案管理员")){
						isadmin = true;
					}
				}
				Filter filter = null; 
				TopDocs topDocs = indexSearcher.search(query, filter, 10000);
				for(ScoreDoc scoreDoc:topDocs.scoreDocs){             
					int docSn = scoreDoc.doc;//文档内部编号            
					Document doc = indexSearcher.doc(docSn);//根据文档编号取出相应的文档             
					RollPepodomBean dbean = new RollPepodomBean();
					dbean.setRollname(doc.get("title"));
					dbean.setUuid(doc.get("rollid"));
					//去掉重复索引的显示
					boolean flag = true;
					for(RollPepodomBean rdata:datalist){
						if(rdata.getUuid().equals(dbean.getUuid())){
							flag = false;
							break;
						}
					}
					if(!flag){
						continue;
					}
					if(query1!=null&&!query1.equals("")&&seartype.equals("2")){
						if(!query1.equals(doc.get("query1"))){
							continue;
						}
					}
					if(query2!=null&&!query2.equals("")&&seartype.equals("2")){
						if(!query2.equals(doc.get("query2"))){
							continue;
						}
					}
					if(isadmin&&doc.get("filetype").equals(seartype)){
						
						dbean.setRollname(doc.get("title"));
						dbean.setRollid(doc.get("rollid"));
						dbean.setStarttime(doc.get("overperson"));
						dbean.setEndtime(doc.get("createtime"));
						dbean.setFiletype(doc.get("filetype"));
						datalist.add(dbean);
						continue;
					}
					
					PepodomSearchAction paction = new PepodomSearchAction();
					paction.setUid(doc.get("rollid"));
					paction.setPersonid(personId);
					paction.setIspass("2");
					paction.setFiletype(seartype);
					paction.searchlist();
					List<RollPepodomBean> rplist = paction.getDatalist();
					datalist.addAll(rplist);
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			try {
				return format.parse(date);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		
	}
}
