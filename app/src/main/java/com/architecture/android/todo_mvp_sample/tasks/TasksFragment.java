package com.architecture.android.todo_mvp_sample.tasks;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.architecture.android.todo_mvp_sample.BR;
import com.architecture.android.todo_mvp_sample.R;
import com.architecture.android.todo_mvp_sample.data.Task;
import com.architecture.android.todo_mvp_sample.databinding.TaskItemBinding;
import com.architecture.android.todo_mvp_sample.databinding.TasksFragmentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangsimin on 2017/11/29.
 */

public class TasksFragment extends Fragment implements TasksContract.View{

    private TasksContract.Presenter mPresenter;
    private TasksAdapter mAdapter;

    TasksFragmentBinding binding;

    private TasksFragment() {
        // Requires empty public constructor
    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = TasksFragmentBinding.inflate(inflater, container, false);
        binding.setTasksfragment(TasksFragment.this);
        initView();

        return binding.getRoot();
    }



    private void initView() {
        final ArrayList<Task> list = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new TasksAdapter(list, mOnDeleteItemListener);
        // 设置布局管理器
        binding.tasksList.setLayoutManager(mLayoutManager);
        // 设置adapter
        binding.tasksList.setAdapter(mAdapter);

    }

    public void onAddButtonClick(View view){
        mPresenter.addItem();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }



    @Override
    public void showTasks(List<Task> tasks) {
        mAdapter.refreshList(tasks);
    }


    OnDeleteItemListener mOnDeleteItemListener = new OnDeleteItemListener() {
        @Override
        public void onItemDelete(Task task) {
            mPresenter.deleteItem(task);

        }
    };

    public static class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder>{

        private List<Task> mTasks ;

        private OnDeleteItemListener mOnDeleteItemListener;


        public TasksAdapter(List<Task> tasks, OnDeleteItemListener onDeleteItemListener){
            mTasks = tasks;
            mOnDeleteItemListener = onDeleteItemListener;
        }

        public void refreshList(List<Task> tasks){
            mTasks = tasks;
            notifyDataSetChanged();
        }

        @Override
        public TasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            ViewDataBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.task_item,
                    parent,
                    false);
            ViewHolder holder = new ViewHolder(binding.getRoot());
            return holder;
        }

        @Override
        public void onBindViewHolder(TasksAdapter.ViewHolder holder, final int position) {
//            holder.mTv.setText(mTasks.get(position).getData());
//            holder.mDeleteItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mOnDeleteItemListener.onItemDelete(mTasks.get(position));
//                }
//            });

            ((TaskItemBinding)holder.getBinding()).itemDelete.setTag(position);
            holder.getBinding().setVariable(BR.itemdata,mTasks.get(position).getData());
            holder.getBinding().setVariable(BR.tasksadapter, this);
            holder.getBinding().executePendingBindings();
        }

        public void onDeleteBtnClick(View view){
            mOnDeleteItemListener.onItemDelete(mTasks.get((int)view.getTag()));

        }

        @Override
        public int getItemCount() {
            return mTasks == null ? 0 : mTasks.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            private ViewDataBinding mBinding;
            public ViewHolder(View itemView) {
                super(itemView);
                mBinding = DataBindingUtil.bind(itemView);
            }

            public void setBinding(ViewDataBinding binding){
                mBinding = binding;
            }

            public ViewDataBinding getBinding(){
                return mBinding;
            }
        }
    }

    interface OnDeleteItemListener{
        void onItemDelete(Task task);
    }
}
