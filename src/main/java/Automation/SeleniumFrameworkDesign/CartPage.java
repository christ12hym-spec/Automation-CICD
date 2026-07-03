package Automation.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Automation.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents  {
		
		WebDriver driver;
		
		public CartPage (WebDriver driver)
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this); 
		}
		
		By Cart = By.cssSelector(".cartSection h3");
		@FindBy (css = ".cartSection h3")
		List<WebElement> Cartproducts;
		
		@FindBy (css = ".totalRow button")
		WebElement checkOutElement;
		
		public boolean verifyProductDisplay(String ProductName )
		{
			waitForListElementToAppear (Cartproducts);
			boolean match = Cartproducts.stream().anyMatch(CartP -> CartP.getText().equalsIgnoreCase(ProductName)); 
			return match;
		}
		
		public CheckOutPage checkOut()
		{
			checkOutElement.click();
			CheckOutPage COP = new CheckOutPage(driver);
			return COP;
		}
	}


