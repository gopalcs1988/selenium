package baseclass;


import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;


@SuppressWarnings("deprecation")
public class login  extends SeleneseTestCase 
{
	private Properties props;
	public String username;
	public String password;
	public WebDriver driver;
	public WebDriverBackedSelenium selenium;
	public static String logfile;
	public String url1;

	public void login1() throws IOException {


	    props = new Properties();
		props.load(login.class
				.getResourceAsStream("/testconfig.csv"));
		username = props.getProperty("username");
		password = props.getProperty("password");
		url1=props.getProperty("url");


	}
	
	public void pwait()
	{
		
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);	 
   	    return;
	}
	
	public void loginui()
	{
		selenium.type("id=j_username", username);
		selenium.type("id=j_password", password);
		selenium.click("name=btnEnter");
		pwait();
		
	}
}
	