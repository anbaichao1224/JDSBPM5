package kzxd.tixing.action;

public interface TiXingSaveDao {

	public void save(String personid,String mkname,String title,String delid);
	
	public void delete(String delid);
	
}
