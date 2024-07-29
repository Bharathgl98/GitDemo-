package Selenium_Framework.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Selenium_Framework.PageObjects.CartPage;
import Selenium_Framework.PageObjects.CheckoutPage;
import Selenium_Framework.PageObjects.ConfirmationPage;
import Selenium_Framework.PageObjects.ProductCatalog;
import Selenium_Framework.TestComponents.BaseTest;

public class ErrorValidations extends  BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException
	{
		String productName = "ZARA COAT 3";
		landingpage.loginApplication("thilak131926@gmail.com", "Thilak90@");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());	
		
		
		}
	
	@Test
	public void ProductErrorValidation() throws IOException
	{
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog=landingpage.loginApplication("bharath131926@gmail.com", "Bharath98@");
		List<WebElement> products=  productCatalog.getProductList();
		productCatalog.addProductsToCart(productName);
	    CartPage cartpage = productCatalog.goToCartPage();
		Boolean match =cartpage.VerifyProductDisplay(productName);
		Assert.assertFalse(match);


	}


}
