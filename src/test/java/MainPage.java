import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import java.util.Locale;


/**
 * Главная страница сайта
 */
public class MainPage {
    private final Page page;
    public MainPage(Page page){
        this.page = page;
    }

    /**
     * Переход на главную страницу сайта
     * @return объект, с которым работали
     */
    public MainPage goToMainPage(){
        page.navigate("http://localhost/");
        return this;
    }

    /**
     * Регистрация пользователя
     * Заполняет все поля с помощью Faker
     * @return объект, с которым работали
     */
    @Step("Регистрация пользовтеля")
    public MainPage registrationUser(){
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register")).click();

        page.locator("#register-username-modal").fill(faker.name().username());
        page.getByPlaceholder("first name").fill(faker.name().firstName());
        page.getByPlaceholder("last name").fill(faker.name().lastName());
        page.getByPlaceholder("email").fill(fakeValuesService.regexify("[a-z1-9]{10}") + "@gmail.com");
        page.locator("#register-password-modal").fill(fakeValuesService.regexify("[a-z1-9]{8}"));

        page.locator("button").filter(new Locator.FilterOptions().setHasText("Register")).click();
        return this;
    }

    /**
     * Переход на страницу носка Colourful
     * @return объект класса SocksPage
     */
    @Step("Переход на SocksPage")
    public SocksPage goToColourfulSocksPage(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Colourful")).click();
        return new SocksPage(page);
    }

}
