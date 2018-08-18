package cn.nodemedia.react_native_nodemediaclient;

/**
 * Created by aliang on 2018/2/28.
 */

import android.util.Log;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import cn.nodemedia.NodePlayer;
import cn.nodemedia.react_native_nodemediaclient.NodePlayerViewManager;
import cn.nodemedia.react_native_nodemediaclient.RCTNodeMediaClient;
import cn.nodemedia.react_native_nodemediaclient.RCTNodePlayerView;

public class RCTNodeMediaClient extends ReactContextBaseJavaModule {
    private static String mPremium = "";
    private static RCTNodePlayerView mNodePlayerView;

    public RCTNodeMediaClient(ReactApplicationContext reactContext, NodePlayerViewManager viewNodePlayer) {
        super(reactContext);
        if (viewNodePlayer != null) {
            mNodePlayerView = viewNodePlayer.getNodePlayerView();
        }
    }

    @Override
    public String getName() {
        return "NodeMediaClient";
    }

    @ReactMethod
    public void setPremium(String premium) {
        mPremium = premium;
    }

    @ReactMethod
    public void getDuration(Callback cb) {
        if (mNodePlayerView != null) {
            try {
                cb.invoke(Long.toString(mNodePlayerView.getDuration()), null);
            } catch (Exception e){
                cb.invoke(null, e.toString());
            }
        }
    }

    @ReactMethod
    public void getCurrentPosition(Callback cb) {
        if (mNodePlayerView != null) {
            try {
                cb.invoke(Long.toString(mNodePlayerView.getCurrentPosition()), null);
            } catch (Exception e){
                cb.invoke(null, e.toString());
            }
        }
    }

    @ReactMethod
    public void getBufferPosition(Callback cb) {
        if (mNodePlayerView != null) {
            try {
                cb.invoke(Long.toString(mNodePlayerView.getBufferPosition()), null);
            } catch (Exception e){
                cb.invoke(null, e.toString());
            }
        }
    }

    @ReactMethod
    public void getBufferPercentage(Callback cb) {
        if (mNodePlayerView != null) {
            try {
                cb.invoke(Long.toString(mNodePlayerView.getBufferPercentage()), null);
            } catch (Exception e){
                cb.invoke(null, e.toString());
            }
        }
    }

    @ReactMethod
    public void isPlaying(Callback cb) {
        if (mNodePlayerView != null) {
            try {
                cb.invoke(Boolean.toString(mNodePlayerView.isPlaying()), null);
            } catch (Exception e){
                cb.invoke(null, e.toString());
            }
        }
    }

    @ReactMethod
    public void isLive(Callback cb) {
        if (mNodePlayerView != null) {
            try {
                cb.invoke(Boolean.toString(mNodePlayerView.isLive()), null);
            } catch (Exception e){
                cb.invoke(null, e.toString());
            }
        }
    }

    @ReactMethod
    public void seekTo(String position) {
        if (mNodePlayerView != null) {
            mNodePlayerView.seekTo(Long.parseLong(position));
        }
    }

    public static String getPremium() {
        return mPremium;
    }
}
