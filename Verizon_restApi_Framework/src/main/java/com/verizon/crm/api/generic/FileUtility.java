package com.verizon.crm.api.generic;

import java.io.FileInputStream;
/*
 * @author Saif
 */
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	/*
	 * used to read the datafrom properties file based on key
	 * @param key
	 * @return 
	 * @throws Throwable
	 */
	// property file
public String getDataFromPropertyFile(String key) throws IOException {
	FileInputStream fis = new FileInputStream("./configEnvData/configEnvdata.properties");
	Properties prop = new Properties();
	prop.load(fis);
	 String data = prop.getProperty(key);
	return data;
}


}
