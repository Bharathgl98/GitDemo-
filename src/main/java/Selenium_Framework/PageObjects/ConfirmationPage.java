package Selenium_Framework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium_Framework.Abstract_Components.Abstract_Components;

public class ConfirmationPage extends Abstract_Components {

	
	WebDriver driver;
	public  ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = ".action__submit")
	WebElement confirmationmessage;
	
	
	public String  getcinfirmatiomessage()
	{
		 return confirmationmessage.getText();
	}
}
