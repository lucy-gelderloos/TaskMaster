package com.gelderloos.taskmaster.models;

import static java.util.Objects.isNull;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    public Long id;
    private String title;
    private String body;
    private StateEnum state;
    private java.util.Date dateCreated;

    public Task(String title, String body, StateEnum state) {
        this.title = title;
        this.body = body;
        this.state = state;
        this.dateCreated = new Date();
    }

    public Task() {

    }

    //class for state Enum
    public enum StateEnum {
        NEW("New"), ASSIGNED("Assigned"), IN_PROGRESS("In Progress"), COMPLETE("Complete");

        private final String taskState;

        StateEnum(String _taskState) {
            this.taskState = _taskState;
        }

        public String getTaskState() {
            return taskState;
        }

        public static StateEnum fromString(String possibleTaskState) {
            for(StateEnum state : StateEnum.values()) {
                if(state.taskState.equals(possibleTaskState)) {
                    return state;
                }
            }
            return null;
        }

        @NonNull
        @Override
        public String toString() {
            if(isNull(taskState)) {
                return "";
            }
            return taskState;
        }
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
