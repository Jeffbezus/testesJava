package cursoSelenium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	private DSL dsl;
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	/********* TextField e TextArea ************/
	
	public void escreve(String id_campo, String text) {
		driver.findElement(By.id(id_campo)).sendKeys(text);
	}
	
	public void escreveBy(By by, String text) {
		driver.findElement(by).sendKeys(text);
	}
	
	public String obterValorCampo(By by) {
		return driver.findElement(by).getAttribute("value");
	}
	
	/********* Radio e Check ************/
	
	public void clicarRadioOuCheck(String id) {
		driver.findElement(By.id(id)).click();
	}
	public void clicarBy(By by) {
		driver.findElement(by).click();
	}
	
	public Boolean isRadioOuCheckMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	public Boolean isMarcadoBy(By by, String text) {
		return driver.findElement(by).getText().equals(text);
	}
	
	/********* Combo ************/
	
	public void selectCombo(String id, String text) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByValue(text);
		
	}
	public void selectComboBy(By by, String text) {
		WebElement element = driver.findElement(by);
		Select combo = new Select(element);
		combo.selectByVisibleText(text);
		
	}
	
	public void deselectCombo(String id,String text) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(text);
	}
	
	public List<String> obterValoresCombo(String id) {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}
	
	public String comboSelecionado(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	/********* Obter Valores ************/
	
	public String buttonAttribute(String id) {
		WebElement botao = driver.findElement(By.id(id));
		return botao.getAttribute("value");
	}
	public String selectByBy(By by) {
		return driver.findElement(by).getText();	
	}
	/********* Link ************/
	
	public void linkClick(String linkText) {
		driver.findElement(By.linkText(linkText)).click();
	}
	
	/********* Textos ************/
	
	public String getTextByID(String id) {
		return driver.findElement(By.id(id)).getText();
	}
	
	public String getTextBy(By by) {
		return driver.findElement(by).getText();
	}
	
	/********* Alert ************/
	
	public String alertObterTexto() {
		return driver.switchTo().alert().getText();
		
	}
	public String alertObterTextoEAceita() {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();
		return text;
	}
	public String alertObterTextoENega() {
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.dismiss();
		return text;
	}
	public void aceitarAlert() {
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
	}
	public void recusarAlert() {
		driver.switchTo().alert().dismiss();	
	}
	
	public void verificarTextoAlert(String text) {
		Assert.assertEquals(text, driver.switchTo().alert().getText());
	}
	public void escreverAlert(String text) {
		driver.switchTo().alert().sendKeys(text);
	}
	
	/********* Frames e Janelas ************/
	
	public void entrarFrame(String idFrame) {
		driver.switchTo().frame(idFrame);	
	}
	public void sairFrame(String idFrame) {
		driver.switchTo().defaultContent();	
	}
	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}
	
	/********* Verificacoes ************/

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
	
	
	/**************** JS ****************/
	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
	}
	
	/**************** Tabela ****************/

	public void clickInTableButton(String searchColunm, String value, String colunmButton, String idTable) {
		//Search for Column Table
		WebElement table = driver.findElement(By.xpath("//table[@id='elementosForm:tableUsuarios']"));
		int idColumn = getColumnIndex(searchColunm, table);
		
		//Search for Line Table
		int idLine = getLineIndex(value, table, idColumn);
		
		//Search for column button
		int idColumnButton = getColumnIndex(colunmButton, table);
		
		//Click Found Cell Button
		WebElement cell = table.findElement(By.xpath(".//tr["+idLine+"]/td["+idColumnButton+"]"));
		cell.findElement(By.xpath("./input")).click();
	}

	private int getLineIndex(String value, WebElement table, int idColumn) {
		List<WebElement> lines = table.findElements(By.xpath("./tbody/tr/td["+idColumn+"]"));
		int idLine = -1;
		for(int i = 0; i < lines.size(); i++) {
			if(lines.get(i).getText().equals(value)) {
				idLine = i+1;
				break;
			}
		}
		return idLine;
	}

	private int getColumnIndex(String Column, WebElement tabela) {
		List<WebElement> columns = tabela.findElements(By.xpath(".//th"));
		int idColumn = -1;
		for(int i = 0; i < columns.size(); i++) {
			if(columns.get(i).getText().equals(Column)) {
				idColumn = i+1;
				break;
			}	
		}
		return idColumn;
	}
}
