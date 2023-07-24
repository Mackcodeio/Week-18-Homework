package com.studentAPI.cucumber.steps;

import com.studentAPI.studentinfo.StudentCURD;
import com.studentAPI.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

public class StudentStepsCRED {

    static String firstName;
    static String newEmail;
    static ValidatableResponse response;

    static int studentID;
    @Steps
    StudentCURD studentCURD;

    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {
        response = studentCURD.getAllStudentInfo();

    }

    @Then("^User must get back a valid status code \"([^\"]*)\"$")
    public void userMustGetBackAValidStatusCode(int statusCode) throws Throwable {
        response.statusCode(statusCode);
    }

    @When("^I create a new student by providing the infromation firstname \"([^\"]*)\" lastname \"([^\"]*)\" email \"([^\"]*)\" programme \"([^\"]*)\" courses \"([^\"]*)\"$")
    public void iCreateANewStudentByProvidingTheInfromationFirstnameLastnameEmailProgrammeCourses(String firstName1, String lastName, String email1, String programme, String course) {
        List<String> courseList = new ArrayList<>();
        courseList.add(course);
        firstName = TestUtils.getRandomValue() + firstName1;
        newEmail = TestUtils.getRandomValue() + email1; //123codebuster1@gmail.com
        response = studentCURD.createStudent(firstName,lastName,newEmail,programme,courseList);
//        studentID = response.extract().path("id");
//        System.out.println("----------studentID---"+studentID);
    }

    @Then("^I verify that the student with email is created$")
    public void iVerifyThatTheStudentWithEmailIsCreated() {
        HashMap<String,Object> studentDetails = studentCURD.getStudentByEmail(newEmail);
        Assert.assertThat(studentDetails,hasValue(newEmail));
        studentID = (int)studentDetails.get("id");
    }

    @When("^I give data infromation to update firstname \"([^\"]*)\" lastname \"([^\"]*)\" email\"([^\"]*)\" programme \"([^\"]*)\" courses \"([^\"]*)\"$")
    public void iGiveDataInfromationToUpdateFirstnameLastnameEmailProgrammeCourses(String firstname, String lastname, String email, String programme, String course)  {

        System.out.println("-------------------in Update----------------");
        List<String> courseList = new ArrayList<>();
        courseList.add(course);
        courseList.add("Postman");
        courseList.add("API");
        response = studentCURD.updateStudent(studentID,firstname,lastname,email,programme,courseList);
        System.out.println("-------------------Out Update----------------");
        System.out.println("response "+response);
    }

    @When("^I give data infromation to update firstname \"([^\"]*)\" email\"([^\"]*)\"$")
    public void iGiveDataInfromationToUpdateFirstnameEmail(String firstName, String email) {
        response = studentCURD.updateByPatch(studentID,firstName,email);

        //System.out.println("--------------response-----------------"+response);

//        HashMap<String,Object> studentDetails = studentCURD.getStudentByEmail(newEmail);
//        System.out.println("--------studentDetails---------"studentDetails);
//        Assert.assertThat(studentDetails,hasValue(newEmail));

    }


    @Then("^I verify that the student with email is updated$")
    public void iVerifyThatTheStudentWithEmailIsUpdated() {
        HashMap<String,Object> studentDetails = (HashMap<String, Object>) StudentCURD.getSingleStudentInfo(studentID);
        Assert.assertThat(studentDetails,hasValue(newEmail));

    }

    @When("^User sends a DELETE request to endpoint$")
    public void userSendsADELETERequestToEndpoint() {
        response = studentCURD.deleteStudent(studentID);
    }


}
