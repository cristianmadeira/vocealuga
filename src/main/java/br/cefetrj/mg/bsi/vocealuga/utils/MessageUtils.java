package br.cefetrj.mg.bsi.vocealuga.utils;

import br.cefetrj.mg.bsi.vocealuga.enums.MessageEnums;

public  class MessageUtils {
	
	
	public static String format(String msg, Object ...args){
		return String.format(msg,args);
	}
	public static String format(MessageEnums msg, Object ...args){
		return String.format(msg.getValue(),args);
	}
	
	public static String getSaveSuccessMessage(String str) {
		return format(MessageEnums.SUCCESS,str,"inserido");
	}
	public static String getUpdateSuccessMessage(String str) {
		return format(MessageEnums.SUCCESS,str,"atualizado");
	}
	public static String getDeleteSuccessMessage(String str) {
		return format(MessageEnums.SUCCESS,str,"excluído");
	}
	
	public static String getSaveErrorMessage(String str, String ...details) {
		String message = str;
		if(details.length > 0)
			message = message.concat(format(": %s",details[0]));
		return format(MessageEnums.ERROR,"inserir",message);
	}
	
	public static String getUpdateErrorMessage(String str, String ...details) {
		String message = str;
		if(details.length > 0)
			message = message.concat(format(": %s",details[0]));
		return format(MessageEnums.ERROR,"atualizar",message);
	}
	public static String getDeleteErrorMessage(String str, String ...details) {
		String message = str;
		if(details.length > 0)
			message = message.concat(format(": %s",details[0]));
		return format(MessageEnums.ERROR,"excluir",message);
	}
	public static String getBlankFieldMessage(String fieldName) {
		return format(MessageEnums.BLANK_FIELD,fieldName);
	}
	public static String getEmptyListMessage(String listName) {
		return format(MessageEnums.EMPTY_LIST,listName);
	}
	public static String getInvalidIdMessage(int id) {
		return format(MessageEnums.INVALID_ID,id);
	}
	
}
