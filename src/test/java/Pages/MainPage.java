package Pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.typesafe.config.Config;
import io.qameta.allure.Step;

/**
 * Главная страница сайта
 */
public class MainPage {
    private final Page page;
    private final Config config;
    private final Faker faker = new Faker();
    public final Locator buttonRegistration;
    public final Locator registrationInputFieldUsername;
    public final Locator registrationInputFieldFirstName;
    public final Locator registrationInputFieldLastName;
    public final Locator registrationInputFieldEmail;
    public final Locator registrationInputFieldPassword;
    public final Locator registrationButtonRegister;
    public final Locator buttonCatalogue;

    public MainPage(Page page, Config config) {
        this.page = page;
        this.config = config;
        page.navigate(config.getString("url.mainPage"));

        buttonRegistration = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register"));
        registrationInputFieldUsername = page.locator("#register-username-modal");
        registrationInputFieldFirstName = page.getByPlaceholder("first name");
        registrationInputFieldLastName = page.getByPlaceholder("last name");
        registrationInputFieldEmail = page.getByPlaceholder("email");
        registrationInputFieldPassword = page.locator("#register-password-modal");
        registrationButtonRegister = page.locator("button").filter(new Locator.FilterOptions().setHasText("Register"));
        buttonCatalogue = page.locator("#tabCatalogue").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Catalogue"));
    }

    /**
     * Регистрация пользователя
     * Заполняет все поля с помощью Faker
     *
     * @return объект, с которым работали
     */
    @Step("registration user")
    public MainPage registrationUser() {
        buttonRegistration.click();

        registrationInputFieldUsername.fill(faker.name().username());
        registrationInputFieldFirstName.fill(faker.name().firstName());
        registrationInputFieldLastName.fill(faker.name().lastName());
        registrationInputFieldEmail.fill(faker.internet().emailAddress());
        registrationInputFieldPassword.fill(faker.internet().password());

        registrationButtonRegister.click();
        return this;
    }

    /**
     * Переход на страницу каталога
     *
     * @return объект класса Pages.CataloguePage
     */
    @Step("click on catalogue page")
    public CataloguePage clickOnCatalogueButton() {
        buttonCatalogue.click();
        return new CataloguePage(page, config);
    }
}