package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objs.Home;
import objs.SearchResults;
import resources.Constants;

@SuppressWarnings("unused")
public class TestPlayList {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {

		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(Constants.URL);
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void testPlayList() {

		File f = new File("pesme.xlsx");

		try {
			InputStream in = new FileInputStream(f);
			OutputStream os = new FileOutputStream("rezultat.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);

			SoftAssert sa = new SoftAssert();

			for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
				driver.navigate().to(Constants.URL);
				Row row = sheet.getRow(i);
				Cell c0 = row.getCell(0);
				Cell c1 = row.getCell(1);

				Cell c4 = row.createCell(4);

				String pesma = c0.toString();
				String link = c1.toString();

				Home.findSong(driver, pesma);
				SearchResults.playSong(driver, pesma);

				sa.assertEquals(driver.getCurrentUrl(), link, pesma);

				if (driver.getCurrentUrl().equals(link)) {
					c4.setCellValue("OK");
				} else {
					c4.setCellValue("KO");
				}
			}

			wb.write(os);
			
			wb.close();

			sa.assertAll();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	@AfterClass
	public void quitDriver() {
		driver.quit();
	}

}
