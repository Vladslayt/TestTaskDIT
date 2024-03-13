package Tests;

import com.microsoft.playwright.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    protected Page page;
    protected Config config;

    @BeforeAll
    public void launchBrowser() {
        config = ConfigFactory.load();
        playwright = Playwright.create();
        String strBrowser = config.getString("browser");
        switch (strBrowser) {
            case "firefox" -> browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            default -> browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }
    }

    @AfterAll
    public void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    public void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    public void closeContext() {
        context.close();
    }

}