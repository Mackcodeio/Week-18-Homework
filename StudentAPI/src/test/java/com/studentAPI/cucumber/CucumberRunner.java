package com.studentAPI.cucumber;

import com.studentAPI.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature",
        tags = "@SMOKE, @Create, @Update(PUT), @Update(Patch),  @Delete")// tags are optional, can run from runner class


public class CucumberRunner extends TestBase {

}
