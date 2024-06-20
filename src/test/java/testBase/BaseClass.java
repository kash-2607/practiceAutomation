package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties config;
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups = {"master","sanity", "regression"})
	@Parameters({"browser" ,"os"})
	
	public void setup(String br, String os) throws IOException {
		//logger
		logger = LogManager.getLogger(this.getClass());
		
		//properties
		FileReader file = new FileReader(".//src/test/resources/config.properties");
		config = new Properties();
		config.load(file);
		
		//execution on remote environment
		
		if(config.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			String url = "http://localhost:4444/wd/hub";
			
			//OS
			
			if(os.equalsIgnoreCase("windows")) {
				
				capabilities.setPlatform(Platform.WIN11);
				
				
			}else if(os.equalsIgnoreCase("linux")){
				capabilities.setPlatform(Platform.LINUX);
				logger.info("Opening OS");
				
			}else {
				System.out.println("invalid OS.");
			}
			
			//browser
			
			switch(br.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("Browser not found"); return; 
			}
			logger.info("Open the browser");
			logger.info("Enter the URL ");
			String huburl = url;
			driver = new RemoteWebDriver(new URL(huburl),capabilities );
			
		}
		
		//running on local environment
		
		else if(config.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br) {
			case "chrome":driver = new ChromeDriver(); break;
			case "edge":driver = new EdgeDriver(); break;
			default:System.out.println("invalid browser");
			}
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(config.getProperty("appURL"));

		
	}
	@AfterClass(groups = {"master","sanity", "regression"})
	public void teardown() {
		driver.quit();
	}
	
	public String randomStringGenerator() {
		RandomStringUtils random = new RandomStringUtils();
		String str = random.random(6);
		return str;
	}
	public String randomAlphaNumaricStringGenerator() {
		 String randomString = RandomStringUtils.randomAlphanumeric(6);
		 return randomString;
	}
	public String randomEmailGenerator() {
		 String randomString = RandomStringUtils.randomAlphanumeric(6)+"@gmail.com";
		 return randomString;
	}
	
	public String captureScreen(String tname) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddmmss");
		Date dt = new Date();
		String timeStamp = df.format(dt);
		
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"/screenshots/"+tname+"_"+timeStamp;
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
	}
	
}
