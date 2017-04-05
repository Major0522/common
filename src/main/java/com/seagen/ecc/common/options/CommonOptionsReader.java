package com.seagen.ecc.common.options;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seagen.ecc.utils.ExceptionUtils;

/**
 * 公共配置信息读取器。
 * 
 * @version	1.0 2010-10-13 
 * @author	吴天斌
 */
public class CommonOptionsReader {
    /**
     * 读取公共配置选项信息。
     * 
     * @return 公共配置选项信息。
     * @throws IOException 
     */
    public static CommonOptions readOptions() throws IOException, 
            FileNotFoundException {
        return readOptions(FILE_NAME);
    }    

    public static CommonOptions readOptions(String configFile) throws IOException,
            FileNotFoundException {
        CommonOptions config = (CommonOptions) JsonReader.fromFile(configFile, 
        		CommonOptions.class);
        
        return config;
    }   
    
    public static void main(String[] args) {
        try {
            CommonOptions config = CommonOptionsReader.readOptions();
            System.out.print(config.toString());
        } catch (IOException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }   
    }
    
    private static final String FILE_NAME = "/conf/Common.json";
    
    private final static Logger log = LoggerFactory.getLogger(CommonOptionsReader.class);
}
