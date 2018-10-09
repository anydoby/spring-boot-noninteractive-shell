package com.test;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * This application runner will invoke just one command and exit.
 * 
 * @author sergey
 */
@Component
@Order(InteractiveShellApplicationRunner.PRECEDENCE - 200)
public class NonInteractiveRunner implements ApplicationRunner {

    private Shell shell;
    private ConfigurableEnvironment environment;

    public NonInteractiveRunner(Shell shell, ConfigurableEnvironment environment) {
        this.shell = shell;
        this.environment = environment;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*
         * Empty args means we want to get into interactive mode with the InteractiveShellApplicationRunner
         * 
         * If all arguments are filenames with scripts then leave it to the ScriptShellApplicationRunner
         */
        if (args.getNonOptionArgs().isEmpty() || args.getNonOptionArgs().stream().allMatch(s -> s.startsWith("@"))) {
            return;
        } else {
            /*
             * Else just do the job and exit
             */
            InteractiveShellApplicationRunner.disable(environment);
            final Object evaluate = shell.evaluate(() -> String.join(" ", args.getSourceArgs()));
            if (evaluate != null) {
                System.out.println(evaluate);
            }
        }
    }

}
