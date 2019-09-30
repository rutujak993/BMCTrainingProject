package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GmoHome 
{
	WebDriver driver;
	@FindBy(how=How.NAME,using="bSubmit")
	private WebElement buttonEnterGMOOnline;
	
	@FindBy(name="bAbout")
	private WebElement buttonAboutGmoSite;
	
	//@FindBy(xpath="//input[@name='bBrowserTest']")
	@FindBy(how=How.XPATH, using="//input[@name='bBrowserTest']")
	private WebElement buttonBrowserTestPage;
	

	public GmoHome(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void enterGMOOnline()
	{
		buttonEnterGMOOnline.click();
	}
	
	public void enterAboutTheGMOSite()
	{
		buttonAboutGmoSite.click();
		driver.navigate().back();
	}
	
	public void enterBrowserTestPage()
	{
		buttonBrowserTestPage.click();
		driver.navigate().back();
	}
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	
}
