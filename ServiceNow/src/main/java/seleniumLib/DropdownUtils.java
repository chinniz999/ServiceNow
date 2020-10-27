package seleniumLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownUtils {

	public static void selectFromDropdown(WebElement element,DropdownOption drpoption, String valueorIndex) {
		
		Select select =new Select(element);
		
		String optionToSelect = drpoption.toString();
		
        if(optionToSelect.equals("VALUE")) {
        	select.selectByValue(valueorIndex);
        }else if(optionToSelect.equals("INDEX")) {
        	select.selectByIndex(Integer.valueOf(valueorIndex));
        }else if(optionToSelect.equals("VISIBLETEXT")) {
        	select.selectByVisibleText(valueorIndex);
        }
	}
	
	public enum DropdownOption {
	    VALUE,
	    INDEX,
	    VISIBLETEXT
	  }


}