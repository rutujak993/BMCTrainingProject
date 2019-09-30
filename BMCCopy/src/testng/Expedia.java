package testng;

import org.testng.annotations.Test;

import utilityScripts.ActionBot;

import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Expedia 
{
	WebDriver wd = null;
	Object[] winHandle;
	
  @BeforeClass
  public void beforeClass() 
  {
	  System.out.println("In method beforeClass");
	  System.setProperty("webdriver.chrome.driver", "C:\\BMCSelenium\\CoreFiles\\chromedriver.exe");
	  wd=new ChromeDriver();
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
  public void TestLaunchExpedia()
  {
	  wd.get("https://www.expedia.co.in/");
	  wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); //Wait for response from server
	  Assert.assertTrue(wd.getTitle().startsWith("Expedia Travel"));
  }
  
  @Test(priority=2,dependsOnMethods = "TestLaunchExpedia" )
  public void TestOpenSupportPage() throws InterruptedException
  {
	  wd.findElement(By.xpath("//*[@id=\"header-support-menu\"]")).click();
	  wd.findElement(By.xpath("//*[@id=\"support-feedback\"]")).click();
	  Set<String> windows = wd.getWindowHandles();
	  winHandle = new Object[windows.size()];
	  winHandle = windows.toArray();
	  wd.switchTo().window(String.valueOf(winHandle[1]));
	  Thread.sleep(5000);
	  wd.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);	 
	  //wd.findElement(By.xpath("//a[contains(text(),'Feedback')]")).click();
	  Assert.assertTrue(wd.getCurrentUrl().contains("expedia"));

  }
  
  @Test(priority=3,dependsOnMethods = "TestOpenSupportPage" )
  public void TestSendFeedback() throws InterruptedException
  {
	  Thread.sleep(2000);
	  wd.findElement(By.xpath("//a[contains(text(),'Feedback')]")).click();
	  Assert.assertTrue(wd.findElement(By.xpath("//*[@id=\"twocolumnMessage\"]")).getText().contains("Thank you"));
	  wd.findElement(By.xpath("//*[@id=\"rater_3\"]")).click();
	  
	  WebElement we = wd.findElement(By.cssSelector("#topic_select"));
	  Select topics = new Select(we);
	  int rnd = (int) (Math.random()*topics.getOptions().size());
	  topics.selectByIndex(rnd);
	  System.out.println(we.getAttribute("value")+ " selected from topics");
	  wd.findElement(By.cssSelector("#comment_box")).sendKeys("some topic selected");

  }
  
  @Test(priority=4,dependsOnMethods = "TestSendFeedback" )
  public void TestSwitchToFirstBrowser()
  {
	  wd.close();
	  wd.switchTo().window(String.valueOf(winHandle[0]));
	  Assert.assertTrue(wd.getTitle().startsWith("Expedia Travel"));
  }
  
  @Test(priority=5,dependsOnMethods = "TestSwitchToFirstBrowser" )
  public void TestDestinationSelection() throws InterruptedException
  {
	  WebElement source = wd.findElement(By.cssSelector("#hotel-destination-hp-hotel"));
	  source.sendKeys("Be");
	  Thread.sleep(500);
	  source.sendKeys(Keys.DOWN);
	  Thread.sleep(500);
	  source.sendKeys(Keys.DOWN);
	  Thread.sleep(500);
	  source.sendKeys(Keys.DOWN);
	  Thread.sleep(500);
	  source.sendKeys(Keys.ENTER);
	  System.out.println("City selected: "+source.getAttribute("value"));
	  Assert.assertTrue(source.getAttribute("value").startsWith("Be"));
  }


}
