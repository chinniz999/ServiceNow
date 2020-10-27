package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import seleniumLib.SeleniumWrapper;

public class CheckoutSummary {

	WebDriver driver = null;
	SeleniumWrapper selLib = null;

	public CheckoutSummary(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		selLib = new SeleniumWrapper(driver);
	}

	@FindBy(how = How.XPATH, using = "//span[text()='Proceed to checkout']")
	private WebElement checkout;

	@FindBy(how = How.ID, using = "email")
	private WebElement emailId;

	@FindBy(how = How.ID, using = "passwd")
	private WebElement user_password;

	@FindBy(how = How.ID, using = "SubmitLogin")
	private WebElement signin;

	@FindBy(how = How.XPATH, using = "//a/span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckout;

	@FindBy(how = How.ID, using = "layer_cart_product_price")
	private WebElement product_price;

	@FindBy(how = How.ID, using = "layer_cart_product_title")
	private WebElement product_Title;

	@FindBy(how = How.ID, using = "layer_cart_product_quantity")
	private WebElement product_Quantity;

	@FindBy(how = How.ID, using = "cgv")
	private WebElement termsAndCondCheckbox;

	@FindBy(how = How.XPATH, using = "//button/span[contains(text(),'Proceed to checkout')]")
	private WebElement checkoutshipping;

	@FindBy(how = How.XPATH, using = "//p[@class='product-name']/a")
	private WebElement checkoutProductTitle;

	@FindBy(how = How.XPATH, using = "//td/span/span[@class='price special-price']")
	private WebElement checkoutProductPrice;

	@FindBy(how = How.XPATH, using = "//td[@class='cart_quantity text-center']/span")
	private WebElement checkoutProductQuantity;

	@FindBy(how = How.XPATH, using = "//a[@title='Pay by check.']")
	private WebElement payByCheck;
	
	@FindBy(how = How.XPATH, using = "//span[text()='I confirm my order']")
	private WebElement confirmOrder;
	
	@FindBy(how = How.XPATH, using = "//p[text()='Your order on My Store is complete.']")
	private WebElement orderConfirmCheck;
	

	
	

	String cartProductTitle = null;
	String cartProductPrice = null;
	String cartProductQuantity = null;

	public void verifyCartAndproceedCheckout() {

		cartProductPrice = selLib.getText(product_price);
		cartProductTitle = selLib.getText(product_Title);
		cartProductQuantity = selLib.getText(product_Quantity);

		System.out.println(selLib.getText(product_price));
		System.out.println(selLib.getText(product_Title));
		System.out.println(selLib.getText(product_Quantity));

		selLib.clickJs(proceedToCheckout);

		selLib.click(checkout);
	}

	public void signIn(String email, String password) {
		selLib.sendKeys(emailId, email);
		selLib.sendKeys(user_password, password);
		selLib.click(signin);
		selLib.click(proceedToCheckout);
	}

	public void address() {
		selLib.click(checkoutshipping);
	}

	public void shipping() {
		selLib.clickJs(termsAndCondCheckbox);
		selLib.click(checkoutshipping);
	}

	public void verifyOrderDetailsCartVsShipping() {

		Assert.assertEquals(cartProductTitle, selLib.getText(checkoutProductTitle));
		Assert.assertEquals(cartProductPrice, selLib.getText(checkoutProductPrice));
		Assert.assertEquals(cartProductQuantity, selLib.getText(checkoutProductQuantity));

	}

	public void verifyTranscationCompleteDetails() {
		selLib.click(payByCheck);
		selLib.click(confirmOrder);
        Assert.assertEquals("Your order on My Store is complete.", selLib.getText(orderConfirmCheck));
        
        
        
	}

}
