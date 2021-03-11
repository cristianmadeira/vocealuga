package br.cefetrj.mg.bsi.vocealuga.cucumber.step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class GrupoStep {

	WebDriver driver = null;
	WebDriverWait wait = null;
	private String nome = null;
	@Before
	public void init() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 60);

	}

	@Dado("que eu estou logado como administrador")
	public void que_estou_como_administrador() {
		// Write code here that turns the phrase above into concrete actions
		assertTrue(!false);
	}

	@E("entro na página de cadastro de grupos")
	public void na_página_de_cadastro_de_grupos() {
		// Write code here that turns the phrase above into concrete actions
		driver.get("http://localhost:8081/grupos/create");

	}
	@E("na pagina de atualizar")
	public void na_pagina_de_atualizar_grupos() {
		driver.get("http://localhost:8081/grupos");
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td[3]/div/div[1]/a/button")));
		element.click();
		
	}

	@Quando("eu preencho o nome do grupo {string}")
	public void eu_preencher_o_nome_do_grupo_com(String nome) {
		// Write code here that turns the phrase above into concrete actions
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name("Nome")));
		this.nome = nome;
		element.clear();
		element.sendKeys(nome);

	}

	@E("aperto o botão")
	public void apertar_no_botao() {
		// Write code here that turns the phrase above into concrete actions
		WebElement button  = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button")));
		button.click();

	}

	@Entao("verei {string}")
	public void verei_msg(String msg)  {
		// Write code here that turns the phrase above into concrete actions
		String id = this.nome.isBlank()?"error-span":"success-span";
		WebElement span = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
		String result = span.getText();
		assertEquals(msg,result);
		driver.quit();
	}
	
	
  	
  		

}