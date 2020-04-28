package com.easyandroid.banner.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * package: com.easyandroid.banner.adapter.BasePagerAdapter
 * author: gyc
 * description:自定义PagerAdapter
 * time: create at 2019/6/24 11:24
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter {

    protected List<T> list;
    protected SparseArray<BasePagerHolder<T>> sparseArray;

    public BasePagerAdapter(List<T> list) {
        this.list = list;
        sparseArray = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        BasePagerHolder<T> holder = sparseArray.get(getSparsePosition(position));
        if (null == holder) {
            holder = onBindHolder(container);
            holder.setPosition(position);
            holder.bindData(list.get(position), position);
            sparseArray.put(getSparsePosition(position), holder);
        }
        View view = holder.itemView;
        container.addView(view);
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        sparseArray.clear();
        super.notifyDataSetChanged();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    protected View inflate(int resource, ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
    }

    protected abstract BasePagerHolder<T> onBindHolder(ViewGroup container);

    public void onDestroy() {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }


    protected int getSparsePosition(int position) {
        return (position + 10) * 2;
    }

    public String getIcon(int position) {
        return null;
    }

    protected abstract class BasePagerHolder<D> {
        protected Context mContext;
        private int position;

        SparseArray<View> sparseArray;
        View itemView;

        public BasePagerHolder(View itemView) {
            this.itemView = itemView;
            this.mContext = itemView.getContext();
            sparseArray = new SparseArray<>();
            initView();
        }

        public abstract void initView();

        public abstract void bindData(D d, int position);

        public View findViewById(int id) {
            View view = sparseArray.get(id);
            if (null == view) {
                view = itemView.findViewById(id);
                sparseArray.append(id, view);
            }
            return view;
        }

        public void onActivityResult(int requestCode, int resultCode, Intent data) {
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }

        public void onDestroy() {
            sparseArray.clear();
        }
    }
}
