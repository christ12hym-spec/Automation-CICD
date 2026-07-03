package Automation.CucumberStepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Automation.SeleniumFrameworkDesign.CartPage;
import Automation.SeleniumFrameworkDesign.CheckOutPage;
import Automation.SeleniumFrameworkDesign.ConfirmationPage;
import Automation.SeleniumFrameworkDesign.Landingpage;
import Automation.SeleniumFrameworkDesign.ProductCatalog;
import Automation.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefinitions extends BaseTest {

	public Landingpage LP;
	public ProductCatalog PC;
	public ConfirmationPage CMP;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {

		LP = lunchApplication();
	}

	@Given("^Logged with username (.+) and password (.+)$")
	public void Logged_with_username_and_password(String username, String password) {
		 PC = LP.loginApplication(username, password);	
	}
	
	 @When ("^I add the product (.+) to cart$")
	 public void I_add_product_to_cart(String productName)
	 {
		 List<WebElement> products = PC.getProductList();
			PC.addProductToCart(productName);
	 }
	 
	 @And ("^Checkout (.+) and submit the order$")
	 public void Checkout_Submit_Order(String productName)
	 {
		 CartPage CP = PC.goToCartPage();		
			boolean match = CP.verifyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckOutPage COP = CP.checkOut();
			COP.selectCountry("India");
			CMP = COP.submitOrder();
	 }
	 
	 @Then ("{string} message is displayed on confirmation page")
	 public void Msg_Disply_Confirmation_Page(String message)
	 {
		 String Msg = CMP.getConfirmationMsg();
			Assert.assertTrue(Msg.equalsIgnoreCase(message));
			driver.close();
	 }

	 @Then ("{string} message is displayed")
	 public void Incorrect_Login_Msg_is_Display(String message)
	 {
		 Assert.assertEquals(message, LP.getErrorMessage());
		 driver.close();
	 }

}
