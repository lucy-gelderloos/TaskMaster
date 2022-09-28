package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

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

/** This is an auto generated class representing the Task type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Tasks", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Task implements Model {
  public static final QueryField ID = field("Task", "id");
  public static final QueryField TASK_TITLE = field("Task", "taskTitle");
  public static final QueryField TASK_BODY = field("Task", "taskBody");
  public static final QueryField TASK_DATE_CREATED = field("Task", "taskDateCreated");
  public static final QueryField TASK_STATUS = field("Task", "taskStatus");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String taskTitle;
  private final @ModelField(targetType="String") String taskBody;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime taskDateCreated;
  private final @ModelField(targetType="TaskStatusEnum") TaskStatusEnum taskStatus;
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
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Task(String id, String taskTitle, String taskBody, Temporal.DateTime taskDateCreated, TaskStatusEnum taskStatus) {
    this.id = id;
    this.taskTitle = taskTitle;
    this.taskBody = taskBody;
    this.taskDateCreated = taskDateCreated;
    this.taskStatus = taskStatus;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Task task = (Task) obj;
      return ObjectsCompat.equals(getId(), task.getId()) &&
              ObjectsCompat.equals(getTaskTitle(), task.getTaskTitle()) &&
              ObjectsCompat.equals(getTaskBody(), task.getTaskBody()) &&
              ObjectsCompat.equals(getTaskDateCreated(), task.getTaskDateCreated()) &&
              ObjectsCompat.equals(getTaskStatus(), task.getTaskStatus()) &&
              ObjectsCompat.equals(getCreatedAt(), task.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), task.getUpdatedAt());
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
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Task {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("taskTitle=" + String.valueOf(getTaskTitle()) + ", ")
      .append("taskBody=" + String.valueOf(getTaskBody()) + ", ")
      .append("taskDateCreated=" + String.valueOf(getTaskDateCreated()) + ", ")
      .append("taskStatus=" + String.valueOf(getTaskStatus()) + ", ")
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
  public static Task justId(String id) {
    return new Task(
      id,
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
      taskStatus);
  }
  public interface TaskTitleStep {
    TaskDateCreatedStep taskTitle(String taskTitle);
  }
  

  public interface TaskDateCreatedStep {
    BuildStep taskDateCreated(Temporal.DateTime taskDateCreated);
  }
  

  public interface BuildStep {
    Task build();
    BuildStep id(String id);
    BuildStep taskBody(String taskBody);
    BuildStep taskStatus(TaskStatusEnum taskStatus);
  }
  

  public static class Builder implements TaskTitleStep, TaskDateCreatedStep, BuildStep {
    private String id;
    private String taskTitle;
    private Temporal.DateTime taskDateCreated;
    private String taskBody;
    private TaskStatusEnum taskStatus;
    @Override
     public Task build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Task(
          id,
          taskTitle,
          taskBody,
          taskDateCreated,
          taskStatus);
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
    private CopyOfBuilder(String id, String taskTitle, String taskBody, Temporal.DateTime taskDateCreated, TaskStatusEnum taskStatus) {
      super.id(id);
      super.taskTitle(taskTitle)
        .taskDateCreated(taskDateCreated)
        .taskBody(taskBody)
        .taskStatus(taskStatus);
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
  }
  
}
