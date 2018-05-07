package com.test;

import static java.util.Arrays.stream;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class MyAppTest {

    @Rule
    public SystemOutRule out = new SystemOutRule();

    @Test
    public void testMain() throws Exception {
        out.enableLog();
        MyApp.main("echo", "hello");
        Stream<String> lines = stream(out.getLogWithNormalizedLineSeparator().split("\n"));
        assertTrue(lines.anyMatch("hello"::equals));
    }

    @Test
    public void testMain_CommandRetursSomething() throws Exception {
        out.enableLog();
        MyApp.main("help", "echo");
        Stream<String> lines = stream(out.getLogWithNormalizedLineSeparator().split("\n"));
        assertTrue(lines.anyMatch("SYNOPSYS"::equals));
    }

    @Test
    public void testMain_Script() {
        out.enableLog();
        MyApp.main("@src/test/resources/script");
        Stream<String> lines = stream(out.getLogWithNormalizedLineSeparator().split("\n"));
        assertTrue(lines.anyMatch("foo"::equals));
    }

}
