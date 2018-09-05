package com.cbwrapper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.payu.custombrowser.Bank;
import com.payu.custombrowser.CustomBrowser;
import com.payu.custombrowser.PayUCustomBrowserCallback;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.bean.ReviewOrderBundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class CustomBrowserWrapperActivity extends AppCompatActivity {
    private Bank nativePayUCustomBrowser;
    private String tranxID;
    private String merchantKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String request  = getIntent().getExtras().getString(CBWrapperConstant.CONFIG);
        setContentView(R.layout.activity_layout);
        makePayment(request);
    }

    public void makePayment(String request){

        try {
            JSONObject obj = new JSONObject(request);
            JSONObject nativeModuleObject = obj.getJSONObject("NativeMap");
            if (nativeModuleObject.has(CBWrapperConstant.TRANSACTION_ID)) {
                tranxID = nativeModuleObject.getString(CBWrapperConstant.TRANSACTION_ID);
            }

            if (nativeModuleObject.has(CBWrapperConstant.MERCHANT_KEY)) {
                merchantKey = nativeModuleObject.getString(CBWrapperConstant.MERCHANT_KEY);
            }

            CustomBrowserConfig  config = new CustomBrowserConfig(merchantKey,tranxID);

            if (nativeModuleObject.has(CBWrapperConstant.PAYU_POST_DATA)) {
                config.setPayuPostData(nativeModuleObject.getString(CBWrapperConstant.PAYU_POST_DATA));
            }
            if (nativeModuleObject.has(CBWrapperConstant.POST_URL)) {
                config.setPostURL(nativeModuleObject.getString(CBWrapperConstant.POST_URL));
            }

            if (nativeModuleObject.has(CBWrapperConstant.VIEW_PORT_WIDE_ENABLE)) {
                config.setViewPortWideEnable(nativeModuleObject.getBoolean(CBWrapperConstant.VIEW_PORT_WIDE_ENABLE));
            }

            if (nativeModuleObject.has(CBWrapperConstant.AUTO_APPROVE)) {
                config.setAutoApprove(nativeModuleObject.getBoolean(CBWrapperConstant.AUTO_APPROVE));
            }

            if (nativeModuleObject.has(CBWrapperConstant.AUTO_SELECT_OTP)) {
                config.setAutoSelectOTP(nativeModuleObject.getBoolean(CBWrapperConstant.AUTO_SELECT_OTP));
            }

            if (nativeModuleObject.has(CBWrapperConstant.MAGIC_RETRY)) {
                config.setmagicRetry(nativeModuleObject.getBoolean(CBWrapperConstant.MAGIC_RETRY));
            }

            if (nativeModuleObject.has(CBWrapperConstant.ENABLE_SURE_PAY)) {
                config.setEnableSurePay(nativeModuleObject.getInt(CBWrapperConstant.ENABLE_SURE_PAY));
            }

            if (nativeModuleObject.has(CBWrapperConstant.SURE_PAY_MODE)) {
                config.setSurePayMode(nativeModuleObject.getInt(CBWrapperConstant.SURE_PAY_MODE));
            }

            if (nativeModuleObject.has(CBWrapperConstant.SURE_PAY_BACKGROUND_TTL)) {
                config.setSurePayBackgroundTTL(nativeModuleObject.getInt(CBWrapperConstant.SURE_PAY_BACKGROUND_TTL));
            }

            if (nativeModuleObject.has(CBWrapperConstant.INTERNET_RESTORED_WINDOW_TTL)) {
                config.setInternetRestoredWindowTTL(nativeModuleObject.getInt(CBWrapperConstant.INTERNET_RESTORED_WINDOW_TTL));
            }
            if (nativeModuleObject.has(CBWrapperConstant.SDK_VERSION_NAME)) {
                config.setSdkVersionName(nativeModuleObject.getString(CBWrapperConstant.SDK_VERSION_NAME));
            }
            if (nativeModuleObject.has(CBWrapperConstant.MERCHANT_SMS_PERMISSION)) {
                config.setMerchantSMSPermission(nativeModuleObject.getBoolean(CBWrapperConstant.MERCHANT_SMS_PERMISSION));
            }
            if (nativeModuleObject.has(CBWrapperConstant.MERCHANT_CHECKOUT_ACTIVITY_PATH)) {
                config.setMerchantCheckoutActivityPath(nativeModuleObject.getString(CBWrapperConstant.MERCHANT_CHECKOUT_ACTIVITY_PATH));
            }
            if (nativeModuleObject.has(CBWrapperConstant.HTML_DATA)) {
                config.setHtmlData(nativeModuleObject.getString(CBWrapperConstant.HTML_DATA));
            }
            if (nativeModuleObject.has(CBWrapperConstant.REVIEW_ORDER_CUSTOM_VIEW)) {
                config.setReviewOrderCustomView(nativeModuleObject.getInt(CBWrapperConstant.REVIEW_ORDER_CUSTOM_VIEW));
            }
            if (nativeModuleObject.has(CBWrapperConstant.ENABLE_REVIEW_ORDER)) {
                config.setEnableReviewOrder(nativeModuleObject.getInt(CBWrapperConstant.ENABLE_REVIEW_ORDER));
            }
            if (nativeModuleObject.has(CBWrapperConstant.REVIEW_ORDER_BUNDLE)) {
                JSONObject reviewOrderJSONObject = new JSONObject(nativeModuleObject.getString(CBWrapperConstant.REVIEW_ORDER_BUNDLE));
                Iterator iterator = reviewOrderJSONObject.keys();
                ReviewOrderBundle reviewOrderBundle = new ReviewOrderBundle();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    try {
                        String value = reviewOrderJSONObject.getString(key);
                        reviewOrderBundle.addOrderDetails(key, value);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                config.setReviewOrderDefaultViewData(reviewOrderBundle);
            }
            if (nativeModuleObject.has(CBWrapperConstant.REVIEW_ORDER_BUTTON_TEXT)) {
                config.setReviewOrderButtonText(nativeModuleObject.getString(CBWrapperConstant.REVIEW_ORDER_BUTTON_TEXT));
            }
            if (nativeModuleObject.has(CBWrapperConstant.REVIEW_ORDER_BUTTON_TEXT_COLOR)) {
                config.setReviewOrderButtonTextColor(nativeModuleObject.getInt(CBWrapperConstant.REVIEW_ORDER_BUTTON_TEXT_COLOR));
            }
            if (nativeModuleObject.has(CBWrapperConstant.CBDRAWER_CUSTOM_MENU)) {
                config.setCbDrawerCustomMenu(nativeModuleObject.getInt(CBWrapperConstant.CBDRAWER_CUSTOM_MENU));
            }
            if (nativeModuleObject.has(CBWrapperConstant.GMS_PROVIDER_UPDATED_STATUS)) {
                config.setGmsProviderUpdatedStatus(nativeModuleObject.getInt(CBWrapperConstant.GMS_PROVIDER_UPDATED_STATUS));
            }
            if (nativeModuleObject.has(CBWrapperConstant.SURE_PAY_S2SURL)) {
                config.setSurepayS2Surl(nativeModuleObject.getString(CBWrapperConstant.SURE_PAY_S2SURL));
            }
            config.setReactVersion("1.0");

            PayUCustomBrowserCallback payUCustomBrowserCallback = new PayUCustomBrowserCallback() {
                @Override
                public void setCBProperties(WebView webview, Bank payUCustomBrowser) {
                    super.setCBProperties(webview, payUCustomBrowser);
                    nativePayUCustomBrowser = payUCustomBrowser;
                }

                @Override
                public void onPaymentSuccess(String payuResult, String merchantResponse) {
                    super.onPaymentSuccess(payuResult, merchantResponse);
                    getIntent().putExtra(CBWrapperConstant.PAYMENT_STATUS,CBWrapperConstant.STATUS_SUCCESS);
                    getIntent().putExtra(CBWrapperConstant.PAYU_RESPONSE,payuResult);
                    getIntent().putExtra(CBWrapperConstant.MERCHANT_RESPONSE,payuResult);
                    setResult(CBWrapper.START_PAYMENT_OK, getIntent() );
                    if (nativePayUCustomBrowser != null)
                        nativePayUCustomBrowser.getActivity().finish();
                    finish();

                }

                @Override
                public void onPaymentFailure(String payuResult, String merchantResponse) {
                    super.onPaymentFailure(payuResult, merchantResponse);
                    getIntent().putExtra(CBWrapperConstant.PAYMENT_STATUS,CBWrapperConstant.STATUS_FAILURE);
                    getIntent().putExtra(CBWrapperConstant.PAYU_RESPONSE,payuResult);
                    getIntent().putExtra(CBWrapperConstant.MERCHANT_RESPONSE,payuResult);
                    setResult(CBWrapper.START_PAYMENT_OK, getIntent() );
                    if (nativePayUCustomBrowser != null)
                        nativePayUCustomBrowser.getActivity().finish();
                    finish();
                }

                @Override
                public void onPaymentTerminate() {
                    super.onPaymentTerminate();
                    getIntent().putExtra(CBWrapperConstant.PAYMENT_STATUS,CBWrapperConstant.PAYMENT_CANCELLED);
                    setResult(CBWrapper.START_PAYMENT_OK, getIntent() );
                    nativePayUCustomBrowser.getActivity().finish();
                    finish();
                }
            };

            new CustomBrowser().addCustomBrowser(this, config, payUCustomBrowserCallback);
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }

}
