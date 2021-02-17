package pers.fazhi.androidbase.adapter;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;

import java.util.List;

/**
 * @author FaZhi
 * @date 2021年01月25日 22时55分
 * @description This is RecyclerViewPageAdapter Class.
 */
public final class RecyclerViewPageAdapter<Data> extends BaseRecyclerViewAdapter<BaseRecyclerViewAdapter.ViewHolder,Data> {
    public RecyclerViewPageAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }
    public RecyclerViewPageAdapter(Context context, int layoutId, List<Data> datas) {
        super(context, layoutId, datas);
    }
    public RecyclerViewPageAdapter(Context context, int layoutId,  OnBindViewHolder<Data, ViewHolder> onBindViewHolder) {
        super(context, layoutId, null, onBindViewHolder);
    }
    public RecyclerViewPageAdapter(Context context, int layoutId, List<Data> datas, OnBindViewHolder<Data, ViewHolder> onBindViewHolder) {
        super(context, layoutId, datas, onBindViewHolder);
    }
    @Override
    protected ViewHolder getViewHolder(@NonNull View itemView) {
        return new ViewHolder(itemView);
    }
}
