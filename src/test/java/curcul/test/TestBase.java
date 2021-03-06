package curcul.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import curcul.config.CredentialsConfig;
import curcul.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;




import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        String login = config.login();
        String password = config.password();
        String baseURL = System.getProperty("baseURL", "https://demoqa.com");
        String browserSize = System.getProperty("browserSize","1920x1080");
        String remote = System.getProperty("remote","selenoid.autotests.cloud/wd/hub");
        String browser = System.getProperty("browser","chrome");

        Configuration.baseUrl = baseURL;
        Configuration.browserSize = browserSize;
        Configuration.remote = String.format("https://%s:%s@%s", login, password, remote);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
