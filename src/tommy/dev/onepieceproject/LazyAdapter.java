package tommy.dev.onepieceproject;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private int data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    ArrayList<HashMap<String, String>> todoItemsmap = new ArrayList<HashMap<String, String>>();;
    public LazyAdapter(Activity a,ArrayList<HashMap<String, String>> list) {
    	todoItemsmap=list;
        activity = a;
        data=todoItemsmap.size();
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    

    public View getView(final int position, View convertView, final ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)vi = inflater.inflate(R.layout.item, null);
        if(position<todoItemsmap.size()){
 
      
       
  
        
        TextView text=(TextView)vi.findViewById(R.id.text);;
        text.setTextColor(Color.DKGRAY);
       // text.setBackgroundColor(0xFFB8E459);
        ImageView image=(ImageView)vi.findViewById(R.id.image);
        image.setImageResource(R.drawable.ficon);
        Log.e("POS",""+position);
        text.setText(todoItemsmap.get(position).get("title"));
        vi.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View arg0) {

				//layout.setBackgroundResource(R.drawable.app_background_d);
				//Log.e("POS",""+position+"|"+todoItemsmap.get(position).get("data"));
		        String filename =todoItemsmap.get(position).get("data");
		        String filenameArray[] = filename.split("\\.");
		        String extension = filenameArray[filenameArray.length-1];
		      //  Log.e("",filename);
				if(extension.equals("mp4")){
					Intent tostart = new Intent(Intent.ACTION_VIEW);
					tostart.setDataAndType(Uri.parse(filename), "video/*");
					activity.startActivity(tostart);
				}else{
					//vi.setBackgroundResource(R.drawable.app_background_d);
					//Intent intent = new Intent(context, UsingMyWebview.class);
					//intent.putExtra("url",todoItemsmap.get(aposition).get(""+position));
					//startActivity(intent);
				}
				
				
				}
		});

        vi.setPadding(15, 15, 15, 15);
       // Log.e("IMG URL","http://opvideosite.neezyl.com/thumnail/"+todoItemsmap.get(position).get("thumbnail")+".png");
        imageLoader.DisplayImage("http://opvideosite.neezyl.com/thumnail/"+todoItemsmap.get(position).get("thumbnail")+".png", image);
        }
        return vi;
    }

	public void setData(ArrayList<HashMap<String, String>> listdata) {
		todoItemsmap.clear();
		todoItemsmap=listdata;
		
	}

	public ArrayList<HashMap<String, String>> getTodoItemsmap() {
		
		return todoItemsmap;
	}


	
}