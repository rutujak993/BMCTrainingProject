package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;



public class CatalogPage {
	
	WebDriver driver;
	@FindBy(how=How.NAME,using="bSubmit")
	private WebElement buttonrPlaceAnOrder;
	@FindBy(how=How.NAME,using="bReset")
	private WebElement buttonrResetForm;
	@FindBy(how=How.NAME,using="QTY_TENTS")
	private WebElement orderQTY_TENTS;
	@FindBy(how=How.NAME,using="QTY_BACKPACKS")
	private WebElement orderQTY_BACKPACKS;
	@FindBy(how=How.NAME,using="QTY_GLASSES")
	private WebElement orderQTY_GLASSES;
	@FindBy(how=How.NAME,using="QTY_SOCKS")
	private WebElement orderQTY_SOCKS;
	@FindBy(how=How.NAME,using="QTY_BOOTS")
	private WebElement orderQTY_BOOTS;
	@FindBy(how=How.NAME,using="QTY_SHORTS")
	private WebElement orderQTY_SHORTS;
	@FindBy(how=How.XPATH,using="/html/body/form/table/tbody/tr[2]/td/div/center/table")
	private WebElement catalogTable;
	@FindBy(how=How.XPATH,using="/html/body/table/tbody/tr/td[1]/h1")
	private WebElement HeaderText;
	@FindBy(how=How.XPATH,using="/html/body/form/table/tbody/tr[2]/td/div/center/table/tbody/tr")
	@CacheLookup //it makes lookup for the element happen just once.
	private List<WebElement> rowList;
	@FindBy(how=How.CSS,using="a>strong")
	private List<WebElement> itemLists;
	
	public CatalogPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void ResetForm()
	{
		buttonrResetForm.click();
	}
	
	public void PlaceOrder()
	{
		buttonrPlaceAnOrder.click();
	}
	
	public String getHeaderText()
	{
		return HeaderText.getText();
	}
	
	public boolean isResetForm()
	{
		if(Integer.parseInt(orderQTY_BACKPACKS.getAttribute("value")) == 0 &&
		Integer.parseInt(orderQTY_BACKPACKS.getAttribute("value")) == 0 &&
		Integer.parseInt(orderQTY_BOOTS.getAttribute("value")) == 0 &&
		Integer.parseInt(orderQTY_GLASSES.getAttribute("value")) == 0 &&
		Integer.parseInt(orderQTY_SHORTS.getAttribute("value")) == 0 &&
		Integer.parseInt(orderQTY_SOCKS.getAttribute("value")) == 0 &&
		Integer.parseInt(orderQTY_TENTS.getAttribute("value")) == 0)
			return true;
		else
			return false;
				
	}
	
//	Setters for OrderQty
	public void setOrderQtyTENTS(String qty)
	{
		orderQTY_TENTS.clear();
		orderQTY_TENTS.sendKeys(qty);
	}
	
	public void setOrderQtyBACKPACKS(String qty)
	{
		orderQTY_BACKPACKS.clear();
		orderQTY_BACKPACKS.sendKeys(qty);
	}
	
	public void setOrderQtyGLASSES(String qty)
	{
		orderQTY_GLASSES.clear();
		orderQTY_GLASSES.sendKeys(qty);
	}
	
	public void setOrderQtyBOOTS(String qty)
	{
		orderQTY_BOOTS.clear();
		orderQTY_BOOTS.sendKeys(qty);
	}
	
	public void setOrderQtySHORTS(String qty)
	{
		orderQTY_SHORTS.clear();
		orderQTY_SHORTS.sendKeys(qty);
	}
	
	public void setOrderQtySOCKS(String qty)
	{
		orderQTY_SOCKS.clear();
		orderQTY_SOCKS.sendKeys(qty);
	}
	
//Getter methods
	
	public String GetOrderQtyTENTS()
	{
		return orderQTY_TENTS.getAttribute("value");
	}
	
	public String GetOrderQtyBACKPACKS()
	{
		return orderQTY_BACKPACKS.getAttribute("value");
	}
	
	public String GetOrderQtyBOOTS()
	{
		return orderQTY_BOOTS.getAttribute("value");
	}
	
	public String GetOrderQtyGLASSES()
	{
		return orderQTY_GLASSES.getAttribute("value");
	}
	
	public String GetOrderQtySHORTS()
	{
		return orderQTY_SHORTS.getAttribute("value");
	}
	
	public String GetOrderQtySOCKS()
	{
		return orderQTY_SOCKS.getAttribute("value");
	}
	
	//table attributes
	public int getRowCount()
	{
		return rowList.size();
	}
	
	public int getTableRowCount()
	{
		List<WebElement> rows = catalogTable.findElements(By.xpath("tbody/tr"));
		return rows.size();
	}
	
	public int getTableColCount()
	{
		List<WebElement> cols = catalogTable.findElements(By.xpath("tbody/tr[1]/td"));
		return cols.size();
	}
	
	public String getCellData(int row, int col)
	{
		String cellData = null;
		WebElement colElement = catalogTable.findElement(By.xpath("tr["+row+"]/td["+col+"]"));
		cellData = colElement.getText().replaceAll("\\s", " ");
		if(cellData.isEmpty()==true)
		{
			cellData = colElement.findElement(By.xpath("h1/input")).getAttribute("value");
	
		}
		return cellData;
	}
	
	public boolean isPopupExists()
	{
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void handlePopup()
	{
		driver.switchTo().alert().accept();
	}
	
}
	

