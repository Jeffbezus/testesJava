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

public class testeCampoTreinamento2 {
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
	public void verificarTexto() {
		driver.findElement(By.id("alert")).click();
		Assert.assertEquals("Alert Simples", driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Alert Simples");
		Assert.assertEquals("Alert Simples", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
	}
	
	@Test
	public void interagirAlertaAccept() {
		driver.findElement(By.id("confirm")).click();
		
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		
		Assert.assertEquals(alerta.getText(), "Confirmado");
		
		alerta.accept();
	}
	
	@Test
	public void interagirAlertaDismiss() {
		driver.findElement(By.id("confirm")).click();
		
		Alert alerta = driver.switchTo().alert();
		
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		
		Assert.assertEquals(alerta.getText(), "Negado");
		alerta.accept();
	}
	
	@Test
	public void interagirPrompt() {
		driver.findElement(By.id("prompt")).click();
		
		Alert prompt = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", prompt.getText());
		prompt.sendKeys("12");
		prompt.accept();

		Assert.assertEquals(prompt.getText(), "Era 12?");
		prompt.accept();
		
		Assert.assertEquals(prompt.getText(), ":D");
		prompt.accept();
	}
	@Test
	public void cadastro() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Jeferson");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Guedes");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");	
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");	
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("jeferson"));
		Assert.assertEquals("guedes", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Pizza", driver.findElement(By.id("descComida")).getText());	
	}
	@Test
	public void frames() {
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	
	@Test
	public void popUp() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		//driver.close();
		driver.switchTo().window("");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("e agora?");
	}
	
	@Test
	public void popUpSemTitulo() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
	}
	
}
