package com.architecture.android.todo_mvp_sample.Application;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by yangsimin on 2017/12/7.
 */

public class TasksApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
