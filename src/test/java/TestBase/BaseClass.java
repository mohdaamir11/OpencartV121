package TestBase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
//import java.util.logging.LogManager;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties prop;

	@BeforeClass (groups = {"Sanity","Regression","Master","DataDriver"})
	@Parameters({"os","browser"})
	public void setup(@Optional("defaultOS") String os, @Optional("chrome") String br) throws IOException {
		  
		//loading config.properties file
		
		prop = new Properties();
		
		try {  
			
			FileInputStream fReader=new FileInputStream("C:\\Users\\adpo\\Desktop\\aamir\\OpencartV121\\src\\test\\resources\\config.properties");
			prop.load(fReader);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// Log4j

		logger = LogManager.getLogger(this.getClass());

		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				
				capabilities.setPlatform(Platform.WIN10);				
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("NO Matching Operating system");
				return;
			}
			
			switch (br.toLowerCase()) {
			
			case "chrome" : capabilities.setBrowserName("chrome");
			break;
			
			case "edge" : capabilities.setBrowserName("MicrosoftEdge");
			break;
			
			case "firefox" : capabilities.setBrowserName("firefox");
			break;
			
			default: System.out.println("NO Matching Browser");
			return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			
		}
		
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			
//			 browser parameter handle and switching
			switch (br.toLowerCase()) {

			case "chrome": {
				driver = new ChromeDriver();
				break;
			}
			case "edge": {
				driver = new EdgeDriver();
				break;
			}
	        case "firefox": {
				driver = new FirefoxDriver();
				break;
			}
			default:
				System.out.println("Invalid browser name..");
				return;
			}
		}
		


//		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(prop.getProperty("appURL"));
//		driver.get("https://practice.automationtesting.in/");
		driver.manage().window().maximize();
	}

	@AfterClass (groups = {"Sanity","Regression","Master","DataDriver"})
	public void tearDown() {

		driver.quit();

	}

	  public String randomString() {

			String generatedString = RandomStringUtils.randomAlphabetic(5);
			return generatedString;

		}
	  
	  public String captureScreen(String tname) throws IOException {
		  
		    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		    
		    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		    
		    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		    File targetFile = new File(targetFilePath);
		    sourceFile.renameTo(targetFile);
		    return targetFilePath;
		}

}
