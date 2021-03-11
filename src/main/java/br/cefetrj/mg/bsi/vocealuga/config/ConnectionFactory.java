package br.cefetrj.mg.bsi.vocealuga.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {

	private static Properties props = null;
	private static String path = null;
	private  String filename = null;
	private  String fullpath = null;
	private static File file = null;
	private static FileInputStream  fileInput = null;
	private static ConnectionFactory instance = null;
	private  Connection conn = null;
 
	private final static Logger log = LogManager.getLogger(ConnectionFactory.class);

	private void setUp() throws FileNotFoundException {
		props = new Properties();
		path = System.getProperty("user.dir");
		filename = "database.properties";
		fullpath = path.concat("/src/main/java/br/cefetrj/mg/bsi/vocealuga/config").concat(String.format("/%s",filename));
		file = new File(fullpath);
		fileInput = new FileInputStream(file);
	}
	
	private ConnectionFactory() {
		try {
			this.setUp();
			Class.forName(getValueOfProps("CLASS_FORNAME"));
		} catch (FileNotFoundException | ClassNotFoundException e) {
			log.error(e.getMessage());
		}
	}
	

	public Connection getConn() {

		try {
			if( conn == null || conn.isClosed())
				conn = DriverManager.getConnection(getUrl(),getValueOfProps("DATABASE_USER"),getValueOfProps("DATABASE_PASSWORD"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return conn;
	}
	
	public static ConnectionFactory getInstance(){
		if (instance == null)
			instance =  new ConnectionFactory();
		return instance;
	}
	
	public boolean fileIsExist() {
		return file.exists();
	}

	public String getValueOfProps(String key) {
		try {
			props.load(fileInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return props.getProperty(key);
	}
	public  String getUrl() {
		// TODO Auto-generated method stub
		String host = getValueOfProps("DATABASE_HOST");
		int port = Integer.parseInt(getValueOfProps("DATABASE_PORT"));
		String databaseName = getValueOfProps("DATABASE_NAME");
		return String.format("jdbc:mysql://%s:%d/%s",host,port,databaseName);

	}
	
	



}
