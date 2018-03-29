package com.architecture.android.todo_mvp_sample.tasks;

import com.architecture.android.todo_mvp_sample.data.Task;
import com.architecture.android.todo_mvp_sample.data.source.TaskDataSource;
import com.architecture.android.todo_mvp_sample.data.source.TaskRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by yangsimin on 2018/3/29.
 */
public class TasksPresenterTest {

    private static List<Task> TASKS;

    @Mock
    private TaskRepository mTasksRepository;

    @Mock
    private TasksContract.View mTasksView;

    @Captor
    private ArgumentCaptor<TaskDataSource.GetTasksCallback> mGetTasksCallbackCaptor;

    private TasksPresenter mTasksPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mTasksPresenter = new TasksPresenter(mTasksRepository, mTasksView);

        TASKS = new ArrayList<Task>();
        TASKS.add(new Task());
        TASKS.add(new Task());
    }

    @Test
    public void addTask() throws Exception {
//        mTasksPresenter.addItem();
//        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
//        verify(mTasksRepository).addTask(taskArgumentCaptor.capture());
//        assertTrue(taskArgumentCaptor.getValue().getStr().equals("OK"));
    }

    @Test
    public void deleteTask() throws Exception {

    }

    @Test
    public void getTask() throws Exception {
        mTasksPresenter.start();
        verify(mTasksRepository).getTasks(mGetTasksCallbackCaptor.capture());
        mGetTasksCallbackCaptor.getValue().onTasksLoad(TASKS);

        ArgumentCaptor<List> showTasksArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mTasksView).showTasks(showTasksArgumentCaptor.capture());
        assertTrue(showTasksArgumentCaptor.getValue().size() == 2);
        assertTrue(((Task)showTasksArgumentCaptor.getValue().get(0)).getData().equals("item1"));
    }
}