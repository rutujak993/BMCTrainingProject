package testng;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

public class TestBase {
	protected WebDriver wd;
	

  @BeforeTest
  @Parameters({"hubAddress","platform","browser","version"})
  public void startDriver(String hubAddress, String platform, String browser, String version) throws MalformedURLException 
  {
	  DesiredCapabilities dc = new DesiredCapabilities();
	  dc.setBrowserName(browser);
	  dc.setVersion(version);
	  
	  if(platform.equalsIgnoreCase("windows"))
		  dc.setPlatform(org.openqa.selenium.Platform.WIN10);
	  if(platform.equalsIgnoreCase("any"))
		  dc.setPlatform(org.openqa.selenium.Platform.ANY);
	  
	  if(browser.equalsIgnoreCase("firefox"))
		  dc = DesiredCapabilities.firefox();
	  
	  if(browser.equalsIgnoreCase("internet explorer"))
		  dc = DesiredCapabilities.internetExplorer();
	  
	  if(browser.equalsIgnoreCase("chrome"))
		  dc = DesiredCapabilities.chrome();
	  
	  wd = new RemoteWebDriver(new URL(hubAddress), dc);
	  wd.manage().window().maximize();
	  wd.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	  

  }

  @AfterTest
  public void stopDriver() 
  {
	  wd.quit();
	  wd = null;
  }

}
