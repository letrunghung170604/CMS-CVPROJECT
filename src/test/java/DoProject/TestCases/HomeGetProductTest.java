package DoProject.TestCases;

import DoPrepare.Common.Listener;
import DoPrepare.Common.SetUp;
import DoPrepare.Help.CaptureHelp;
import DoProject.Pages.HomeGetProduct;
import DoProject.Pages.Login;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Epic("E-Commerce Functionality")
@Feature("Product Retrieval")
@Listeners(Listener.class)
public class HomeGetProductTest extends SetUp {

    private WebDriver driver;
    private Login login;
    private HomeGetProduct homeGetproduct;

    @BeforeMethod
    public void setUpDriver()
    {
        driver = getDriver();
        login = new Login(driver);
        homeGetproduct = new HomeGetProduct(driver);
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) throws InterruptedException {
        Thread.sleep(1000);
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                CaptureHelp.captureScreenshot(driver, result.getName());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }

    @Step
    @Test
    @Story("Product Access")
    @Description("Test retrieving product details from the home page")
    public void homeGetProductTest() throws Exception {
        homeGetproduct.confirmHomeGetProduct();
        homeGetproduct.doHomeGetProduct();
    }
}