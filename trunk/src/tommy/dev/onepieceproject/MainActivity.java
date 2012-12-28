package tommy.dev.onepieceproject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity {
    final MainActivity context=this;
  public  static  ListView list;
    LazyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
       
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.main);
        
       
        final CheckBox en=(CheckBox) findViewById(R.id.en);
        final CheckBox es=(CheckBox) findViewById(R.id.es);
        final CheckBox th=(CheckBox) findViewById(R.id.th);
        
        ImageView im=(ImageView) findViewById(R.id.ImageView1);
        int[] cards={R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6,R.drawable.i7,R.drawable.i8};
        Random r = new Random();
        int n=r.nextInt(8);
        im.setImageResource(cards[n]);
        
        
 
        
        ArrayList<HashMap<String, String>>  listdata  = PrepareListFromXml("http://opvideosite.neezyl.com/data/dataen.xml");
		en.setChecked(true);
		es.setChecked(false);
		th.setChecked(false);
		
        list=(ListView)findViewById(R.id.list);
        adapter=new LazyAdapter(this, listdata);
        list.setAdapter(adapter);
        list.setSelected(true);
      //  Button b=(Button)findViewById(R.id.button1);
       // b.setOnClickListener(listener);

       
        
     
        en.setOnClickListener(new OnClickListener() {
        	 
			
			@Override
			public void onClick(View arg0) {
				String urlxml="";
				if(mode==0){
					 urlxml="http://opvideosite.neezyl.com/data/dataen.xml";	
				}else if(mode==1){
					 urlxml="http://opvideosite.neezyl.com/data/mven.xml";	
				}
				ArrayList<HashMap<String, String>>  listdata  = PrepareListFromXml(urlxml);
				 adapter=new LazyAdapter(context, listdata);
				 list.setAdapter(adapter);
				
				adapter.notifyDataSetChanged();
				//if (((CheckBox) en).isChecked()) {
					
					en.setChecked(true);
					es.setChecked(false);th.setChecked(false);
				//}
				
			}
		});
        th.setOnClickListener(new OnClickListener() {
        	 
			
			@Override
			public void onClick(View arg0) {
			
				String urlxml="";
				if(mode==0){
					 urlxml="http://opvideosite.neezyl.com/data/datath.xml";	
				}else if(mode==1){
					 urlxml="http://opvideosite.neezyl.com/data/mvth.xml";	
				}
				
				ArrayList<HashMap<String, String>>  listdata  = PrepareListFromXml(urlxml);
				 adapter=new LazyAdapter(context, listdata);
				 list.setAdapter(adapter);
				
				adapter.notifyDataSetChanged();
				//if (((CheckBox) en).isChecked()) {
				
					th.setChecked(true);
					es.setChecked(false);en.setChecked(false);
				//}
				
			}
		});
        es.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				String urlxml="";
				if(mode==0){
					 urlxml="http://opvideosite.neezyl.com/data/dataes.xml";	
				}else if(mode==1){
					 urlxml="http://opvideosite.neezyl.com/data/mves.xml";	
				}
				
				ArrayList<HashMap<String, String>>  listdata  = PrepareListFromXml(urlxml);
				 adapter=new LazyAdapter(context, listdata);
				 list.setAdapter(adapter);
				//adapter.setData(listdata);
				 adapter.notifyDataSetChanged();
				//if (((CheckBox) es).isChecked()) {
					
					en.setChecked(false);
					es.setChecked(true);th.setChecked(false);
				//}
				
			}
		});
    }
    
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

	
	public ArrayList<HashMap<String, String>>  PrepareListFromXml(String url) {
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
					//String datasp[]=todolistXml.getAttributeValue(null, "data").split("[|]");
					//map.put("Size",""+datasp.length);
				//	map.put("data",datasp[0]);
					//map.put("1", todolistXml.getAttributeValue(null, datasp[i]));
					
					//map.put("data", todolistXml.getAttributeValue(null, "data"));
				//	for(int i=0;i<datasp.length;i++){
				//		Log.e(""+i,datasp[i]);
						map.put("data", todolistXml.getAttributeValue(null, "data"));
						map.put("thumbnail", todolistXml.getAttributeValue(null, "thumbnail"));
				//	}
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.menu, menu);
	return true;
	}
	int mode=0;
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
        final CheckBox en=(CheckBox) findViewById(R.id.en);
        final CheckBox es=(CheckBox) findViewById(R.id.es);
        final CheckBox th=(CheckBox) findViewById(R.id.th);
        ArrayList<HashMap<String, String>>  listdata ;
		switch (item.getItemId()) {
		case R.id.onepiecemode:mode=0;
		 listdata  = PrepareListFromXml("http://opvideosite.neezyl.com/data/dataen.xml");
		 adapter=new LazyAdapter(context, listdata);
		 list.setAdapter(adapter);
		 adapter.notifyDataSetChanged();
		 en.setChecked(true);
		 es.setChecked(false);
		 th.setChecked(false);
		 break;
		case R.id.onepiecethemovie:mode=1;
		 listdata  = PrepareListFromXml("http://opvideosite.neezyl.com/data/mven.xml");
		 adapter=new LazyAdapter(context, listdata);
		 list.setAdapter(adapter);
		 adapter.notifyDataSetChanged();
		 en.setChecked(true);
		 es.setChecked(false);
		 th.setChecked(false);
		 break;
		
	
		}		
		
		return super.onOptionsItemSelected(item);
	}
}