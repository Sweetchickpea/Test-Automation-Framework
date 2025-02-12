package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
//import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private WebDriverWait wait;

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);// initialize inst var driver
		wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching the browser" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {

			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName.equalsIgnoreCase("edge")) {

			driver.set(new EdgeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else {
			logger.error("Invalid browser name!!Please select chrome or edge");
			System.err.print("Invalid browser name!!Please select chrome or edge");
		}
	}

	public BrowserUtility(Browser browserName) {
		logger.info("Launching the browser" + browserName);
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		}

	}

	public BrowserUtility(Browser browserName, boolean isHeadLess) {
		logger.info("Launching the browser" + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadLess) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old--");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new ChromeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}

		} else if (browserName == Browser.EDGE) {
			if (isHeadLess) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old--");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));

			} else {
				driver.set(new EdgeDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		} else if (browserName == Browser.FIREFOX) {
			if (isHeadLess) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old--");

				driver.set(new FirefoxDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new FirefoxDriver());
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}

		}

	}

	public void goToWebsite(String url) {

		logger.info("Visiting the website" + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding element with the locator" + locator);
		//WebElement element = driver.get().findElement(locator);// Selenium webdriver will find the element
		
		WebElement element =wait.until(ExpectedConditions.elementToBeClickable(locator));
		logger.info("Element Found and now performing Click");
		element.click();

	}
	
	public void clickOnCheckBox(By locator) {
		logger.info("Finding element with the locator" + locator);
		//WebElement element = driver.get().findElement(locator);// Selenium webdriver will find the element
		
		WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Element Found and now performing Click");
		element.click();

	}

	public void clickOn(WebElement element) {

		logger.info("Element Found and now performing Click");
		element.click();

	}

	public void enterText(By locator, String textToEnter) {

		logger.info("Finding element with the locator" + locator);
		//WebElement element = driver.get().findElement(locator);
		
		WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		logger.info("Element Found and now enter text" + textToEnter);
		element.sendKeys(textToEnter);// invalid cred "nebix68940@perceint.com"

	}

	public void clearText(By textBoxLocator) {

		logger.info("Finding element with the locator" + textBoxLocator);
		//WebElement element = driver.get().findElement(textBoxLocator);
		
		WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));

		logger.info("Element Found and clearing the text");
		element.clear();
	}

	public void selectFromDropDown(By dropDownLocator, String optionToSelect) {

		logger.info("Finding element with the locator" + dropDownLocator);
		WebElement element = driver.get().findElement(dropDownLocator);
		Select select = new Select(element);
		logger.info("Selecting the option" + optionToSelect);
		select.selectByVisibleText(optionToSelect);

	}

	public void enterSpecialKey(By locator, Keys keyToEnter) {

		logger.info("Finding element with the locator" + locator);
		//WebElement element = driver.get().findElement(locator);
		WebElement element =wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		logger.info("Element Found and now enter special key" + keyToEnter);
		element.sendKeys(keyToEnter);// invalid cred "nebix68940@perceint.com"

	}

	public List<String> getAllVisibleText(By locator) {
		logger.info("Finding All elements with the locator" + locator);

		List<WebElement> elementList = driver.get().findElements(locator);
		logger.info("Element Found and printing the list of elements");
		List<String> visibleTextList = new ArrayList<String>();
		for (WebElement element : elementList) {
			System.out.println(getVisibleText(element));
			visibleTextList.add(getVisibleText(element));
		}

		return visibleTextList;
	}

	public List<WebElement> getAllElements(By locator) {
		logger.info("Finding All elements with the locator" + locator);

		List<WebElement> elementList = driver.get().findElements(locator);
		logger.info("Element Found and printing the list of elements");

		return elementList;
	}

	public String getVisibleText(By locator) {
		logger.info("Finding element with the locator" + locator);

		WebElement element = driver.get().findElement(locator);
		logger.info("Element Found and now returning the visibles" + element.getText());

		return element.getText();
	}

	public String getVisibleText(WebElement element) {

		logger.info("returning the visible text" + element.getText());

		return element.getText();
	}

	public String takeScreenshot(String name) {

		TakesScreenshot screenshot = (TakesScreenshot) driver.get();

		File screenshotdata = screenshot.getScreenshotAs(OutputType.FILE);

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path = "./screenshots/" + name + " - " + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotdata, screenshotFile);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return path;

	}
	public void quit() {
		driver.get().quit();
	}

}
