package com.architecture.android.todo_mvp_sample.tasks;

import com.architecture.android.todo_mvp_sample.data.source.TaskRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by yangsimin on 2017/12/7.
 */
@Module
public abstract class TasksModule {

    @ContributesAndroidInjector
    abstract TasksFragment tasksFragment();


}
