package pageObjects;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import seleniumLib.DropdownUtils;
import seleniumLib.DropdownUtils.DropdownOption;
import seleniumLib.SeleniumWrapper;

public class HomePage {

	WebDriver driver = null;
	SeleniumWrapper selLib = null;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		selLib = new SeleniumWrapper(driver);
	}

	@FindBy(how = How.ID, using = "search_query_top")
	private WebElement searchBox;

	@FindBy(how = How.NAME, using = "submit_search")
	private WebElement searchButton;

	@FindBy(how = How.ID, using = "selectProductSort")
	private WebElement filterProducts;

	@FindBys({
			@FindBy(how = How.XPATH, using = "//p/following-sibling::div[@class='content_price']/span[@class='price product-price']") })
	private List<WebElement> productPrices;

	@FindBy(how = How.XPATH, using = "//button/span[text()='Add to cart']")
	private WebElement addToCart;

	@FindBy(how = How.XPATH, using = "//a/span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckout;

	public void searchText(String searchText) {

		selLib.sendKeys(searchBox, searchText);
		selLib.click(searchButton);

	}

	public void filterProductsBy(String sortBy) {
		DropdownUtils.selectFromDropdown(filterProducts, DropdownOption.VISIBLETEXT, sortBy);
	}

	public CheckoutSummary selectLowPriceProduct() {

		List<Double> prices = productPrices.stream().map(wb -> Double.valueOf(wb.getText().replace("$", "").trim())).collect(Collectors.toList());
		Collections.sort(prices);

		WebElement lowPriceItem = driver.findElement(By.xpath("//p/following-sibling::div[@class='content_price']/span[contains(text(),'" + prices.get(0) + "')]"));

		new Actions(driver).moveToElement(lowPriceItem).click().build().perform();

		selLib.click(addToCart);
		return PageFactory.initElements(driver, CheckoutSummary.class);
	}

}
