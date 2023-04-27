package cursoSelenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestePrimeFaces {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(800, 800));
		//driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=1465d");
		dsl = new DSL(driver);
	}
	@After
	public void finaliza() {
		driver.quit();
	}
		@Test
	public void testeRadioPrimeFaces() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=1465d");

		dsl.clicarBy(By.xpath("//*[@id='j_idt344:console']/tbody/tr/td[1]/div/div[2]/span"));
		dsl.clicarBy(By.xpath("//input[@id=\"j_idt344:console:1\"]/../following-sibling::div/span"));
		dsl.clicarBy(By.xpath("//input[@id='j_idt344:city:8']/../..//span"));
		dsl.isRadioOuCheckMarcado("j_idt344:city:8");
		}
	@Test
	public void testeMenuPrimeFaces() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=f83f8");

			//Basic
		dsl.selectComboPrime(By.xpath("//div[@id=\"j_idt343:option\"]/div[3]/span"), By.xpath("//ul[@id=\"j_idt343:option_items\"]/li[4]"));		
		dsl.isMarcadoBy(By.xpath("//label[@id=\"j_idt343:option_label\"]"), "Option3");
			//Grouping
		dsl.selectComboPrime(By.xpath("//div[@id=\"j_idt343:group\"]//span"), By.xpath("//ul[@id=\"j_idt343:group_items\"]/li[8]"));		
		dsl.isMarcadoBy(By.xpath("//label[@id=\"j_idt343:group_label\"]"), "Brazil");
			//Editable¹
		dsl.selectComboPrime(By.xpath("//div[@id=\"j_idt343:city\"]//span"), By.xpath("//li[@id=\"j_idt343:city_2\"]"));		
		dsl.isMarcadoBy(By.xpath("//div[@id=\"j_idt343:city\"]/input"), "Barcelona");
			//Editable²
		dsl.escreveBy(By.xpath("//div[@id=\"j_idt343:city\"]/input"), "Rio de Janeiro");		
		dsl.isMarcadoBy(By.xpath("//div[@id=\"j_idt343:city\"]/input"), "Rio de Janeiro");
			//Advanced
		dsl.selectComboPrime(By.xpath("//form[@id='j_idt343']//div[@id='j_idt343:advanced']//label"), By.xpath("//div[@id='j_idt343:advanced_panel']//table[@id='j_idt343:advanced_table']//tr[@id='j_idt343:advanced_30']//td[.='Brazil']"));
		dsl.isMarcadoBy(By.xpath("//form[@id='j_idt343']//div[@id='j_idt343:advanced']//label"), "Brazil");
			//Lazy
		dsl.selectComboPrime(By.xpath("//form[@id='j_idt343']//div[@id='j_idt343:lazy']/label"), By.xpath("//div[@id='j_idt343:lazy_panel']//ul[@id='j_idt343:lazy_items']/li[@data-label='Option 17']"));	
		dsl.isMarcadoBy(By.xpath("//form[@id='j_idt343']//div[@id='j_idt343:lazy']/label"), "Option 17");
		
	}
	
	
}
