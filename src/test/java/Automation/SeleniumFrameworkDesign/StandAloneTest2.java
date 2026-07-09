package Automation.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;

public class StandAloneTest2 extends BaseTest {

		// TODO Auto-generated method stu

	@Test(dataProvider = "getData", groups= {"Purchase"})
	//public void SubmitOrder(String email,String password,String productName) throws IOException
	//public void SubmitOrder(HashMap<String, String> input) throws IOException
		
		public void SubmitOrder(HashMap<String, String> input) throws IOException
		{
		
		String Country = "India";
		
		ProductCatalog PC = LP.loginApplication(input.get("email"), input.get("password"));		
		
		List<WebElement> products = PC.getProductList();
		PC.addProductToCart(input.get("productName"));
		
		CartPage CP = PC.goToCartPage();
		
		
		boolean match = CP.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage COP = CP.checkOut();
		COP.selectCountry(Country);
		ConfirmationPage CMP = COP.submitOrder();
		
		
		String Msg = CMP.getConfirmationMsg();
		Assert.assertTrue(Msg.equalsIgnoreCase("Thankyou for the order."));

	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void orderHistoryTest() 
	{
		String ProductName = "ZARA COAT 3";
		ProductCatalog PC = LP.loginApplication("christ12hym@gmail.com", "Rose12**");	
		Orderpage OP = PC.goToOrdersPage();		
		Assert.assertTrue(OP.verifyOrderDisplay(ProductName));
System.out.println("OK");
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		 List<HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\Automation\\Data\\PurchaseOrder.json");
		
		return new Object [][] {{data.get(0)}, {data.get(1)}}; 
		
	}
	
}
	
	/*@DataProvider
	public Object[][] getData() throws IOException
	{		 
		
		return new Object [][] {{"christ12hym@gmail.com", "Rose12**", "ZARA COAT 3"},
			{"christhym@gmail.com", "Rose12***","ADIDAS ORIGINAL"}}; //object can send all data types  istead of this can use below*/
		
	/*	HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "christ12hym@gmail.com");
		map.put("password", "Rose12**");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "christhym@gmail.com");
		map1.put("password", "Rose12***");
		map1.put("productName", "ADIDAS ORIGINAL");
		return new Object [][] {{map}, {map1}};  */	





/*Encapsulation in Selenium means:

Keep WebElements as private.
Expose only meaningful public methods such as loginApplication(), addProductToCart(), goToCart(), and submitOrder().
Test classes should never call sendKeys() or click() directly on page elements. They should call the page's methods instead.

This is one of the core principles behind the Page Object Model (POM) and makes Selenium frameworks easier to maintain and extend.

Why Selenium Uses Encapsulation

Imagine your login button changes.

Old locator:

@FindBy(id="login")

New locator:

@FindBy(css=".login-btn")

If every test clicked the button directly, you'd update all those tests.

With encapsulation, you update only the LoginPage class. All tests continue calling:

loginApplication(username, password);

without any changes. */
