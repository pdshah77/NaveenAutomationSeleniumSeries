package com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestExample {
	static String resString;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver_win32_88\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.get("https://www.worldometers.info/world-population/");
	    
	    WebElement mainData = driver.findElement(By.xpath("//div[@class='maincounter-number']"));
		System.out.println("----------Printing Main Population----------------");
		getCountfor10Sec(mainData);			
	
		System.out.println("----------Printing Deaths Count----------------");
		getCountfor10Sec(driver,"Deaths today");
	
		System.out.println("----------Printing Births Count----------------");
		getCountfor10Sec(driver,"Births today");
	
		System.out.println("----------Printing Population Growth Count----------------");
		getCountfor10Sec(driver,"Population Growth today");
	
		System.out.println("----------Printing Birth Year Count----------------");
		getCountfor10Sec(driver,"Births this year");
	
		System.out.println("----------Printing Death Year Count----------------");
		getCountfor10Sec(driver,"Deaths this year");
	
		System.out.println("----------Printing Death Year Count----------------");
		getCountfor10Sec(driver,"Population Growth this year");  
		
		driver.quit();
	}
	
	public static void getCountfor10Sec(WebDriver driver,String inpString) {
		WebElement element = driver.findElement(By.xpath("//div[normalize-space(text())='"+inpString+"']/following-sibling::div"));
		printingData(element, inpString);
	}
	
	public static void getCountfor10Sec(WebElement element) {
		printingData(element, "Main Population Count");
	}
	public static void printingData(WebElement element, String inpString) {
		long startTime = System.currentTimeMillis();
		while((System.currentTimeMillis()-startTime)<10000) {
			resString = element.getText().replaceAll(",", "");
			System.out.println(inpString + ":" + Long.parseLong(resString));
	}
}

}
