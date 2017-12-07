package com.architecture.android.todo_mvp_sample.tasks;

import com.architecture.android.todo_mvp_sample.BaseView;
import com.architecture.android.todo_mvp_sample.data.Task;
import com.architecture.android.todo_mvp_sample.data.source.TaskDataSource;
import com.architecture.android.todo_mvp_sample.data.source.TaskRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yangsimin on 2017/11/29.
 */

public class TasksPresenter implements TasksContract.Presenter, TaskRepository.GetTasksCallback{
    private TaskDataSource mTaskDataSource;
    private TasksContract.View mView;

    public TasksPresenter(TaskDataSource taskDataSource, TasksContract.View view){
        mTaskDataSource = taskDataSource;
        mView = view;
//        view.setPresenter(this);
    }

    @Inject
    public TasksPresenter(){
    }


    public TasksPresenter(TaskDataSource taskDataSource){
        mTaskDataSource = taskDataSource;
    }

    public void setTaskDataSource(TaskDataSource taskDataSource){
        mTaskDataSource = taskDataSource;
    }



    @Override
    public void setView(TasksContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mTaskDataSource.getTasks(this);
    }


    @Override
    public void addItem() {
        Task task = new Task();
        mTaskDataSource.addTask(task);
        mTaskDataSource.getTasks(this);
    }

    @Override
    public void deleteItem(Task task) {
        mTaskDataSource.deleteTask(task);
        mTaskDataSource.getTasks(this);
    }

    @Override
    public void onTasksLoad(List<Task> tasks) {
        mView.showTasks(tasks);
    }
}
