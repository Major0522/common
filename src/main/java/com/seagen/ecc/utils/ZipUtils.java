package com.seagen.ecc.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 压缩 解压缩
 * 
 * @author chendaoxing
 * 
 */
public class ZipUtils {
	/**
	 * 压缩文件
	 * @param fromZipFileArray 源文件
	 * @param toZipFile 目标文件(压缩后)
	 * @throws Exception
	 */
	 public static void zip(File[] fromZipFileArray ,File toZipFile) throws Exception  
	    {  
	      
	        if (!toZipFile.exists())  
	        {  
	        	toZipFile.createNewFile();  
	        }  
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(toZipFile));  
	        for (File f : fromZipFileArray)  
	        {  
	            writeToZip(out, f, "");  
	        }  
	        out.close();  
	       
	    }  
	    /**
	     * 解决压缩文件
	     * @param zipFile 压缩文件
	     * @param unzipFilePath 解压目录
	     * @throws Exception
	     */
	    public static void unzip(File zipFile,File unzipFilePath) throws Exception  
	    {  
	        ZipFile zipfile = new ZipFile(zipFile);  
	        Enumeration<?> enu = zipfile.entries();  
	        while(enu.hasMoreElements())  
	        {  
	            ZipEntry entry = (ZipEntry)enu.nextElement();  
	            writeToDir(zipfile, entry, new File(unzipFilePath ,entry.getName()));  
	              
	        }  
	    }  
	        
	    private static void writeToZip(ZipOutputStream out,File fromZipFile,String base) throws Exception  
	    {  
	        if (fromZipFile.isDirectory())  
	        {  
	            for (File file : fromZipFile.listFiles())  
	            {  
	                writeToZip(out, file, base + fromZipFile.getName() + File.separator);  
	            }  
	        }  
	        else  
	        {  
	            //add this file   
	            addFileToZip(out,fromZipFile,base + fromZipFile.getName());  
	        }  
	    }  
	      
	    private static void addFileToZip(ZipOutputStream out,File file,String base) throws Exception  
	    {  
	        byte[] buff = new byte[1024];  
	        int bytesRead = -1;  
	        ZipEntry entry = new ZipEntry(base);  
	        out.putNextEntry(entry);  
	        InputStream in = new BufferedInputStream(new FileInputStream(file));  
	        while (-1 != (bytesRead = in.read(buff, 0, buff.length))) {  
	            out.write(buff, 0, bytesRead);  
	        }  
	        in.close();  
	        out.flush();  
	    }  
	      
	
	    private static void writeToDir(ZipFile zip, ZipEntry entry, File toFile) throws Exception  
	    {  
	        if (!entry.isDirectory()) {  
	            File file = toFile.getParentFile();  
	            if (!file.exists())  
	            {  
	                file.mkdirs();  
	            }  
	            FileOutputStream fos = new FileOutputStream(toFile);  
	            byte[] buffer = new byte[1024];  
	            InputStream is = zip.getInputStream(entry);  
	            int len;  
	            while((len = is.read(buffer,0,buffer.length)) != -1)  
	            {  
	                fos.write(buffer,0,len);  
	            }  
	            fos.close();  
	        }  
	    }  
	    
	    
	    public static void main(String[] args) throws Exception{
	    	zip(new File[]{
	    			new File("D:\\test\\src\\20090601150301903600.jpg"),
	    			new File("D:\\test\\src\\images.jpg"),
	    			new File("D:\\test\\src\\print.png"),
	    			new File("D:\\test\\src\\s3.psd")
	    	},new File("D:\\test\\zip\\a.zip"));
	    	
	    	unzip(new File("D:\\test\\zip\\a.zip"),new File("D:\\test\\unzip\\a\\"));
	    }
	}  
