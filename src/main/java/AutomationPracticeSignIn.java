import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class AutomationPracticeSignIn {

    public static WebDriver driver;
    public static WebDriverWait wdwait;
    String URL = "http://automationpractice.com/index.php";


    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver_89.exe");
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void register() {
        driver.get(URL);
        String email = "email" + System.currentTimeMillis() + "@email.com";


        driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();

        driver.findElement(By.cssSelector("#email_create")).sendKeys(email);
        driver.findElement(By.xpath("//button[@name='SubmitCreate']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Create an account']")).getText(), "CREATE AN ACCOUNT");
        Assert.assertTrue(driver.findElement(By.cssSelector(".logo.img-responsive")).isDisplayed());

        driver.findElement(By.cssSelector("#id_gender1")).click();

        driver.findElement(By.cssSelector("#customer_firstname")).sendKeys("NekoIme");
        driver.findElement(By.cssSelector("#customer_lastname")).sendKeys("Prezime");

        driver.findElement(By.cssSelector("#passwd")).sendKeys("Neki123pass!!");

        Select dayOB = new Select(driver.findElement(By.cssSelector("#days")));
        dayOB.selectByValue("1");
        Select monthOB = new Select(driver.findElement(By.cssSelector("#months")));
        monthOB.selectByValue("1");
        Select yearOB = new Select(driver.findElement(By.cssSelector("#years")));
        yearOB.selectByValue("2000");

        driver.findElement(By.cssSelector("#address1")).sendKeys("Address 1234");

        driver.findElement(By.cssSelector("#city")).sendKeys("City");

        Select state = new Select(driver.findElement(By.cssSelector("#id_state")));
        state.selectByIndex(5);

        driver.findElement(By.cssSelector("#postcode")).sendKeys("12345");

        Select country = new Select(driver.findElement(By.cssSelector("#id_country")));
        country.selectByValue("21");

        driver.findElement(By.cssSelector("#phone_mobile")).sendKeys("1231231231");

        driver.findElement(By.cssSelector("#alias")).clear();
        driver.findElement(By.cssSelector("#alias")).sendKeys("Adresa 2");

        driver.findElement(By.cssSelector("#submitAccount")).click();
        Assert.assertEquals(driver.getTitle(), "My account - My Store");
        Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='My account']")).getText(), "MY ACCOUNT");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='View my customer account']")).getText(), "NekoIme Prezime");
    }


    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();

    }


}
