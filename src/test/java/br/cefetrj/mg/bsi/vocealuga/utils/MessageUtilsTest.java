package br.cefetrj.mg.bsi.vocealuga.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.cefetrj.mg.bsi.vocealuga.enums.MessageEnums;

class MessageUtilsTest {

	@Test
	void testFormatStringObjectArray() {
		String message = "Meu nome é %s";
		String args = "Cristian";
		String expected = "Meu nome é Cristian";
		String result = MessageUtils.format(message, args);
		assertEquals(expected, result);

	}

	@Test
	void testFormatMessageEnumsObjectArray() {
		String name = "Cristian";
		String action = "inserido";
		String expected = "O(a)(s) Cristian foi inserido(a)(s) com sucesso";
		String result = MessageUtils.format(MessageEnums.SUCCESS, name, action);
		assertEquals(expected, result);

		String name2 = "Cristian";
		String action2 = "inserir";
		String expected2 = "Error ao inserir Cristian";
		String result2 = MessageUtils.format(MessageEnums.ERROR, action2, name2);
		assertEquals(expected2, result2);

		String arg = "nome";
		String expected3 = "O(a) nome não pode está em branco";
		String result3 = MessageUtils.format(MessageEnums.BLANK_FIELD, arg);
		assertEquals(expected3, result3);

	}

	@Test
	void testGetSaveSuccessMessage() {
		String expected = "O(a)(s) produto foi inserido(a)(s) com sucesso";
		String result = MessageUtils.getSaveSuccessMessage("produto");
		assertEquals(expected, result);
	}

	@Test
	void testGetUpdateSuccessMessage() {
		String expected = "O(a)(s) produto foi atualizado(a)(s) com sucesso";
		String result = MessageUtils.getUpdateSuccessMessage("produto");
		assertEquals(expected, result);
	}

	@Test
	void testGetDeleteSuccessMessage() {
		String expected = "O(a)(s) produto foi excluído(a)(s) com sucesso";
		String result = MessageUtils.getDeleteSuccessMessage("produto");
		assertEquals(expected, result);
	}

	@Test
	void testGetSaveErrorMessage() {
		String expected = "Error ao inserir produto";
		String result = MessageUtils.getSaveErrorMessage("produto");
		assertEquals(expected, result);
	}

	@Test
	void testGetSaveErrorMessageWithDetails() {
		String expected = "Error ao inserir produto: Sem conexão com banco de dados";
		String result = MessageUtils.getSaveErrorMessage("produto", "Sem conexão com banco de dados");
		assertEquals(expected, result);
	}

	@Test
	void testGetUpdateErrorMessage() {
		String expected = "Error ao atualizar produto";
		String result = MessageUtils.getUpdateErrorMessage("produto");
		assertEquals(expected, result);
	}

	@Test
	void testGetUpdateErrorMessageWithDetails() {
		String expected = "Error ao atualizar produto: Sem conexão com banco de dados";
		String result = MessageUtils.getUpdateErrorMessage("produto", "Sem conexão com banco de dados");
		assertEquals(expected, result);
	}

	@Test
	void testGetDeleteErrorMessage() {
		String expected = "Error ao excluir produto";
		String result = MessageUtils.getDeleteErrorMessage("produto");
		assertEquals(expected, result);
	}

	@Test
	void testGetDeleteErrorMessageWithDetails() {
		String expected = "Error ao excluir produto: Sem conexão com banco de dados";
		String result = MessageUtils.getDeleteErrorMessage("produto", "Sem conexão com banco de dados");
		assertEquals(expected, result);
	}

	@Test
	void testGetBlankFieldMessage() {
		String expected = "O(a) nome não pode está em branco";
		String result = MessageUtils.getBlankFieldMessage("nome");
		assertEquals(expected, result);
	}

	@Test
	void testGetEmptyListMessage() {
		String expected = "Não há grupos cadastrado(a)(s)";
		String actual = MessageUtils.getEmptyListMessage("grupos");
		assertEquals(expected, actual);
	}

	@Test
	void testGetInvalidIdMessage() {
		String expected = "Id 0 é inválido";
		String actual = MessageUtils.getInvalidIdMessage(0);
		assertEquals(expected, actual);
	}

	@Test
	void testGetResultNotFoundMessage() {
		String result = "nome";
		String expected = "O(a) nome não foi encontrado";
		String actual = MessageUtils.getResultNotFoundMessage(result);
		assertEquals(expected, actual);
	}
	@Test
	public void testGetDeleteExceptionMessage(){
		String entityName = "cargo";
		int size = 1;
		String toEntityName = "funcionário";
		String expected = "Você não pode excluir este(a)(s) cargo, pois encontra(m)-se 1 funcionário(s) .";
		String actual = MessageUtils.getDeleteExceptionMessage(entityName, size, toEntityName);
		assertEquals(expected, actual);
	}
}
