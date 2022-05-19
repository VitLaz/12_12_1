package curcul.page;

import com.codeborne.selenide.SelenideElement;
import curcul.page.component.Calendar;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFromPage {

    Calendar calendar = new Calendar();

    private SelenideElement firstNameLoc = $("#firstName"),
            lastNameLoc = $("#lastName"),
            emailLoc = $("#userEmail"),
            genderLoc = $("#genterWrapper"),
            teleNumberLoc = $("#userNumber"),
            birthDateLoc = $("#dateOfBirthInput"),
            subjectLoc = $("#subjectsInput"),
            hobbiesLoc = $("#hobbiesWrapper"),
            pictureLoc = $("#uploadPicture"),
            addressLoc = $("#currentAddress"),
            stateLoc =$("#state"),
            cityLoc = $("#city"),
            submitLoc =$("#submit"),
            resultsTableLoc = $(".table-responsive");





    @Step("Открываем страницу")
    public RegistrationFromPage openPage () {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    @Step("Вводим имя")
    public RegistrationFromPage setFirstName (String firstName) {
        firstNameLoc.setValue(firstName);
        return this;
    }

    @Step("Вводим фамилию")
    public RegistrationFromPage setLastName (String lastName){
        lastNameLoc.setValue(lastName);
        return this;
    }

    @Step("Вводим почту")
    public RegistrationFromPage setEmail (String email) {
        emailLoc.setValue(email);
        return this;
    }

    @Step("Вводим пол")
    public RegistrationFromPage setGender (String gender) {
        genderLoc.$(byText(gender)).click();
        return this;
    }

    @Step("Вводим телефон")
    public RegistrationFromPage setTele (String teleNumber) {
        teleNumberLoc.setValue(teleNumber);
        return this;
    }

    @Step("Вводим день рождение")
    public RegistrationFromPage setBirthDate (String day, String month, String year) {
        birthDateLoc.click();
        calendar.setDate(day, month, year);
        return this;
    }

    @Step("Вводим предметы")
    public RegistrationFromPage setSubjects (String subject) {
        subjectLoc.setValue(subject).pressEnter();
        return this;
    }

    @Step("Вводим хобби")
    public RegistrationFromPage setHobbies (String hobbies) {
        hobbiesLoc.$(byText(hobbies)).click();
        return this;
    }

    @Step("Вставляем файл")
    public RegistrationFromPage setUploadFile (String file) {
        pictureLoc.uploadFromClasspath(file);
        return this;
    }

    @Step("Вводим адрес")
    public RegistrationFromPage setCurrentAddress (String currentAddress) {
        addressLoc.setValue(currentAddress);
        return this;
    }

    @Step("Выбираем штат")
    public RegistrationFromPage setState (String state) {
        stateLoc.click();
        $(byText(state)).click();
        return this;
    }

    @Step("Выбираем город")
    public RegistrationFromPage setCity (String city) {
        cityLoc.click();
        $(byText(city)).click();
        return this;
    }

    @Step("Нажимаем на кнопку")
    public RegistrationFromPage setSubmit (){
        submitLoc.click();
        return this;
    }

    @Step("Проверяем отображение введенных данных")
    public RegistrationFromPage checkForm (String fieldName, String value) {
        resultsTableLoc.$(byText(fieldName)).parent().shouldHave(text(value));
        return this;
    }
}
