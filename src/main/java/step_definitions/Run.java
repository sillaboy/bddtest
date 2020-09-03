package step_definitions;

import io.cucumber.java.an.E;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.CountDownLatch;

//main
//https://stackoverflow.com/questions/47486573/running-cucumber-project-using-main-run-from-another-main-method
//report
//https://stackoverflow.com/questions/46117883/execute-code-after-cucumber-cli-main-method-to-generate-report
//plugin
//https://cucumber.io/docs/cucumber/reporting/
public class Run {
    public static void main(String[] args) {
//        String[] cmds = {"-g","" // to used Step definitions in default package
//                , "--plugin", "progress","src/main/resources/feature/todo.feature"};
//        String[] cmds = {"--plugin", "progress", "-t", "@Tag0",
//                "src/main/resources/feature/login.feature"};
        String[] cmds = {"--plugin ", "html:result/aa.html",
                "src/main/resources/feature/ff.feature"};
//        String[] cmds = { "" // to used Step definitions in default package
//                ,"--plugin", "report.json" ,"src/main/resources/feature/todo.feature"};

        final String[] ret = {""};
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                PrintStream old = System.out;
                System.setOut(ps);
                byte exitStatus = io.cucumber.core.cli.Main.run(cmds, Thread.currentThread().getContextClassLoader());
                System.out.flush();
                System.setOut(old);
                ret[0] = baos.toString();
                countDownLatch.countDown();
                System.exit(exitStatus);
            }
        }).start();
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ret[0]);
    }
}
