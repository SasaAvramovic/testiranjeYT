package objs;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.Constants;

public class Home {
	
	public static void findSong (WebDriver driver, String title) {
		WebElement we = driver.findElement(By.xpath(Constants.SEARCH_XPATH));
		we.sendKeys(title);
		we.sendKeys(Keys.ENTER);
	}

}
