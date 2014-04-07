package com.phonegap.helloworld;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;

public class TestPlugin extends CordovaPlugin {
	 public static final String ACTION_TEST_PLUGIN_ENTRY = "testPluginEntry";
	 public static CallbackContext cc; 
	 
	 @Override
	 public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		 try {
			 	System.out.println(":::Test Plugin from JS "+action);
			    if (ACTION_TEST_PLUGIN_ENTRY.equals(action)) { 
			    	cc = callbackContext;
			    	testFunc();
//			    	callbackContext.success();
			    }
//			    callbackContext.error("Invalid action");
			    return false;
			} catch(Exception e) {
			    System.err.println("Test Plugin Exception: " + e.getMessage());
			    callbackContext.error(e.getMessage());
			    return false;
			} 
	 }
	 
	    public void testFunc(){
		   	System.out.println(":::testFunc");
		   	
		   	final Intent intent = new Intent();
		    intent.putExtra("result","good");
		   	ComponentName cName = new ComponentName
		   	("com.idmission.apitservice","com.idmission.apitservicelib.web.TestActivity");

		   	intent.setComponent(cName);         
//		   	startActivity(intent);
		   	
//		    Intent i = new Intent(Intent.ACTION_SEND, Uri.parse("content://contacts"));
//		    i.setType(HTTP.PLAIN_TEXT_TYPE);
//		   	Intent i = new Intent("com.sample.action.MY_CUSTOM_ACTION");
//		    System.out.println(":::testFunc");
//		    PackageManager packageManager = this.cordova.getActivity().getPackageManager();
//		    System.out.println(":::testFunc");
//		    List<ResolveInfo> activities = packageManager.queryIntentActivities(i, 0);
//		    System.out.println(":::testFunc");
//		    boolean isIntentSafe = activities.size() > 0;
//		    System.out.println(":::testFunc");
//		    if(isIntentSafe){
		    	System.out.println(":::IntentSafe");
		    	this.cordova.setActivityResultCallback(TestPlugin.this); 
		    	this.cordova.getActivity().startActivityForResult(intent, 101);
//		    }
		 }
}