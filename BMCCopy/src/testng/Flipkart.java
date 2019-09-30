package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Flipkart 
{
	WebDriver wd = null;
	Object[] winHandle;
	Actions mouse_actions=null;

  @BeforeClass
  public void beforeClass() 
  {
	  System.out.println("In method beforeClass");
	  System.setProperty("webdriver.gecko.driver", "C:\\BMCSelenium\\CoreFiles\\geckodriver.exe");
	  wd=new FirefoxDriver();
	  wd.manage().window().maximize();
	  wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

  }

  @AfterClass
  public void afterClass() 
  {
	  System.out.println("In method afterClass");
	  wd.quit();

  }
  
  @Test(priority=1)
  public void TestLaunchFlipkart()
  {
	  wd.get("https://www.flipkart.com/");
	  wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); //Wait for response from server
	  Assert.assertTrue(wd.getTitle().startsWith("Online Shopping"));
  }
  
  @Test(priority=2, dependsOnMethods = "TestLaunchFlipkart")
  public void TestLogin() throws InterruptedException
  {
	  Thread.sleep(5000);
	  try {
		  WebElement we = wd.findElement(By.cssSelector("._2AkmmA._29YdH8"));
		  we.click();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  
	  WebElement logo = wd.findElement(By.xpath("//img[@title='Flipkart']"));
	  Assert.assertTrue(logo.isDisplayed());
  }
  
  
  @Test(priority=3,dependsOnMethods = "TestLogin")
  public void TestSelectCategory() throws InterruptedException
  {
	  Robot r;
	  try {
		  r = new Robot();
		  r.mouseMove(0, 0);
	  }
	  catch(AWTException e)
	  {
		  e.printStackTrace();
	  }
	  
	  List<WebElement> Categories = wd.findElements(By.cssSelector("span._1QZ6fC._3Lgyp8"));
	  for(WebElement link:Categories) 
	  {
		  System.out.println(link.getText());
	  }
	  
	  int rnd = (int) (Math.random()*Categories.size()); 
	  System.out.println("Selected menu: "+Categories.get(rnd).getText());
	  
	
	  
	  Thread.sleep(2000);
	  mouse_actions = new Actions(wd);
	  mouse_actions.moveToElement(Categories.get(rnd)).perform();
	  Thread.sleep(2000);
	  List<WebElement> SubMenus = wd.findElements(By.xpath("//a[contains(@href,'"+Categories.get(rnd).getText()+"')]"));
	  
	  for(WebElement list:SubMenus) 
	  {
		  System.out.println(list.getText());
	  }
	  
	  int rand = (int) (Math.random()*SubMenus.size()); 
	  System.out.println("Rand no: "+rand);
	  System.out.println("Selected sub menu: "+SubMenus.get(rand).getText());
	  
	  Thread.sleep(2000);
	  mouse_actions.moveToElement(SubMenus.get(rand)).perform();

	  
  }
  


}
