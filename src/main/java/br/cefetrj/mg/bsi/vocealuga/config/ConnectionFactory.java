package br.cefetrj.mg.bsi.vocealuga.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static Properties props = new Properties();
	private static String path = System.getProperty("user.dir");
	private  String filename = "database.properties";
	private  String fullpath = path.concat("/src/main/java/br/cefetrj/mg/bsi/vocealuga/config").concat(String.format("/%s",filename));
	private static File file = null;
	private static FileInputStream  fileInput = null;
	private static ConnectionFactory instance = null;
	private  Connection conn = null;


	private ConnectionFactory(String filename) {
		try {
			fullpath = path.concat("/src/main/java/br/cefetrj/mg/bsi/vocealuga/config").concat(String.format("/%s",filename));
			file = new File(fullpath);
			fileInput = new FileInputStream(file);
			Class.forName(getValueOfProps("CLASS_FORNAME"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private ConnectionFactory() {
		try {
			fullpath = path.concat("/src/main/java/br/cefetrj/mg/bsi/vocealuga/config").concat(String.format("/%s",filename));
			file = new File(fullpath);
			fileInput = new FileInputStream(file);
			Class.forName(getValueOfProps("CLASS_FORNAME"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public Connection getConn() {

		try {
			if( conn == null || conn.isClosed())
				conn = DriverManager.getConnection(getUrl(),getValueOfProps("DATABASE_USER"),getValueOfProps("DATABASE_PASSWORD"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static ConnectionFactory getInstance(){
		if (instance == null)
			instance =  new ConnectionFactory();
		return instance;
	}
	public static ConnectionFactory getInstance(String filename){
		if (instance == null)
			instance =  new ConnectionFactory(filename);
		return instance;
	}

	public static boolean fileIsExist() {
		return file.exists();
	}

	public static String getValueOfProps(String key) {
		try {
			props.load(fileInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props.getProperty(key);
	}
	public static String getUrl() {
		// TODO Auto-generated method stub
		String host = getValueOfProps("DATABASE_HOST");
		int port = Integer.parseInt(getValueOfProps("DATABASE_PORT"));
		String databaseName = getValueOfProps("DATABASE_NAME");
		return String.format("jdbc:mysql://%s:%d/%s",host,port,databaseName);

	}
	
	



}
