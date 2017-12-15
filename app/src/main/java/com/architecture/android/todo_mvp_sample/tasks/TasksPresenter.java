package com.architecture.android.todo_mvp_sample.tasks;

import android.util.Log;

import com.architecture.android.todo_mvp_sample.data.Task;
import com.architecture.android.todo_mvp_sample.data.source.TaskDataSource;
import com.architecture.android.todo_mvp_sample.data.source.TaskRepository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yangsimin on 2017/11/29.
 */

public class TasksPresenter implements TasksContract.Presenter, TaskRepository.GetTasksCallback {
    private TaskDataSource mTaskDataSource;
    private TasksContract.View mView;

    public TasksPresenter(TaskDataSource taskDataSource, TasksContract.View view) {
        mTaskDataSource = taskDataSource;
        mView = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        mTaskDataSource.getTasks(this);
    }


    @Override
    public void addItem() {
        Task task = new Task();
        mTaskDataSource.addTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Task>() {
                    @Override
                    public void accept(Task task) throws Exception {
                        mTaskDataSource.getTasks(TasksPresenter.this);
                    }
                });


    }

    @Override
    public void deleteItem(Task task) {
        mTaskDataSource.deleteTask(task);
        mTaskDataSource.getTasks(this);

        mTaskDataSource.deleteTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Task>() {
                    @Override
                    public void accept(Task task) throws Exception {
                        mTaskDataSource.getTasks(TasksPresenter.this);
                    }
                });
    }

    @Override
    public void onTasksLoad(List<Task> tasks) {
        mView.showTasks(tasks);
    }
}
