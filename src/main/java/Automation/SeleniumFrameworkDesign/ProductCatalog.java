package Automation.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Automation.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

		
		WebDriver driver;
		
		public ProductCatalog (WebDriver driver)
		{		
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);  
		}		
		
		// PageFactory
		@FindBy(css=".col-lg-4") 		
		List <WebElement>  products;
		By ProductsBy = By.cssSelector(".col-lg-4");
		By addToCart = By.cssSelector(".card-body button:last-of-type");
		By toastMsg= By.id("toast-container");
		@FindBy(css=".ng-animating") 		
		WebElement  spinner;
	
		
		
		public List<WebElement> getProductList( ) // Action methods
		{
			waitForElementToAppear(ProductsBy);
			return products;	
		}
		
		public WebElement getProductBytName(String productName)
		{
			WebElement ProName = getProductList().stream()
					.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
					.orElse(null); //After changing .equals("ZARA COAT 3") to .equals(ProductName), 
		//								does the second test now add "ADIDAS ORIGINAL" still that selected zara coat
			return ProName;
		}
		
		public void addProductToCart(String ProductName)
		{			
			WebElement Prod = getProductBytName(ProductName);
			Prod.findElement(addToCart).click(); // :last-of-type is point the			
			waitForElementToAppear(toastMsg);
			waitForElementToDisappear(spinner);
			// last button here
		}
		
		
	}


