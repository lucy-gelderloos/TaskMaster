package com.gelderloos.taskmaster.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;
import com.gelderloos.taskmaster.R;
import com.gelderloos.taskmaster.activities.TaskDetailActivity;

import java.util.List;

public class TaskListRecyclerViewAdapter extends RecyclerView.Adapter<TaskListRecyclerViewAdapter.TaskListViewHolder> {
    List<Task> tasks;
    Context callingActivity;

    public TaskListRecyclerViewAdapter(List<Task> tasks, Context callingActivity) {
        this.tasks = tasks;
        this.callingActivity = callingActivity;
    }

    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View taskFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent, false);
        return new TaskListViewHolder(taskFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {
        TextView taskFragmentTaskTitleTextView = holder.itemView.findViewById(R.id.textViewTaskFragmentTaskTitle);
        String taskTitle = tasks.get(position).getTaskTitle();
        taskFragmentTaskTitleTextView.setText(position + 1 + ". " + taskTitle);

        TextView taskFragmentTaskBodyTextView = holder.itemView.findViewById(R.id.textViewTaskFragmentTaskBody);
        String taskBody = tasks.get(position).getTaskBody();
        taskFragmentTaskBodyTextView.setText(taskBody);

        String taskState = tasks.get(position).getTaskStatus().toString();


//        TextView taskFragmentTaskStateTextView = holder.itemView.findViewById(R.id.TaskFragmentState);
//        String state = tasks.get(position).getState().toString();

        View taskViewHolder = holder.itemView;
        taskViewHolder.setOnClickListener(view -> {
            Intent goToTaskDetail = new Intent(callingActivity, TaskDetailActivity.class);
            goToTaskDetail.putExtra(TaskDetailActivity.TASK_TITLE_EXTRA_TAG, taskTitle);
            goToTaskDetail.putExtra(TaskDetailActivity.TASK_BODY_EXTRA_TAG, taskBody);
            goToTaskDetail.putExtra(TaskDetailActivity.TASK_STATE_EXTRA_TAG, taskState);
            callingActivity.startActivity(goToTaskDetail);
        });
    }

    @Override
    public int getItemCount() {
        //        return 100;
        return tasks.size();
    }


    public static class TaskListViewHolder extends RecyclerView.ViewHolder{
        public TaskListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
