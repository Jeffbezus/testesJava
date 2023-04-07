package cursoSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	public void escreve(String id_campo, String text) {
		driver.findElement(By.id(id_campo)).sendKeys(text);
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();
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
	
	
}
