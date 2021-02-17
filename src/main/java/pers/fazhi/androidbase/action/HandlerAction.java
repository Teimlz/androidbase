package pers.fazhi.androidbase.action;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/**
 * @author FaZhi
 * @date 2021年02月17日 09时53分
 * @description This is HandlerAction Interface.
 */
public interface HandlerAction{

    default Handler getHandler(){
        //由子类实现，如果子类不实现则返回null，处理时使用new Handler(Looper.getMainLooper());的handler进行处理
        return null;
    }

    /**
     * 延迟执行
     */
    default boolean handlerPost(Runnable r) {
        return handlerPostDelayed(r, 0);
    }

    /**
     * 延迟一段时间执行
     *
     * @param r           执行的线程
     * @param delayMillis 延时的毫秒数
     */
    default boolean handlerPostDelayed(Runnable r, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return handlerPostAtTime(r, SystemClock.uptimeMillis() + delayMillis);
    }

    /**
     * 在指定的时间执行
     *
     * @param r            执行的线程
     * @param uptimeMillis 在多少毫秒后执行
     */
    default boolean handlerPostAtTime(Runnable r, long uptimeMillis) {
        // 发送和这个 Activity 相关的消息回调
        Handler handler = getHandler();
        if (handler == null){
            handler = new Handler(Looper.getMainLooper());
        }
        return handler.postAtTime(r, this, uptimeMillis);
    }

    /**
     * 移除单个消息回调
     */
    default void removeCallbacks(Runnable r) {
        if (getHandler() != null){
            getHandler().removeCallbacks(r);
        }
    }

    /**
     * 移除全部消息回调
     */
    default void removeCallbacks() {
        if (getHandler() != null){
            getHandler().removeCallbacksAndMessages(this);
        }
    }
}
