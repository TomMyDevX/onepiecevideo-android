package tommy.dev.onepieceproject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    protected static final Handler handler = new Handler();
	final MainActivity context=this;
  public  static  ListView list;
    LazyAdapter adapter;

    
    public void  isAvailable() {
		Intent tostart = new Intent(Intent.ACTION_VIEW);
		tostart.setDataAndType(Uri.parse("1.mp4"), "video/*");
 	   final PackageManager mgr = context.getPackageManager();
 	   List<ResolveInfo> list =
 	      mgr.queryIntentActivities(tostart, 
 	         PackageManager.MATCH_DEFAULT_ONLY);
 	   Log.e("app video",""+list.size());
 	  if(list.size()<=0){
     
      	try {
      		context. startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.mxtech.videoplayer.ad")));
      	} catch (android.content.ActivityNotFoundException anfe) {
      		context. startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.mxtech.videoplayer.ad")));
      	}
      finish();
      }
     
 	  
 	 
 	
 	}
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
       
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.main);
        isAvailable();

        
        
        
        final TextView loading=(TextView) findViewById(R.id.loading);  final TextView modestatus=(TextView) findViewById(R.id.modestatus);
        final CheckBox en=(CheckBox) findViewById(R.id.en);
        final CheckBox es=(CheckBox) findViewById(R.id.es);
        final CheckBox th=(CheckBox) findViewById(R.id.th);
        final CheckBox ge=(CheckBox) findViewById(R.id.ge);
        final ImageView im_lang=(ImageView) findViewById(R.id.icon_img);
        goOnline();
        
        
        ImageView im=(ImageView) findViewById(R.id.ImageView1);
        int[] cards={R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6,R.drawable.i7,R.drawable.i8};
        Random r = new Random();
        int n=r.nextInt(8);
        im.setImageResource(cards[n]);
        loading.setVisibility(View.GONE);
        
        
        makeloading(true);
		String urlxml="";
		if(getdefaultMovie()==0){
			 urlxml="http://opvideosite.neezyl.com/data/dataen.xml";	
		}else if(getdefaultMovie()==1){
			 urlxml="http://opvideosite.neezyl.com/data/mven.xml";	
		}else if(getdefaultMovie()==2){
			 urlxml="http://opvideosite.neezyl.com/data/manga/mangaen.xml";	
		}
		if(getdefaultMovie()==0){
			modestatus.setText("One Piece");
		}else if(getdefaultMovie()==1){
			modestatus.setText("The Movie");
		}else if(getdefaultMovie()==2){
			modestatus.setText("Manga");
		}
		ArrayList<HashMap<String, String>> listdata  = testmr(urlxml);
        list=(ListView)findViewById(R.id.list);
        adapter=new LazyAdapter(this, listdata);
        list.setAdapter(adapter);
        list.setSelected(true);
		en.setChecked(true);
		es.setChecked(false);
		th.setChecked(false);
      //  Button b=(Button)findViewById(R.id.button1);
       // b.setOnClickListener(listener);

       
        
     
        en.setOnClickListener(new OnClickListener() {
        	 
			
			@Override
			public void onClick(View arg0) {
				makeloading(true);
				String urlxml="";
				if(getdefaultMovie()==0){
					 urlxml="http://opvideosite.neezyl.com/data/dataen.xml";	
				}else if(getdefaultMovie()==1){
					 urlxml="http://opvideosite.neezyl.com/data/mven.xml";	
				}else if(getdefaultMovie()==2){
					 urlxml="http://opvideosite.neezyl.com/data/manga/mangaen.xml";	
				}
				if(getdefaultMovie()==0){
					modestatus.setText("One Piece");
				}else if(getdefaultMovie()==1){
					modestatus.setText("The Movie");
				}else if(getdefaultMovie()==2){
					modestatus.setText("Manga");
				}
				ArrayList<HashMap<String, String>> listdata = testmr(urlxml);
				 adapter=new LazyAdapter(context, listdata);
				 list.setAdapter(adapter);
				
				adapter.notifyDataSetChanged();
				//if (((CheckBox) en).isChecked()) {
				im_lang.setImageResource(R.drawable.us);
					en.setChecked(true);
					es.setChecked(false);th.setChecked(false);
				//}
				
			}
		});
        ge.setOnClickListener(new OnClickListener() {
        	 
			
			@Override
			public void onClick(View arg0) {
				makeloading(true);
				String urlxml="";
				if(getdefaultMovie()==0){
					 urlxml="http://opvideosite.neezyl.com/data/datager.xml";	
				}else if(getdefaultMovie()==1){
					 urlxml="http://opvideosite.neezyl.com/data/mvger.xml";	
				}else if(getdefaultMovie()==2){
					 urlxml="http://opvideosite.neezyl.com/data/manga/mangager.xml";	
				}
				if(getdefaultMovie()==0){
					modestatus.setText("One Piece");
				}else if(getdefaultMovie()==1){
					modestatus.setText("The Movie");
				}else if(getdefaultMovie()==2){
					modestatus.setText("Manga");
				}
				ArrayList<HashMap<String, String>> listdata = testmr(urlxml);
				 adapter=new LazyAdapter(context, listdata);
				 list.setAdapter(adapter);
				
				adapter.notifyDataSetChanged();
				//if (((CheckBox) en).isChecked()) {
				im_lang.setImageResource(R.drawable.ge);
					en.setChecked(true);
					es.setChecked(false);th.setChecked(false);
				//}
				
			}
		});
        
        th.setOnClickListener(new OnClickListener() {
        	 
			
			@Override
			public void onClick(View arg0) {
				makeloading(true);
				String urlxml="";
				if(getdefaultMovie()==0){
					 urlxml="http://opvideosite.neezyl.com/data/datath.xml";	
				}else if(getdefaultMovie()==1){
					 urlxml="http://opvideosite.neezyl.com/data/mvth.xml";	
				}else if(getdefaultMovie()==2){
					 urlxml="http://opvideosite.neezyl.com/data/manga/mangath.xml";	
				}
				if(getdefaultMovie()==0){
					modestatus.setText("One Piece");
				}else if(getdefaultMovie()==1){
					modestatus.setText("The Movie");
				}else if(getdefaultMovie()==2){
					modestatus.setText("Manga");
				}
				ArrayList<HashMap<String, String>> listdata = testmr(urlxml);
				 adapter=new LazyAdapter(context, listdata);
				 list.setAdapter(adapter);
				
				adapter.notifyDataSetChanged();
				//if (((CheckBox) en).isChecked()) {
				im_lang.setImageResource(R.drawable.flag_th2);
					th.setChecked(true);
					es.setChecked(false);en.setChecked(false);
				//}
				
			}
		});
        es.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				makeloading(true);
				String urlxml="";
				if(getdefaultMovie()==0){
					 urlxml="http://opvideosite.neezyl.com/data/dataes.xml";	
				}else if(getdefaultMovie()==1){
					 urlxml="http://opvideosite.neezyl.com/data/mves.xml";	
				}else if(getdefaultMovie()==2){
					 urlxml="http://opvideosite.neezyl.com/data/manga/mangaes.xml";	
				}
				if(getdefaultMovie()==0){
					modestatus.setText("One Piece");
				}else if(getdefaultMovie()==1){
					modestatus.setText("The Movie");
				}else if(getdefaultMovie()==2){
					modestatus.setText("Manga");
				}
				ArrayList<HashMap<String, String>> listdata = testmr(urlxml);
			
				 adapter=new LazyAdapter(context, listdata);
				 list.setAdapter(adapter);
				//adapter.setData(listdata);
				 adapter.notifyDataSetChanged();
				//if (((CheckBox) es).isChecked()) {
				 im_lang.setImageResource(R.drawable.flag_sp);
					en.setChecked(false);
					es.setChecked(true);th.setChecked(false);
				//}
				
			}
		});

      final CheckBox cb_menu=(CheckBox) findViewById(R.id.bt_img_menu);
		 registerForContextMenu(cb_menu);
		
    
      cb_menu.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		Log.e("","setting click");
		 openContextMenu(v);
			
		}
	});
    }
    
    //////////////////////////////////////////////////////////DCTECT HOME AND BACK PREASSS
    @Override
    public void onBackPressed() {
    	goOffline();
    	super.onBackPressed();
    }


    
    /////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onDestroy()
    {
        list.setAdapter(null);
        super.onDestroy();
    }
    
    public OnClickListener listener=new OnClickListener(){
        @Override
        public void onClick(View arg0) {
            adapter.imageLoader.clearCache();
            adapter.notifyDataSetChanged();
        }
    };

	////////////////////////////CONTECT MENU////////////////////////////////////////////////////////////////////////////////////////

	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu.setHeaderIcon(R.drawable.system_config_boot);
            menu.setHeaderTitle("App Setting.");
            MenuInflater inflater = getMenuInflater();

            inflater.inflate(R.menu.menu, menu);
    }
//	 public boolean onContextItemSelected(MenuItem item) {
//		 switch (item.getItemId()) {
//		 case 1: //Action for menu item “Edit”
//		 return true;
//		 case 2: //Action for Menu item “Delete”
//		 return true;
//		 default: return super.onContextItemSelected(item);
//		 }
//		}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//	MenuInflater inflater = getMenuInflater();
//	inflater.inflate(R.menu.menu, menu);
//	return true;
//	}

	@Override
	 public boolean onContextItemSelected(MenuItem item) {
        final CheckBox en=(CheckBox) findViewById(R.id.en);
        final CheckBox es=(CheckBox) findViewById(R.id.es);
        final CheckBox th=(CheckBox) findViewById(R.id.th);
        ArrayList<HashMap<String, String>>  listdata ;
		switch (item.getItemId()) {
		case R.id.menumoviemode:onCreateDialog(MODE_DIALOG_MOVIE);break;
		case R.id.report:onCreateDialog(MODE_DIALOG_REPORT);break;
		}		
		
		return super.onOptionsItemSelected(item);
	}
	
	public int getdefaultMovie(){
    SharedPreferences settings1 = getSharedPreferences("One_Piece_Video_By_TomMy", 0);
    return settings1.getInt("movie_option", 0);
}
	public boolean setdefaultMovie(int arg1){
	    SharedPreferences settings1 = getSharedPreferences("One_Piece_Video_By_TomMy", 0);
	    SharedPreferences.Editor editor1 = settings1.edit();
	    editor1.putInt("movie_option", arg1);
	    editor1.commit();
	    return editor1.commit();
	}
	int MODE_DIALOG_MOVIE=0;
	int MODE_DIALOG_REPORT=1;	int MODE_DIALOG_MANGA=2;
	protected Dialog onCreateDialog(int id) {
		  final Dialog dialog;
		  switch(id) {
		   case 0:
		    dialog = new Dialog(this);
		    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		    dialog.setContentView(R.layout.moviemode);
		    dialog.setTitle("Movie Mode");	
		    RadioButton rbdefaultmodeonepiece=(RadioButton) dialog.findViewById(R.id.modeonepiece);
		    RadioButton rbdefaultmodemovie=(RadioButton) dialog.findViewById(R.id.modemovie);
		    RadioButton rbdefaultmodemanga=(RadioButton) dialog.findViewById(R.id.modemanga);
		    if(getdefaultMovie()==0){
		    
		    	rbdefaultmodeonepiece.setChecked(true);
		    }else if(getdefaultMovie()==1){
		    	
		    	rbdefaultmodemovie.setChecked(true);
		    }else if(getdefaultMovie()==2){
		    	
		    	rbdefaultmodemanga.setChecked(true);
		    }
		    final ImageView im_lang=(ImageView) findViewById(R.id.icon_img);
		    rbdefaultmodeonepiece.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
			        final CheckBox en=(CheckBox) findViewById(R.id.en);
			        final CheckBox es=(CheckBox) findViewById(R.id.es);
			        final CheckBox th=(CheckBox) findViewById(R.id.th);
			        makeloading(true);
						ArrayList<HashMap<String, String>> listdata = testmr("http://opvideosite.neezyl.com/data/dataen.xml");
					 adapter=new LazyAdapter(context, listdata);
					 list.setAdapter(adapter);
					 adapter.notifyDataSetChanged();
					im_lang.setImageResource(R.drawable.us);
					 en.setChecked(true);
					 es.setChecked(false);
					 th.setChecked(false);
							 setdefaultMovie(0);
							 TextView modestatus=(TextView) findViewById(R.id.modestatus);
								if(getdefaultMovie()==0){
									modestatus.setText("One Piece");
								}else if(getdefaultMovie()==1){
									modestatus.setText("The Movie");
								}else if(getdefaultMovie()==2){
									modestatus.setText("Manga");
								}
				}
			});
		    rbdefaultmodemovie.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
			        final CheckBox en=(CheckBox) findViewById(R.id.en);
			        final CheckBox es=(CheckBox) findViewById(R.id.es);
			        final CheckBox th=(CheckBox) findViewById(R.id.th);
			        makeloading(true);
						ArrayList<HashMap<String, String>> listdata = testmr("http://opvideosite.neezyl.com/data/mven.xml");
					 adapter=new LazyAdapter(context, listdata);
					 list.setAdapter(adapter);
					 adapter.notifyDataSetChanged();
					 im_lang.setImageResource(R.drawable.us);
					 en.setChecked(true);
					 es.setChecked(false);
					 th.setChecked(false);
							 setdefaultMovie(1);
							 TextView modestatus=(TextView) findViewById(R.id.modestatus);
								if(getdefaultMovie()==0){
									modestatus.setText("One Piece");
								}else if(getdefaultMovie()==1){
									modestatus.setText("The Movie");
								}else if(getdefaultMovie()==2){
									modestatus.setText("Manga");
								}
				}
			});
		    rbdefaultmodemanga.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
			        final CheckBox en=(CheckBox) findViewById(R.id.en);
			        final CheckBox es=(CheckBox) findViewById(R.id.es);
			        final CheckBox th=(CheckBox) findViewById(R.id.th);
			        makeloading(true);
						ArrayList<HashMap<String, String>> listdata = testmr("http://opvideosite.neezyl.com/data/manga/mangaen.xml");
					 adapter=new LazyAdapter(context, listdata);
					 list.setAdapter(adapter);
					 adapter.notifyDataSetChanged();
					 im_lang.setImageResource(R.drawable.us);
					 en.setChecked(true);
					 es.setChecked(false);
					 th.setChecked(false);
							 setdefaultMovie(2);
							 TextView modestatus=(TextView) findViewById(R.id.modestatus);
								if(getdefaultMovie()==0){
									modestatus.setText("One Piece");
								}else if(getdefaultMovie()==1){
									modestatus.setText("The Movie");
								}else if(getdefaultMovie()==2){
									modestatus.setText("Manga");
								}
				}
			});
		    
		    CheckBox btclose=(CheckBox) dialog.findViewById(R.id.closedialog);
		    btclose.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					dialog.dismiss();
					
				}
			});
		    dialog.show();
		    break;
		    
		   case 1: dialog = new Dialog(this);
		    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		    dialog.setContentView(R.layout.report);
		    dialog.setTitle("Report");	
		    ImageView bt_img_report=(ImageView) dialog.findViewById(R.id.bt_img_report);
		    final EditText title=(EditText) dialog.findViewById(R.id.reporttitle);
		    final EditText message=(EditText) dialog.findViewById(R.id.reportmess);

		    bt_img_report.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					if(!title.getText().toString().matches("")&&!message.getText().toString().matches("")){
						
						TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
					
					HttpClient httpclient = new DefaultHttpClient();
				    HttpPost httppost = new HttpPost("http://opvideosite.neezyl.com/data/report/report.php");

				    //This is the data to send
				  

				    try {
				        // Add your data
				        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
					   
				        nameValuePairs.add(new BasicNameValuePair("imei",  telephonyManager.getDeviceId()+"|"+new Date().getTime()));
				        nameValuePairs.add(new BasicNameValuePair("title", title.getText().toString()));
				        nameValuePairs.add(new BasicNameValuePair("message", message.getText().toString()));
	
				        
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
					dialog.dismiss();
					Toast.makeText(context, "Thank you for report. We will fix this soon.", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(context, "Field rquest.", Toast.LENGTH_LONG).show();
				}}
				
			});
		    dialog.show();
		    break;
		    
		    
		default:
		    dialog = null;
		}
		return null;
		}
	
	
	public void goOffline(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				if(isOnline()){
								HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://opvideosite.neezyl.com/data/userOnline/offline.php");
			    try {
			    	TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
			        nameValuePairs.add(new BasicNameValuePair("imei",  telephonyManager.getDeviceId()));
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			        httppost.setHeader( "Cache-Control", "no-cache" );
			        ResponseHandler<String> responseHandler = new BasicResponseHandler();
			        String response = httpclient.execute(httppost, responseHandler);
			        Log.d("Log Off","Complete!");
			    } catch (ClientProtocolException e) {
			    } catch (IOException e) {
			    }
				}else{
					  AlertDialog.Builder alertbox = new AlertDialog.Builder(context);
			            alertbox.setMessage("Please check your connection!");
			            alertbox.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface arg0, int arg1) {
			                   finish();
			                }
			            });
			            alertbox.show();
				}
	
				
			}
		}).start();
		
	}

	public void goOnline(){
		String response ="";
		if(isOnline()){
		HttpClient httpclient = new DefaultHttpClient();  
	    HttpPost httppost = new HttpPost("http://opvideosite.neezyl.com/data/userOnline/online.php");
	    try {
	    	TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
	        nameValuePairs.add(new BasicNameValuePair("imei",  telephonyManager.getDeviceId()));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        httppost.setHeader( "Cache-Control", "no-cache" );
	        ResponseHandler<String> responseHandler = new BasicResponseHandler();
	         response = httpclient.execute(httppost, responseHandler);
	        TextView Onlinex=(TextView) findViewById(R.id.Online);
	        Onlinex.setText(response.split("<")[0]);
	    } catch (ClientProtocolException e) {
	    } catch (IOException e) {
	    }}else{                 
            AlertDialog.Builder alertbox = new AlertDialog.Builder(context);
            alertbox.setMessage("Please check your connection!");
            alertbox.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                   finish();
                }
            });
            alertbox.show();
		}
		
	
	    
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
	
	 public void makeloading(final boolean show){
		  final Handler handler = new Handler();
		    new Thread(new Runnable() {
		    	TextView loading = (TextView) findViewById(R.id.loading);
		        @Override
		        public void run() {
		          
		        	if(show){
		            handler.post(new Runnable() {
		            	
		                @Override
		                    public void run() {

		                
		                	
		                    loading.setVisibility(View.VISIBLE);
		                    TranslateAnimation slide = new TranslateAnimation(0, 0, 120,0 );   
		                    slide.setDuration(1000);   
		                    slide.setFillAfter(true);   
		                    loading.startAnimation(slide); 
		                	
		                }});}else{
		                	
				            handler.postDelayed(new Runnable() {
				                @Override
				                    public void run() {

				                
				                	//loading.setVisibility(View.VISIBLE);
			                		//loading.setVisibility(View.INVISIBLE);
				                    TranslateAnimation slide1 = new TranslateAnimation(0, 0, 0,100 );   
				                    slide1.setDuration(2000);   
				                    slide1.setFillBefore(true);   
				                    loading.startAnimation(slide1); 
				                    slide1.setAnimationListener(new AnimationListener() {
										
										@Override
										public void onAnimationStart(Animation animation) {
											loading.setVisibility(View.VISIBLE);
											
										}
										
										@Override
										public void onAnimationRepeat(Animation animation) {
											// TODO Auto-generated method stub
											
										}
										
										@Override
										public void onAnimationEnd(Animation animation) {
											loading.setVisibility(View.GONE);
											
										}
									});
				                	
				                }},1000);
				            
				            
		                }
		            }
		        }).start();
	 }

	  public ArrayList<HashMap<String, String>>  testmr(String url){
		  

		  
		 
		  ArrayList<HashMap<String, String>> datas = new  ArrayList<HashMap<String, String>>() ;
		    ExecutorService es = Executors.newFixedThreadPool(1);
		    DownloadXml d = new DownloadXml(url);
		    es.submit(new Runnable() {
				
				@Override
				public void run() {
					makeloading(true);
					
					
				}
			});
		    Future<ArrayList<HashMap<String, String>>> f2 = es.submit(d);
		    es.shutdown();
		    		while(true){
		    			if(f2.isDone()){
		    				try {
							datas=f2.get();
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
		    				
		    			  
		    			  makeloading(false);
		  		          break;
		    			}
		    			
		    			
		    			
		    		}
			return datas;

		}
	
		class DownloadXml implements Callable<ArrayList<HashMap<String, String>>> {

			String url="";
			DownloadXml(String string) {
				url=string;
		  }
		
	
		public ArrayList<HashMap<String, String>> call() {
		
			ArrayList<String> todoItems = new ArrayList<String>();
			ArrayList<HashMap<String, String>> todoItemsmap = new ArrayList<HashMap<String, String>>();
			XmlPullParser todolistXml = null;
			try {
				todolistXml = XmlPullParserFactory.newInstance().newPullParser();
				try {
					URL URL	=new URL(url);
					URLConnection ucon = URL.openConnection();
					ucon.setUseCaches(false);
					ucon.setRequestProperty("Cache-Control", "no-cache");
					todolistXml.setInput(ucon.getInputStream(),null);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (XmlPullParserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			//XmlResourceParser todolistXml = getResources().getXml(R.xml.data);

			int eventType = -1;
			while (eventType != XmlResourceParser.END_DOCUMENT) {
				if (eventType == XmlResourceParser.START_TAG) {

					String strNode = todolistXml.getName();
					if (strNode.equals("url")) {
						HashMap<String, String> map=new HashMap<String, String>();
						map.put("title", todolistXml.getAttributeValue(null, "title"));
					//	Log.e("",todolistXml.getAttributeValue(null, "title"));
						map.put("data", todolistXml.getAttributeValue(null, "data"));
						map.put("size", todolistXml.getAttributeValue(null, "size"));
						map.put("thumbnail", todolistXml.getAttributeValue(null, "thumbnail"));
						todoItemsmap.add(map);
						todoItems.add(todolistXml.getAttributeValue(null, "title"));
					}
				}

				try {
					eventType = todolistXml.next();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return todoItemsmap;
		  }
		}
		   public boolean isPackageExists(){
		        List<ApplicationInfo> packages;
		        PackageManager pm;
		            pm = getPackageManager();        
		            packages = pm.getInstalledApplications(0);
		            for (ApplicationInfo packageInfo : packages) {
		        if(packageInfo.packageName.equals("com.mxtech.videoplayer.ad")) return true;
		        }        
		        return false;
		    }
		
}




