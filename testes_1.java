package cursoSelenium;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class TesteCampoTreinamento {
	
	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 800));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
		
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	@Test
	public void testeNome() {
		page.setNome("Jeferson");
		page.verificarNome("Jeferson");
	}
	
	@Test
	public void testeTextArea() {
		dsl.escreve("elementosForm:sugestoes", "teste");
		dsl.comparaçãoTextoAtributo("teste", "elementosForm:sugestoes");
	}
	
	@Test
	public void testeRadioButton() {
		dsl.buttonClick("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void testeCheckBox() {
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void testeCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		Assert.assertEquals("Mestrado", dsl.comboSelecionado("elementosForm:escolaridade"));
	}
	@Test
	public void testeCombo2() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}
	@Test
	public void testeComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		
		dsl.deselectCombo("elementosForm:esportes", "Corrida");
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(5, options.size());
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Karate") || option.getText().equals("Natacao")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
		
	}
	
	@Test
	public void testeInteragirBotoes() {
		dsl.buttonClick("buttonSimple");
		dsl.comparaçãoTextoAtributo("Obrigado!", "buttonSimple");
	}
	@Test
	//@Ignore
	public void testeInteragirLinks() {
		dsl.linkClick("Voltar");
		dsl.comparaçãoTexto("Voltou!", "resultado");
	}
	@Test
	public void verificarTexto() {
		dsl.comparaçãoTextoBy("Campo de Treinamento", By.tagName("h3"));
		dsl.comparaçãoTextoBy("Cuidado onde clica, muitas armadilhas...", By.className("facilAchar"));
	}
}
