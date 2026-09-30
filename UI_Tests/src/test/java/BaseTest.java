import com.codeborne.selenide.Configuration;
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
    }


    @BeforeEach
    void startTest() {
        open(ConfigProvider.CONFIG.Url());
    }
}
