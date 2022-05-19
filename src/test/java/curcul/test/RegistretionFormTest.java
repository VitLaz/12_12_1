package curcul.test;

import com.github.javafaker.Faker;
import curcul.page.RegistrationFromPage;
import curcul.test.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static java.lang.String.format;

public class RegistretionFormTest extends TestBase {
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            teleNumber = faker.numerify("##########"),
            day = "29",
            month = "October",
            year = "1990",
            gender = "Male",
            hobbies = "Reading",
            file = "lightning_PNG52.png",
            state = "NCR",
            city = "Delhi",
            subject = "Maths",
            currentAddress = faker.address().fullAddress();

    String expectedFullName = format("%s %s", firstName, lastName);
    String expectedBirthdayDate = format("%s %s,%s",day, month,year);
    String expectedStateAndCity = format("%s %s", state, city);

    @Test
    @DisplayName("Проверка формы регистрации")
    public void registrationFromTest () {
        RegistrationFromPage page = new RegistrationFromPage();
        page.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setBirthDate(day, month, year)
                .setTele(teleNumber)
                .setSubjects(subject)
                .setHobbies(hobbies)
                .setUploadFile(file)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .setSubmit()
                .checkForm("Student Name", expectedFullName)
                .checkForm("Student Email", email)
                .checkForm("Gender", gender)
                .checkForm("Mobile", teleNumber)
                .checkForm("Date of Birth", expectedBirthdayDate)
                .checkForm("Subjects", subject)
                .checkForm("Hobbies", hobbies)
                .checkForm("Picture", file)
                .checkForm("Address", currentAddress)
                .checkForm("State and City",expectedStateAndCity);
    }
}