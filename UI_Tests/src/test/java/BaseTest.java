import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.CartPage;
import pages.MainPage;
import static com.codeborne.selenide.Selenide.*;
import config.ConfigProvider;
import rest.endpoints.GoodsApi;


public class BaseTest {
    protected MainPage mainPage = new MainPage();
    protected CartPage cartPopup = new CartPage(mainPage);
    static GoodsApi goodsApi = new GoodsApi();

    @BeforeAll
    static void configSettings() {
        ConfigProvider.printConfigParams();
        Configuration.timeout = ConfigProvider.CONFIG.timeout();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }


    @BeforeEach
    void startTest() {
        open(ConfigProvider.CONFIG.Url());
    }
}
