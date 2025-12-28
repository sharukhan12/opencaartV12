package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; // Log4j
import org.apache.logging.log4j.Logger;     // Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver; 
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups = {"sanity","master","regression"})
	@Parameters({"os","browser"})
public void Setup(String os,String br) throws IOException {
		
		
		//add config prop file
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		
		// logger
		logger = LogManager.getLogger(this.getClass()); // LOG4J2

		// REMOTE EXECUTION
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

		    DesiredCapabilities capabilities = new DesiredCapabilities();

		    // OS
		    if (os.equalsIgnoreCase("windows")) {
		        capabilities.setPlatform(Platform.WIN11);
		    } else if (os.equalsIgnoreCase("linux")) {
		        capabilities.setPlatform(Platform.LINUX);
		    } else {
		        System.out.println("No matching OS");
		        return;
		    }

		    // Browser
		    switch (br.toLowerCase()) {
		        case "chrome":
		            capabilities.setBrowserName("chrome");
		            break;
		        case "edge":
		            capabilities.setBrowserName("MicrosoftEdge");
		            break;
		        case "firefox":
		            capabilities.setBrowserName("firefox");
		            break;
		        default:
		            System.out.println("No matching browser");
		            return;
		    }

		    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}

		// LOCAL EXECUTION
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

		    switch (br.toLowerCase()) {
		        case "chrome":
		            driver = new ChromeDriver();
		            break;
		        case "edge":
		            driver = new EdgeDriver();
		            break;
		        case "firefox":
		            driver = new FirefoxDriver();
		            break;
		        default:
		            System.out.println("Invalid browser name..");
		            return;
		    }
		}

		
		
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().deleteAllCookies();
	driver.get(p.getProperty("appURL"));
	driver.manage().window().maximize();
	
		
}
	@AfterClass(groups = {"sanity","master","regression"})
public void Teardown() throws InterruptedException {
		 if (driver != null) {
		        driver.quit();   // quit, not close
		}

}
	
	public String randomString() {
		String generateAlphabetic = RandomStringUtils.randomAlphabetic(5);
        return generateAlphabetic;
	}
	
	public String randomNumber() {
	 String generateNumeric = RandomStringUtils.randomNumeric(5);
	 return generateNumeric;
	 }
	
	public String randomAlphaNumeric() {
		String generateAlphabetic = RandomStringUtils.randomAlphabetic(5);
		 String generateNumeric = RandomStringUtils.randomNumeric(10);
return (generateAlphabetic+"*"+generateNumeric);
	}
	public String captureScreen(String tname) throws IOException {

	    String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
	            .format(new Date());

	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File sourceFile = ts.getScreenshotAs(OutputType.FILE);

	    String targetFilePath =
	            System.getProperty("user.dir") +
	            "\\screenshots\\" +
	            tname + "_" + timeStamp + ".png";

	    File targetFile = new File(targetFilePath);

	    FileUtils.copyFile(sourceFile, targetFile);

	    return targetFilePath;
	}

}
