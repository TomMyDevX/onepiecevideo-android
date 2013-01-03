package tommy.dev.onepieceproject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstAC extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_ac);
		new Thread(checkOnline).start();
		
	}
    //////////////////////////////////////////////////////////DCTECT HOME AND BACK PREASSS
    @Override
    public void onBackPressed() {
    	//finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
      //  return super.onKeyDown(keyCode, event);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////
       int count=0;
    Runnable checkOnline=   new Runnable() {
		
		@Override
		public void run() {
			isOnline.post(new Runnable() {
				@Override
				public void run() {
					 TextView module_status=(TextView) findViewById(R.id.module_status);
					 module_status.setText("Initialize");
				}
	    	 	});
	        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	     
	        isOnline.post(new Runnable() {
				@Override
				public void run() {
					 TextView module_status=(TextView) findViewById(R.id.module_status);
					 module_status.setText("Load Net Module");
				}
	    	 	});
	       
               NetworkInfo netInfo = cm.getActiveNetworkInfo();
               if (netInfo != null && netInfo.isConnected()) {
                   try {
           	        isOnline.post(new Runnable() { 
           				@Override
           				public void run() {  
           				 TextView module_status=(TextView) findViewById(R.id.module_status);
    					 module_status.setText("Check your connection.");
    					 module_status.setTextColor(Color.RED);
           					
           				}
           	    	 	});
                       URL url = new URL("http://www.google.com");
                       HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                       urlc.setConnectTimeout(3000);
                       urlc.connect();
  
                       
               if (urlc.getResponseCode() == 200) {
            	  
       	        	isOnline.post(new Runnable() {
    				@Override
    				public void run() {
    					 TextView module_status=(TextView) findViewById(R.id.module_status);
    					 module_status.setText("Welcome!");
    					 module_status.setTextColor(Color.WHITE);
    					 isOnline.post(CONNECT_COMPLETE);
    				}
    	    	 	});
       	      	  
       	      	   urlc.disconnect();
               }else{
            	   isOnline.post(CONNECT_ERROR);
            	   urlc.disconnect();
               }
               } catch (MalformedURLException e1) {
            	   isOnline.post(CONNECT_ERROR);
            	   
           } catch (IOException e) {
        	   	   isOnline.post(CONNECT_ERROR);
               }
               }else{
            	   isOnline.post(CONNECT_ERROR);
               }
           
			
		}
	};

	
	
    	 Runnable   CONNECT_ERROR     =	   new Runnable() {
			@Override
			public void run() {
				Message onstatus=new Message();
				onstatus.what=404;
				isOnline.sendMessage(onstatus);
			}
    	 	};
       	 Runnable   CONNECT_COMPLETE     =	   new Runnable() {
				@Override
				public void run() {
					Message onstatus=new Message();
					onstatus.what=200;
					isOnline.sendMessage(onstatus);
				}
			};
			Handler 
			isOnline=new Handler(){
				@Override
				public void handleMessage(Message msg) {
    		
					if(msg.what==200){
	    			  
						finish();
					    Intent intent = new Intent(FirstAC.this, MainActivity.class);
					    startActivity(intent);
					}else if(msg.what==404){
						AlertDialog.Builder alertbox = new AlertDialog.Builder(FirstAC.this);
						alertbox.setMessage("Please check your connection!");
						alertbox.setCancelable(false);
						alertbox.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
								finish();
							}
		            });
		            alertbox.show();
    			
    		}							
			
    		
    	}
    }; 
    ////////////////////////////////////////////////////////////////////////////////////

	
	public String returnNumber() {
	     String number = null;
	     String service = Context.TELEPHONY_SERVICE;
	     TelephonyManager tel_manager = (TelephonyManager) getSystemService(service);
	     int device_type = tel_manager.getPhoneType();

	     switch (device_type) {
	           case (TelephonyManager.PHONE_TYPE_CDMA):
	              number = tel_manager.getLine1Number();
	           break;
	           default:
	             //return something else
	             number = "no number";
	            break;
	     }
	     return number;
	}

	public String getDeviceName() {
		  String manufacturer = Build.MANUFACTURER;
		  String model = Build.MODEL;
		  if (model.startsWith(manufacturer)) {
		    return capitalize(model);
		  } else {
		    return capitalize(manufacturer) + " " + model;
		  }
		}


		private String capitalize(String s) {
		  if (s == null || s.length() == 0) {
		    return "";
		  }
		  char first = s.charAt(0);
		  if (Character.isUpperCase(first)) {
		    return s;
		  } else {
		    return Character.toUpperCase(first) + s.substring(1);
		  }
		} 

	        
	 
	    	    
	    	
}
