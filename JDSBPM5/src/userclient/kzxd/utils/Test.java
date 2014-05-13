package kzxd.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Test {
	static final int wdDoNotSaveChanges = 0;// 不保存待定的更改

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String oldfile = "c:\\1.docx";
		String newfile = "c:\\2.docx";
		ActiveXComponent app = null;
		try {
			app = new ActiveXComponent("Word.Application");
			app.setProperty("Visible",new Variant(false));
			Dispatch docs = app.getProperty("Documents").toDispatch();
			Dispatch doc = Dispatch.call(docs,
					"Open", 
					oldfile,// FileName
					false,// ConfirmConversions
					true // ReadOnly
			).toDispatch();
			
			Dispatch.call(doc,
					"AcceptAllRevisions");

			File tofile = new File(newfile);
			if (tofile.exists()) {
				tofile.delete();
			}
			Dispatch.call(doc,
					"SaveAs", 
					newfile);

			Dispatch.call(doc, "Close", false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (app != null)
				app.invoke("Quit", new Variant(wdDoNotSaveChanges));
				//app.
		}
	}

}
