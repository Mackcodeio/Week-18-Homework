package com.studentAPI.studentinfo;

import com.studentAPI.constants.EndPoints;
import com.studentAPI.model.StudentPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

import static net.serenitybdd.rest.RestRequests.given;

public class StudentCURD {
    @Step("Getting all Student infromation")
    public ValidatableResponse getAllStudentInfo(){
        return given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200);
    }

    @Step("Create New Student")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courseList){
        StudentPojo pojo=new StudentPojo();
        pojo.setFirstName(firstName);
        pojo.setLastName(lastName);
        pojo.setEmail(email);
        pojo.setProgramme(programme);
        pojo.setCourses(courseList);



        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(pojo)
                .when()
                .post()
                .then().log().all();
    }

    @Step("Getting response by email")
    public HashMap<String, Object> getStudentByEmail(String email) {
        String p1 = "findAll{it.email=='";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + email + p2);
    }

    @Step("Update student by email, programme")
    public ValidatableResponse updateStudent(int studentID, String firstName, String lastName, String email, String progrmmer, List<String> courseList){
        StudentPojo pojo = new StudentPojo();
        pojo.setFirstName(firstName);
        pojo.setLastName(lastName);
        pojo.setEmail(email);
        pojo.setProgramme(progrmmer);
        pojo.setCourses(courseList);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("studentID",studentID)
                .body(pojo)
                .when()
                .put(EndPoints.UPDATE_STUDENT_BY_ID)
                .then().log().all();
    }

    public ValidatableResponse updateByPatch(int studentID,String firstName, String email){
        StudentPojo pojo = new StudentPojo();
        pojo.setFirstName(firstName);;
        pojo.setEmail(email);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("studentID",studentID)
                .body(pojo)
                .when()
                .patch(EndPoints.UPDATE_STUDENT_BY_ID)
                .then();

    }

    public static ValidatableResponse getSingleStudentInfo(int studentID){
        return SerenityRest.given()
                .pathParam("studentID",studentID)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
                .then()
                .statusCode(200);
    }

    @Step("Delete Student by ID")
    public ValidatableResponse deleteStudent(int studentID){
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("studentID",studentID)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then();

    }
}
