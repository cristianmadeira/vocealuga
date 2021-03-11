package br.cefetrj.mg.bsi.vocealuga.config;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnectionFactoryTest {

	private ConnectionFactory instance = null;
	private Connection conn = null;

	@BeforeEach
	void setUp() {
		this.instance = ConnectionFactory.getInstance();
		this.conn = this.instance.getConn();
	}
	
	@Test
	void testIfDatabasePropertiesIfExist() {
		assertTrue(this.instance.fileIsExist());

	}

	@Test
	void testGetValueOfDatabaseName() {
		String result = this.instance.getValueOfProps("DATABASE_NAME");
		String expected = "vocealuga";
		assertEquals(expected,result);
	}
	@Test
	void testGetValueOfDatabasePassword() {
		String result = this.instance.getValueOfProps("DATABASE_PASSWORD");
		String expected = "vocealuga";
		assertEquals(expected,result);
	}

	@Test
	void testGetValueOfDatabaseHost() {
		String result = this.instance.getValueOfProps("DATABASE_HOST");
		String expected = "mariadb-vocealuga";
		assertEquals(expected,result);
	}
	@Test
	void testGetUrl() {
		String expected = "jdbc:mysql://mariadb-vocealuga:3306/vocealuga";
		String resulted = this.instance.getUrl();
		assertEquals(expected,resulted);
	}
	@Test
	void testGetConn() {
		assertEquals(this.conn, this.instance.getConn());
	}
	@Test
	void testGetConAfterClose() {
		assertNotNull(this.instance.getConn());
	}






}
