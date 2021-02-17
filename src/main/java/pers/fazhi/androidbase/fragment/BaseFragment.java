package pers.fazhi.androidbase.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import pers.fazhi.androidbase.R;
import pers.fazhi.androidbase.action.ClickAction;
import pers.fazhi.androidbase.action.HandlerAction;


/**
 * @author FaZhi
 * @date 2021年01月07 20点13分
 * @description This is BaseFragment Class.
 */
public abstract class BaseFragment<A extends Activity> extends Fragment implements ClickAction,Handler.Callback, HandlerAction {

    protected View root;

    protected A activity;

    protected Handler handler = new Handler(Looper.getMainLooper(),this);

    public String TAG = getClass().getSimpleName();

    protected Dialog waitDialog = null;

    protected abstract int getLayoutID();

    protected abstract void initView();

    protected abstract void initData();

    protected void initFragment(){
        initView();
        initData();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (A) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutID();
        if (layoutId > 0){
            root = inflater.inflate(getLayoutID(),container,false);
        }
        //初始化Dialog
        waitDialog = new AppCompatDialog(getContext());
        waitDialog.setContentView(R.layout.dialog_base_loading);
        waitDialog.setCanceledOnTouchOutside(false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment();
    }

    @Override
    public <T extends View> T findViewById(int id) {
        T t = root.findViewById(id);
        if (t == null){
            throw new NullPointerException("resource id "+ id +" not find! findViewById error!");
        }
        return t;
    }

    @NonNull
    @Override
    public Context getContext() {
        return this.activity;
    }

    /**
     * 得到附属的Activity
     * @return 附属的Activity
     */
    public A getAttachActivity(){
        return this.activity;
    }


    @Override
    public void onResume() {
        super.onResume();
        //重新加载....此处可能需要等待
    }

    @Override
    public void onDestroyView() {
        removeCallbacks();
        super.onDestroyView();
    }

    /**
     * 销毁当前 Fragment 所在的 Activity
     */
    public void finish() {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        //交给子类完成
        return false;
    }

    public void startActivity(Class<? extends Activity> activityClass) {
        startActivity(new Intent(getContext(),activityClass));
    }


    public void showWait(){
        waitDialog.show();
    }

    public void dismessWait(){
        waitDialog.dismiss();
    }
}
