package br.cefetrj.mg.bsi.vocealuga.enums;

public enum MessageEnums {

	SUCCESS("O(a)(s) %s foi %s(a)(s) com sucesso"),
	ERROR("Error ao %s %s"),
	INVALID_ID("Id %d é inválido"),
	EMPTY_LIST("Não há %s cadastrados"),
	BLANK_FIELD("O(a) %s não pode está em branco");
	
	private String value;
	MessageEnums(String string) {
		// TODO Auto-generated constructor stub
		this.setValue(string);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
	
}
