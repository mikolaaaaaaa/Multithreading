package com.mikola.multithreading.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikola.multithreading.entity.Van;
import com.mikola.multithreading.entity.VanWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    private static final Logger LOGGER = LogManager.getLogger();

    public List<Van> read(String path) {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        List<Van> vans = new ArrayList<>();
        try {
            VanWrapper vanWrapper = mapper.readValue(file, VanWrapper.class);
            vans = vanWrapper.getVans();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("Reading from file is succesful");
        return vans;
    }
}
