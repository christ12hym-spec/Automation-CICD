package Automation.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Automation.AbstractComponents.AbstractComponents;


public class CheckOutPage extends AbstractComponents  {
		
		WebDriver driver;
		
		public CheckOutPage (WebDriver driver)
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this); 
		}	
		
		@FindBy (css = "[placeholder='Select Country']")
			WebElement countryElement;
		
		@FindBy (css = ".ta-item:nth-of-type(2)")
		WebElement country;
		
		By countrylistbox = By.cssSelector(".ta-results");
		
		@FindBy (css = ".btnn.action__submit")
		WebElement Submit; 
		
		public void selectCountry(String Country)
		{
			Actions A = new Actions(driver); // can by pass actions & done through normalsen keys wise
			A.sendKeys(countryElement, Country).build().perform();
			waitForElementToAppear(countrylistbox);			
			country.click(); // new nth of type --can give required
		}
		
		public ConfirmationPage submitOrder()
		{
			//Submit.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Submit);
			return new ConfirmationPage (driver);
		}
		
}