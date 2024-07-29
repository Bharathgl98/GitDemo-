package Selenium_Framework.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import javax.swing.Action;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium_Framework.PageObjects.CartPage;
import Selenium_Framework.PageObjects.CheckoutPage;
import Selenium_Framework.PageObjects.ConfirmationPage;
import Selenium_Framework.PageObjects.LandingPage;
import Selenium_Framework.PageObjects.OrderPage;
import Selenium_Framework.PageObjects.ProductCatalog;
import Selenium_Framework.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends  BaseTest {

	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData", groups= {"purchase"})
	public void submiteOder(HashMap<String, String> input) throws IOException
	{
		
		ProductCatalog productCatalog=landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=  productCatalog.getProductList();
		productCatalog.addProductsToCart(input.get("productName"));
	    CartPage cartpage = productCatalog.goToCartPage();
		Boolean match =cartpage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,5000)");
		CheckoutPage checkoutpage =  cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		ConfirmationPage  confirmationPage	= checkoutpage.submitOrder();
		String confirmMessage = confirmationPage.getcinfirmatiomessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	
	// To  verify ZARA COAT 3 is displaying in orders
	
	@Test(dependsOnMethods = {"submiteOder"})
	public void OrderHistoryTest()
	{
		ProductCatalog productCatalog=landingpage.loginApplication("bharath131926@gmail.com", "Bharath98@");
	OrderPage ordrPage =	productCatalog.goToOrdersPage();
	ordrPage.VerifyOrderDisplay(productName);
	Assert.assertTrue(ordrPage.VerifyOrderDisplay(productName));
		
	}
	
	
	
	// Extent Reports 
	
	
	

	@DataProvider
	public Object[][] getData() throws IOException
	{ 
		 
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") +"\\src\\test\\java\\Selenium_Framework\\data\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)},{data.get(1)} };
	}
	
	
	/*
	  @DataProvider
	 public Object[][] getData()
     {
     HashMap<String,String> map = new HashMap<String, String>();
	map.put("email", "bharath131926@gmail.com");
	map.put("password", "Bharath98@");
	map.put("productName", "ZARA COAT 3");
	HashMap<String,String> map1 = new HashMap<String, String>();
	map1.put("email", "thilak131926@gmail.com");
	map1.put("password", "Thilak98@");
	map1.put("productName", "ADIDAS ORIGINAL");
	}*/
	

	// object is something which is parent data type  for all this and it's generic data type which accepts any kind of data type 
	
	/* @DataProvider
	  public Object[][] getData()
      {
	    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
	    
	  } */ 
}
