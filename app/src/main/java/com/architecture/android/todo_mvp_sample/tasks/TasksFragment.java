package com.architecture.android.todo_mvp_sample.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.architecture.android.todo_mvp_sample.R;
import com.architecture.android.todo_mvp_sample.data.Task;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


/**
 * Created by yangsimin on 2017/11/29.
 */

public class TasksFragment extends DaggerFragment implements TasksContract.View{

    private TasksContract.Presenter mPresenter;
    private Button mAddItemBtn;
    private RecyclerView mRecycleView;
    private TasksAdapter mAdapter;

    @Inject
    public TasksFragment() {
        // Requires empty public constructor
    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.setView(this);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tasks_fragment, container, false);
        mAddItemBtn =  root.findViewById(R.id.add_item);
        mRecycleView = root.findViewById(R.id.tasks_list);
        initView();
        return root;
    }



    private void initView() {
        final ArrayList<Task> list = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new TasksAdapter(list, mOnDeleteItemListener);
        // 设置布局管理器
        mRecycleView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecycleView.setAdapter(mAdapter);
        mAddItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addItem();
            }
        });
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

    private static class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder>{

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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
            // 实例化viewholder
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(TasksAdapter.ViewHolder holder, final int position) {
            holder.mTv.setText(mTasks.get(position).getData());
            holder.mDeleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnDeleteItemListener.onItemDelete(mTasks.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mTasks == null ? 0 : mTasks.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            TextView mTv;

            Button mDeleteItem;

            public ViewHolder(View itemView) {
                super(itemView);
                mTv =  itemView.findViewById(R.id.item_data);
                mDeleteItem = itemView.findViewById(R.id.item_delete);
            }
        }



    }

    interface OnDeleteItemListener{
        void onItemDelete(Task task);
    }


}
