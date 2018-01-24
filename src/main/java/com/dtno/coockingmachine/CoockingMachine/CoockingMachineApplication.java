package com.dtno.coockingmachine.CoockingMachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class CoockingMachineApplication {

    private static Logger log = Logger.getLogger(CoockingMachineApplication.class.getName());
    public static void main(String[] args) {
        log.info("Application starting;");
        SpringApplication.run(CoockingMachineApplication.class, args);


    }
}
