package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
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

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);// initialize inst var driver
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching the browser" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {

			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {

			driver.set(new EdgeDriver());
		} else {
			logger.error("Invalid browser name!!Please select chrome or edge");
			System.err.print("Invalid browser name!!Please select chrome or edge");
		}
	}

	public BrowserUtility(Browser browserName) {
		logger.info("Launching the browser" + browserName);
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
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
			} else {
				driver.set(new ChromeDriver());
			}

		} else if (browserName == Browser.EDGE) {
			if (isHeadLess) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old--");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));

			} else {
				driver.set(new EdgeDriver());
			}
		} else if (browserName == Browser.FIREFOX) {
			if(isHeadLess) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old--");
				
				driver.set(new FirefoxDriver(options));
			}
			else {
				driver.set(new FirefoxDriver());
			}
			driver.set(new FirefoxDriver());
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
		WebElement element = driver.get().findElement(locator);// Selenium webdriver will find the element
		logger.info("Element Found and now performing Click");
		element.click();

	}

	public void enterText(By locator, String textToEnter) {

		logger.info("Finding element with the locator" + locator);
		WebElement element = driver.get().findElement(locator);

		logger.info("Element Found and now enter text" + textToEnter);
		element.sendKeys(textToEnter);// invalid cred "nebix68940@perceint.com"

	}

	public String getVisibleText(By locator) {
		logger.info("Finding element with the locator" + locator);

		WebElement element = driver.get().findElement(locator);
		logger.info("Element Found and now returning the visibles" + element.getText());

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

}
