package com.architecture.android.todo_mvp_sample.data.source;

import com.architecture.android.todo_mvp_sample.data.Task;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by yangsimin on 2017/11/29.
 */

public interface TaskDataSource {

    interface GetTasksCallback {

        void onTasksLoad(List<Task> tasks);

    }

    Observable<Task> addTask(Task task);

    Observable<Task> deleteTask(Task task);

    void getTasks(GetTasksCallback callback);
}
