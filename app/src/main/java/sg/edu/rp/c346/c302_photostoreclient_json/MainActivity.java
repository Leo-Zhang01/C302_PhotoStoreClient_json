package sg.edu.rp.c346.c302_photostoreclient_json;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvCategories;
    //ArrayList<String> alCategories = new ArrayList<String>();
    ArrayAdapter<String> aaCategories;
    ArrayList<Category> categoryArrayList;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void onResume() {
        super.onResume();
        categoryArrayList = new ArrayList<>();
        lvCategories = (ListView) findViewById(R.id.listViewCategories);
        //aaCategories = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alCategories);
        customAdapter = new CustomAdapter(this, R.layout.row, categoryArrayList);
        lvCategories.setAdapter(customAdapter);
        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category current = categoryArrayList.get(position);

                int currentId = current.getId();
                Intent i = new Intent(getBaseContext(), SecondActivity.class);
                i.putExtra("id",currentId);
                startActivity(i);
                }
        });
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C203_P06_PhotoStoreWS/getCategories.php");
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

                            int categoryId = jsonObj.getInt("category_id");
                            String categoryName = jsonObj.getString("name");
                            String description = jsonObj.getString("description");
                            Category set = new Category(categoryId, categoryName, description);
                            categoryArrayList.add(set);
                            //String displayResults = "Category Id: " + categoryId + "\n\nCategory Name: "
                                    //+ categoryName + "\n\nDescription: " + description + "\n";
                            //alCategories.add(displayResults);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    customAdapter.notifyDataSetChanged();
                }
            };

}
