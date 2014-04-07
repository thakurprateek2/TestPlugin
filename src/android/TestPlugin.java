package com.phonegap.helloworld;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;

public class TestPlugin extends CordovaPlugin {
	 public static final String ACTION_TEST_PLUGIN_ENTRY = "testPluginEntry";
	 HelloWorld hw = new HelloWorld(); 
	 
	 @Override
	 public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		 try {
			 	System.out.println(":::Test Plugin from JS "+action);
			    if (ACTION_TEST_PLUGIN_ENTRY.equals(action)) { 
			    	hw.testFunc(callbackContext);
			    }
			    callbackContext.error("Invalid action");
			    return false;
			} catch(Exception e) {
			    System.err.println("Test Plugin Exception: " + e.getMessage());
			    callbackContext.error(e.getMessage());
			    return false;
			} 
	 }
	 
}