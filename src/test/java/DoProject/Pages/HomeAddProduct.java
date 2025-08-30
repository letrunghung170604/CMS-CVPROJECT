package DoProject.Pages;

import DoProject.CommonFunctions.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomeAddProduct {

    private WebDriver driver;
    private Functions functions;
    private String urlCart = "/cart";
    private By BannerCookie = By.xpath("//button[@class='btn btn-primary aiz-cookie-accept' and normalize-space()='Ok. I Understood']");
    private By HomeTitle = By.xpath("//span[normalize-space()='New Products']");
    private By LaptopProduct = By.xpath("//a[normalize-space()='Laptop Gaming']");
    private By AddToCartBTN = By.xpath("//button[@class='btn btn-soft-primary mr-2 add-to-cart fw-600']");
    private By CosyProduct = By.xpath("//a[normalize-space()='Cosy Anh Tester OQUBKZBM']");
    private By Cart = By.xpath("//i[@class='la la-shopping-cart la-2x opacity-80']");
    private By ViewCart = By.xpath("//a[normalize-space()='View cart']");
    private By NameLaptopProduct = By.xpath("//span[normalize-space()='Laptop Gaming - Crimson']");
    private By NameCosyProduct = By.xpath("(//span[normalize-space()='Cosy Anh Tester OQUBKZBM'])[2]");


    public HomeAddProduct(WebDriver driver)
    {
        this.driver = driver;
        functions = new Functions(driver);
    }

    public void confirmAddProduct()
    {
        WebElement CloseBanner = driver.findElement(By.xpath("//i[@class='la la-close fs-20']"));
        if(CloseBanner.isDisplayed())
        {
            functions.ClickWE(CloseBanner);
        }
        functions.Click(BannerCookie);
        Assert.assertTrue(functions.getTextBy(HomeTitle).equals("New Products"), "You not place in home");
    }

    public void doHomeAddProduct(String NameLaptopProductData, String NameCosyProductData) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 600);");
        functions.Click(LaptopProduct);
        functions.Click(AddToCartBTN);
        Thread.sleep(2000);
        driver.navigate().back();
        functions.Click(CosyProduct);
        functions.Click(AddToCartBTN);
        Thread.sleep(2000);
        driver.navigate().back();
        driver.navigate().refresh();
        functions.Click(Cart);
        functions.Click(ViewCart);
        functions.waitUrlContains(urlCart);
        Assert.assertTrue(functions.getTextBy(NameLaptopProduct).equals(NameLaptopProductData), "Product still not add in cart");
        Assert.assertTrue(functions.getTextBy(NameCosyProduct).equals(NameCosyProductData), "Product still not add in cart");
    }
}
