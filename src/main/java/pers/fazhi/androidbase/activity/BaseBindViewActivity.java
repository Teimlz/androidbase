package pers.fazhi.androidbase.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.*;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.viewbinding.ViewBinding;
import pers.fazhi.androidbase.R;
import pers.fazhi.androidbase.action.ClickAction;
import pers.fazhi.androidbase.action.HandlerAction;

/**
 * @author FaZhi
 * @date 2021年01月06 11点36分
 * @description This is BaseBindViewActivity Class.
 */
public abstract class BaseBindViewActivity<Bind extends ViewBinding> extends AppCompatActivity implements ClickAction,Handler.Callback, HandlerAction {

    protected Handler handler = new Handler(Looper.getMainLooper(),this);

    public String TAG = getClass().getSimpleName();

    protected Bind bindView;

    protected Dialog waitDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化bindView
        bindView = getBindView();
        //设置ContentView
        setContentView(bindView.getRoot());
        //初始化Dialog
        waitDialog = new AppCompatDialog(this);
        waitDialog.setContentView(R.layout.dialog_base_loading);
        waitDialog.setCanceledOnTouchOutside(false);
        //初始化initData
        initData();
        //隐藏输入法
        hideSoftInput();
    }

    protected abstract Bind getBindView();

    protected abstract void initData();


    //点击外部自动隐藏输入法
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideSoftInput();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 返回点击的组件
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if ((v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationOnScreen(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom);
        }
        return false;
    }

    public void hideSoftInput() {
        Window window = this.getWindow();
        View view = window.getCurrentFocus();
        if (view == null) {
            View decorView = window.getDecorView();
            View focusView = decorView.findViewWithTag("keyboardTagView");
            if (focusView == null) {
                view = new EditText(window.getContext());
                view.setTag("keyboardTagView");
                ((ViewGroup) decorView).addView(view, 0, 0);
            } else {
                view = focusView;
            }
            view.requestFocus();
        }
        //隐藏View的输入法
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    protected void onDestroy() {
        removeCallbacks();
        super.onDestroy();
    }


    @Override
    public void finish() {
        hideSoftInput();//隐藏软输入法
        super.finish();
    }
    /**
     * 获取 Handler
     */
    @Override
    public Handler getHandler() {
        return handler;
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        //交给子类完成
        return false;
    }


    /**
     * 帮findViewById改个名字,顺便优化一下，当绑定异常时，抛出空指针异常
     * @param id 资源的ID
     * @param <T> View的子类
     * @return 绑定的View
     */
    protected <T extends View> T bindView(@IdRes int id){
        T t = super.findViewById(id);
        if (t == null){
            throw new NullPointerException("resource id "+ id +" not find! findViewById error!");
        }
        return t;
    }

    public String getTAG(){
        return TAG;
    }

    public void showWait(){
        waitDialog.show();
    }

    public void dismessWait(){
        waitDialog.dismiss();
    }
}
