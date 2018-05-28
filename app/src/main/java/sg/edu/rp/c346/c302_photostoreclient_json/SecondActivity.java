package sg.edu.rp.c346.c302_photostoreclient_json;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    Custom2Adapter aa;
    ArrayList<Photo> photoArrayList;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent i = getIntent();
        int id = i.getIntExtra("id",-1);
        listView = findViewById(R.id.listView);
        photoArrayList = new ArrayList<>();
        aa = new Custom2Adapter(this, R.layout.row, photoArrayList);
        listView.setAdapter(aa);
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C203_P06_PhotoStoreWS/getPhotoStoreByCategory.php?category_id="+id);
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
    }
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            int photo_id = jsonObj.getInt("photo_id");
                            String desc = jsonObj.getString("description");
                            String title = jsonObj.getString("title");
                            String img = jsonObj.getString("image");
                            String created = jsonObj.getString("created_by");
                            Photo set = new Photo(photo_id, title, desc,img,created);
                            photoArrayList.add(set);
                            //String displayResults = "Category Id: " + categoryId + "\n\nCategory Name: "
                            //+ categoryName + "\n\nDescription: " + description + "\n";
                            //alCategories.add(displayResults);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    aa.notifyDataSetChanged();
                }
            };
}
