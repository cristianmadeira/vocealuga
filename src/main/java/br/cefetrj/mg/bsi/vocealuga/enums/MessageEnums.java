package br.cefetrj.mg.bsi.vocealuga.enums;

public enum MessageEnums {

	SUCCESS("O(a)(s) %s foi %s(a)(s) com sucesso"), ERROR("Error ao %s %s"), INVALID_ID("Id %d é inválido"),
	EMPTY_LIST("Não há %s cadastrado(a)(s)"), BLANK_FIELD("O(a) %s não pode está em branco"),
	RESULT_NOT_FOUND("O(a) %s não foi encontrado"), DELETE_EXCEPTION("Você não pode excluir este(a)(s) %s, pois encontra(m)-se %d %s(s) .");

	private String value;

	private MessageEnums(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
