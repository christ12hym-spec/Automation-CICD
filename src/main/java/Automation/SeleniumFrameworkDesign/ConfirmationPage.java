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


public class ConfirmationPage extends AbstractComponents  {
		
		WebDriver driver;
		
		public ConfirmationPage (WebDriver driver)
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this); 
		}	 
		
		By MsgEnable = By.cssSelector(".hero-primary");	
		
		@FindBy (css = ".hero-primary")
		WebElement Msg;
		
		
		public String getConfirmationMsg()
		{
			waitForElementToAppear(MsgEnable);
			return Msg.getText();
		}
		
		
		
}