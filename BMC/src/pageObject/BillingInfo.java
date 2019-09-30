package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BillingInfo {
	WebDriver driver;
	@FindBy(how=How.NAME,using="bSubmit")
	private WebElement buttonrPlaceOrder;
	
	@FindBy(how=How.NAME,using="billName")
	private WebElement billName;
	
	@FindBy(how=How.NAME,using="billAddress")
	private WebElement Address;
	
	@FindBy(how=How.NAME,using="billCity")
	private WebElement City;
	
	@FindBy(how=How.NAME,using="billState")
	private WebElement State;
	
	@FindBy(how=How.NAME,using="billZipCode")
	private WebElement ZipCode;
	
	@FindBy(how=How.NAME,using="billPhone")
	private WebElement Phone;
	
	@FindBy(how=How.NAME,using="billEmail")
	private WebElement Email;
	
	@FindBy(how=How.NAME,using="shipSameAsBill")
	private WebElement ShipTo;
	
	@FindBy(how=How.XPATH,using="/html/body/form/table/tbody/tr[2]/td[1]/table/tbody/tr[10]/td[2]/select/option")
	private WebElement CardType;
	
	@FindBy(how=How.NAME,using="CardNumber")
	private WebElement CardNo;
	
	@FindBy(how=How.NAME,using="CardDate")
	private WebElement Expiration;

	@FindBy(how=How.XPATH,using="/html/body/table/tbody/tr/td[1]/h1")
	private WebElement HeaderText;

	
	public BillingInfo(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void PlaceOrder()
	{
		buttonrPlaceOrder.click();
	}
	
	public String getHeaderText()
	{
		return HeaderText.getText();
	}

//	Setters 
	public void setName(String nm)
	{
		billName.clear();
		billName.sendKeys(nm);
	}
	
	public void setAddress(String add)
	{
		Address.clear();
		Address.sendKeys(add);
	}
	
	public void setCity(String cityName)
	{
		City.clear();
		City.sendKeys(cityName);
	}
	
	public void setState(String stateName)
	{
		State.clear();
		State.sendKeys(stateName);
	}
	
	public void setZip(String zipCode)
	{
		ZipCode.clear();
		ZipCode.sendKeys(zipCode);
	}
	
	public void setCardNo(String CardNum)
	{
		CardNo.clear();
		CardNo.sendKeys(CardNum);
	}
	
	public void setEmail(String emailID)
	{
		Email.clear();
		Email.sendKeys(emailID);
	}
	
	public void setExpiration(String Exp)
	{
		Expiration.clear();
		Expiration.sendKeys(Exp);
	}
	
	public void setPhone(String PhoneNo)
	{
		Phone.clear();
		Phone.sendKeys(PhoneNo);
	}


	


}
