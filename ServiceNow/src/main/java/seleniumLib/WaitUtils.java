package seleniumLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	
	WebDriver driver=null;
	WebDriverWait wait=null;
	long timeOutInSeconds=0;
	
	
	public WaitUtils(WebDriver driver) {
		
		this.driver =driver;
		if(wait==null) {
			wait=new WebDriverWait(driver, this.timeOutInSeconds);
		}
		
	}
	
	public void waitElementToBeClickable(WebElement element,long timeOutInSeconds) {
		this.timeOutInSeconds=timeOutInSeconds;
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void visibilityOfElement(WebElement element,long timeOutInSeconds) {
		this.timeOutInSeconds=timeOutInSeconds;
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
