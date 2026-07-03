package Automation.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponents.AbstractComponents;

public class Landingpage extends AbstractComponents  {

		
		WebDriver driver;
		
		public Landingpage (WebDriver driver)
		{
			super(driver);
			// This constructor First this method willexecute beforeany process executes orhappens in the class
			this.driver = driver;// this.driver --local variable
			PageFactory.initElements(driver, this); // construt the driver line as previous for all web elements which driver we mentioned here
			//initElements() is a method from Selenium's PageFactory class. It initializes all the web elements marked with @FindBy annotations in a Page Object Model (POM) class.
		}
		
		//WebElement Email = driver.findElement(By.id("userEmail"));  //to give driver life, created constructor & using this to assign to local variable.

		
		// PageFactory
		@FindBy(id="userEmail") // how it knows driver using initElements methods
		WebElement EmailElement;
		@FindBy(id="userPassword") 
		WebElement PasswordElement;
		@FindBy(id="login") 
		WebElement Submit;
		
		@FindBy (css = ".ng-trigger-flyInOut")
		WebElement errorMsg;
		
		
		
		public ProductCatalog loginApplication(String Email, String password ) // Action methods
		{
			EmailElement.sendKeys(Email);
			PasswordElement.sendKeys(password);
			Submit.click();
			ProductCatalog PC =new ProductCatalog(driver);
			return PC;
		}
		
		public String getErrorMessage()
		{
			waitForWebElementToAppear (errorMsg);
			return errorMsg.getText();
		}
		
		public void goTo()// Action methods
		{
			driver.get("https://rahulshettyacademy.com/client/");
		}
		
	}


