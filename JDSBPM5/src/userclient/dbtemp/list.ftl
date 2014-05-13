package ${package}.db.${tempbeanid}.database;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collection;

import net.itjds.usm2.*;
import ${package}.db.${tempbeanid}.*;
import net.itjds.usm2.db.*;
import ${package}.db.${tempbeanid}.inner.*;

	public class ${table.className}List extends DbUSMList implements Serializable {

	private static int PREFETCH_SIZE = 30;

	int prefetchIndex = 0;

	public ${table.className}List() {
		super();
	}

	
	public ${table.className}List(Collection c) {
		super(c);
	}

	public ${table.className}List(EI${table.className}[] acts) {
		size = acts.length;

		elementData = new Object[size];
		System.arraycopy(acts, 0, elementData, 0, size);
	}

	
	protected Object getUSMObject(Object obj) {
		${table.className}DAO ${table.fieldName}DAO = (${table.className}DAO) obj;
		try {
			EI${table.className} result = Db${table.className}Manager.getInstance()
					.loadByKey(${table.fieldName}DAO.get${table.pkClassName}());
			return result;
		} catch (USMException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	protected void prepareGet(int index) {
		if (index >= prefetchIndex) {
			// the prefetched objects used up , need load more
			prefetch();
		}
	}


	private void prefetch() {
		int length = prefetchIndex + PREFETCH_SIZE;
		List v = new ArrayList();
		for (; prefetchIndex < length; prefetchIndex++) {
			if (prefetchIndex >= size()) {
				break;
			}
			${table.className}DAO ${table.fieldName}DAO = (${table.className}DAO) elementData[prefetchIndex];
			String uuid = ${table.fieldName}DAO.get${table.pkClassName}();
			v.add(uuid);
		}
		Db${table.className}Manager ${table.fieldName}Manager = (Db${table.className}Manager) Db${table.className}Manager
				.getInstance();
		${table.fieldName}Manager.prepareCache(v);
	}

}
	