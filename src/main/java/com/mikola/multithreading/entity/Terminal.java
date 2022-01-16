package com.mikola.multithreading.entity;

import com.mikola.multithreading.util.TerminalIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;


public class Terminal {
    private static final Logger LOGGER = LogManager.getLogger();
    private final long id;

    public Terminal() {
        id = TerminalIdGenerator.generateId();
    }

    public void process(Van van) {
        switch (van.getOperation()) {
            case UNLOAD -> {
                unload(van);
            }
            case DOWNLOAD -> {
                load(van);
            }
            default -> {
                LOGGER.error("Unknown operation in van {}",van.getId());
            }
        }
        long processingTime = (long) (Math.random() % 80) + 20;
        try {
            TimeUnit.MILLISECONDS.sleep(processingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        van.setState(Van.State.FINISHED);
        LOGGER.info("Terminal {} processed van {}", this.id, van.getId());

    }

    public void load(Van van) {
        van.setState(Van.State.DOWNLOADING);
        int CONTAINERS_COUNT = 10;
        van.setContainers(CONTAINERS_COUNT);
    }

    public void unload(Van van) {
        van.setState(Van.State.UNLOADING);
        int CONTAINERS_COUNT = 0;
        van.setContainers(CONTAINERS_COUNT);
    }

}
