
package com.cbwrapper;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.payu.custombrowser.util.CBConstant;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CBWrapper extends ReactContextBaseJavaModule implements LifecycleEventListener {
  private ReactApplicationContext reactContext;
  private Promise promise;
  static final int START_PAYMENT_OK = 1;

  public CBWrapper(ReactApplicationContext reactContext) {

    super(reactContext);
    this.reactContext = reactContext;
    reactContext.addActivityEventListener(mActivityEventListener);
    reactContext.addLifecycleEventListener(this);
  }

  @Override
  public void onHostResume() {

  }

  @Override
  public void onHostPause() {

  }

  @Override
  public void onHostDestroy() {

  }

  @Override
  public String getName() {
    return "CBWrapper";
  }

  @ReactMethod
  public void startPayment(ReadableMap map, Promise promise){
    this.promise = promise;
    Intent intent = new Intent(reactContext,CustomBrowserWrapperActivity.class);
    intent.putExtra(CBWrapperConstant.CONFIG, map.toString());
    reactContext.startActivityForResult(intent, START_PAYMENT_OK,null);

  }
  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(CBWrapperConstant.PAYMENT_STATUS, CBWrapperConstant.PAYMENT_STATUS);
    constants.put(CBWrapperConstant.STATUS_FAILURE, CBWrapperConstant.STATUS_FAILURE);
    constants.put(CBWrapperConstant.STATUS_SUCCESS, CBWrapperConstant.STATUS_SUCCESS);
    constants.put(CBWrapperConstant.PAYMENT_CANCELLED,CBWrapperConstant.PAYMENT_CANCELLED);
    constants.put(CBWrapperConstant.SURE_PAY_WARN_MODE, CBConstant.WARN_MODE);
    constants.put(CBWrapperConstant.SURE_PAY_FAIL_MODE, CBConstant.FAIL_MODE);
    constants.put(CBWrapperConstant.STORE_ONE_CLICK_HASH, CBWrapperConstant.STORE_ONE_CLICK_HASH);
    constants.put(CBWrapperConstant.DISABLE_BACK_BUTTON_DIALOG, CBWrapperConstant.DISABLE_BACK_BUTTON_DIALOG);
    constants.put(CBWrapperConstant.SDK_VERSION_NAME, CBWrapperConstant.SDK_VERSION_NAME);
    constants.put(CBWrapperConstant.MERCHANT_SMS_PERMISSION, CBWrapperConstant.MERCHANT_SMS_PERMISSION);
    constants.put(CBWrapperConstant.MERCHANT_CHECKOUT_ACTIVITY_PATH, CBWrapperConstant.MERCHANT_CHECKOUT_ACTIVITY_PATH);
    constants.put(CBWrapperConstant.HTML_DATA, CBWrapperConstant.HTML_DATA);
    constants.put(CBWrapperConstant.REVIEW_ORDER_CUSTOM_VIEW, CBWrapperConstant.REVIEW_ORDER_CUSTOM_VIEW);
    constants.put(CBWrapperConstant.ENABLE_REVIEW_ORDER, CBWrapperConstant.ENABLE_REVIEW_ORDER);
    constants.put(CBWrapperConstant.REVIEW_ORDER_BUTTON_TEXT, CBWrapperConstant.REVIEW_ORDER_BUTTON_TEXT);
    constants.put(CBWrapperConstant.REVIEW_ORDER_BUTTON_TEXT_COLOR, CBWrapperConstant.REVIEW_ORDER_BUTTON_TEXT_COLOR);
    constants.put(CBWrapperConstant.CBDRAWER_CUSTOM_MENU, CBWrapperConstant.CBDRAWER_CUSTOM_MENU);
    constants.put(CBWrapperConstant.ENABLE_WEB_FLOW, CBWrapperConstant.ENABLE_WEB_FLOW);
    constants.put(CBWrapperConstant.GMS_PROVIDER_UPDATED_STATUS, CBWrapperConstant.GMS_PROVIDER_UPDATED_STATUS);
    constants.put(CBWrapperConstant.REVIEW_ORDER_BUNDLE, CBWrapperConstant.REVIEW_ORDER_BUNDLE);

    return constants;
  }

  private final ActivityEventListener mActivityEventListener = new ActivityEventListener() {
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
      JSONObject jsonObject = new JSONObject();
      try {
        jsonObject.put(CBWrapperConstant.PAYMENT_STATUS,  data.getStringExtra(CBWrapperConstant.PAYMENT_STATUS));
        jsonObject.put(CBWrapperConstant.PAYU_RESPONSE, data.getStringExtra(CBWrapperConstant.PAYU_RESPONSE));
        jsonObject.put(CBWrapperConstant.MERCHANT_RESPONSE, data.getStringExtra(CBWrapperConstant.MERCHANT_RESPONSE));
        promise.resolve(jsonObject.toString());
      }catch(Exception e){
        e.printStackTrace();
        promise.reject(CBWrapperConstant.ERROR_CODE, CBWrapperConstant.ERROR_MESSAGE);
      }
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
  };
}




