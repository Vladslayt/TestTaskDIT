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
    private final String urlCatalogue;
    private final Locator basketButton;

    public CataloguePage(Page page, Config config) {
        this.page = page;
        this.config = config;
        this.urlCatalogue = config.getString("mainPage") + "category.html/";
        basketButton = page.locator("//div[@id='navbar']//a[@href=\"basket.html\"]");
    }

    /**
     * Переход на страницу каталога
     */
    @Step("go to catalogue page")
    public CataloguePage navigate() {
        page.navigate(urlCatalogue);
        return this;
    }

    /**
     * @param name название товара
     */
    @Step("add sock into basket")
    public CataloguePage addProductIntoBasket(String name) {
        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(name)).
                locator("..").getByText("Add to cart").click();
        return this;
    }

    /**
     * @param serialNumber порядковый номер товара
     */
    @Step("add sock into basket")
    public CataloguePage addProductIntoBasket(int serialNumber) {
        page.locator(String.format("div:nth-child(%d) > .product > .text > .buttons > a:nth-child(2)", serialNumber)).click();
        return this;
    }

    /**
     * Переход на страницу корзины
     */
    @Step("go to basket page")
    public void goToBasketPage() {
        basketButton.click();
    }
}
