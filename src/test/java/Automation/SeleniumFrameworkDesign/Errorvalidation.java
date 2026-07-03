package Automation.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import Automation.TestComponents.Retry;

public class Errorvalidation extends BaseTest {

		// TODO Auto-generated method stu

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class) 
	//alwaysRun = true tells TestNG: "Run this method even if the test belongs to a group 
		//that is not being executed or some dependency fails."
	public void loginErrorValidation () throws IOException
	{
		 LP.loginApplication("christ12hymm@gmail.com", "Rose12**");	
		Assert.assertEquals("Incorrect email  password.", LP.getErrorMessage());		
	}
	
	@Test
	public void productErrorValidation() throws IOException
	{
		String ProductName = "ZARA COAT 3";
		
		ProductCatalog PC = LP.loginApplication("christ12hym@gmail.com", "Rose12**");		
		
		List<WebElement> products = PC.getProductList();
		PC.addProductToCart(ProductName);
		
		CartPage CP = PC.goToCartPage();		
		
		boolean match = CP.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);		
	}
}

		
