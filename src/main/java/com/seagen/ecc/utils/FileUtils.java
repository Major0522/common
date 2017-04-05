package com.seagen.ecc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	/**
	 * 读取文件返回文件内容.
	 * 
	 * @param filePath
	 *            文件路径及名称.
	 * @return 文件内容信息串.
	 */
	public static String readToString(String filePath) {
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(filePath);
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			return new String(bytes);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					log.error(ExceptionUtils.getStackTrace(e));
				}
			}
		}

		return "";
	}

	/**
	 * 写文件内容到文件中.
	 * 
	 * @param fileName
	 *            文件名称.
	 * @param fileContext
	 *            文件内容.
	 */
	public static void writerFromString(String fileName, String fileContext) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			fos.write(fileContext.getBytes());
			fos.flush();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					log.error(ExceptionUtils.getStackTrace(e));
				}
			}
		}
	}

	public static boolean copyFile(String sourceFile, String destFile) {
		try {
			org.apache.tools.ant.util.FileUtils.getFileUtils().copyFile(
					sourceFile, destFile);
			return true;
		} catch (IOException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}

		return false;
	}

	public static boolean renameFile(String sourceFile, String destFile) {
		File src = new File(sourceFile);
		File dest = new File(destFile);

		return src.renameTo(dest);
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		return file.delete();
	}

	public static void createDirectory(String outputDir, String subDir) {
		File file = new File(outputDir);
		if (!(subDir == null || subDir.trim().equals(""))) {
			// 子目录不为空
			file = new File(outputDir + "/" + subDir);
		}

		if (!file.exists()) {
			file.mkdirs();
		}
	}
	/**
	 * 
	*
	* @Title: createFile 
	* @Description: 创建文件，自动补上缺少文件夹
	* @return void 
	* @param targer 文件的绝对路径
	 */
	public static void createFile(String targer) {
		createFile(new File(targer)) ;
	}
	/**
	 * 
	*
	* @Title: createFile 
	* @Description: 创建文件，自动补上缺少文件夹
	* @return void 
	* @param targer 文件的绝对路径,包含文件名
	 */
	public static void createFile(File targer) {
		File file = targer.getParentFile();
		if(!file.exists()){
			file.mkdirs();
		}
	}
	/**
	 * 清理文件(目录或文件).
	 */
	public static void deleteDirectory(File file) {
		if (file.isFile()) {
			file.delete(); // 清理文件
		} else {
			File list[] = file.listFiles();
			if (list != null) {
				for (File f : list) {
					deleteDirectory(f);
				}
				file.delete();// 清理目录
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static void writeMap2File(Map map, File file) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			Set set = map.keySet();
			for (Object key : set) {
				String line = key + "=" + map.get(key) + "\r\n";
				fos.write(line.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 序列号对象到文件
	 * 
	 * @param obj
	 * @param file
	 * @return
	 */
	public static boolean saveObj2File(Serializable obj, File file) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 从文件读取系列化对象
	 * 
	 * @param cla
	 * @param file
	 * @return
	 */
	public static Object readObjFromFile(File file) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 如果文件目录不存在,则创建
	 * 
	 * @param file
	 * @return
	 */
	public static boolean mkdirs(File file) {
		if (!file.exists()) {
			file.mkdirs();
		}
		return true;
	}

	private final static Logger log = LoggerFactory.getLogger(FileUtils.class);
}
