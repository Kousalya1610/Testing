package Package_Git;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class GitClass {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Jar Files\\Driver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://smartdeskst.cognizant.com/#/Projects/1000049063");


		// 1. Operation which are performed by Action Keyword
		Actions act = new Actions(driver);
		WebElement test = driver.findElement(By.xpath(""));
		WebElement Destest = driver.findElement(By.xpath(""));


		act.moveToElement(test).build().perform();			//Mouse hover
		act.contextClick(test).build().perform();			//Right click
		act.clickAndHold(test).build().perform();			//Click and hold 
		act.doubleClick(test).build().perform();			//Double click
		act.dragAndDrop(test, Destest).build().perform();	//Drag and drop
		act.dragAndDropBy(test, 100, 200).build().perform(); //Drag and drop by offset resize
		act.moveByOffset(100, 200).build().perform();			//offset
		act.moveByOffset(100, 200).build().perform();			//offset
		act.keyDown(Keys.ARROW_DOWN).build().perform();			//Keysdown
		act.keyDown(test, Keys.DOWN).build().perform();
		act.keyUp(Keys.ENTER).build().perform();
		act.keyUp(test, Keys.ARROW_LEFT).build().perform();


		// 2. Operation which are performed by Select Keyword

		Select select = new Select(driver.findElement(By.xpath("")));

		select.deselectAll();			//deselect
		select.deselectByIndex(0);
		select.deselectByIndex(0);
		select.deselectByVisibleText("");
		select.selectByIndex(0);		//select
		select.selectByValue("");
		select.selectByValue("");
		select.getAllSelectedOptions();

		// 3. Operation which are performed by JavaScript Executor

		JavaScriptExecutor js = (JavaScriptExecutor) driver;
		js.executeScript("argumnets[0].click();" ,test);  	//Click through JS executor

		js.executeScript("arguments[0].Value=''", test);			//value - Sendkeys and test - webelement

		js.executescript("window.scrollBy(100, 200)","");			//Scroll by
		//Scroll by using java script executor
		js.executeScript("arguments[0].scrollIntoView(true)", test);	//Scrollinto view by java script executor test-WebElement

		String Title = js.executeScript("return document.title;").toString();		//Get title of the page
		System.out.println("Title of the screen : "+Title);

		String Domain = js.executeScript("return document.domain;").toString();		//Get domain of the page
		System.out.println("Domain of the screen : "+Domain);

		String URL = js.executeScript("return document.URL;").toString();			//Get url of the page
		System.out.println("URL of the screen : "+URL);


		// 4. Waits

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  //implicit wait

		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(test));		//test - webelement explicit wait

		Wait fluentait = new FluentWait <WebDriver> (driver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);	//Fluent Wait


		// 5. Take Screenshot
		Date d = new Date();
		String filename = d.toString() + ".jpg";
		File Source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Source, new File("filepath" + filename));

		// 6. Switch to Alert
		Alert alt = driver.switchTo().alert();
		String alttext = alt.getText();
		System.out.println("Alt msg : "+alttext);
		alt.accept();
		alt.dismiss();

		// 7. Switch to frames - frames always starts with iframe tag
		driver.switchTo().frame(0);			//by index
		driver.switchTo().frame("FrameName");		//frame name
		driver.switchTo().frame(test); 		//test - webelement

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='https://onecsitazrbcapps.cognizant.com/2698/appreciation#/Create']")));		//Eg - WebElement
		driver.findElement(By.xpath("//textarea[@placeholder='Appreciation Description Comments']")).sendKeys("123");

		driver.switchTo().defaultContent();

		// 8. Switch to window
		String currentwindow = driver.getWindowHandle();  //returns current window name
		Set<String> winhandles = driver.getWindowHandles();
		for (String window : winhandles) {

			System.out.println("Windows : "+window);
			String windowTitle = driver.getTitle();
			System.out.println("windowTitle : "+windowTitle);
			if(windowTitle.equals("Requestedwindow"))
			{
				System.out.println("Perform actions in requested window");
				break;
			}
			
		}
		driver.switchTo().defaultContent();

		// 9. Handling multiple tabs
		ArrayList tabs = new ArrayList(driver.getWindowHandles());
		System.out.println("Total number of tabs : "+tabs.size());
		driver.switchTo().window(tabs.get(0));		// 0 - index id
		
		//xpath and CSS selector syntax
		//tagname[@attribute='value']-xpath	(Can travel backward and forward)
		//Css Selector    -  tagname[attribute='value']  (Can travel only forward no backward)  ^Starts-with  $Ends-with *Contains
		
		// 10 .Assert
				Assert asert = new Assert();
				String title = "Google";
				String getTitle = driver.getTitle();
				asert.assertEquals(title, getTitle, "Expected and actual is same");
				
				// 11. Soft Assert
				SoftAssert softassert = new SoftAssert();
				softassert.assertTrue(true);
				softassert.assertAll();
				
				// 12. Highlighting the element using javascript Executor
				js.executeScript("arguments[0].setAttribute('style','background:red; border:2px solid blue;')", test);
				
				// 14. a. Window Resizing through java script executor
				js.executeScript("arguments[0].window.resizeto(150,200);");
				
				// 14. b. Window Resizing 
				driver.manage().window().getSize();
				Dimension dimension = new Dimension(500,600);
				driver.manage().window().setSize(dimension);

	}

}
