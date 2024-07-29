package Selenium_Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Selenium_Framework.Abstract_Components.Abstract_Components;

public class ProductCatalog extends Abstract_Components {
	
 WebDriver driver;
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		// initialization
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}
	
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products; 
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public  List<WebElement> getProductList()
	{
		waitForElementsToAppear(productsBy);
		return products;
		
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProductsToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addTocart).click();
		waitForElementsToAppear(toastMessage);
		waitForElementsToDisapper(spinner);
		
		
	}
     

}
