package TestBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
//import java.util.logging.LogManager;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties properties;

	@BeforeTest
	@Parameters({"os","browser"})
	public void setup(String osys,String br) throws IOException {

		//loading config.properties file
		
		FileReader fReader=new FileReader("/Users/mac/eclipse-workspace/OpencartV121/src/test/resources/config.properties");
		properties.load(fReader);
		
		// Log4j

		logger = LogManager.getLogger(this.getClass());

		// browser parameter handle and switching
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

//		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(properties.getProperty("appURL"));
		driver.manage().window().maximize();
	}


	@AfterTest
	public void tearDown() {

		driver.quit();

	}

	  public String randomString() {

			String generatedString = RandomStringUtils.randomAlphabetic(5);
			return generatedString;

		}


}
