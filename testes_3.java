package cursoSelenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class desafio39 {
	
	private WebDriver driver;
	private DSL dsl;
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void validarNome() {
		dsl.buttonClick("elementosForm:cadastrar");
		dsl.compararTextoAlert("Nome eh obrigatorio");
	}
	@Test
	public void validarSobrenome() {
		dsl.escreve("elementosForm:nome", "Jeferson");
		dsl.buttonClick("elementosForm:cadastrar");
		dsl.compararTextoAlert("Sobrenome eh obrigatorio");
	}
	@Test
	public void validarComida() {
		dsl.escreve("elementosForm:nome", "Jeferson");
		dsl.escreve("elementosForm:sobrenome", "Guedes");
		dsl.buttonClick("elementosForm:sexo:0");
		dsl.buttonClick("elementosForm:comidaFavorita:0");
		dsl.buttonClick("elementosForm:comidaFavorita:3");
		dsl.buttonClick("elementosForm:cadastrar");
		dsl.compararTextoAlert("Tem certeza que voce eh vegetariano?");
	}
		
	@Test
	public void validarEsporte() {
		dsl.escreve("elementosForm:nome", "Jeferson");
		dsl.escreve("elementosForm:sobrenome", "Guedes");
		dsl.buttonClick("elementosForm:sexo:0");
		dsl.buttonClick("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.buttonClick("elementosForm:cadastrar");
		dsl.compararTextoAlert("Voce faz esporte ou nao?");
	}
		
}
