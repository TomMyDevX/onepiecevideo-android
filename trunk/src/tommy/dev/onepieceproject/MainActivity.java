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
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.Loader;
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

	final 				MainActivity 		context=this;
	private  static  	ListView 			list;
	private				LazyAdapter 		adapter;
	private				String 				URLGOBAL="";
    

    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
       
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        
        isAvailable();
   
        String urlxml="";
 
        final 		TextView modestatus=(TextView) 		findViewById(R.id.modestatus);
        final 		CheckBox en			=(CheckBox) 	findViewById(R.id.en);
        final 		CheckBox es			=(CheckBox) 	findViewById(R.id.es);
        final 		CheckBox th			=(CheckBox) 	findViewById(R.id.th);
        final 		CheckBox ge			=(CheckBox) 	findViewById(R.id.ge);
        final 		ImageView im_lang	=(ImageView) 	findViewById(R.id.icon_img);
        ImageView 	im					=(ImageView) 	findViewById(R.id.ImageView1);
        			list				=(ListView)		findViewById(R.id.list);
        
        			
        im.setImageResource(R.drawable.i7);
        
	    
	    
		
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

		 URLGOBAL=urlxml;
		 new Thread(LoderUrl).start();
       
        
     
        en.setOnClickListener(new OnClickListener() {
        	 
			
			@Override
			public void onClick(View arg0) {
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
				 URLGOBAL=urlxml;
				 new Thread(LoderUrl).start();
				im_lang.setImageResource(R.drawable.us);

				
			}
		});
        ge.setOnClickListener(new OnClickListener() {
        	 
			
			@Override
			public void onClick(View arg0) {
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
				 URLGOBAL=urlxml;
				 new Thread(LoderUrl).start();
				im_lang.setImageResource(R.drawable.ge);
					
				//}
				
			}
		});
        
        th.setOnClickListener(new OnClickListener() {
        	 
			
			@Override
			public void onClick(View arg0) {
	
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
				 URLGOBAL=urlxml;
				 new Thread(LoderUrl).start();
				im_lang.setImageResource(R.drawable.flag_th2);

			}
		});
        es.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
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
				 URLGOBAL=urlxml;
				 new Thread(LoderUrl).start();
				 im_lang.setImageResource(R.drawable.flag_sp);
	
				//}
				
			}
		});

      final CheckBox cb_menu=(CheckBox) findViewById(R.id.bt_img_menu);
		 registerForContextMenu(cb_menu);
         cb_menu.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
		//Log.e("","setting click");
		 openContextMenu(v);
			
		}
	});
    }
    
    //////////////////////////////////////////////////////////DCTECT HOME AND BACK PREASSS
    @Override
    public void onBackPressed() {
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
	@Override
	 public boolean onContextItemSelected(MenuItem item) {
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

					
					 URLGOBAL="http://opvideosite.neezyl.com/data/dataen.xml";
					 new Thread(LoderUrl).start();
					im_lang.setImageResource(R.drawable.us);
							 setdefaultMovie(0);
							 TextView modestatus=(TextView) findViewById(R.id.modestatus);
								if(getdefaultMovie()==0){
									modestatus.setText("One Piece");
								}else if(getdefaultMovie()==1){
									modestatus.setText("The Movie");
								}else if(getdefaultMovie()==2){
									modestatus.setText("Manga");
								}
								dialog.dismiss();
								
				}
			});
		    rbdefaultmodemovie.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
			      
						 URLGOBAL="http://opvideosite.neezyl.com/data/mven.xml";
						 new Thread(LoderUrl).start();
					 im_lang.setImageResource(R.drawable.us);
							 setdefaultMovie(1);
							 TextView modestatus=(TextView) findViewById(R.id.modestatus);
								if(getdefaultMovie()==0){
									modestatus.setText("One Piece");
								}else if(getdefaultMovie()==1){
									modestatus.setText("The Movie");
								}else if(getdefaultMovie()==2){
									modestatus.setText("Manga");
								}
								dialog.dismiss();
				}
			});
		    rbdefaultmodemanga.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
			        makeloading(true);
					 URLGOBAL="http://opvideosite.neezyl.com/data/mangaen.xml";//////////////////////////will fix manga i edit path Realpath http://opvideosite.neezyl.com/data/manga/mangaen.xml
					 new Thread(LoderUrl).start();
					 im_lang.setImageResource(R.drawable.us);
							 setdefaultMovie(2);
							 TextView modestatus=(TextView) findViewById(R.id.modestatus);
								if(getdefaultMovie()==0){
									modestatus.setText("One Piece");
								}else if(getdefaultMovie()==1){
									modestatus.setText("The Movie");
								}else if(getdefaultMovie()==2){
									modestatus.setText("Manga");
								}
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
				
						
				
			
							
					new Thread(new Runnable() {
						Handler Handlerx=   new Handler(){
							public void handleMessage(Message msg) {
								if(msg.what==200){
									dialog.dismiss();
									Toast.makeText(context, "Thank you for report. We will fix this soon.", Toast.LENGTH_LONG).show();
					
								}else if(msg.what==500){
									Toast.makeText(context, "Field rquest.", Toast.LENGTH_LONG).show();
								}
							
							}
							};
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
				
					if(!title.getText().toString().matches("")&&!message.getText().toString().matches("")){
						
						TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
					
					HttpClient httpclient = new DefaultHttpClient();
				    HttpPost httppost = new HttpPost("http://opvideosite.neezyl.com/data/report/report.php");

		
				  

				    try {
				  
				        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
					   
				        nameValuePairs.add(new BasicNameValuePair("imei",  telephonyManager.getDeviceId()+"|"+new Date().getTime()));
				        nameValuePairs.add(new BasicNameValuePair("title", title.getText().toString()));
				        nameValuePairs.add(new BasicNameValuePair("message", message.getText().toString()));
				        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				        httppost.setHeader( "Cache-Control", "no-cache" );
				        ResponseHandler<String> responseHandler = new BasicResponseHandler();
				        String response = httpclient.execute(httppost, responseHandler);
			    } catch (ClientProtocolException e) {
				    isOnline.post(CONNECT_ERROR);
				    } catch (IOException e) {
				    isOnline.post(CONNECT_ERROR);
				    }
					Handlerx.post(new Runnable() {
						
						@Override
						public void run() {
							
							Message msg=new Message();
							msg.what=200;
						    Handlerx.sendMessage(msg);
							
						}
					});
				  
			}else{
				Handlerx.post(new Runnable() {
					
					@Override
					public void run() {
						Message msg=new Message();
						msg.what=500;
					    Handlerx.sendMessage(msg);
						
					}
				});
				}
				
						}
					}).start();

					
				}
			}); dialog.show();
		    break;
		    
		    
		default:
		    dialog = null;
		}
		return null;
		}
	
    public void  isAvailable() {
		Intent tostart = new Intent(Intent.ACTION_VIEW);
		tostart.setDataAndType(Uri.parse("1.mp4"), "video/*");
 	   final PackageManager mgr = context.getPackageManager();
 	   List<ResolveInfo> list =
 	      mgr.queryIntentActivities(tostart, 
 	         PackageManager.MATCH_DEFAULT_ONLY);
 	  if(list.size()<=0){
     
      	try {
      		context. startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.mxtech.videoplayer.ad")));
      	} catch (android.content.ActivityNotFoundException anfe) {
      		context. startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.mxtech.videoplayer.ad")));
      	}
      finish();
      }
     
 	  
 	 
 	
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
		                	
		                    TranslateAnimation slide1 = new TranslateAnimation(0, 1000, 0,0 );   
		                    slide1.setDuration(500);   
		                    slide1.setFillBefore(true);
		                	
		                   
		                    TranslateAnimation slide = new TranslateAnimation(1000, 0, 0,0 );   
		                    slide.setDuration(500);   
		                    
		                    loading.setText("Loading..");
		                    loading.startAnimation(slide); 
		                	loading.setVisibility(View.VISIBLE);
		                	list.setAnimation(slide1);
		                	list.setVisibility(View.INVISIBLE);
		                    
		                }});}else{
		                	
				            handler.post(new Runnable() {
				                @Override
				                    public void run() {
				                    TranslateAnimation slide1 = new TranslateAnimation(0,2000, 0,0 );   
				                    loading.setText("Complete!");
				                    slide1.setDuration(1000);   
				                    slide1.setFillBefore(true);   
				                    loading.startAnimation(slide1); 
				                    
				                    slide1.setAnimationListener(new AnimationListener() {
										
										@Override
										public void onAnimationStart(Animation animation) {
						                    TranslateAnimation slide = new TranslateAnimation(2000, 0, 0,0 );   
						                    slide.setDuration(1000);   
						                	list.setAnimation(slide);
						                	list.setVisibility(View.VISIBLE);
										}
										
										@Override
										public void onAnimationRepeat(Animation animation) {
									
											
										}
										
										@Override
										public void onAnimationEnd(Animation animation) {
											loading.setVisibility(View.GONE);

										}
									});
				                	
				                }});
				            
				            
		                }
		            }
		        }).start();
	 }

	  ArrayList<HashMap<String, String>> todoItemsmapGobal = new ArrayList<HashMap<String, String>>();
	  Handler urlloader=new Handler(){
		  @Override
		public void handleMessage(Message msg) {
			  	if(msg.what==200){
						  adapter=new LazyAdapter(context, todoItemsmapGobal);
						  list.setAdapter(adapter);
			  			  adapter.notifyDataSetChanged();
			  	}
			  	if(msg.what==0){
			  			makeloading(true);
			  		}
			  	if(msg.what==1){
			  		makeloading(false);
			  	}
	  }};
	  Runnable LoderUrl=new Runnable() {
		
		@Override
		public void run() {
			urlloader.post(new Runnable() {
				
				@Override
				public void run() {
					Message msg=new Message();
					msg.what=0;
				    urlloader.sendMessage(msg);
					
				}
			});
	        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL("http://www.google.com");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(5000);
                    urlc.connect();
            if (urlc.getResponseCode() == 200) {
            	urlc.disconnect();
    	       isOnline.post(CONNECT_COMPLETE);
          
			final ArrayList<HashMap<String, String>> todoItemsmap = new ArrayList<HashMap<String, String>>();
			XmlPullParser todolistXml = null;
			try {
				todolistXml = XmlPullParserFactory.newInstance().newPullParser();
				try {
					URL URL	=new URL(URLGOBAL);
					URLConnection ucon = URL.openConnection();
					ucon.setUseCaches(false);
					ucon.setRequestProperty("Cache-Control", "no-cache");
					todolistXml.setInput(ucon.getInputStream(),null);
				
				} catch (MalformedURLException e) {
					isOnline.post(CONNECT_ERROR);
				} catch (IOException e) {
					isOnline.post(CONNECT_ERROR);
				}
			} catch (XmlPullParserException e1) {
				isOnline.post(CONNECT_ERROR);
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
					
					}
				}

				try {
					eventType = todolistXml.next();
				} catch (XmlPullParserException e) {
					isOnline.post(CONNECT_ERROR);
				} catch (IOException e) {
					isOnline.post(CONNECT_ERROR);
				}
				
			}
			urlloader.post(new Runnable() {
				
				@Override
				public void run() {
					todoItemsmapGobal=todoItemsmap;
					Message msg=new Message();
					msg.what=200;
				    urlloader.sendMessage(msg);
					
				}
			});
			urlloader.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					todoItemsmapGobal=todoItemsmap;
					Message msg=new Message();
					msg.what=1;
				    urlloader.sendMessage(msg);
					
				}
			},300); 
		
            }else{
	         	   isOnline.post(CONNECT_ERROR);
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
	  
	  Handler	isOnline=new Handler(){
			@Override
			public void handleMessage(Message msg) {
		
				if(msg.what==200){

				}else if(msg.what==404){

					AlertDialog.Builder alertbox = new AlertDialog.Builder(context);
					alertbox.setMessage("Please check your connection!");
					alertbox.setCancelable(false);
					alertbox.setNeutralButton("Retry", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							TextView loading = (TextView) findViewById(R.id.loading);
							loading.setText("No Connection.");
							//new Thread(checkOnline).start();
						}
	            });
//					alertbox.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface arg0, int arg1) {
//							finish();
//						}
//	            });
	            alertbox.show();
			
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



}




