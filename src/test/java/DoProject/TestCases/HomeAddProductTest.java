package DoProject.TestCases;

import DoPrepare.Common.Listener;
import DoPrepare.Common.SetUp;
import DoPrepare.Help.CaptureHelp;
import DoPrepare.Help.ExcelHelp;
import DoProject.Pages.HomeAddProduct;
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
@Feature("Product Management")
@Listeners(Listener.class)
public class HomeAddProductTest extends SetUp {

    private WebDriver driver;
    private HomeAddProduct homeaddproduct;
    private ExcelHelp excelhelp;

    @BeforeMethod
    public void setUpDriver()
    {
        driver = getDriver();
        homeaddproduct = new HomeAddProduct(driver);
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
    @Story("Add Product")
    @Description("Test adding a product using data from Excel")
    public void homeAddProductTest() throws Exception {
        excelhelp.setExcelFile("src/main/resources/ExcellFile.xlsx", "HomeAddProductData");
        homeaddproduct.confirmAddProduct();
        homeaddproduct.doHomeAddProduct(excelhelp.getCellData("NAME PRODUCT", 1), excelhelp.getCellData("NAME PRODUCT", 2));
    }
}