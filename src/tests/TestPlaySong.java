package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import objs.Home;
import objs.SearchResults;
import resources.Constants;

public class TestPlaySong {
	
	private static WebDriver driver;
	
	@BeforeClass
	public void createDriver() {

		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.navigate().to(Constants.URL);
		driver.manage().window().maximize();
	}
	
	@Test(priority = 1)
	public void testPlay () {
		Home.findSong(driver,  "Jedva èekam rat");
		SearchResults.playSong(driver,  "Jedva èekam rat");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/watch?v=9uL7-cEF6mY");
	}
	
	

}
