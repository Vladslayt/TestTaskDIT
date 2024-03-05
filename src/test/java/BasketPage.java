import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.util.List;

/**
 * Страница корзины
 */
public class BasketPage {
    private final Page page;
    public BasketPage(Page page){
        this.page = page;
    }

    /**
     * Получение значения суммы корзины
     * @return сумма корзины
     */
    @Step("Получение суммы корзины")
    public double takeTotalSum(){
        page.waitForCondition(() -> page.locator("#cartTotal").textContent().length() > 0);
        return Double.parseDouble(page.locator("#cartTotal").textContent().substring(1));
    }

    /**
     * Подсчет суммы всех товаров в корзине
     * @return сумма всех товаров корзины
     */
    @Step("Вычисление суммы всех товаров корзины")
    public double takeSumAllProduct(){
        double sum = 0;
        List<Locator> listLoc = page.locator("form")
                .filter(new Locator.FilterOptions().setHasText("Shopping cart Product"))
                .locator("tr").all();

        for (int i = 1; i < listLoc.size() - 1; i++){
            sum += Double.parseDouble(listLoc.get(i).locator("td").
                    nth(5).textContent().substring(1));
        }

        return sum;
    }
}
