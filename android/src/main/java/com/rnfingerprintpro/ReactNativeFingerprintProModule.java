package com.rnfingerprintpro;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.util.Log;

import androidx.annotation.MainThread;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;
import static android.app.Activity.RESULT_OK;

import com.fingerprintjs.android.fpjs_pro.Configuration;
import com.fingerprintjs.android.fpjs_pro.FingerprintJS;
import com.fingerprintjs.android.fpjs_pro.FingerprintJSFactory;

@ReactModule(name = ReactNativeFingerprintProModule.NAME)
public class ReactNativeFingerprintProModule extends ReactContextBaseJavaModule implements InstallStateUpdatedListener, ActivityEventListener {
    public static final String NAME = "FingerprintPro";
    public static int IN_APP_UPDATE_REQUEST_CODE = 42139;

    private FingerprintJSFactory factory = null;
    private Configuration configuration = new Configuration("g7gd68Lh2Ey4xFJOF5RE");
    private FingerprintJS fpjsClient = null;

    public ReactNativeFingerprintProModule(ReactApplicationContext reactContext) {
        super(reactContext);
        // appUpdateManager = AppUpdateManagerFactory.create(reactContext);
        // appUpdateManager.registerListener(this);
        factory = new FingerprintJSFactory(reactContext);
        fpjsClient = factory.createInstance(configuration);
        reactContext.addActivityEventListener(this);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void getVisitorId(Promise resolutionPromise) {
      try {
        fpjsClient.getVisitorId(visitorIdResponse -> {
          // Use the ID
          String visitorId = visitorIdResponse.getVisitorId();
          resolutionPromise.resolve(visitorId);
        });
      } catch (IntentSender.SendIntentException e) {
        resolutionPromise.reject("SendIntentException","Error into getVisitorId: "+e.toString());
      }
    }

    @ReactMethod
    public void addListener(String eventName) {
        // Keep: Required for RN built in Event Emitter Calls.
    }

    @ReactMethod
    public void removeListeners(double count) {
        // Keep: Required for RN built in Event Emitter Calls.
    }

    @Override
    public void onNewIntent(Intent intent) {
        // no-op
    }
}
