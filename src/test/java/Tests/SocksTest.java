package Tests;

import Pages.BasketPage;
import Pages.CataloguePage;
import Pages.MainPage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;

class SocksTest extends BaseTest {
    /**
     * Проверка, что сумма цены товаров в корзине соответствует сумме цен заказанных товаров
     */
    @Owner("Vladislav Alpha")
    @Test
    void authorizationAndAddElemInCart() {
        new MainPage(page, config)
                .registrationUser()
                .clickOnCatalogueButton();

        new CataloguePage(page, config)
                .addSocksIntoBasket()
                .goToBasketPage();

        BasketPage basketPage = new BasketPage(page, config);

        basketPage.compareTotalSums(basketPage.takeTotalSum(), basketPage.takeSumAllProduct());
    }
}
