import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SocksTest extends BaseTest{
    /**
     * Проверка, что сумма цены товаровв корзине соответствует сумме цен заказанных товаров
     */
    @Test
    public void authorizationAndAddElemInCart(){
        BasketPage basketPage = new MainPage(page)
                .goToMainPage()
                .registrationUser()
                .goToColourfulSocksPage()

                .addSocksInBasket()
                .goToBasketPage();

        assertEquals(basketPage.takeTotalSum(), basketPage.takeSumAllProduct());
    }
}
