package br.cefetrj.mg.bsi.vocealuga.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ConnectionFactoryTest {

	private ConnectionFactory conn = ConnectionFactory.getInstance("database-external.properties");
	
	
	@Test
	void testIfDatabasePropertiesIfExist() {
		assertTrue(ConnectionFactory.fileIsExist());

	}
	
	@Test
	void testIfDatabasePropertiesIfExistWithFileName() {
		assertTrue(ConnectionFactory.fileIsExist());

	}
	@Test
	void testGetValueOfDatabaseName() {
		String result = ConnectionFactory.getValueOfProps("DATABASE_NAME");
		String expected = "vocealuga";
		assertEquals(expected,result);
	}
	@Test
	void testGetValueOfDatabasePassword() {
		String result = ConnectionFactory.getValueOfProps("DATABASE_PASSWORD");
		String expected = "vocealuga";
		assertEquals(expected,result);
	}
	
	@Test
	void testGetValueOfDatabaseHost() {
		String result = ConnectionFactory.getValueOfProps("DATABASE_HOST");
		String expected = "localhost";
		assertEquals(expected,result);
	}
	@Test
	void testGetUrl() {
		String expected = "jdbc:mysql://localhost:3305/vocealuga";
		String resulted = ConnectionFactory.getUrl();
		assertEquals(expected,resulted);
	}
	
	
	
	
	

}
