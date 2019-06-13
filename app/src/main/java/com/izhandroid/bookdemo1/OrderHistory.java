package com.izhandroid.bookdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class OrderHistory extends AppCompatActivity {
Context c;
ImageView img;
TextView ordert, txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        ordert = findViewById(R.id.ordertv);
        img = findViewById(R.id.imgmt);

        SQLiteAdaptor db = new SQLiteAdaptor(this);
        final ArrayList<HashMap<String, String>> userList = db.GetUsers();

        final ListView lv = (ListView) findViewById(R.id.listorders);
        txt = findViewById(R.id.txtmt);

        ListAdapter adapter = new SimpleAdapter(OrderHistory.this, userList, R.layout.list_item,new String[]{"orderid","time","item","quantity"}, new int[]{R.id.ordertv, R.id.timetv, R.id.itemtv, R.id.quantitytv});
        lv.setAdapter(adapter);
if(userList.isEmpty()){
    txt.setVisibility(View.VISIBLE);
    img.setVisibility(View.VISIBLE);
}else {
    txt.setVisibility(View.GONE);
    img.setVisibility(View.GONE);
}

        lv.getOnItemClickListener();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


               // if(pos.equals("1") ) {


                final Dialog fullscreenDialog = new Dialog(OrderHistory.this, R.style.Dialog);
                    fullscreenDialog.setContentView(R.layout.dialog_oh);
                    TextView tvit = fullscreenDialog.findViewById(R.id.tvitem);
                    TextView tvpr = fullscreenDialog.findViewById(R.id.tvprice);
                    TextView tvqu = fullscreenDialog.findViewById(R.id.tvquantity);
                    TextView tvad = fullscreenDialog.findViewById(R.id.tvaddr);
                    TextView tvna = fullscreenDialog.findViewById(R.id.tvname);
                    TextView tvem = fullscreenDialog.findViewById(R.id.tvemail);
                    TextView tvtim = fullscreenDialog.findViewById(R.id.tvtime);
                    TextView tvid = fullscreenDialog.findViewById(R.id.tvid);





                SQLiteAdaptor db = new SQLiteAdaptor(view.getContext());
                ArrayList<HashMap<String, String>> userList = db.GetUserByUserId(i+1);
                HashMap<String, String> m = userList.get(0);
                String strArr[] = new String[m.size()];
                int k = 0;
                for (HashMap<String, String> hash : userList) {
                    for (String current : hash.values()) {
                        strArr[k] = current;
                        k++;
                    }
                }


String one = strArr[0];//mail
                String two = strArr[1];//addr
                String three = strArr[2];//name
                String four = strArr[3];//quantity

                String five = strArr[4];//pric
                String six = strArr[5];//date
                String seven = strArr[6];//item
                String orderid = strArr[7]; //id



                tvit.setText("Item: "+seven);
                tvpr.setText("Price: "+five);
                tvqu.setText("Quantity: "+four);
                tvad.setText("Address: "+two);
                tvna.setText("Name: "+three);
                tvem.setText("Email: "+one);
                tvid.setText("Order ID: "+orderid);
                tvtim.setText("Time: "+six);


                fullscreenDialog.show();

/* String k = hashDetails.get("name");
                Toast.makeText(OrderHistory.this, k, Toast.LENGTH_SHORT).show();*/
                 /*else {
                    x = Integer.parseInt(pos);
                    final Dialog fullscreenDialog = new Dialog(c);
                    fullscreenDialog.setContentView(R.layout.dialog_oh);
                    TextView tvit = findViewById(R.id.tvitem);
                    TextView tvpr = findViewById(R.id.tvprice);
                    TextView tvqu = findViewById(R.id.tvquantity);
                    TextView tvad = findViewById(R.id.tvaddr);
                    TextView tvna = findViewById(R.id.tvname);
                    TextView tvem = findViewById(R.id.tvemail);
                    TextView tvtim = findViewById(R.id.tvtime);

                    int idd = x + 1;

                    SQLiteAdaptor db = new SQLiteAdaptor(c);
                    ArrayList<HashMap<String, String>> userList = db.GetUserByUserId(idd);
                    HashMap<String, String> hashDetails = userList.get(0); // Use this index accordingly
                    tvit.setText(hashDetails.get("item"));
                    tvpr.setText(hashDetails.get("price"));
                    tvqu.setText(hashDetails.get("quantity"));
                    tvad.setText(hashDetails.get("address"));
                    tvna.setText(hashDetails.get("name"));
                    tvem.setText(hashDetails.get("email"));
                    tvtim.setText(hashDetails.get("time"));
                }*/
            }
        });


    }

}
