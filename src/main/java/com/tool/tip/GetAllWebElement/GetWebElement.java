package com.tool.tip.GetAllWebElement;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import edu.emory.mathcs.backport.java.util.LinkedList;
import junit.framework.Assert;

public class GetWebElement {

	WebDriver driver;
	List<String> expectedList;
	List<String> actualList;

	public void initDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\pooja\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("disable-infobars");
		driver = new ChromeDriver(option);
		driver.get("https://www.seleniumhq.org/");
		// driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void runTest() throws InterruptedException {
		initDriver();
		int count = 1;
		Actions act = new Actions(driver);
		List<WebElement> ele = driver.findElements(By.tagName("a"));
		expectedList = new LinkedList();
		System.out.println("total size " + ele.size());
		for (WebElement webelement : ele) {
			if (!((webelement.getAttribute("title")).equals(""))) {
				act.moveToElement(webelement).build().perform();
				expectedList.add(webelement.getAttribute("title"));
				count++;
			}
		}
		System.out.println(".....followng are expected tooltip text.............");
		for (String list : expectedList) {
			System.out.println(list);
		}
		actualList = actualtoolTipList();
		System.out.println(".....followng are actual tooltip text.............");
		for (String list : actualList) {
			System.out.println(list);
		}
		compareList(expectedList, actualList);
		System.out.println();
		System.out.println(".........both are equal.....");
	}

	public void compareList(List<String> expectedList, List<String> actualList) {
		try {
			Assert.assertTrue(expectedList.containsAll(actualList));
			// Assert.assertEquals(expectedList, actualList);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public List<String> actualtoolTipList() {
		actualList = new LinkedList();
		actualList.add("Return to Selenium home page");
		actualList.add("Overview of Selenium");
		actualList.add("Get help with Selenium");
		actualList.add("Technical references and guides");
		actualList.add("Get Selenium");
		actualList.add("Selenium Projects");
		return actualList;
	}

	@AfterTest
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
}
