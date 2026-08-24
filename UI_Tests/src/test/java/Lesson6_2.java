
import static com.Utils.*;
import static com.codeborne.selenide.Condition.*;
import static org.assertj.core.api.Assertions.assertThat;
import com.Utils;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import java.util.Random;
import java.math.BigDecimal;

import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Tag("UI-test")
public class Lesson6_2 {




    @BeforeEach
    void setup(){
        Selenide.open("http://localhost:8080");
    }

    @DisplayName("add new product Selenide")
    @Order(1)
    @Test
    void addProduct(){

        String name = "NewName" + new Random().nextInt(0,100);
        String price = "150";

        signIn("admin", "secret123");
        $x("//div[contains(@class, 'container')]")
                .should(exist);

        nameField.sendKeys(name);
        priceField.sendKeys(price);
        addNewProductBtn.click();


        returnToMainBtn.click();

        productList.$x(
                        ".//div[@data-name='" + name + "'" +
                                " and @data-price='" + price + "']"
                )
                .shouldBe(visible);

    }


    @DisplayName("Add to bucket and check saving")
    @Test
    void addToBucket() {
        SelenideElement firstProduct =
               firstProduct();

        String productName =
                Utils.getProductName(firstProduct);


        addToCart(firstProduct);

        $x("//div[contains(@class, 'toast')]")
                .shouldBe(visible);

        bucketBtn.click();

        itemInBucket
                .shouldBe(visible)
                .shouldHave(text(productName));







    }

    @DisplayName("Bed credentials")
    @Test
    void bedCredentialsTest() {
        signIn("login", "password");
        $x("//div[contains(@class, 'alert')]")
                .shouldBe(visible)
                .shouldHave(text("Неверные учетные данные пользователя"));
    }

    @DisplayName("Refresh bucket")
    @Test
    void refreshBucketTest() {
        SelenideElement firstProduct =
                firstProduct();

        String productName =
                getProductName(firstProduct);


        addToCart(firstProduct);

        $x("//div[contains(@class, 'toast')]")
                .shouldBe(visible)
                .shouldHave(text(productName + "  (1 шт.) добавлен в корзину")); //как будто излишне

        bucketBtn.click();

        itemInBucket
                .shouldBe(visible)
                .shouldHave(text(productName));

        refresh();

        bucketBtn.click();

        itemInBucket
                .shouldBe(visible)
                .shouldHave(text(productName));





    }


    @DisplayName("Bucket > 300")
    @Test
    void buyGreater300() {
        SelenideElement product = firstProduct();
        String productName = getProductName(product);
        int productPrice = getProductPrice(product);

        int quantity = (301 + productPrice - 1) / productPrice;

        for (int i = 0; i <= quantity; i++) {
            product
                    .$x(".//button[@data-action='add-to-cart']")
                    .click();
        }

        addToCart(product);
        bucketBtn.click();

        itemInBucket
                .shouldBe(visible)
                .shouldHave(text(productName));

        $("#total-price").shouldHave(
                match("Total price might be greater then 300",
                        element -> Integer.parseInt(element.getText()) > 300
                )
        );

        makeOrder.click();

        sleep(3000);

        confirm();

        sleep(3000);
    }

    @AfterEach
    void tearDown() {
        cookies().clear();
        closeWebDriver();
    }

}
