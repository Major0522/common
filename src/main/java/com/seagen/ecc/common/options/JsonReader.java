package com.seagen.ecc.common.options;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

/**
 * Json配置信息读取器。
 * 
 * @version 1.0 2010-10-15
 * @author 吴天斌
 */
public class JsonReader {

	/**
	 * 读取配置选项信息。
	 * 
	 * @return 配置选项信息
	 */
	public static <T> T fromFile(String fileName,  Class<T> clazz) throws IOException,
			FileNotFoundException {
        URL url = JsonReader.class.getResource(fileName);
        String path = URLDecoder.decode(url.getPath(),"UTF-8");

    	T config = readObject(path,clazz);
    	
    	return config;
	}
	
	/**
	 * 将对象序列化成Json字符串
	 * 
	 * @param o
	 * @return
	 */
	public static String objectToString(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json; // 返回字符串
	}

	/**
	 * 将对象序列化成格式化的Json字符串
	 * 
	 * @param o
	 * @return
	 */
	public static String objectToPrettyString(Object o) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
		String prettyJson = writer.writeValueAsString(o);
		return prettyJson;
	}

	/**
	 * 将字符串写入文件
	 * 
	 * @param path
	 *            文件路径
	 * @param string
	 *            写入的内容
	 */
	public static void writeStringToFile(String path, String string)
			throws IOException {
		FileWriter fileWriter;
		fileWriter = new FileWriter(path);
		fileWriter.write(string);
		fileWriter.flush();
		fileWriter.close();

	}

	/**
	 * 从Json配置文件中读取配置并反序列化成配置对象
	 * 
	 * @param path
	 *            Json配置文件路径
	 * @param c
	 *            反序列化的类型
	 * @return 反序列化对象
	 */
	public static <T> T readObject(String path, Class<T> c) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(path);
		T object = null;
		object = mapper.readValue(file, c);

		return object;
	}

}
