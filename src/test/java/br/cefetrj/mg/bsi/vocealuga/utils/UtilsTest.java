package br.cefetrj.mg.bsi.vocealuga.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;

class UtilsTest {

	@Test
	void testInstance() {
		assertNotNull(new Utils());
	}
	@Test
	void testConverterLocalDatetimeToTimestamp() {
		LocalDateTime hoje = LocalDateTime.now();
		Timestamp result = Utils.converterLocalDateTimeToTimestamp(hoje);
		Timestamp expected  = Timestamp.valueOf(hoje); 
		assertEquals(expected,result);
		
		
	}

	@Test
	void testConverterTimestampToLocalDateTime() {
		Timestamp hoje = new Timestamp(new Date().getTime());
		LocalDateTime expected = hoje.toLocalDateTime();
		LocalDateTime result = Utils.converterTimestampToLocalDateTime(hoje);
		assertEquals(expected,result);
		
	}

}
