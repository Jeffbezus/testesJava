package cursoSelenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1000, 1000));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	

	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void cadastro() {
		page.setNome("Jeferson");
		page.setSobrenome("Guedes");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();
		
		Assert.assertTrue(dsl.getTextBy(By.id("resultado")).startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.getTextBy(By.id("descNome")).endsWith("Jeferson"));
		page.verificarSobrenomeCadastro("Sobrenome: Guedes");
		page.verificarSexoCadastro("Sexo: Masculino");
		page.verificarComidaCadastro("Comida: Pizza");
		page.verificarEscolaridadeCadastro("Escolaridade: mestrado");
		page.verificarEsporteCadastro("Esportes: Natacao");
	}
	
	@Test
	public void validarEsportistaIndeciso() {
		page.setNome("Nome Qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setEsporte("Karate", "O que eh esporte?");
		page.cadastrar();
		page.verificarTextoAlertEAceita("Voce faz esporte ou nao?");
	}
}
