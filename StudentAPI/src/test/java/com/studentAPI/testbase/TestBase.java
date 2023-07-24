package com.studentAPI.testbase;

import com.studentAPI.constants.Path;
import com.studentAPI.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("student.BaseUrl"); //http://localhost
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("student.Port"));//8080
        RestAssured.basePath = Path.STUDENT; // /student
        //http://localhost:8080/student
    }
}
