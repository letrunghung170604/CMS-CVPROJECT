package DoProject.Pages;

import DoProject.CommonFunctions.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Dashboard {

    private WebDriver driver;
    private Functions functions;
    private String urlDashboard = "/dashboard";
    private String urlProfile = "/profile";
    private By DashboardTitle = By.xpath("//h1[normalize-space()='Dashboard']");
    private By ManageProfileTitle = By.xpath("//h1[normalize-space()='Manage Profile']");
    private By YourName = By.xpath("//input[@placeholder='Your name']");
    private By YourPhone = By.xpath("//input[@placeholder='Your Phone']");
    private By YourPassword = By.xpath("//input[@placeholder='New Password']");
    private By ConfirmPassword = By.xpath("//input[@placeholder='Confirm Password']");
    private By Photo = By.xpath("(//div[normalize-space()='Browse'])[1]");
    private By ChooseMinionPhoto = By.xpath("//div[@class='card-file-thumb']//img[@class='img-fit']");
    private By AddFileBTN = By.xpath("//button[normalize-space()='Add Files']");
    private By UpdateProfileBTN = By.xpath("//button[normalize-space()='Update Profile']");

    public Dashboard(WebDriver driver)
    {
        this.driver = driver;
        functions = new Functions(driver);
    }

    public void confirmDashboard()
    {
        functions.waitUrlContains(urlDashboard);
        Assert.assertTrue(functions.getTextBy(DashboardTitle).equals("Dashboard"), "Login Not Success - Failed Test");
    }

    public void doDashboard(String YourNameData, String YourPhoneData, String YourPasswordData, String ConfirmPasswordData) throws InterruptedException {
        //Verify site
        WebElement ManageProfile = driver.findElement(By.xpath("(//span[normalize-space()='Manage Profile'])[1]"));
        JavascriptExecutor js = (JavascriptExecutor)  driver;
        js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", ManageProfile);
        functions.ClickWE(ManageProfile);
        functions.waitUrlContains(urlProfile);
        Assert.assertTrue(functions.getTextBy(ManageProfileTitle).equals("Manage Profile"), "Not stay in Manage Profile");
        //Update profile
        functions.Clear(YourName);
        functions.sendKeys(YourName, YourNameData);
        functions.Clear(YourPhone);
        functions.sendKeys(YourPhone, YourPhoneData);
        functions.Click(Photo);
        functions.Click(ChooseMinionPhoto);
        functions.waitElementToBeClickable(AddFileBTN);
        functions.Click(AddFileBTN);
        functions.Clear(YourPassword);
        functions.sendKeys(YourPassword, YourPasswordData);
        functions.Clear(ConfirmPassword);
        functions.sendKeys(ConfirmPassword, ConfirmPasswordData);
        functions.Submit(UpdateProfileBTN);
    }
}
