package cucumber.Options;

import java.io.File;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
monochrome= true, glue= {"stepDefinations"})
public class TestRunner {
	//,tags= "@DeletePlace"
    }


