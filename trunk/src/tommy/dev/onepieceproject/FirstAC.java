package tommy.dev.onepieceproject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.Menu;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class FirstAC extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_ac);
		
			Handler myHandler = new Handler();
			myHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(isOnline()){
				    checkState();
					finish();
				    Intent intent = new Intent(FirstAC.this, MainActivity.class);
				    startActivity(intent);
					}else{                 
			            AlertDialog.Builder alertbox = new AlertDialog.Builder(FirstAC.this);
			            alertbox.setMessage("Please check your connection!");
			            alertbox.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface arg0, int arg1) {
			                   finish();
			                }
			            });
			            alertbox.show();
					}
				}
			}, 1000);

		    
		    
		
	}

	public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
               NetworkInfo netInfo = cm.getActiveNetworkInfo();
               if (netInfo != null && netInfo.isConnected()) {
                   try {
                       URL url = new URL("http://www.google.com");
                       HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                       urlc.setConnectTimeout(3000);
                       urlc.connect();
               if (urlc.getResponseCode() == 200) {
                       return new Boolean(true);
               }
               } catch (MalformedURLException e1) {
                       // TODO Auto-generated catch block
                       e1.printStackTrace();
           } catch (IOException e) {
                       // TODO Auto-generated catch block
                       e.printStackTrace();
               }
               }
               return false;
       } 
	
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
	
	public void checkState(){
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
		Account[] list = manager.getAccountsByType("com.google");
		
		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		Pattern PHONEPattern = Patterns.PHONE;// API level 8+
		
		Account[] accounts = AccountManager.get(this).getAccounts();
		
		String email="";
		String phonenumber="";
		
		for (Account account : accounts) {
		   if (emailPattern.matcher(account.name).matches()) {
			   email= account.name;
		      
		   }
		   if (PHONEPattern.matcher(account.name).matches()) {
			   phonenumber= account.name;
		       
		   }
		}
		
		

		
		HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://opvideosite.neezyl.com/data/phonedata/verifyphone.php");

	    //This is the data to send
	  

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
	        nameValuePairs.add(new BasicNameValuePair("imei", telephonyManager.getDeviceId()));
	        nameValuePairs.add(new BasicNameValuePair("device", getDeviceName()));
	        nameValuePairs.add(new BasicNameValuePair("osversion", android.os.Build.VERSION.RELEASE));
	        nameValuePairs.add(new BasicNameValuePair("account", ""+email));
	        nameValuePairs.add(new BasicNameValuePair("number", ""+phonenumber));
	        
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        httppost.setHeader( "Cache-Control", "no-cache" );
	        // Execute HTTP Post Request

	        ResponseHandler<String> responseHandler = new BasicResponseHandler();
	        String response = httpclient.execute(httppost, responseHandler);


	        //This is the response from a php application
	        String reverseString = response;
	        
	       // Toast.makeText(this, "response" + reverseString, Toast.LENGTH_LONG).show();

	    } catch (ClientProtocolException e) {
	       // Toast.makeText(this, "CPE response " + e.toString(), Toast.LENGTH_LONG).show();
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	       // Toast.makeText(this, "IOE response " + e.toString(), Toast.LENGTH_LONG).show();
	        // TODO Auto-generated catch block
	    }
		
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
	    private String getMyPhoneNumber(){
	        TelephonyManager mTelephonyMgr;
	        mTelephonyMgr = (TelephonyManager)
	                getSystemService(Context.TELEPHONY_SERVICE); 
	        return mTelephonyMgr.getLine1Number();
	        }

	        private String getMy10DigitPhoneNumber(){
	                String s = getMyPhoneNumber();
	                return s.substring(2);
	        }
}
