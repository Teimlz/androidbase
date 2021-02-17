package pers.fazhi.androidbase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.*;

/**
 * @author FaZhi
 * @date 2021年01月25日 21时00分
 * @description This is BaseRecyclerViewPagerAdapter Class.
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder,Data> extends RecyclerView.Adapter<VH>{

    protected final Context context;

    protected final int layoutid;

    protected List<Data> datas;

    private OnBindViewHolder<Data,VH> onBindViewHolder;

    protected BaseRecyclerViewAdapter(Context context, int layoutid) {
        this.context = Objects.requireNonNull(context);
        this.layoutid = layoutid;
        this.datas = new LinkedList<>();
    }

    protected BaseRecyclerViewAdapter(Context context, int layoutid, List<Data> datas) {
        this.context = Objects.requireNonNull(context);
        this.layoutid = layoutid;
        this.datas = datas;
        if (this.datas == null){
            this.datas = new LinkedList<>();
        }
    }

    protected BaseRecyclerViewAdapter(Context context, int layoutid, List<Data> datas,OnBindViewHolder<Data,VH> onBindViewHolder) {
        this.context = Objects.requireNonNull(context);
        this.layoutid = layoutid;
        this.datas = datas;
        if (this.datas == null){
            this.datas = new LinkedList<>();
        }
        this.onBindViewHolder = onBindViewHolder;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutid,parent,false);
        return getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (onBindViewHolder != null){
            onBindViewHolder.onBindViewHolder(this,holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() :0;
    }

    protected abstract VH getViewHolder(@NonNull View itemView);

    public boolean dataIsEmpty() {
        if (datas == null){
            return false;
        }
        return datas.isEmpty();
    }

    public boolean dataContains(@Nullable Object o) {
        if (o == null || datas.size() == 0){
            return false;
        }
        return datas.contains(o);
    }

    @Nullable
    public Iterator<Data> dataIterator() {
        if (datas == null){
            return null;
        }
        return datas.iterator();
    }

    public boolean addData(Data data) {
        if (datas == null){
            return false;
        }
        boolean flag = datas.add(data);
        notifyItemInserted(datas.size());
        notifyItemChanged(datas.size());
        return flag;
    }

    public boolean addData(int index,Data data) {
        if (datas == null){
            return false;
        }
        datas.add(index,data);
        notifyItemInserted(index);
        notifyItemChanged(datas.size());
        return true;
    }

    public boolean remove(int index) {
        if (datas == null){
            return false;
        }
        Data data = datas.remove(index);
        notifyItemRemoved(index);
        notifyItemChanged(index);
        return data != null;
    }

    public boolean remove(@NonNull Object o){
        if (datas == null){
            return false;
        }
        Data data = null;
        int index = 0;
        for (;index < datas.size(); index++) {
            if(o.equals(datas.get(index))){
                data = datas.remove(index);
            }
        }
        notifyItemRemoved(index);
        notifyItemChanged(index);
        return data != null;
    }

    public boolean containsAll(@NonNull Collection<?> c) {
        if (datas == null){
            return false;
        }
        return datas.containsAll(c);
    }

    public boolean addAll(@NonNull Collection<? extends Data> c) {
        if (datas == null){
            return false;
        }
        int startPostion = datas.size();
        boolean flag = datas.addAll(c);
        notifyItemRangeInserted(startPostion,c.size());
        notifyItemRangeChanged(startPostion,c.size());
        return flag;
    }

    public boolean removeAll(@NonNull Collection<?> c) {
        if (datas == null){
            return false;
        }
        int startPostion = datas.size();
        boolean flag = datas.removeAll(c);
        notifyItemRangeInserted(startPostion,c.size());
        notifyItemRangeChanged(startPostion,c.size());
        return flag;
    }

    public boolean retainAll(@NonNull Collection<?> c) {
        if (datas == null){
            return false;
        }
        boolean flag = datas.retainAll(c);
        notifyDataSetChanged();
        return flag;
    }

    public void clear() {
        if (datas != null && datas.size() != 0){
            datas.clear();
            notifyDataSetChanged();
        }
    }

    public Context getContext() {
        return context;
    }

    public int getLayoutId() {
        return layoutid;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public <V extends View> V bindView(@IdRes int id){
            V view = itemView.findViewById(id);
            if (view == null){
                throw new NullPointerException("resource id "+ id +" not find! findViewById error!");
            }
            return view;
        }
    }

    public static abstract class BindViewHolder<Bind extends ViewBinding> extends RecyclerView.ViewHolder{

        private Bind bindView;

        public BindViewHolder(@NonNull View itemView) {
            super(itemView);
            bindView = createBindView(itemView);
        }


        public abstract Bind createBindView(View itemView);

        public Bind getBindView() {
            return bindView;
        }
    }

    public interface OnBindViewHolder<Data,VH extends RecyclerView.ViewHolder>{
        public void onBindViewHolder(BaseRecyclerViewAdapter<VH,Data> adapter, VH holder, int index);
    }
}
