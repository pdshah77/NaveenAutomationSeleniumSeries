package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import edu.emory.mathcs.backport.java.util.Collections;

public class TestExample2 {
	static WebDriver driver;
	static WebElement arrowButton;
	static Set<String> res;
	static String selector;
	
	public static void main(String[] args) {
		// Driver Initialization
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver_win32_88\\chromedriver.exe");
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get("https://www.noon.com/uae-en/");
	   
	    // Printing Results 
	    List<String> data = sortedItems("Health care essentials");
	    data.forEach(t -> System.out.println(t));
	    driver.quit();     
	}
	
	public static List<String> sortedItems(String sectionName){
		
		res = new HashSet<String>();
		selector = "//h3[text()='"+sectionName+"']/../../following-sibling::div//ancestor::div[@data-qa=\"product-name\"]";
		String name;
				
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scrolling Vertically and Checking for Element to be Visible
		while(true) {
			js.executeScript("window.scrollBy(0,1000)");
			try {
				WebElement element = driver.findElement(By.xpath("//h3[text()='"+sectionName+"']"));
				if(element.isDisplayed()) {
					js.executeScript("arguments[0].scrollIntoView(false);",element);
					break;
				}
			}
			catch(NoSuchElementException e) {
				
			}
		}
		// Clicking on Arrow Button
		arrowButton = driver.findElement(By.xpath("//h3[text()='"+sectionName+"']/../../following-sibling::div/div[2]"));
		while(true) {
		List<WebElement> items = driver.findElements(By.xpath(selector)); 
			for(int i=0; i < items.size(); i++) {
				name = items.get(i).getText();
				res.add(name);
			}
			if(!arrowButton.isDisplayed()) break;
		js.executeScript("arguments[0].click()", arrowButton);
		}
		List<String> reslist = new ArrayList<String>(res);
		 
		Collections.sort(reslist);
		return reslist;
	}
}
