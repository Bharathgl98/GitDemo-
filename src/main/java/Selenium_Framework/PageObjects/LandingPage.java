package Selenium_Framework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium_Framework.Abstract_Components.Abstract_Components;

public class LandingPage extends Abstract_Components {
	
 WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		// initialization
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail =	driver.findElement(By.id("userEmail"));
	// WebElement userpassword = driver.findElement(By.id("userPassword"));
	// PageFactory
	
	// Here we are sending locator or attribute in this argument and value also sending
	// at runtime this will constructed like this(WebElement userEmail = driver.findElement(By.id("userEmail")));
	@FindBy(id = "userEmail")
	WebElement userEmail; // variable 
	
	@FindBy(id = "userPassword")
	WebElement userpassword;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement  errorMessage ;
	
	public  ProductCatalog loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userpassword.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementsToAppear(errorMessage);
		 return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

}
