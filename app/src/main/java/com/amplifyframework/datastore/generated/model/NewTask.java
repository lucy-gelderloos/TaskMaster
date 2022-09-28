package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.annotations.BelongsTo;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the NewTask type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "NewTasks", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byTeam", fields = {"teamID"})
public final class NewTask implements Model {
  public static final QueryField ID = field("NewTask", "id");
  public static final QueryField TASK_TITLE = field("NewTask", "taskTitle");
  public static final QueryField TASK_BODY = field("NewTask", "taskBody");
  public static final QueryField TASK_DATE_CREATED = field("NewTask", "taskDateCreated");
  public static final QueryField TASK_STATUS = field("NewTask", "taskStatus");
  public static final QueryField TEAM = field("NewTask", "teamID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String taskTitle;
  private final @ModelField(targetType="String") String taskBody;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime taskDateCreated;
  private final @ModelField(targetType="TaskStatusEnum") TaskStatusEnum taskStatus;
  private final @ModelField(targetType="Team") @BelongsTo(targetName = "teamID", type = Team.class) Team team;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTaskTitle() {
      return taskTitle;
  }
  
  public String getTaskBody() {
      return taskBody;
  }
  
  public Temporal.DateTime getTaskDateCreated() {
      return taskDateCreated;
  }
  
  public TaskStatusEnum getTaskStatus() {
      return taskStatus;
  }
  
  public Team getTeam() {
      return team;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private NewTask(String id, String taskTitle, String taskBody, Temporal.DateTime taskDateCreated, TaskStatusEnum taskStatus, Team team) {
    this.id = id;
    this.taskTitle = taskTitle;
    this.taskBody = taskBody;
    this.taskDateCreated = taskDateCreated;
    this.taskStatus = taskStatus;
    this.team = team;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      NewTask newTask = (NewTask) obj;
      return ObjectsCompat.equals(getId(), newTask.getId()) &&
              ObjectsCompat.equals(getTaskTitle(), newTask.getTaskTitle()) &&
              ObjectsCompat.equals(getTaskBody(), newTask.getTaskBody()) &&
              ObjectsCompat.equals(getTaskDateCreated(), newTask.getTaskDateCreated()) &&
              ObjectsCompat.equals(getTaskStatus(), newTask.getTaskStatus()) &&
              ObjectsCompat.equals(getTeam(), newTask.getTeam()) &&
              ObjectsCompat.equals(getCreatedAt(), newTask.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), newTask.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTaskTitle())
      .append(getTaskBody())
      .append(getTaskDateCreated())
      .append(getTaskStatus())
      .append(getTeam())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("NewTask {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("taskTitle=" + String.valueOf(getTaskTitle()) + ", ")
      .append("taskBody=" + String.valueOf(getTaskBody()) + ", ")
      .append("taskDateCreated=" + String.valueOf(getTaskDateCreated()) + ", ")
      .append("taskStatus=" + String.valueOf(getTaskStatus()) + ", ")
      .append("team=" + String.valueOf(getTeam()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TaskTitleStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static NewTask justId(String id) {
    return new NewTask(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      taskTitle,
      taskBody,
      taskDateCreated,
      taskStatus,
      team);
  }
  public interface TaskTitleStep {
    TaskDateCreatedStep taskTitle(String taskTitle);
  }
  

  public interface TaskDateCreatedStep {
    BuildStep taskDateCreated(Temporal.DateTime taskDateCreated);
  }
  

  public interface BuildStep {
    NewTask build();
    BuildStep id(String id);
    BuildStep taskBody(String taskBody);
    BuildStep taskStatus(TaskStatusEnum taskStatus);
    BuildStep team(Team team);
  }
  

  public static class Builder implements TaskTitleStep, TaskDateCreatedStep, BuildStep {
    private String id;
    private String taskTitle;
    private Temporal.DateTime taskDateCreated;
    private String taskBody;
    private TaskStatusEnum taskStatus;
    private Team team;
    @Override
     public NewTask build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new NewTask(
          id,
          taskTitle,
          taskBody,
          taskDateCreated,
          taskStatus,
          team);
    }
    
    @Override
     public TaskDateCreatedStep taskTitle(String taskTitle) {
        Objects.requireNonNull(taskTitle);
        this.taskTitle = taskTitle;
        return this;
    }
    
    @Override
     public BuildStep taskDateCreated(Temporal.DateTime taskDateCreated) {
        Objects.requireNonNull(taskDateCreated);
        this.taskDateCreated = taskDateCreated;
        return this;
    }
    
    @Override
     public BuildStep taskBody(String taskBody) {
        this.taskBody = taskBody;
        return this;
    }
    
    @Override
     public BuildStep taskStatus(TaskStatusEnum taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }
    
    @Override
     public BuildStep team(Team team) {
        this.team = team;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String taskTitle, String taskBody, Temporal.DateTime taskDateCreated, TaskStatusEnum taskStatus, Team team) {
      super.id(id);
      super.taskTitle(taskTitle)
        .taskDateCreated(taskDateCreated)
        .taskBody(taskBody)
        .taskStatus(taskStatus)
        .team(team);
    }
    
    @Override
     public CopyOfBuilder taskTitle(String taskTitle) {
      return (CopyOfBuilder) super.taskTitle(taskTitle);
    }
    
    @Override
     public CopyOfBuilder taskDateCreated(Temporal.DateTime taskDateCreated) {
      return (CopyOfBuilder) super.taskDateCreated(taskDateCreated);
    }
    
    @Override
     public CopyOfBuilder taskBody(String taskBody) {
      return (CopyOfBuilder) super.taskBody(taskBody);
    }
    
    @Override
     public CopyOfBuilder taskStatus(TaskStatusEnum taskStatus) {
      return (CopyOfBuilder) super.taskStatus(taskStatus);
    }
    
    @Override
     public CopyOfBuilder team(Team team) {
      return (CopyOfBuilder) super.team(team);
    }
  }
  
}
