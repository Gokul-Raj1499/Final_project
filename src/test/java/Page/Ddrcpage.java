package Page;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ddrcpage {
    WebDriver driver;
    WebDriverWait wait;

    By alert = By.xpath("/html/body/div[3]/div/div/div[1]/button"); // Alert handling 
    By bookbtn = By.xpath("//*[@id=\"myCarousel\"]/div/ul/li[2]"); // Book home sample collection button 
    By search = By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div/div/input"); // Search field
    By pricefilter = By.xpath("//*[@id=\"root\"]/section/div[2]/div[1]/div/div/div/div[1]"); // Price filter dropdown
    By lowtohigh = By.xpath("//*[@id=\"root\"]/section/div[2]/div[1]/div/div/div/div[1]/div[1]"); // Price of index 1
    By upload = By.xpath("//*[@id=\"root\"]/section/div[1]/div/div[1]/div[2]/button");

    By name = By.id("name");
    By mobnumber = By.id("mobile_no");
    By email = By.id("email");
    By gender = By.xpath("/html/body/div[3]/div/div/div[2]/div/div/form/div/div[2]/div[4]/div[1]/div/div/div[1]/div[1]");
    By date = By.name("dob");
    By location = By.xpath("/html/body/div[3]/div/div/div[2]/div/div/form/div/div[2]/div[5]/div/div/div/div[1]");
    By uploadpres = By.xpath("/html/body/div[3]/div/div/div[2]/div/div/form/div/div[2]/div[6]/div/div/input");
    By chekbox = By.xpath("//*[@id=\"upload-pres-agreement\"]");
    By submit = By.xpath("/html/body/div[3]/div/div/div[2]/div/div/form/div/div[2]/div[8]/button");
    By backbtn = By.xpath("//*[@id=\"root\"]/div[2]/a");

    public Ddrcpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void bookbtn() {
        driver.findElement(bookbtn).click();
    }

    public void alertclose() {
        wait.until(ExpectedConditions.elementToBeClickable(alert)); // Wait for alert to be clickable
        driver.findElement(alert).click(); // Close alert
    }

    public void search(String query) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(search)); // Wait for search field to be visible
        WebElement searchBox = driver.findElement(search);
        searchBox.sendKeys(query, Keys.ENTER); // Perform search with provided query
        
        Thread.sleep(3000);
    }

    public void pricefilter(String sortValue) throws InterruptedException {
        // Wait for price filter to be visible and clickable
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(pricefilter));
        dropdown.click(); // Open the dropdown
        
        // Locate the input field inside the dropdown (verify the selector)
        Actions actions = new Actions(driver);
    	WebElement filterOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Low to High')]")));
    	actions.moveToElement(filterOption).click().perform();
        Thread.sleep(1000); // Wait briefly for the dropdown to populate

        // Using JavaScriptExecutor as a fallback to ensure the option is selected
      
      }


    public void uploadPrescription() {
        driver.findElement(upload).click();
    }

    public void name(String value) {
        driver.findElement(name).sendKeys(value);
    }

    public void mobileNumber(String value) {
        driver.findElement(mobnumber).sendKeys(value);
    }

    public void email(String value) {
        driver.findElement(email).sendKeys(value);
    }

    public void gendervalue(String value) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(gender));
        dropdown.click();
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select__input input")));
        inputField.sendKeys(value);
        inputField.sendKeys(Keys.ENTER);
    }

//    public void location(String value) throws InterruptedException {
//        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(location));
//        dropdown.click();
//        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"react-select-16-input\"]")));
//        inputField.sendKeys(value);
//        Thread.sleep(2000); // Can be replaced with WebDriverWait for better reliability
//        inputField.sendKeys(Keys.ENTER);
//    }
    public void location(String value) throws InterruptedException {
    	Actions actions = new Actions(driver);
    	WebElement locationDropdown=driver.findElement(location);
    	// Move to the dropdown and click it
    	actions.moveToElement(locationDropdown).click().perform();

    	// Move to the desired option and click it
    	WebElement locationOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Ernakulam')]")));
    	actions.moveToElement(locationOption).click().perform();



    }


    public void date(String value) {
        WebElement datepicker = driver.findElement(date);
        datepicker.click();
        datepicker.sendKeys(value);
    }

    public void uploadPrescriptionbtn(String filePath) throws AWTException, InterruptedException {
        WebElement uploadElement = driver.findElement(uploadpres);
        Actions actions = new Actions(driver);
        actions.click(uploadElement).perform();
        fileupload(filePath);
    }

    public void fileupload(String filePath) throws AWTException, InterruptedException {
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        // Use Robot class to simulate keyboard actions
        Thread.sleep(2000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER); 
    }

    public void checkbox() {
        driver.findElement(chekbox).click();
    }

    public void submitAndNavigateBack() throws InterruptedException {
        driver.findElement(submit).click();
        Thread.sleep(3000); 
        backbtn();
        Thread.sleep(2000);
        bookbtn();
        
   
        
    }
    public void backbtn()
    {
    	driver.findElement(backbtn).click();
    	
    }
}
