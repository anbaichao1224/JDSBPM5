package net.itjds.fdt.db.fdtoafwbl.database;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collection;

import net.itjds.usm2.*;
import net.itjds.fdt.db.fdtoafwbl.*;
import net.itjds.usm2.db.*;
import net.itjds.fdt.db.fdtoafwbl.inner.*;

	public class FdtOaFwblList extends DbUSMList implements Serializable {

	private static int PREFETCH_SIZE = 30;

	int prefetchIndex = 0;

	public FdtOaFwblList() {
		super();
	}

	
	public FdtOaFwblList(Collection c) {
		super(c);
	}

	public FdtOaFwblList(EIFdtOaFwbl[] acts) {
		size = acts.length;

		elementData = new Object[size];
		System.arraycopy(acts, 0, elementData, 0, size);
	}

	
	protected Object getUSMObject(Object obj) {
		FdtOaFwblDAO fdtOaFwblDAO = (FdtOaFwblDAO) obj;
		try {
			EIFdtOaFwbl result = DbFdtOaFwblManager.getInstance()
					.loadByKey(fdtOaFwblDAO.getUuid());
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
			FdtOaFwblDAO fdtOaFwblDAO = (FdtOaFwblDAO) elementData[prefetchIndex];
			String uuid = fdtOaFwblDAO.getUuid();
			v.add(uuid);
		}
		DbFdtOaFwblManager fdtOaFwblManager = (DbFdtOaFwblManager) DbFdtOaFwblManager
				.getInstance();
		fdtOaFwblManager.prepareCache(v);
	}

}
