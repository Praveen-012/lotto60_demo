package org.lotto.lib;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.tools.ant.filters.LineContains.Contains;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.Select;

public class GeneralLotto extends GlobalLotto {

	
	public static WebDriver driver;
	public static WebDriver driver2;
	public static Actions a;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Robot r;
	public static Screen scrn;
	
	//Reusable methods 
	
	public void openLottoApp() throws Exception {
	
		//WebDriverManager.chromedriver().browserVersion("111.0.5563.64").setup();
		
		WebDriverManager.chromedriver().setup();
		//WebDriverManager.edgedriver().setup();
			
		ChromeOptions co=new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		
		EdgeOptions options=new EdgeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		//disable infobars
		co.addArguments("--disable-infobars");
		co.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

		//disable notification parameter
		
		//co.addArguments("--disable-notifications");
		
		co.addArguments("--disable-notifications");
		co.addArguments("--disable-geolocation");
		co.addArguments("--disable-media-stream");
		//co.addArguments("--disable-profile.password_manager");
		
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		co.setExperimentalOption("prefs", prefs);
		
		//co.setExperimentalOption("credentials_enable_service", false);
		//co.setExperimentalOption("profile.password_manager_enabled", false);
		
		
		driver=new ChromeDriver(co);
		//driver2=new EdgeDriver(options);
		
		//driver=new EdgeDriver();
		WebDriver driver=new ChromeDriver();
		driver.navigate().to(dev_url);
		
		driver2.get(staging_url);
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		js=(JavascriptExecutor)driver;
		
		wait= new WebDriverWait(driver,Duration.ofSeconds(20));
		
		a=new Actions(driver);
		
		r=new Robot();
		
		scrn=new Screen();
				
		System.out.println("***********Application opened successfully************");
		
		
	}
	
		public void closeLottoapp() {
		
			driver.close();
			System.out.println("***********Application closed successfully********");
		
	}
	
	public void loginPositiveCaseModule() throws Exception {
		
		//login module - logging after 5 minutes of account locked
		
				//login/register btn clk
				Thread.sleep(300000);
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
				
				//email field
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
				Thread.sleep(2000);
				
				//password field
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass);
				Thread.sleep(2000);
				
				//eye visibility icon 
				driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']")).click();
				Thread.sleep(3000);
				
				//login btn clk
				WebElement loginbtn = driver.findElement(By.xpath("(//button[text()='Login'])[2]"));
				js.executeScript("arguments[0].click()", loginbtn);
				Thread.sleep(4000);
		
		System.out.println("***************Logged in successfully*************");

	}
	
		public void loginNegativeCaseModule() throws Exception {
			
			//Logging with empty data
				
				//login/register btn clk
		
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
				
				//login btn clk
				WebElement loginbtnclk = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
				loginbtnclk.click();
				Thread.sleep(4000);
				
			//Logging with invalid data
				
				//email field
				WebElement emailfield = driver.findElement(By.xpath("//input[@id='email']"));
				emailfield.sendKeys(invalidemail);
				Thread.sleep(2000);
				
				//password field
				WebElement passfield = driver.findElement(By.xpath("//input[@id='password']"));
				passfield.sendKeys(invalidpass);
				Thread.sleep(2000);
				
				//eye visibility icon 
				WebElement eyeiconclk = driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']"));
				eyeiconclk.click();
				Thread.sleep(3000);
				
				//login btn clk
				loginbtnclk.click();
				Thread.sleep(4000);
				
				emailfield.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
				
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				
				passfield.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
				
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
	
			//Logging with incorrect data
				
				//email field
				emailfield.sendKeys(incorrectemail);
				Thread.sleep(2000);
				
				//password field
				passfield.sendKeys(incorrectpass);
				Thread.sleep(2000);
				
				//login btn clk
				loginbtnclk.click();
				Thread.sleep(4000);
				
				emailfield.click();
				
				//Logging with invalid pass data
				
				//email field
				emailfield.sendKeys(email);
				Thread.sleep(2000);
				
				//password field
				passfield.sendKeys(invalidpass2);
				Thread.sleep(2000);
				
				loginbtnclk.click();
				Thread.sleep(4000);
				
				
			//Checking whether logging in validly displays invalid msg or not
				
				//email field
				emailfield.sendKeys(email);
				Thread.sleep(2000);
				
				//password field
				passfield.sendKeys(pass);
				Thread.sleep(2000);
				
				loginbtnclk.click();
				Thread.sleep(4000);
				
				//Logout
				//Logged in user click
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				WebElement loggedinuser = driver.findElement(By.xpath("//div[@id='avatar']"));
				js.executeScript("arguments[0].click()", loggedinuser);
				Thread.sleep(2000);
				
				//Logout Button
				WebElement logoutbtnclk = driver.findElement(By.xpath("//small[text()='Logout']"));
				js.executeScript("arguments[0].click()", logoutbtnclk);
				Thread.sleep(5000);
				
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
				
				//eye visibility icon
				WebElement eyeiconclk2 = driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']"));
				eyeiconclk2.click();
				Thread.sleep(3000);
			
			//Checking account locked or not after 5 try
				
				for (int i = 0; i <=4; i++) {
					
					//Logging with invalid pass data
					
					//email field
					WebElement emailfield2 = driver.findElement(By.xpath("//input[@id='email']"));
					emailfield2.sendKeys(email);
					Thread.sleep(2000);
					
					//password field
					WebElement passfield2 = driver.findElement(By.xpath("//input[@id='password']"));
					passfield2.sendKeys(invalidpass2);
					Thread.sleep(2000);
				
					//login btn clk
					WebElement loginbtnclk2 = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
					loginbtnclk2.click();
					Thread.sleep(4000);
					
				}
			
	}
	
	public void loginwithSignedupEmailModule() throws Exception {
		
			//login module
				
				//email field
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email2);
				Thread.sleep(2000);
				
				//password field
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass2);
				Thread.sleep(2000);
				
				//eye visibility icon 
				driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']")).click();
				Thread.sleep(2000);
				
				//login btn clk
				driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				Thread.sleep(4000);
				
				//Two factor authentication as "None'
				driver.findElement(By.xpath("//span[text()='Submit']")).click();
				Thread.sleep(3000);
					
				System.out.println("***************Login with email successful***********");	

	}
	public void loginwithSignedupGmailModule() throws Exception {
		
			//login module
		
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
				
				//email field
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email3);
				Thread.sleep(2000);
				
				//password field
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass3);
				Thread.sleep(2000);
				
				//eye visibility icon 
				driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']")).click();
				Thread.sleep(2000);
				
				//login btn clk
				driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				Thread.sleep(4000);
						
				System.out.println("**************Logged in successfully**************");
		
	}
	
	public void loggingwith_GoogleAuth() throws Exception {
		
				//login module
				
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
				
				//email field
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email3);
				Thread.sleep(2000);
				
				//password field
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass3);
				Thread.sleep(2000);
				
				//eye visibility icon 
				driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']")).click();
				Thread.sleep(2000);
				
				//login btn clk
				driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				Thread.sleep(4000);
				
				System.out.println("**************Logged in successfully**************");
				
			//Submit button click after verifying with Invalid Google Authentication
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				
				driver.findElement(By.xpath("//span[text()='Submit']")).click();
				Thread.sleep(4000);
				
				if (driver.getPageSource().contains("Button to launch messaging window")) {
					
					System.out.println("*************Invalid Code is not accepted***********");
					
				} else {
					
					System.out.println("************Invalid Code is accepted successfully***********");
				}
				
			//Submit button click after verifying with Invalid Google Authentication
				
				WebElement lastfieldcode = driver.findElement(By.xpath("(//input[@class='otpInput'])[6]"));
				lastfieldcode.click();
				Thread.sleep(2000);
				
				for (int i = 1; i < 12; i++) {
					
					r.keyPress(KeyEvent.VK_DELETE);
					Thread.sleep(1000);
				}
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
						
				driver.findElement(By.xpath("//span[text()='Submit']")).click();
				Thread.sleep(4000);	
		
				System.out.println("**************Logged in with GoogleAuthentication***********");
				
				//Restoring to "None" option
					
				//Profile avatar click
				driver.findElement(By.xpath("//div[@id='avatar']")).click();
				Thread.sleep(2000);
						
				//Profile tab click
				driver.findElement(By.xpath("(//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root center MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'])[1]")).click();
				Thread.sleep(2000);
							
				//Two factor auth tab click
				driver.findElement(By.xpath("//p[text()='Two-Factor Authentication']")).click();
				Thread.sleep(2000);
				
				//Clicking none option btn
				driver.findElement(By.xpath("(//input[@name='quiz'])[3]")).click();
				Thread.sleep(2000);
						
				//Submit btn clk
				driver.findElement(By.xpath("//span[text()='Submit']")).click();
				Thread.sleep(2000);
	}
	
	public void loggingwith_NoneAuthenticationOption() throws Exception {
		
				//login module
					
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
					
				//email field
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email3);
				Thread.sleep(2000);
				
				//password field
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass3);
				Thread.sleep(2000);
					
				//eye visibility icon 
				driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']")).click();
				Thread.sleep(2000);
					
				//login btn clk
				driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				Thread.sleep(4000);
					
				System.out.println("**************Logged in successfully with 'None' authentication option**************");
	
		
}
	
	public void loggingwith_ChangedPassword() throws Exception {
		
				//login module
				
			//Checking whether it is logging with old password
					
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
					
				//email field
				WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
				email.sendKeys(cp_email);
				Thread.sleep(2000);
					
				//password field
				WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
				pass.sendKeys(cp_pass1);
				Thread.sleep(2000);
					
				//eye visibility icon 
				WebElement eyeicon = driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']"));
				eyeicon.click();
				Thread.sleep(2000);
			
				//login btn clk
				WebElement loginbtn = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
				loginbtn.click();
				Thread.sleep(4000);
					
				System.out.println("**************Not Logged in with old password**************");
					
				//Checking whether it is logging with changed password
				
				//email field
				email.sendKeys(cp_email);
				Thread.sleep(2000);
							
				//password field
				pass.sendKeys(cp_pass2);
				Thread.sleep(2000);
					
				//login btn clk
				loginbtn.click();
				Thread.sleep(4000);
					
				System.out.println("**************Logged in successfully with changed password**************");

	}
	
	public void gameLogin() throws Exception {
		
			//login module
			
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
					
				//email field
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys(instant_email);
				Thread.sleep(2000);
					
				//password field
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(instant_pass);
				Thread.sleep(2000);
					
				//eye visibility icon 
				driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']")).click();
				Thread.sleep(2000);
					
				//login btn clk
				driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				Thread.sleep(4000);
			
			System.out.println("***************Logged in successfully*************");
		
	}
	
	
	public void logoutModule () throws Exception {
		
		//Logout Module
				
				//Logged in user click
				//FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS);
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				WebElement loggedinuser = driver.findElement(By.xpath("//div[@id='avatar']"));
				js.executeScript("arguments[0].click()", loggedinuser);
				Thread.sleep(2000);
				
				//Logout Button
				WebElement logoutbtnclk = driver.findElement(By.xpath("//small[text()='Logout']"));
				js.executeScript("arguments[0].click()", logoutbtnclk);
				Thread.sleep(5000);
			
			System.out.println("**************Logged out successfully**************");
		
			driver.close();
		
	}
	public void loggingoutgmail() throws Exception {
		
				//Logout Module
				Thread.sleep(4000);			
				WebElement  loggedinuser = driver.findElement(By.xpath("//div[@id='avatar']"));
				js.executeScript("arguments[0].click()", loggedinuser);
				Thread.sleep(2000);
				
				//Logout Button
				WebElement logoutbtnclk = driver.findElement(By.xpath("//button[@class='btncustom-logout btn btn-primary']"));
				js.executeScript("arguments[0].click()", logoutbtnclk);
				Thread.sleep(5000);
			
				System.out.println("***********Logged out successfully**************");
				
				driver.close();		
	}
	
	public void signupwithGmail_PositiveCase() throws Exception {
		
			//Sign Up with Google account
			
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
				
				//Sign up with google button clk
				driver.findElement(By.id("google-btn")).click();
				Thread.sleep(2000);
				
				//Google - email field
				driver.findElement(By.id("identifierId")).sendKeys(gmailfield);
				Thread.sleep(2000);
				
				//next btn clk in email page
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				Thread.sleep(3000);
				
				//Password field
				driver.findElement(By.xpath("//input[@name='Passwd']")).sendKeys(gmailpass);
				Thread.sleep(2000);
				
				//Google - pass field checkbox
				driver.findElement(By.xpath("//input[@type='checkbox']")).click();
				Thread.sleep(2000);
				
				//Next btn clk in passwrd page
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				Thread.sleep(4000);
				
			//Update Info Page ---------- 
				
				//Firstname field	
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				WebElement frstname = driver.findElement(By.xpath("//input[@id='firstName']"));
				
				//Clearing Pre populated data
				frstname.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				frstname.sendKeys(data_firstname);
				//js.executeScript("arguments[0].setAttribute('value','data_firstname", frstname);
				Thread.sleep(2000);
				
				//Lastname field
				WebElement lastname = driver.findElement(By.name("lastName"));
				
				//Clearing Pre populated data
				lastname.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				lastname.sendKeys(data_lastname);
				Thread.sleep(2000);
				
				//Dob data
				WebElement dob = driver.findElement(By.id("date-picker-dialog"));
				dob.sendKeys(data_birthdate);
				Thread.sleep(2000);
				
				//dob.clear();
				Thread.sleep(2000);
				
				//Dob calendar popup
				//driver.findElement(By.xpath("(//*[local-name()='svg' and @aria-hidden='true'])[1]")).click();
				
				Pattern ptrn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Date icon.png");
				scrn.wait(ptrn,30);
				scrn.click();
				Thread.sleep(2000);
				
				//year click
				driver.findElement(By.xpath("//h6[@class='MuiTypography-root MuiPickersToolbarText-toolbarTxt MuiTypography-subtitle1']")).click();
				Thread.sleep(2000);
			
				//year scrolling up
				WebElement yearscrollup = driver.findElement(By.xpath("//div[text()='2002']"));
				js.executeScript("arguments[0].scrollIntoView(false)",yearscrollup);
				Thread.sleep(2000);
				
				//year selection
				driver.findElement(By.xpath("//div[text()='1999']")).click();
				Thread.sleep(2000);
			
				//rightchevronbtn 
				//WebElement leftchevronbtnclk = driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[5]"));
				
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Calendar popup - left chevron button.png");
					
				for (int i = 0; i < 4; i++) {
					
					//js.executeScript("arguments[0].click()", leftchevronbtnclk);
					
					scrn.wait(ptrn3,30);
					scrn.click();
					Thread.sleep(2000);
					
				}
				//leftchevronbtn
				//WebElement rightchevronbtnclk = driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[6]"));
				
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Calendar popup - right chevron button.png");
				
				for (int i = 0; i < 4; i++) {
					
					//js.executeScript("arguments[0].click()", rightchevronbtnclk);
					
					scrn.wait(ptrn4,30);
					scrn.click();
					Thread.sleep(2000);
					
				}

				Pattern ptrn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\calendar popup - date .png");
				scrn.wait(ptrn2,30);
				scrn.click();
	
				//OK button click
				driver.findElement(By.xpath("//span[text()='OK']")).click();
				Thread.sleep(2000);
				
				//scrolling down
				WebElement phonenofield = driver.findElement(By.xpath("//input[@id='phone']"));
				js.executeScript("arguments[0].scrollIntoView(true)",phonenofield);
				
				//Phone number field - Country flag click
				WebElement countrycodeclk = driver.findElement(By.xpath("//div[@class='flag us']"));
				countrycodeclk.click();
				Thread.sleep(2000);
				
				//Country code
				WebElement bruneitext = driver.findElement(By.xpath("//span[text()='Brunei']"));
				js.executeScript("arguments[0].scrollIntoView(false)",bruneitext );
				Thread.sleep(2000);
				
				WebElement braziltext = driver.findElement(By.xpath("//span[text()='Brazil']"));
				braziltext.click();
				Thread.sleep(2000);
				
				//Phone no field
				driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phnum);
				Thread.sleep(2000);
				
				//Password field
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(gmailpass);
				Thread.sleep(2000);
				
				//eye icon
				driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']")).click();
				Thread.sleep(2000);
				
				//Currency dropdown selection
				driver.findElement(By.xpath("//select[@name='currency']")).click();
				Thread.sleep(2000);
				
				//Changing to BRL Option
				WebElement BRLoption = driver.findElement(By.xpath("//option[text()='Brazil Real']"));
				BRLoption.click();
				Thread.sleep(3000);
				
				//Submit button click
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				Thread.sleep(2000);
				
				System.out.println("Sign up with gmail is successful");
				
	}
	
	public void signupwithGmail_NegativeCase() throws Exception{
		
			//Sign Up with Google account
				
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
				
				//Sign up with google button clk
				driver.findElement(By.id("google-btn")).click();
				Thread.sleep(2000);
				
				//Google - email field
				driver.findElement(By.id("identifierId")).sendKeys(gmailfield);
				Thread.sleep(2000);
				
				//next btn clk in email page
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				Thread.sleep(3000);
				
				//Password field
				driver.findElement(By.xpath("//input[@name='Passwd']")).sendKeys(gmailpass);
				Thread.sleep(2000);
				
				//Google - pass field checkbox
				driver.findElement(By.xpath("//input[@type='checkbox']")).click();
				Thread.sleep(2000);
				
				//Next btn clk in passwrd page
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				Thread.sleep(4000);
				
				//Update Info Page ---------- 
				
			//Checking without entering data in all fields
				
				//Clearing Pre populated data
				//Firstname field
				WebElement firstnamefield = driver.findElement(By.id("firstName"));
				firstnamefield.click();
			
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				//Lastname field
				WebElement lastnamefield = driver.findElement(By.id("lastName"));
				lastnamefield.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				//Submit button click
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				Thread.sleep(2000);
			
			//Checking without entering valid data in all fields
				
				//Firstname field
				WebElement firstnamefield2 = driver.findElement(By.id("firstName"));
				firstnamefield2.sendKeys(frstnme_negdata);
				Thread.sleep(2000);
				
				//Lastname field
				WebElement lastnamefield2 = driver.findElement(By.id("lastName"));
				lastnamefield2.sendKeys(lstnme_negdata);
				Thread.sleep(2000);
	
				//DOB field
				Pattern ptrn1=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Date icon.png");
				scrn.wait(ptrn1,30);
				scrn.click();
				Thread.sleep(2000);
				
				//rightchevronbtn click
				Pattern ptrn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Calendar popup - left chevron button.png");
				scrn.wait(ptrn2,30);
				scrn.click();
				Thread.sleep(2000);
					
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\calendar popup - date .png");
				scrn.wait(ptrn3,30);
				scrn.click();
				Thread.sleep(2000);		
				
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\calendar popup - cancel button.png");
				scrn.wait(ptrn4,30);
				scrn.click();
				Thread.sleep(2000);	
				
				//Entering invalid dob data
				WebElement dobgmail = driver.findElement(By.id("date-picker-dialog"));
				dobgmail.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				dobgmail.sendKeys(birthdate_negdata);
				Thread.sleep(2000);
				
				//Phone number field - Country flag click
				WebElement countrycodeclk = driver.findElement(By.xpath("//div[@class='flag us']"));
				countrycodeclk.click();
				Thread.sleep(2000);
				
				r.keyPress(KeyEvent.VK_I);
				r.keyRelease(KeyEvent.VK_I);
				Thread.sleep(2000);
				
				WebElement indiatext = driver.findElement(By.xpath("//span[text()='India']"));
				indiatext.click();
				Thread.sleep(2000);
				
				//Phone no field
				WebElement phonenumfield = driver.findElement(By.xpath("//input[@id='phone']"));
				phonenumfield.sendKeys(phonenum_negdata);
				Thread.sleep(2000);
				
				//Password field
				WebElement passfield = driver.findElement(By.xpath("//input[@id='password']"));
				passfield.sendKeys(pass_negdata);
				Thread.sleep(2000);
				
				//eye icon
				driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']")).click();
				Thread.sleep(2000);
				
				//Currency dropdown selection 
				driver.findElement(By.xpath("//select[@name='currency']")).click();
				Thread.sleep(2000);
				
				//Choosing RUB Option
				WebElement RUBoption = driver.findElement(By.xpath("//option[text()='Russian Ruble']"));
				RUBoption.click();
				Thread.sleep(3000);
				
				//Currency dropdown selection
				driver.findElement(By.xpath("//select[@name='currency']")).click();
				Thread.sleep(2000);
				
				//Changing to BRL Option
				WebElement BRLoption = driver.findElement(By.xpath("//option[text()='Brazil Real']"));
				BRLoption.click();
				Thread.sleep(3000);
			
				//Submit button click
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				Thread.sleep(2000);
				
				driver.close();
			
	}
	public void loginEmail_withoutConfirmation() throws Exception {
		
		//Checking whether it is accepting without confirming the account from email	
				
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
				
				//email field
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys(gmailfield);
				Thread.sleep(2000);
				
				//password field
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(gmailpass);
				Thread.sleep(2000);
				
				//eye visibility icon 
				driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']")).click();
				Thread.sleep(2000);
				
				//login btn clk
				driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				Thread.sleep(5000);
				
				System.out.println("**************It is not logging without confirming the account**************");
						
				driver.close();

	}
	public void signupwithExisting_gmailid() throws Exception {
				
				//Sign Up with Google account
				
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
				
				//Sign up with google button clk
				driver.findElement(By.id("google-btn")).click();
				Thread.sleep(2000);
				
				//Google - email field
				driver.findElement(By.id("identifierId")).sendKeys(gmailfield);
				Thread.sleep(2000);
				
				//next btn clk in email page
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				Thread.sleep(3000);
				
				//Password field
				driver.findElement(By.xpath("//input[@name='Passwd']")).sendKeys(gmailpass);
				Thread.sleep(2000);
				
				//Google - pass field checkbox
				driver.findElement(By.xpath("//input[@type='checkbox']")).click();
				Thread.sleep(2000);
				
				//Next btn clk in passwrd page
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				Thread.sleep(4000);
				
				System.out.println("*********Automatically logged in with Gmail************");

	}
	
	public void signupwithEmail_PositiveCase() throws Exception {
		
			//Sign Up with Google account
				
				//login/register btn clk
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
				
				//Sign up with Email button clk
				driver.findElement(By.xpath("(//span[text()='Signup with Email'])[1]")).click();
				Thread.sleep(2000);
		
				//Email field
				driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emaildata);
				Thread.sleep(2000);
		
				//Password field
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(passdata);
				Thread.sleep(2000);
				
				//eye icon
				driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']")).click();
				Thread.sleep(2000);
				
				//Firstname field
				driver.findElement(By.id("firstName")).sendKeys(frstnmedata);
				Thread.sleep(2000);
				
				//Lastname field
				driver.findElement(By.id("lastName")).sendKeys(lstnmedata);
				Thread.sleep(2000);
				
				//Phone number field - Country flag click
				WebElement countrycodeclk = driver.findElement(By.xpath("//div[@class='flag us']"));
				countrycodeclk.click();
				Thread.sleep(2000);
				
				//Country code selection popup
				
					//WebElement countrypopup = driver.findElement(By.xpath("//ul[@class='MuiList-root MuiMenu-list MuiList-padding']"));
				
					//js.executeScript("window.scrollBy(0,2500)",countrypopup);
					//Thread.sleep(2000);
				
					//js.executeScript("document.querySelector('.MuiList-root.MuiMenu-list.MuiList-padding').scrollBy(0,2500)",indiatext);
				
				WebElement iraqtext = driver.findElement(By.xpath("//span[text()='Iraq']"));
				js.executeScript("arguments[0].scrollIntoView(false)",iraqtext );
				Thread.sleep(2000);
				
				WebElement indiatext = driver.findElement(By.xpath("//span[text()='India']"));
				indiatext.click();
				Thread.sleep(2000);
				
				//Phone no field
				driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phone_num);
				Thread.sleep(2000);
				
				//Dob data
				WebElement dobemail = driver.findElement(By.id("date-picker-dialog"));
				dobemail.sendKeys(birthdatedata);
				Thread.sleep(2000);
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				//Dob calendar popup
				//driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[4]")).click();
				Pattern ptrn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Date icon.png");
				scrn.wait(ptrn,30);
				scrn.click();
				Thread.sleep(2000);
				
				//year click
				driver.findElement(By.xpath("//h6[@class='MuiTypography-root MuiPickersToolbarText-toolbarTxt MuiTypography-subtitle1']")).click();
				Thread.sleep(2000);
			
				//year scrolling up
				WebElement yearscrollup = driver.findElement(By.xpath("//div[text()='2002']"));
				js.executeScript("arguments[0].scrollIntoView(false)",yearscrollup);
				Thread.sleep(2000);
				
				//year selection
				driver.findElement(By.xpath("//div[text()='1999']")).click();
				Thread.sleep(2000);
			
				//rightchevronbtn 
				//WebElement leftchevronbtnclk = driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[5]"));
				
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Calendar popup - left chevron button.png");
		
				for (int i = 0; i < 4; i++) {
					
					//js.executeScript("arguments[0].click()", leftchevronbtnclk);
					
					scrn.wait(ptrn3,30);
					scrn.click();
					Thread.sleep(2000);
					
				}
				//leftchevronbtn
				//WebElement rightchevronbtnclk = driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[6]"));
				
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Calendar popup - right chevron button.png");
				
				for (int i = 0; i < 4; i++) {
					
					//js.executeScript("arguments[0].click()", rightchevronbtnclk);
					
					scrn.wait(ptrn4,30);
					scrn.click();
					Thread.sleep(2000);
				}

				Pattern ptrn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\calendar popup - date .png");
				scrn.wait(ptrn2,30);
				scrn.click();
	
				//OK button click
				driver.findElement(By.xpath("//span[text()='OK']")).click();
				Thread.sleep(2000);
				
				//Currency dropdown selection 
				driver.findElement(By.xpath("//select[@name='currency']")).click();
				Thread.sleep(2000);
				
				//Choosing RUB Option
				WebElement RUBoption = driver.findElement(By.xpath("//option[text()='Russian Ruble']"));
				RUBoption.click();
				Thread.sleep(3000);
	
				//Terms and conditions Checkbox click
				driver.findElement(By.name("check")).click();
				Thread.sleep(2000);
				
				//Register Button click
				driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				Thread.sleep(4000);
				
				//driver.navigate().back();
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[8]")));
				driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
				Thread.sleep(2000);
				
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='feature_productName__HqKos']"))); 
				//login/register btn clk
				//driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				//Thread.sleep(2000);
				
				//Sign up with Email button clk
				driver.findElement(By.xpath("(//span[text()='Signup with Email'])[1]")).click();
				Thread.sleep(2000);
				
				//Login button click
				Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\login text (sign up with email popup).png");
				scrn.wait(ptrn5,30);
				scrn.click();
				Thread.sleep(2000);
				
				//driver.findElement(By.xpath("(//span[text()='Login'])[1]")).click();
				
				/*r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_T);
				
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_T);
				Thread.sleep(2000);
				
				//Frame handling
				driver.switchTo().frame("backgroundImage");
				
				//Search box
				driver.findElement(By.xpath("//input[@placeholder='Search Google or type a URL']")).sendKeys("test");
				Thread.sleep(2000);
				
				Pattern ptrn1=new Pattern("D:\\Projects\\Eclipse backup\\Eclipse\\ShopQApplication\\Images\\search google text .png");
				scrn.wait(ptrn1,50);
				scrn.click();
				Thread.sleep(2000);
				
				//a.sendKeys("test").perform();
				
				//Runtime.getRuntime().exec("‪D:\\Documents\\Documents\\Google search script.exe");
				//Thread.sleep(5000);*/
						
	}
	
	public void signupwithEmail_NegativeCase() throws Exception {
		
		//Sign Up with Email account
			
			//Checking without entering data in all fields
			
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(4000);
				
				//Sign up with Email button clk
				WebElement signupwithemail = driver.findElement(By.xpath("(//button[@type='button'])[16]"));
				signupwithemail.click();
				Thread.sleep(2000);
				
				//Register button click - Checking error message in all fields
				driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				Thread.sleep(5000);
				
			//Checking without entering valid data in all fields
				
				//Email field
				WebElement emailfield = driver.findElement(By.xpath("//input[@id='email']"));
				emailfield.sendKeys(email_negdata);
				Thread.sleep(2000);
		
				//Password field
				WebElement passfield = driver.findElement(By.xpath("//input[@id='password']"));
				passfield.sendKeys(pass_negdata);
				Thread.sleep(2000);
				
				//eye icon
				driver.findElement(By.xpath("//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray']")).click();
				Thread.sleep(2000);
				
				//Firstname field
				WebElement firstnamefield = driver.findElement(By.id("firstName"));
				firstnamefield.sendKeys(frstnme_negdata);
				Thread.sleep(2000);
				
				//Lastname field
				WebElement lastnamefield = driver.findElement(By.id("lastName"));
				lastnamefield.sendKeys(lstnme_negdata);
				Thread.sleep(2000);
				
				//Phone number field - Country flag click
				WebElement countrycodeclk = driver.findElement(By.xpath("//div[@class='flag us']"));
				countrycodeclk.click();
				Thread.sleep(2000);
				
				r.keyPress(KeyEvent.VK_I);
				r.keyRelease(KeyEvent.VK_I);
				Thread.sleep(2000);
				
				/*WebElement iraqtext = driver.findElement(By.xpath("//span[text()='Iraq']"));
				js.executeScript("arguments[0].scrollIntoView(false)",iraqtext );
				Thread.sleep(2000);*/
				
				WebElement indiatext = driver.findElement(By.xpath("//span[text()='India']"));
				indiatext.click();
				Thread.sleep(2000);
				
				//Phone no field
				WebElement phonenumfield = driver.findElement(By.xpath("//input[@id='phone']"));
				phonenumfield.sendKeys(phonenum_negdata);
				Thread.sleep(2000);
					
				//DOB field
				Pattern ptrn1=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Date icon.png");
				scrn.wait(ptrn1,30);
				scrn.click();
				Thread.sleep(2000);
				
				//rightchevronbtn click
				Pattern ptrn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Calendar popup - left chevron button.png");	
				scrn.wait(ptrn2,30);
				scrn.click();
				Thread.sleep(2000);
					
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\calendar popup - date .png");
				scrn.wait(ptrn3,30);
				scrn.click();
				Thread.sleep(2000);			
				
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\calendar popup - cancel button.png");
				scrn.wait(ptrn4,30);
				scrn.click();
				Thread.sleep(2000);
		
				//Entering invalid dob data
				WebElement dobemail = driver.findElement(By.id("date-picker-dialog"));
				dobemail.sendKeys(birthdate_negdata);
				Thread.sleep(2000);
				
				//Currency dropdown selection 
				driver.findElement(By.xpath("//select[@name='currency']")).click();
				Thread.sleep(2000);
				
				//Choosing RUB Option
				WebElement RUBoption = driver.findElement(By.xpath("//option[text()='Russian Ruble']"));
				RUBoption.click();
				Thread.sleep(3000);
				
				//Currency dropdown selection
				driver.findElement(By.xpath("//select[@name='currency']")).click();
				Thread.sleep(2000);
				
				//Changing to BRL Option
				WebElement BRLoption = driver.findElement(By.xpath("//option[text()='Brazil Real']"));
				BRLoption.click();
				Thread.sleep(3000);
			
				//Register button click - Checking error message in all fields
				driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				Thread.sleep(5000);
				
			//Clearing all data
				emailfield.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				passfield.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				firstnamefield.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				lastnamefield.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				phonenumfield.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				
				dobemail.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
			//Signing up with existing email id
				
				//Email field
				emailfield.sendKeys(email_existingemail);
				Thread.sleep(2000);
		
				//Password field
				passfield.sendKeys(passdata);
				Thread.sleep(2000);
				
				//Firstname field
				firstnamefield.sendKeys(frstnmedata);
				Thread.sleep(2000);
				
				//Lastname field
				lastnamefield.sendKeys(lstnmedata);
				Thread.sleep(2000);
				
				//Phone no field
				phonenumfield.sendKeys(phone_num);
				Thread.sleep(2000);
				
				//Entering dob data
				dobemail.sendKeys(birthdatedata);
				Thread.sleep(2000);
				
				//Terms and conditions Checkbox click
				WebElement checkbox = driver.findElement(By.name("check"));
				checkbox.click();
				Thread.sleep(2000);
				
				//Currency dropdown selection 
				driver.findElement(By.xpath("//select[@name='currency']")).click();
				Thread.sleep(2000);
				
				//Choosing RUB Option
				WebElement RUBoption2 = driver.findElement(By.xpath("//option[text()='Russian Ruble']"));
				RUBoption2.click();
				Thread.sleep(3000);
				
				//Register Button click
				driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
				Thread.sleep(4000);
				
				System.out.println("*****************User already exists*************");
				
				//Closing login/sign up popup
				Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\try text from homepage.png");
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
			
	}
	public void profile_PersonalInfo_NegativeCase() throws Exception {
		
				//Profile avatar clk
				driver.findElement(By.xpath("//div[@id='avatar']")).click();
				Thread.sleep(2000);
				
				//Profile tab click
				driver.findElement(By.xpath("(//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root center MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'])[1]")).click();
				Thread.sleep(2000);
				
				//Firstname field
				WebElement frstname = driver.findElement(By.xpath("//input[@id='firstName']"));
				
				//Lastname field
				WebElement lastname = driver.findElement(By.xpath("//input[@id='lastName']"));
				
				//Dob field
				WebElement dob_data = driver.findElement(By.id("date-picker-dialog"));
				dob_data.click();
				
				//Phone number field
				WebElement phonenum = driver.findElement(By.xpath("//input[@id='phone']"));
				phonenum.click();
				
		//Checking whether it is accepting empty data
				
			//Clearing data 
				frstname.click();
				Thread.sleep(1000);
				
				a.doubleClick(frstname).perform();
				Thread.sleep(1000);
		
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				a.doubleClick(lastname).perform();
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				dob_data.click();
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				phonenum.click();
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				//Save button click
				WebElement savebtn = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
				savebtn.click();
				Thread.sleep(2000);
				
			//Checking without entering valid data in all fields
				
				//Firstname field
				frstname.sendKeys(frstnme_negdata);
				Thread.sleep(2000);
				
				//Lastname field
				lastname.sendKeys(lstnme_negdata);
				Thread.sleep(2000);
				
				//DOB field
				Pattern ptrn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Date icon.png");
				scrn.wait(ptrn2,30);
				scrn.click();
				Thread.sleep(2000);
				
				//rightchevronbtn click
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Calendar popup - left chevron button.png");
				scrn.wait(ptrn3,30);
				scrn.click();
				Thread.sleep(2000);
				
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\calendar popup - date .png");
				scrn.wait(ptrn4,30);
				scrn.click();
				Thread.sleep(2000);
					
				Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\calendar popup - cancel button.png");
				scrn.wait(ptrn5,30);
				scrn.click();
				Thread.sleep(2000);
							
				//Entering invalid dob data
				
				dob_data.sendKeys(birthdate_negdata);
				Thread.sleep(2000);
				
				//Phone number field - Country flag click
				WebElement countrycodeclk = driver.findElement(By.xpath("//div[@class='flag us']"));
				countrycodeclk.click();
				Thread.sleep(2000);
				
				r.keyPress(KeyEvent.VK_I);
				r.keyRelease(KeyEvent.VK_I);
				Thread.sleep(2000);
				
				WebElement indiatext = driver.findElement(By.xpath("//span[text()='India']"));
				indiatext.click();
				Thread.sleep(2000);
				
				//Phone no field
				phonenum.sendKeys(phonenum_negdata);
				Thread.sleep(2000);
				
				//save btn clk
				savebtn.click();
				Thread.sleep(2000);
						
				frstname.click();
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
		
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				lastname.click();
				Thread.sleep(1000);
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				dob_data.click();
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				phonenum.click();
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);

	}	
	public void profile_PersonalInfo_PositiveCase() throws Exception {
		
				//Profile name click
				
				//driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle jss7 MuiAvatar-colorDefault']")).click();
				//Thread.sleep(2000);
				
				/*Pattern ptrn1=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\default profile pic avatar in header section.png");
				scrn.wait(ptrn1,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Profile tab click
				driver.findElement(By.xpath("(//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root center MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'])[1]")).click();
				Thread.sleep(2000);*/
				
				//Firstname field
				WebElement frstname = driver.findElement(By.xpath("//input[@id='firstName']"));
				frstname.sendKeys(edit_frstname);
				Thread.sleep(2000);
				
				//Lastname field
				WebElement lastname = driver.findElement(By.xpath("//input[@id='lastName']"));
				lastname.sendKeys(edit_lastname);
				Thread.sleep(2000);
				
				//Dob field
				WebElement dob_data = driver.findElement(By.id("date-picker-dialog"));
				
				//Entering birthdate straight in field box
				dob_data.sendKeys(edit_birthdate);
				Thread.sleep(2000);
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				//Dob calendar popup
				//driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[4]")).click();
				Pattern ptrn1=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Date icon.png");
				scrn.wait(ptrn1,30);
				scrn.click();
				Thread.sleep(2000);
				
				//year click
				driver.findElement(By.xpath("//h6[@class='MuiTypography-root MuiPickersToolbarText-toolbarTxt MuiTypography-subtitle1']")).click();
				Thread.sleep(2000);
			
				//year scrolling up
				WebElement yearscrollup = driver.findElement(By.xpath("//div[text()='2002']"));
				js.executeScript("arguments[0].scrollIntoView(false)",yearscrollup);
				Thread.sleep(2000);
				
				//year selection
				driver.findElement(By.xpath("//div[text()='1999']")).click();
				Thread.sleep(2000);
			
				//rightchevronbtn 
				//WebElement leftchevronbtnclk = driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[5]"));
				
				Pattern ptrn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Calendar popup - left chevron button.png");
					
				for (int i = 0; i < 2; i++) {
					
					//js.executeScript("arguments[0].click()", leftchevronbtnclk);
					
					scrn.wait(ptrn2,30);
					scrn.click();
					Thread.sleep(2000);
					
				}
				//leftchevronbtn
				//WebElement rightchevronbtnclk = driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[6]"));
				
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Calendar popup - right chevron button.png");
				
				for (int i = 0; i < 2; i++) {
					
					//js.executeScript("arguments[0].click()", rightchevronbtnclk);
					
					scrn.wait(ptrn3,30);
					scrn.click();
					Thread.sleep(2000);
					
				}
				
				//Date selection in calendar
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\calendar popup - date .png");
				scrn.wait(ptrn4,30);
				scrn.click();
	
				//OK button click
				driver.findElement(By.xpath("//span[text()='OK']")).click();
				Thread.sleep(2000);
	
				//Phone number field
				WebElement phonenum = driver.findElement(By.xpath("//input[@id='phone']"));
				
				//Entering Phone no in the field
				phonenum.sendKeys(edit_indphonenum);
				Thread.sleep(2000);
					
				//Save button click
				WebElement savebtn = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
				
				WebElement personalinfotext = driver.findElement(By.xpath("//h5[text()='Personal Information']"));
				js.executeScript("arguments[0].scrollIntoView(true)",personalinfotext);
				savebtn.click();
				Thread.sleep(4000);
				
				System.out.println("Profile edited successfully");	
		
	}
	
	public void reeditprofile_PersonalInfo() throws Exception {
		
			//For displaying changes
				
				WebElement defaultimgavatar = driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle']"));
				js.executeScript("arguments[0].scrollIntoView(false)",defaultimgavatar);
				Thread.sleep(1000);
			
				//Homepage button click
				driver.findElement(By.xpath("//img[@class=' cursor-pointer']")).click();
				Thread.sleep(2000);
				
				//Profile avatar clk
				driver.findElement(By.xpath("//div[@id='avatar']")).click();
				Thread.sleep(2000);
				
				//Profile tab click
				driver.findElement(By.xpath("(//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root center MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'])[1]")).click();
				Thread.sleep(5000);
			
				//Firstname field
				Thread.sleep(2000);
				WebElement frstname = driver.findElement(By.xpath("//input[@id='firstName']"));
				frstname.click();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
			
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				frstname.sendKeys(edit_frstname2);
				Thread.sleep(1000);
				
				//Lastname field
				WebElement lastname = driver.findElement(By.xpath("//input[@id='lastName']"));
				lastname.click();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
				
				lastname.sendKeys(edit_lastname2);
				Thread.sleep(1000);
			
				//Phone number field
				WebElement phonenum = driver.findElement(By.xpath("//input[@id='phone']"));
				phonenum.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				WebElement countrycodeclk = driver.findElement(By.xpath("//div[@class='flag in']"));
				countrycodeclk.click();
				Thread.sleep(1000);
					
				WebElement usatext = driver.findElement(By.xpath("//span[text()='+1251']"));
				js.executeScript("arguments[0].scrollIntoView(false)",usatext );
				Thread.sleep(1000);
				
				WebElement usatext2 = driver.findElement(By.xpath("(//span[text()='United States'])[1]"));
				usatext2.click();
				Thread.sleep(1000);
				
				//Entering Phone no in the field
				phonenum.sendKeys(edit_usaphonenum);
				Thread.sleep(2000);
				
				//Save button click
			
				//WebElement personalinfotext = driver.findElement(By.xpath("//h5[text()='Personal Information']"));
				//js.executeScript("arguments[0].scrollIntoView(true)",personalinfotext);
				WebElement savebtn = driver.findElement(By.xpath("(//button[@type='submit'])[1]"));
				savebtn.click();
				Thread.sleep(1000);
				
				System.out.println("********Profile reedited successfully*********");
			
	}
	
	public void addingNeditingProfilePicture() throws Exception {
		
				//Profile name click
				driver.findElement(By.xpath("//div[@id='avatar']")).click();
				Thread.sleep(2000);
				
				//Profile tab option click
				driver.findElement(By.xpath("(//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root center MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'])[1]")).click();
				Thread.sleep(2000);
				
				Pattern ptrn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\profile picture avatar default.png");
				scrn.wait(ptrn,20);
				scrn.click();
				Thread.sleep(2000);
				
				//Using AutoIT Tool
				Runtime.getRuntime().exec("D:\\Documents\\Documents\\Lotto - profile pic upload(spidey pic).exe");
				Thread.sleep(5000);
				System.out.println("****************Profile picture added successfully*************");
				
				//Clicking on added Profile Picture
				Pattern ptrn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\spidey pic screenshot.png");
				scrn.wait(ptrn2,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Editing profile picture
				Runtime.getRuntime().exec("D:\\Documents\\Documents\\Lotto - profile pic upload(john wick).exe");
				Thread.sleep(5000);
				System.out.println("****************Profile picture edited successfully*************");
				
				//Adding default avatar pic again
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\john wick pic screenshot.png");
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);
				
				Runtime.getRuntime().exec("D:\\Documents\\Documents\\Lotto - profile pic upload(default avatar).exe");
				Thread.sleep(2000);
		
	}
	public void changePasswordModule_NegativeCase() throws Exception {
		
				//Profile avatar clk
				driver.findElement(By.xpath("//div[@id='avatar']")).click();
				Thread.sleep(2000);
			
				//Profile tab click
				driver.findElement(By.xpath("(//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root center MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'])[1]")).click();
				Thread.sleep(2000);
			
				//Change password tab click
				WebElement changepasswrd = driver.findElement(By.xpath("//p[text()='Change Password']"));
				changepasswrd.click();
				Thread.sleep(2000);
				
			//Checking whether it is accepting empty data
				WebElement updatepasswrdbtn = driver.findElement(By.xpath("(//button[text()='Update New Password'])[2]"));
				js.executeScript("arguments[0].click()", updatepasswrdbtn);
				Thread.sleep(2000);
			
			//Checking whether it is accepting without minimum requirements
				
				//Current password field
				WebElement currentpass = driver.findElement(By.xpath("(//input[@placeholder='Enter  Password'])[1]"));
				currentpass.sendKeys(data_currentpass1);
				Thread.sleep(2000);
				
				//Current pass eye icon click
				driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray'])[1]")).click();
				Thread.sleep(2000);
				
				//New password field
				WebElement newpass = driver.findElement(By.xpath("(//input[@placeholder='Enter  Password'])[2]"));
				newpass.sendKeys(data_newpass1);
				Thread.sleep(2000);
				
				//New pass eye icon click
				driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray'])[1]")).click();
				Thread.sleep(2000);
	
				//Confirm password field
				WebElement cnfrmpass = driver.findElement(By.xpath("(//input[@placeholder='Enter  Password'])[3]"));
				cnfrmpass.sendKeys(data_cnfrmpass1);
				Thread.sleep(2000);
				
				//Confirm pass eye icon click
				driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray'])[1]")).click();
				Thread.sleep(2000);
			
				//Clearing data
				currentpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
					
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				newpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);		
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				cnfrmpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
						
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
			//Checking whether it is accepting same data in both current pass & new pass field
				
				currentpass.sendKeys(data_currentpass2);
				Thread.sleep(2000);
				
				newpass.sendKeys(data_newpass2);
				Thread.sleep(2000);
				
				cnfrmpass.sendKeys(data_cnfrmpass2);
				Thread.sleep(2000);
				
				//Clearing data
				currentpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
						
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				newpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
						
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				cnfrmpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);		
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
		//Checking whether it is accepting different data in current password field
				
				currentpass.sendKeys(data_currentpass3);
				Thread.sleep(2000);
				
				newpass.sendKeys(data_newpass3);
				Thread.sleep(2000);
				
				cnfrmpass.sendKeys(data_cnfrmpass3);
				Thread.sleep(4000);
				
				//Update password button click
				WebElement updatebtn = driver.findElement(By.xpath("(//button[text()='Update New Password'])[2]"));
				js.executeScript("arguments[0].click()", updatebtn);
				Thread.sleep(4000);
				
				//Clearing data
				currentpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				newpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				cnfrmpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
			//Checking whether it is accepting different data in new and confirm pass field
				
				currentpass.sendKeys(data_currentpass4);
				Thread.sleep(2000);
				
				newpass.sendKeys(data_newpass4);
				Thread.sleep(2000);
				
				cnfrmpass.sendKeys(data_cnfrmpass4);
				Thread.sleep(4000);
				
				//Clearing data
				currentpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				newpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);
				
				cnfrmpass.click();
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
			
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(1000);	
	}
	
	public void changePasswordModule_PositiveCase() throws Exception {
	
				//Profile name click
				
				//driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle jss7 MuiAvatar-colorDefault']")).click();
				//Thread.sleep(2000);
			
			//For displaying changes
				
				WebElement defaultimgavatar = driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle']"));
				js.executeScript("arguments[0].scrollIntoView(false)",defaultimgavatar);
				Thread.sleep(1000);
			
				//Homepage button click
				driver.findElement(By.xpath("//img[@class=' cursor-pointer']")).click();
				Thread.sleep(2000);
				
				//Profile avatar clk
				driver.findElement(By.xpath("//div[@id='avatar']")).click();
				Thread.sleep(2000);
				
				//Profile tab click
				driver.findElement(By.xpath("(//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root center MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'])[1]")).click();
				Thread.sleep(5000);
				
				//Change password tab click
				WebElement changepasswrd = driver.findElement(By.xpath("//p[text()='Change Password']"));
				changepasswrd.click();
				Thread.sleep(2000);
				
				//Current password field
				driver.findElement(By.xpath("(//input[@placeholder='Enter  Password'])[1]")).sendKeys(data_currentpass5);
				Thread.sleep(2000);
				
				//Current pass eye icon click
				driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray'])[1]")).click();
				Thread.sleep(2000);
				
				//New password field
				driver.findElement(By.xpath("(//input[@placeholder='Enter  Password'])[2]")).sendKeys(data_newpass5);
				Thread.sleep(2000);
				
				//New pass eye icon click
				driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray'])[1]")).click();
				Thread.sleep(2000);
	
				//Confirm password field
				driver.findElement(By.xpath("(//input[@placeholder='Enter  Password'])[3]")).sendKeys(data_cnfrmpass5);
				Thread.sleep(2000);
				
				//Confirm pass eye icon click
				driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray'])[1]")).click();
				Thread.sleep(2000);
			
				//Update password button click
				WebElement updatepasswrdbtn = driver.findElement(By.xpath("(//button[text()='Update New Password'])[2]"));
				js.executeScript("arguments[0].click()", updatepasswrdbtn);
				Thread.sleep(2000);
				
				System.out.println("***************Password Changed Successfully*************");
	}
	
	public void restoringChangedPasswordModule() throws Exception {
		
				//Profile name click
				
				//driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle jss7 MuiAvatar-colorDefault']")).click();
				//Thread.sleep(2000);
				
				//Profile avatar clk
				driver.findElement(By.xpath("//div[@id='avatar']")).click();
				Thread.sleep(2000);
				
				//Profile tab click
				driver.findElement(By.xpath("(//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root center MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'])[1]")).click();
				Thread.sleep(2000);
				
				//Change password tab click
				WebElement changepasswrd = driver.findElement(By.xpath("//p[text()='Change Password']"));
				changepasswrd.click();
				Thread.sleep(2000);
		
				//Current password field
				driver.findElement(By.xpath("(//input[@placeholder='Enter  Password'])[1]")).sendKeys(data_currentpass6);
				Thread.sleep(2000);
				
				//Current pass eye icon click
				driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray'])[1]")).click();
				Thread.sleep(2000);
				
				//New password field
				driver.findElement(By.xpath("(//input[@placeholder='Enter  Password'])[2]")).sendKeys(data_newpass6);
				Thread.sleep(2000);
				
				//New pass eye icon click
				driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray'])[1]")).click();
				Thread.sleep(2000);
		
				//Confirm password field
				driver.findElement(By.xpath("(//input[@placeholder='Enter  Password'])[3]")).sendKeys(data_cnfrmpass6);
				Thread.sleep(2000);
				
				//Current pass eye icon click
				driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root tex-color-gray'])[1]")).click();
				Thread.sleep(2000);
			
				//Update password button click
				WebElement updatepasswrdbtn = driver.findElement(By.xpath("(//button[text()='Update New Password'])[2]"));
				js.executeScript("arguments[0].click()", updatepasswrdbtn);
				Thread.sleep(2000);
				
				System.out.println("*********Password resetted to old one*****************");
		
	}
	
	public void GoogleAuthTabModule() throws Exception {
		
				//Profile avatar clk
				driver.findElement(By.xpath("//div[@id='avatar']")).click();
				Thread.sleep(2000);
				
				//Profile tab click
				driver.findElement(By.xpath("(//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root center MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'])[1]")).click();
				Thread.sleep(2000);
				
				//Two factor auth tab click
				driver.findElement(By.xpath("//p[text()='Two-Factor Authentication']")).click();
				Thread.sleep(2000);
				
				//Google authenticator radio btn clk
				driver.findElement(By.xpath("(//input[@name='quiz'])[2]")).click();
				Thread.sleep(2000);

	}
	public void googleAuth_NegCaseModule() throws Exception {
		
			//Authenticating with valid QR and code
			
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Verify']")));
				WebElement verifybtnclk = driver.findElement(By.xpath("//p[text()='Verify']"));
				verifybtnclk.click();
				Thread.sleep(2000);
				
				//String contains="Verified Successfully";
				
				//String pageSource = driver.getPageSource();
				//System.out.println(pageSource);
				
				if (driver.getPageSource().contains("Button to launch messaging window")) {
					
					System.out.println("*************Verified Successfully***********");
					
				} else {
					
					System.out.println("**************Verification Failed************");
				}
		
				//Clicking none option btn
				driver.findElement(By.xpath("(//input[@name='quiz'])[3]")).click();
				Thread.sleep(2000);
				
				//Submit btn clk
				driver.findElement(By.xpath("//span[text()='Submit']")).click();
				Thread.sleep(2000);
				
			//Authenticating with Old verification code
				
					//Google authenticator radio btn clk
					driver.findElement(By.xpath("(//input[@name='quiz'])[2]")).click();
					Thread.sleep(2000);
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));	
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Verify']")));
				WebElement verifybtnclk2 = driver.findElement(By.xpath("//p[text()='Verify']"));
				verifybtnclk2.click();
				Thread.sleep(2000);
				
				//Displaying Invalid error message 
						//WebElement invalidmsg = driver.findElement(By.xpath("//p[text()='Invalid  Code.']"));
						//boolean msgdisplayed = invalidmsg.isDisplayed();
						//System.out.println(msgdisplayed);
				
				
				if (driver.getPageSource().contains("Button to launch messaging window")) {
					
					System.out.println("*************Old verification Code is not accepted***********");
					
				} else {
					
					System.out.println("**************Old verification code has been accepted*********");
				}
				
				
			//Authenticating with Old QR code
				
				//Google authenticator radio btn clk
				//driver.findElement(By.xpath("(//input[@name='quiz'])[2]")).click();
				//Thread.sleep(2000);
				
				WebElement lastfieldcode = driver.findElement(By.xpath("(//input[@class='otpInput   col-sm-2 col-xs-2 col-lg-2'])[6]"));
				lastfieldcode.click();
				Thread.sleep(2000);
				
				for (int i = 1; i < 12; i++) {
					
					r.keyPress(KeyEvent.VK_DELETE);
					Thread.sleep(1000);
				}
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Verify']")));
				WebElement verifybtnclk3 = driver.findElement(By.xpath("//p[text()='Verify']"));
				verifybtnclk3.click();
				Thread.sleep(2000);
			
				//Displaying Invalid error message 
					
				
					if (driver.getPageSource().contains("Button to launch messaging window")) {
						
						System.out.println("*************Old QR Code is not accepted***********");
						
					} else {
						
						System.out.println("**************Old QR code has been accepted*********");
					}
					
	}
	public void googleAuth_PositiveCaseModule() throws InterruptedException {
		
			//Authenticating with valid QR and code
			
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Verify']")));
				WebElement verifybtnclk = driver.findElement(By.xpath("//p[text()='Verify']"));
				verifybtnclk.click();
				Thread.sleep(2000);
				
				if (driver.getPageSource().contains("Button to launch messaging window")) {
					
					System.out.println("*************Valid Code Verified Successfully***********");
					
				} else {
					
					System.out.println("************Valid Code Verification Failed***********");
				}
				
				//Switching tabs
				driver.findElement(By.xpath("//p[text()='Change Password']")).click();
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//p[text()='Two-Factor Authentication']")).click();
				Thread.sleep(4000);
			
	}
	
	public void none_OptionAuthentication() throws Exception {
			
				//Profile avatar click
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@id='avatar']")).click();
				Thread.sleep(2000);
				
				//Profile tab click
				driver.findElement(By.xpath("(//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root center MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'])[1]")).click();
				Thread.sleep(2000);
						
				//Two factor auth tab click
				driver.findElement(By.xpath("//p[text()='Two-Factor Authentication']")).click();
				Thread.sleep(4000);
	
	}
	
	public void Forgot_PasswordModule() throws Exception {
		
				//login/register btn clk
				driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
				Thread.sleep(2000);
				
				//Forgot Password btn clk
				driver.findElement(By.xpath("//span[text()='Forgot Password ?']")).click();
				Thread.sleep(2000);
			
			//Checking whether email field has validation
				
				//Reset button clk
				WebElement resetbtnclk = driver.findElement(By.xpath("//button[text()='Send Reset Link']"));
				resetbtnclk.click();
				Thread.sleep(2000);
				
				System.out.println("***********Email is required***********");
			
			//Checking whether it is accepting random email
				
				//Email field
				WebElement emailfield = driver.findElement(By.xpath("//input[@placeholder='Enter Email']"));
				emailfield.sendKeys(fp_email);
				Thread.sleep(3000);
				
				System.out.println("***********Enter valid email***********");
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
				
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
			
			//Checking whether it is accepting unregistered email
				
				//Email field
				emailfield.sendKeys(fp_email2);
				Thread.sleep(2000);
				
				//Reset btn clk
				resetbtnclk.click();
				Thread.sleep(2000);
				
				System.out.println("**********No user found with this email**********");
				
				emailfield.click();
				Thread.sleep(1000);
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_A);
				
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_A);
				
				r.keyPress(KeyEvent.VK_DELETE);
				r.keyRelease(KeyEvent.VK_DELETE);
				Thread.sleep(2000);
			
			//Entering registered email
				
				emailfield.sendKeys(fp_email3);
				Thread.sleep(2000);
				
				//Reset Password btn clk
				
				resetbtnclk.click();
				Thread.sleep(4000);
				
				System.out.println("************Password reset otp sent*************");
				
			//Clicking try another email address button
				
				driver.findElement(By.xpath("//a[text()='try another email address']")).click();
				Thread.sleep(2000);
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				WebElement emailfield2 = driver.findElement(By.xpath("//input[@placeholder='Enter Email']"));
				emailfield2.sendKeys(fp_email3);
				Thread.sleep(2000);
				
				//Reset Password btn clk
				WebElement resetbtnclk2 = driver.findElement(By.xpath("//button[text()='Send Reset Link']"));
				resetbtnclk2.click();
				Thread.sleep(2000);
				
				System.out.println("*********Password OTP resent successfully************");
				
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_T);
				
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_T);
					
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));	
			
	}
	public void instant_WithoutLoggedInUser() throws Exception {
		
				//Instant jackpot button click
				
				driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
				Thread.sleep(2000);
					
				//Quick pick btn clk
				Thread.sleep(2000);
				Pattern ptrn1=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(ptrn1,50);
				scrn.click();
				Thread.sleep(2000);
				
				//System.out.println("********Quick Pick button is not functioning as expected********");
				
				//Normal ball Numbers pick 
					//Number - 7 
				Thread.sleep(2000);
				Pattern ptrn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 7 number.png");
				scrn.wait(ptrn2,50);
				scrn.click();
				Thread.sleep(2000);
						
					//Number - 9
				Thread.sleep(2000);
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 9 number.png");
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Number - 10
				Thread.sleep(2000);
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 10 number.png");
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Number - 12
				Thread.sleep(2000);
				Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 12 number.png");
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Number -15
				Pattern ptrn6=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 15 number.png");
				scrn.wait(ptrn6,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Golden ball Numbers pick 
					//Number - 1 
				Thread.sleep(2000);
				Pattern ptrn7=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 1 number.png");
				scrn.wait(ptrn7,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Reset pick btn clk
				Pattern ptrn8=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled reset pick button.png");
				scrn.wait(ptrn8,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Selecting Chip value
					//Chip - 2
				Pattern ptrn9=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\instant game - disabled 2 chip value.png");
				scrn.wait(ptrn9,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Chip - 3
				Pattern ptrn10=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\instant game - disabled 3 chip value.png");
				scrn.wait(ptrn10,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Chip - 4
				Pattern ptrn11=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\instant game - disabled 4 chip value.png");
				scrn.wait(ptrn11,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Chip - 5
				Pattern ptrn12=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\instant game - disabled 5 chip value.png");
				scrn.wait(ptrn12,50);
				scrn.click();
				Thread.sleep(2000);
				
				System.out.println("***********User cannot able to select or play the game as expected***********");
				
				//Prize Chart button click
				Pattern ptrn14=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Instant game - prize chart text.png");
				scrn.wait(ptrn14,50);
				scrn.click();
				Thread.sleep(2000);
		
				//Scrolling down in prize chart popup
				WebElement scrolldown = driver.findElement(By.xpath("//div[text()='Ticket Value']"));
				js.executeScript("arguments[0].scrollIntoView(false)", scrolldown);
				Thread.sleep(2000);
				
				//Scrolling up in prize chart popup
				WebElement scrollup = driver.findElement(By.xpath("//h5[text()='Your Ticket & Prize Chart']"));
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);
				
				//Closing the popup
				driver.findElement(By.xpath("//img[@class='cursor-pointer']")).click();
				Thread.sleep(4000);
				
				//Clicking back button
				driver.findElement(By.xpath("//h5[text()='Back']")).click();
				Thread.sleep(2000);	
	}
	
	public void instant_WithLoggedInUser_NegativeCaseModule() throws Exception {
		
				String jsStyle = "'3px solid red'";
				String jsStyle2="'none'";
				
				//Instant jackpot button click
				driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
				Thread.sleep(2000);
				
				//Bonus - No option click
				Pattern bonusNoOption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
				scrn.wait(bonusNoOption,50);
				scrn.click();
				Thread.sleep(4000);
			
			///////////Normal Ball Numbers Section///////////	
			
			//Manually - Checking whether user can select more than 5 ball numbers 
				
				//Number - 1 
				WebElement num1clk = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
				num1clk.click();
				Thread.sleep(2000);
				
				//Number - 2
				WebElement num2clk = driver.findElement(By.xpath("(//div[text()='2'])[1]"));
				num2clk.click();
				Thread.sleep(2000);
				
				//Number - 3
				WebElement num3clk = driver.findElement(By.xpath("(//div[text()='3'])[1]"));
				num3clk.click();
				Thread.sleep(2000);
				
				//Number - 4
				WebElement num4clk = driver.findElement(By.xpath("//div[text()='4']"));
				num4clk.click();
				Thread.sleep(2000);
				
				//Number - 5
				WebElement num5clk = driver.findElement(By.xpath("//div[text()='5']"));
				num5clk.click();
				Thread.sleep(2000);
				
				//Number - 7 
				WebElement num7clk = driver.findElement(By.xpath("//div[text()='7']"));
				num7clk.click();
				Thread.sleep(2000);
			
				js.executeScript("arguments[0].style.border=" + jsStyle, num7clk);
				Thread.sleep(3000);
				 
				js.executeScript("arguments[0].style.border=" + jsStyle2, num7clk);
				Thread.sleep(2000);
				
				//Manually - Trying to select chip value only by selecting 5 numbers(Chip value - 2)
				
				//1st Chip 
				WebElement firstchip = driver.findElement(By.xpath("//p[@class='chip-images-numbers-1']"));
				firstchip.click();
				Thread.sleep(3000);
				
				//Manually - Trying to play game only by selecting 5 numbers(Play now btn clk)
				
				//Play now btn clk
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled play now button.png");
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);
		
	//Checking whether reset pick button is clickable without selecting any balls
		
				//Number - 1 
				js.executeScript("arguments[0].click()", num1clk);
				Thread.sleep(2000);
				
				//Number - 2
				js.executeScript("arguments[0].click()", num2clk);
				Thread.sleep(2000);
				
				//Number - 3
				js.executeScript("arguments[0].click()", num3clk);
				Thread.sleep(2000);
				
				//Number - 4
				js.executeScript("arguments[0].click()", num4clk);
				Thread.sleep(2000);
				
				//Number - 5
				js.executeScript("arguments[0].click()", num5clk);
				Thread.sleep(2000);
		
				//Trying to click reset pick button
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled reset pick button.png");
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(4000);
				
		//Quick Pick button - Checking whether user can select more than 5 ball numbers 		
		
				//Quick pick btn clk
				Thread.sleep(2000);
				Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
		
				//Trying to click more than 5 number (Number - 7) 
				Pattern ptrn1=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 7 number.png");
				scrn.wait(ptrn1,50);
				scrn.click();
				Thread.sleep(3000);
				
		//Trying to play game without selecting chip value
				
				//Play now btn clk
				
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(3000);
				
				//Reset pick button click
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(2000);
				
	///////////Golden Ball Numbers Section///////////	
						
		//Manually - trying to select more than 1 golden ball number
		
				//Number - 1 
				WebElement goldennum1clk = driver.findElement(By.xpath("(//div[text()='1'])[2]"));
				goldennum1clk.click();
				Thread.sleep(2000);
			
				//Number - 2 
				WebElement goldennum2clk = driver.findElement(By.xpath("(//div[text()='2'])[2]"));
				
				js.executeScript("arguments[0].click()", goldennum2clk);
				Thread.sleep(3000);
				js.executeScript("arguments[0].style.border=" + jsStyle, goldennum2clk);
				Thread.sleep(3000);
				 
				js.executeScript("arguments[0].style.border=" + jsStyle2, goldennum2clk);
				Thread.sleep(2000);	
		
			//Manually - Trying to select chip value only by selecting 1 Golden numbers(Chip value - 2)
				
				//1st Chip 
				WebElement firstchip2 = driver.findElement(By.xpath("//p[@class='chip-images-numbers-1']"));
				firstchip2.click();
				Thread.sleep(3000);
						
			//Manually - Trying to play game only by selecting 1 Golden numbers(Play now btn clk)
				
				//Play now btn clk
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);
			
		//Manually - trying to click disabled reset pick button
				
				//Number - 1 
				goldennum1clk.click();
				Thread.sleep(2000);
				
				//Reset pick button clk
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
		
		//Quick pick - tryting to select more than 1 golden number
				
				//Quick pick button clk
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(3000);
					
				//Number - 2 
				js.executeScript("arguments[0].click()", goldennum2clk);
				Thread.sleep(3000);
				js.executeScript("arguments[0].style.border=" + jsStyle, goldennum2clk);
				Thread.sleep(3000);
				 
				js.executeScript("arguments[0].style.border=" + jsStyle2, goldennum2clk);
				Thread.sleep(2000);
			    
				//Reset pick button click
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
				
		///////////Chip value Section///////////
				
			//Trying to select all disabled chip value
				
				//1st Chip 
				WebElement firstchip3 = driver.findElement(By.xpath("//p[@class='chip-images-numbers-1']"));
				firstchip3.click();
				Thread.sleep(3000);
				
				js.executeScript("arguments[0].style.border=" + jsStyle,firstchip3 );
				Thread.sleep(3000);
				 
				js.executeScript("arguments[0].style.border=" + jsStyle2, firstchip3);
				Thread.sleep(2000);
				
				//2nd Chip 
				WebElement secondchip = driver.findElement(By.xpath("(//p[@class='chip-images-numbers'])[1]"));
				secondchip.click();
				Thread.sleep(3000);
				
				js.executeScript("arguments[0].style.border=" + jsStyle,secondchip );
				Thread.sleep(3000);
				 
				js.executeScript("arguments[0].style.border=" + jsStyle2, secondchip);
				Thread.sleep(2000);
				
				//3rd Chip 
				WebElement thirdchip = driver.findElement(By.xpath("(//p[@class='chip-images-numbers'])[2]"));
				thirdchip.click();
				Thread.sleep(3000);
				
				js.executeScript("arguments[0].style.border=" + jsStyle,thirdchip );
				Thread.sleep(3000);
				 
				js.executeScript("arguments[0].style.border=" + jsStyle2, thirdchip);
				Thread.sleep(2000);
					
				//4th Chip 
				WebElement fourthchip = driver.findElement(By.xpath("(//p[@class='chip-images-numbers'])[3]"));
				fourthchip.click();
				Thread.sleep(3000);
				
				js.executeScript("arguments[0].style.border=" + jsStyle,fourthchip );
				Thread.sleep(3000);
				 
				js.executeScript("arguments[0].style.border=" + jsStyle2, fourthchip);
				Thread.sleep(2000);
		
		//////Play now button section/////////////
				
			//Trying to play game without selecting any balls or chips
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(2000);		
				
			//Trying to play game without any sufficient funds
				
				//Quick pick button clk
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
				
				//1st Chip 
				WebElement firstchip4 = driver.findElement(By.xpath("//p[@class='chip-images-numbers-1']"));
				firstchip4.click();
				Thread.sleep(3000);
				
				//Play now btn clk
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);	
				
				System.out.println("***********Insufficient Funds**********");
				
				//Refreshing the page
				driver.navigate().refresh();
				Thread.sleep(2000);
				
			//Trying to play game without any sufficient bonus funds
				
				//Bonus - Yes option click
				Pattern bonusYesOption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\bonus popup - yes option.png");
				scrn.wait(bonusYesOption,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Quick pick button clk
				Pattern quickpick=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(quickpick,50);
				scrn.click();
				Thread.sleep(2000);
				
				//1st Chip 
				WebElement firstchip5 = driver.findElement(By.xpath("//p[@class='chip-images-numbers-1']"));
				firstchip5.click();
				Thread.sleep(3000);
				
				//Play now btn clk
				WebElement playnowbtn = driver.findElement(By.xpath("//button[text()='Play Now']"));
				playnowbtn.click();
				Thread.sleep(2000);
				
				System.out.println("***********Insufficient Bonus Funds**********");
					
	}
	
	public void instant_WithLoggedInUser_PositiveCaseModule() throws Exception {
		
			//Instant jackpot button click
		
				driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
				Thread.sleep(2000);
				
				//Bonus - No option click
				Pattern bonusNoOption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
				scrn.wait(bonusNoOption,50);
				scrn.click();
				Thread.sleep(4000);
		
		//Manually - Selecting all normal ball numbers
				
				//Number - 1 
				WebElement num1clk = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
				num1clk.click();
				Thread.sleep(2000);
				num1clk.click();
				Thread.sleep(2000);
				
				//Number - 2
				WebElement num2clk = driver.findElement(By.xpath("(//div[text()='2'])[1]"));
				num2clk.click();
				Thread.sleep(2000);
				num2clk.click();
				Thread.sleep(2000);
				
				//Number - 3
				WebElement num3clk = driver.findElement(By.xpath("(//div[text()='3'])[1]"));
				num3clk.click();
				Thread.sleep(2000);
				num3clk.click();
				Thread.sleep(2000);
				
				//Number - 4
				WebElement num4clk = driver.findElement(By.xpath("(//div[text()='4'])[1]"));
				num4clk.click();
				Thread.sleep(2000);
				num4clk.click();
				Thread.sleep(2000);
			
				//Number - 5
				WebElement num5clk = driver.findElement(By.xpath("(//div[text()='5'])[1]"));
				num5clk.click();
				Thread.sleep(2000);
				num5clk.click();
				Thread.sleep(2000);
				
				//Number - 6
				WebElement num6clk = driver.findElement(By.xpath("(//div[text()='6'])[1]"));
				num6clk.click();
				Thread.sleep(2000);
				num6clk.click();
				Thread.sleep(2000);
				
				//Number - 7
				WebElement num7clk = driver.findElement(By.xpath("(//div[text()='7'])[1]"));
				num7clk.click();
				Thread.sleep(2000);
				num7clk.click();
				Thread.sleep(2000);
				
				//Number - 8
				WebElement num8clk = driver.findElement(By.xpath("(//div[text()='8'])[1]"));
				num8clk.click();
				Thread.sleep(2000);
				num8clk.click();
				Thread.sleep(2000);
				
				//Number - 9
				WebElement num9clk = driver.findElement(By.xpath("(//div[text()='9'])[1]"));
				num9clk.click();
				Thread.sleep(2000);
				num9clk.click();
				Thread.sleep(2000);
			
				//Number - 10
				WebElement num10clk = driver.findElement(By.xpath("(//div[text()='10'])[1]"));
				num10clk.click();
				Thread.sleep(2000);
				num10clk.click();
				Thread.sleep(2000);

				//Number - 11
				WebElement num11clk = driver.findElement(By.xpath("(//div[text()='11'])[1]"));
				num11clk.click();
				Thread.sleep(2000);
				num11clk.click();
				Thread.sleep(2000);
				
				//Number - 12
				WebElement num12clk = driver.findElement(By.xpath("(//div[text()='12'])[1]"));
				num12clk.click();
				Thread.sleep(2000);
				num12clk.click();
				Thread.sleep(2000);
				
				//Number - 13
				WebElement num13clk = driver.findElement(By.xpath("(//div[text()='13'])[1]"));
				num13clk.click();
				Thread.sleep(2000);
				num13clk.click();
				Thread.sleep(2000);
				
				//Number - 14
				WebElement num14clk = driver.findElement(By.xpath("(//div[text()='14'])[1]"));
				num14clk.click();
				Thread.sleep(2000);
				num14clk.click();
				Thread.sleep(2000);
				
				//Number - 15
				WebElement num15clk = driver.findElement(By.xpath("(//div[text()='15'])[1]"));
				num15clk.click();
				Thread.sleep(2000);
				num15clk.click();
				Thread.sleep(2000);
				
				System.out.println("**********All normal balls are selectable*********");
				
		//Quick pick - clicking multiple times
				
				//Quick pick btn clk
				
				for (int i = 0; i <=2; i++) {
					
					Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
					scrn.wait(ptrn5,50);
					scrn.click();
					Thread.sleep(2000);		
				}
				System.out.println("*******Quick pick clicked different normal ball numbers*******");
				
				//reset pick clk
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled reset pick button.png");
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(4000);
						
			//Manually - Selecting all golden ball numbers
				
				//Number - 1 
				WebElement goldennum1clk = driver.findElement(By.xpath("(//div[text()='1'])[2]"));
				goldennum1clk.click();
				Thread.sleep(2000);
				goldennum1clk.click();
				Thread.sleep(2000);
			
				//Number - 2 
				WebElement goldennum2clk = driver.findElement(By.xpath("(//div[text()='2'])[2]"));
				goldennum2clk.click();
				Thread.sleep(2000);
				goldennum2clk.click();
				Thread.sleep(2000);
				
				//Number - 3 
				WebElement goldennum3clk = driver.findElement(By.xpath("(//div[text()='3'])[2]"));
				goldennum3clk.click();
				Thread.sleep(2000);
				goldennum3clk.click();
				Thread.sleep(2000); 
				
		//Quick pick - clicking multiple times
				
				//Quick pick btn clk
				
				for (int i = 0; i <=2; i++) {
					
					Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
					scrn.wait(ptrn5,50);
					scrn.click();
					Thread.sleep(2000);
		
				}
				System.out.println("*******Quick pick clicked different golden ball numbers*******");
				
				//reset pick clk
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(4000);	
				
		//Selecting all the chip values
				
				//Quick pick button clk
				Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
				
				Pattern ptrn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled 2 chip value.png");

				Pattern ptrn6=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled 3 chip value.png");

				Pattern ptrn7=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled 4 chip value.png");

				Pattern ptrn8=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled 5 chip value.png");
				
				//1st Chip 
				WebElement firstchip1 = driver.findElement(By.xpath("//p[@class='chip-images-numbers-1']"));
				firstchip1.click();
				Thread.sleep(3000);
				
				//2nd Chip 
				WebElement secondchip = driver.findElement(By.xpath("(//p[@class='chip-images-numbers'])[1]"));
				secondchip.click();
				Thread.sleep(3000);
				
				//3rd Chip 
				WebElement thirdchip = driver.findElement(By.xpath("(//p[@class='chip-images-numbers'])[2]"));
				thirdchip.click();
				Thread.sleep(3000);
				
				//4th Chip 
				WebElement fourthchip = driver.findElement(By.xpath("(//p[@class='chip-images-numbers'])[3]"));
				fourthchip.click();
				Thread.sleep(3000);
				
				System.out.println("*******All Chips value are selectable********");
				
				//Reset pick button click
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
				
		//Quick play - Playing game 
				
				//Quick pick button clk	
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
				
				//1st Chip 
				WebElement firstchip2 = driver.findElement(By.xpath("//p[@class='chip-images-numbers-1']"));
				firstchip2.click();
				Thread.sleep(3000);
				
				//Play now btn clk
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled play now button.png");
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(20000);
			
				//Play again button
				
				WebElement playagainbtn = driver.findElement(By.xpath("(//button[text()='Play again'])[1]"));
				playagainbtn.click();
				Thread.sleep(2000);
				
	//Manually - Playing game		
			
				//Bonus - No option click
				Pattern bonusNoOption2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
				scrn.wait(bonusNoOption2,50);
				scrn.click();
				Thread.sleep(4000);
						
				//Normal ball Numbers pick 
				//Number - 7 
				Thread.sleep(2000);
				Pattern ptrnnum7=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 7 number.png");
				scrn.wait(ptrnnum7,50);
				scrn.click();
				Thread.sleep(2000);
					
				//Number - 9
				Thread.sleep(2000);
				Pattern ptrnnum9=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 9 number.png");
				scrn.wait(ptrnnum9,50);
				scrn.click();
				Thread.sleep(2000);
			
				//Number - 10
				Thread.sleep(2000);
				Pattern ptrnnum10=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 10 number.png");
				scrn.wait(ptrnnum10,50);
				scrn.click();
				Thread.sleep(2000);
			
				//Number - 12
				Thread.sleep(2000);
				Pattern ptrnnum12=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 12 number.png");
				scrn.wait(ptrnnum12,50);
				scrn.click();
				Thread.sleep(2000);
			
				//Number -15
				Pattern ptrnnum15=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 15 number.png");
				scrn.wait(ptrnnum15,50);
				scrn.click();
				Thread.sleep(2000);
			
			//Golden ball Numbers pick 
				
				//Number - 1 
				Thread.sleep(2000);
				Pattern ptrngoldennum1=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 1 number.png");
				scrn.wait(ptrngoldennum1,50);
				scrn.click();
				Thread.sleep(2000);
			
				//4th Chip 
				WebElement fourthchip2 = driver.findElement(By.xpath("(//p[@class='chip-images-numbers'])[3]"));
				fourthchip2.click();
				Thread.sleep(3000);
			
				//Play now btn clk
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(20000);
			
			//Play again button
			
				/*js.executeScript("arguments[0].click()", playagainbtn);
				playagainbtn.click();
				Thread.sleep(2000);*/
				
				//Close button clk
				driver.findElement(By.xpath("(//button[text()='Close'])[1]")).click();
				Thread.sleep(2000);
		
				//Instant jackpot button click
				driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
				Thread.sleep(2000);
				
			//Prize Chart empty popup
			
				//Bonus - No option click
				Pattern bonusNoOption3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
				scrn.wait(bonusNoOption3,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Prize chart btn clk
				Pattern ptrn_prizechart=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Instant game - prize chart text.png");
				scrn.wait(ptrn_prizechart,50);
				scrn.click();
				Thread.sleep(2000);
			
				//Scrolling down in prize chart popup
				WebElement scrolldown = driver.findElement(By.xpath("//div[text()='Ticket Value']"));
				js.executeScript("arguments[0].scrollIntoView(false)", scrolldown);
				Thread.sleep(2000);
				
				//Scrolling up in prize chart popup
				WebElement scrollup = driver.findElement(By.xpath("//h5[text()='Your Ticket & Prize Chart']"));
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);
				
				//Closing the popup
				driver.findElement(By.xpath("//img[@class='cursor-pointer']")).click();
				Thread.sleep(4000);
			
		//Prize Chart popup with few selected ball numbers
				
				//Normal ball Numbers pick 
				
					//Number - 7 
				Thread.sleep(2000);
				scrn.wait(ptrnnum7,50);
				scrn.click();
				Thread.sleep(2000);
						
					//Number - 9
				Thread.sleep(2000);
				scrn.wait(ptrnnum9,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Number -15
				scrn.wait(ptrnnum15,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Golden ball Numbers pick 
					
					//Number - 1 
				Thread.sleep(2000);
				scrn.wait(ptrngoldennum1,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Prize chart btn clk
				scrn.wait(ptrn_prizechart,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Closing the popup
				driver.findElement(By.xpath("//img[@class='cursor-pointer']")).click();
				Thread.sleep(4000);
				
				//Reset pick button click
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
				
			//Prize Chart popup with all ball numbers
				
				//Quick pick button clk
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Prize chart btn clk
				scrn.wait(ptrn_prizechart,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Closing the popup
				driver.findElement(By.xpath("//img[@class='cursor-pointer']")).click();
				Thread.sleep(4000);
				
				//Reset pick button click
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
				
			//Prize Chart popup with selected ticket value
				
				//Quick pick button clk
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
				
				//4th Chip 
				WebElement fourthchip3 = driver.findElement(By.xpath("(//p[@class='chip-images-numbers'])[3]"));
				fourthchip3.click();
				Thread.sleep(3000);
				
				//Prize chart btn clk
				scrn.wait(ptrn_prizechart,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Closing the popup
				driver.findElement(By.xpath("//img[@class='cursor-pointer']")).click();
				Thread.sleep(4000);
				
				//Back btn clk
				driver.findElement(By.xpath("//h5[text()='Back']")).click();
				Thread.sleep(2000);
			
	}
	
		public void instant_bonus() throws Exception {
		
				//Instant jackpot button click
				
				driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
				Thread.sleep(2000);
				
				//Bonus - Yes option click
				Pattern bonusYesOption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\bonus popup - yes option.png");
				scrn.wait(bonusYesOption,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Quick play - Playing game 
				
				//Quick pick button clk
				Pattern quickpickbtn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(quickpickbtn,50);
				scrn.click();
				Thread.sleep(2000);
				
				//1st Chip 
				WebElement firstchip = driver.findElement(By.xpath("//p[@class='chip-images-numbers-1']"));
				firstchip.click();
				Thread.sleep(3000);
				
				//Play now btn clk
				Pattern ptrn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled play now button.png");
				scrn.wait(ptrn,50);
				scrn.click();
				Thread.sleep(20000);
			
				//Close button clk
				driver.findElement(By.xpath("(//button[text()='Close'])[1]")).click();
				Thread.sleep(2000);
	
	}
	
	
	public void roulotto_WithoutLoggedInUser() throws Exception {
		
				//Roulotto button click
				
				driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
				Thread.sleep(2000);
				
			//Exacto clk
				
				//Box-1
				Pattern ptrn_exactotxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - exacto 20x text.png");
				scrn.wait(ptrn_exactotxt,50);
				scrn.click();
				Thread.sleep(2000);
	
				//Box-1
				/*driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]")).click();
				Thread.sleep(2000);
				
				//Box-2
				driver.findElement(By.xpath("(//h6[text()='Exacto'])[2]")).click();
				Thread.sleep(2000);
			
				//Box-3
				driver.findElement(By.xpath("(//h6[text()='Exacto'])[3]")).click();
				Thread.sleep(2000);
			
				//Box-4
				driver.findElement(By.xpath("(//h6[text()='Exacto'])[4]")).click();
				Thread.sleep(2000);
				
				//Box-5
				driver.findElement(By.xpath("(//h6[text()='Exacto'])[5]")).click();
				Thread.sleep(2000);*/
				
			//Odd text
				
				Pattern ptrn_oddtxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - odd text.png");
				scrn.wait(ptrn_oddtxt,50);
				scrn.click();
				Thread.sleep(2000);
				
			//Even text
				
				Pattern ptrn_eventxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - even text.png");
				scrn.wait(ptrn_eventxt,50);
				scrn.click();
				Thread.sleep(2000);
				
			//11-20 text
				
				Pattern ptrn_11_20txt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 11 to 20 text.png");
				scrn.wait(ptrn_11_20txt,50);
				scrn.click();
				Thread.sleep(2000);
				
			//1-10 text 
				
				Pattern ptrn_1_10txt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 1 to 10 text.png");
				scrn.wait(ptrn_1_10txt,50);
				scrn.click();
				Thread.sleep(2000);
			
			//Red text 
			
				Pattern ptrn_redtxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - red text.png");
				scrn.wait(ptrn_redtxt,50);
				scrn.click();
				Thread.sleep(2000);
				
			//Black text 
				
				Pattern ptrn_blacktxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - black text.png");
				scrn.wait(ptrn_blacktxt,50);
				scrn.click();
				Thread.sleep(2000);
					
			//1st ball combo text 
					
				Pattern ptrn_1stballcombotxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 1st ball combo text.png");
				scrn.wait(ptrn_1stballcombotxt,50);
				scrn.click();
				Thread.sleep(2000);	
					
			//2nd ball combo text 
					
				Pattern ptrn_2ndballcombotxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 2nd ball combo text.png");
				scrn.wait(ptrn_2ndballcombotxt,50);
				scrn.click();
				Thread.sleep(2000);	
					
			//3rd ball combo text 
					
				Pattern ptrn_3rdballcombotxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 3rd ball combo text.png");
				scrn.wait(ptrn_3rdballcombotxt,50);
				scrn.click();
				Thread.sleep(2000);	
				
			//4th ball combo text 
					
				Pattern ptrn_4thballcombotxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 4th ball combo text.png");
				scrn.wait(ptrn_4thballcombotxt,50);
				scrn.click();
				Thread.sleep(2000);	
					
			//5th ball combo text 
					
				Pattern ptrn_5thballcombotxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 5th ball combo text.png");
				scrn.wait(ptrn_5thballcombotxt,50);
				scrn.click();
				Thread.sleep(2000);			
					
			//Multi ball combo text 
					
				Pattern ptrn_Multiballcombotxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - multi ball combo text.png");
				scrn.wait(ptrn_Multiballcombotxt,50);
				scrn.click();
				Thread.sleep(2000);
					
				//Scrolling to chips value and buttons
					
				WebElement thirdballcombotext = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(2000);
					
			//Reset all btn
					
				Pattern ptrn_Resetalltxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - reset all.png");
				scrn.wait(ptrn_Resetalltxt,50);
				scrn.click();
				Thread.sleep(2000);
				
			//Chips value click
					
				//chip - 1
				Pattern ptrn_chipone=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - chip value - 1.png");
				scrn.wait(ptrn_chipone,50);
				scrn.click();
				Thread.sleep(2000);
					
				//chip - 5
				Pattern ptrn_chipfive=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 5 chip value.png");
				scrn.wait(ptrn_chipfive,50);
				scrn.click();
				Thread.sleep(2000);
				
				//chip - 10
				Pattern ptrn_chipten=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 10 chip value.png");
				scrn.wait(ptrn_chipten,50);
				scrn.click();
				Thread.sleep(2000);
				
				//chip - 25
				Pattern ptrn_chiptwentyfive=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 25 chip value.png");
				scrn.wait(ptrn_chiptwentyfive,50);
				scrn.click();
				Thread.sleep(2000);
					
				//chip - 100
				Pattern ptrn_chiphundred=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 100 chip value.png");
				scrn.wait(ptrn_chiphundred,50);
				scrn.click();
				Thread.sleep(2000);
					
				//None button
				Pattern ptrn_nonebtn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - none button.png");
				scrn.wait(ptrn_nonebtn,50);
				scrn.click();
				Thread.sleep(2000);
					
				//Play now button
				Pattern ptrn_playnowbtn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - play now button.png");
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Scrolling to roulotto title upwards	
				WebElement roulottotitle = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle);
				Thread.sleep(2000);
					
				//Prize chart txt	
				Pattern ptrn_prizechart=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - prize chart btn.png");
				scrn.wait(ptrn_prizechart,50);
				scrn.click();
				Thread.sleep(2000);
			
				//Prize chart popup - Scrolling downwards		
				WebElement scrolldowntext = driver.findElement(By.xpath("//h6[text()='650x']"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldowntext);
				Thread.sleep(2000);
				
				//Prize chart popup - Scrolling upwards	
				WebElement scrolluptext = driver.findElement(By.xpath("//h3[text()='Roulotto Ticket & Prize Chart']"));
				js.executeScript("arguments[0].scrollIntoView(false)", scrolluptext);
				Thread.sleep(2000);
					
				//Close button 
				driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[4]")).click();
				Thread.sleep(4000);
					
				//Back button
				driver.findElement(By.xpath("//h5[text()='Back']")).click();
				Thread.sleep(2000);
	
				System.out.println("*******Roulotto without login module successful********");
						
	}
	
	public void roulotto_WithLoggedInUser_NegativeCaseModule() throws Exception {
		
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
				//Closing the chat box close button
				/*int size = driver.findElements(By.tagName("iframe")).size();
				System.out.println(size);
				
				driver.switchTo().frame(2);
				Thread.sleep(2000);
				System.out.println("********Switched to frame**********");
				
				WebElement chatbox_closebtn = driver.findElement(By.xpath("//*[local-name()='svg' and @class='sc-1uf0igr-1 fjHZYk']"));
				js.executeScript("arguments[0].click()",chatbox_closebtn);
				Thread.sleep(2000);*/
				
				Pattern chatbox_closebtn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\chat box - close button.png");
				scrn.wait(chatbox_closebtn,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Roulotto button click
				driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
				Thread.sleep(3000);
				
				//Bonus popup - clicking "no" option
				Pattern bonusNooption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
				scrn.wait(bonusNooption,50);
				scrn.click();
				Thread.sleep(4000);
		
			//Exacto - trying to Place bets without closing exacto popup on exacto bets box
		
				//Exacto popup box 1
				WebElement exacto1stbox = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox.click();
				Thread.sleep(2000);
				
				//Exacto num - 11 clk
				WebElement ex_num11 = driver.findElement(By.xpath("(//div[text()='11'])[1]"));
				ex_num11.click();
				Thread.sleep(2000);
				
				//Exacto bets box
				Pattern exactobetsboxon11=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - exacto bets box 11.png");
				scrn.wait(exactobetsboxon11,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Exacto popup box 5
				WebElement exacto5thbox = driver.findElement(By.xpath("(//div[@class='exacto-text'])[5]"));
				exacto5thbox.click();
				Thread.sleep(2000);
				
				//Exacto num - 14 clk
				WebElement ex_num14 = driver.findElement(By.xpath("(//div[text()='14'])[1]"));
				ex_num14.click();
				Thread.sleep(2000);
				
				Pattern exactobetsboxon14=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto -exacto bets box 14.png");
				scrn.wait(exactobetsboxon14,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Closing the exacto popup
				WebElement exactoclosebtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[3]"));
				exactoclosebtn.click();
				Thread.sleep(2000);
			
	//Exacto - trying to place bets & play game without chip value
			
				//Exacto bets box
				scrn.wait(exactobetsboxon11,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Exacto popup - num 11 clk
				Pattern exactopopup_num11=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - exacto popup - 11 number.png");
				scrn.wait(exactopopup_num11,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Exacto num - 14 clk
				WebElement ex_num14_2 = driver.findElement(By.xpath("(//div[text()='14'])[1]"));
				ex_num14_2.click();
				Thread.sleep(4000);
				
				WebElement ex_num11_2 = driver.findElement(By.xpath("(//div[text()='11'])[1]"));
				ex_num11_2.click();
				Thread.sleep(4000);
				
				//popup close btn
				WebElement exactoclosebtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
				exactoclosebtn2.click();
				Thread.sleep(2000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(2000);
				
				//Play now btn
				Pattern ptrn_playnowbtn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - play now button.png");
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Reset all clk
				Pattern ptrn_Resetalltxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - reset all.png");
				scrn.wait(ptrn_Resetalltxt,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle);
				Thread.sleep(4000);
				
				//Odd text in box 1
				WebElement oddoption = driver.findElement(By.xpath("(//div[text()='Odd'])[1]"));
				oddoption.click();
				Thread.sleep(4000);
			
				//Scrolling to chips value and buttons
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(2000);
			
				//Play now button
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Reset all clk
				scrn.wait(ptrn_Resetalltxt,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle2 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle2);
				Thread.sleep(4000);
				
		//Bets option - trying to place bets without chip value
				
				//Exacto popup box 1
				WebElement exacto1stbox2 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox2.click();
				Thread.sleep(2000);
			
				//Exacto popup - num 11 clk
				scrn.wait(exactopopup_num11,50);
				scrn.click();
				Thread.sleep(2000);
			
				//popup close btn
				WebElement exactoclosebtn3 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
				exactoclosebtn3.click();
				Thread.sleep(2000);
	
				//Odd text in box 1
				WebElement oddoption2 = driver.findElement(By.xpath("(//div[text()='Odd'])[1]"));
				oddoption2.click();
				Thread.sleep(2000);
				
				//Even text in box 2
				WebElement eventext = driver.findElement(By.xpath("(//div[text()='Even'])[2]"));
				eventext.click();
				Thread.sleep(2000);
				
				//11-20 text in box 3
				WebElement option11to20 = driver.findElement(By.xpath("(//div[text()='11-20'])[3]"));
				option11to20.click();
				Thread.sleep(2000);
				
				//1-10 text in box 4
				WebElement option1to10 = driver.findElement(By.xpath("(//div[text()='1-10'])[4]"));
				option1to10.click();
				Thread.sleep(2000);
				
				//Red text in box 5
				WebElement redtext = driver.findElement(By.xpath("(//div[text()='Red'])[5]"));
				redtext.click();
				Thread.sleep(2000);
				
				//Black text in box 1
				WebElement blacktext = driver.findElement(By.xpath("(//div[text()='Black'])[1]"));
				blacktext.click();
				Thread.sleep(2000);
				
		//Single combo bets box -  trying to place bets without chip value
				
				//1st ball combo bets box
				Pattern frstballcombobetsbox=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 1st ball combo bets box.png");
				scrn.wait(frstballcombobetsbox,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Scrolling to chips value and buttons
				
				WebElement thirdballcombotext2 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext2 );
				Thread.sleep(2000);
							
				//Play now button
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(4000);
		
				//Scrolling to roulotto title upwards
				WebElement roulottotitle3 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle3);
				Thread.sleep(4000);
				
				//Even text in 5th box
				WebElement eventext2 = driver.findElement(By.xpath("(//div[text()='Even'])[5]"));
				eventext2.click();
				Thread.sleep(2000);
				
				//5th ball combo bets box
				Pattern fifthballcombobetsbox=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 5th ball combo bets box.png");
				scrn.wait(fifthballcombobetsbox,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext3 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext3 );
				Thread.sleep(2000);
				
				//Multi ball combo bets box
				Pattern Multiballcombobetsbox=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - mutli ball combo bets box.png");
				scrn.wait(Multiballcombobetsbox,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Play now button
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Reset all clk
				scrn.wait(ptrn_Resetalltxt,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle4 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle4);
				Thread.sleep(4000);
				
		//Placing bets in single combo box without placing bets in two options
				
				//Scrolling to chips value and buttons
				
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(2000);
				
				//chip - 1 (value - 5)
				/*Pattern ptrn_chipone=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 1 chip value.png");
				scrn.wait(ptrn_chipone,50);
				scrn.click();
				Thread.sleep(2000);*/
				
				WebElement chip1 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[1]"));
				chip1.click();
				Thread.sleep(3000);
				
				//Scrolling to roulotto title upwards
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle);
				Thread.sleep(4000);
			
				//Black text - box 1
				blacktext.click();
				Thread.sleep(2000);
				
				//1st ball combo play text 
				Pattern ptrn_1stballcombotxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - 1st ball combo text.png");
				scrn.wait(ptrn_1stballcombotxt,50);
				scrn.click();
				Thread.sleep(2000);	
				
				//Exacto popup box 1
				WebElement exacto1stbox3 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				js.executeScript("arguments[0].click()",exacto1stbox3);
				//exacto1stbox.click();
				Thread.sleep(2000);
				
				//Exacto num - 11 clk
				scrn.wait(exactopopup_num11,50);
				scrn.click();
				scrn.click();
				Thread.sleep(2000);
				
				//Closing the exacto popup
				WebElement exactoclosebtn4 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
				exactoclosebtn4.click();
				Thread.sleep(2000);
				
				//1st ball combo play text 
				scrn.wait(ptrn_1stballcombotxt,50);
				scrn.click();
				Thread.sleep(2000);	
				
				//Odd text click - box 1
				WebElement oddoption3 = driver.findElement(By.xpath("(//div[text()='Odd'])[1]"));
				oddoption3.click();
				Thread.sleep(2000);
				
				//Multi ball combo text 
				Pattern ptrn_Multiballcombotxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - multi ball combo text.png");
				scrn.wait(ptrn_Multiballcombotxt,50);
				scrn.click();
				Thread.sleep(2000);
				
				System.out.println("**********Please place bet atleast in 2 single combo balls**********");
				
				//Even text in box 2
				WebElement eventext3 = driver.findElement(By.xpath("(//div[text()='Even'])[2]"));
				eventext3.click();
				Thread.sleep(2000);
					
				//Scrolling to chips value and buttons
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(2000);
						
				//Multi ball combo bets box
				scrn.wait(Multiballcombobetsbox,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Scrolling to roulotto title upwards
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle);
				Thread.sleep(4000);
				
				//Closing the placed bet in black option in box - 1
				WebElement closeblackoption = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[4]"));
				closeblackoption.click();
				Thread.sleep(2000);
				
				//1st ball combo play text 
				scrn.wait(ptrn_1stballcombotxt,50);
				scrn.click();
				Thread.sleep(4000);	
				
				//Black text - box 1
				blacktext.click();
				Thread.sleep(4000);
				
				//Closing the placed bet in even option in box - 2
				WebElement closeevenoption = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[6]"));
				closeevenoption.click();
				Thread.sleep(4000);
				
				//Scrolling to chips value and buttons
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(4000);
				
				//Multi ball combo text 
				scrn.wait(ptrn_Multiballcombotxt,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Scrolling to roulotto title upwards
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle);
				Thread.sleep(4000);
				
				//1st ball combo play text 
				scrn.wait(ptrn_1stballcombotxt,50);
				scrn.click();
				Thread.sleep(4000);	
				
				//Closing placed bets in 1st ball combo play box
				WebElement close1stballcomboboxoption = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[5]"));
				close1stballcomboboxoption.click();
				Thread.sleep(4000);
	
				//11-20 option in box - 1
				WebElement option11to202 = driver.findElement(By.xpath("(//div[text()='11-20'])[1]"));
				option11to202.click();
				Thread.sleep(3000);
				
				//Black option in box -5
				WebElement blackoption5thbox = driver.findElement(By.xpath("(//div[text()='Black'])[5]"));
				blackoption5thbox.click();
				Thread.sleep(2000);
				
				//Closing black option in box-5
				WebElement closeblackoptionbox5 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[7]"));
				closeblackoptionbox5.click();
				Thread.sleep(3000);
				
			//After closing bets option - Rebetting in black option in box-5
				WebElement blackoption5thbox2 = driver.findElement(By.xpath("(//div[text()='Black'])[5]"));
				blackoption5thbox2.click();
				Thread.sleep(3000);
				
				//Betting in Red option in box - 5
				WebElement redoption5thbox = driver.findElement(By.xpath("(//div[text()='Red'])[5]"));
				redoption5thbox.click();
				Thread.sleep(3000);
				
			//After closing bets box - Betting in single ball combo box -1 
				
				//1st ball combo play text 
				scrn.wait(ptrn_1stballcombotxt,50);
				scrn.click();
				Thread.sleep(4000);	
					
				//Scrolling to chips value and buttons
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(4000);
				
				//Placing bets in Multi ball 
				scrn.wait(ptrn_Multiballcombotxt,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Closing bets in Multi ball
				WebElement closemultiballbox = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[8]"));
				closemultiballbox.click();
				Thread.sleep(3000);
				
			//After closing multi box - Betting in multi ball combo box
				
				//Multi ball combo text 
				scrn.wait(ptrn_Multiballcombotxt,50);
				scrn.click();
				Thread.sleep(4000);
						
				//Reset all btn clk
				scrn.wait(ptrn_Resetalltxt,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Clicking chip - none button
				WebElement chipnonebtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[3]"));
				a.doubleClick(chipnonebtn).click();
				Thread.sleep(2000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle5 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle5);
				Thread.sleep(4000);
				
		//Checking Bet limitation in options box - 3
				
				//Scrolling to chips value and buttons
				//WebElement thirdballcombotext = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(4000);
				
				//Chip - 4 (value 30)
				WebElement chip5 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[5]"));
				chip5.click();
				Thread.sleep(3000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle6 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle6);
				Thread.sleep(4000);
				
				//1- 10 option in box - 3
				WebElement option1to10box3 = driver.findElement(By.xpath("(//div[text()='1-10'])[3]"));
				
				for (int i = 0; i < 3400; i++) {
					
					option1to10box3.click();
				
				}
				//Scrolling to chips value and buttons
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(4000);
				
				//Chip - 1 (value - 5)
				//WebElement chip1 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[1]"));
				chip1.click();
				Thread.sleep(3000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle7 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle7);
				Thread.sleep(4000);
				
				//trying to place more than limit
				option1to10box3.click();
				Thread.sleep(3000);
			
		//Checking Bet limitation in single combo box - 3
				
				//Red option
				WebElement redoptionbox3 = driver.findElement(By.xpath("(//div[text()='Red'])[3]"));
				redoptionbox3.click();
				Thread.sleep(3000);
				
				//Scrolling to chips value and buttons
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(4000);
				
				//Chip - 5 (value 30)
				chip5.click();
				Thread.sleep(3000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle8 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle8);
				Thread.sleep(4000);
				
				//Single combo box - 3
				WebElement singlebox3 = driver.findElement(By.xpath("(//input[@type='text'])[3]"));	
	
				for (int i = 0; i < 340; i++) {
					
					singlebox3.click();
				
				}
				
				Thread.sleep(2000);
				//Scrolling to chips value and buttons
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(4000);
				
				//Chip - 1 (value - 5)
				chip1.click();
				Thread.sleep(3000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle9 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle9);
				Thread.sleep(4000);
				
				//trying to place more than limit
				
				for (int i = 0; i < 3; i++) {
					
					singlebox3.click();
					Thread.sleep(2000);
					
				}
				
		//Checking Bet limitation in multi combo box - 3	
				
				//Black option in box - 4
				WebElement blackoptionbox4 = driver.findElement(By.xpath("(//div[text()='Black'])[4]"));
				blackoptionbox4.click();
				Thread.sleep(2000);
				
				//Scrolling to chips value and buttons
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(4000);
				
				//Chip - 5 (value 30)
				chip5.click();
				Thread.sleep(3000);
				
				//placing bets in Multi ball combo box
				WebElement multibox = driver.findElement(By.xpath("(//input[@type='text'])[5]"));
				
				for (int i = 0; i < 340; i++) {
					
					multibox.click();
					
				}
				
				Thread.sleep(2000);
				
				//Chip - 1 (value - 5)
				chip1.click();
				Thread.sleep(3000);
				
				//trying to place more than limit
				
				for (int i = 0; i < 3; i++) {
					
					multibox.click();
					Thread.sleep(2000);
					
				}
				
		//Trying to play game without funds - main balance
				
				//Scrolling to chips value and buttons
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(4000);
				
				//Play now button
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Refreshing the page
				driver.navigate().refresh();
				Thread.sleep(2000);
				
		//Trying to play game without funds - bonus
				
				//Bonus popup - clicking "Yes" option
				Pattern bonusYesoption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\bonus popup - yes option.png");
				scrn.wait(bonusYesoption,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext4 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext4);
				Thread.sleep(4000);
				
				//Chip - 1 (value - 5)
				WebElement chip11 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[1]"));
				chip11.click();
				Thread.sleep(3000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle10 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle10);
				Thread.sleep(4000);
				
				//Placing bets in exacto box -4
				WebElement exactobox4 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[4]"));
				exactobox4.click();
				Thread.sleep(2000);
				
				//Exacto option number - 1
				WebElement exactooptionnum1 = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
				exactooptionnum1.click();
				Thread.sleep(2000);
				
				//Closing the exacto popup
				WebElement closingexactopopup = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
				closingexactopopup.click();
				Thread.sleep(2000);
				
				//Odd option in box - 4
				WebElement oddoptionbox4 = driver.findElement(By.xpath("(//div[text()='Odd'])[4]"));
				oddoptionbox4.click();
				Thread.sleep(2000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext5 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext5 );
				Thread.sleep(4000);
				
				//Play now button
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(4000);
			
		//Prize chart popup		
		
				//Scrolling to roulotto title upwards
				WebElement roulottotitle11 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle11);
				Thread.sleep(4000);
				
				//Prize chart txt
				Pattern ptrn_prizechart=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - prize chart btn.png");
				scrn.wait(ptrn_prizechart,50);
				scrn.click();
				Thread.sleep(2000);
			
				//Prize chart popup - Scrolling downwards
				WebElement scrolldowntext = driver.findElement(By.xpath("//h6[text()='650x']"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldowntext);
				Thread.sleep(2000);
			
				//Prize chart popup - Scrolling upwards
				WebElement scrolluptext = driver.findElement(By.xpath("//h3[text()='Roulotto Ticket & Prize Chart']"));
				js.executeScript("arguments[0].scrollIntoView(false)", scrolluptext);
				Thread.sleep(2000);
				
				//Close button 
				driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[6]")).click();
				Thread.sleep(4000);
				
				//Back button
				driver.findElement(By.xpath("//h5[text()='Back']")).click();
				Thread.sleep(2000);
				
	}
	
	public void roulotto_WithLoggedInUser_PositiveCaseModule() throws Exception {
		
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
				Pattern chatbox_closebtn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\chat box - close button.png");
				scrn.wait(chatbox_closebtn,50);
				scrn.click();
				Thread.sleep(4000);
			
				//Roulotto button click
				driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
				Thread.sleep(3000);
				
				//Bonus popup - clicking "No" option
				Pattern bonusNooption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
				scrn.wait(bonusNooption,50);
				scrn.click();
				Thread.sleep(4000);
			
		//Playing game only with exacto option - box 1
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(4000);
				
				//Chip value - 5
				WebElement chip1 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[1]"));
				chip1.click();
				Thread.sleep(3000);
			
				//Scrolling to roulotto title upwards
				WebElement roulottotitle = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle);
				Thread.sleep(4000);
			
				//Exacto popup box 1
				WebElement exacto1stbox = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox.click();
				Thread.sleep(2000);
				
				//Exacto num - 11 clk
				WebElement ex_num11 = driver.findElement(By.xpath("(//div[text()='11'])[1]"));
				ex_num11.click();
				Thread.sleep(2000);
				
				//Scrolling to chips value and buttons downwards
				//WebElement thirdballcombotext = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext );
				Thread.sleep(4000);
				
				//Play now button
				Pattern ptrn_playnowbtn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - play now button.png");
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(2000);	
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle2 = driver.findElement(By.xpath("//h4[text()='Your Roulotto Picks']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle2);
				Thread.sleep(15000);
	
		//Playing game only with one bet options - box 2
				
				//Scrolling to chips value and buttons downwards
				WebElement thirdballcombotext2 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext2 );
				Thread.sleep(4000);
				
				//Play again btn clk
				WebElement playagainbtnclk = driver.findElement(By.xpath("//button[text()='Play again']"));
				playagainbtnclk.click();
				Thread.sleep(4000);
				
				//Bonus popup - clicking "no" option
				scrn.wait(bonusNooption,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext3 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext3 );
				Thread.sleep(4000);
				
				//Chip - value - 10
				WebElement chip2 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[2]"));
				chip2.click();
				Thread.sleep(3000);
			
				//Scrolling to roulotto title upwards
				WebElement roulottotitle3 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle3);
				Thread.sleep(4000);
				
				//Even option - box -2
				WebElement evenbox2 = driver.findElement(By.xpath("(//div[text()='Even'])[2]"));
				evenbox2.click();
				Thread.sleep(2000);
				
				//Scrolling to chips value and buttons downwards
				//WebElement thirdballcombotext = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext3 );
				Thread.sleep(4000);
				
				//Play now button
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(2000);	
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle4 = driver.findElement(By.xpath("//h4[text()='Your Roulotto Picks']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle4);
				Thread.sleep(15000);
				
				//Scrolling to chips value and buttons downwards
				WebElement thirdballcombotext4 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext4 );
				Thread.sleep(4000);
				
				//Play again btn clk
				WebElement playagainbtnclk2 = driver.findElement(By.xpath("//button[text()='Play again']"));
				playagainbtnclk2.click();
				Thread.sleep(4000);
				
			//Playing game with single combo bets - box 3
				
				//Bonus popup - clicking "no" option
				scrn.wait(bonusNooption,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext5 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext5 );
				Thread.sleep(4000);
				
				//Chip value - 15
				WebElement chip3 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[3]"));
				chip3.click();
				Thread.sleep(3000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle5 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle5);
				Thread.sleep(4000);
				
				//Odd option - box 3
				WebElement oddoption4 = driver.findElement(By.xpath("(//div[text()='Odd'])[3]"));
				oddoption4.click();
				Thread.sleep(2000);
				
				//11-20 option - box 3
				WebElement option11to20box3 = driver.findElement(By.xpath("(//div[text()='11-20'])[3]"));
				option11to20box3.click();
				Thread.sleep(2000);
				
				//Single combo box - 3
				WebElement singlebox3 = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
				singlebox3.click();
				Thread.sleep(2000);
				
				//Scrolling to chips value and buttons
				//WebElement thirdballcombotext5 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext5 );
				Thread.sleep(4000);
				
				//Play now button
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(2000);	
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle6 = driver.findElement(By.xpath("//h4[text()='Your Roulotto Picks']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle6);
				Thread.sleep(15000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext6 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext6 );
				Thread.sleep(4000);
				
				//Play again btn clk
				WebElement playagainbtnclk3 = driver.findElement(By.xpath("//button[text()='Play again']"));
				playagainbtnclk3.click();
				Thread.sleep(4000);
			
		//Playing game with multi combo bets - box 3	
				
				//Bonus popup - clicking "no" option
				scrn.wait(bonusNooption,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext7 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext7 );
				Thread.sleep(2000);
				
				//Chip value - 15
				WebElement chip4 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[3]"));
				chip4.click();
				Thread.sleep(3000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle7 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle7);
				Thread.sleep(4000);
				
			//Box -1 (Checking all exacto numbers are selectable)
				
				//Exacto popup box 1
				WebElement exacto1stbox12 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox12.click();
				Thread.sleep(2000);
				
				//Exacto num - 11 clk
				WebElement ex_num1 = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
				ex_num1.click();
				Thread.sleep(2000);
				
				WebElement ex_num2 = driver.findElement(By.xpath("(//div[text()='2'])[1]"));
				ex_num2.click();
				Thread.sleep(2000);
				
				WebElement ex_num3 = driver.findElement(By.xpath("(//div[text()='3'])[1]"));
				ex_num3.click();
				Thread.sleep(2000);
				
				WebElement ex_num4 = driver.findElement(By.xpath("(//div[text()='4'])[1]"));
				ex_num4.click();
				Thread.sleep(2000);
				
				WebElement ex_num5 = driver.findElement(By.xpath("(//div[text()='5'])[1]"));
				ex_num5.click();
				Thread.sleep(2000);
				
				WebElement ex_num6 = driver.findElement(By.xpath("(//div[text()='6'])[1]"));
				ex_num6.click();
				Thread.sleep(2000);
				
				WebElement ex_num7 = driver.findElement(By.xpath("(//div[text()='7'])[1]"));
				ex_num7.click();
				Thread.sleep(2000);
				
				WebElement ex_num8 = driver.findElement(By.xpath("(//div[text()='8'])[1]"));
				ex_num8.click();
				Thread.sleep(2000);
				
				WebElement ex_num9 = driver.findElement(By.xpath("(//div[text()='9'])[1]"));
				ex_num9.click();
				Thread.sleep(2000);
				
				WebElement ex_num10 = driver.findElement(By.xpath("(//div[text()='10'])[1]"));
				ex_num10.click();
				Thread.sleep(2000);
				
				WebElement ex_num112 = driver.findElement(By.xpath("(//div[text()='11'])[1]"));
				ex_num112.click();
				Thread.sleep(2000);
				
				WebElement ex_num12 = driver.findElement(By.xpath("(//div[text()='12'])[1]"));
				ex_num12.click();
				Thread.sleep(2000);
				
				WebElement ex_num13 = driver.findElement(By.xpath("(//div[text()='13'])[1]"));
				ex_num13.click();
				Thread.sleep(2000);
				
				WebElement ex_num14 = driver.findElement(By.xpath("(//div[text()='14'])[1]"));
				ex_num14.click();
				Thread.sleep(2000);
				
				WebElement ex_num15 = driver.findElement(By.xpath("(//div[text()='15'])[1]"));
				ex_num15.click();
				Thread.sleep(2000);
				
				WebElement ex_num16 = driver.findElement(By.xpath("(//div[text()='16'])[1]"));
				ex_num16.click();
				Thread.sleep(2000);
				
				WebElement ex_num17 = driver.findElement(By.xpath("(//div[text()='17'])[1]"));
				ex_num17.click();
				Thread.sleep(2000);
				
				WebElement ex_num18 = driver.findElement(By.xpath("(//div[text()='18'])[1]"));
				ex_num18.click();
				Thread.sleep(2000);
				
				WebElement ex_num19 = driver.findElement(By.xpath("(//div[text()='19'])[1]"));
				ex_num19.click();
				Thread.sleep(2000);
				
				WebElement ex_num20 = driver.findElement(By.xpath("(//div[text()='20'])[1]"));
				ex_num20.click();
				Thread.sleep(2000);
				
				//Closing the Exacto popup 
				WebElement closeexactopopup1 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
				closeexactopopup1.click();
				Thread.sleep(2000);
				
				//Odd option
				WebElement oddoptionbox1 = driver.findElement(By.xpath("(//div[text()='Odd'])[1]"));
				oddoptionbox1.click();
				Thread.sleep(2000);
				
				//1-10 option
				WebElement option1to20box1 = driver.findElement(By.xpath("(//div[text()='1-10'])[1]"));
				option1to20box1.click();
				Thread.sleep(2000);
				
				//Red option
				WebElement redoptionbox1 = driver.findElement(By.xpath("(//div[text()='Red'])[1]"));
				redoptionbox1.click();
				Thread.sleep(2000);
				
				//1st single combo box
				WebElement singlebox1 = driver.findElement(By.xpath("(//input[@type='text'])[5]"));
					singlebox1.click();
					Thread.sleep(2000);
					
			//Box -2
				
				//Exacto popup box 2
				WebElement exacto1stbox2 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox2.click();
				Thread.sleep(2000);
				
				//Exacto num - 15 clk
				WebElement ex_num1box2 = driver.findElement(By.xpath("(//div[text()='15'])[1]"));
				ex_num1box2.click();
				Thread.sleep(2000);
				
				//Closing the Exacto popup 
				WebElement closeexactopopup2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[7]"));
				closeexactopopup2.click();
				Thread.sleep(2000);
				
				//Even option
				WebElement evenptionbox2 = driver.findElement(By.xpath("(//div[text()='Even'])[2]"));
				evenptionbox2.click();
				Thread.sleep(2000);
				
				//11-20 option
				WebElement option11to20box2 = driver.findElement(By.xpath("(//div[text()='11-20'])[2]"));
				option11to20box2.click();
				Thread.sleep(2000);
				
				//Black option
				WebElement blackoptionbox2 = driver.findElement(By.xpath("(//div[text()='Black'])[2]"));
				blackoptionbox2.click();
				Thread.sleep(2000);
				
				//2nd single combo box
				WebElement singlebox2 = driver.findElement(By.xpath("(//input[@type='text'])[10]"));
	
					singlebox2.click();
					Thread.sleep(2000);
	
			//Box -3
				
				//Exacto popup box 3
				WebElement exacto1stbox3 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox3.click();
				Thread.sleep(2000);
				
				//Exacto num - 15 clk
				WebElement ex_num1box3 = driver.findElement(By.xpath("(//div[text()='20'])[1]"));
				ex_num1box3.click();
				Thread.sleep(2000);
				
				//Closing the Exacto popup
				WebElement closeexactopopupbox3 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[12]"));
				closeexactopopupbox3.click();
				Thread.sleep(2000);
			
				//Odd option
				WebElement oddoptionbox3 = driver.findElement(By.xpath("(//div[text()='Odd'])[3]"));
				oddoptionbox3.click();
				Thread.sleep(2000);
				
				//1-10 option
				WebElement option1to20box3 = driver.findElement(By.xpath("(//div[text()='1-10'])[3]"));
				option1to20box3.click();
				Thread.sleep(2000);
				
				//Red option
				WebElement redoptionbox3 = driver.findElement(By.xpath("(//div[text()='Red'])[3]"));
				redoptionbox3.click();
				Thread.sleep(2000);
				
				//3rd single combo box
				WebElement singlebox32 = driver.findElement(By.xpath("(//input[@type='text'])[15]"));
					singlebox32.click();
					Thread.sleep(2000);
		
			//Box - 4
				
				//Exacto popup box 4
				WebElement exacto1stbox4 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox4.click();
				Thread.sleep(2000);
				
				//Exacto num - 15 clk
				WebElement ex_num1box4 = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
				ex_num1box4.click();
				Thread.sleep(2000);
				
				//Closing the Exacto popup 
				WebElement closeexactopopup4 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[17]"));
				closeexactopopup4.click();
				Thread.sleep(2000);
				
				//Even option
				WebElement evenptionbox4 = driver.findElement(By.xpath("(//div[text()='Even'])[4]"));
				evenptionbox4.click();
				Thread.sleep(2000);
				
				//11-20 option
				WebElement option11to20box4 = driver.findElement(By.xpath("(//div[text()='11-20'])[4]"));
				option11to20box4.click();
				Thread.sleep(2000);
				
				//Black option
				WebElement blackoptionbox4 = driver.findElement(By.xpath("(//div[text()='Black'])[4]"));
				blackoptionbox4.click();
				Thread.sleep(2000);
				
				//4th single combo box
				WebElement singlebox4 = driver.findElement(By.xpath("(//input[@type='text'])[20]"));
			
					singlebox4.click();
					Thread.sleep(2000);
		
			//Box -5
				
				//Exacto popup box 3
				WebElement exacto1stbox5 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox5.click();
				Thread.sleep(2000);
				
				//Exacto num - 15 clk
				WebElement ex_num1box5 = driver.findElement(By.xpath("(//div[text()='10'])[1]"));
				ex_num1box5.click();
				Thread.sleep(2000);
				
				//Closing the Exacto popup
				WebElement closeexactopupbox5 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[22]"));
				closeexactopupbox5.click();
				Thread.sleep(2000);
				
				//Odd option
				WebElement oddoptionbox5 = driver.findElement(By.xpath("(//div[text()='Odd'])[5]"));
				oddoptionbox5.click();
				Thread.sleep(2000);
				
				//1-10 option
				WebElement option1to20box5 = driver.findElement(By.xpath("(//div[text()='1-10'])[5]"));
				option1to20box5.click();
				Thread.sleep(2000);
				
				//Red option
				WebElement redoptionbox5 = driver.findElement(By.xpath("(//div[text()='Red'])[5]"));
				redoptionbox5.click();
				Thread.sleep(2000);
				
				//5th single combo box
				WebElement singlebox5 = driver.findElement(By.xpath("(//input[@type='text'])[25]"));
			
					singlebox5.click();
					Thread.sleep(2000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext8 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext8 );
				Thread.sleep(4000);
				
				//Multi ball combo box
				WebElement multiballbox = driver.findElement(By.xpath("(//input[@type='text'])[26]"));
				multiballbox.click();
				Thread.sleep(2000);
				
				//Play now btn clk
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle8 = driver.findElement(By.xpath("//h4[text()='Your Roulotto Picks']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle8);
				Thread.sleep(15000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext9 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext9 );
				Thread.sleep(4000);
				
				//Close btn clk
				WebElement closebtnclk = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
				closebtnclk.click();
				Thread.sleep(2000);
		
	}
	public void roulotto_WithLoggedInUser_Bonus() throws Exception {
		
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
				//Roulotto button click
				driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
				Thread.sleep(3000);
				
				//Bonus popup - clicking "Yes" option
				Pattern bonusYesoption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\bonus popup - yes option.png");
				scrn.wait(bonusYesoption,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext7 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext7 );
				Thread.sleep(2000);
				
				//Chip value - 15
				WebElement chip4 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[3]"));
				chip4.click();
				Thread.sleep(3000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle7 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle7);
				Thread.sleep(4000);
				
			//Box -1 (Checking all exacto numbers are selectable)
				
				//Exacto popup box 1
				WebElement exacto1stbox12 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox12.click();
				Thread.sleep(2000);
				
				//Exacto num - 1 clk
				WebElement ex_num1 = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
				ex_num1.click();
				Thread.sleep(2000);
				
				//Closing the Exacto popup 
				WebElement closeexactopopup1 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
				closeexactopopup1.click();
				Thread.sleep(2000);
				
				//Odd option
				WebElement oddoptionbox1 = driver.findElement(By.xpath("(//div[text()='Odd'])[1]"));
				oddoptionbox1.click();
				Thread.sleep(2000);
				
				//1-10 option
				WebElement option1to20box1 = driver.findElement(By.xpath("(//div[text()='1-10'])[1]"));
				option1to20box1.click();
				Thread.sleep(2000);
				
				//Red option
				WebElement redoptionbox1 = driver.findElement(By.xpath("(//div[text()='Red'])[1]"));
				redoptionbox1.click();
				Thread.sleep(2000);
				
				//1st single combo box
				WebElement singlebox1 = driver.findElement(By.xpath("(//input[@type='text'])[5]"));
					singlebox1.click();
					Thread.sleep(2000);
				
			//Box -2
				
				//Exacto popup box 2
				WebElement exacto1stbox2 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox2.click();
				Thread.sleep(2000);
				
				//Exacto num - 15 clk
				WebElement ex_num1box2 = driver.findElement(By.xpath("(//div[text()='15'])[1]"));
				ex_num1box2.click();
				Thread.sleep(2000);
				
				//Closing the Exacto popup 
				WebElement closeexactopopup2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[7]"));
				closeexactopopup2.click();
				Thread.sleep(2000);
				
				//Even option
				WebElement evenptionbox2 = driver.findElement(By.xpath("(//div[text()='Even'])[2]"));
				evenptionbox2.click();
				Thread.sleep(2000);
				
				//11-20 option
				WebElement option11to20box2 = driver.findElement(By.xpath("(//div[text()='11-20'])[2]"));
				option11to20box2.click();
				Thread.sleep(2000);
				
				//Black option
				WebElement blackoptionbox2 = driver.findElement(By.xpath("(//div[text()='Black'])[2]"));
				blackoptionbox2.click();
				Thread.sleep(2000);
				
				//2nd single combo box
				WebElement singlebox2 = driver.findElement(By.xpath("(//input[@type='text'])[10]"));
	
					singlebox2.click();
					Thread.sleep(2000);
	
			//Box -3
				
				//Exacto popup box 3
				WebElement exacto1stbox3 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox3.click();
				Thread.sleep(2000);
				
				//Exacto num - 15 clk
				WebElement ex_num1box3 = driver.findElement(By.xpath("(//div[text()='20'])[1]"));
				ex_num1box3.click();
				Thread.sleep(2000);
				
				//Closing the Exacto popup
				WebElement closeexactopopupbox3 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[12]"));
				closeexactopopupbox3.click();
				Thread.sleep(2000);
			
				//Odd option
				WebElement oddoptionbox3 = driver.findElement(By.xpath("(//div[text()='Odd'])[3]"));
				oddoptionbox3.click();
				Thread.sleep(2000);
				
				//1-10 option
				WebElement option1to20box3 = driver.findElement(By.xpath("(//div[text()='1-10'])[3]"));
				option1to20box3.click();
				Thread.sleep(2000);
				
				//Red option
				WebElement redoptionbox3 = driver.findElement(By.xpath("(//div[text()='Red'])[3]"));
				redoptionbox3.click();
				Thread.sleep(2000);
				
				//3rd single combo box
				WebElement singlebox32 = driver.findElement(By.xpath("(//input[@type='text'])[15]"));
					singlebox32.click();
					Thread.sleep(2000);
		
			//Box - 4
				
				//Exacto popup box 4
				WebElement exacto1stbox4 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox4.click();
				Thread.sleep(2000);
				
				//Exacto num - 15 clk
				WebElement ex_num1box4 = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
				ex_num1box4.click();
				Thread.sleep(2000);
				
				//Closing the Exacto popup 
				WebElement closeexactopopup4 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[17]"));
				closeexactopopup4.click();
				Thread.sleep(2000);
				
				//Even option
				WebElement evenptionbox4 = driver.findElement(By.xpath("(//div[text()='Even'])[4]"));
				evenptionbox4.click();
				Thread.sleep(2000);
				
				//11-20 option
				WebElement option11to20box4 = driver.findElement(By.xpath("(//div[text()='11-20'])[4]"));
				option11to20box4.click();
				Thread.sleep(2000);
				
				//Black option
				WebElement blackoptionbox4 = driver.findElement(By.xpath("(//div[text()='Black'])[4]"));
				blackoptionbox4.click();
				Thread.sleep(2000);
				
				//4th single combo box
				WebElement singlebox4 = driver.findElement(By.xpath("(//input[@type='text'])[20]"));
			
					singlebox4.click();
					Thread.sleep(2000);
		
			//Box -5
				
				//Exacto popup box 3
				WebElement exacto1stbox5 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
				exacto1stbox5.click();
				Thread.sleep(2000);
				
				//Exacto num - 15 clk
				WebElement ex_num1box5 = driver.findElement(By.xpath("(//div[text()='10'])[1]"));
				ex_num1box5.click();
				Thread.sleep(2000);
				
				//Closing the Exacto popup
				WebElement closeexactopupbox5 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[22]"));
				closeexactopupbox5.click();
				Thread.sleep(2000);
				
				//Odd option
				WebElement oddoptionbox5 = driver.findElement(By.xpath("(//div[text()='Odd'])[5]"));
				oddoptionbox5.click();
				Thread.sleep(2000);
				
				//1-10 option
				WebElement option1to20box5 = driver.findElement(By.xpath("(//div[text()='1-10'])[5]"));
				option1to20box5.click();
				Thread.sleep(2000);
				
				//Red option
				WebElement redoptionbox5 = driver.findElement(By.xpath("(//div[text()='Red'])[5]"));
				redoptionbox5.click();
				Thread.sleep(2000);
				
				//5th single combo box
				WebElement singlebox5 = driver.findElement(By.xpath("(//input[@type='text'])[25]"));
	
					singlebox5.click();
					Thread.sleep(2000);
			
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext8 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext8 );
				Thread.sleep(4000);
				
				//Multi ball combo box
				WebElement multiballbox = driver.findElement(By.xpath("(//input[@type='text'])[26]"));
				multiballbox.click();
				Thread.sleep(2000);
				
				//Play now btn clk
				Pattern ptrn_playnowbtn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\roulotto - play now button.png");
				scrn.wait(ptrn_playnowbtn,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Scrolling to roulotto title upwards
				WebElement roulottotitle8 = driver.findElement(By.xpath("//h4[text()='Your Roulotto Picks']"));
				js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle8);
				Thread.sleep(15000);
				
				//Scrolling to chips value and buttons
				WebElement thirdballcombotext9 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
				js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext9 );
				Thread.sleep(4000);
				
				//Close btn clk
				WebElement closebtnclk = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
				closebtnclk.click();
				Thread.sleep(2000);
		
	}
	
	public void megajackpot_WithoutLoggedInUser() throws Exception {
		
				//Mega jackpot button click
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
				Thread.sleep(2000);
				
				//Play Now button click
				driver.findElement(By.xpath("//button[text()='Play Now']")).click();
				Thread.sleep(2000);
				
				//Quick pick btn clk
				Thread.sleep(2000);
				Pattern ptrn1=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(ptrn1,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Normal ball Numbers pick 
					//Number - 7 
				Thread.sleep(2000);
				Pattern ptrn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 7 number.png");
				scrn.wait(ptrn2,50);
				scrn.click();
				Thread.sleep(2000);
						
					//Number - 9
				Thread.sleep(2000);
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 9 number.png");
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Number - 10
				Thread.sleep(2000);
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 10 number.png");
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Number - 12
				Thread.sleep(2000);
				Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 12 number.png");
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Number -15
				Pattern ptrn6=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 15 number.png");
				scrn.wait(ptrn6,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Golden ball Numbers pick 
					//Number - 1 
				Thread.sleep(2000);
				Pattern ptrn7=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega - golden 2 pic.png");
				scrn.wait(ptrn7,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Reset pick btn clk
				Pattern ptrn8=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled reset pick button.png");
				scrn.wait(ptrn8,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Selecting Chip value
					//Chip - 2
				Pattern ptrn9=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega chip - 2usd.png");
				scrn.wait(ptrn9,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Chip - 3
				Pattern ptrn10=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega chip - 3usd.png");
				scrn.wait(ptrn10,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Chip - 4
				Pattern ptrn11=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega chip- 4 usd.png");
				scrn.wait(ptrn11,50);
				scrn.click();
				Thread.sleep(2000);
				
					//Chip - 5
				Pattern ptrn12=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega chip - 5usd.png");
				scrn.wait(ptrn12,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Mega Raffle info icon click
				Pattern ptrn13=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega raffle info icon.png");
				scrn.wait(ptrn13,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Mega raffle - yes option click
				Pattern ptrn14=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega raffle - yes option .png");
				scrn.wait(ptrn14,50);
				scrn.click();
				Thread.sleep(2000);
				
				
				//Mega raffle - No option click
				Pattern ptrn15=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega raffle - no option.png");
				scrn.wait(ptrn15,50);
				scrn.click();
				Thread.sleep(2000);
				
				System.out.println("***********User cannot able to select or play the game as expected***********");
				
				//Prize Chart button click
				Pattern ptrn16=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Instant game - prize chart text.png");
				scrn.wait(ptrn16,50);
				scrn.click();
				Thread.sleep(2000);
		
				//Scrolling down in prize chart popup
				WebElement scrolldown = driver.findElement(By.xpath("//div[text()='Ticket Value']"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
				
				//Scrolling up in prize chart popup
				WebElement scrollup = driver.findElement(By.xpath("//h5[text()='Your Ticket & Prize Chart']"));
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);
				
				//Closing the popup
				driver.findElement(By.xpath("//img[@class='cursor-pointer']")).click();
				Thread.sleep(4000);
				
				driver.close();
		
	}
	public void megajackpot_WithLoggedInUser_NegativeCaseModule() throws Exception {
	
				//Mega jackpot button click
				driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
				Thread.sleep(3000);
				
				//Play now button
				driver.findElement(By.xpath("//button[text()='Play Now']")).click();
				Thread.sleep(3000);
				
				//Bonus popup - clicking "No" option
				Pattern bonusNooption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
				scrn.wait(bonusNooption,50);
				scrn.click();
				Thread.sleep(4000);
	
			///////////Normal Ball Numbers Section///////////	
			
			//Manually - Checking whether user can select more than 5 ball numbers 
				
				//Number - 1 
				WebElement num1clk = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
				num1clk.click();
				Thread.sleep(2000);
				
				//Number - 2
				WebElement num2clk = driver.findElement(By.xpath("(//div[text()='2'])[1]"));
				num2clk.click();
				Thread.sleep(2000);
				
				//Number - 3
				WebElement num3clk = driver.findElement(By.xpath("(//div[text()='3'])[1]"));
				num3clk.click();
				Thread.sleep(2000);
				
				//Number - 4
				WebElement num4clk = driver.findElement(By.xpath("(//div[text()='4'])[1]"));
				num4clk.click();
				Thread.sleep(2000);
				
				//Number - 5
				WebElement num5clk = driver.findElement(By.xpath("(//div[text()='5'])[1]"));
				num5clk.click();
				Thread.sleep(2000);
				
				//Number - 7 
				Pattern ptrn1=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - 7 number.png");
				scrn.wait(ptrn1,50);
				scrn.click();
				Thread.sleep(3000);
				
				//Manually - Trying to select chip value only by selecting 5 numbers(Chip value - 2)
				
				//1st Chip 
				WebElement firstchip = driver.findElement(By.xpath("(//div[@class='amount-style'])[1]"));
				firstchip.click();
				Thread.sleep(3000);
				
				//Scrolling to buy ticket button (your lucky numbers text)
				
				WebElement scrolldown = driver.findElement(By.xpath("(//h6[text()='Your Lucky Numbers'])[2]"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
				
			//Manually - Trying to play game only by selecting 5 numbers(Play now btn clk)
				
				//Buy Ticket btn clk
				Pattern ptrn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega - buy ticket button.png");
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Scrolling up in prize chart popup
				WebElement scrollup = driver.findElement(By.xpath("//h3[text()='Lotto60 Mega Jackpot']"));
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);
				
			//Checking whether reset pick button is clickable without selecting any balls
				
				//Number - 1 
				js.executeScript("arguments[0].click()", num1clk);
				Thread.sleep(2000);
					
				//Number - 2
				js.executeScript("arguments[0].click()", num2clk);
				Thread.sleep(2000);
						
				//Number - 3
				js.executeScript("arguments[0].click()", num3clk);
				Thread.sleep(2000);
						
				//Number - 4
				js.executeScript("arguments[0].click()", num4clk);
				Thread.sleep(2000);
						
				//Number - 5
				js.executeScript("arguments[0].click()", num5clk);
				Thread.sleep(2000);
				
				//Trying to click reset pick button
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled reset pick button.png");
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(4000);
						
				//Quick Pick button - Checking whether user can select more than 5 ball numbers 		
				
				//Quick pick btn clk
				Thread.sleep(2000);
				Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
					
				//Trying to click more than 5 number (Number - 7) 
					
				scrn.wait(ptrn1,50);
				scrn.click();
				Thread.sleep(3000);
						
			//Trying to play game without selecting chip value
						
				//Scrolling to buy ticket button (your lucky numbers text)
						
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
						
				//Buy Ticket btn clk
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);
						
				//Scrolling up in prize chart popup
						
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);	
						
				//Reset pick button click
						
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(2000);
						
			///////////Golden Ball Numbers Section///////////	
								
				//Manually - trying to select more than 1 golden ball number
						
					String jsStyle = "'3px solid red'";
					String jsStyle2="'none'";
					
					//Number - 1 
					WebElement goldennum1clk = driver.findElement(By.xpath("(//div[text()='1'])[2]"));
					goldennum1clk.click();
					Thread.sleep(2000);
					
					//Number - 2 
					WebElement goldennum2clk = driver.findElement(By.xpath("(//div[text()='2'])[2]"));
					
					js.executeScript("arguments[0].click()", goldennum2clk);
					Thread.sleep(3000);
					js.executeScript("arguments[0].style.border=" + jsStyle, goldennum2clk);
					Thread.sleep(3000);
					 
					js.executeScript("arguments[0].style.border=" + jsStyle2, goldennum2clk);
					Thread.sleep(2000);
							
				//Manually - Trying to select chip value only by selecting 1 Golden numbers(Chip value - 2)
						
				//2nd Chip 
				WebElement secondchip = driver.findElement(By.xpath("(//div[@class='amount-style'])[2]"));
				secondchip.click();
				Thread.sleep(3000);
						
				//Manually - Trying to play game only by selecting 1 Golden numbers(Play now btn clk)
						
				//Scrolling to buy ticket button (your lucky numbers text)
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
						
				//Buy Ticket btn clk
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);
						
				//Scrolling up in prize chart popup
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);
								
				//Manually - trying to click disabled reset pick button
						
				//Number - 1 
				goldennum1clk.click();
				Thread.sleep(2000);
						
			//Reset pick button clk
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
				
				//Quick pick - tryting to select more than 1 golden number
						
				//Quick pick button clk
						
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(3000);
						
				//Number - 2 
					
				js.executeScript("arguments[0].click()", goldennum2clk);
				Thread.sleep(3000);
				js.executeScript("arguments[0].style.border=" + jsStyle, goldennum2clk);
				Thread.sleep(3000);
				
				js.executeScript("arguments[0].style.border=" + jsStyle2, goldennum2clk);
				Thread.sleep(2000);
					    
				//Reset pick button click
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
						
			///////////Chip value Section///////////
						
				//Trying to select all disabled chip value
						
				//1st Chip 
				firstchip.click();
				Thread.sleep(3000);
						
				js.executeScript("arguments[0].style.border=" + jsStyle,firstchip );
				Thread.sleep(3000);
						 
				js.executeScript("arguments[0].style.border=" + jsStyle2, firstchip);
				Thread.sleep(2000);
						
				//2nd Chip 
				secondchip.click();
				Thread.sleep(3000);
						
				js.executeScript("arguments[0].style.border=" + jsStyle,secondchip );
				Thread.sleep(3000);
					 
				js.executeScript("arguments[0].style.border=" + jsStyle2, secondchip);
				Thread.sleep(2000);
						
				//3rd Chip 
				WebElement thirdchip = driver.findElement(By.xpath("(//div[@class='amount-style'])[3]"));
				thirdchip.click();
				Thread.sleep(3000);
					
				js.executeScript("arguments[0].style.border=" + jsStyle,thirdchip );
				Thread.sleep(3000);
						 
				js.executeScript("arguments[0].style.border=" + jsStyle2, thirdchip);
				Thread.sleep(2000);
							
				//4th Chip 
				WebElement fourthchip = driver.findElement(By.xpath("(//div[@class='amount-style'])[4]"));
				fourthchip.click();
				Thread.sleep(3000);
						
				js.executeScript("arguments[0].style.border=" + jsStyle,fourthchip );
				Thread.sleep(3000);
						 
				js.executeScript("arguments[0].style.border=" + jsStyle2, fourthchip);
				Thread.sleep(2000);
					
		///////// Mega raffle section //////////
						
				//Mousehovering on Mega raffle info icon
				WebElement raffleinfoicon = driver.findElement(By.xpath("//*[local-name()='svg' and @stroke='currentColor']"));
				a.moveToElement(raffleinfoicon).perform();
				Thread.sleep(2000);
						
				//"No" option click in raffle
				WebElement rafflenooption = driver.findElement(By.xpath("(//input[@name='radio-button-demo'])[2]"));
				rafflenooption.click();
				Thread.sleep(2000);
					
				//Scrolling to buy ticket button (your lucky numbers text)
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
						
				//Buy Ticket btn clk
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);
						
				//Scrolling up in prize chart popup
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);
				
				//"Yes" option click in raffle		
				WebElement raffleyesoption = driver.findElement(By.xpath("(//input[@name='radio-button-demo'])[1]"));
				raffleyesoption.click();
				Thread.sleep(2000);
						
				//Scrolling to buy ticket button (your lucky numbers text)
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
						
				//Buy Ticket btn clk
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);
						
				//Scrolling up in prize chart popup
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);
						
			//Trying to play game only with raffle & normal balls	
				
				//Number - 1 	
				num1clk.click();
				Thread.sleep(2000);
						
				//Number - 2
				num2clk.click();
				Thread.sleep(2000);
						
				//Number - 3
				num3clk.click();
				Thread.sleep(2000);
						
				//Number - 4
				num4clk.click();
				Thread.sleep(2000);
				
				//Number - 5
				num5clk.click();
				Thread.sleep(2000);
						
				//3rd Chip 
				thirdchip.click();
				Thread.sleep(3000);
						
				//Scrolling to buy ticket button (your lucky numbers text)
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
						
				//Buy Ticket btn clk
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);
						
				//Scrolling up in prize chart popup
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);
						
				//Reset pick button click
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
						
				//Trying to play game only with raffle & golden balls
				goldennum1clk.click();
				Thread.sleep(2000);
						
				//4th Chip 
				fourthchip.click();
				Thread.sleep(3000);

				//"Yes" option click in raffle
				raffleyesoption.click();
				Thread.sleep(2000);
						
				//Scrolling to buy ticket button (your lucky numbers text)	
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
				
				//Buy Ticket btn clk
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);
						
				//Scrolling up in prize chart popup
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);
									
				//////Buy Ticket section/////////////
					
				//Reset pick button click
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
						
				//Trying to play game without selecting any balls or chips	
				
				//Scrolling to buy ticket button (your lucky numbers text)
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
						
				//Buy Ticket btn clk
				scrn.wait(ptrn3,50);
				scrn.click();
				Thread.sleep(4000);
						
				//Scrolling up in prize chart popup	
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);				
						
			//Trying to play only mega jackpot game without any sufficient funds
						
				//Quick pick button clk	
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
						
				//1st Chip 
				firstchip.click();
				Thread.sleep(3000);
						
				//"Yes" option click in raffle
				raffleyesoption.click();
				Thread.sleep(2000);
						
				//Scrolling to buy ticket button (your lucky numbers text)
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
						
				//Buy Ticket btn clk
				WebElement buyticketbtn = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
				buyticketbtn.click();
				Thread.sleep(2000);
						
				//Scrolling up in prize chart popup
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(3000);
			
				//Clicking Lotto logo
				WebElement lottologoclk = driver.findElement(By.xpath("//img[@class=' cursor-pointer']"));
				lottologoclk.click();
				Thread.sleep(3000);
						
				//Mega jackpot button click
				driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
				Thread.sleep(3000);
						
			//Trying to play raffle game with bonus	
		
				//Play now button
				driver.findElement(By.xpath("//button[text()='Play Now']")).click();
				Thread.sleep(3000);
						
				//Bonus popup - clicking "Yes" option
				Pattern bonusYesoption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\bonus popup - yes option.png");
				scrn.wait(bonusYesoption,50);
				scrn.click();
				Thread.sleep(4000);	
				
				//Quick pick button clk
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
			
				//Trying to hover & click on raffle section with bonus
						
				//Mega Raffle info icon click
				Pattern raffleinfoiconpic=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega raffle info icon.png");
				scrn.wait(raffleinfoiconpic,50);
				scrn.click();
				Thread.sleep(2000);
						
				//Mega raffle - yes option click
				Pattern raffleYesoptionpic=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega raffle - yes option .png");
				scrn.wait(raffleYesoptionpic,50);
				scrn.click();
				Thread.sleep(2000);
						
				//Mega raffle - No option click
				Pattern raffleNooptionpic=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega raffle - no option.png");
				scrn.wait(raffleNooptionpic,50);
				scrn.click();
				Thread.sleep(2000);
						
				//Reset pick button click
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
	
			//Trying to hover & click on raffle section with bonus after resetting
						
				//Mega Raffle info icon click
				scrn.wait(raffleinfoiconpic,50);
				scrn.click();
				Thread.sleep(2000);
						
				//Mega raffle - yes option click
				scrn.wait(raffleYesoptionpic,50);
				scrn.click();
				Thread.sleep(2000);
						
				//Mega raffle - No option click
				scrn.wait(raffleNooptionpic,50);
				scrn.click();
				Thread.sleep(3000);
					
				//Clicking Lotto logo
				WebElement lottologoclk2 = driver.findElement(By.xpath("//img[@class=' cursor-pointer']"));
				lottologoclk2.click();
				Thread.sleep(3000);
						
				//Mega jackpot button click
				driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
				Thread.sleep(3000);
	
				//Play now button
				driver.findElement(By.xpath("//button[text()='Play Now']")).click();
				Thread.sleep(3000);
						
				//Bonus popup - clicking "Yes" option
				scrn.wait(bonusYesoption,50);
				scrn.click();
				Thread.sleep(4000);	
						
				//Quick pick button clk
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
						
				//2nd Chip 
				WebElement secondchip2 = driver.findElement(By.xpath("(//div[@class='amount-style'])[2]"));
				secondchip2.click();
				Thread.sleep(3000);
						
				//Scrolling to buy ticket button (your lucky numbers text)
						
				WebElement scrolldown2 = driver.findElement(By.xpath("(//h6[text()='Your Lucky Numbers'])[2]"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown2);
				Thread.sleep(2000);
						
				//Buy Ticket btn clk
				WebElement buyticketbtn2 = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
				buyticketbtn2.click();
				Thread.sleep(2000);
						
				//Scrolling up in prize chart popup
				WebElement scrollup2 = driver.findElement(By.xpath("//h3[text()='Lotto60 Mega Jackpot']"));
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup2);
				Thread.sleep(3000);
						
					System.out.println("***********Insufficient Funds********** "); 
					
}
	public void megajackpot_WithLoggedInUser_PositiveCaseModule() throws Exception {
		
				//Mega jackpot button click
			
				driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
				Thread.sleep(3000);
				
				//Play now button
				driver.findElement(By.xpath("//button[text()='Play Now']")).click();
				Thread.sleep(3000);
				
				//Bonus popup - clicking "No" option
				Pattern bonusNooption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
				scrn.wait(bonusNooption,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Manually - Selecting all normal ball numbers
				
				//Number - 1 
				WebElement num1clk = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
				num1clk.click();
				Thread.sleep(2000);
				num1clk.click();
				Thread.sleep(2000);
				
				//Number - 5
				WebElement num5clk = driver.findElement(By.xpath("(//div[text()='5'])[1]"));
				num5clk.click();
				Thread.sleep(2000);
				num5clk.click();
				Thread.sleep(2000);
				
				//Number - 10
				WebElement num10clk = driver.findElement(By.xpath("(//div[text()='10'])[1]"));
				num10clk.click();
				Thread.sleep(2000);
				num10clk.click();
				Thread.sleep(2000);
				
				//Number - 11
				WebElement num11clk = driver.findElement(By.xpath("(//div[text()='11'])[1]"));
				num11clk.click();
				Thread.sleep(2000);
				num11clk.click();
				Thread.sleep(2000);
			
				//Number - 15
				WebElement num15clk = driver.findElement(By.xpath("(//div[text()='15'])[1]"));
				num15clk.click();
				Thread.sleep(2000);
				num15clk.click();
				Thread.sleep(2000);
				
				//Number - 20
				WebElement num20clk = driver.findElement(By.xpath("(//div[text()='20'])[1]"));
				num20clk.click();
				Thread.sleep(2000);
				num20clk.click();
				Thread.sleep(2000);
				
				//Number - 25
				WebElement num25clk = driver.findElement(By.xpath("(//div[text()='25'])[1]"));
				num25clk.click();
				Thread.sleep(2000);
				num25clk.click();
				Thread.sleep(2000);
				
				//Number - 30
				WebElement num30clk = driver.findElement(By.xpath("(//div[text()='30'])[1]"));
				num30clk.click();
				Thread.sleep(2000);
				num30clk.click();
				Thread.sleep(2000);
				
				//Number - 31
				WebElement num31clk = driver.findElement(By.xpath("(//div[text()='31'])[1]"));
				num31clk.click();
				Thread.sleep(2000);
				num31clk.click();
				Thread.sleep(2000);
				
				//Number - 35
				WebElement num35clk = driver.findElement(By.xpath("(//div[text()='35'])[1]"));
				num35clk.click();
				Thread.sleep(2000);
				num35clk.click();
				Thread.sleep(2000);
				
				//Number - 40
				WebElement num40clk = driver.findElement(By.xpath("(//div[text()='40'])[1]"));
				num40clk.click();
				Thread.sleep(2000);
				num40clk.click();
				Thread.sleep(2000);
				
				//Number - 41
				WebElement num41clk = driver.findElement(By.xpath("(//div[text()='41'])[1]"));
				num41clk.click();
				Thread.sleep(2000);
				num41clk.click();
				Thread.sleep(2000);
				
				//Number - 45
				WebElement num45clk = driver.findElement(By.xpath("(//div[text()='45'])[1]"));
				num45clk.click();
				Thread.sleep(2000);
				num45clk.click();
				Thread.sleep(2000);
				
				//Number - 49
				WebElement num49clk = driver.findElement(By.xpath("(//div[text()='49'])[1]"));
				num49clk.click();
				Thread.sleep(2000);
				num49clk.click();
				Thread.sleep(2000);		
			
				//Quick pick - clicking multiple times
				
				//Quick pick btn clk
				
				for (int i = 0; i <=2; i++) {
					
					Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
					scrn.wait(ptrn5,50);
					scrn.click();
					Thread.sleep(2000);
	
					
				}
				System.out.println("*******Quick pick clicked different normal ball numbers*******");
				
				//reset pick clk
				Pattern ptrn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - disabled reset pick button.png");
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(4000);
						
				//Manually - Selecting all golden ball numbers
				
				//Number - 1 
				WebElement goldennum1clk = driver.findElement(By.xpath("(//div[text()='1'])[2]"));
				goldennum1clk.click();
				Thread.sleep(2000);
				goldennum1clk.click();
				Thread.sleep(2000);
			
				//Number - 2 
				WebElement goldennum2clk = driver.findElement(By.xpath("(//div[text()='2'])[2]"));
				goldennum2clk.click();
				Thread.sleep(2000);
				goldennum2clk.click();
				Thread.sleep(2000);
				
				//Number - 3 
				WebElement goldennum3clk = driver.findElement(By.xpath("(//div[text()='3'])[2]"));
				goldennum3clk.click();
				Thread.sleep(2000);
				goldennum3clk.click();
				Thread.sleep(2000); 
				
				//Number - 4 
				WebElement goldennum4clk = driver.findElement(By.xpath("(//div[text()='4'])[2]"));
				goldennum4clk.click();
				Thread.sleep(2000);
				goldennum4clk.click();
				Thread.sleep(2000); 
				
				//Number - 5 
				WebElement goldennum5clk = driver.findElement(By.xpath("(//div[text()='5'])[2]"));
				goldennum5clk.click();
				Thread.sleep(2000);
				goldennum5clk.click();
				Thread.sleep(2000); 
			
		//Quick pick - clicking multiple times
				
				//Quick pick btn clk
				
				for (int i = 0; i <=2; i++) {
					
					Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
					scrn.wait(ptrn5,50);
					scrn.click();
					Thread.sleep(2000);
			
				}
				System.out.println("*******Quick pick clicked different golden ball numbers*******");
				
				//reset pick clk
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(4000);	
				
		///////////Your Lucky Numbers & Prize chart popup Section////////////
				
				//Prize Chart empty popup
				
				//Prize chart btn clk
				Pattern ptrn_prizechart=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Instant game - prize chart text.png");
				scrn.wait(ptrn_prizechart,50);
				scrn.click();
				Thread.sleep(2000);
	
				//Scrolling down in prize chart popup
				WebElement scrolldown = driver.findElement(By.xpath("//div[text()='Ticket Value']"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
				
				//Scrolling up in prize chart popup
				WebElement scrollup = driver.findElement(By.xpath("//h5[text()='Your Ticket & Prize Chart']"));
				js.executeScript("arguments[0].scrollIntoView(false)", scrollup);
				Thread.sleep(2000);
				
				//Closing the popup
				driver.findElement(By.xpath("//img[@class='cursor-pointer']")).click();
				Thread.sleep(4000);
				
		//Prize Chart popup with few selected ball numbers
		
				//Normal ball Numbers pick 
			
				//Number - 8 
				WebElement num8clk = driver.findElement(By.xpath("(//div[text()='8'])[1]"));
				num8clk.click();
				Thread.sleep(2000);
		
				//Number - 24
				WebElement num24clk = driver.findElement(By.xpath("(//div[text()='24'])[1]"));
				num24clk.click();
				Thread.sleep(2000);
			
				//Number - 42
				WebElement num42clk = driver.findElement(By.xpath("(//div[text()='42'])[1]"));
				num42clk.click();
				Thread.sleep(2000);
			
				//Golden ball Numbers pick 
				
				//Number - 1 
				WebElement goldennum1clk2 = driver.findElement(By.xpath("(//div[text()='1'])[2]"));
				goldennum1clk2.click();
				Thread.sleep(2000);
	
				//Prize chart btn clk
				scrn.wait(ptrn_prizechart,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Closing the popup
				driver.findElement(By.xpath("//img[@class='cursor-pointer']")).click();
				Thread.sleep(4000);
				
				//Reset pick button click
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
				
			//Prize Chart popup with all ball numbers
				
				//Quick pick button clk
				Pattern ptrn5=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Prize chart btn clk
				scrn.wait(ptrn_prizechart,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Closing the popup
				driver.findElement(By.xpath("//img[@class='cursor-pointer']")).click();
				Thread.sleep(4000);
				
				//Reset pick button click
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(3000);
				
			//Prize Chart popup with selected ticket value
				
				//Quick pick button clk
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
				
				//1st Ticket value
				WebElement firstticketvalue = driver.findElement(By.xpath("(//div[@class='amount-style'])[1]"));
				firstticketvalue.click();
				Thread.sleep(3000);
				
				//Prize chart btn clk
				scrn.wait(ptrn_prizechart,50);
				scrn.click();
				Thread.sleep(2000);
				
				//Closing the popup
				driver.findElement(By.xpath("//img[@class='cursor-pointer']")).click();
				Thread.sleep(4000);
				
				//reset pick clk
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(4000);	
	
	//Selecting all the chip values

			//Quick pick btn clk
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
				
			//Ticket Values 
				
				//1st Ticket value
				firstticketvalue.click();
				Thread.sleep(3000);
				
				//2nd Ticket value
				WebElement secondticketvalue = driver.findElement(By.xpath("(//div[@class='amount-style'])[2]"));
				secondticketvalue.click();
				Thread.sleep(3000);
				
				//3rd Ticket value
				WebElement thirdticketvalue = driver.findElement(By.xpath("(//div[@class='amount-style'])[3]"));
				thirdticketvalue.click();
				Thread.sleep(3000);
				
				//4th Ticket value
				WebElement fourthticketvalue = driver.findElement(By.xpath("(//div[@class='amount-style'])[4]"));
				fourthticketvalue.click();
				Thread.sleep(3000);
			
				System.out.println("*******All Chips value are selectable********");
				
			//Mega raffle section
				
				//"Yes" option click in raffle
				WebElement raffleYesoption = driver.findElement(By.xpath("(//input[@name='radio-button-demo'])[1]"));
				raffleYesoption.click();
				Thread.sleep(2000);
				
				//"No" option click in raffle
				WebElement raffleNooption = driver.findElement(By.xpath("(//input[@name='radio-button-demo'])[2]"));
				raffleNooption.click();
				Thread.sleep(2000);
				
				//reset pick clk
				scrn.wait(ptrn4,50);
				scrn.click();
				Thread.sleep(4000);	
				
			//Quick Play - Playing game without raffle
				
				//Quick pick btn clk
				scrn.wait(ptrn5,50);
				scrn.click();
				Thread.sleep(2000);
				
			//Ticket Values 
				
				//1st Ticket value
				firstticketvalue.click();
				Thread.sleep(3000);
				
				//Mega raffle option - "No" option click in raffle				
				raffleNooption.click();
				Thread.sleep(2000);
				
				//Scrolling to buy ticket button (your lucky numbers text)
				WebElement scrolldown2 = driver.findElement(By.xpath("(//h6[text()='Your Lucky Numbers'])[2]"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown2);
				Thread.sleep(2000);
				
				//Buy Ticket btn clk
				WebElement buyticketbtn = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
				buyticketbtn.click();
				Thread.sleep(2000);
				
				//Ok button click
				driver.findElement(By.xpath("//a[text()='OK']")).click();
				Thread.sleep(2000);
				
				//Quick Play - Playing game with raffle
				
				//Mega jackpot button click
				driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
				Thread.sleep(3000);
				
				//Play now button
				driver.findElement(By.xpath("//button[text()='Play Now']")).click();
				Thread.sleep(3000);
				
				//Bonus popup - clicking "No" option
				scrn.wait(bonusNooption,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Manually - Selecting all normal ball numbers
				
				//Number - 1 
				WebElement num1clk2 = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
				num1clk2.click();
				Thread.sleep(2000);
				
				//Number - 15
				WebElement num12clk = driver.findElement(By.xpath("(//div[text()='12'])[1]"));
				num12clk.click();
				Thread.sleep(2000);
			
				//Number - 22
				WebElement num22clk = driver.findElement(By.xpath("(//div[text()='22'])[1]"));
				num22clk.click();
				Thread.sleep(2000);
				
				//Number - 33
				WebElement num33clk = driver.findElement(By.xpath("(//div[text()='33'])[1]"));
				num33clk.click();
				Thread.sleep(2000);
		
				//Number - 44
				WebElement num44clk = driver.findElement(By.xpath("(//div[text()='44'])[1]"));
				num44clk.click();
				Thread.sleep(2000);
				
				//Manually - Selecting all golden ball numbers
				
				//Number - 1 
				WebElement goldennum3clk2 = driver.findElement(By.xpath("(//div[text()='3'])[2]"));
				goldennum3clk2.click();
				Thread.sleep(2000);
				
				//2nd Ticket value
				WebElement secondticketvalue2 = driver.findElement(By.xpath("(//div[@class='amount-style'])[2]"));
				secondticketvalue2.click();
				Thread.sleep(3000);
				
				//Mega raffle option - "Yes" option click in raffle				
				WebElement raffleYesoption2 = driver.findElement(By.xpath("(//input[@name='radio-button-demo'])[1]"));
				raffleYesoption2.click();
				Thread.sleep(2000);
				
				//Scrolling to buy ticket button (your lucky numbers text)
				
				WebElement scrolldown3 = driver.findElement(By.xpath("(//h6[text()='Your Lucky Numbers'])[2]"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown3);
				Thread.sleep(2000);
				
				//Buy Ticket btn clk
				WebElement buyticketbtn2 = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
				buyticketbtn2.click();
				Thread.sleep(2000);
				
				//Ok button click
				driver.findElement(By.xpath("//a[text()='OK']")).click();
				Thread.sleep(2000);
	
}
	
	public void megaJackpot_WithLoggedInUser_Bonus() throws Exception {
		
				//Mega jackpot button click
				driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
				Thread.sleep(3000);
				
				//Play now button
				driver.findElement(By.xpath("//button[text()='Play Now']")).click();
				Thread.sleep(3000);
				
				//Bonus popup - clicking "Yes" option
				Pattern bonusYesoption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\bonus popup - yes option.png");
				scrn.wait(bonusYesoption,50);
				scrn.click();
				Thread.sleep(4000);
				
		//Quick Play - Playing game without raffle
				
				//Quick pick btn clk
				Pattern quickpickbtn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(quickpickbtn,50);
				scrn.click();
				Thread.sleep(2000);
				
			//Ticket Values 
				
				//1st Ticket value
				WebElement firstticketvalue = driver.findElement(By.xpath("(//div[@class='amount-style'])[1]"));
				firstticketvalue.click();
				Thread.sleep(3000);
				
				//Mega raffle option - "Yes" option click in raffle	
				Pattern raffleYesoptionpic=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega raffle - yes option .png");
				scrn.wait(raffleYesoptionpic,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Mega raffle option - "No" option click in raffle	
				Pattern raffleNooptionpic=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\mega raffle - no option.png");
				scrn.wait(raffleNooptionpic,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Scrolling to buy ticket button (your lucky numbers text)
				WebElement scrolldown = driver.findElement(By.xpath("(//h6[text()='Your Lucky Numbers'])[2]"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
				
				//Buy Ticket btn clk
				WebElement buyticketbtn = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
				buyticketbtn.click();
				Thread.sleep(2000);
				
				//Ok button click
				driver.findElement(By.xpath("//a[text()='OK']")).click();
				Thread.sleep(2000);
				
	}
	
	public void megaActivityUpcomingDrawsModule() throws Exception {
		
			//For displaying empty records purpose
				
				//Profile Tab Module
				driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle']")).click();
				Thread.sleep(2000);
			
				//Activity Tab Module
				WebElement activitytab = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
				activitytab.click();
				Thread.sleep(4000);
				
				//Mega Jackpot - Upcoming draws tab click
				WebElement upcomingdrawstab = driver.findElement(By.xpath("(//h6[text()='Upcoming Draws'])[2]"));
				upcomingdrawstab.click();
				Thread.sleep(2000);
				
			//Displaying Mega Jackpot Game Activity without mega raffle
				
				//Mega jackpot button click
				driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
				Thread.sleep(3000);
				
				//Play now button
				driver.findElement(By.xpath("//button[text()='Play Now']")).click();
				Thread.sleep(3000);
				
				//Bonus popup - clicking "No" option
				Pattern bonusNooption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
				scrn.wait(bonusNooption,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Quick pick btn clk
				Pattern quickpickbtn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(quickpickbtn,50);
				scrn.click();
				Thread.sleep(2000);
				
				//1st Ticket value
				WebElement firstticketvalue = driver.findElement(By.xpath("(//div[@class='amount-style'])[1]"));
				firstticketvalue.click();
				Thread.sleep(3000);
				
				//"No" option click in raffle
				WebElement raffleNooption = driver.findElement(By.xpath("(//input[@name='radio-button-demo'])[2]"));
				raffleNooption.click();
				Thread.sleep(2000);
				
				//Scrolling to buy ticket button (your lucky numbers text)
				WebElement scrolldown = driver.findElement(By.xpath("(//h6[text()='Your Lucky Numbers'])[2]"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
				
				//Buy Ticket btn clk
				WebElement buyticketbtn = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
				buyticketbtn.click();
				Thread.sleep(2000);
				
				//Ok button click
				driver.findElement(By.xpath("//a[text()='OK']")).click();
				Thread.sleep(2000);
				
				//Profile picture click
				WebElement pfpclk = driver.findElement(By.xpath("//div[@id='avatar']"));
				pfpclk.click();
				Thread.sleep(2000);
				
				//Activity Tab Module
				WebElement activitytab2 = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
				activitytab2.click();
				Thread.sleep(4000);
				
				//Mega Jackpot - Upcoming draws tab click
				WebElement upcomingdrawstab2 = driver.findElement(By.xpath("(//h6[text()='Upcoming Draws'])[2]"));
				upcomingdrawstab2.click();
				Thread.sleep(2000);
				
				//View button click
				WebElement viewbtnclk = driver.findElement(By.xpath("(//small[text()='View'])[1]"));
				viewbtnclk.click();
				Thread.sleep(4000);
		
				//Closing popup
				WebElement closebtn = driver.findElement(By.xpath("//img[@class='cursor-pointer']"));
				closebtn.click();
				Thread.sleep(2000);
				
			////Displaying Mega Jackpot Game Activity with mega raffle
				
				//Mega jackpot button click
				driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
				Thread.sleep(3000);
				
				//Play now button
				driver.findElement(By.xpath("//button[text()='Play Now']")).click();
				Thread.sleep(3000);
				
				//Bonus popup - clicking "No" option
				Pattern bonusNooption2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
				scrn.wait(bonusNooption2,50);
				scrn.click();
				Thread.sleep(4000);
				
				//Quick pick btn clk
				Pattern quickpickbtn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(quickpickbtn2,50);
				scrn.click();
				Thread.sleep(2000);
				
				//2nd Ticket value
				WebElement secondticketvalue = driver.findElement(By.xpath("(//div[@class='amount-style'])[2]"));
				secondticketvalue.click();
				Thread.sleep(3000);
				
				//"Yes" option click in raffle
				WebElement raffleYesoption2 = driver.findElement(By.xpath("(//input[@name='radio-button-demo'])[1]"));
				raffleYesoption2.click();
				Thread.sleep(2000);
				
				//Scrolling to buy ticket button (your lucky numbers text)
				WebElement scrolldown2 = driver.findElement(By.xpath("(//h6[text()='Your Lucky Numbers'])[2]"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown2);
				Thread.sleep(2000);
				
				//Buy Ticket btn clk
				WebElement buyticketbtn2 = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
				buyticketbtn2.click();
				Thread.sleep(2000);
				
				//Ok button click
				driver.findElement(By.xpath("//a[text()='OK']")).click();
				Thread.sleep(2000);
				
				//Profile picture click
				WebElement pfpclk2 = driver.findElement(By.xpath("//div[@id='avatar']"));
				pfpclk2.click();
				Thread.sleep(2000);
				
				//Activity Tab Module
				WebElement activitytab3 = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
				activitytab3.click();
				Thread.sleep(4000);
				
				//Mega Jackpot - Upcoming draws tab click
				WebElement upcomingdrawstab3 = driver.findElement(By.xpath("(//h6[text()='Upcoming Draws'])[2]"));
				upcomingdrawstab3.click();
				Thread.sleep(2000);
				
				//View button click
				WebElement viewbtnclk2 = driver.findElement(By.xpath("(//small[text()='View'])[1]"));
				viewbtnclk2.click();
				Thread.sleep(4000);
	
				//Closing popup
				WebElement closebtn2 = driver.findElement(By.xpath("//img[@class='cursor-pointer']"));
				closebtn2.click();
				Thread.sleep(2000);
				
			////Displaying Mega Jackpot Game Activity with Bonus
			
				//Mega jackpot button click
				driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
				Thread.sleep(3000);
					
				//Play now button
				driver.findElement(By.xpath("//button[text()='Play Now']")).click();
				Thread.sleep(3000);
					
				//Bonus popup - clicking "No" option
				Pattern bonusYesoption2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\bonus popup - yes option.png");
				scrn.wait(bonusYesoption2,50);
				scrn.click();
				Thread.sleep(4000);
					
				//Quick pick btn clk
				Pattern quickpickbtn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
				scrn.wait(quickpickbtn3,50);
				scrn.click();
				Thread.sleep(2000);
					
				//2nd Ticket value
				WebElement thirdticketvalue = driver.findElement(By.xpath("(//div[@class='amount-style'])[3]"));
				thirdticketvalue.click();
				Thread.sleep(3000);
					
				//Scrolling to buy ticket button (your lucky numbers text)
				WebElement scrolldown3 = driver.findElement(By.xpath("(//h6[text()='Your Lucky Numbers'])[2]"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown3);
				Thread.sleep(2000);
					
				//Buy Ticket btn clk
				WebElement buyticketbtn3 = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
				buyticketbtn3.click();
				Thread.sleep(2000);
					
				//Ok button click
				driver.findElement(By.xpath("//a[text()='OK']")).click();
				Thread.sleep(2000);
					
				//Profile picture click
				WebElement pfpclk3 = driver.findElement(By.xpath("//div[@id='avatar']"));
				pfpclk3.click();
				Thread.sleep(2000);
					
				//Activity Tab Module
				WebElement activitytab4 = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
				activitytab4.click();
				Thread.sleep(4000);
					
				//Mega Jackpot - Upcoming draws tab click
				WebElement upcomingdrawstab4 = driver.findElement(By.xpath("(//h6[text()='Upcoming Draws'])[2]"));
				upcomingdrawstab4.click();
				Thread.sleep(2000);
					
				//View button click
				WebElement viewbtnclk3 = driver.findElement(By.xpath("(//small[text()='View'])[1]"));
				viewbtnclk3.click();
				Thread.sleep(4000);
					
				//Closing popup
				WebElement closebtn3 = driver.findElement(By.xpath("//img[@class='cursor-pointer']"));
				closebtn3.click();
				Thread.sleep(2000);
			
			//////////// Pagination Section ////////////////
					
				for (int i = 0; i <=10; i++) {
				
					//Mega jackpot button click
							
					driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
					Thread.sleep(3000);
						
					//Play now button
					driver.findElement(By.xpath("//button[text()='Play Now']")).click();
					Thread.sleep(3000);
							
					//Bonus popup - clicking "No" option
					Pattern bonusNooption3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
					scrn.wait(bonusNooption3,50);
					scrn.click();
					Thread.sleep(4000);
							
					//Quick pick btn clk
					Pattern quickpickbtn4=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
					scrn.wait(quickpickbtn4,50);
					scrn.click();
					Thread.sleep(2000);
							
					//2nd Ticket value
					WebElement fourthticketvalue = driver.findElement(By.xpath("(//div[@class='amount-style'])[4]"));
					fourthticketvalue.click();
					Thread.sleep(3000);
							
					//"Yes" option click in raffle
					WebElement raffleYesoption3 = driver.findElement(By.xpath("(//input[@name='radio-button-demo'])[1]"));
					raffleYesoption3.click();
					Thread.sleep(2000);
							
					//Scrolling to buy ticket button (your lucky numbers text)
					WebElement scrolldown4 = driver.findElement(By.xpath("(//h6[text()='Your Lucky Numbers'])[2]"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown4);
					Thread.sleep(2000);
							
					//Buy Ticket btn clk
					WebElement buyticketbtn4 = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
					buyticketbtn4.click();
					Thread.sleep(2000);
							
					//Ok button click
					driver.findElement(By.xpath("//a[text()='OK']")).click();
					Thread.sleep(2000);
							
					}

					//Profile picture click
					WebElement pfpclk4 = driver.findElement(By.xpath("//div[@id='avatar']"));
					pfpclk4.click();
					Thread.sleep(2000);
					
					//Activity Tab Module
					WebElement activitytab5 = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
					activitytab5.click();
					Thread.sleep(4000);
					
					//Mega Jackpot - Upcoming draws tab click
					WebElement upcomingdrawstab5 = driver.findElement(By.xpath("(//h6[text()='Upcoming Draws'])[2]"));
					upcomingdrawstab5.click();
					Thread.sleep(2000);
							
	}
	
		public void megaActivityPastDrawsModule() throws Exception{
		
					//Profile picture click
					WebElement pfpclk4 = driver.findElement(By.xpath("//div[@id='avatar']"));
					pfpclk4.click();
					Thread.sleep(2000);
					
					//Activity Tab Module
					WebElement activitytab5 = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
					activitytab5.click();
					Thread.sleep(4000);
					
					//View btn clk
					WebElement viewbtnclk = driver.findElement(By.xpath("(//small[text()='View'])[1]"));
					viewbtnclk.click();
					Thread.sleep(4000);
					
					//Scrolling down in popup
					WebElement scrolldownpopup = driver.findElement(By.xpath("//h5[text()='Mega Lucky Prize Winners']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldownpopup);
					Thread.sleep(4000);
					
					//Scrolling up in popup
					WebElement scrolluppopup = driver.findElement(By.xpath("//h5[text()='Lotto60 Jackpot Ticket']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolluppopup);
					Thread.sleep(4000);
					
					//Closing popup
					WebElement closebtn = driver.findElement(By.xpath("//img[@class='cursor-pointer']"));
					closebtn.click();
					Thread.sleep(2000);
					
	}

		public void instantActivityModule() throws Exception{
		
			//For displaying empty records purpose
				
					//Profile Tab Module
					driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle']")).click();
					Thread.sleep(2000);
				
					//Activity Tab Module
					WebElement activitytab = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
					activitytab.click();
					Thread.sleep(4000);
					
					//Instant Jackpot tab click
					WebElement instanttabclk = driver.findElement(By.xpath("(//h6[text()='Instant Jackpot'])[2]"));
					instanttabclk.click();
					Thread.sleep(2000);
				
			//Activity Data without Bonus
					
					//Instant jackpot button click
					
					driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
					Thread.sleep(2000);
					
					//Bonus popup - clicking "No" option
					Pattern bonusNooption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
					scrn.wait(bonusNooption,50);
					scrn.click();
					Thread.sleep(4000);
				
					//Quick pick button clk
					Pattern quickpickbtn=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
					scrn.wait(quickpickbtn,50);
					scrn.click();
					Thread.sleep(2000);
					
					//1st Chip 
					WebElement firstchip = driver.findElement(By.xpath("//p[@class='chip-images-numbers-1']"));
					firstchip.click();
					Thread.sleep(3000);
					
					//Play now btn clk
					WebElement playnowbtnclk = driver.findElement(By.xpath("//button[text()='Play Now']"));
					playnowbtnclk.click();
					Thread.sleep(20000);
					
					//Close btn clk
					WebElement closebtnclk = driver.findElement(By.xpath("(//button[text()='Close'])[1]"));
					closebtnclk.click();
					Thread.sleep(2000);
					
					//Profile Tab Module
					driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle']")).click();
					Thread.sleep(2000);
				
					//Activity Tab Module
					WebElement activitytab2 = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
					activitytab2.click();
					Thread.sleep(4000);
					
					//Instant Jackpot tab click
					WebElement instanttabclk2 = driver.findElement(By.xpath("(//h6[text()='Instant Jackpot'])[2]"));
					instanttabclk2.click();
					Thread.sleep(2000);
					
					//View btn clk
					WebElement viewbtnclk = driver.findElement(By.xpath("(//small[text()='View'])[1]"));
					viewbtnclk.click();
					Thread.sleep(4000);
					
					//Closing popup
					WebElement closebtn = driver.findElement(By.xpath("//img[@class='cursor-pointer']"));
					closebtn.click();
					Thread.sleep(2000);
					
			//Activity Data with Bonus
					
					//Instant jackpot button click
					
					driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
					Thread.sleep(2000);
					
					//Bonus popup - clicking "No" option
					Pattern bonusYesoption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\bonus popup - yes option.png");
					scrn.wait(bonusYesoption,50);
					scrn.click();
					Thread.sleep(4000);
				
					//Quick pick button clk
					Pattern quickpickbtn2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
					scrn.wait(quickpickbtn2,50);
					scrn.click();
					Thread.sleep(2000);
					
					//2nd Chip 
					WebElement secondchip = driver.findElement(By.xpath("(//p[@class='chip-images-numbers'])[1]"));
					secondchip.click();
					Thread.sleep(3000);
					
					//Play now btn clk
					WebElement playnowbtnclk2 = driver.findElement(By.xpath("//button[text()='Play Now']"));
					playnowbtnclk2.click();
					Thread.sleep(20000);
					
					//Close btn clk
					WebElement closebtnclk2 = driver.findElement(By.xpath("(//button[text()='Close'])[1]"));
					closebtnclk2.click();
					Thread.sleep(2000);
					
					//Profile Tab Module
					driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle']")).click();
					Thread.sleep(2000);
				
					//Activity Tab Module
					WebElement activitytab3 = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
					activitytab3.click();
					Thread.sleep(4000);
					
					//Instant Jackpot tab click
					WebElement instanttabclk3 = driver.findElement(By.xpath("(//h6[text()='Instant Jackpot'])[2]"));
					instanttabclk3.click();
					Thread.sleep(2000);
					
					//View btn clk
					WebElement viewbtnclk2 = driver.findElement(By.xpath("(//small[text()='View'])[1]"));
					viewbtnclk2.click();
					Thread.sleep(4000);
					
					//Closing popup
					WebElement closebtn2 = driver.findElement(By.xpath("//img[@class='cursor-pointer']"));
					closebtn2.click();
					Thread.sleep(2000);	
				
				for (int i = 0; i <=10; i++) {
					
					//Instant jackpot button click
					driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
					Thread.sleep(2000);
					
					//Bonus popup - clicking "No" option
					Pattern bonusNooption2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
					scrn.wait(bonusNooption2,50);
					scrn.click();
					Thread.sleep(4000);
				
					//Quick pick button clk
					Pattern quickpickbtn3=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\game - quick pick button.png");
					scrn.wait(quickpickbtn3,50);
					scrn.click();
					Thread.sleep(2000);
					
					//3rd Chip 
					WebElement thirdchip = driver.findElement(By.xpath("(//p[@class='chip-images-numbers'])[2]"));
					thirdchip.click();
					Thread.sleep(3000);
					
					//Play now btn clk
					WebElement playnowbtnclk3 = driver.findElement(By.xpath("//button[text()='Play Now']"));
					playnowbtnclk3.click();
					Thread.sleep(20000);
					
					//Close btn clk
					WebElement closebtnclk3 = driver.findElement(By.xpath("(//button[text()='Close'])[1]"));
					closebtnclk3.click();
					Thread.sleep(2000);
		
				}
				
					//Profile Tab Module
					driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle']")).click();
					Thread.sleep(2000);
				
					//Activity Tab Module
					WebElement activitytab4 = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
					activitytab4.click();
					Thread.sleep(4000);
					
					//Instant Jackpot tab click
					WebElement instanttabclk4 = driver.findElement(By.xpath("(//h6[text()='Instant Jackpot'])[2]"));
					instanttabclk4.click();
					Thread.sleep(2000);
		
		}
		
		public void roulottoActivityModule() throws Exception{
			
			//For displaying empty records purpose
				
					//Profile Tab Module
					driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle']")).click();
					Thread.sleep(2000);
				
					//Activity Tab Module
					WebElement activitytab = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
					activitytab.click();
					Thread.sleep(4000);
					
					//Roulotto tab click
					WebElement roulottotabclk = driver.findElement(By.xpath("//h6[text()='Roulotto']"));
					roulottotabclk.click();
					Thread.sleep(2000);
					
				//Roulotto Game Activity without Bonus
					
					//Roulotto button click
					driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
					Thread.sleep(3000);
					
					//Bonus popup - clicking "Yes" option
					Pattern bonusNooption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
					scrn.wait(bonusNooption,50);
					scrn.click();
					Thread.sleep(4000);
					
					//Scrolling to chips value and buttons
					WebElement thirdballcombotext = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext);
					Thread.sleep(2000);
					
					//Chip value - 5
					WebElement chipvalue1 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[1]"));
					chipvalue1.click();
					Thread.sleep(3000);
					
					//Scrolling to roulotto title upwards
					WebElement roulottotitle = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
					js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle);
					Thread.sleep(4000);
					
				//Box -1
					
					//Exacto popup box 1
					WebElement exacto1stbox = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
					exacto1stbox.click();
					Thread.sleep(2000);
					
					//Exacto num - 1 clk
					WebElement ex_num1 = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
					ex_num1.click();
					Thread.sleep(2000);
					
					//Closing the Exacto popup 
					WebElement closeexactopopup1 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
					closeexactopopup1.click();
					Thread.sleep(2000);
					
					//Odd option
					WebElement oddoptionbox1 = driver.findElement(By.xpath("(//div[text()='Odd'])[1]"));
					oddoptionbox1.click();
					Thread.sleep(2000);
					
					//1-10 option
					WebElement option1to20box1 = driver.findElement(By.xpath("(//div[text()='1-10'])[1]"));
					option1to20box1.click();
					Thread.sleep(2000);
					
					//Red option
					WebElement redoptionbox1 = driver.findElement(By.xpath("(//div[text()='Red'])[1]"));
					redoptionbox1.click();
					Thread.sleep(2000);
					
					//1st single combo box
					WebElement singlebox1 = driver.findElement(By.xpath("(//small[text()='1'])[1]"));
					singlebox1.click();
					Thread.sleep(2000);
			
				//Box -2
					
					//Exacto popup box 2
					WebElement exacto1stbox2 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
					exacto1stbox2.click();
					Thread.sleep(2000);
					
					//Exacto num - 15 clk
					WebElement ex_num1box2 = driver.findElement(By.xpath("(//div[text()='15'])[1]"));
					ex_num1box2.click();
					Thread.sleep(2000);
					
					//Closing the Exacto popup 
					WebElement closeexactopopup2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[7]"));
					closeexactopopup2.click();
					Thread.sleep(2000);
					
					//Even option
					WebElement evenoptionbox2 = driver.findElement(By.xpath("(//div[text()='Even'])[2]"));
					evenoptionbox2.click();
					Thread.sleep(2000);
					
					//11-20 option
					WebElement option11to20box2 = driver.findElement(By.xpath("(//div[text()='11-20'])[2]"));
					option11to20box2.click();
					Thread.sleep(2000);
					
					//Black option
					WebElement blackoptionbox2 = driver.findElement(By.xpath("(//div[text()='Black'])[2]"));
					blackoptionbox2.click();
					Thread.sleep(2000);
					
					//2nd single combo box
					WebElement singlebox2 = driver.findElement(By.xpath("(//small[text()='2'])[1]"));
					singlebox2.click();
					Thread.sleep(2000);
		
				//Box -3
					
					//Exacto popup box 3
					WebElement exacto1stbox3 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
					exacto1stbox3.click();
					Thread.sleep(2000);
					
					//Exacto num - 15 clk
					WebElement ex_num1box3 = driver.findElement(By.xpath("(//div[text()='20'])[1]"));
					ex_num1box3.click();
					Thread.sleep(2000);
					
					//Closing the Exacto popup
					WebElement closeexactopopupbox3 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[12]"));
					closeexactopopupbox3.click();
					Thread.sleep(2000);
				
					//Odd option
					WebElement oddoptionbox3 = driver.findElement(By.xpath("(//div[text()='Odd'])[3]"));
					oddoptionbox3.click();
					Thread.sleep(2000);
					
					//1-10 option
					WebElement option1to20box3 = driver.findElement(By.xpath("(//div[text()='1-10'])[3]"));
					option1to20box3.click();
					Thread.sleep(2000);
					
					//Red option
					WebElement redoptionbox3 = driver.findElement(By.xpath("(//div[text()='Red'])[3]"));
					redoptionbox3.click();
					Thread.sleep(2000);
					
					//3rd single combo box
					WebElement singlebox32 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
					singlebox32.click();
					Thread.sleep(2000);
					
				//Box - 4
					
					//Exacto popup box 4
					WebElement exacto1stbox4 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
					exacto1stbox4.click();
					Thread.sleep(2000);
					
					//Exacto num - 15 clk
					WebElement ex_num1box4 = driver.findElement(By.xpath("(//div[text()='1'])[1]"));
					ex_num1box4.click();
					Thread.sleep(2000);
					
					//Closing the Exacto popup 
					WebElement closeexactopopup4 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[17]"));
					closeexactopopup4.click();
					Thread.sleep(2000);
					
					//Even option
					WebElement evenptionbox4 = driver.findElement(By.xpath("(//div[text()='Even'])[4]"));
					evenptionbox4.click();
					Thread.sleep(2000);
					
					//11-20 option
					WebElement option11to20box4 = driver.findElement(By.xpath("(//div[text()='11-20'])[4]"));
					option11to20box4.click();
					Thread.sleep(2000);
					
					//Black option
					WebElement blackoptionbox4 = driver.findElement(By.xpath("(//div[text()='Black'])[4]"));
					blackoptionbox4.click();
					Thread.sleep(2000);
					
					//4th single combo box
					WebElement singlebox4 = driver.findElement(By.xpath("(//small[text()='4'])[1]"));
					singlebox4.click();
					Thread.sleep(2000);
			
				//Box -5
					
					//Exacto popup box 3
					WebElement exacto1stbox5 = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
					exacto1stbox5.click();
					Thread.sleep(2000);
					
					//Exacto num - 15 clk
					WebElement ex_num1box5 = driver.findElement(By.xpath("(//div[text()='10'])[1]"));
					ex_num1box5.click();
					Thread.sleep(2000);
					
					//Closing the Exacto popup
					WebElement closeexactopupbox5 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[22]"));
					closeexactopupbox5.click();
					Thread.sleep(2000);
					
					//Odd option
					WebElement oddoptionbox5 = driver.findElement(By.xpath("(//div[text()='Odd'])[5]"));
					oddoptionbox5.click();
					Thread.sleep(2000);
					
					//1-10 option
					WebElement option1to20box5 = driver.findElement(By.xpath("(//div[text()='1-10'])[5]"));
					option1to20box5.click();
					Thread.sleep(2000);
					
					//Red option
					WebElement redoptionbox5 = driver.findElement(By.xpath("(//div[text()='Red'])[5]"));
					redoptionbox5.click();
					Thread.sleep(2000);
					
					//5th single combo box
					WebElement singlebox5 = driver.findElement(By.xpath("(//small[text()='5'])[1]"));
					singlebox5.click();
					Thread.sleep(2000);
							
					//Scrolling to chips value and buttons
					WebElement thirdballcombotext8 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext8 );
					Thread.sleep(4000);
					
					//Multi ball combo box
					WebElement multiballbox = driver.findElement(By.xpath("(//small[text()='Multi Ball Combo Play'])[1]"));
					multiballbox.click();
					Thread.sleep(2000);
					
					//Play now btn clk
					WebElement playnowbtnclk = driver.findElement(By.xpath("(//span[text()='Play Now'])[1]"));
					playnowbtnclk.click();
					Thread.sleep(2000);
					
					//Scrolling to roulotto title upwards
					WebElement roulottotitle2 = driver.findElement(By.xpath("//h4[text()='Your Roulotto Picks']"));
					js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle2);
					Thread.sleep(15000);
					
					//Scrolling to chips value and buttons
					WebElement thirdballcombotext2 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext2 );
					Thread.sleep(4000);
					
					//Close btn clk
					WebElement closebtnclk = driver.findElement(By.xpath("(//button[@type='button'])[8]"));
					closebtnclk.click();
					Thread.sleep(2000);
					
					//Profile Tab Module
					driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle']")).click();
					Thread.sleep(2000);
				
					//Activity Tab Module
					WebElement activitytab2 = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
					activitytab2.click();
					Thread.sleep(4000);
					
					//Roulotto tab click
					WebElement roulottotabclk2 = driver.findElement(By.xpath("//h6[text()='Roulotto']"));
					roulottotabclk2.click();
					Thread.sleep(2000);
					
					//View btn clk
					WebElement viewbtnclk = driver.findElement(By.xpath("(//small[text()='View'])[1]"));
					viewbtnclk.click();
					Thread.sleep(3000);
					
					//Scrolling down in results popup
					WebElement thirdballcombotext3 = driver.findElement(By.xpath("(//small[text()='3'])[2]"));
					js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext3 );
					Thread.sleep(4000);
					
					//Scrolling to close btn upwards
					WebElement oddtxtinpopup = driver.findElement(By.xpath("(//div[text()='Odd'])[5]"));
					js.executeScript("arguments[0].scrollIntoView(false)", oddtxtinpopup);
					Thread.sleep(2000);
					
					//Close btn clk
					WebElement closebtnclkpopup = driver.findElement(By.xpath("(//img[@class='cursor-pointer'])[2]"));
					a.click(closebtnclkpopup).perform();
					Thread.sleep(2000);
					
				//Roulotto Game Activity with Bonus
					
					//Roulotto button click
					driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
					Thread.sleep(3000);
					
					//Bonus popup - clicking "Yes" option
					Pattern bonusYesoption=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\bonus popup - yes option.png");
					scrn.wait(bonusYesoption,50);
					scrn.click();
					Thread.sleep(4000);
					
					//Scrolling to chips value and buttons
					WebElement thirdballcombotext4 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext4);
					Thread.sleep(2000);
					
					//Chip value - 10
					WebElement chipvalue2 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[2]"));
					chipvalue2.click();
					Thread.sleep(3000);
					
					//Scrolling to roulotto title upwards
					WebElement roulottotitle4 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
					js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle4);
					Thread.sleep(4000);
					
				//Box -1
					
					//Exacto popup box 1
					WebElement exacto1stboxwithbonus = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
					exacto1stboxwithbonus.click();
					Thread.sleep(2000);
					
					//Exacto num - 5 clk
					WebElement ex_num5 = driver.findElement(By.xpath("(//div[text()='5'])[1]"));
					ex_num5.click();
					Thread.sleep(2000);
					
					//Closing the Exacto popup 
					WebElement closeexactopopup1withbonus = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
					closeexactopopup1withbonus.click();
					Thread.sleep(2000);
					
					//Odd option
					WebElement oddoptionbox1withbonus = driver.findElement(By.xpath("(//div[text()='Odd'])[1]"));
					oddoptionbox1withbonus.click();
					Thread.sleep(2000);
				
					//Red option
					WebElement redoptionbox1withbonus = driver.findElement(By.xpath("(//div[text()='Red'])[1]"));
					redoptionbox1withbonus.click();
					Thread.sleep(2000);
					
					//1st single combo box
					WebElement singlebox1withbonus = driver.findElement(By.xpath("(//small[text()='1'])[1]"));
					singlebox1withbonus.click();
					Thread.sleep(2000);
			
				//Box -2
				
					//Even option
					WebElement evenoptionbox2withbonus = driver.findElement(By.xpath("(//div[text()='Even'])[2]"));
					evenoptionbox2withbonus.click();
					Thread.sleep(2000);
				
					//Scrolling to chips value and buttons
					WebElement thirdballcombotext5 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext5 );
					Thread.sleep(4000);
					
					//Multi ball combo box
					WebElement multiballbox2 = driver.findElement(By.xpath("(//small[text()='Multi Ball Combo Play'])[1]"));
					multiballbox2.click();
					Thread.sleep(2000);
					
					//Play now btn clk
					WebElement playnowbtnclk2 = driver.findElement(By.xpath("(//span[text()='Play Now'])[1]"));
					playnowbtnclk2.click();
					Thread.sleep(2000);
					
					//Scrolling to roulotto title upwards
					WebElement roulottotitle5 = driver.findElement(By.xpath("//h4[text()='Your Roulotto Picks']"));
					js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle5);
					Thread.sleep(15000);
					
					//Scrolling to chips value and buttons
					WebElement thirdballcombotext6 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext6 );
					Thread.sleep(4000);
					
					//Close btn clk
					WebElement closebtnclk2= driver.findElement(By.xpath("(//button[@type='button'])[8]"));
					closebtnclk2.click();
					Thread.sleep(2000);
					
					//Profile Tab Module
					driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle']")).click();
					Thread.sleep(2000);
				
					//Activity Tab Module
					WebElement activitytab3 = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
					activitytab3.click();
					Thread.sleep(4000);
					
					//Roulotto tab click
					WebElement roulottotabclk3 = driver.findElement(By.xpath("//h6[text()='Roulotto']"));
					roulottotabclk3.click();
					Thread.sleep(2000);
					
					//View btn clk
					WebElement viewbtnclk2 = driver.findElement(By.xpath("(//small[text()='View'])[1]"));
					viewbtnclk2.click();
					Thread.sleep(3000);
					
					//Scrolling down in results popup
					WebElement thirdballcombotext7 = driver.findElement(By.xpath("(//small[text()='3'])[2]"));
					js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext7  );
					Thread.sleep(4000);
					
					//Scrolling to close btn upwards
					WebElement oddtxtinpopup2 = driver.findElement(By.xpath("(//div[text()='Odd'])[5]"));
					js.executeScript("arguments[0].scrollIntoView(false)", oddtxtinpopup2);
					Thread.sleep(2000);
					
					//Close btn clk
					WebElement closebtnclkpopup2= driver.findElement(By.xpath("(//img[@class='cursor-pointer'])[2]"));
					a.click(closebtnclkpopup2).perform();
					Thread.sleep(2000);
						
				for (int i = 0; i <=10; i++) {
					
					//Roulotto Game Activity without Bonus
					
					//Roulotto button click
					driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
					Thread.sleep(3000);
					
					//Bonus popup - clicking "Yes" option
					Pattern bonusNooption2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Bonus popup - no option.png");
					scrn.wait(bonusNooption2,50);
					scrn.click();
					Thread.sleep(4000);
					
					//Scrolling to chips value and buttons
					WebElement thirdballcombotext9 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext9);
					Thread.sleep(2000);
					
					//Chip value - 15
					WebElement chipvalue3 = driver.findElement(By.xpath("(//p[@class='coin-img position-absolute center m-0'])[3]"));
					chipvalue3.click();
					Thread.sleep(3000);
					
					//Scrolling to roulotto title upwards
					WebElement roulottotitle6 = driver.findElement(By.xpath("//h3[text()='Roulotto']"));
					js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle6);
					Thread.sleep(4000);
					
				//Box -1
					
					//Exacto popup box 1
					WebElement exacto1stboxwithoutbonus = driver.findElement(By.xpath("(//h6[text()='Exacto'])[1]"));
					exacto1stboxwithoutbonus.click();
					Thread.sleep(2000);
					
					//Exacto num - 5 clk
					WebElement ex_num6 = driver.findElement(By.xpath("(//div[text()='6'])[1]"));
					ex_num6.click();
					Thread.sleep(2000);
					
					//Closing the Exacto popup 
					WebElement closeexactopopup1withoutbonus2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
					closeexactopopup1withoutbonus2.click();
					Thread.sleep(2000);
					
					//Odd option
					WebElement oddoptionbox1withoutbonus2 = driver.findElement(By.xpath("(//div[text()='Odd'])[1]"));
					oddoptionbox1withoutbonus2.click();
					Thread.sleep(2000);
				
					//Red option
					WebElement redoptionbox1withoutbonus = driver.findElement(By.xpath("(//div[text()='Red'])[1]"));
					redoptionbox1withoutbonus.click();
					Thread.sleep(2000);
					
					//1st single combo box
					WebElement singlebox1withoutbonus = driver.findElement(By.xpath("(//small[text()='1'])[1]"));
					singlebox1withoutbonus.click();
					Thread.sleep(2000);
			
				//Box -2
				
					//Even option
					WebElement evenoptionbox2withoutbonus = driver.findElement(By.xpath("(//div[text()='Even'])[2]"));
					evenoptionbox2withoutbonus.click();
					Thread.sleep(2000);
				
					//Scrolling to chips value and buttons
					WebElement thirdballcombotext10 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext10 );
					Thread.sleep(4000);
					
					//Multi ball combo box
					WebElement multiballbox3 = driver.findElement(By.xpath("(//small[text()='Multi Ball Combo Play'])[1]"));
					multiballbox3.click();
					Thread.sleep(2000);
					
					//Play now btn clk
					WebElement playnowbtnclk3 = driver.findElement(By.xpath("(//span[text()='Play Now'])[1]"));
					playnowbtnclk3.click();
					Thread.sleep(2000);
					
					//Scrolling to roulotto title upwards
					WebElement roulottotitle7 = driver.findElement(By.xpath("//h4[text()='Your Roulotto Picks']"));
					js.executeScript("arguments[0].scrollIntoView(false)", roulottotitle7);
					Thread.sleep(15000);
					
					//Scrolling to chips value and buttons
					WebElement thirdballcombotext11 = driver.findElement(By.xpath("(//small[text()='3'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)",thirdballcombotext11 );
					Thread.sleep(4000);
					
					//Close btn clk
					WebElement closebtnclk3= driver.findElement(By.xpath("(//button[@type='button'])[8]"));
					closebtnclk3.click();
					Thread.sleep(2000);
						
				}
				
					//Profile Tab Module
					driver.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circle']")).click();
					Thread.sleep(2000);
				
					//Activity Tab Module
					WebElement activitytab4 = driver.findElement(By.xpath("(//li[@role='menuitem'])[9]"));
					activitytab4.click();
					Thread.sleep(4000);
					
					//Roulotto tab click
					WebElement roulottotabclk4 = driver.findElement(By.xpath("//h6[text()='Roulotto']"));
					roulottotabclk4.click();
					Thread.sleep(2000);		
			
		}
		public void activityPaginationModule() throws Exception {
			
			////////Pagination Section////////////	
			
					//Scrolling down to pagination section
					WebElement viewbtnlastdata = driver.findElement(By.xpath("(//small[text()='View'])[19]"));
					js.executeScript("arguments[0].scrollIntoView(true)", viewbtnlastdata);
					Thread.sleep(2000);
							
				//Trying to click disabled previous button
					
					Pattern leftchveronbtnclk=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Calendar popup - left chevron button.png");
					scrn.wait(leftchveronbtnclk,50);
					scrn.click();
					Thread.sleep(3000);
						
					//Next button click
					WebElement nextbtnclk = driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[2]"));
					nextbtnclk.click();
					Thread.sleep(2000); 
						
					//Scrolling up
							
					//Roulotto Jackpot Past draws tab click
					WebElement activitytxt = driver.findElement(By.xpath("(//h3[text()='Activity'])[2]"));
					js.executeScript("arguments[0].scrollIntoView(false)",activitytxt );
					Thread.sleep(3000);
						
					//Scrolling down 
					WebElement rowstxt = driver.findElement(By.xpath("//p[text()='Rows per page:']"));
					js.executeScript("arguments[0].scrollIntoView(true)",rowstxt );
					Thread.sleep(3000);
								
					//Previous button click
					WebElement previousbtnclk = driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[1]"));
					previousbtnclk.click();
					Thread.sleep(3000); 
							
					//Scrolling up
					
					//Roulotto Past draws tab click
					WebElement activitytxt2 = driver.findElement(By.xpath("(//h3[text()='Activity'])[2]"));
					js.executeScript("arguments[0].scrollIntoView(false)",activitytxt2 );
					Thread.sleep(3000);
							
					//Scrolling down 
					WebElement rowstxt2 = driver.findElement(By.xpath("//p[text()='Rows per page:']"));
					js.executeScript("arguments[0].scrollIntoView(true)",rowstxt2 );
					Thread.sleep(3000);
						
				//////Pagination Filter Section/////
						
					//Filter button click
					WebElement filterbtnclk = driver.findElement(By.xpath("(//div[@role='button'])[1]"));
					filterbtnclk.click();
					Thread.sleep(2000);
						
					//25 option click
					WebElement option25clk = driver.findElement(By.xpath("(//li[@role='option'])[2]"));
					option25clk.click();
					Thread.sleep(2000);
						
					//Scrolling up
					//Roulotto Past draws tab click
					js.executeScript("arguments[0].scrollIntoView(false)",activitytxt2 );
					Thread.sleep(3000);
						
					//Scrolling down 
					WebElement rowstxt3 = driver.findElement(By.xpath("//p[text()='Rows per page:']"));
					js.executeScript("arguments[0].scrollIntoView(true)",rowstxt3 );
					Thread.sleep(2000);
						
					//Filter button click
					WebElement filterbtnclk2 = driver.findElement(By.xpath("(//div[@role='button'])[1]"));
					filterbtnclk2.click();
					Thread.sleep(2000);
						
					//50 option click
					WebElement option50clk = driver.findElement(By.xpath("(//li[@role='option'])[3]"));
					option50clk.click();
					Thread.sleep(2000);
						
					//Scrolling up
					//Roulotto Past draws tab click
					js.executeScript("arguments[0].scrollIntoView(false)",activitytxt2 );
					Thread.sleep(3000);
						
					//Scrolling down 
					WebElement rowstxt4 = driver.findElement(By.xpath("//p[text()='Rows per page:']"));
					js.executeScript("arguments[0].scrollIntoView(true)",rowstxt4 );
					Thread.sleep(2000);
						
					//Filter button click
					WebElement filterbtnclk3 = driver.findElement(By.xpath("(//div[@role='button'])[1]"));
					filterbtnclk3.click();
					Thread.sleep(2000);
						
					//100 option click
					WebElement option100clk = driver.findElement(By.xpath("(//li[@role='option'])[4]"));
					option100clk.click();
					Thread.sleep(2000);
						
					//Scrolling up
					//Roulotto Past draws tab click
					js.executeScript("arguments[0].scrollIntoView(false)",activitytxt2 );
					Thread.sleep(3000);
						
					//Scrolling down 
					WebElement rowstxt5 = driver.findElement(By.xpath("//p[text()='Rows per page:']"));
					js.executeScript("arguments[0].scrollIntoView(true)",rowstxt5 );
					Thread.sleep(2000);
				
					//Filter button click
					WebElement filterbtnclk4 = driver.findElement(By.xpath("(//div[@role='button'])[1]"));
					filterbtnclk4.click();
					Thread.sleep(2000);
						
					//10 option click
					WebElement option10clk = driver.findElement(By.xpath("(//li[@role='option'])[1]"));
					option10clk.click();
					Thread.sleep(2000); 
						
					//Scrolling up
					//Roulotto Past draws tab click
					js.executeScript("arguments[0].scrollIntoView(false)",activitytxt2 );
					Thread.sleep(3000);
						
					//Scrolling down 
					WebElement rowstxt6 = driver.findElement(By.xpath("//p[text()='Rows per page:']"));
					js.executeScript("arguments[0].scrollIntoView(true)",rowstxt6);
					Thread.sleep(2000);
						
					//Next button click
					WebElement nextbtnclk2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[2]"));
					nextbtnclk2.click();
					Thread.sleep(2000);
						
					//Scrolling up
					//Roulotto Past draws tab click
					js.executeScript("arguments[0].scrollIntoView(false)",activitytxt2 );
					Thread.sleep(3000);
						
					//Scrolling down 
					WebElement rowstxt7 = driver.findElement(By.xpath("//p[text()='Rows per page:']"));
					js.executeScript("arguments[0].scrollIntoView(true)",rowstxt7);
					Thread.sleep(2000);
						
					//Previous btn clk
					WebElement previousbtnclk2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @focusable='false'])[2]"));
					previousbtnclk2.click();
					Thread.sleep(2000);
						
					//Scrolling up
					//Roulotto Past draws tab click
					js.executeScript("arguments[0].scrollIntoView(false)",activitytxt2 );
					Thread.sleep(3000);
						
					//Scrolling down 
					WebElement rowstxt8 = driver.findElement(By.xpath("//p[text()='Rows per page:']"));
					js.executeScript("arguments[0].scrollIntoView(true)",rowstxt8);
					Thread.sleep(2000);
						
					//Next button click
					WebElement nextbtnclk3 = driver.findElement(By.xpath("(//*[local-name()='svg' and @class='MuiSvgIcon-root'])[2]"));
					nextbtnclk3.click();
					Thread.sleep(2000);
					
					//Filter button click
					WebElement filterbtnclk5 = driver.findElement(By.xpath("(//div[@role='button'])[1]"));
					filterbtnclk5.click();
					Thread.sleep(2000);
						
					//25 option click
					WebElement option25clk2 = driver.findElement(By.xpath("(//li[@role='option'])[2]"));
					option25clk2.click();
					Thread.sleep(2000);
						
					//Scrolling up
					//Roulotto Past draws tab click
					js.executeScript("arguments[0].scrollIntoView(false)",activitytxt2 );
					Thread.sleep(3000);
						
					//Scrolling down 
					WebElement rowstxt9 = driver.findElement(By.xpath("//p[text()='Rows per page:']"));
					js.executeScript("arguments[0].scrollIntoView(true)",rowstxt9 );
					Thread.sleep(2000);
				
					//Scrolling up
					//Roulotto Past draws tab click
					js.executeScript("arguments[0].scrollIntoView(false)",activitytxt2 );
					Thread.sleep(3000);

		}
		
			public void homepageModule() throws Exception {
			
			//////Last draw winning numbers section/////// 
				
					Thread.sleep(3000);
					//Scrolling to last draw section - mega jackpot grand prize text
					WebElement megajackpotprizetxt = driver.findElement(By.xpath("//p[text()='Mega Jackpot Grand Prize']"));
					js.executeScript("arguments[0].scrollIntoView(true)",megajackpotprizetxt );
					Thread.sleep(2000);
					
				//Last draw popup
					//View button clk
					WebElement viewbtnclk = driver.findElement(By.xpath("(//p[text()='View'])[1]"));
					viewbtnclk.click();
					Thread.sleep(3000);
					
					//Scrolling down in popup
					WebElement megaluckytxt = driver.findElement(By.xpath("//b[text()='Mega Lucky Prize Winners']"));
					js.executeScript("arguments[0].scrollIntoView(true)",megaluckytxt );
					Thread.sleep(4000);
					
					//Scrolling up in popup
					WebElement noofwinningstxt = driver.findElement(By.xpath("//p[text()='No of Winning Tickets']"));
					js.executeScript("arguments[0].scrollIntoView(true)",noofwinningstxt );
					Thread.sleep(4000);
					
					//Close btn clk
					WebElement closebtnclk = driver.findElement(By.xpath("//*[local-name()='svg' and @class='cursor-pointer']"));
					closebtnclk.click();
					Thread.sleep(2000);
					
				//Previous Draws section
					
					//scrolling to previous draws section
					WebElement previousdrawstxt = driver.findElement(By.xpath("//p[text()='Previous Draws']"));
					js.executeScript("arguments[0].scrollIntoView(true)", previousdrawstxt);
					Thread.sleep(4000);
					
				//Recent draws section
					
					//Recent draw popup
					//View button clk
					WebElement viewbtnclk2 = driver.findElement(By.xpath("(//p[text()='View'])[3]"));
					viewbtnclk2.click();
					Thread.sleep(3000);
					
					//Scrolling down in popup
					WebElement megaluckytxt2 = driver.findElement(By.xpath("//b[text()='Mega Lucky Prize Winners']"));
					js.executeScript("arguments[0].scrollIntoView(true)",megaluckytxt2 );
					Thread.sleep(4000);
					
					//Scrolling up in popup
					WebElement noofwinningstxt2 = driver.findElement(By.xpath("//p[text()='No of Winning Tickets']"));
					js.executeScript("arguments[0].scrollIntoView(true)",noofwinningstxt2 );
					Thread.sleep(4000);
					
					//Close btn clk
					WebElement closebtnclk2 = driver.findElement(By.xpath("//*[local-name()='svg' and @class='cursor-pointer']"));
					closebtnclk2.click();
					Thread.sleep(2000);
					
				/////Date Picker section/////
					
					//start date field box
					WebElement startdatebox = driver.findElement(By.xpath("(//input[@id='datetime-local'])[1]"));
					startdatebox.sendKeys(startdate);
					Thread.sleep(2000);
					
					//End date field box
					WebElement enddatebox = driver.findElement(By.xpath("(//input[@id='datetime-local'])[2]"));
					enddatebox.sendKeys(enddate);
					Thread.sleep(2000);
					
					//Search btn clk
					WebElement searchbtnclk = driver.findElement(By.xpath("//button[text()='Search']"));
					searchbtnclk.click();
					Thread.sleep(2000);
					
					//start date field box
					WebElement startdatebox2 = driver.findElement(By.xpath("(//input[@id='datetime-local'])[1]"));
					startdatebox2.sendKeys(startdate2);
					Thread.sleep(2000);
					
					//End date field box
					WebElement enddatebox2= driver.findElement(By.xpath("(//input[@id='datetime-local'])[2]"));
					enddatebox2.sendKeys(enddate2);
					Thread.sleep(2000);
					
					//Search btn clk
					WebElement searchbtnclk2 = driver.findElement(By.xpath("//button[text()='Search']"));
					searchbtnclk.click();
					Thread.sleep(2000);
					
					//Clear btn clk
					WebElement clearbtnclk = driver.findElement(By.xpath("//button[text()='Clear']"));
					clearbtnclk.click();
					Thread.sleep(2000);
					
					//start date click
					startdatebox.click();
					Thread.sleep(2000);
					
					r.keyPress(KeyEvent.VK_TAB);
					r.keyRelease(KeyEvent.VK_TAB);
					Thread.sleep(1000);
					
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(1000);
					
		}
		
			public void chatBoxModule() throws Exception {
				
					//Close button clk in chat box
					driver.findElement(By.xpath("//iframe[@title='Close message']")).click();
					Thread.sleep(2000);
					
					//Chat box btn clk
					driver.findElement(By.xpath("//iframe[@title='Button to launch messaging window']")).click();
					Thread.sleep(2000);
					
					//Switching to Frame
					int size = driver.findElements(By.tagName("iframe")).size();
					System.out.println(size);
					
					driver.switchTo().frame(1);
					Thread.sleep(4000);
					
					//Name field
					WebElement name = driver.findElement(By.xpath("//input[@data-garden-id='forms.input']"));
					name.sendKeys(namedata);
					Thread.sleep(2000);
					
					//Send btn clk
					WebElement sendbtn = driver.findElement(By.xpath("//span[text()='Send']"));
					sendbtn.click();
					Thread.sleep(4000);
					
					//Typing a message field
					WebElement msgfield = driver.findElement(By.xpath("(//textarea[@data-garden-id='forms.textarea'])[1]"));
					msgfield.sendKeys(querydata);
					Thread.sleep(2000);
					
					//Send icon clk
					WebElement sendicon = driver.findElement(By.xpath("(//*[local-name()='svg' and @data-garden-id='buttons.icon'])[4]"));
					sendicon.click();
					Thread.sleep(3000);
					
					//Attach Icon clk
					WebElement attachicon = driver.findElement(By.xpath("(//*[local-name()='svg' and @data-garden-id='buttons.icon'])[3]"));
					attachicon.click();
					Thread.sleep(2000);
					
					//Attaching Image
					Runtime.getRuntime().exec("D:\\Documents\\Documents\\Lotto - profile pic upload(spidey pic).exe");
					Thread.sleep(5000);
					System.out.println("****************Image attached successfully*************");
					
					//Attach Icon clk
					attachicon.click();
					Thread.sleep(2000);
					
					//Attaching Video
					Runtime.getRuntime().exec("D:\\Documents\\Documents\\Lotto - recorded video.exe");
					Thread.sleep(5000);
					System.out.println("****************Video attached successfully*************");
			
		}
		
		public void footerMegaJackpotNonLoggedInModule() throws Exception {
			
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(3000);
					
				// 1.What is mega jackpot option clk section
					driver.findElement(By.xpath("//li[text()='What is the Lotto60® Mega Jackpot?']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					WebElement megajackpottitle = driver.findElement(By.xpath("//h1[@class='text-center mega-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle);
					Thread.sleep(3000);
				
				// 2.How much prize option clk section
					driver.findElement(By.xpath("//li[text()='How much is the Lotto60® Mega Jackpot® Grand Prize?']")).click();
					Thread.sleep(3000);
					
					//Prize chart btn clk
					WebElement prizecharttxt = driver.findElement(By.xpath("(//span[@class='footer-para'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)", prizecharttxt);
					Thread.sleep(2000);
					
					prizecharttxt.click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle);
					Thread.sleep(3000);
					
				// 3.How much Jackpot Ticket clk section
					driver.findElement(By.xpath(" //li[text()='How much is the Lotto60® Mega Jackpot® Ticket?']")).click();
					Thread.sleep(3000);
				
					//Prize chart btn clk
					WebElement prizecharttxt2 = driver.findElement(By.xpath("(//span[@class='footer-para'])[2]"));
					js.executeScript("arguments[0].scrollIntoView(true)", prizecharttxt2);
					Thread.sleep(2000);
					
					prizecharttxt2.click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle);
					Thread.sleep(3000);
					
				// 4.When jackpot draws? section
					driver.findElement(By.xpath("//li[text()='When are the Lotto60® Mega Jackpot® Draws?']")).click();
					Thread.sleep(2000);
					
					//Results page text clk
					driver.findElement(By.xpath("//span[text()='Results page']")).click();
					Thread.sleep(2000);
					
					//Home page text clk
					Pattern homepagetxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Footer - mega - homepage text.png");
					scrn.wait(homepagetxt,50);
					scrn.click();
					Thread.sleep(3000);
					
					//Scrolling to footer section
					WebElement scrolldown2 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown2);
					Thread.sleep(2000);
					
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(3000);
					
				// 5.Where to watch live draw? section
					driver.findElement(By.xpath("//li[text()='Where Can I Watch the Lotto60® Mega Jackpot® Draws Live?']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					WebElement megajackpottitle2 = driver.findElement(By.xpath("//h1[@class='text-center mega-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle2);
					Thread.sleep(3000);
					
				// 6.How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Lotto60®Mega Jackpot:']")).click();
					Thread.sleep(3000);
					
					//Register btn clk
					driver.findElement(By.xpath("//a[text()='Register']")).click();
					Thread.sleep(2000);
					
					//Scrolling to footer section
					WebElement scrolldown3 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown3);
					Thread.sleep(2000);
					
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(3000);
					
					//6.How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Lotto60®Mega Jackpot:']")).click();
					Thread.sleep(2000);
					
					//Deposit txt clk
					driver.findElement(By.xpath("//span[text()='Deposit']")).click();
					Thread.sleep(3000);
					
					//Mega jackpot txt clk
					driver.findElement(By.xpath("//span[text()='Mega Jackpot']")).click();
					Thread.sleep(3000);
					
					//Your activity txt clk
					driver.findElement(By.xpath("//span[text()='Your Activity.']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					WebElement megajackpottitle3 = driver.findElement(By.xpath("//h1[@class='text-center mega-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
				// 7.How to claim prize section
					driver.findElement(By.xpath("//li[text()='How to Claim Your Prize Winnings']")).click();
					Thread.sleep(2000);
					
					//1st Your account txt clk
					driver.findElement(By.xpath("(//span[text()='Your Account.'])[1]")).click();
					Thread.sleep(5000);
					
					//2nd Your account txt clk
					driver.findElement(By.xpath("(//span[text()='Your Account.'])[2]")).click();
					Thread.sleep(5000);
					
					//3rd Your account txt clk
					driver.findElement(By.xpath("(//span[text()='Your Account.'])[3]")).click();
					Thread.sleep(5000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
				// 8.Prize chart section
					driver.findElement(By.xpath("//li[text()='Prize Chart for Lotto60® Mega Jackpot® Draw:']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
				// 9.Odds of winning section
					WebElement oddofwinningtxt = driver.findElement(By.xpath("//li[text()='Odds of Winning Lotto60® Mega Jackpot®']"));
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", oddofwinningtxt);
					Thread.sleep(3000);
					
				// 10. Play responsibly section
					driver.findElement(By.xpath("//li[text()='Play Responsibly, Play for Fun!']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", oddofwinningtxt);
					Thread.sleep(3000);
					
				// 11. Check your numbers section
					driver.findElement(By.xpath("//li[text()='Check Your Numbers!']")).click();
					Thread.sleep(3000);
				
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", oddofwinningtxt);
					Thread.sleep(3000);
				
				// 12. Don't get pressed section
					Pattern dontgetpressedtxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\"
							+ "Images\\footer - mega - dont get pressed txt.png");
					scrn.wait(dontgetpressedtxt,50);
					scrn.click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", oddofwinningtxt);
					Thread.sleep(3000);
					
				// 13. We love our winners section
					driver.findElement(By.xpath("//li[text()='We Love Our Winners!']")).click();
					Thread.sleep(2000);
						
					
				}


			public void footerMegaJackpotLoggedInModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(3000);
					
				// 1.What is mega jackpot option clk section
					driver.findElement(By.xpath("//li[text()='What is the Lotto60® Mega Jackpot?']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					WebElement megajackpottitle = driver.findElement(By.xpath("//h1[@class='text-center mega-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle);
					Thread.sleep(3000);
				
				// 2.How much prize option clk section
					driver.findElement(By.xpath("//li[text()='How much is the Lotto60® Mega Jackpot® Grand Prize?']")).click();
					Thread.sleep(3000);
					
					//Prize chart btn clk
					WebElement prizecharttxt = driver.findElement(By.xpath("(//span[@class='footer-para'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)", prizecharttxt);
					Thread.sleep(2000);
					
					prizecharttxt.click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle);
					Thread.sleep(3000);
					
				// 3.How much Jackpot Ticket clk section
					driver.findElement(By.xpath(" //li[text()='How much is the Lotto60® Mega Jackpot® Ticket?']")).click();
					Thread.sleep(3000);
				
					prizecharttxt.click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle);
					Thread.sleep(3000);
					
				// 4.When jackpot draws? section
					driver.findElement(By.xpath("//li[text()='When are the Lotto60® Mega Jackpot® Draws?']")).click();
					Thread.sleep(2000);
					
					//Results page text clk
					driver.findElement(By.xpath("//a[text()='Results page']")).click();
					Thread.sleep(4000);
					
					//Close btn clk in live draw page
					driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]")).click();
					Thread.sleep(2000);
					
					//Scrolling to footer section
					WebElement scrolldown2 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown2);
					Thread.sleep(2000);
					
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(3000);
					
					//4.When jackpot draws? section
					driver.findElement(By.xpath("//li[text()='When are the Lotto60® Mega Jackpot® Draws?']")).click();
					Thread.sleep(2000);
				
					//Home page text clk
					Pattern homepagetxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\Footer - mega - homepage text.png");
					scrn.wait(homepagetxt,50);
					scrn.click();
					Thread.sleep(3000);
					
					//Scrolling to footer section
					WebElement scrolldown3 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown3);
					Thread.sleep(2000);
					
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(3000);
					
				// 5.Where to watch live draw? section
					driver.findElement(By.xpath("//li[text()='Where Can I Watch the Lotto60® Mega Jackpot® Draws Live?']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					WebElement megajackpottitle2 = driver.findElement(By.xpath("//h1[@class='text-center mega-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle2);
					Thread.sleep(3000);
					
				// 6.How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Lotto60®Mega Jackpot:']")).click();
					Thread.sleep(2000);
					
					//Register btn clk
					driver.findElement(By.xpath("//a[text()='Register']")).click();
					Thread.sleep(2000);
					
					//Scrolling to footer section
					WebElement scrolldown4 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown4);
					Thread.sleep(2000);
					
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(3000);
					
					//6.How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Lotto60®Mega Jackpot:']")).click();
					Thread.sleep(2000);
					
					//Deposit txt clk
					driver.findElement(By.xpath("//a[text()='Deposit']")).click();
					Thread.sleep(3000);
					
					//Scrolling to footer section
					WebElement scrolldown5 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown5);
					Thread.sleep(2000);
					
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(3000);
					
					//Mega jackpot txt clk
					driver.findElement(By.xpath("//a[text()='Mega Jackpot']")).click();
					Thread.sleep(3000);
					
					//Scrolling to footer section
					WebElement scrolldown6 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown6);
					Thread.sleep(2000);
				
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(3000);
			
					//Your activity txt clk
					driver.findElement(By.xpath("//a[text()='Your Activity.']")).click();
					Thread.sleep(2000);
					
					//Scrolling to footer section
					WebElement scrolldown7 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown7);
					Thread.sleep(2000);
				
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(3000);
				
				// 7.How to claim prize section
					driver.findElement(By.xpath("//li[text()='How to Claim Your Prize Winnings']")).click();
					Thread.sleep(4000);
					
					//1st Your account txt clk
					driver.findElement(By.xpath("(//a[text()='Your Account.'])[1]")).click();
					Thread.sleep(3000);
					
					//Scrolling to footer section
					WebElement scrolldown8 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown8);
					Thread.sleep(2000);
					
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(4000);
				
					//2nd Your account txt clk
					driver.findElement(By.xpath("(//a[text()='Your Account.'])[2]")).click();
					Thread.sleep(3000);
					
					//Scrolling to footer section
					WebElement scrolldown9 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown9);
					Thread.sleep(2000);
					
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(4000);
					
					//3rd Your account txt clk
					driver.findElement(By.xpath("(//a[text()='Your Account.'])[3]")).click();
					Thread.sleep(3000);
					
					//Scrolling to footer section
					WebElement scrolldown10 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown10);
					Thread.sleep(2000);
				
					//Mega Jackpot section click
					driver.findElement(By.xpath("//a[text()='Mega']")).click();
					Thread.sleep(3000);
				
				// 8.Prize chart section
					driver.findElement(By.xpath("//li[text()='Prize Chart for Lotto60® Mega Jackpot® Draw:']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					WebElement megajackpottitle3 = driver.findElement(By.xpath("//h1[@class='text-center mega-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
				// 9.Odds of winning section
					driver.findElement(By.xpath("//li[text()='Odds of Winning Lotto60® Mega Jackpot®']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
					//Scrolling downwards
					WebElement scrolluptohowtoplay = driver.findElement(By.xpath("//li[text()='How to play Lotto60®Mega Jackpot:']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolluptohowtoplay);
					Thread.sleep(2000);
					
				// 10. Play responsibly section
					driver.findElement(By.xpath("//li[text()='Play Responsibly, Play for Fun!']")).click();
					Thread.sleep(2000);
				
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", scrolluptohowtoplay);
					Thread.sleep(2000);
					
				// 10. Play responsibly section
					driver.findElement(By.xpath("//li[text()='Check Your Numbers!']")).click();
					Thread.sleep(2000);
				
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", scrolluptohowtoplay);
					Thread.sleep(2000);
					
				// 11. Don't get pressed section
					Pattern dontgetpressedtxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\"
							+ "Images\\footer - mega - dont get pressed txt.png");
					scrn.wait(dontgetpressedtxt,50);
					scrn.click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", scrolluptohowtoplay);
					Thread.sleep(2000);
					
				// 13. We love our winners section
					driver.findElement(By.xpath("//li[text()='We Love Our Winners!']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",megajackpottitle3);
					Thread.sleep(3000);
					
					//Closing the chevron btn clk
					driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[1]")).click();
					Thread.sleep(3000);
					
					//Closing the chevron btn clk
					driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[1]")).click();
					Thread.sleep(3000);
			
			}
			
			public void footerInstantNonLoggedInModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//Instant jackpot section click
					driver.findElement(By.xpath("//a[text()='Instant Jackpot®']")).click();
					Thread.sleep(3000);
					
				// 1.What is instant jackpot option clk section
					driver.findElement(By.xpath("//li[text()='What is Lotto60® Instant Jackpot?']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					WebElement instantjackpottitle = driver.findElement(By.xpath("//h1[@class='text-center instant-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle);
					Thread.sleep(3000);
				
				// 2.How much prize option clk section
					driver.findElement(By.xpath("//li[text()='How much is the Lotto60® Instant Jackpot Grand Prize?']")).click();
					Thread.sleep(3000);
					
					//Prize chart btn clk
					WebElement prizecharttxt = driver.findElement(By.xpath("(//span[@class='footer-para'])[1]"));	
					prizecharttxt.click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle);
					Thread.sleep(3000);
					
				// 3.How much Jackpot Ticket clk section
					driver.findElement(By.xpath("//li[text()='How much is a Lotto60® Instant Jackpot ticket?']")).click();
					Thread.sleep(3000);
					
					//Prize chart btn clk
					WebElement prizecharttxt2 = driver.findElement(By.xpath("(//span[@class='footer-para'])[2]"));
					js.executeScript("arguments[0].scrollIntoView(true)", prizecharttxt2);
					Thread.sleep(2000);
					
					prizecharttxt2.click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle);
					Thread.sleep(3000);
					
				// 4.When jackpot draws? section
					driver.findElement(By.xpath("//li[text()='When are the Lotto60® Instant Jackpot draws?']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle);
					Thread.sleep(3000);
								
			// 5.How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Lotto60®Instant Jackpot:']")).click();
					Thread.sleep(3000);
					
					//Register btn clk
					driver.findElement(By.xpath("//a[text()='Register']")).click();
					Thread.sleep(2000);
					
					//Scrolling to footer section
					WebElement scrolldown2 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown2);
					Thread.sleep(2000);
					
					//Instant jackpot section click
					driver.findElement(By.xpath("//a[text()='Instant Jackpot®']")).click();
					Thread.sleep(3000);
					
					//5.How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Lotto60®Instant Jackpot:']")).click();
					Thread.sleep(2000);
					
					//Deposit txt clk
					driver.findElement(By.xpath("//span[text()='Deposit']")).click();
					Thread.sleep(5000);
					
					//Instant jackpot txt clk
					driver.findElement(By.xpath("//span[text()='Instant Jackpot']")).click();
					Thread.sleep(5000);
					
					//Your activity txt clk
					driver.findElement(By.xpath("//span[text()='Your Activity']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					WebElement instantjackpottitle2 = driver.findElement(By.xpath("//h1[@class='text-center instant-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle2);
					Thread.sleep(3000);
					
				// 6.How to claim prize section
					driver.findElement(By.xpath("//li[text()='How to Claim Your Prize Winnings']")).click();
					Thread.sleep(2000);
					
					//1st Your account txt clk
					driver.findElement(By.xpath("//span[text()='Your Account']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle2);
					Thread.sleep(3000);
					
				// 7.Prize chart section
					driver.findElement(By.xpath("//li[text()='Prize Chart for Lotto60® Instant Jackpot Draw:']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle2);
					Thread.sleep(3000);
					
				// 8.Odds of winning section
					WebElement oddofwinningtxt = driver.findElement(By.xpath("//li[text()='Odds of Winning Lotto60® Instant Jackpot:']"));
					oddofwinningtxt.click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", oddofwinningtxt);
					Thread.sleep(3000);
					
				// 9. Play responsibly section
					driver.findElement(By.xpath("//li[text()='Play responsibly, play for fun!']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", oddofwinningtxt);
					Thread.sleep(3000);
					
				// 10. Check your numbers section
					driver.findElement(By.xpath("//li[text()='Double check your numbers!']")).click();
					Thread.sleep(3000);
				
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", oddofwinningtxt);
					Thread.sleep(3000);
				
				// 11. Did you know section
					driver.findElement(By.xpath("//li[text()='Did you know?']")).click();
					Thread.sleep(3000);
					
					//Mega jackpot btn clk
					driver.findElement(By.xpath("//span[text()='Mega Jackpot']")).click();
					Thread.sleep(3000);
					
					//scrolling down to provably fairplay txt
					WebElement scrolldowntoprovablytxt = driver.findElement(By.xpath("//a[text()='Provably Fair Blockchain Gaming systems']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldowntoprovablytxt);
					Thread.sleep(3000);
					
					Pattern provablytxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\footer - instant - blockchain gaming systems txt.png");
					scrn.wait(provablytxt,50);
					scrn.click();
					Thread.sleep(3000);
					
					//Instant jackpot side bar option clk
					driver.findElement(By.xpath("//li[text()='How to Play Lotto60 Instant Jackpot']")).click();
					Thread.sleep(2000);
					
				//Side menu bar chevron btn clk
					WebElement instantchevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
					instantchevronbtn.click();
					Thread.sleep(2000);
					
					WebElement instantchevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
					instantchevronbtn2.click();
					Thread.sleep(2000);
				
				
			}

			public void footerInstantLoggedInModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//Instant jackpot section click
					driver.findElement(By.xpath("//a[text()='Instant Jackpot®']")).click();
					Thread.sleep(3000);
					
				// 1.What is instant jackpot option clk section
					driver.findElement(By.xpath("//li[text()='What is Lotto60® Instant Jackpot?']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					WebElement instantjackpottitle = driver.findElement(By.xpath("//h1[@class='text-center instant-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle);
					Thread.sleep(3000);
				
				// 2.How much prize option clk section
					driver.findElement(By.xpath("//li[text()='How much is the Lotto60® Instant Jackpot Grand Prize?']")).click();
					Thread.sleep(3000);
					
					//Prize chart btn clk
					WebElement prizecharttxt = driver.findElement(By.xpath("(//span[@class='footer-para'])[1]"));	
					prizecharttxt.click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle);
					Thread.sleep(3000);
					
				// 3.How much Jackpot Ticket clk section
					driver.findElement(By.xpath("//li[text()='How much is a Lotto60® Instant Jackpot ticket?']")).click();
					Thread.sleep(3000);
					
					//Prize chart btn clk
					WebElement prizecharttxt2 = driver.findElement(By.xpath("(//span[@class='footer-para'])[2]"));
					js.executeScript("arguments[0].scrollIntoView(true)", prizecharttxt2);
					Thread.sleep(2000);
					
					prizecharttxt2.click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle);
					Thread.sleep(3000);
					
				// 4.When jackpot draws? section
					driver.findElement(By.xpath("//li[text()='When are the Lotto60® Instant Jackpot draws?']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle);
					Thread.sleep(3000);
					
				// 5.How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Lotto60®Instant Jackpot:']")).click();
					Thread.sleep(3000);
					
					//Register btn clk
					driver.findElement(By.xpath("//a[text()='Register']")).click();
					Thread.sleep(2000);
					
					//Scrolling to footer section
					WebElement scrolldown2 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown2);
					Thread.sleep(2000);
					
					//Instant jackpot section click
					driver.findElement(By.xpath("//a[text()='Instant Jackpot®']")).click();
					Thread.sleep(3000);
					
					//5.How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Lotto60®Instant Jackpot:']")).click();
					Thread.sleep(2000);
					
					//Deposit txt clk
					driver.findElement(By.xpath("//a[text()='Deposit']")).click();
					Thread.sleep(5000);
					
					//Scrolling to footer section
					WebElement scrolldown3 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown3);
					Thread.sleep(2000);
				
					//Instant jackpot txt clk
					driver.findElement(By.xpath("//a[text()='Instant Jackpot®']")).click();
					Thread.sleep(3000);
					
					//5.How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Lotto60®Instant Jackpot:']")).click();
					Thread.sleep(2000);
					
					//Your activity txt clk
					driver.findElement(By.xpath("//a[text()='Your Activity']")).click();
					Thread.sleep(4000);
					
					//Scrolling to footer section
					WebElement scrolldown4 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown4);
					Thread.sleep(2000);
				
					//Instant jackpot txt clk
					driver.findElement(By.xpath("//a[text()='Instant Jackpot®']")).click();
					Thread.sleep(3000);
				
				// 6.How to claim prize section
					driver.findElement(By.xpath("//li[text()='How to Claim Your Prize Winnings']")).click();
					Thread.sleep(2000);
					
					//1st Your account txt clk
					driver.findElement(By.xpath("//a[text()='Your Account']")).click();
					Thread.sleep(3000);
					
					//Scrolling to footer section
					WebElement scrolldown5 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown5);
					Thread.sleep(2000);
				
					//Instant jackpot txt clk
					driver.findElement(By.xpath("//a[text()='Instant Jackpot®']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					WebElement instantjackpottitle2 = driver.findElement(By.xpath("//h1[@class='text-center instant-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle2);
					Thread.sleep(3000);
					
				// 7.Prize chart section
					driver.findElement(By.xpath("//li[text()='Prize Chart for Lotto60® Instant Jackpot Draw:']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle2);
					Thread.sleep(3000);
					
				// 8.Odds of winning section
					WebElement oddofwinningtxt = driver.findElement(By.xpath("//li[text()='Odds of Winning Lotto60® Instant Jackpot:']"));
					oddofwinningtxt.click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", oddofwinningtxt);
					Thread.sleep(3000);
					
				// 9. Play responsibly section
					driver.findElement(By.xpath("//li[text()='Play responsibly, play for fun!']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", oddofwinningtxt);
					Thread.sleep(3000);
					
				// 10. Double Check your numbers section
					driver.findElement(By.xpath("//li[text()='Double check your numbers!']")).click();
					Thread.sleep(3000);
				
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",instantjackpottitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)", oddofwinningtxt);
					Thread.sleep(3000);
				
				// 11. Did you know section
					driver.findElement(By.xpath("//li[text()='Did you know?']")).click();
					Thread.sleep(3000);
					
					//Mega jackpot btn clk
					driver.findElement(By.xpath("//a[text()='Mega Jackpot']")).click();
					Thread.sleep(3000);
					
					//Scrolling to footer section
					WebElement scrolldown6 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown6);
					Thread.sleep(2000);
				
					//Instant jackpot txt clk
					driver.findElement(By.xpath("//a[text()='Instant Jackpot®']")).click();
					Thread.sleep(3000);
					
					//11. Did you know section
					driver.findElement(By.xpath("//li[text()='Did you know?']")).click();
					Thread.sleep(3000);
					
					//scrolling down to provably fairplay txt
					WebElement scrolldowntoprovablytxt = driver.findElement(By.xpath("//a[text()='Provably Fair Blockchain Gaming systems']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldowntoprovablytxt);
					Thread.sleep(3000);
					
					Pattern provablytxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\footer - instant - blockchain gaming systems txt.png");
					scrn.wait(provablytxt,50);
					scrn.click();
					Thread.sleep(3000);
					
					//Instant jackpot side bar option clk
					driver.findElement(By.xpath("//li[text()='How to Play Lotto60 Instant Jackpot']")).click();
					Thread.sleep(2000);
					
				//Side menu bar chevron btn clk
					WebElement instantchevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
					instantchevronbtn.click();
					Thread.sleep(2000);
					
					WebElement instantchevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[2]"));
					instantchevronbtn2.click();
					Thread.sleep(2000);
			
			}
			
			public void footerRoulottoNonLoggedInModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//Roulotto section click
					driver.findElement(By.xpath("//a[text()='Roulotto®']")).click();
					Thread.sleep(3000);
					
				// 1.What is roulotto option clk section
					driver.findElement(By.xpath("//li[text()='What is Roulotto?']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					WebElement roulottotitle = driver.findElement(By.xpath("//h1[@class='text-center mega-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle);
					Thread.sleep(3000);
					
				// 2. How to play section
					driver.findElement(By.xpath("//li[text()='How do you play Roulotto®?']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle);
					Thread.sleep(3000);
					
				// 3.Play combinations section
					driver.findElement(By.xpath("//li[text()='Play Combinations for Increased Payouts!']")).click();
					Thread.sleep(2000);
					
					//Payout charts txt clk
					driver.findElement(By.xpath("//span[text()='Payouts Chart']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle);
					Thread.sleep(3000);
					
				// 4. How much cost to play section
					driver.findElement(By.xpath("//li[text()='How much does it cost to play Roulotto®?']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle);
					Thread.sleep(3000);
					
				// 5. Where do i play section
					WebElement wheretoplaysection = driver.findElement(By.xpath("//li[text()='Where Do I Play Roulotto®?']"));
					wheretoplaysection.click();
					Thread.sleep(2000);
					
					//Your account btn clk
					driver.findElement(By.xpath("//span[text()='“Your Account”']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle);
					Thread.sleep(3000);
				
				// 6. How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Roulotto®:']")).click();
					Thread.sleep(4000);
					
					//Register btn clk
					driver.findElement(By.xpath("//a[text()='Register']")).click();
					Thread.sleep(2000);
					
					//Scrolling to footer section
					WebElement scrolldown2 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown2);
					Thread.sleep(2000);
					
					//Roulotto section click
					driver.findElement(By.xpath("//a[text()='Roulotto®']")).click();
					Thread.sleep(3000);
					
					// 6. How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Roulotto®:']")).click();
					Thread.sleep(2000);
					
					//Deposit txt clk
					driver.findElement(By.xpath("//span[text()='Deposit']")).click();
					Thread.sleep(5000);
					
					//Roulotto txt clk
					driver.findElement(By.xpath("//span[text()='Roulotto']")).click();
					Thread.sleep(5000);
						
					//Scrolling upwards
					WebElement roulottotitle2 = driver.findElement(By.xpath("//h1[@class='text-center mega-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
					
				// 7. How to cash out section
					driver.findElement(By.xpath("//li[text()='How to Cash out Your Roulotto® Winnings']")).click();
					Thread.sleep(2000);	
					
					//1st your lotto60 account clk
					driver.findElement(By.xpath("(//span[text()='Your Lotto60 Account'])[1]")).click();
					Thread.sleep(4000);
					
					//2nd your lotto60 account clk
					driver.findElement(By.xpath("(//span[text()='Your Lotto60 Account'])[2]")).click();
					Thread.sleep(4000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
					
				// 8.Payouts section
					driver.findElement(By.xpath("//li[text()='Payouts Chart for Roulotto®:']")).click();
					Thread.sleep(2000);		
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
				
				// 9.Odds of winning section
					WebElement oddsofwinning = driver.findElement(By.xpath("//li[text()='Odds of Winning Roulotto® Games:']"));
					//oddsofwinning.click();
					//Thread.sleep(2000);		
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",oddsofwinning);
					Thread.sleep(3000);
					
				// 10.Example - Individual play section
					driver.findElement(By.xpath("//li[text()='Example of Individual Play/s']")).click();
					Thread.sleep(2000);	
				
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",oddsofwinning);
					Thread.sleep(3000);
					
					
				// 11.Example - Individual & combo play section
					driver.findElement(By.xpath("//li[text()='Examples of Individual Plays and a Combo Plays']")).click();
					Thread.sleep(2000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",oddsofwinning);
					Thread.sleep(3000);
					
				// 12.Double check plays section
					driver.findElement(By.xpath("//li[text()='Double check your plays!']")).click();
					Thread.sleep(2000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",oddsofwinning);
					Thread.sleep(3000);
					
				// 13.Play responsibly section
					driver.findElement(By.xpath("//li[text()='Play responsibly, play for fun!']")).click();
					Thread.sleep(2000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
								
				//Side menu bar chevron btn clk
					WebElement instantchevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[3]"));
					instantchevronbtn.click();
					Thread.sleep(2000);
					
					WebElement instantchevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[3]"));
					instantchevronbtn2.click();
					Thread.sleep(2000);
				
			}
			
			public void footerRoulottoLoggedInModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//Roulotto section click
					driver.findElement(By.xpath("//a[text()='Roulotto®']")).click();
					Thread.sleep(3000);
					
				// 1.What is roulotto option clk section
					driver.findElement(By.xpath("//li[text()='What is Roulotto?']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					WebElement roulottotitle = driver.findElement(By.xpath("//h1[@class='text-center mega-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle);
					Thread.sleep(3000);
					
				// 2. How to play section
					driver.findElement(By.xpath("//li[text()='How do you play Roulotto®?']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle);
					Thread.sleep(3000);
					
				// 3.Play combinations section
					driver.findElement(By.xpath("//li[text()='Play Combinations for Increased Payouts!']")).click();
					Thread.sleep(2000);
					
					//Payout charts txt clk
					driver.findElement(By.xpath("//span[text()='Payouts Chart']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle);
					Thread.sleep(3000);
					
				// 4. How much cost to play section
					driver.findElement(By.xpath("//li[text()='How much does it cost to play Roulotto®?']")).click();
					Thread.sleep(2000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle);
					Thread.sleep(3000);
					
				// 5. Where do i play section
					WebElement wheretoplaysection = driver.findElement(By.xpath("//li[text()='Where Do I Play Roulotto®?']"));
					wheretoplaysection.click();
					Thread.sleep(2000);
					
					//Your account btn clk
					driver.findElement(By.xpath("//a[text()='“Your Account”']")).click();
					Thread.sleep(3000);
					
					//Scrolling to footer section
					WebElement scrolldown2 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown2);
					Thread.sleep(2000);
					
					//Roulotto section click
					driver.findElement(By.xpath("//a[text()='Roulotto®']")).click();
					Thread.sleep(3000);
			
			// 6. How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Roulotto®:']")).click();
					Thread.sleep(4000);
					
					//Register btn clk
					driver.findElement(By.xpath("//a[text()='Register']")).click();
					Thread.sleep(2000);
					
					//Scrolling to footer section
					WebElement scrolldown3 = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown3);
					Thread.sleep(2000);
					
					//Roulotto section click
					driver.findElement(By.xpath("//a[text()='Roulotto®']")).click();
					Thread.sleep(3000);
					
					// 6. How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Roulotto®:']")).click();
					Thread.sleep(2000);
					
					//Deposit txt clk
					driver.findElement(By.xpath("//a[text()='Deposit']")).click();
					Thread.sleep(3000);
					
					//Scrolling to footer section
					WebElement scrolldown4= driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown4);
					Thread.sleep(2000);
					
					//Roulotto section click
					driver.findElement(By.xpath("//a[text()='Roulotto®']")).click();
					Thread.sleep(3000);
					
					// 6. How to play game section
					driver.findElement(By.xpath("//li[text()='How to play Roulotto®:']")).click();
					Thread.sleep(2000);
					
					//Roulotto txt clk
					driver.findElement(By.xpath("//a[text()='Roulotto']")).click();
					Thread.sleep(4000);
					
					//Scrolling to footer section
					WebElement scrolldown5= driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown5);
					Thread.sleep(2000);
					
					//Roulotto section click
					driver.findElement(By.xpath("//a[text()='Roulotto®']")).click();
					Thread.sleep(3000);
				
				// 7. How to cash out section
					driver.findElement(By.xpath("//li[text()='How to Cash out Your Roulotto® Winnings']")).click();
					Thread.sleep(2000);	
					
					//1st your lotto60 account clk
					driver.findElement(By.xpath("(//a[text()='Your Lotto60 Account'])[1]")).click();
					Thread.sleep(4000);
					
					//Scrolling to footer section
					WebElement scrolldown6= driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown6);
					Thread.sleep(2000);
					
					//Roulotto section click
					driver.findElement(By.xpath("//a[text()='Roulotto®']")).click();
					Thread.sleep(3000);
					
					// 7. How to cash out section
					driver.findElement(By.xpath("//li[text()='How to Cash out Your Roulotto® Winnings']")).click();
					Thread.sleep(2000);
					
					//2nd your lotto60 account clk
					driver.findElement(By.xpath("(//a[text()='Your Lotto60 Account'])[2]")).click();
					Thread.sleep(4000);
					
					//Scrolling to footer section
					WebElement scrolldown7= driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown7);
					Thread.sleep(2000);
					
					//Roulotto section click
					driver.findElement(By.xpath("//a[text()='Roulotto®']")).click();
					Thread.sleep(3000);
					
				// 8.Payouts section
					driver.findElement(By.xpath("//li[text()='Payouts Chart for Roulotto®:']")).click();
					Thread.sleep(2000);		
					
					//Scrolling upwards
					WebElement roulottotitle2 = driver.findElement(By.xpath("//h1[@class='text-center mega-page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
					
				// 9.Odds of winning section
					WebElement oddsofwinning = driver.findElement(By.xpath("//li[text()='Odds of Winning Roulotto® Games:']"));
					//oddsofwinning.click();
					//Thread.sleep(2000);		
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",oddsofwinning);
					Thread.sleep(3000);
					
				// 10.Example - Individual play section
					driver.findElement(By.xpath("//li[text()='Example of Individual Play/s']")).click();
					Thread.sleep(2000);	
				
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",oddsofwinning);
					Thread.sleep(3000);
					
					
				// 11.Example - Individual & combo play section
					driver.findElement(By.xpath("//li[text()='Examples of Individual Plays and a Combo Plays']")).click();
					Thread.sleep(2000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",oddsofwinning);
					Thread.sleep(3000);
					
				// 12.Double check plays section
					driver.findElement(By.xpath("//li[text()='Double check your plays!']")).click();
					Thread.sleep(2000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",oddsofwinning);
					Thread.sleep(3000);
					
				// 13.Play responsibly section
					driver.findElement(By.xpath("//li[text()='Play responsibly, play for fun!']")).click();
					Thread.sleep(2000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",roulottotitle2);
					Thread.sleep(3000);
								
				//Side menu bar chevron btn clk
					WebElement roulottochevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[3]"));
					roulottochevronbtn.click();
					Thread.sleep(2000);
					
					WebElement roulottochevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[3]"));
					roulottochevronbtn2.click();
					Thread.sleep(2000);
			
			}
			
			public void footerGameRulesModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//Game rules section click
					driver.findElement(By.xpath("//a[text()='Game Rules']")).click();
					Thread.sleep(3000);
					
				// 1.Introduction section
					driver.findElement(By.xpath("//li[text()='Introduction']")).click();
					Thread.sleep(3000);
					
					//How to play mega jackpot txt
					driver.findElement(By.xpath("//a[text()='How to Play Mega Jackpot']")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//How to play instant jackpot txt
					driver.findElement(By.xpath("//a[text()='How to Play Instant Jackpot']")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//How to play roulotto txt
					driver.findElement(By.xpath("(//a[text()='How to Play Roulotto'])[1]")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//Data privacy statement
					driver.findElement(By.xpath("//a[text()='Data Privacy Statement']")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
				
					//General terms txt
					driver.findElement(By.xpath("//a[text()='General Terms and Conditions']")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
				// 2. Definitions section
					driver.findElement(By.xpath("//li[text()='Definitions']")).click();
					Thread.sleep(3000);
					
					//See roulotto prize chart btn clk
					driver.findElement(By.xpath("//a[text()='See Roulotto']")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//scrolling down to terms & conditions txt
					WebElement termstxt = driver.findElement(By.xpath("//a[text()='Terms and Conditions']"));
					js.executeScript("arguments[0].scrollIntoView(true)", termstxt);
					Thread.sleep(2000);
					termstxt.click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//scrolling down to terms of use txt
					WebElement termsofusetxt = driver.findElement(By.xpath("(//a[text()='Terms of Use'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)", termsofusetxt);
					Thread.sleep(2000);
					termsofusetxt.click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					// lotto60.com btn clk
					WebElement lotto60txt = driver.findElement(By.xpath("(//a[text()='Lotto60.com'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)", lotto60txt);
					Thread.sleep(2000);
					lotto60txt.click();
					Thread.sleep(3000);
					
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_F4);
					
					r.keyRelease(KeyEvent.VK_CONTROL);
					r.keyRelease(KeyEvent.VK_F4);
					Thread.sleep(4000);
					
					//scrolling down to 2nd terms of use txt
					WebElement termsofusetxt2 = driver.findElement(By.xpath("(//a[text()='Terms of Use'])[2]"));
					js.executeScript("arguments[0].scrollIntoView(true)", termsofusetxt2);
					Thread.sleep(2000);
					termsofusetxt2.click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//scrolling down to 3rd terms of use txt
					WebElement termsofusetxt3 = driver.findElement(By.xpath("(//a[text()='Terms of Use'])[3]"));
					js.executeScript("arguments[0].scrollIntoView(true)", termsofusetxt3);
					Thread.sleep(2000);
					termsofusetxt3.click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(2000);
				
					//Scrolling upwards
					WebElement gamerulestitle = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle);
					Thread.sleep(3000);
				
				// 3.Mega jackpot game section
					driver.findElement(By.xpath("//li[text()='Mega Jackpot® Game']")).click();
					Thread.sleep(3000);	
					
					//Mega jackpot prize chart btn clk
					Pattern megajackpottxt=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\footer - game rules - mega jackpot prize chart txt.png");
					scrn.wait(megajackpottxt,50);
					scrn.click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//Scrolling upwards
					WebElement gamerulestitle2 = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle2);
					Thread.sleep(3000);
				
				// 4.Instant jackpot game section
					driver.findElement(By.xpath("//li[text()='Instant Jackpot® Game']")).click();
					Thread.sleep(3000);
					
					//instant prize chart clk
					driver.findElement(By.xpath("//a[text()='Instant Jackpot']")).click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//Scrolling upwards
					WebElement gamerulestitle3 = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle3);
					Thread.sleep(3000);
					
				// 5.Roulotto game section
					driver.findElement(By.xpath("//li[text()='Roulotto® Game']")).click();
					Thread.sleep(3000);
					
					//How to play roulotto text 
					driver.findElement(By.xpath("(//a[text()='How to Play Roulotto'])[2]")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//Roulotto prize chart
					WebElement roulottoprizechart = driver.findElement(By.xpath("//a[text()='Prize Chart.']"));
					js.executeScript("arguments[0].scrollIntoView(true)", roulottoprizechart);
					Thread.sleep(1000);
					roulottoprizechart.click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//Scrolling upwards
					WebElement gamerulestitle4 = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle4);
					Thread.sleep(3000);
				
				// 6.Game entry mechanism section
					driver.findElement(By.xpath("//li[text()='Game Entry Mechanism']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle4);
					Thread.sleep(3000);
					
				// 7.Results section
					WebElement resultstxt = driver.findElement(By.xpath("//li[text()='Results']"));
					resultstxt.click();
					Thread.sleep(3000);	
				
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle4);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",resultstxt);
					Thread.sleep(3000);
					
				// 8.Payment of prizes section
					driver.findElement(By.xpath("//li[text()='Payment of Prizes']")).click();
					Thread.sleep(3000);	
					
					//Terms of use txt
					Pattern termstxt2=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\footer - game rules - payment prizes - terms txt.png");
					scrn.wait(termstxt2,50);
					scrn.click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//Scrolling upwards
					WebElement gamerulestitle5 = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					//Scrolling downwards
					WebElement resultstxt2 = driver.findElement(By.xpath("//li[text()='Results']"));
					js.executeScript("arguments[0].scrollIntoView(true)",resultstxt2);
					Thread.sleep(3000);
					
				// 9.Disclaimers section
					driver.findElement(By.xpath("//li[text()='Disclaimers']")).click();
					Thread.sleep(3000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",resultstxt2);
					Thread.sleep(3000);
				
				// 10.Limitations of liability section
					driver.findElement(By.xpath("//li[text()='Limitation of Liability']")).click();
					Thread.sleep(3000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",resultstxt2);
					Thread.sleep(3000);
					
				// 11.Decision binding section
					driver.findElement(By.xpath("//li[text()='Lotto60® Decisions Binding']")).click();
					Thread.sleep(3000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					//Side menu bar chevron btn clk
					WebElement gameruleschevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[4]"));
					gameruleschevronbtn.click();
					Thread.sleep(2000);
					
					WebElement gameruleschevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[4]"));
					gameruleschevronbtn2.click();
					Thread.sleep(2000);
					
			//Checking sub heading routing in content
					
					// 1.Introduction section
					driver.findElement(By.xpath("//span[text()='Introduction']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					// 2. Definitions section
					driver.findElement(By.xpath("//span[text()='Definitions']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					// 3.Mega jackpot game section
					driver.findElement(By.xpath("(//span[text()='Mega Jackpot'])[1]")).click();
					Thread.sleep(3000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					// 4.Instant jackpot game section
					driver.findElement(By.xpath("(//span[text()='Instant Jackpot'])[1]")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					// 5.Roulotto game section
					driver.findElement(By.xpath("(//span[text()='Roulotto'])[1]")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					// 6.Game entry mechanism section
					driver.findElement(By.xpath("//span[text()='Game Entry Mechanism']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					// 7.Results section
					WebElement resultssection = driver.findElement(By.xpath("//span[text()='Results']"));
					resultssection.click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",resultssection);
					Thread.sleep(3000);
					
					// 8.Payment of prizes section
					driver.findElement(By.xpath("//span[text()='Payment of Prizes']")).click();
					Thread.sleep(3000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",resultssection);
					Thread.sleep(3000);
					
					// 9.Disclaimers section
					driver.findElement(By.xpath("//span[text()='Disclaimers']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",resultssection);
					Thread.sleep(3000);
					
					// 10.Limitations of liability section
					driver.findElement(By.xpath("//span[text()='Limitation of Liability']")).click();
					Thread.sleep(3000);	
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
					
					//Scrolling downwards
					js.executeScript("arguments[0].scrollIntoView(true)",resultssection);
					Thread.sleep(3000);
					
					// 11.Decision binding section
					driver.findElement(By.xpath("//span[text()='Decisions Binding']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)",gamerulestitle5);
					Thread.sleep(3000);
		
			}
			
			public void footerHowToOpenAccountModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//How to open account section click
					driver.findElement(By.xpath("//a[text()='How to Open Account']")).click();
					Thread.sleep(3000);
					
					//Side menu bar chevron btn clk
					WebElement openaccountchevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[5]"));
					openaccountchevronbtn.click();
					Thread.sleep(2000);
					
					WebElement openaccountchevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[5]"));
					openaccountchevronbtn2.click();
					Thread.sleep(2000);
				
			}	
			public void footerBonusModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//Bonus section click
					driver.findElement(By.xpath("//a[text()='Bonuses & Payouts']")).click();
					Thread.sleep(3000);
					
					//Side menu bar chevron btn clk
					WebElement bonuschevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[6]"));
					bonuschevronbtn.click();
					Thread.sleep(2000);
					
					WebElement bonuschevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[6]"));
					bonuschevronbtn2.click();
					Thread.sleep(2000);
			
			}
			
			public void footerTermsOfUseModule() throws Exception {
					
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//Terms of use section click
					driver.findElement(By.xpath("//a[text()='Terms of Use']")).click();
					Thread.sleep(3000);
					
				//Lotto60.com clk
					driver.findElement(By.xpath("//a[text()='www.lotto60.com']")).click();
					Thread.sleep(5000);
					
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_F4);
					
					r.keyRelease(KeyEvent.VK_CONTROL);
					r.keyRelease(KeyEvent.VK_F4);
					Thread.sleep(4000);
					
				// 1.Introduction section
					driver.findElement(By.xpath("//li[text()='Introduction']")).click();
					Thread.sleep(3000);
					
				// 2.Contracting Parties section
					driver.findElement(By.xpath("//li[text()='Contracting Parties']")).click();
					Thread.sleep(3000);
					
					//lotto60.com clk
					driver.findElement(By.xpath("//a[text()='https://www.lotto60.com']")).click();
					Thread.sleep(6000);
					
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_F4);
					
					r.keyRelease(KeyEvent.VK_CONTROL);
					r.keyRelease(KeyEvent.VK_F4);
					Thread.sleep(4000);
					
					//scrolling upwards
					WebElement termstitle = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
							
				// 3.Opening your account section
					driver.findElement(By.xpath("//li[text()='Opening Your Lotto60® Account']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
				// 4.Username, password section
					driver.findElement(By.xpath("//li[text()='User Name, Password, & Customer Information']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
				// 5.Deposits, withdrawals section
					driver.findElement(By.xpath("//li[text()='Lotto60® Deposits & Withdrawl Policy']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
				// 6.Bonus policy section
					WebElement bonuspolicytxt = driver.findElement(By.xpath("//li[text()='Lotto60® Bonus Policy']"));
					js.executeScript("arguments[0].scrollIntoView(true)", bonuspolicytxt);
					Thread.sleep(3000);
					bonuspolicytxt.click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", bonuspolicytxt);
					Thread.sleep(3000);
					
				// 7.Game fairness & rng testing section
					driver.findElement(By.xpath("//li[text()='Lotto60® Game Fairness And RNG Testing Methods']")).click();
					Thread.sleep(3000);	
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", bonuspolicytxt);
					Thread.sleep(3000);
				
				// 8.Playing lotto games section
					driver.findElement(By.xpath("//li[text()='Playing Lotto60® Games']")).click();
					Thread.sleep(3000);	
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", bonuspolicytxt);
					Thread.sleep(3000);
					
				// 9. Identity verification & kyc section
					driver.findElement(By.xpath("//li[text()='Lotto60® Identity Verification & Know Your Client Policy']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", bonuspolicytxt);
					Thread.sleep(3000);
					
				// 10. Privacy policy section
					driver.findElement(By.xpath("//li[text()='LOTTO60® PRIVACY POLICY']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", bonuspolicytxt);
					Thread.sleep(3000);
					
				// 11. Anti Money laundering section
					driver.findElement(By.xpath("//li[text()='Lotto60® Anti-Money Laundering Policy']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", bonuspolicytxt);
					Thread.sleep(3000);
					
				// 12. Legal use section
					driver.findElement(By.xpath("//li[text()='Legal Use of The Website']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", bonuspolicytxt);
					Thread.sleep(3000);
					
				// 13. Closure of account section
					driver.findElement(By.xpath("//li[text()='Closure of Your Lotto60® Account']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", bonuspolicytxt);
					Thread.sleep(3000);
					
				// 14.Cookies policy section
					driver.findElement(By.xpath("//li[text()='Lotto60® ‘COOKIES’ Policy']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", bonuspolicytxt);
					Thread.sleep(3000);
					
				// 15.Hyperlink policy section
					driver.findElement(By.xpath("//li[text()='Lotto60® HyperLink Policy']")).click();
					Thread.sleep(3000);
					
					//lotto60.com clk
					driver.findElement(By.xpath("//a[text()='www.lotto60.com.']")).click();
					Thread.sleep(6000);
					
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_F4);
					
					r.keyRelease(KeyEvent.VK_CONTROL);
					r.keyRelease(KeyEvent.VK_F4);
					Thread.sleep(4000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
		
				// 15. Responsible gaming policy section
					WebElement responsiblegaming = driver.findElement(By.xpath("//li[text()='Lotto60® Responsible Gaming Policy']"));
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", responsiblegaming);
					Thread.sleep(3000);
					responsiblegaming.click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", responsiblegaming);
					Thread.sleep(3000);
				
				// 16. Self exclusion policy section
					driver.findElement(By.xpath("//li[text()='Lotto60® Self-Exclusion Policy']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", responsiblegaming);
					Thread.sleep(3000);
					
				// 17. Self exclusion policy section
					driver.findElement(By.xpath("//li[text()='Lotto60® Self-Exclusion Policy']")).click();
					Thread.sleep(3000);	
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", responsiblegaming);
					Thread.sleep(3000);
				
				// 18. Complaints & disputes resolution policy section
					driver.findElement(By.xpath("//li[text()='Lotto60® Complaints, Notices, & Disputes Resolution Policy']")).click();
					Thread.sleep(3000);
					
					//Your activity clk
					driver.findElement(By.xpath("//span[text()='Your Activity.']")).click();
					Thread.sleep(2000);
				
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", responsiblegaming);
					Thread.sleep(3000);
					
				// 19. Events outside control section
					Pattern eventscontrol=new Pattern("C:\\Users\\System9\\eclipse-workspace\\Project_Lotto\\Images\\footer - terms of use - events outside lotto60 control point.png");
					scrn.wait(eventscontrol,50);
					scrn.click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", responsiblegaming);
					Thread.sleep(3000);
					
				// 20. Disclaimer of liabilities section
					driver.findElement(By.xpath("//li[text()='Disclamier of Liabilities']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//scroll downwards
					js.executeScript("arguments[0].scrollIntoView(true)", responsiblegaming);
					Thread.sleep(3000);
					
				// 21. Contacting lotto section
					driver.findElement(By.xpath("//li[text()='Contacting Lotto60®']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(false)", termstitle);
					Thread.sleep(3000);
					
					//Side menu bar chevron btn clk
					WebElement termschevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[7]"));
					termschevronbtn.click();
					Thread.sleep(2000);
					
					WebElement termschevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[7]"));
					termschevronbtn2.click();
					Thread.sleep(2000);
				
			
			}
			
			public void footerKYCandAMLpolicyModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//KYC & AML Policy section click
					driver.findElement(By.xpath("//a[text()='KYC & AML policy']")).click();
					Thread.sleep(3000);
					
					//Side menu bar chevron btn clk
					WebElement bonuschevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[8]"));
					bonuschevronbtn.click();
					Thread.sleep(2000);
					
					WebElement bonuschevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[8]"));
					bonuschevronbtn2.click();
					Thread.sleep(2000);
			
			}
			
			public void footerRNGtestingMethods() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//RNG Testing section click
					WebElement rngtestingtxt = driver.findElement(By.xpath("//a[text()='Fairness & RNG']"));
					rngtestingtxt.click();
					Thread.sleep(3000);
					
					//Side menu bar chevron btn clk
					WebElement rngchevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[15]"));
					js.executeScript("arguments[0].scrollIntoView(true)", rngchevronbtn);
					Thread.sleep(2000);
					
					rngchevronbtn.click();
					Thread.sleep(2000);
					
					WebElement rngchevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[15]"));
					rngchevronbtn2.click();
					Thread.sleep(2000);
				
			}
			
			public void footerFAQModule() throws Exception {
			
				//FAQ Section click
					WebElement rngtestingtxt = driver.findElement(By.xpath("//li[text()='Frequently Asked Questions / FAQ']"));
					rngtestingtxt.click();
					Thread.sleep(3000);
					
				//Roulotto here btn clk
					driver.findElement(By.xpath("(//span[text()='Here'])[1]")).click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(3000);
					
				//Instant here btn clk
					driver.findElement(By.xpath("(//span[text()='Here'])[2]")).click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(3000);
					
				//Mega jackpot here btn clk
					WebElement megajackpothere = driver.findElement(By.xpath("(//span[text()='Here'])[3]"));
					megajackpothere.click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(3000);
					
					//scroll downwards
					WebElement megajackpothere2 = driver.findElement(By.xpath("(//span[text()='Here'])[3]"));
					js.executeScript("arguments[0].scrollIntoView(true)", megajackpothere2);
					Thread.sleep(3000);
					
				//Instant jackpot payout here btn clk
					driver.findElement(By.xpath("(//span[text()='Here'])[4]")).click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(3000);	
					
				//Mega jackpot payout here btn clk
					driver.findElement(By.xpath("(//span[text()='Here'])[5]")).click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(3000);
					
					//Scroll upwards to title
					WebElement scrollupwardstottitle = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(false)", scrollupwardstottitle);
					Thread.sleep(3000);
		
			}
			public void footerNonLoggedInAboutUsModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//About us section click
					WebElement aboutustxt = driver.findElement(By.xpath("//a[text()='About Us']"));
					aboutustxt.click();
					Thread.sleep(3000);
					
					//Scroll to about us menu option
					WebElement aboutusmenuoption = driver.findElement(By.xpath("//li[text()='About us']"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption);
					Thread.sleep(2000);
					
				//1. Best Games Section
					driver.findElement(By.xpath("//li[text()='The Best Games!']")).click();
					Thread.sleep(3000);
					
					//1st mega jackpot txt clk
					driver.findElement(By.xpath("(//span[text()='Mega Jackpot'])[1]")).click();
					Thread.sleep(4000);
					
					//2nd mega jackpot txt clk
					driver.findElement(By.xpath("(//span[text()='Mega Jackpot'])[2]")).click();
					Thread.sleep(4000);
					
					//3rd mega jackpot txt clk
					driver.findElement(By.xpath("(//span[text()='Mega Jackpot'])[3]")).click();
					Thread.sleep(4000);
						
					//4th mega jackpot txt clk
					driver.findElement(By.xpath("(//span[text()='Mega Jackpot'])[4]")).click();
					Thread.sleep(4000);
					
					//5th mega jackpot txt clk
					driver.findElement(By.xpath("(//span[text()='Mega Jackpot'])[5]")).click();
					Thread.sleep(4000);
					
					//6th mega jackpot txt clk
					driver.findElement(By.xpath("(//span[text()='Mega Jackpot'])[6]")).click();
					Thread.sleep(4000);
					
					//1st instant jackpot txt clk
					WebElement instantjackpottxt = driver.findElement(By.xpath("(//span[text()='Instant Jackpot'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)", instantjackpottxt);
					Thread.sleep(2000);
					instantjackpottxt.click();
					Thread.sleep(4000);
					
					//7th mega jackpot txt clk
					driver.findElement(By.xpath("(//span[text()='Mega Jackpot'])[7]")).click();
					Thread.sleep(4000);
					
					//2nd instant jackpot txt clk
					driver.findElement(By.xpath("(//span[text()='Instant Jackpot'])[2]")).click();
					Thread.sleep(4000);
					
					//1st Roulotto txt clk
					driver.findElement(By.xpath("(//span[text()='Roulotto'])[1]")).click();
					Thread.sleep(4000);
					
					//scrolling upwards
					WebElement aboutustitle = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption);
					Thread.sleep(2000);
					
				//2.Who we are section
					driver.findElement(By.xpath("//li[text()='Who We Are and What We Do']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption);
					Thread.sleep(2000);
					
				//3.Fastest payout section
					driver.findElement(By.xpath("//li[text()='The Fastest Payouts to Winners']")).click();
					Thread.sleep(3000);	
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption);
					Thread.sleep(2000);
					
				//4.Best customer service section
					driver.findElement(By.xpath("//li[text()='Best Customer Service in Online Gaming']")).click();
					Thread.sleep(3000);	
					
					//Contact us txt clk
					driver.findElement(By.xpath("//a[text()='contact us.']")).click();
					Thread.sleep(2000);
					
					//Customer service txt clk
					driver.findElement(By.xpath("//a[text()=' Customer Service Help Center ']")).click();
					Thread.sleep(2000);
					
					//FAQ txt clk
					driver.findElement(By.xpath("//a[text()='Frequently Asked Questions']")).click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//Mega jackpot game clk
					driver.findElement(By.xpath("//a[text()='Mega Jackpot']")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//instant jackpot game clk
					driver.findElement(By.xpath("//a[text()='Instant Jackpot']")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//Roulotto game clk
					driver.findElement(By.xpath("//a[text()='How to Play Roulotto']")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//scrolling upwards
					WebElement aboutustitle2 = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle2);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					WebElement aboutusmenuoption2 = driver.findElement(By.xpath("//li[text()='About us']"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption2);
					Thread.sleep(2000);
					
				//5.Lotto60 vision section
					driver.findElement(By.xpath("//li[text()='Lotto60®Vision']")).click();
					Thread.sleep(3000);		
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle2);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption2);
					Thread.sleep(2000);
					
				//6.Lotto60 mission section
					driver.findElement(By.xpath("//li[text()='Lotto60®Mission']")).click();
					Thread.sleep(3000);	
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle2);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption2);
					Thread.sleep(2000);
					
				//7.Lotto60 values section
					driver.findElement(By.xpath("//li[text()='Lotto60®Values']")).click();
					Thread.sleep(3000);
					
					//Provably blockchain txt clk
					driver.findElement(By.xpath("//a[text()='Provably Fair Blockchain Gaming']")).click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//scrolling upwards
					WebElement aboutustitle3 = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle3);
					Thread.sleep(2000);
				
			}
			
			public void footerLoggedInAboutUsModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//About us section click
					WebElement aboutustxt = driver.findElement(By.xpath("//a[text()='About Us']"));
					aboutustxt.click();
					Thread.sleep(3000);
					
					//Scroll to about us menu option
					WebElement aboutusmenuoption = driver.findElement(By.xpath("//li[text()='About us']"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption);
					Thread.sleep(2000);
					
				//1. Best Games Section
					driver.findElement(By.xpath("//li[text()='The Best Games!']")).click();
					Thread.sleep(3000);
					
					//1st mega jackpot txt clk
					driver.findElement(By.xpath("(//a[text()='Mega Jackpot'])[1]")).click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//2nd mega jackpot txt clk
					driver.findElement(By.xpath("(//a[text()='Mega Jackpot'])[2]")).click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//3rd mega jackpot txt clk
					driver.findElement(By.xpath("(//a[text()='Mega Jackpot'])[3]")).click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
						
					//4th mega jackpot txt clk
					driver.findElement(By.xpath("(//a[text()='Mega Jackpot'])[4]")).click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//5th mega jackpot txt clk
					driver.findElement(By.xpath("(//a[text()='Mega Jackpot'])[5]")).click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//6th mega jackpot txt clk
					driver.findElement(By.xpath("(//a[text()='Mega Jackpot'])[6]")).click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//1st instant jackpot txt clk
					WebElement instantjackpottxt = driver.findElement(By.xpath("(//a[text()='Instant Jackpot'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)", instantjackpottxt);
					Thread.sleep(2000);
					instantjackpottxt.click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//7th mega jackpot txt clk
					driver.findElement(By.xpath("(//a[text()='Mega Jackpot'])[7]")).click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//2nd instant jackpot txt clk
					driver.findElement(By.xpath("(//a[text()='Instant Jackpot'])[2]")).click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//1st Roulotto txt clk
					driver.findElement(By.xpath("(//a[text()='Roulotto'])[1]")).click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//scrolling upwards
					WebElement aboutustitle = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					WebElement aboutusmenuoption2 = driver.findElement(By.xpath("//li[text()='About us']"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption2);
					Thread.sleep(2000);
				
				//2.Who we are section
					driver.findElement(By.xpath("//li[text()='Who We Are and What We Do']")).click();
					Thread.sleep(3000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption2);
					Thread.sleep(2000);
					
				//3.Fastest payout section
					driver.findElement(By.xpath("//li[text()='The Fastest Payouts to Winners']")).click();
					Thread.sleep(3000);	
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption2);
					Thread.sleep(2000);
					
				//4.Best customer service section
					driver.findElement(By.xpath("//li[text()='Best Customer Service in Online Gaming']")).click();
					Thread.sleep(3000);	
					
					//Contact us txt clk
					driver.findElement(By.xpath("//a[text()='contact us.']")).click();
					Thread.sleep(2000);
					
					//Customer service txt clk
					driver.findElement(By.xpath("//a[text()=' Customer Service Help Center ']")).click();
					Thread.sleep(2000);
					
					//FAQ txt clk
					driver.findElement(By.xpath("//a[text()='Frequently Asked Questions']")).click();
					Thread.sleep(4000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//Mega jackpot game clk
					driver.findElement(By.xpath("//a[text()='Mega Jackpot']")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//instant jackpot game clk
					driver.findElement(By.xpath("//a[text()='Instant Jackpot']")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//Roulotto game clk
					driver.findElement(By.xpath("//a[text()='How to Play Roulotto']")).click();
					Thread.sleep(2000);
					
					driver.navigate().back();
					Thread.sleep(2000);
					
					//scrolling upwards
					WebElement aboutustitle2 = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle2);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					WebElement aboutusmenuoption3 = driver.findElement(By.xpath("//li[text()='About us']"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption3);
					Thread.sleep(2000);
					
				//5.Lotto60 vision section
					driver.findElement(By.xpath("//li[text()='Lotto60®Vision']")).click();
					Thread.sleep(3000);		
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle2);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption3);
					Thread.sleep(2000);
					
				//6.Lotto60 mission section
					driver.findElement(By.xpath("//li[text()='Lotto60®Mission']")).click();
					Thread.sleep(3000);	
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle2);
					Thread.sleep(2000);
					
					//Scroll to about us menu option
					js.executeScript("arguments[0].scrollIntoView(true)", aboutusmenuoption3);
					Thread.sleep(2000);
					
				//7.Lotto60 values section
					driver.findElement(By.xpath("//li[text()='Lotto60®Values']")).click();
					Thread.sleep(3000);
					
					//Provably blockchain txt clk
					driver.findElement(By.xpath("//a[text()='Provably Fair Blockchain Gaming']")).click();
					Thread.sleep(3000);
					
					driver.navigate().back();
					Thread.sleep(2000);
				
					//scrolling upwards
					WebElement aboutustitle3 = driver.findElement(By.xpath("//h1[@class='text-center page-heading']"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle3);
					Thread.sleep(2000);
					
					//Side menu bar chevron btn clk
					WebElement aboutuschevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[13]"));
					js.executeScript("arguments[0].scrollIntoView(true)", aboutuschevronbtn);
					Thread.sleep(2000);
					
					aboutuschevronbtn.click();
					Thread.sleep(2000);
					
					WebElement aboutuschevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[13]"));
					aboutuschevronbtn2.click();
					Thread.sleep(2000);
					
					//scrolling upwards
					js.executeScript("arguments[0].scrollIntoView(true)", aboutustitle3);
					Thread.sleep(2000);
					
			}	
			public void footerProvablyBlockchainGamingModule() throws Exception {
				
					//Scrolling to footer section
					Thread.sleep(2000);
					WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
					Thread.sleep(2000);
					
					//Provably blockchain gaming section click
					WebElement provablyfairtxt = driver.findElement(By.xpath("//a[text()='Provably Fair Blockchain Gaming®']"));
					provablyfairtxt.click();
					Thread.sleep(3000);
					
					//Scrolling down to provably side menu bar
					WebElement scrolldowntoprovably = driver.findElement(By.xpath("(//li[text()='Provably Fair Blockchain Gaming'])[1]"));
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldowntoprovably);
					Thread.sleep(3000);
					
				//1. Lotto60 Fair gaming section
					driver.findElement(By.xpath("//li[text()='Lotto60 Fair Gaming']")).click();
					Thread.sleep(3000);
					
				//2. Provably Blockchain gaming section
					driver.findElement(By.xpath("(//li[text()='Provably Fair Blockchain Gaming'])[2]")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards to title
					WebElement scrolluptotitle = driver.findElement(By.xpath("//h1[text()='Provably Fair Blockchain Gaming']"));
					js.executeScript("arguments[0].scrollIntoView(false)", scrolluptotitle);
					Thread.sleep(3000);
					
					//Scrolling down to provably side menu bar
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldowntoprovably);
					Thread.sleep(3000);
					
				//3. Blockchain & ledger technologies section
					driver.findElement(By.xpath("//li[text()='Blockchain & Distributed Ledger Technologies']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards to title
					js.executeScript("arguments[0].scrollIntoView(false)", scrolluptotitle);
					Thread.sleep(3000);
					
					//Scrolling down to provably side menu bar
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldowntoprovably);
					Thread.sleep(3000);
					
				//4. Highest winnings & payouts section
					driver.findElement(By.xpath("//li[text()='Highest Winnings; Highest Payouts']")).click();
					Thread.sleep(3000);
					
					//Scrolling upwards to title
					js.executeScript("arguments[0].scrollIntoView(false)", scrolluptotitle);
					Thread.sleep(3000);
					
					//Scrolling down to provably side menu bar
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldowntoprovably);
					Thread.sleep(3000);
					
				//5.No withdrawal limits section
					driver.findElement(By.xpath("//li[text()='No Withdrawal Limits']")).click();
					Thread.sleep(3000);	
					
					//Scrolling upwards to title
					js.executeScript("arguments[0].scrollIntoView(false)", scrolluptotitle);
					Thread.sleep(3000);
					
					//Scrolling down to provably side menu bar
					js.executeScript("arguments[0].scrollIntoView(true)", scrolldowntoprovably);
					Thread.sleep(3000);
					
				//6.Advanced security section
					driver.findElement(By.xpath("//li[text()='Advanced Security and 2FA']")).click();
					Thread.sleep(3000);	
					
					//Scrolling upwards to title
					js.executeScript("arguments[0].scrollIntoView(false)", scrolluptotitle);
					Thread.sleep(3000);
					
					//Side menu bar chevron btn clk
					WebElement provablychevronbtn = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[14]"));
					js.executeScript("arguments[0].scrollIntoView(true)", provablychevronbtn);
					Thread.sleep(2000);
					
					provablychevronbtn.click();
					Thread.sleep(2000);
					
					WebElement provablychevronbtn2 = driver.findElement(By.xpath("(//*[local-name()='svg' and @stroke='currentColor'])[14]"));
					provablychevronbtn2.click();
					Thread.sleep(2000);
					
					//Scrolling upwards to title
					js.executeScript("arguments[0].scrollIntoView(false)", scrolluptotitle);
					Thread.sleep(3000);
				
			}
			
			public void footerPrivacyManagementModule() throws Exception {
			
				//Scrolling to footer section
				Thread.sleep(2000);
				WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
				
				//Provably blockchain gaming section click
				WebElement blockchaingamingtxt = driver.findElement(By.xpath("//a[text()='Privacy & Management of Personal Data']"));
				blockchaingamingtxt.click();
				Thread.sleep(3000);
				
				//Logo clk
				driver.findElement(By.xpath("//img[@class=' cursor-pointer']")).click();
				Thread.sleep(3000);
			
			} 
			
			public void footerResponsibleGamingModule() throws Exception {
				
				//Scrolling to footer section
				Thread.sleep(2000);
				WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
				
				//Responsible gaming section click
				WebElement responsiblegamingtxt = driver.findElement(By.xpath("//a[text()='Responsible Gaming']"));
				responsiblegamingtxt.click();
				Thread.sleep(3000);
				
				//Logo clk
				driver.findElement(By.xpath("//img[@class=' cursor-pointer']")).click();
				Thread.sleep(3000);
				
			}
			public void footerSelfExclusionModule() throws Exception {
			
				//Scrolling to footer section
				Thread.sleep(2000);
				WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
				
				//Self Exclusion section click
				WebElement selfexclusiontxt = driver.findElement(By.xpath("//a[text()='Self-exclusion']"));
				selfexclusiontxt.click();
				Thread.sleep(3000);
				
				//Gamblock website clk
				WebElement gamblock = driver.findElement(By.xpath("//a[text()='www.gamblock.com']"));
				js.executeScript("arguments[0].scrollIntoView(true)", gamblock);
				Thread.sleep(2000);
				gamblock.click();
				Thread.sleep(2000);
				
				//Closing the website
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_F4);
				
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_F4);
				Thread.sleep(4000);
				
				//Responsible Gaming website clk
				WebElement responsiblegamblock = driver.findElement(By.xpath("//a[text()='www.responsiblegambling.org']"));
				responsiblegamblock.click();
				Thread.sleep(2000);
				
				//Closing the website
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_F4);
				
				r.keyRelease(KeyEvent.VK_CONTROL);
				r.keyRelease(KeyEvent.VK_F4);
				Thread.sleep(4000);
				
				//Logo clk
				driver.findElement(By.xpath("//img[@class=' cursor-pointer']")).click();
				Thread.sleep(3000);
			
			}
			
			public void footerCustomerPolicyModule() throws Exception {
				
				//Scrolling to footer section
				Thread.sleep(2000);
				WebElement scrolldown = driver.findElement(By.xpath("//a[text()='Privacy & Management']"));
				js.executeScript("arguments[0].scrollIntoView(true)", scrolldown);
				Thread.sleep(2000);
				
				//Customer Policy section click
				WebElement customerpolicytxt = driver.findElement(By.xpath("//a[text()='Customer First Complaints Policy']"));
				customerpolicytxt.click();
				Thread.sleep(3000);
				
				//Your activity clk
				WebElement youractivity = driver.findElement(By.xpath("//a[text()='Your Account Activity.']"));
				js.executeScript("arguments[0].scrollIntoView(true)", youractivity);
				Thread.sleep(2000);
				youractivity.click();
				Thread.sleep(4000);

			}
			
			public void bankTransactionHistoryModule() throws Exception {
				
					//Bank clk
					driver.findElement(By.xpath("//span[text()='Bank']")).click();
					Thread.sleep(2000);
					
					//Transaction History Section
					WebElement typedropdown = driver.findElement(By.xpath("//select[@class='type-filter']"));
					typedropdown.click();
					Thread.sleep(2000);
						
					Select slct=new Select(typedropdown);
					
					//1. Cash in option
					slct.selectByVisibleText("cash-in");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
		
					//2. Cash out option
					slct.selectByVisibleText("cash-out");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//3. Manual Bonus Debited option
					slct.selectByVisibleText("manual-bonus-debited");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//4. Manual Bonus Credited option
					slct.selectByVisibleText("manual-bonus-credited");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//5. Manual adjustment withdraw option
					slct.selectByVisibleText("manual-adjustment-withdraw");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//6. Manual adjustment deposit option
					slct.selectByVisibleText("manual-adjustment-deposit");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//7. Mega Jackpot winnings option
					slct.selectByVisibleText("mega-jackpot-winnings");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//8. Bonus Mega Jackpot winnings option
					slct.selectByVisibleText("Bonus-mega-jackpot-winnings");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//9. Bonus roulotto winnings option
					slct.selectByVisibleText("Bonus-roulotto-jackpot-winnings");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//10. roulotto winnings option
					slct.selectByVisibleText("roulotto-jackpot-winnings");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//11. Instant jackpot bet option
					slct.selectByVisibleText("instant-jackpot-bet");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//12. Bonus Instant jackpot bet option
					slct.selectByVisibleText("Bonus-instant-jackpot-bet");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//12. Roulotto jackpot bet option
					slct.selectByVisibleText("roulotto-jackpot-bet");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//13. Instant jackpot winnings option
					slct.selectByVisibleText("instant-jackpot-winnings");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//14. Bonus Instant jackpot winnings option
					slct.selectByVisibleText("Bonus-instant-jackpot-winnings");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//15. Bonus bet refund option
					slct.selectByVisibleText("Bonus-bet-refund");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//16.Mega jackpot bet option
					slct.selectByVisibleText("mega-jackpot-bet");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
					//17. Bonus Mega jackpot bet option
					slct.selectByVisibleText("Bonus-mega-jackpot-bet");
					Thread.sleep(5000);	

			}
			
			public void bankDatePickerModule() throws Exception {
			
					//Bank clk
					driver.findElement(By.xpath("//span[text()='Bank']")).click();
					Thread.sleep(2000);
					
					//Transaction History Section
					WebElement typedropdown = driver.findElement(By.xpath("//select[@class='type-filter']"));
					typedropdown.click();
					Thread.sleep(2000);
						
					Select slct=new Select(typedropdown);
					
					//16.Mega jackpot bet option
					slct.selectByVisibleText("mega-jackpot-bet");
					Thread.sleep(3000);
					
					typedropdown.click();
					Thread.sleep(3000);
					
				//Manually entering invalid data
					
					//From date field
					WebElement fromdatefield = driver.findElement(By.xpath(("(//input[@id='date-picker-dialog'])[1]")));
					fromdatefield.sendKeys(randomdatedata);
					Thread.sleep(3000);
					
					//To date field
					WebElement todatefield = driver.findElement(By.xpath(("(//input[@id='date-picker-dialog'])[2]")));
					todatefield.sendKeys(randomdatedata);
					Thread.sleep(3000);
					
					//Clearing entered data
					fromdatefield.click();
					
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_A);
					
					r.keyRelease(KeyEvent.VK_CONTROL);
					r.keyRelease(KeyEvent.VK_A);
					
					r.keyPress(KeyEvent.VK_DELETE);
					r.keyRelease(KeyEvent.VK_DELETE);
					Thread.sleep(2000);
					
					todatefield.click();
					
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_A);
					
					r.keyRelease(KeyEvent.VK_CONTROL);
					r.keyRelease(KeyEvent.VK_A);
					
					r.keyPress(KeyEvent.VK_DELETE);
					r.keyRelease(KeyEvent.VK_DELETE);
					Thread.sleep(2000);
				
				//Manually entering Future date data
					
					//From date field
					fromdatefield.sendKeys(futuredatedata);
					Thread.sleep(3000);
					
					//To date field
					todatefield.sendKeys(futuredatedata);
					Thread.sleep(3000);
					
					//Clearing entered data
					fromdatefield.click();
					
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_A);
					
					r.keyRelease(KeyEvent.VK_CONTROL);
					r.keyRelease(KeyEvent.VK_A);
					
					r.keyPress(KeyEvent.VK_DELETE);
					r.keyRelease(KeyEvent.VK_DELETE);
					Thread.sleep(2000);
					
					todatefield.click();
					
					r.keyPress(KeyEvent.VK_CONTROL);
					r.keyPress(KeyEvent.VK_A);
					
					r.keyRelease(KeyEvent.VK_CONTROL);
					r.keyRelease(KeyEvent.VK_A);
					
					r.keyPress(KeyEvent.VK_DELETE);
					r.keyRelease(KeyEvent.VK_DELETE);
					Thread.sleep(2000);
					
				//Manually entering valid date data	
					
					//From date field
					fromdatefield.sendKeys(futuredatedata);
					Thread.sleep(3000);
					
					//To date field
					todatefield.sendKeys(futuredatedata);
					Thread.sleep(3000);
						
	
				}
			
}
