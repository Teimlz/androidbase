package pers.fazhi.androidbase.action;

import android.view.View;
import androidx.annotation.IdRes;

/**
 * @author FaZhi
 * @date 2021年01月07 20点14分
 * @description This is ClickAction Interface.
 */
public interface ClickAction extends View.OnClickListener{

    <T extends View> T findViewById(@IdRes int id);

    default void setOnClickListener(@IdRes int... ids) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(this);
        }
    }

    default void setOnClickListener(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    @Override
    default void onClick(View v) {
        // 默认不实现，让子类实现
    }
}
