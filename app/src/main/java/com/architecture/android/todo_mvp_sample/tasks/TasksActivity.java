package com.architecture.android.todo_mvp_sample.tasks;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.architecture.android.todo_mvp_sample.R;
import com.architecture.android.todo_mvp_sample.data.source.TaskRepository;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class TasksActivity extends DaggerAppCompatActivity {

    @Inject
    TasksFragment mTasksFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TasksFragment tasksFragment =
                (TasksFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (tasksFragment == null) {
            // Create the fragment
//            tasksFragment = TasksFragment.newInstance();
            tasksFragment = mTasksFragment;

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, tasksFragment);
            transaction.commit();
        }
        TasksPresenter tasksPresenter = new TasksPresenter();
        tasksPresenter.setTaskDataSource(TaskRepository.getInstance());
        tasksFragment.setPresenter(tasksPresenter);

        //present创建
//        new TasksPresenter(TaskRepository.getInstance(), tasksFragment);

    }
}
