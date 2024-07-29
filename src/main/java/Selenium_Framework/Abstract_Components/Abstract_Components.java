package Selenium_Framework.Abstract_Components;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium_Framework.PageObjects.CartPage;
import Selenium_Framework.PageObjects.OrderPage;

public class Abstract_Components {

	WebDriver driver;
	
	public Abstract_Components(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css= "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css= "[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	
	

	public void waitForElementsToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	

	public void waitForWebElementsToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartpage = new  CartPage(driver);
		return cartpage;
	}
	
	
	public OrderPage goToOrdersPage()
	{
		orderHeader.click();
		OrderPage orderpage = new  OrderPage(driver);
		return orderpage;
	}
	
	
	public void waitForElementsToDisapper(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
