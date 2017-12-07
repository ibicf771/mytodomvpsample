package com.architecture.android.todo_mvp_sample.tasks;

import com.architecture.android.todo_mvp_sample.BasePresenter;
import com.architecture.android.todo_mvp_sample.BaseView;
import com.architecture.android.todo_mvp_sample.data.Task;

import java.util.List;

/**
 * Created by yangsimin on 2017/11/29.
 */

public class TasksContract {

    interface View extends BaseView<Presenter> {

        void showTasks(List<Task> tasks);
    }

    interface Presenter extends BasePresenter {

        void start();

        void addItem();

        void deleteItem(Task task);

        void setView(View view);
    }
}
