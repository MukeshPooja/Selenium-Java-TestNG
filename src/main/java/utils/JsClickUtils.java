package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsClickUtils {
	 private final JavascriptExecutor js;

	    public JsClickUtils(WebDriver driver) {
	        this.js = (JavascriptExecutor) driver;
	    }

	    /**
	     * Force-click an element using JavaScript (bypasses Selenium's visibility/interactability checks)
	     */
	    public void click(WebElement element) {
	        js.executeScript("arguments[0].click();", element);
	    }

	    /**
	     * Click an element only after scrolling it into view (prevents "not clickable" errors)
	     */
	    public void clickWithScroll(WebElement element) {
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	        js.executeScript("arguments[0].click();", element);
	    }

	    /**
	     * Click using a CSS selector (no WebElement needed)
	     */
	    public void clickByCss(String cssSelector) {
	        js.executeScript("document.querySelector(arguments[0]).click();", cssSelector);
	    }

	    /**
	     * Click using an XPath (no WebElement needed)
	     */
	    public void clickByXPath(String xpath) {
	        js.executeScript("document.evaluate(arguments[0], document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();", xpath);
	    }

	    /**
	     * Double-click an element
	     */
	    public void doubleClick(WebElement element) {
	        js.executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick', {bubbles: true}));", element);
	    }

	    /**
	     * Right-click (context menu)
	     */
	    public void rightClick(WebElement element) {
	        js.executeScript("arguments[0].dispatchEvent(new MouseEvent('contextmenu', {bubbles: true}));", element);
	    }

}
