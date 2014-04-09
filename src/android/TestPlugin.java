package com.phonegap.helloworld;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.ComponentName;
import android.content.Intent;

public class TestPlugin extends CordovaPlugin {
	public static final String ACTION_TEST_PLUGIN_ENTRY = "testPluginEntry";
	public static CallbackContext cc;
	public static String data;
	
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
	    super.initialize(cordova, webView);
	    cc = null;
	    data = null;
	}
	
	@Override
	public boolean execute(String action, JSONArray args,
			final CallbackContext callbackContext) throws JSONException {
		cc = callbackContext;
		try {
			System.out.println(":::TestPlugin action " + action);
			if (ACTION_TEST_PLUGIN_ENTRY.equals(action)) {
				JSONObject arg_object = args.getJSONObject(0);
				System.out.println(":::TestPlugin data " + arg_object.getString("title"));
				data = arg_object.getString("title");
				
	            cordova.getThreadPool().execute(new Runnable() {
	                public void run() {
	                	testFunc();// Thread-safe.
	                }
	            });
				return true;
			}else{
				cc.error("Invalid action");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Test Plugin Exception: ");
			System.err.println("Test Plugin Exception: " + e.getMessage());
			cc.error(e.getMessage());
			return false;
		}
	}

	public void testFunc() {
		final Intent intent = new Intent();
		intent.putExtra("result", data);
		ComponentName cName = new ComponentName("com.idmission.apitservice",
				"com.idmission.apitservicelib.web.TestActivity");
		intent.setComponent(cName);
		this.cordova.setActivityResultCallback(TestPlugin.this);
		this.cordova.getActivity().startActivityForResult(intent, 101);
	}

	public void sonActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 101) {
			if (resultCode == -1) {
				String result = data.getStringExtra("result");
				System.out.println(":::onActivityResult data " + result);
                cc.success(result.toString());
			} else {
				System.out.println(":::onActivityResult resultCode FAIL");
				cc.error("resultCode Failed");
			}
		} else {
			System.out.println(":::onActivityResult requestCode FAIL");
			cc.error("requestCode Failed");
		}
	}
}