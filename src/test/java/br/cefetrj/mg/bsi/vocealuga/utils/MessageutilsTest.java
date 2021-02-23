package br.cefetrj.mg.bsi.vocealuga.utils;

import static org.junit.Assert.*;

import org.junit.Test;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.format;
public class MessageutilsTest {



	@Test
	public void testFormatName() {
		String msg = "Meu nome é %s";
		String args = "Cristian";
		String expected = "Meu nome é Cristian";
		String result = format(msg,args);
		assertEquals(expected,result);
	}

}
