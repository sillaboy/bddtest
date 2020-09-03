package step_definitions;

import implementation.CommandStr;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * biginteger	"-?\d+" 或者 "\d+"
 * string	""([^"\\]*(\\.[^"\\]*)*)"|'([^'\\]*(\\.[^'\\]*)*)'"
 * bigdecimal	"-?\d*[.,]\d+"
 * byte	"-?\d+" 或者 "\d+"
 * double	"-?\d*[.,]\d+"
 * short	"-?\d+" 或者 "\d+"
 * float	"-?\d*[.,]\d+"
 * word	"\w+"
 * int	"-?\d+" 或者 "\d+"
 * long	"-?\d+" 或者 "\d+"
 * Object callRemote(Object instance, String sMethod, Object... arguments) throws Exception {
 *     Class<?>[] argumentTypes = createArgumentTypes(arguments);
 *     Method method = instance.getClass().getMethod(sMethod, argumentTypes );
 *     Object[] argumentsWithSession = createArguments(arguments);
 *     return method.invoke(instance, argumentsWithSession);
 * }
 *
 * Object[] createArguments(Object[] arguments) {
 *     Object[] args = new Object[arguments.length + 1];
 *     System.arraycopy(arguments, 0, args, 0, arguments.length);
 *     args[arguments.length] = sessionId;
 *     return args;
 * }
 *
 * Class<?>[] createArgumentTypes(Object[] arguments) {
 *     Class[] types = new Class[arguments.length + 1];
 *     for (int i = 0; i < arguments.length; i++) {
 *         types[i] = arguments[i].getClass();
 *     }
 *     types[arguments.length] = int.class;
 *     return types;
 * }
 */

public class Demo2Step {
    Pattern ACTIONS = Pattern
            .compile("\\[(.+)\\]");
    Pattern ONE_ACTION = Pattern.compile("(\\w+)\\s*\\(([\\w,.\"\\s]*)\\)");
    Pattern INTEGER = Pattern.compile("\\d+");
    Pattern DECIMAL = Pattern.compile("\\d+[.]\\d+");

    private List<CommandStr> list ;

    @When("click\\({int}, {int})")
    public void click(int xCoordinate, int yCoordinate) throws Throwable {
        System.out.println("click " + xCoordinate + "," + yCoordinate);
    }

    @When("clickElement\\({string})")
    public void clickElement(String id) throws Throwable {
        System.out.println("clickElement " + id);
        //assertEquals(1, 2);
    }

    @Then("click\\({int}, {string}, {float})")
    public void click(int x, String res, float fd) {
        System.out.println(x + "," + res + "," + fd);
    }

    @Then("wait\\({int})")
    public void wait(int x) {
        System.out.println("wait:" + x);
    }

    @Then("loop\\({}, {int})")
    public void loop(String param, int times) {
        Matcher matcher = ACTIONS.matcher(param);
        if (matcher.matches()) {
            String list = matcher.group(1);
            for (int i = 0; i < times; i++) {
                startActions(list);
            }
        }
    }

    @Given("initCmdList")
    public void initcmdlist(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        list = new ArrayList<>();
        for (List<String> columns : rows) {
            list.add(new CommandStr(columns.get(0), columns.get(1)));
        }
        for (CommandStr cmd : list) {
            System.out.println(cmd.toString());
        }
    }
    @When("executeCmd\\({string})")
    public void executeCmd (String cmdMethod) {
        for (CommandStr commandStr : list) {
            if (commandStr.getCmd().equals(cmdMethod)){
                System.out.println("execute cmd :" + commandStr.getCmd() + ", params=" + commandStr.getArgs());
            }
        }
    }

    @When("startActions\\({})")
    private void startActions(String actions) {
        Matcher funMatcher = ONE_ACTION.matcher(actions);
        while (funMatcher.find()) {
            String params = funMatcher.group(2).replaceAll("\\s*", "");
            String[] paramArray = params.split(",");
            List<Class<?>> paramTypes = new ArrayList<>();
            List<Object> values = new ArrayList<>();
            for (String oneParam : paramArray) {
                Matcher intMatcher = INTEGER.matcher(oneParam);
                Matcher decMatcher = DECIMAL.matcher(oneParam);
                if (intMatcher.matches()) {
                    paramTypes.add(int.class);
                    values.add((int)(Integer.parseInt(oneParam)));
                } else if (decMatcher.matches()) {
                    paramTypes.add(float.class);
                    values.add((float)Float.parseFloat(oneParam));
                } else {
                    paramTypes.add(String.class);
                    String paramtmp = oneParam.substring(1,oneParam.length()-1);
                    values.add(paramtmp);
                }
            }
            try {
                Method methodBook = Demo2Step.class.getDeclaredMethod(funMatcher.group(1),
                        paramTypes.toArray(new Class[0]));
                if (methodBook != null) {
                    int paramCount = methodBook.getParameterCount();
                    if (paramCount > 0) {
                        methodBook.invoke(this, values.toArray());
                    } else {
                        methodBook.invoke(this);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
