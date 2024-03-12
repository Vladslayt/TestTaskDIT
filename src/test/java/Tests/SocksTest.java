package Tests;

import Base.BaseTest;
import Pages.BasketPage;
import Pages.MainPage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SocksTest extends BaseTest {
    /**
     * Проверка, что сумма цены товаров в корзине соответствует сумме цен заказанных товаров
     */
    @Owner("Vladislav Alpha")
    @Test
    public void authorizationAndAddElemInCart() {
        BasketPage basketPage = new MainPage(page, config)
                .registrationUser()
                .clickOnCatalogueButton()
                .addSocksIntoBasket()
                .goToBasketPage();

        assertEquals(basketPage.takeTotalSum(), basketPage.takeSumAllProduct());
    }
}