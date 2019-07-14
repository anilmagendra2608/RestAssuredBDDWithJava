package testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * @author amagendra
 *
 */
/**
 * This class helps as running the cucumber feature and step definitions
 *
 */
@CucumberOptions(features="src/test/java/features", glue="stepDefinitions")
// Extending AbstractTestNGCucumberTests to run TestRunner using TestNG
public class TestRunner_testAPI extends AbstractTestNGCucumberTests {

}
