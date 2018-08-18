//
//  RCTNodePlayerView.java
//
//  Created by Mingliang Chen on 2017/11/29.
//  Copyright © 2017年 NodeMedia. All rights reserved.
//

package cn.nodemedia.react_native_nodemediaclient;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import cn.nodemedia.NodePlayer;
import cn.nodemedia.NodePlayerDelegate;
import cn.nodemedia.NodePlayerView;

public class RCTNodePlayerView extends NodePlayerView implements LifecycleEventListener {
    private NodePlayer mNodePlayer;
    private Boolean isAutoPlay = false;

    public RCTNodePlayerView(ReactApplicationContext context) {
        super(context);
        context.addLifecycleEventListener(this);
        mNodePlayer = new NodePlayer(context, RCTNodeMediaClient.getPremium());
        mNodePlayer.setPlayerView(this);
        mNodePlayer.setNodePlayerDelegate(new NodePlayerDelegate() {
            @Override
            public void onEventCallback(NodePlayer nodePlayer, int i, String s) {
                WritableMap event = Arguments.createMap();
                event.putInt("code", i);
                event.putString("message", "s");
                ReactContext reactContext = (ReactContext) getContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        getId(),
                        "topChange",
                        event);

            }
        });
    }

    public void setInputUrl(String inputUrl) {
        mNodePlayer.setInputUrl(inputUrl);
        if(isAutoPlay) {
            start();
        }
    }

    public void setBufferTime(int bufferTime) {
        mNodePlayer.setBufferTime(bufferTime);
    }

    public void setMaxBufferTime(int maxBufferTime) {
        mNodePlayer.setMaxBufferTime(maxBufferTime);
    }

    public void setScaleMode(String smode) {
        NodePlayerView.UIViewContentMode mode = NodePlayerView.UIViewContentMode.valueOf(smode);
        setUIViewContentMode(mode);
    }

    public void setRenderType(String stype) {
        NodePlayerView.RenderType type =  NodePlayerView.RenderType.valueOf(stype);
        setRenderType(type);
    }

    public void setHWEnable(Boolean hwEnable) {
        mNodePlayer.setHWEnable(hwEnable);
    }


    public int pause() {
        return mNodePlayer.pause();
    }

    public int start() {
        return mNodePlayer.start();
    }

    public int stop() {
        return mNodePlayer.stop();
    }

    public void seekTo(long position) {
        mNodePlayer.seekTo(position);
    }

    public long getDuration() {
        return mNodePlayer.getDuration();
    }

    public long getCurrentPosition() {
        return mNodePlayer.getCurrentPosition();
    }

    public long getBufferPosition() {
        return mNodePlayer.getBufferPosition();
    }

    public int getBufferPercentage() {
        return mNodePlayer.getBufferPercentage();
    }

    public boolean isPlaying() {
        return mNodePlayer.isPlaying();
    }

    public boolean isLive() {
        return mNodePlayer.isLive();
    }

    public void release(){
        mNodePlayer.release();
    }

    public void autoPlay() {
        isAutoPlay = true;
        start();
    }

    @Override
    public void onHostResume() {

    }

    @Override
    public void onHostPause() {

    }

    @Override
    public void onHostDestroy() {
        stop();
        release();
    }
}
