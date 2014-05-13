package kzxd.electronicfile.action;

import kzxd.electronicfile.file.CopyFileAction;

public class ConvertTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String oldfile = "d:\\jdsbpm4\\file\\file\\electronicfile\\2011\\12\\b0e60d-13421f592ce-2f87dce8d8643a73d4d0ede3f3cf44cb\\b0e60d-13421f5936b-2f87dce8d8643a73d4d0ede3f3cf44cb.doc";
		String oldfile = "d:\\jdsbpm4\\aaabbb.doc";
		String newfile = "d:\\jdsbpm4\\testpdf.pdf";
		new CopyFileAction().fileconvert(oldfile, newfile, "doc");

	}

}
