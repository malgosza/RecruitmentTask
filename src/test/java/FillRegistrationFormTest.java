import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class FillRegistrationFormTest {
    private static final String DRIVER_PATH = "C:\\Users\\User\\Desktop\\chromedriver_win32\\chromedriver.exe";
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                DRIVER_PATH);

        driver = new ChromeDriver();
        baseUrl = "https://dev-1.clicktrans.pl/register-test/courier";

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testFillRegistrationForm() throws Exception {
        driver.get(baseUrl);

        WebElement companyNameField = driver.findElement(By.id("user_register_company_name"));
        companyNameField.sendKeys("Masaż. Mobilny Gabinet Fizjoterapii Jan Kowalski");

        WebElement emailAddressField = driver.findElement(By.id("user_register_email"));
        emailAddressField.sendKeys("masaz@aw.com");

        WebElement userNameField = driver.findElement(By.id("user_register_name"));
        userNameField.sendKeys("Jan Kowalski");

        WebElement phoneField = driver.findElement(By.id("user_register_phone"));
        phoneField.sendKeys("123456789");

        WebElement passwordField = driver.findElement(By.id("user_register_plainPassword"));
        passwordField.sendKeys("87654321");

        WebElement agreementRegulationsCheckBox = driver.findElement(By.id("user_register_settings_agreementRegulations"));
        agreementRegulationsCheckBox.click();

        WebElement agreementPersonalDataCheckBox = driver.findElement(By.id("user_register_settings_agreementPersonalData"));
        agreementPersonalDataCheckBox.click();

        WebElement submitButton = driver.findElement(By.id("user_register_submit"));
        submitButton.click();

        String resultMessage = driver.findElement(By.xpath("//div[@class='ui container flashmsg']/div[@class='ui success message']")).getText();
        String expectedMessage = "OK - some registration logic is mocked";
        Assert.assertEquals(resultMessage, expectedMessage);
    }

    @After
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}