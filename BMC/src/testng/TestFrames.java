package testng;

import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class TestFrames extends TestBase
{
	
	//static WebDriver wd;
	List<WebElement> framesList;
  
  
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
