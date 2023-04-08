package cursoSelenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testeCampoTreinamento2 {
	
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
	public void verificarTexto() {
		dsl.buttonClick("alert");
		dsl.compararTextoAlert("Alert Simples");
		dsl.aceitarAlert();
		dsl.escreve("elementosForm:nome", "Alert Simples");
		dsl.comparaçãoTextoAtributo("Alert Simples", "elementosForm:nome");
	}
	
	@Test
	public void interagirAlertaAccept() {
		dsl.buttonClick("confirm");
		dsl.compararTextoAlert("Confirm Simples");
		dsl.aceitarAlert();
		dsl.compararTextoAlert("Confirmado");
		dsl.aceitarAlert();
	}
	
	@Test
	public void interagirAlertaDismiss() {
		dsl.buttonClick("confirm");
		dsl.compararTextoAlert("Confirm Simples");
		dsl.recusarAlert();
		dsl.compararTextoAlert("Negado");
		dsl.aceitarAlert();
	}
	
	@Test
	public void interagirPrompt() {
		dsl.buttonClick("prompt");
		dsl.compararTextoAlert("Digite um numero");
		dsl.escreverAlert("12");
		dsl.aceitarAlert();
		dsl.compararTextoAlert("Era 12?");
		dsl.aceitarAlert();
		dsl.compararTextoAlert(":D");
		dsl.aceitarAlert();
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
	public void frames() {
		
		dsl.clickFrame("frame1", "frameButton");	
		dsl.compararTextoAlert("Frame OK!");
		dsl.aceitarAlert();
		driver.switchTo().defaultContent();
		dsl.escreve("elementosForm:nome", "Jeferson");
	}
	
	@Test
	public void popUp() {
		dsl.buttonClick("buttonPopUpEasy");
		driver.switchTo().window("Popup");
		dsl.escreveBy(By.tagName("textarea"), "Deu certo?");
		//driver.close();
		driver.switchTo().window("");
		dsl.escreve("elementosForm:sugestoes", "e agora?");
	}
	
	@Test
	public void popUpSemTitulo() {
		dsl.buttonClick("buttonPopUpEasy");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		dsl.escreveBy(By.tagName("textarea"), "Deu certo?");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		dsl.escreveBy(By.tagName("textarea"), "e agora?");
	}
	
}
