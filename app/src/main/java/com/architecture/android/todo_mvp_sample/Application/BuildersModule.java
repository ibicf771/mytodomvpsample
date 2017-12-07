package com.architecture.android.todo_mvp_sample.Application;

import com.architecture.android.todo_mvp_sample.tasks.TasksActivity;
import com.architecture.android.todo_mvp_sample.tasks.TasksModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by yangsimin on 2017/12/6.
 */

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = TasksModule.class)
    abstract TasksActivity tasksActivityInjector();
}
