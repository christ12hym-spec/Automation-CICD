package Automation.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		
		String ProductName ="ZARA COAT 3";

		WebDriverManager.chromedriver().setup(); //instead of s/ property automaticcallchromedriver willdownload
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		Landingpage LP =new Landingpage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("christ12hym@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rose12**");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
	List<WebElement> products =	driver.findElements(By.cssSelector(".col-lg-4"));
	WebElement ProName =	products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	
	ProName.findElement(By.cssSelector(".card-body button:last-of-type")).click(); //:last-of-type is point the last button here
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); //thissomewhat faster than previous step
	driver.findElement(By.cssSelector("[routerlink= '/dashboard/cart']")).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));	
	List <WebElement> Cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
	boolean match = Cartproducts.stream().anyMatch(CartP -> CartP.getText().equalsIgnoreCase(ProductName)); //anymatch retrun boolean values
	Assert.assertTrue(match);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	Actions A = new Actions(driver); // can by pass actions & done through normalsen keys wise
	A.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click(); // new nth of type --can give required index
	//wait.until(ExpectedConditions.elementToBeClickable(
	    //    By.cssSelector(".btnn.action__submit")));
	
	WebElement submitBtn =
	        driver.findElement(By.cssSelector(".btnn.action__submit"));

	((JavascriptExecutor)driver)
	        .executeScript("arguments[0].click();", submitBtn);
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.cssSelector(".hero-primary")));
	
	String Msg = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(Msg.equalsIgnoreCase("Thankyou for the order."));
	
	
	
	
	
	
	
	
	
	
	
		
		
		
		
	}

}
