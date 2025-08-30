package DoProject.Pages;

import DoPrepare.Help.ExcelHelp;
import DoProject.CommonFunctions.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomeGetProduct {

    private WebDriver driver;
    private Functions functions;
    private ExcelHelp excelhelp;
    private By BannerCookie = By.xpath("//button[@class='btn btn-primary aiz-cookie-accept' and normalize-space()='Ok. I Understood']");
    private By HomeTitle = By.xpath("//span[normalize-space()='New Products']");
    private By CaseIphoneProduct = By.xpath("//div[@class='img']//img[@alt='Ốp điện thoại Iphone']");
    private By NameCaseIphoneProduct = By.xpath("//h1[contains(text(),'Ốp điện thoại Iphone')]");
    private By PriceCaseIphoneProduct = By.xpath("//strong[@class='h2 fw-600 text-primary']");
    private By QuantityCaseIphoneProduct = By.xpath("//input[@placeholder='1']");
    private By TotalPriceCaseIphoneProduct = By.xpath("//strong[@id='chosen_price']");
    private By DescriptionCaseIphoneProduct = By.xpath("//p[contains(text(),'Ốp điện thoại')]");
    private By NewspaperProduct = By.xpath("//img[@alt='Sách báo']");
    private By NameNewspaperProduct = By.xpath("//h1[normalize-space()='Sách báo']");
    private By PriceNewspaperProduct = By.xpath("//strong[@class='h2 fw-600 text-primary']");
    private By QuantityNewspaperProduct = By.xpath("//input[@placeholder='1']");
    private By TotalPriceNewspaperProduct = By.xpath("//strong[@id='chosen_price']");


    public HomeGetProduct(WebDriver driver)
    {
        this.driver = driver;
        functions = new Functions(driver);
        excelhelp = new ExcelHelp();
    }

    public void confirmHomeGetProduct()
    {
        WebElement CloseBanner = driver.findElement(By.xpath("//i[@class='la la-close fs-20']"));
        if(CloseBanner.isDisplayed())
        {
            functions.ClickWE(CloseBanner);
        }
        functions.Click(BannerCookie);
        Assert.assertTrue(functions.getTextBy(HomeTitle).equals("New Products"), "You not place in home");
    }

    public void doHomeGetProduct() throws Exception {

        excelhelp.setExcelFile("src/main/resources/ExcellFile.xlsx", "HomeGetProductData");
        //Get information case iphone product
        functions.Click(CaseIphoneProduct);
        excelhelp.setCellData(functions.getTextBy(NameCaseIphoneProduct), 1, 0);
        excelhelp.setCellData(functions.getTextBy(PriceCaseIphoneProduct), 1, 1);
        excelhelp.setCellData(functions.getAttributeBy(QuantityCaseIphoneProduct, "value"), 1, 2);
        excelhelp.setCellData(functions.getTextBy(TotalPriceCaseIphoneProduct), 1, 3);
        excelhelp.setCellData(functions.getTextBy(DescriptionCaseIphoneProduct), 1, 4);
        //Get information newspaper product
        driver.navigate().back();
        functions.Click(NewspaperProduct);
        excelhelp.setCellData(functions.getTextBy(NameNewspaperProduct), 2, 0);
        excelhelp.setCellData(functions.getTextBy(PriceNewspaperProduct), 2, 1);
        excelhelp.setCellData(functions.getAttributeBy(QuantityNewspaperProduct, "value"), 2, 2);
        excelhelp.setCellData(functions.getTextBy(TotalPriceNewspaperProduct), 2, 3);
    }
}
