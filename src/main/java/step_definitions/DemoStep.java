package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class DemoStep {
    @Given("setupDevice {string}")
    public void setupDevice(String typeStr) throws Throwable {
        System.out.println("init:" + typeStr);
    }

    @When("click {int},{int}")
    public void click(Integer int1, Integer int2) throws Throwable {
        System.out.println("click " + int1 + "," + int2);
    }

    @When("clickElement {string}")
    public void clickElement(String id) throws Throwable {
        System.out.println("clickElement " + id);
    }

    @When("doubleClick {int},{int}")
    public void doubleClick(int xCoordinate, int yCoordinate) throws Throwable {
        System.out.println("doubleClick " + xCoordinate + "," + yCoordinate);
        assertEquals(1,2);
    }

    @When("doubleClickElement {string}")
    public void doubleClickElement(String id) throws Throwable {
        System.out.println("doubleClickElement " + id);
    }

    @Then("result")
    public void result() throws Throwable {
        System.out.println("result");
    }
}
