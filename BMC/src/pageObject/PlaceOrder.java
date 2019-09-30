package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PlaceOrder {
	WebDriver driver;
	@FindBy(how=How.NAME,using="bSubmit")
	private WebElement buttonrProceedWithOrder;
	
	@FindBy(how=How.XPATH,using="/html/body/table/tbody/tr/td[1]/h1")
	private WebElement HeaderText;
	
	public PlaceOrder(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getHeaderText()
	{
		return HeaderText.getText();
	}
	
	public void ProceedWithOrder()
	{
		buttonrProceedWithOrder.click();
	}
}
