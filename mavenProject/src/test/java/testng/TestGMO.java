package testng;

import org.testng.annotations.Test;


import pageObject.CatalogPage;
import pageObject.GmoHome;
import pageObject.PlaceOrder;

import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class TestGMO extends TestBase
{	  
	//WebDriver wd;
	  
  @DataProvider
  public Object[][] dpCatalog() 
  {
    return new Object[][] 
    {
      new Object[] { "External Frame Backpack", "2" },
      new Object[] { "Glacier Sun Glasses", "3" }
    };    
  }
  
  @DataProvider
  public Object[][] dpAddress() 
  {
    return new Object[][] 
    {
      new Object[] { "BMC", "Yerawada", "Pune", "Maharashtra", "12345", "9876543210", "BMC@bmc.com" },
    };    
  }
  
  
  
  @Test(priority=1)
  public void TestLaunchGMO()
  {
	  wd.get("http://demo.borland.com/gmopost/");
	  wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); //Wait for response from server
	  Assert.assertEquals(wd.getTitle(), "Welcome to Green Mountain Outpost"); //mainly used for verification purpose
	  
  }
  
  @Test(priority=2,dependsOnMethods = "TestLaunchGMO") //dependsOnMethods-> execute this method only if TestLaunchGMO is executed
  public void TestGMOHome()
  {
	  //wd.findElement(By.cssSelector("[value='Enter GMO OnLine']")).click(); 
	  //ActionBot.elementClick(wd, By.cssSelector("[value='Enter GMO OnLine']")); //using bot pattern to implement global click method
	  
	  GmoHome gmoHome = PageFactory.initElements(wd, GmoHome.class); //Using Page Object Model - using page factory initializes all locators as well as constructors
	  System.out.println("Page Title: "+gmoHome.getPageTitle());
	  gmoHome.enterAboutTheGMOSite();
	  gmoHome.enterBrowserTestPage();
	  gmoHome.enterGMOOnline();
	  
	  wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); //Wait for response from server
	  Assert.assertEquals(wd.getTitle(), "OnLine Catalog"); 
  }
  
  
  @Test(priority=3,dependsOnMethods = "TestGMOHome",dataProvider = "dpCatalog", enabled = false) // created for accessing hard-coded data
  public void TestCatalog(String itemName, String qty) 
  {
	  String xpath = "//strong[normalize-space(text())='"+itemName+"']/../../following-sibling::*[last()]/descendant::input";
	  WebElement OrderQty = wd.findElement(By.xpath(xpath));
	  OrderQty.clear();
	  OrderQty.sendKeys(qty);
  }
  
  
  @Test(priority=3,dependsOnMethods = "TestGMOHome",dataProvider = "dpCatalogData",enabled=false) // created for accessing excel sheet data
  public void TestCatalog(String[] itemName) 
  {
	  String xpath = "//strong[normalize-space(text())='"+itemName[0]+"']/../../following-sibling::*[last()]/descendant::input";
	  WebElement OrderQty = wd.findElement(By.xpath(xpath));
	  OrderQty.clear();
	  OrderQty.sendKeys(itemName[1]);
//	  ActionBot.elementInput(wd, By.xpath(xpath), itemName[1]); //using bot pattern to implement global input method
  }
  
  @Test(priority=3,dependsOnMethods ="TestGMOHome")
  public void TestCatalogPage() 
  {
	  CatalogPage catalog = PageFactory.initElements(wd,CatalogPage.class);
	  System.out.println("Caption: "+catalog.getHeaderText());
	  catalog.setOrderQtyBOOTS("3");
	  catalog.setOrderQtyBACKPACKS("2");
	  catalog.setOrderQtyGLASSES("1");
	  catalog.ResetForm();
	  if(catalog.isResetForm())
	  {
		  System.out.println("All is well");
	  }
	  else
	  {
		  System.out.println("Something is wrong");
	  }
	  catalog.PlaceOrder();
	  if(catalog.isPopupExists())
	  {
		  catalog.handlePopup();
	  }
	  catalog.setOrderQtyBACKPACKS("2");
	  catalog.PlaceOrder();
	  
	 
  }
  
  @Test(priority=4,dependsOnMethods = "TestCatalog", enabled=false)
  public void TestCatalogPlaceOrder()
  {
	  //wd.findElement(By.cssSelector("[value='Place An Order']")).click();
	  //ActionBot.elementClick(wd, By.cssSelector("[value='Place An Order']"));
	  
	  PlaceOrder order = PageFactory.initElements(wd, PlaceOrder.class);
	  System.out.println("Caption: "+order.getHeaderText());
	  order.ProceedWithOrder();
	  
	  wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	  Assert.assertEquals(wd.getTitle(), "Place Order");
  }
  
  @Test(priority=5,dependsOnMethods = "TestCatalogPage")
  public void TestPlaceOrder()
  {
	  wd.findElement(By.cssSelector("[value='Proceed With Order']")).click();
//	  ActionBot.elementClick(wd,By.cssSelector("[value='Proceed With Order']"));
	  wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	  
  }
  
  @Test(priority=6,dependsOnMethods = "TestPlaceOrder",dataProvider = "dpAddress")
  public void TestBillingInfo(String[] billAddress)
  {
	  
	  wd.findElement(By.name("billName")).sendKeys(billAddress[0]);
	  wd.findElement(By.name("billAddress")).sendKeys(billAddress[1]);
	  wd.findElement(By.name("billCity")).sendKeys(billAddress[2]);
	  wd.findElement(By.name("billState")).sendKeys(billAddress[3]);
	  wd.findElement(By.name("billZipCode")).sendKeys(billAddress[4]);
	  wd.findElement(By.name("billPhone")).sendKeys(billAddress[5]);
	  wd.findElement(By.name("billEmail")).sendKeys(billAddress[6]);
	  
	  
		/*
		 * ActionBot.elementInput(wd, By.name("billName"), billAddress[0]);
		 * ActionBot.elementInput(wd, By.name("billAddress"), billAddress[1]);
		 * ActionBot.elementInput(wd, By.name("billCity"), billAddress[2]);
		 * ActionBot.elementInput(wd, By.name("billState"), billAddress[3]);
		 * ActionBot.elementInput(wd, By.name("billZipCode"), billAddress[4]);
		 * ActionBot.elementInput(wd, By.name("billPhone"), billAddress[5]);
		 * ActionBot.elementInput(wd, By.name("billEmail"), billAddress[6]);
		 */
		String cardSelected = selectCard("CardType");
		System.out.println(cardSelected);
		if(cardSelected.equalsIgnoreCase("amex")) 
		{
			wd.findElement(By.name("CardNumber")).sendKeys("123456789012345");
		}else 
		{
			wd.findElement(By.name("CardNumber")).sendKeys("1234567890123456");					
		}
		wd.findElement(By.name("CardDate")).sendKeys("11/22");
		wd.findElement(By.name("shipSameAsBill")).click();
		wd.findElement(By.cssSelector("[value='Place The Order']")).click();
		
//		ActionBot.elementInput(wd,By.name("CardDate"), "11/22");
//		ActionBot.elementClick(wd,By.name("shipSameAsBill"));
//		ActionBot.elementClick(wd,By.cssSelector("[value='Place The Order']"));
  }
  
  private String selectCard(String cardLocator) 
  {
		int n = (int)(Math.random()*3)+1;
		WebElement we = wd.findElement(By.name(cardLocator)); // needs it to retrieve value which is selected in selectbox
		wd.findElement(By.cssSelector("select[name='"+cardLocator+"']>option:nth-child("+n+")")).click(); //goes to selectbox and selects random option
//		ActionBot.elementClick(wd,By.cssSelector("select[name='"+cardLocator+"']>option:nth-child("+n+")"));
		return we.getAttribute("value");
		
	}

}
