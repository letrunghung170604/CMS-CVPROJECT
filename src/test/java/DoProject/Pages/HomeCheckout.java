package DoProject.Pages;

import DoProject.CommonFunctions.Functions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class HomeCheckout {

    private WebDriver driver;
    private Functions functions;
    private String urlCheckout = "/checkout";
    private String urlDeliveryInfor = "/delivery_info";
    private String urlPayment = "/payment_select";
    private By Home = By.xpath("//a[contains(text(),'Home')]");
    private By HomeTitle = By.xpath("//span[normalize-space()='New Products']");
    private By LaptopProduct = By.xpath("//a[normalize-space()='Laptop Gaming']");
    private By AddToCartBTN = By.xpath("//button[@class='btn btn-soft-primary mr-2 add-to-cart fw-600']");
    private By CosyProduct = By.xpath("//a[normalize-space()='Cosy Anh Tester OQUBKZBM']");
    private By Cart = By.xpath("//i[@class='la la-shopping-cart la-2x opacity-80']");
    private By Checkout = By.xpath("//a[normalize-space()='Checkout']");
    private By AddNewAddress = By.xpath("(//div[normalize-space()='Add New Address'])[5]");
    private By Address = By.xpath("//textarea[@placeholder='Your Address']");
    private By Country = By.xpath("//div[contains(text(),'Select your country')]");
    private By ChooseCountry = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By State = By.xpath("//div[contains(text(),'Select State')]");
    private By ChooseState = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By PostalCode = By.xpath("//input[@placeholder='Your Postal Code']");
    private By Phone = By.xpath("//input[@placeholder='+880']");
    private By SaveBTN = By.xpath("//button[normalize-space()='Save']");
    private By ChooseAddressCheck = By.xpath("//span[@class='aiz-rounded-check flex-shrink-0 mt-1']");
    private By DeliveryBTN = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    private By PaymentBTN = By.xpath("//button[normalize-space()='Continue to Payment']");
    private By LaptopProductCheck = By.xpath("(//span[normalize-space()='Laptop Gaming'])[2]");
    private By CosyProductCheck = By.xpath("(//span[normalize-space()='Cosy Anh Tester OQUBKZBM'])[2]");
    private By Text = By.xpath("//textarea[@placeholder='Type your text']");
    private By AgreeCheck = By.xpath("//span[@class='aiz-square-check']");
    private By CompleteOrderBTN = By.xpath("//button[normalize-space()='Complete Order']");
    private By ConfirmationTitle = By.xpath("//h1[normalize-space()='Something went wrong!']");


    public HomeCheckout(WebDriver driver)
    {
        this.driver = driver;
        functions = new Functions(driver);
    }

    public void confirmHomeCheckout()
    {
        functions.Click(Home);
        Assert.assertTrue(functions.getTextBy(HomeTitle).equals("New Products"), "You not place in home");
    }

    public void doHomeCheckOut(String AddressData, String ChooseCountryData, String ChooseStateData, String PostalCodeData, String PhoneData, String TextData) throws InterruptedException {
        //Add product in cart
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
        functions.Click(Checkout);
        //Shipping infor
        functions.waitUrlContains(urlCheckout);
        functions.Click(AddNewAddress);
        functions.sendKeys(Address, AddressData);
        functions.Click(Country);
        functions.sendKeys(ChooseCountry, ChooseCountryData);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
        functions.Click(State);
        functions.sendKeys(ChooseState, ChooseStateData);
        actions.sendKeys(Keys.ENTER).perform();
        functions.sendKeys(PostalCode, PostalCodeData);
        functions.sendKeys(Phone, PhoneData);
        functions.Submit(SaveBTN);
        functions.Click(ChooseAddressCheck);
        functions.Click(DeliveryBTN);
        //Delivery infor
        functions.waitUrlContains(urlDeliveryInfor);
        Assert.assertTrue(functions.getTextBy(LaptopProductCheck).equals("Laptop Gaming"), "Product wrong");
        Assert.assertTrue(functions.getTextBy(CosyProductCheck).equals("Cosy Anh Tester OQUBKZBM"), "Product wrong");
        functions.Click(PaymentBTN);
        //Payment
        functions.waitUrlContains(urlPayment);
        functions.sendKeys(Text, TextData);
        js.executeScript("window.scrollBy(0, 600);");
        functions.Click(AgreeCheck);
        functions.Click(CompleteOrderBTN);
        //Confirmation
        js.executeScript("window.scrollBy(0, 600);");
        Assert.assertTrue(functions.getTextBy(ConfirmationTitle).equals("Something went wrong!"));
    }
}
