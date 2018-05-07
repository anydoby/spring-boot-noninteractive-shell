package com.test;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MyCommands {

    @ShellMethod("Just a naive echo implementation")
    public void echo(String what) {
        System.out.println(what);
    }

}
