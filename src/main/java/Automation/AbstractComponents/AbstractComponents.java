package Automation.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automation.SeleniumFrameworkDesign.CartPage;
import Automation.SeleniumFrameworkDesign.Orderpage;

public class AbstractComponents {

	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}

	@FindBy(css="[routerlink= '/dashboard/cart']") 		
	WebElement  cartHeader;
	
	@FindBy(css ="[routerlink = '/dashboard/myorders']")
	WebElement OrderHeader;

	public void waitForElementToAppear(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	
	public void waitForListElementToAppear(List<WebElement> elements) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void waitForWebElementToAppear(WebElement elements) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void waitForElementToDisappear(WebElement Ele)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
	// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	wait.until(ExpectedConditions.invisibilityOf(Ele)); // thissomewhat
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage CP = new CartPage(driver);
		return CP;
	}
	
	public Orderpage goToOrdersPage()
	{
		waitForWebElementToAppear (OrderHeader);
		OrderHeader.click();
		Orderpage OP = new Orderpage(driver);
		return OP;
	}
	
	
	
	
}
