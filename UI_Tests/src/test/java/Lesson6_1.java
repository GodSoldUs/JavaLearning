import com.codeborne.selenide.Configuration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import config.ConfigProvider;

import java.time.Duration;
import java.util.Random;

@Tag("UI-test")
public class Lesson6_1 {

    WebDriver driver;

    String name = "name" + new Random().nextInt();

    @BeforeAll
    static void printConfig() {
        ConfigProvider.printConfigParams();
        Configuration.timeout = ConfigProvider.CONFIG.timeout();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get(ConfigProvider.CONFIG.Url());
    }

    @DisplayName("Task1.1")
    @Test
    void addGoodAdminTest() {


        driver.findElement(
                By.xpath("//a[@href='/admin']")
        ).click();

        driver.findElement(By.id("username"))
                .sendKeys("admin");

        driver.findElement(By.id("password"))
                .sendKeys("secret123");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement adminTitle =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("h1")
                        )
                );


        Assertions.assertThat(driver.findElement(By.tagName("h1"))
                .getText()
        ).as("Sing in to admin panel")
                .isEqualTo("SmartShop Admin");

        driver.findElement(By.xpath("//input[@id='n-name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@id='n-price']")).sendKeys("123");
        driver.findElement(By.xpath("//button[@id='add-btn']")).click();

        driver.findElement(By.xpath("//a")).click();

        WebElement MainTitle =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("h1")
                        )
                );

        WebElement newElement =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[@data-name='" + name + "']")
                        )
                );


        Assertions.assertThat(
                newElement.getAttribute("data-name"))
                .as("Check name")
                .isEqualTo(name);


        Assertions.assertThat(
                        newElement.getAttribute("data-price"))
                .as("Check price")
                .isEqualTo("123");

    }



    @DisplayName("Task1.2")
    @Test
    void addGoodToBasket() {

        WebElement element = driver.findElement(By.xpath("(//div[contains(@class, 'product-card')])[1]"));

        element.findElement(By.xpath(".//button[@data-action='add-to-cart']")).click();

        driver.findElement(By.xpath("//button[@id='open-cart-btn']")).click();

        WebElement busketElement = driver.findElement(By.xpath("//div[contains(@class, 'cart-item')]"));



        Assertions.assertThat(busketElement
                .findElement(By.xpath(".//b")).getText())
                .as("in busket same product")
                .isEqualTo(
                        element.findElement(By.xpath(".//h4")).getText()
                );
    }

    @DisplayName("Task1.3")
    @Test
    void singUpWrong() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(
                By.xpath("//a[@href='/admin']")
        ).click();

        driver.findElement(By.id("username"))
                .sendKeys("admin123");

        driver.findElement(By.id("password"))
                .sendKeys("secret");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement alert =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("div[role='alert']")
                        )
                );

        Assertions.assertThat(alert.getText())
                .as("Alert")
                .isEqualTo("Неверные учетные данные пользователя");
    }


    @DisplayName("Task1.4")
    @Test
    void refreshBucket() {


        WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'product-card')]"));

        element.findElement(By.xpath(".//button[@data-action='add-to-cart']")).click();

        driver.findElement(By.xpath("//button[@id='open-cart-btn']")).click();

        WebElement busketElement = driver.findElement(By.xpath("//div[contains(@class, 'cart-item')]"));



        Assertions.assertThat(busketElement
                        .findElement(By.xpath(".//b")).getText())
                .as("in busket same product")
                .isEqualTo(
                        element.findElement(By.xpath(".//h4")).getText()
                );

        driver.navigate().refresh();

        driver.findElement(By.xpath("//button[@id='open-cart-btn']")).click();


        Assertions.assertThat(
                        driver.findElements(
                                By.xpath("//div[contains(@class, 'cart-item')]")
                        )
                )
                .as("Save in bucket")
                .isNotEmpty();

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
