package br.cefetrj.mg.bsi.vocealuga.enums;

public enum MessageEnums {

	INSERT_SUCCESS("%s inserido(a)(s) com sucesso."),
	UPDATE_SUCCESS("%s atualizado(a)(s) com sucesso."),
	DELETE_SUCCESS("%s excluído(a)(s) com sucesso."),
	INSERT_ERROR("Erro ao inserir %s."),
	UPDATE_ERROR("Erro ao atualizar %s."),
	DELETE_ERROR("Erro ao excluir %s.");
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
