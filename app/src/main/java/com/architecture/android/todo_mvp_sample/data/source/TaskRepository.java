package com.architecture.android.todo_mvp_sample.data.source;

import com.architecture.android.todo_mvp_sample.data.Task;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by yangsimin on 2017/11/29.
 */

public class TaskRepository implements TaskDataSource {

    private static TaskRepository instance;


    @Inject
    public TaskRepository() {
    }

    public static TaskRepository getInstance() {
        if (instance == null) {
            instance = new TaskRepository();
        }
        return instance;
    }
    private ArrayList<Task> mList = new ArrayList<>();

    @Override
    public void addTask(Task task) {
        mList.add(task);
    }

    @Override
    public void deleteTask(Task task) {
        mList.remove(task);
    }

    @Override
    public void getTasks(GetTasksCallback callback) {
        callback.onTasksLoad(mList);
    }
}
