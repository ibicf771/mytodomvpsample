package com.architecture.android.todo_mvp_sample.tasks;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by yangsimin on 2017/12/7.
 */
@Module
public abstract class TasksModule {

    @ContributesAndroidInjector
    abstract TasksFragment tasksFragment();
}
