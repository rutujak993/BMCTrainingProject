package utilityScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ActionBot 
{
	public static void elementClick(WebDriver driver, By locator)
	{
		driver.findElement(locator).click();
	}

	public static void elementInput(WebDriver driver, By locator, Object data)
	{
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(String.valueOf(data));
	}
}
