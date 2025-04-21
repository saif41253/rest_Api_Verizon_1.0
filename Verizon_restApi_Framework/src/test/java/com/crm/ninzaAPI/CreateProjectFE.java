package com.crm.ninzaAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreateProjectFE {
	// create project using selenium
	@Test
	public void createProjectUsingSelenium() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
//		ChromeOptions options = new ChromeOptions();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8091/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		Thread.sleep(3000);
//        options.addArguments("--disable-notifications");
//        driver = new ChromeDriver(options);
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys("awfis_28");
		driver.findElement(By.name("createdBy")).sendKeys("Saif_11");
		WebElement status = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		Select sel = new Select(status);
		sel.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).submit();
		WebElement projTxt = driver.findElement(By.xpath("//select[@class='form-control']"));
		Select select = new Select(projTxt);
		select.selectByContainsVisibleText("awfis");
		
	}
	// get project using restApi
			@Test
			public void getProject() {
				given().contentType(ContentType.JSON)
				.when().get("http://49.249.28.218:8091/project/NH_PROJ_25026")
				.then().log().all();
			}
			// validate project using restApi
			
			


}
