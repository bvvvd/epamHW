package com.epam.java.se.task6andtask7;

/**
 * Created by chris on 28.02.2017.
 */
@Schooner(
        author = "Valeriy Burmisrov",
        date = "28.02.2017",
        version = "0.00000001"
)
public class AtomicSubmarine {

    private String status;
    private Engine engine;


    public AtomicSubmarine() {
        engine = new Engine();
        status = "not launched";
    }


    public String getStatus() {
        return status;
    }


    public void start() {
        engine.turnOn();
    }

    public String getEngineStatus() {
        return engine.getStatus();
    }

    public void stop() {
        engine.turnOff();
    }

    private class Engine {

        private String engineStatus;

        public Engine() {
            engineStatus = "switched off";
        }

        public void turnOn() {
            engineStatus = "switched on";
            status = "launched";
        }

        public String getStatus() {
            return engineStatus;
        }

        public void turnOff() {
            engineStatus = "switched off";
            status = "not launched";
        }
    }
}
