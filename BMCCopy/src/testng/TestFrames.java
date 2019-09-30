package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestFrames 
{
	
	static WebDriver wd;
	List<WebElement> framesList;
  
  @BeforeClass
  public void beforeClass() 
  {
	  
	  System.out.println("In method beforeClass");
	  System.setProperty("webdriver.ie.driver", "C:\\BMCSelenium\\CoreFiles\\IEDriverServer.exe");
	  wd=new InternetExplorerDriver();
	  wd.manage().window().maximize();
	  wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @AfterClass
  public void afterClass() 
  {
	  wd.quit();
  }
  
  @Test(priority=1)
  public void TestLaunchFrames() 
  {
	  wd.get("https://seleniumhq.github.io/selenium/docs/api/java/index.html");
	  wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	  Assert.assertEquals(wd.getTitle(), "Overview");
  }
  
  @Test(priority=2, dependsOnMethods = "TestLaunchFrames")
  public void TestFramesInPage() 
  {
	  framesList = wd.findElements(By.tagName("frame"));
	  Assert.assertEquals(framesList.size(), 3);
	  
  }
  

  @Test(priority=3, dependsOnMethods = "TestFramesInPage")
  public void TestFrameNavigation() 
  {
	  for(WebElement frame:framesList)
	  {
		  String frameName = frame.getAttribute("name");
		  System.out.println("----"+frameName+"----");
		  wd.switchTo().frame(frameName);
		  List<WebElement> linksList = wd.findElements(By.tagName("a"));
		  for(WebElement link:linksList) {
			  System.out.println(link.getText());
		  }
		  wd.switchTo().defaultContent();
	  }
	  
  }

}
