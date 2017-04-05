package com.seagen.ecc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http 请求工具,不依赖第三方库
 * 
 * @author jimbo
 */
public class HttpUtils {

    public static void main(String[] args) {
        HttpUtils cli = new HttpUtils();
        // String url = "http://61.145.229.29:9006/MWGate/wmgw.asmx/MongateSendSubmit";
        String url = "http://61.145.229.29:9006/MWGate/wmgw.asmx/MongateGetDeliver";
        // String url = "http://61.145.229.29:9006/MWGate/wmgw.asmx/MongateQueryBalance";
        Map<String, String> data = new HashMap<String, String>();
        String msg = "同事您好，感谢您对此次测试的配合。[123456]";
        data.put("userId", "JC2004");
        data.put("password", "118200");
        data.put("pszMobis", "13528483097,13528483017");
        data.put("pszMsg", msg);
        data.put("iMobiCount", "2");
        data.put("pszSubPort", "*");
        data.put("MsgId", "123343");
        String ret = cli.httpPost(url, data);
        System.out.println(ret);
        System.out.println(XmlUtils.xml2List(ret, "ArrayOfString"));
    }

    private Logger log = LoggerFactory.getLogger(getClass());

    /** 编码 */
    private String charset = "UTF-8";

    /** 连接超时时间 ms */
    private Integer connectTimeout = 500;

    /** 读取超时时间 ms */
    private Integer readTimeout = 60 * 1000;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String httpPost(String url, Map<String, String> args) {
        StringBuilder data = new StringBuilder();
        String ret = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            if (args != null) {
                for (String key : args.keySet()) {
                    data.append("&" + key + "=" + URLEncoder.encode(args.get(key), charset));
                }
                byte[] buf = data.toString().getBytes(charset);
                conn.setRequestProperty("Content-length", "" + buf.length);
                conn.setDoOutput(true);
                os = conn.getOutputStream();
                os.write(buf);
            }
            is = conn.getInputStream();
            ret = IOUtils.toString(is);
            if (log.isDebugEnabled()) {
                log.debug(String.format("url=%s, ret=%s", url, ret));
            }
        } catch (IOException e) {
            log.error("httpPost request failed,url=" + url, e);
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(is);
        }
        return ret;
    }

    public String httpGet(String url, Map<String, String> args) {
        StringBuilder data = new StringBuilder();
        data.append(url);
        String ret = null;
        InputStream is = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(data.toString()).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            if (args != null) {
                data.append("?");
                for (String key : args.keySet()) {
                    data.append("&" + key + "=" + URLEncoder.encode(args.get(key), charset));
                }
            }
            is = conn.getInputStream();
            ret = IOUtils.toString(is);
            if (log.isDebugEnabled()) {
                log.debug(String.format("url=%s, ret=%s", url, ret));
            }
        } catch (IOException e) {
            log.error("httpGet request failed,url=" + url, e);
        } finally {
            IOUtils.closeQuietly(is);
        }
        return ret;
    }
}
