package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.typesafe.config.Config;
import io.qameta.allure.Step;

/**
 * Страница каталога носков
 */
public class CataloguePage {
    private final Page page;
    private final Config config;
    public final Locator addIntoCartHolyButton;
    public final Locator addIntoCartColourfulButton;
    public final Locator addIntoCartCrossedButton;
    public final Locator basketButton;

    public CataloguePage(Page page, Config config) {
        this.page = page;
        this.config = config;

        addIntoCartHolyButton = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Holy")).
                locator("..").getByText("Add to cart");
        addIntoCartColourfulButton = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Colourful")).
                locator("..").getByText("Add to cart");
        addIntoCartCrossedButton = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Crossed")).
                locator("..").getByText("Add to cart");
        basketButton = page.locator("//div[@id='navbar']//a[@href=\"basket.html\"]");
    }

    /**
     * Переход на страницу каталога
     */
    @Step("go to catalogue page")
    public CataloguePage navigate() {
        page.navigate(config.getString("url.cataloguePage"));
        return this;
    }

    /**
     * Добавление в корзину носков
     *
     * @return объект, с которым работали
     */
    @Step("Add socks in basket")
    public CataloguePage addSocksIntoBasket() {
        addHolySockIntoBasket();
        addColourfulSockIntoBasket();
        addCrossedSockIntoBasket();
        return this;
    }

    /**
     * Добавление носка "Holy" в корзину
     */
    @Step("add holy sock in basket")
    public void addHolySockIntoBasket() {
        addIntoCartHolyButton.click();
    }

    /**
     * Добавление носка "Colourful" в корзину
     */
    @Step("add colourful sock in basket")
    public void addColourfulSockIntoBasket() {
        addIntoCartColourfulButton.click();
    }

    /**
     * Добавление носка "Crossed" в корзину
     */
    @Step("add crossed sock in basket")
    public void addCrossedSockIntoBasket() {
        addIntoCartCrossedButton.click();
    }

    /**
     * Переход на страницу корзины
     *
     * @return объект класса Pages.BasketPage
     */
    @Step("go to basket page")
    public BasketPage goToBasketPage() {
        basketButton.click();
        return new BasketPage(page, config);
    }
}