package config;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static executionEngine.DriverScript.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import executionEngine.DriverScript;
import io.appium.java_client.android.AndroidDriver;
import utility.ExcelUtils;
import utility.Log;

public class ActionKeywords {
	
		public static WebDriver driver;
			
	@SuppressWarnings("rawtypes")
	public static void openBrowser(String object,String data) throws Exception{	
		@SuppressWarnings("unused")
		ExcelUtils ex = new ExcelUtils();
		String DeviceName = ExcelUtils.getCellData(1, 1, "Caps"); 
		String Version = ExcelUtils.getCellData(2, 1, "Caps"); 
		String PlatformName = ExcelUtils.getCellData(3, 1, "Caps"); 
		String AppPackage = ExcelUtils.getCellData(4, 1, "Caps"); 
		String AppActivity = ExcelUtils.getCellData(5, 1, "Caps"); 
		String URL = ExcelUtils.getCellData(6, 1, "Caps"); 
		Log.info("Opening Browser");
		try{	
			if(data.equals("Conquest")){
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("deviceName", DeviceName);
				capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
				capabilities.setCapability(CapabilityType.VERSION, Version);
				capabilities.setCapability("platformName",PlatformName);
				capabilities.setCapability("appPackage", AppPackage);
				capabilities.setCapability("appActivity", AppActivity);
				driver = new AndroidDriver(new URL(URL), capabilities);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				Log.info("Conquest app started");				
				}
			if(data.equals("Mozilla")){
				driver=new FirefoxDriver();
				Log.info("Mozilla browser started");				
				}
			else if(data.equals("IE")){
				//Dummy Code, Implement you own code
				driver=new InternetExplorerDriver();
				Log.info("IE browser started");
				}
			else if(data.equals("Chrome")){
				//Dummy Code, Implement you own code
				driver=new ChromeDriver();
				Log.info("Chrome browser started");
				}
			
			int implicitWaitTime=(10);
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		}catch (Exception e){
			Log.info("Not able to open the Browser --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void navigate(String object, String data){
		try{
			Log.info("Navigating to URL");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(Constants.URL);
		}catch(Exception e){
			Log.info("Not able to navigate --- " + e.getMessage());
			DriverScript.bResult = false;
			}
		}
	
	public static void click(String object, String data){
		try{
			Log.info("Clicking on Webelement "+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		 }catch(Exception e){
 			Log.error("Not able to click --- " + e.getMessage());
 			DriverScript.bResult = false;
         	}
		}
	
	public static void input(String object, String data){
		try{
			Log.info("Entering the text in " + object);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		 }catch(Exception e){
			 Log.error("Not able to Enter UserName --- " + e.getMessage());
			 DriverScript.bResult = false;
		 	}
		}
	

	public static void waitFor(String object, String data) throws Exception{
		try{
			Log.info("Wait for 5 seconds");
			Thread.sleep(5000);
		 }catch(Exception e){
			 Log.error("Not able to Wait --- " + e.getMessage());
			 DriverScript.bResult = false;
         	}
		}

	public static void closeBrowser(String object, String data){
		try{
			Log.info("Closing the browser");
			driver.quit();
		 }catch(Exception e){
			 Log.error("Not able to Close the Browser --- " + e.getMessage());
			 DriverScript.bResult = false;
         	}
		}

	}