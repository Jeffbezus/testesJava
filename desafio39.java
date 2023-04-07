package cursoSelenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class desafio39 {
	WebDriver driver = new FirefoxDriver();
	@Before
	public void inicializa() {
		driver.manage().window().setSize(new Dimension(1000, 1000));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	@Test
	public void validarNome() {
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
	}
	@Test
	public void validarSobrenome() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Jeferson");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
	}
	@Test
	public void validarComida() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Jeferson");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Guedes");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
	}
		
	@Test
	public void validarEsporte() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Jeferson");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Guedes");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Futebol");
		combo.selectByVisibleText("O que eh esporte?");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
	}
		
}
