package objs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResults {

	public static void playSong(WebDriver driver, String title) {
		driver.findElement(By.partialLinkText(title)).click();
	}
	
}
