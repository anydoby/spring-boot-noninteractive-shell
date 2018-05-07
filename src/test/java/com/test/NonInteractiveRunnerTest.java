package com.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.Shell;

@RunWith(MockitoJUnitRunner.class)
public class NonInteractiveRunnerTest {

    @Mock
    Shell shell;

    @Mock
    ConfigurableEnvironment env;

    private NonInteractiveRunner runner;

    private ApplicationArguments args;

    @Before
    public void pre() {
        runner = new NonInteractiveRunner(shell, env);
        args = mock(ApplicationArguments.class);
    }

    @Test
    public void testRun_Empty() throws Exception {
        runner.run(args);
        verifyZeroInteractions(shell, env);
    }

}
