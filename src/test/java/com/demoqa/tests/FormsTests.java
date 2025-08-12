package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.PracticeFormPage;
import com.demoqa.pages.SidePanel;
import com.demoqa.utils.MyArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FormsTests extends TestBase {

    PracticeFormPage practiceForm;

    @BeforeEach
    public void precondition() {
        practiceForm = new PracticeFormPage(driver);
        new HomePage(driver).selectForms();
        new SidePanel(driver).selectPracticeForm();
    }

    @Test
    public void createStudentAccountTest() {
        practiceForm.enterPersonalData("Robert","Wide","wide@gm.com","1234567890")
               .selectGender("Male")
               .typeOfDate("16 Aug 1987")
                .addSubjects(new String[]{"Maths","Chemistry"})
                .selectHobby(new String[]{"Sports","Reading"})
               .uploadFile("C:/Tools/1.jpg")
               .inputState("NCR")
                .inputCity("Delhi")
               .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form")
                ;
    }

    @RepeatedTest(value = 3,failureThreshold = 1,name = "{displayName}{currentRepetition}/{totalRepetitions}")
    @DisplayName("Check to - User can create student account and successful title is displayed")
    public void createStudentAccountWithSelectDateTest() {
        practiceForm.enterPersonalData("Robert","Wide","wide@gm.com","1234567890")
               .selectGender("Male")
                .selectDate("August","1987","16")
                .addSubjects(new String[]{"Maths","Chemistry"})
                .selectHobby(new String[]{"Sports","Reading"})
               .uploadFile("C:/Tools/1.jpg")
               .inputState("NCR")
                .inputCity("Delhi")
               .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form")
                ;
    }

    @ParameterizedTest
    @MethodSource("personalData")
    public void createStudentAccountWithParameterTest(String name, String lastName,
                                                      String email, String phone) {
        practiceForm.enterPersonalData(name,lastName,email,phone)
                .selectGender("Male")
                .selectDate("August","1987","16")
                .addSubjects(new String[]{"Maths","Chemistry"})
                .selectHobby(new String[]{"Sports","Reading"})
                .uploadFile("C:/Tools/1.jpg")
                .inputState("NCR")
                .inputCity("Delhi")
                .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form");
    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    public void createStudentAccountWithParameterFromClassTest(String name, String lastName,
                                                      String email, String phone) {
        practiceForm.enterPersonalData(name,lastName,email,phone)
                .selectGender("Male")
                .selectDate("August","1987","16")
                .addSubjects(new String[]{"Maths","Chemistry"})
                .selectHobby(new String[]{"Sports","Reading"})
                .uploadFile("C:/Tools/1.jpg")
                .inputState("NCR")
                .inputCity("Delhi")
                .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void createStudentAccountWithParameterFromCsvTest(String name, String lastName,
                                                      String email, String phone) {
        practiceForm.enterPersonalData(name,lastName,email,phone)
                .selectGender("Male")
                .selectDate("August","1987","16")
                .addSubjects(new String[]{"Maths","Chemistry"})
                .selectHobby(new String[]{"Sports","Reading"})
                .uploadFile("C:/Tools/1.jpg")
                .inputState("NCR")
                .inputCity("Delhi")
                .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form");
    }

    public static Stream<Arguments> personalData() {
        return Stream.of(
                arguments("Robert1","Weide","weide@gm.com","1234567890"),
                arguments("Robert2","Weide","weide@gm.com","1234567890"),
                arguments("Robert3","Weide","weide@gm.com","1234567890")
        );
    }
}
