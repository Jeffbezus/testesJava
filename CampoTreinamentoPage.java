package cursoSelenium;

import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	private DSL dsl;
	
	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}
	
	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);;
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);;
	}
	
	public void setSexoMasculino() {
		dsl.buttonClick("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino() {
		dsl.buttonClick("elementosForm:sexo:1");
	}
	
	public void setComidaCarne() {
		dsl.buttonClick("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFrango() {
		dsl.buttonClick("elementosForm:comidaFavorita:1");
	}
	
	public void setComidaPizza() {
		dsl.buttonClick("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano() {
		dsl.buttonClick("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String text) {
		dsl.selectByVisibleText("elementosForm:escolaridade", text);
	}
	
	public void setEsporte(String... valores) {
		for(String valor: valores)
			dsl.selectByVisibleText("elementosForm:esportes", valor);
	}
	
	public void cadastrar() {
		dsl.buttonClick("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro() {
		return dsl.obterValorCampo("resultado");
	}
	
	public String verificarNomeCadastro() {
		return dsl.obterValorCampo("descNome");
	}
	public void verificarSobrenomeCadastro(String sobrenome) {
		dsl.comparaçãoTexto(sobrenome, "descSobrenome");
	}
	public void verificarSexoCadastro(String sexo) {
		dsl.comparaçãoTexto(sexo, "descSexo");
	}
	public void verificarComidaCadastro(String comida) {
		dsl.comparaçãoTexto(comida, "descComida");
	}
	public void verificarEscolaridadeCadastro(String escolaridade) {
		dsl.comparaçãoTexto(escolaridade, "descEscolaridade");
	}
	public void verificarEsporteCadastro(String esporte) {
		dsl.comparaçãoTexto(esporte, "descEsportes");
	}
	
	public void verificarTextoAlertEAceita(String text) {
		dsl.compararTextoAlert(text);
		dsl.aceitarAlert();
	}
	
	
	
	
	public void verificarNome(String nome) {
		dsl.comparaçãoTextoAtributo(nome, "elementosForm:nome");
	}
}
