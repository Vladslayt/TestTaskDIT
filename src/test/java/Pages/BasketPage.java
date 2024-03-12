package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.typesafe.config.Config;
import io.qameta.allure.Step;

import java.util.List;

/**
 * Страница корзины
 */
public class BasketPage {
    private final Page page;
    private final Config config;

    public BasketPage(Page page, Config config) {
        this.page = page;
        this.config = config;
    }

    /**
     * Переход на страницу корзины
     */
    @Step("go to basket page")
    public BasketPage navigate() {
        page.navigate(config.getString("url.basketPage"));
        return this;
    }

    /**
     * Получение значения суммы корзины
     *
     * @return сумма корзины
     */
    @Step("Get sum basket")
    public double takeTotalSum() {
        page.waitForCondition(() -> page.locator("#cartTotal").textContent().length() > 0);
        return Double.parseDouble(page.locator("#cartTotal").textContent().substring(1));
    }

    /**
     * Подсчет суммы всех товаров в корзине
     *
     * @return сумма всех товаров корзины
     */
    @Step("Calculating sum all product basket")
    public double takeSumAllProduct() {
        double sum = 0;
        List<Locator> listLoc = page.locator("form")
                .filter(new Locator.FilterOptions().setHasText("Shopping cart Product"))
                .locator("tr").all();

        for (int i = 1; i < listLoc.size() - 1; i++) {
            sum += Double.parseDouble(listLoc.get(i).locator("td").
                    nth(5).textContent().substring(1));
        }

        return sum;
    }
}