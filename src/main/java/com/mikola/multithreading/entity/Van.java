package com.mikola.multithreading.entity;

import com.mikola.multithreading.util.VanIdGenerator;

public class Van implements Runnable {
    private long id;
    private int containers;
    private boolean perishable;
    private State state;
    private Operation operation;

    @Override
    public void run() {
        LogisticsBase logisticsBase = LogisticsBase.getInstance();
        logisticsBase.acquireTerminal(this);
    }

    public enum Operation {
        DOWNLOAD,
        UNLOAD
    }

    public enum State {
        NEW,
        WAITING,
        DOWNLOADING,
        UNLOADING,
        FINISHED
    }

    public Van() {
        this.id = VanIdGenerator.generateId();
    }

    public Van(long id, int containers, boolean perishable, State state, Operation operation) {
        this.id = id;
        this.containers = containers;
        this.perishable = perishable;
        this.state = state;
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getContainers() {
        return containers;
    }

    public void setContainers(int containers) {
        this.containers = containers;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Van van = (Van) object;

        if (getId() != van.getId()) return false;
        if (getContainers() != van.getContainers()) return false;
        if (isPerishable() != van.isPerishable()) return false;
        if (getState() != van.getState()) return false;
        return getOperation() == van.getOperation();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getContainers();
        result = 31 * result + (isPerishable() ? 1 : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getOperation() != null ? getOperation().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Van{")
                .append("id=").append(id)
                .append(", containers=").append(containers)
                .append(", state=").append(state)
                .append(", perishable=").append(perishable)
                .append(", operation=").append(operation)
                .append('}')
                .toString();
    }
}
