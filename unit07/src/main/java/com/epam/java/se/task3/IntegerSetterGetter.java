package com.epam.java.se.task3;

import java.util.Random;

public class IntegerSetterGetter extends Thread {
    private SharedResource resource;
    private boolean run;

    private Random rand = new Random();

    public IntegerSetterGetter(String name, SharedResource resource) {
        super(name);
        this.resource = resource;
        run = true;
    }

    @Override
    public void run() {
        int action;
        try {
            while (run) {
                action = rand.nextInt(1000);
                if (action % 2 == 0) {
                    getIntegersFromResource();
                } else {
                    setIntegersIntoResource();
                }
            }
            System.out.println("Поток " + getName() + " завершил работу.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getIntegersFromResource() throws InterruptedException {
        Integer number;

        synchronized (resource) {
            System.out.println("Поток " + getName() + " хочет извлечь число.");
            number = resource.getElement();
            int waitCount = 0;
            while (number == null) {
                if (waitCount == 5) {
                    run = false;
                    return;
                }
                System.out.println("Поток " + getName() + " ждёт пока заполнится очередь.");
                resource.wait(1000);
                waitCount += 1;
                System.out.println("Поток " + getName() + " возобновил работу.");
                number = resource.getElement();
            }
            System.out.println("Поток " + getName() + " извлёк число " + number);
        }
    }

    private void setIntegersIntoResource() {
        Integer number = rand.nextInt(500);
        synchronized (resource) {
            resource.setElement(number);
            System.out.println("Поток " + getName() + " записал число " + number);
            resource.notify();
        }
    }
}