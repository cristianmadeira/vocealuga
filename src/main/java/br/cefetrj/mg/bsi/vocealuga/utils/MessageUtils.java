package br.cefetrj.mg.bsi.vocealuga.utils;

import br.cefetrj.mg.bsi.vocealuga.enums.MessageEnums;

public  class MessageUtils {
	public static final String FIELD_IS_BLANK = "O(A) %s não pode está em branco.";
	
	public static String format(String msg, Object ...args){
		return String.format(msg,args);
	}
	public static String format(MessageEnums msg, Object ...args){
		return String.format(msg.getValue(),args);
	}
}
