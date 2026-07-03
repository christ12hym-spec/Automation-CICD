package Automation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Automation.SeleniumFrameworkDesign.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Landingpage LP;

	public WebDriver intializeDriver() throws IOException {
		 Properties prop = new Properties();
		FileInputStream FIS = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Automation\\Resources\\DataFile.properties");

		prop.load(FIS); // opens that file for reading and prop contains: Key Value browser chrome */
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");

		//String browserName = prop.getProperty("browser");
		
		System.out.println("Browser Name = " + browserName);

		if (browserName.contains("chrome")) {
			ChromeOptions option = new ChromeOptions();		
			WebDriverManager.chromedriver().setup(); // instead of s/ property automaticcallchromedriver willdownload
			
			if(browserName.contains("headless")) {
				option.addArguments("headless");
			}
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440, 900));//torun infull screen when run in headlessmode
		}

		else if (browserName.equalsIgnoreCase("firefox")) {

		}

		else if (browserName.equalsIgnoreCase("edge")) {

		}

		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// String to HashMapusing Jackson dartabind utilities added in pomxmlfiles
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenshot(String testcasename,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE); // screenshot taken
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(Source, file);
		return System.getProperty("user.dir") + "//reports//" + testcasename + ".png ";
		// fileutils is package
	}

	@BeforeMethod(alwaysRun = true)
	public Landingpage lunchApplication() throws IOException {
		driver = intializeDriver();
		LP = new Landingpage(driver);
		LP.goTo();
		return LP;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}

/*
 * Properties is a Java class from the java.util package. It is used to read
 * key-value pairs from a .properties file. browser is the key chrome is the
 * value
 * 
 * FileInputStream is a Java class used to open and read data from a file. It
 * creates an input stream that allows Java programs to read the contents of a
 * file byte by byte. In Selenium frameworks, it's commonly used to read
 * configuration files like DataFile.properties.
 */
