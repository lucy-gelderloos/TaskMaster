package com.gelderloos.taskmaster.models;

public class Task {
    private String title;
    private String body;
    private State state;

    public enum State {
        NEW, ASSIGNED, IN_PROGRESS, COMPLETE
    }

    public Task(String title, String body) {
        this.title = title;
        this.body = body;
        this.state = State.NEW;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
