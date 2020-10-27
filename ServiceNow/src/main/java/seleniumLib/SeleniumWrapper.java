package seleniumLib;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {

	WebDriver driver = null;
	WaitUtils waits = null;

	public SeleniumWrapper(WebDriver driver) {
		this.driver = driver;
		waits = new WaitUtils(driver);
	}

	public void click(WebElement element) {

		try {
			waits.waitElementToBeClickable(element, 10);
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void clickJs(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void sendKeys(WebElement element, String text) {

		try {
			element.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getText(WebElement element) {

		try {
			return element.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
          return null;
	}

}
