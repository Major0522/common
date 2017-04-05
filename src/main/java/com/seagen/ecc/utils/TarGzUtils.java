package com.seagen.ecc.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

public class TarGzUtils {

	public static void unTarGz(String targzFile, String outFilePath) {
		File srcTarGzFile = new File(targzFile);// 要解压缩的tar.gz文件对象
		String destDir = outFilePath;// 把解压的文件放置outFilePath的目录下面
		boolean boo = false;// 是否压缩成功

		try {
			unpack(srcTarGzFile, destDir);
			boo = true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// 清理操作
			if (!boo) {
				FileUtils.deleteDirectory(new File(destDir));
			}
		}
	}
	
	public static void targz(String srcFileName, String targetFileName) {
		try {
			File tarFile = TarUtils.tar(srcFileName, targetFileName);
			File gzFile = new File(tarFile + ".gz"); // 将要生成的压缩文件
			gz(tarFile, gzFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected static void gz(String srcTarFileName, String targetFileName) {
		File tarFile = new File(srcTarFileName);
		File gzFile = new File(tarFile + ".gz"); // 将要生成的压缩文件
		gz(tarFile, gzFile);
	}
	
	private static void gz(File tarFile, File gzFile) {
		GZIPOutputStream out = null;
		InputStream in = null;
		boolean boo = false; // 是否成功

		try {
			in = new FileInputStream(tarFile);
			out = new GZIPOutputStream(new FileOutputStream(gzFile), 1024 * 2);
			byte b[] = new byte[1024 * 2];
			int length = 0;

			while ((length = in.read(b)) != -1) {
				out.write(b, 0, length);
			}

			boo = true;
		} catch (Exception ex) {
			throw new RuntimeException("Compress the tar file to GZ fault!", ex);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				
				if (in != null) {
					in.close();
				}	
			} catch (IOException ex) {
				throw new RuntimeException("Close TStream Fault!", ex);
			} finally {
				if (!boo) {
					tarFile.delete();
					
					if (gzFile.exists()) {
						gzFile.delete();
					}	
				}
			}
		}
	}

	/**
	 * 解压tar.gz 文件.
	 * 
	 * @param file
	 *            要解压的tar.gz文件对象.
	 * @param outputDir
	 *            要解压到某个指定的目录下.
	 * @throws IOException
	 */
	public static void unpack(File file, String outputDir) throws IOException {
		TarInputStream tarIn = null;
		try {
			tarIn = new TarInputStream(new GZIPInputStream(
					new BufferedInputStream(new FileInputStream(file))),
					1024 * 2);
			FileUtils.createDirectory(outputDir, null);

			TarEntry entry = null;
			while ((entry = tarIn.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					FileUtils.createDirectory(outputDir, entry.getName());
				} else {
					File tmpFile = new File(outputDir + "/" + entry.getName());
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
	
	public static void main(String[] args) {
		TarGzUtils.unTarGz("d:\\temp\\20120118.tar.gz", "d:\\temp");
	}

}
