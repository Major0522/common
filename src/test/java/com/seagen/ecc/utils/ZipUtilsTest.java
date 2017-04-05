package com.seagen.ecc.utils;

import java.io.File;

public class ZipUtilsTest {
	public static void main(String[] args) throws Exception {
		File outFile = new File("C:/temp.zip");
		ZipUtils.zip(new File[] { new File("C:/temp") }, outFile);
		ZipUtils.unzip(outFile, new File("C:/test"));
	}
}
