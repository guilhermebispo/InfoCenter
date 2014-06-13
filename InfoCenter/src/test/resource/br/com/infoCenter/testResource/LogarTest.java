package br.com.infoCenter.testResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;

public class LogarTest {

	private static WebDriver webDriver;
	
	@Before
	public void configurarRecursos() throws Exception {
		webDriver = new FirefoxDriver();		
	}

	@After
	public void liberarRecursos() {
		webDriver.close();
	}
	
	@Test
	public void entrarComUsuarioAdministradorValido() {

		webDriver.get("http://localhost:8080/InfoCenter/login.jsp");
		FluentWebDriver fluentDriver = new FluentWebDriver(webDriver);
		
		fluentDriver.form(By.id("formLogar")).input(By.name("usuario")).sendKeys("alan.saulo");
		fluentDriver.form(By.id("formLogar")).input(By.name("senha")).sendKeys("senha111");
		
		fluentDriver.form(By.id("formLogar")).submit();
		
		fluentDriver.div(By.id("divUsuarioLogado")).span().getText().shouldBe("Olá ALAN SAULO! (Sair)");
		
	}

	@Test
	public void entrarComUsuarioInvalido() {
		
		webDriver.get("http://localhost:8080/InfoCenter/login.jsp");
		FluentWebDriver fluentDriver = new FluentWebDriver(webDriver);
		
		fluentDriver.form(By.id("formLogar")).input(By.name("usuario")).sendKeys("usuarioInvalido");
		fluentDriver.form(By.id("formLogar")).input(By.name("senha")).sendKeys("!@#$%");
		
		fluentDriver.form(By.id("formLogar")).submit();
		
		fluentDriver.div(By.id("mensagemErro")).getText().shouldBe("Usuário ou senha inválidos!");
		
	}

}
