package Selenium_Framework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v122.browser.Browser;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Selenium_Framework.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	 public WebDriver driver;
	public  LandingPage landingpage;
	 
	public WebDriver initializeDriver() throws IOException
	{
		//properties class 
		Properties prop =new Properties();
		FileInputStream fis = new  FileInputStream(System.getProperty("user.dir")+"//src//main//java//Selenium_Framework//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
		 
		  ChromeOptions options = new ChromeOptions();
		  if(browserName.contains("headless"))
		  options.addArguments("headless");
	      driver = new ChromeDriver(options);
	      driver.manage().window().maximize();
		
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			
			WebDriver driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		// read json to string 
	String jsonContent =	FileUtils.readFileToString(new File( filePath),StandardCharsets.UTF_8);
	// string to HashMap 
	// Jackson Databind this is one of the dependency which can help you to convert spring content into hash map 
	
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
	});
	return data;
	
	
}
	
	public String  getScreenShot(String testcaseName,WebDriver driver) throws Throwable
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source =  ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(source,file );
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png" ;
		
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		 driver = initializeDriver();
		 landingpage = new LandingPage(driver);
			landingpage.goTo();
			return landingpage;
	}
	/*
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	} 
	*/

}
