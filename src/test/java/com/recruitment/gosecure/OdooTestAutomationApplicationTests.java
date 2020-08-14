package com.recruitment.gosecure;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
/**
 * 
 * @author Aruna K
 *
 */
class OdooTestAutomationApplicationTests {

	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String DRIVER_EXE = "chromedriver.exe";
	private WebDriver driver;
	private WebDriverWait driverWait;
	private static final String URL = "https://www.odoo.com/en_EN/page/community";
	
	@BeforeClass
	void setup() {
		
		ClassLoader classLoader = getClass().getClassLoader();
		String driverFilePath = classLoader.getResource(DRIVER_EXE).getPath();
		System.setProperty(WEBDRIVER_CHROME_DRIVER, driverFilePath);
		
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driverWait = new WebDriverWait(driver, 60);
		this.driver.get(URL);
	}
	
	@Test
	public void navigateToNotesSelectionPage() {
		this.driverWait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
		final WebElement onlineDemoElement = this.driver.findElement(By.xpath("//a[text()=\"Online Demo\"]"));
		onlineDemoElement.click();
	}
	
	@Test(dependsOnMethods = { "navigateToNotesSelectionPage" })
	public void navigateToNotesPage() {
		this.driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Notes\"]//parent::a")));
		final WebElement notesWebElement = this.driver.findElement(By.xpath("//div[text()=\"Notes\"]//parent::a"));
		notesWebElement.click();
	}
	
	@Test(dependsOnMethods = { "navigateToNotesPage" })
	public void createANote() {
		this.driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"button\" and @accesskey=\"c\"]")));
		final WebElement createNoteWebElement = this.driver.findElement(By.xpath("//button[@type=\"button\" and @accesskey=\"c\"]"));
		createNoteWebElement.click();
	}
	
	@Test(dependsOnMethods = { "createANote" })
	public void AddTextToNote() {
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.switchTo().frame(this.driver.findElement(By.xpath("//div[@name=\"note_pad_url\"]/div/iframe")));
		this.driver.switchTo().frame("ace_outer");
		this.driver.switchTo().frame("ace_inner");
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		final WebElement onlineDemoElement = this.driver.findElement(By.xpath("//html[@dir=\"ltr\"]//body[@id=\"innerdocbody\"]"));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = 'Added note for GoScure hiring evaluation'", onlineDemoElement);

		this.driver.switchTo().defaultContent();
		
	}
	
	@Test(dependsOnMethods = { "AddTextToNote" })
	public void SaveANote() {
		this.driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"button\" and @accesskey=\"s\"]")));
		final WebElement saveNoteWebElement = this.driver.findElement(By.xpath("//button[@type=\"button\" and @accesskey=\"s\"]"));
		saveNoteWebElement.click();
	}
	
	@Test(dependsOnMethods = { "SaveANote" })
	public void editANote() {
		this.driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"button\" and @accesskey=\"a\"]")));
		final WebElement editNoteWebElement = this.driver.findElement(By.xpath("//button[@type=\"button\" and @accesskey=\"a\"]"));
		editNoteWebElement.click();
	}
	
	@Test(dependsOnMethods = { "editANote" })
	public void EditTextToNote() {
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.driver.switchTo().frame(this.driver.findElement(By.xpath("//div[@name=\"note_pad_url\"]/div/iframe")));
		this.driver.switchTo().frame("ace_outer");
		this.driver.switchTo().frame("ace_inner");
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		final WebElement onlineDemoElement = this.driver.findElement(By.xpath("//html[@dir=\"ltr\"]//body[@id=\"innerdocbody\"]"));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = 'Edited note for GoScure hiring evaluation'", onlineDemoElement);

		this.driver.switchTo().defaultContent();
		
	}
	
	@Test(dependsOnMethods = { "EditTextToNote" })
	public void discardNote() {
		this.driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"button\" and @accesskey=\"j\"]")));
		final WebElement editNoteWebElement = this.driver.findElement(By.xpath("//button[@type=\"button\" and @accesskey=\"j\"]"));
		editNoteWebElement.click();
	}

	@AfterClass
	void exit() {
		// Exit the application once all tests been executed
		driver.quit();
	}

}
