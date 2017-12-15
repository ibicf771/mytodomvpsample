package com.architecture.android.todo_mvp_sample.data.source;

import com.architecture.android.todo_mvp_sample.data.Task;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

/**
 * Created by yangsimin on 2017/11/29.
 */

public class TaskRepository implements TaskDataSource<Task> {

    private static TaskRepository instance;

    private TaskRepository() {
    }

    public static TaskRepository getInstance() {
        if (instance == null) {
            instance = new TaskRepository();
        }
        return instance;
    }
    private ArrayList<Task> mList = new ArrayList<>();

    @Override
    public Observable addTask(final Task task) {
        Observable observable = Observable.create(new ObservableOnSubscribe<Task>() {
            @Override
            public void subscribe(ObservableEmitter<Task> e) throws Exception {
                mList.add(task);
                e.onNext(task);
            }
        });
        return observable;
    }

    @Override
    public Observable deleteTask(final Task task) {

        Observable observable = Observable.create(new ObservableOnSubscribe<Task>() {
            @Override
            public void subscribe(ObservableEmitter<Task> e) throws Exception {
                mList.remove(task);
                e.onNext(task);
            }
        });
        return observable;
    }

    @Override
    public void getTasks(GetTasksCallback callback) {
        callback.onTasksLoad(mList);
    }
}
