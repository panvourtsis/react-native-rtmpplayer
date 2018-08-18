//
//  NodeMediaReactPackage.java
//
//  Created by Mingliang Chen on 2017/11/29.
//  Copyright © 2017年 NodeMedia. All rights reserved.
//

package cn.nodemedia.react_native_nodemediaclient;

import android.util.Log;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.List;

public class NodeMediaReactPackage implements ReactPackage{
    private static NodePlayerViewManager viewNodePlayerManager;

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        Log.d("ReactNativeBleManage", "inside native module 1");
        if (viewNodePlayerManager == null) {
            Log.d("ReactNativeBleManage", "inside native module 2");
            viewNodePlayerManager = new NodePlayerViewManager(reactContext);
        }
        return Arrays.<NativeModule>asList(
                new RCTNodeMediaClient(reactContext, viewNodePlayerManager)
        );
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        if (viewNodePlayerManager == null) {
            Log.d("ReactNativeBleManage", "inside view manager");
            viewNodePlayerManager = new NodePlayerViewManager(reactContext);
        }
        return Arrays.<ViewManager>asList(
                viewNodePlayerManager,
                new NodeCameraViewManager()
        );
    }
}
