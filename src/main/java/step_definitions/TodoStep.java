package step_definitions;
import implementation.TodoList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.zh_cn.*;
import static org.junit.Assert.assertEquals;

public class TodoStep {
    implementation.TodoList todo;
    @假设("^我的任务清单里有(\\d+)个任务$")
    public void iHaveSomeTasks(int totalTasks) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        todo = new TodoList();
        assertEquals(todo.getTotalTaskCount(), totalTasks);
    }

    @当("^我完成(\\d+)件任务之后$")
    public void iFinishSomeTasks(int finishedTasks) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        todo.finishTask(finishedTasks);
    }

    @那么("^我还剩下(\\d+)件未完成的任务$")
    public void iLeftSomeTasks(int leftTasks) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(todo.getRestTasksCount(), leftTasks);
    }

    @Given("there are {int} cucumbers")
    public void there_are_cucumbers(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("there_are_cucumbers:" + int1);
    }

    @When("I eat {int} cucumbers")
    public void i_eat_cucumbers(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("i_eat_cucumbers:" + int1);
    }

    @Then("I should have {int} cucumbers")
    public void i_should_have_cucumbers(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("i_should_have_cucumbers:" + int1);
    }
}