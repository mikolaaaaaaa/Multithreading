package com.mikola.multithreading.entity;

import java.util.ArrayList;
import java.util.List;

public class VanWrapper {
    private List<Van> vans = new ArrayList<>();

    public List<Van> getVans() {
        return vans;
    }

    public void setVans(List<Van> vans) {
        this.vans = vans;
    }
}
