package gmopost;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;




public class GmoOnline {
	
	static WebDriver wd;
	

	public static void main(String[] args) throws Exception {
		//openBrowser("firefox");
		//gmoHome();
		//catalog();
		//placeOrder();
		//billingInfo();
		//storeReceipt();
		//closeBrowser();
		 readAccessDB();

	}

	private static void openBrowser(String browser) {
		if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\BMCSelenium\\CoreFiles\\geckodriver.exe");
			wd=new FirefoxDriver();
		}
		
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\BMCSelenium\\CoreFiles\\chromedriver.exe");
			wd=new ChromeDriver();			
		}
		
		if(browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\BMCSelenium\\CoreFiles\\geckodriver.exe");
			wd=new InternetExplorerDriver();
		}
		
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wd.get("http://demo.borland.com/gmopost/");
		wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); //Wait for response from server
	}

	private static void gmoHome() {
		// TODO Auto-generated method stub
		System.out.println("Navigated to: "+wd.getTitle());
		WebElement caption = wd.findElement(By.xpath("html/body/h1/font"));
		System.out.println("Page caption: "+caption.getText());
		WebElement button= wd.findElement(By.name("bAbout"));
		button.click();
		System.out.println("Navigated to: "+wd.getTitle());
		wd.navigate().back();
		wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		System.out.println("Navigated back to: "+wd.getTitle());
		
		button= wd.findElement(By.cssSelector("[value='Enter GMO OnLine']"));
		button.click();
		System.out.println("Navigated to: "+wd.getTitle());
		/*
		wd.navigate().back();
		wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		System.out.println("Navigated back to: "+wd.getTitle());
		
		button= wd.findElement(By.name("bBrowserTest"));
		button.click();
		System.out.println("Navigated to: "+wd.getTitle());
		wd.navigate().back();
		wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		System.out.println("Navigated back to: "+wd.getTitle());
		*/
	}

	private static void catalog() 
	{
		System.out.println("Navigated to: "+wd.getTitle());
		WebElement Qty = wd.findElement(By.name("QTY_TENTS"));
		Qty.clear();
		Qty.sendKeys("2");
		getCatalogTable();
		wd.findElement(By.cssSelector("[value='Place An Order']")).click();
		wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		
		
	}

	private static void getCatalogTable() 
	{
		WebElement table = wd.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[1]/div[1]/center/table/tbody"));
		int rows = table.findElements(By.xpath("tr")).size();
		int columns = table.findElements(By.xpath("tr[1]/td")).size(); //tr[1]/td -> all columns in 1st row
		System.out.println("Rows: "+rows);
		System.out.println("Colums: "+columns);
		for(int i=1; i<=rows;i++) 
		{
			for(int j=1; j<=columns;j++ ) 
			{
				WebElement cell = table.findElement(By.xpath("tr["+i+"]/td["+j+"]"));
				String cellData = cell.getText();
				if(cellData.isEmpty()) //cell data will be empty if it contains textbox
				{
					cell = table.findElement(By.xpath("tr["+i+"]/td["+j+"]/h1/input"));
					cellData = cell.getAttribute("name")+"="+cell.getAttribute("value");					
				}
				System.out.print(cellData +"\t");
			}
			System.out.println();
		}
	}

	private static void placeOrder() {
		// TODO Auto-generated method stub
		System.out.println("Navigated to: "+wd.getTitle());
		wd.findElement(By.cssSelector("[value='Proceed With Order']")).click();
		wd.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	}

	private static void billingInfo(String[] billAddress) {
		System.out.println("Navigated to: "+wd.getTitle());
		wd.findElement(By.name("billName")).sendKeys("BMC");
		wd.findElement(By.name("billAddress")).sendKeys("Yerawada");
		wd.findElement(By.name("billCity")).sendKeys("Pune");
		wd.findElement(By.name("billState")).sendKeys("Maharashtra");
		wd.findElement(By.name("billZipCode")).sendKeys("12345");
		wd.findElement(By.name("billPhone")).sendKeys("9876543210");
		wd.findElement(By.name("billEmail")).sendKeys("BMC@bmc.com");
		String cardSelected = selectCard("CardType");
		System.out.println(cardSelected);
		if(cardSelected.equalsIgnoreCase("amex")) {
			wd.findElement(By.name("CardNumber")).sendKeys("123456789012345");
		}else {
			wd.findElement(By.name("CardNumber")).sendKeys("1234567890123456");					
		}
		wd.findElement(By.name("CardDate")).sendKeys("11/22");
		wd.findElement(By.name("shipSameAsBill")).click();
		wd.findElement(By.cssSelector("[value='Place The Order']")).click();

		
	}
	
	private static String selectCard(String cardLocator) {
		int n = (int)(Math.random()*3)+1;
		WebElement we = wd.findElement(By.name(cardLocator)); // needs it to retrieve value which is selected in selectbox
		wd.findElement(By.cssSelector("select[name='"+cardLocator+"']>option:nth-child("+n+")")).click(); //goes to selectbox and selects random option
		return we.getAttribute("value");
		
	}

	private static void storeReceipt() {
		System.out.println("Navigated to: "+wd.getTitle());
		
	}

	private static void closeBrowser() {
		// TODO Auto-generated method stub
		
		wd.quit();
		
	}
	
	public static void readAccessDB() throws Exception {
		String dbConStr="jdbc:sqlserver://vw-pun-trt-qa04:1433;databaseName=GmoTestDB;user=sa;password=bmcAdm1n";
		Connection con = DriverManager.getConnection(dbConStr);
		String sqlQuery="Select * from GMO";
		Statement sqlStmt=con.createStatement();
		ResultSet rs = sqlStmt.executeQuery(sqlQuery);
		ResultSetMetaData rsmeta = rs.getMetaData();
		int cols=rsmeta.getColumnCount();
		String[] billAddress=new String[cols];
		System.out.println(cols);
		//print the col names with its type 
		for (int i=1;i<=cols;i++){
			System.out.println(i + "."+ rsmeta.getColumnName(i) + 
					"\t"+ rsmeta.getColumnTypeName(i));
		} 
		System.out.println();
		while (rs.next())	{
			billAddress[0] = rs.getString("Name");
			billAddress[1]= rs.getString("Address");
			billAddress[2] = rs.getString("City");
			billAddress[3] = rs.getString("State");
			billAddress[4]= rs.getString("ZipCode");
			billAddress[5] = rs.getString("Phone");
			billAddress[6] = rs.getString("Email");	
			System.out.println(Arrays.toString(billAddress));
			/*
			 * gmoHome(); catalog(); placeOrder(); billingInfo(billAddress); storeReceipt();
			 */	
		}	
		sqlStmt.close();
		con.close();
	}

}
