package pers.fazhi.androidbase.adapter;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import java.util.List;

/**
 * @author FaZhi
 * @date 2021年01月25日 22时55分
 * @description This is RecyclerViewBindAdapter Class.
 */
public abstract class RecyclerViewBindAdapter <Bind extends ViewBinding,Data> extends BaseRecyclerViewAdapter<BaseRecyclerViewAdapter.BindViewHolder<Bind>, Data> {
    public RecyclerViewBindAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }
    public RecyclerViewBindAdapter(Context context, int layoutId, List<Data> datas) {
        super(context, layoutId, datas);
    }
    public RecyclerViewBindAdapter(Context context, int layoutId, OnBindViewHolder<Data, BindViewHolder<Bind>> onBindViewHolder) {
        super(context, layoutId, null, onBindViewHolder);
    }
    public RecyclerViewBindAdapter(Context context, int layoutId, List<Data> datas, OnBindViewHolder<Data, BindViewHolder<Bind>> onBindViewHolder) {
        super(context, layoutId, datas, onBindViewHolder);
    }

    @Override
    protected BindViewHolder<Bind> getViewHolder(@NonNull View itemView) {
        return new BindViewHolder<Bind>(itemView) {
            @Override
            public Bind createBindView(View itemView) {
                return getViewHolderBindView(itemView);
            }
        };
    }

    public abstract Bind getViewHolderBindView(View itemView);

}
