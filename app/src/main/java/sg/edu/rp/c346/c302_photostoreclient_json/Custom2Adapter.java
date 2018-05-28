package sg.edu.rp.c346.c302_photostoreclient_json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom2Adapter extends ArrayAdapter{
    private ArrayList<Photo> customAdapterArrayList;
    private Context context;
    private TextView tvName, tvDesc;
    public Custom2Adapter(Context context, int resource, ArrayList<Photo> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        customAdapterArrayList = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvName =  rowView.findViewById(R.id.tvName);
        // Get the ImageView object
        tvDesc =  rowView.findViewById(R.id.tvDesc);


        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        Photo category = customAdapterArrayList.get(position);
        // Set the TextView to show the food

        tvName.setText(category.getTitle());
        // Set the image to star or nostar accordingly
        String result = category.getDesc()+"\n"+category.getImage()+"\nCreated by "+category.getCreated_by();
        tvDesc.setText(result);
        // Return the nicely done up View to the ListView
        return rowView;
    }
}
