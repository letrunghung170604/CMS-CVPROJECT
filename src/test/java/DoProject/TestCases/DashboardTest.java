package DoProject.TestCases;

import DoPrepare.Common.Listener;
import DoPrepare.Common.SetUp;
import DoPrepare.Help.CaptureHelp;
import DoPrepare.Help.ExcelHelp;
import DoProject.Pages.Dashboard;
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
@Feature("Dashboard Management")
@Listeners(Listener.class)
public class DashboardTest extends SetUp {

    private WebDriver driver;
    private Login login;
    private Dashboard dashboard;
    private ExcelHelp excelhelp;

    @BeforeMethod
    public void setUpDriver()
    {
        driver = getDriver();
        login = new Login(driver);
        dashboard = new Dashboard(driver);
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
    @Test(priority = 1)
    @Story("System Login")
    @Description("Test login functionality with user credentials")
    public void loginTest() throws Exception {
        excelhelp.setExcelFile("src/main/resources/ExcellFile.xlsx", "LoginData");
        login.confirmLogin();
        login.doLogin(excelhelp.getCellData("USERNAME", 1), excelhelp.getCellData("PASSWORD", 1));
    }

    @Step
    @Test(priority = 2)
    @Story("Dashboard Operations")
    @Description("Test dashboard functionality with user profile data")
    public void dashboardTest() throws Exception {
        excelhelp.setExcelFile("src/main/resources/ExcellFile.xlsx", "DashboardData");
        dashboard.confirmDashboard();
        dashboard.doDashboard(excelhelp.getCellData("YOUR NAME", 1), excelhelp.getCellData("YOUR PHONE", 1), excelhelp.getCellData("YOUR PASSWORD", 1), excelhelp.getCellData("CONFIRM PASSWORD", 1));
    }
}