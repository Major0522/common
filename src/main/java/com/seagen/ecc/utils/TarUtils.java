package com.seagen.ecc.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.apache.tools.tar.TarOutputStream;

/**
 * Tar文件打包和解包处理。
 */
public class TarUtils {

	public static void unTar(String tarFile, String outFilePath) {
		File srcTarFile = new File(tarFile); // 要解包的tar文件对象
		String destDir = outFilePath;   // 把解包的文件放置到outFilePath目录下面
		boolean boo = false;  // 是否解包成功

		try {
			unpack(srcTarFile, destDir);
			boo = true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// 清理操作
			if (!boo) {
				// 目标文件夹, 清理
				FileUtils.deleteDirectory(new File(destDir));
			}	
		}

	}

	public static File tar(String srcFileName, String targetFileName)
			throws IOException {
		File srcFile = new File(srcFileName); // 要归档的文件对象
		File targetTarFile = new File(targetFileName); // 归档后的文件名
		TarOutputStream out = null;
		boolean boo = false; // 是否压缩成功

		try {
			out = new TarOutputStream(new BufferedOutputStream(
					new FileOutputStream(targetTarFile)));
			pack(srcFile, out, "", true);
			boo = true;
			return targetTarFile;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} finally {
				// 清理操作
				if (!boo && targetTarFile.exists()) {
					// 归档不成功
					targetTarFile.delete();
				}
			}
		}
	}

	public static void pack(File file, TarOutputStream out, String dir,
			boolean includeNull) throws IOException {
		if (file.isDirectory()) {
			dirPack(file, out, dir, includeNull);
		} else if (file.isFile()) {
			filePack(file, out, dir, includeNull);
		}
	}

	public static void unpack(File file, String outputDir) throws IOException {
		TarInputStream tarIn = null;
		try {
			tarIn = new TarInputStream(new FileInputStream(file), 1024 * 2);
			
			// 创建输出目录
			FileUtils.createDirectory(outputDir, null);

			TarEntry entry = null;
			while ((entry = tarIn.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					// 是目录, 则创建空目录。
					FileUtils.createDirectory(outputDir, entry.getName());
				} else {
					// 是文件
					File tmpFile = new File(outputDir + "/" + entry.getName());
					// 创建输出目录
					FileUtils.createDirectory(tmpFile.getParent() + "/", null);

					OutputStream out = null;
					try {
						out = new FileOutputStream(tmpFile);
						int length = 0;
						byte[] b = new byte[2048];

						while ((length = tarIn.read(b)) != -1) {
							out.write(b, 0, length);
						}
					} catch (IOException ex) {
						throw ex;
					} finally {
						if (out != null) {
							out.close();
						}	
					}
				}
			}
		} finally {
			if (tarIn != null) {
				tarIn.close();
			}
		}
	}

	private static void filePack(File file, TarOutputStream out, String dir,
			boolean includeNull) throws IOException {
		//
		// System.out.println("归档." + dir + file.getName() + "/");
		//
		byte[] bt = new byte[2048 * 2];
		TarEntry ze = new TarEntry(dir + file.getName()); // 构建tar实体

		// 设置压缩前的文件大小
		ze.setSize(file.length());
		// ze.setName(file.getName());//设置实体名称.使用默认名称
		// 将实体放入输出Tar流中
		out.putNextEntry(ze);

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			int i = 0;

			// 循环读出并写入输出Tar流中
			while ((i = fis.read(bt)) != -1) {
				out.write(bt, 0, i);
			}
		} finally {
			if (fis != null) {
				fis.close(); // 关闭输入流
			}
			out.closeEntry();
		}
	}

	private static void dirPack(File file, TarOutputStream out, String dir,
			boolean includeNull) throws IOException {
		File[] listFile = file.listFiles(); // 得出目录下所有的文件对象

		if (listFile.length == 0 && includeNull) {
			// 空目录归档, 将实体放入输出Tar流中
			out.putNextEntry(new TarEntry(dir + file.getName() + "/"));
			//
			// System.out.println("归档." + dir + file.getName() + "/");
			//
			return;
		} else {
			for (File cfile : listFile) {
				// 递归归档
				pack(cfile, out, dir + file.getName() + "/", includeNull);
			}
		}

	}

}
