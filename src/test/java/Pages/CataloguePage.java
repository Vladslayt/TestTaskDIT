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
    private final String URL_CATALOGUE;
    public final Locator basketButton;

    public CataloguePage(Page page, Config config) {
        this.page = page;
        this.config = config;
        this.URL_CATALOGUE = config.getString("mainPage") + "category.html/";
        basketButton = page.locator("//div[@id='navbar']//a[@href=\"basket.html\"]");
    }

    /**
     * Переход на страницу каталога
     */
    @Step("go to catalogue page")
    public CataloguePage navigate() {
        page.navigate(URL_CATALOGUE);
        return this;
    }

    /**
     * Добавление в корзину носков
     *
     * @return объект, с которым работали
     */
    @Step("add socks in basket")
    public CataloguePage addSocksIntoBasket() {
        addProductIntoBasket(1);
        addProductIntoBasket(2);
        addProductIntoBasket(3);

        addProductIntoBasket("Holy");
        addProductIntoBasket("Colourful");
        addProductIntoBasket("Crossed");
        return this;
    }

    /**
     * @param name название товара
     */
    @Step("add sock into basket")
    public void addProductIntoBasket(String name) {
        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(name)).
                locator("..").getByText("Add to cart").click();
    }

    /**
     * @param serialNumber порядковый номер товара
     */
    @Step("add sock into basket")
    public void addProductIntoBasket(int serialNumber) {
        page.locator(String.format("div:nth-child(%d) > .product > .text > .buttons > a:nth-child(2)", serialNumber)).click();
    }

    /**
     * Переход на страницу корзины
     */
    @Step("go to basket page")
    public void goToBasketPage() {
        basketButton.click();
    }
}
