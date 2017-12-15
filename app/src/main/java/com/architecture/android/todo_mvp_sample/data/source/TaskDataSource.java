package com.architecture.android.todo_mvp_sample.data.source;

import com.architecture.android.todo_mvp_sample.data.Task;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by yangsimin on 2017/11/29.
 */

public interface TaskDataSource<T> {

    interface GetTasksCallback<T> {

        void onTasksLoad(List<T> tasks);

    }

    Observable<T> addTask(T task);

    Observable<T> deleteTask(T task);

    void getTasks(GetTasksCallback callback);
}
