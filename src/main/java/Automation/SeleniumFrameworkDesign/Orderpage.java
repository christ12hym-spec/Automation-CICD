package Automation.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Automation.AbstractComponents.AbstractComponents;

public class Orderpage extends AbstractComponents  {
		
		WebDriver driver;
		
		public Orderpage (WebDriver driver)
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this); 
		}
		
		By Cart = By.cssSelector(".cartSection h3");
		@FindBy (css = "tr td:nth-child(3)")
		List<WebElement> ProductList;
		
		
		public boolean verifyOrderDisplay(String ProductName )
		{
			waitForListElementToAppear (ProductList);
			boolean match = ProductList.stream().anyMatch(CartP -> CartP.getText().equalsIgnoreCase(ProductName)); 
			return match;
		}
		

	}


