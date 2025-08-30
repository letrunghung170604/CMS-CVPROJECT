package DoProject.Pages;

import DoProject.CommonFunctions.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Login {

    private WebDriver driver;
    private Functions functions;
    private String urlLogin = "/login";
    private String urlDashboard = "/dashboard";
    private By Login = By.xpath("//a[@class='text-reset d-inline-block opacity-60 py-2' and normalize-space()='Login']");
    private By BannerCookie = By.xpath("//button[@class='btn btn-primary aiz-cookie-accept' and normalize-space()='Ok. I Understood']");
    private By LoginTitle = By.xpath("//h1[normalize-space()='Login to your account.']");
    private By Username = By.xpath("//input[@id='email']");
    private By Password = By.xpath("//input[@id='password']");
    private By LoginBTN = By.xpath("//button[normalize-space()='Login']");
    private By DashboardTitle = By.xpath("//h1[normalize-space()='Dashboard']");


    public Login (WebDriver driver)
    {
        this.driver = driver;
        functions = new Functions(driver);
    }

    public void confirmLogin()
    {
        WebElement CloseBanner = driver.findElement(By.xpath("//i[@class='la la-close fs-20']"));
        if(CloseBanner.isDisplayed())
        {
            functions.ClickWE(CloseBanner);
        }
        functions.Click(BannerCookie);
        functions.Click(Login);
        functions.waitUrlContains(urlLogin);
        Assert.assertTrue(functions.getTextBy(LoginTitle).equals("Login to your account."), "You not place in login");
    }

    public void doLogin(String UsernameData, String PasswordData)
    {
        //Invalid email
        functions.sendKeys(Password, PasswordData);
        functions.Submit(LoginBTN);
        Assert.assertFalse(driver.getCurrentUrl().contains(urlDashboard), "Login Success - Failed Test");
        //Invalid Password
        functions.sendKeys(Username, UsernameData);
        functions.Submit(LoginBTN);
        Assert.assertFalse(driver.getCurrentUrl().contains(urlDashboard), "Login Success - Failed Test");
        //Login success
        functions.sendKeys(Password, PasswordData);
        functions.Submit(LoginBTN);
        functions.waitUrlContains(urlDashboard);
        Assert.assertTrue(driver.getCurrentUrl().contains(urlDashboard), "Login Not Success - Failed Test");
        Assert.assertTrue(functions.getTextBy(DashboardTitle).equals("Dashboard"), "Login Not Success - Failed Test");
    }
}
