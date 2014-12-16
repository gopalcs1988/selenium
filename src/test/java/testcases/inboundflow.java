package testcases;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import java.net.URL;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

import baseclass.login;

public class inboundflow extends login
{
	public inboundflow() throws IOException
	{
		login1();
	}
	
	@BeforeTest
	@Parameters({"seleniumPort", "browser", "webSite" })
	public void homepage(int seleniumPort, String browser, String webSite) throws Exception {
		System.out.println("website"+webSite);
			
		
		
//			  DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//			  capabilities.setCapability("version", "5");
//			  capabilities.setCapability("platform", Platform.WINDOWS);
//			  
//			  this.driver = new RemoteWebDriver(new URL("http://gopalcs1988:33ac956b-8769-4e10-bce5-4d8873b621c4@ondemand.saucelabs.com:80/wd/hub"),capabilities);
			    

		File profileDirectory = new File("C://Users//gopala//AppData//Roaming//Mozilla//Firefox//Profiles//246q38qb.automation");
		FirefoxProfile profile = new FirefoxProfile(profileDirectory);
		profile.setPreference("security.mixed_content.block_active_content;false", false);
		profile.setPreference("security.mixed_content.block_display_content;true", true);
		driver = new FirefoxDriver(profile);
		
		
		//driver = new FirefoxDriver();
	//	File file = new File("D:/IE/IEDriverServer.exe");
		System.out.println("welcome");
//		System.setProperty("webdriver.chrome.driver",  file.getAbsolutePath());
//      driver = new ChromeDriver();    
		
		//System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		//driver = new InternetExplorerDriver();
		
		selenium = new WebDriverBackedSelenium(driver, webSite);
		driver.get(webSite);
		System.out.println("url before"+webSite);

//		driver.navigate().to("javascript:document.getElementById('overridelink').click()");
//		driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		System.out.println("url"+webSite);
		selenium.windowFocus();
		selenium.windowMaximize();
	}

	@Test
	public void loginpage() throws Exception
	{
		
		loginui();		
	}
	
	@Test(dependsOnMethods="loginpage")
	@Parameters({"url1"})
	public void WMSlogin(String rflogin) throws Exception
	{
		
		selenium.click("xpath=.//*[@id='Tools']/span[3]");
		pwait();
		selenium.click("xpath=.//*[@id='ASNList_WBUPopup_toolsItem']/span");
		pwait();
		selenium.select("id=dataForm:warehouse", "Plainfield");
		selenium.select("id=dataForm:bussinessUnit","ARD");
		pwait();
		selenium.click("id=dataForm:DefaultBUOK");
		pwait();
		selenium.click("xpath=.//*[@id='phMenu']/span[3]");
    	pause(1000);
		assertTrue(selenium.isTextPresent("RF Menu"));
		pause(500);
		driver.get(rflogin);
		
//		//selenium.type("xpath=.//*[@id='as_bas1_in']","RF men");
//		pause(3000);
//		driver.findElement(By.xpath(".//*[@id='as_bas1_in']")).sendKeys(Keys.DOWN);
//		pause(3000);
//		driver.findElement(By.xpath(".//*[@id='as_bas1_in']")).sendKeys(Keys.DOWN);
//		pause(3000);
//		driver.findElement(By.xpath(".//*[@id='as_bas1_in']")).sendKeys(Keys.RETURN);
//		pause(3000);")
//		driver.findElement(By.xpath(".//*[@id='as_bas1_in']")).sendKeys("RF men");
//		//selenium.type("xpath=.//*[@id='as_bas1_in']","RF men");
//		pause(3000);
		System.out.println("RF Menu login");
	}
	
	@Test(dependsOnMethods="WMSlogin")
	public void rfmenulogin()throws Exception
	{
		selenium.type("name=j_username","pkumar");
		selenium.type("name=j_password","Pramati@dec14");
		selenium.click("xpath=html/body/form/table[2]/tbody/tr/td[1]/input");
		pwait();
	}
	
	@Test(dependsOnMethods="rfmenulogin")
	public void itemcreation()throws Exception
	{
		selenium.click("id=mnbtn_dataForm:menuList:1:menubut1");
		pwait();
		selenium.click("xpath=.//*[@id='mnbtn_dataForm:menuList:0:menubut1' and text()='Receive & Sort']");
		pwait();
		driver.findElement(By.id("shipinpId")).sendKeys("245322");
		driver.findElement(By.id("shipinpId")).sendKeys(Keys.ENTER);
		pwait();
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		String src3 = (String) executor.executeScript("return document.getElementById('caseinpid').value");
		System.out.println("srcvalue " +src3);
		//String src = (String) executor.executeScript("return document.getElementById('subIframe').src");
		String id1=(String) executor.executeScript("return document.getElementById('subIframe').id");
		System.out.println("src "+id1);
		//driver.switchTo().defaultContent();
		driver.switchTo().frame(id1);
		driver.findElement(By.id("barcode")).sendKeys("887401003799");
		driver.findElement(By.id("okButton")).click();
		pwait();
		driver.findElement(By.id("secondOk")).click();
		pwait();
		//driver.findElement(By.id(""));
		//selenium.click("xpath=.//*[@id='tableConditions']/tbody/tr[3]/td");
		//driver.findElement(By.id("serialNo")).sendKeys("12345");
		//driver.switchTo().frame(id1);
		System.out.println("frame switch");
		driver.findElement(By.xpath(".//*[@id='testingForm']/div/div[4]/table/tbody/tr[3]/td")).click();
		pwait();
		driver.findElement(By.id("input1input2")).sendKeys(Keys.ENTER);
		pwait();
		driver.switchTo().defaultContent();
		pwait();
		driver.switchTo().frame(id1);
		selenium.type("id=scanItem_input", "PLT-GA-GP-CRT-001");
		pwait();
		//driver.findElement(By.id("scanItem_input")).sendKeys("PLT-GA-GP-CRT-001");
		selenium.click("id=scanItem_btn");
		System.out.println("Button click");
		//driver.findElement(By.id("scanItem_btn")).click();
		//driver.close();
		
//		driver.get(src);
//		driver.findElement(By.id("asn")).sendKeys("245322");
//		driver.findElement(By.id("barcode")).sendKeys("887401003799");
//		driver.findElement(By.id("ilpn")).sendKeys(src3);
//		driver.findElement(By.id("userId")).sendKeys("pkumar");
//		driver.findElement(By.id("continue")).click();
//		selenium.waitForPageToLoad("100000");
//		driver.findElement(By.id("okButton")).click();
//		pwait();
//		selenium.click("id=secondOk");
//		driver.get("http://test-wmtools.test.liquidation.com:9090/testingengine");
//		driver.findElement(By.id("ilpnmain")).sendKeys(src3);
//		driver.findElement(By.xpath(".//*[@id='userId' and @type='text']")).sendKeys("rarumugam");
//		driver.findElement(By.xpath(".//*[@id='WmsTools1']/input[3]")).click();
//		pwait();
//		selenium.click("xpath=.//*[@class='condition-label flt-left' and text()='A']");
//		pwait();
//		
//		driver.get("http://test-wmtools.test.liquidation.com:9090/sortingengine");
//		driver.findElement(By.id("ilpn")).sendKeys(src3);
//		driver.findElement(By.id("asn")).sendKeys("221745");
//		driver.findElement(By.id("condition")).sendKeys("A");
//		driver.findElement(By.id("userId")).sendKeys("srenganathan");
//		driver.findElement(By.id("quantity")).sendKeys("1");
//		selenium.click("xpath=.//*[@id='WmsTools']/table/tbody/tr[6]/td[1]/input");
//		pwait();
//		selenium.type("id=scanItem_input","PLT-GA-GP-CRT-001");
//		selenium.click("id=scanItem_btn");
//		pwait();
	}
}

