package br.com.infoCenter.testResource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;

public class LogarTest {

	private WebDriver webDriver;
	private FluentWebDriver fluentDriver; 
	
	@Before
	public void setUp() throws Exception {
		webDriver = new FirefoxDriver();
		webDriver.get("http://localhost:8080/InfoCenter/login.jsp");
		fluentDriver = new FluentWebDriver(webDriver);
	}

	@After
	public void tearDown() throws Exception {
		webDriver.close();
	}

	@Test
	public void entrarComUsuarioAdministradorValido() {

		fluentDriver.input(By.name("usuario")).sendKeys("alan.saulo");
		fluentDriver.input(By.name("senha")).sendKeys("senha111");
		
		fluentDriver.form(By.id("formLogar")).submit();
		
		fluentDriver.div(By.id("divUsuarioLogado")).span().getText().shouldBe("Olá ALAN SAULO! (Sair)");
		
	}

	@Test
	public void entrarComUsuarioInvalido() {

		fluentDriver.input(By.name("usuario")).sendKeys("usuarioInvalido");
		fluentDriver.input(By.name("senha")).sendKeys("!@#$%");
		
		fluentDriver.form(By.id("formLogar")).submit();
		
		fluentDriver.div(By.id("mensagemErro")).getText().shouldBe("Usuário ou senha inválidos!");
		
	}

}
