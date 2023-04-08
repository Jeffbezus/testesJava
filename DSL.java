package cursoSelenium;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	private DSL dsl;
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	public void escreve(String id_campo, String text) {
		driver.findElement(By.id(id_campo)).sendKeys(text);
	}
	public void escreveBy(By by, String text) {
		driver.findElement(by).sendKeys(text);
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	public Boolean isRadioMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	public void selecionarCombo(String id, String text) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(text);
		
	}
	public String comboSelecionado(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void deselectCombo(String id,String text) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(text);
	}
	public void buttonClick(String id) {
		driver.findElement(By.id(id)).click();;
	}
	public String buttonAttribute(String id) {
		WebElement botao = driver.findElement(By.id(id));
		return botao.getAttribute("value");
	}
	public void linkClick(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
	}
	public String getTextByID(String id) {
		return driver.findElement(By.id(id)).getText();
	}
	public String getTextBy(By by) {
		return driver.findElement(by).getText();
	}
	public void selectByVisibleText(String id, String text) {
		new Select(driver.findElement(By.id(id))).selectByVisibleText(text);	
	}
	public void comparaçãoTexto(String text, String id) {
		dsl = new DSL(driver);
		Assert.assertEquals(text, dsl.getTextBy(By.id(id)));
	}
	public void comparaçãoTextoBy(String text, By by) {
		dsl = new DSL(driver);
		Assert.assertEquals(text, dsl.getTextBy(by));
	}
	public void comparaçãoTextoAtributo(String text, String id) {
		dsl = new DSL(driver);
		Assert.assertEquals(text, driver.findElement(By.id(id)).getAttribute("value"));
	}
	public void aceitarAlert() {
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
	}
	public void recusarAlert() {
		driver.switchTo().alert().dismiss();
		
	}
	public void compararTextoAlert(String text) {
		Assert.assertEquals(text, driver.switchTo().alert().getText());
	}
	public void escreverAlert(String text) {
		driver.switchTo().alert().sendKeys(text);
	}
	public void clickFrame(String idFrame, String idFrameButton) {
		driver.switchTo().frame(idFrame);
		driver.findElement(By.id(idFrameButton)).click();
		
	}
}
