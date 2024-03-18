package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.typesafe.config.Config;
import io.qameta.allure.Step;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Страница корзины
 */
public class BasketPage {
    private final Page page;
    private final Config config;
    private final String urlBasket;
    private final Locator totalSumCart;
    private final Locator tableElementsCart;

    public BasketPage(Page page, Config config) {
        this.page = page;
        this.config = config;
        this.urlBasket = config.getString("mainPage") + "category.html/";

        totalSumCart = page.locator("#cartTotal");
        tableElementsCart = page.locator("form").
                filter(new Locator.FilterOptions().setHasText("Shopping cart Product"))
                .locator("tr");
    }

    /**
     * Переход на страницу корзины
     */
    @Step("go to basket page")
    public BasketPage navigate() {
        page.navigate(urlBasket);
        return this;
    }

    /**
     * Получение значения суммы корзины
     *
     * @return сумма корзины
     */
    @Step("get sum basket")
    public float takeTotalSum() {
        page.waitForCondition(() -> totalSumCart.textContent().length() > 0);
        return Float.parseFloat(totalSumCart.textContent().substring(1));
    }

    /**
     * Подсчет суммы всех товаров в корзине
     *
     * @return сумма всех товаров корзины
     */
    @Step("calculating the sum of the total basket of products")
    public float takeSumAllProduct() {
        float sum = 0;
        List<Locator> listLoc = tableElementsCart.all();

        for (int i = 1; i < listLoc.size() - 1; i++) {
            sum += Double.parseDouble(listLoc.get(i).locator("td").
                    nth(5).textContent().substring(1));
        }

        return sum;
    }

    /**
     *  Сравнение суммы цены товаров в корзине и суммы цен заказанных товаров
     */
    @Step("compare total sum and sum all product")
    public void compareTotalSums(float totalSum, float sumAllProduct) {
        assertEquals(totalSum, sumAllProduct);
    }
}
