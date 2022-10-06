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

/** This is an auto generated class representing the Task type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Tasks", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byTeam", fields = {"teamID"})
public final class Task implements Model {
  public static final QueryField ID = field("Task", "id");
  public static final QueryField TASK_TITLE = field("Task", "taskTitle");
  public static final QueryField TASK_BODY = field("Task", "taskBody");
  public static final QueryField TASK_DATE_CREATED = field("Task", "taskDateCreated");
  public static final QueryField TASK_STATUS = field("Task", "taskStatus");
  public static final QueryField TEAM = field("Task", "teamID");
  public static final QueryField ASSOCIATED_IMAGE_S3_KEY = field("Task", "associatedImageS3Key");
  public static final QueryField LOCATION = field("Task", "location");
  public static final QueryField LATITUDE = field("Task", "latitude");
  public static final QueryField LONGITUDE = field("Task", "longitude");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String taskTitle;
  private final @ModelField(targetType="String") String taskBody;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime taskDateCreated;
  private final @ModelField(targetType="TaskStatusEnum") TaskStatusEnum taskStatus;
  private final @ModelField(targetType="Team") @BelongsTo(targetName = "teamID", type = Team.class) Team team;
  private final @ModelField(targetType="String") String associatedImageS3Key;
  private final @ModelField(targetType="String") String location;
  private final @ModelField(targetType="String") String latitude;
  private final @ModelField(targetType="String") String longitude;
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
  
  public String getAssociatedImageS3Key() {
      return associatedImageS3Key;
  }
  
  public String getLocation() {
      return location;
  }
  
  public String getLatitude() {
      return latitude;
  }
  
  public String getLongitude() {
      return longitude;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Task(String id, String taskTitle, String taskBody, Temporal.DateTime taskDateCreated, TaskStatusEnum taskStatus, Team team, String associatedImageS3Key, String location, String latitude, String longitude) {
    this.id = id;
    this.taskTitle = taskTitle;
    this.taskBody = taskBody;
    this.taskDateCreated = taskDateCreated;
    this.taskStatus = taskStatus;
    this.team = team;
    this.associatedImageS3Key = associatedImageS3Key;
    this.location = location;
    this.latitude = latitude;
    this.longitude = longitude;
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
              ObjectsCompat.equals(getTeam(), task.getTeam()) &&
              ObjectsCompat.equals(getAssociatedImageS3Key(), task.getAssociatedImageS3Key()) &&
              ObjectsCompat.equals(getLocation(), task.getLocation()) &&
              ObjectsCompat.equals(getLatitude(), task.getLatitude()) &&
              ObjectsCompat.equals(getLongitude(), task.getLongitude()) &&
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
      .append(getTeam())
      .append(getAssociatedImageS3Key())
      .append(getLocation())
      .append(getLatitude())
      .append(getLongitude())
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
      .append("team=" + String.valueOf(getTeam()) + ", ")
      .append("associatedImageS3Key=" + String.valueOf(getAssociatedImageS3Key()) + ", ")
      .append("location=" + String.valueOf(getLocation()) + ", ")
      .append("latitude=" + String.valueOf(getLatitude()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
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
      null,
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
      team,
      associatedImageS3Key,
      location,
      latitude,
      longitude);
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
    BuildStep team(Team team);
    BuildStep associatedImageS3Key(String associatedImageS3Key);
    BuildStep location(String location);
    BuildStep latitude(String latitude);
    BuildStep longitude(String longitude);
  }
  

  public static class Builder implements TaskTitleStep, TaskDateCreatedStep, BuildStep {
    private String id;
    private String taskTitle;
    private Temporal.DateTime taskDateCreated;
    private String taskBody;
    private TaskStatusEnum taskStatus;
    private Team team;
    private String associatedImageS3Key;
    private String location;
    private String latitude;
    private String longitude;
    @Override
     public Task build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Task(
          id,
          taskTitle,
          taskBody,
          taskDateCreated,
          taskStatus,
          team,
          associatedImageS3Key,
          location,
          latitude,
          longitude);
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
    
    @Override
     public BuildStep associatedImageS3Key(String associatedImageS3Key) {
        this.associatedImageS3Key = associatedImageS3Key;
        return this;
    }
    
    @Override
     public BuildStep location(String location) {
        this.location = location;
        return this;
    }
    
    @Override
     public BuildStep latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }
    
    @Override
     public BuildStep longitude(String longitude) {
        this.longitude = longitude;
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
    private CopyOfBuilder(String id, String taskTitle, String taskBody, Temporal.DateTime taskDateCreated, TaskStatusEnum taskStatus, Team team, String associatedImageS3Key, String location, String latitude, String longitude) {
      super.id(id);
      super.taskTitle(taskTitle)
        .taskDateCreated(taskDateCreated)
        .taskBody(taskBody)
        .taskStatus(taskStatus)
        .team(team)
        .associatedImageS3Key(associatedImageS3Key)
        .location(location)
        .latitude(latitude)
        .longitude(longitude);
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
    
    @Override
     public CopyOfBuilder associatedImageS3Key(String associatedImageS3Key) {
      return (CopyOfBuilder) super.associatedImageS3Key(associatedImageS3Key);
    }
    
    @Override
     public CopyOfBuilder location(String location) {
      return (CopyOfBuilder) super.location(location);
    }
    
    @Override
     public CopyOfBuilder latitude(String latitude) {
      return (CopyOfBuilder) super.latitude(latitude);
    }
    
    @Override
     public CopyOfBuilder longitude(String longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
  }
  
}
