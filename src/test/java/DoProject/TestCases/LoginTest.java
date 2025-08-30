package DoProject.TestCases;

import DoPrepare.Common.Listener;
import DoPrepare.Common.SetUp;
import DoPrepare.Help.CaptureHelp;
import DoPrepare.Help.ExcelHelp;
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

@Epic("System Administration")
@Feature("Authentication")
@Listeners(Listener.class)
public class LoginTest extends SetUp {

    private WebDriver driver;
    private Login login;
    private ExcelHelp excelhelp;

    @BeforeMethod
    public void setUpDriver()
    {
        driver = getDriver();
        login = new Login(driver);
        excelhelp = new ExcelHelp();
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
    @Story("System Login")
    @Description("Test login functionality with user credentials")
    public void loginTest() throws Exception {
        excelhelp.setExcelFile("src/main/resources/ExcellFile.xlsx", "LoginData");
        login.confirmLogin();
        login.doLogin(excelhelp.getCellData("USERNAME", 1), excelhelp.getCellData("PASSWORD", 1));
    }
}