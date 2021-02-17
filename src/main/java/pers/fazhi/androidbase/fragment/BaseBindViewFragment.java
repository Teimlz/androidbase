package pers.fazhi.androidbase.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import pers.fazhi.androidbase.R;
import pers.fazhi.androidbase.action.ClickAction;
import pers.fazhi.androidbase.action.HandlerAction;


/**
 * @author FaZhi
 * @date 2021年01月07 20点13分
 * @description This is BaseFragment Class.
 */
public abstract class BaseBindViewFragment<A extends Activity,Bind extends ViewBinding> extends Fragment implements ClickAction,Handler.Callback, HandlerAction {

    protected Bind bindView;

    protected A activity;

    protected Handler handler = new Handler(Looper.getMainLooper(),this);

    protected Dialog waitDialog = null;

    public String TAG = getClass().getSimpleName();

    protected abstract Bind getBindView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract void initData();


    @Override
    @SuppressWarnings("unchecked")
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (A) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView = getBindView(inflater,container);
        //初始化Dialog
        waitDialog = new AppCompatDialog(getContext());
        waitDialog.setContentView(R.layout.dialog_base_loading);
        waitDialog.setCanceledOnTouchOutside(false);
        return bindView.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //调用初始化方式
        initData();
    }

    @Override
    public <T extends View> T findViewById(int id) {
        T t = bindView.getRoot().findViewById(id);
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
