import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

/**
 * Страница выбранного носка
 */
public class SocksPage {
    private final Page page;
    public SocksPage(Page page){
        this.page = page;
    }

    /**
     * Добавление в корзину три носка
     * @return объект, с которым работали
     */
    @Step("Добавление носков в корзину")
    public SocksPage addSocksInBasket(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add to cart")).nth(0).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add to cart")).nth(1).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add to cart")).nth(2).click();
        return this;
    }

    /**
     * Переход на страницу корзины
     * @return объект класса BasketPage
     */
    @Step("Переход на BasketPage")
    public BasketPage goToBasketPage(){
        page.locator("//div[@id='navbar']//a[@href=\"basket.html\"]").click();
        return new BasketPage(page);
    }

}
