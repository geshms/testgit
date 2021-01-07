/**
 * 
 */
package mavenTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author GMS
 * @date Dec 12, 2020
 * @project MavenTestProject
 */
public class JobServe3 {

	WebDriver driver;
	String url = "https://www.jobserve.com/gb/en/Job-Search/";
	String keyword = "test engineer";
	String location = "london";
	String age = "Today";
	String expected = "";
	
	@BeforeTest
	public void beforeTest () {
	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@Test (priority =1)
	public void testKeyword () {
		//Input keyword & locations
		
		try {
			driver.get(url);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("//*[@id='txtKey']")).sendKeys(keyword);
			driver.findElement(By.xpath("//*[@id='txtLoc']")).sendKeys(location);
			
			//Select today
			
			Select jobAge= new Select(driver.findElement(By.xpath("//*[@id=\"selAge\"]")));
			
			jobAge.selectByVisibleText(age);
			
			//Seach
			driver.findElement(By.xpath("//*[@id=\"btnSearch\"]")).click();
			
			//validate - keyword
			WebDriverWait wait = new WebDriverWait (driver, 10);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"resultrole\"]"))));
			Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"resultrole\"]")).getText().trim(),keyword);		
			System.out.println("Test Passed");
		
		} catch (Exception e) {
			System.out.println("Test Failed");
			Assert.fail(e.getMessage());
		}
		
			
	}	
	@AfterTest
	public void afterTest () {
		driver.close();
	}
		
		
		
		
	}
	
	
	
	
	
	
	

