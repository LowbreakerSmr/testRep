package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class LoginPageMesto {

    private WebDriver driver;
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By signInButton = By.className("auth-form__button");

    public LoginPageMesto(WebDriver driver){
        this.driver = driver;
    }

    public void setUsername(String username) {
        driver.findElement(emailField).sendKeys(username);
    }
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }
    public void login(String username, String password){
        setUsername(username);
        setPassword(password);
        clickSignInButton();
    }
}

// Класс главной страницы
class HomePageMesto {

    private WebDriver driver;

    private By profileTitle = By.className("profile__title");
    // создай локатор для кнопки редактирования профиля
    private By editProfileButton = By.className("profile__edit-button");

    // создай локатор для поля «Занятие» в профиле пользователя
    private By activity = By.className("profile__description");

    public HomePageMesto(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания прогрузки данных профиля
    public void waitForLoadProfileData() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(profileTitle).getText() != null
                && !driver.findElement(profileTitle).getText().isEmpty()
        ));
    }
    // метод для нажатия на кнопку редактирования профиля
    public void clickEditButton() {
        driver.findElement(editProfileButton).click();
    }

    public void waitForChangedActivity(String changed) {
        // здесь нужно дождаться, чтобы текст в элементе «Занятие» стал равен значению из параметра
        new WebDriverWait(driver, 5).until(ExpectedConditions.textToBePresentInElementLocated(activity, "Тестировщик"));
    }
}

// Класс cтраницы редактирования профиля
class ProfilePageMesto {

    private WebDriver driver;
    // создай локатор для поля «Занятие» в профиле пользователя
    private By activity = By.id("owner-description");
    //private By activity = By.cssSelector(".popup__input popup__input_type_description");
    // создай локатор для кнопки «Сохранить» в профиле пользователя
    private By save = By.cssSelector(".popup__button");

    //private By save = By.className("button popup__button");

    public ProfilePageMesto (WebDriver driver){
        this.driver = driver;
    }

    // метод для проверки открытости поля «Занятие», удаления текста из неё и ввода нового значения из параметра
    public void editAvailable() {
        driver.findElement(activity).isEnabled();
    }

    public void deleteValue() {
        driver.findElement(activity).clear();
    }
    public void enterNewValue(String newActivity) {
        driver.findElement(activity).sendKeys(newActivity);
    }
    public void newValue(String newActivity){
        editAvailable();
        deleteValue();
        enterNewValue(newActivity);
    }

    // метод для нажатия на кнопку сохранения профиля
    public void isActive() {
        driver.findElement(save).isEnabled();
    }
    public void isSave() {
        driver.findElement(save).click();
    }
    public void clickSave() {
        isActive();
        isSave();
    }
}