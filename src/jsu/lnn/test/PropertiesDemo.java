package jsu.lnn.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {
	public static void main(String[] args) throws IOException{
		// Properties 继承于Hashtable
		//Properties 也是散列表，key:value 都是String散列表
		Properties cfg = new Properties();
		//将properties 格式的文本文件，读取为散列表
		cfg.load(new FileInputStream("client.properties"));
		//读取配置文件
		System.out.println(cfg);
		//filename 的值是什么
		String filename = cfg.getProperty("UserFile");
		//输出
		System.out.println(filename);//user.txt
		
	}

}
