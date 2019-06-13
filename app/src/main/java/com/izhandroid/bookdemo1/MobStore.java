package com.izhandroid.bookdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MobStore extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView sam, mi, apple, asus, hua;
    TextView brandtype, total;
    Button proceed, add, sub;
    String type;
    String brand;
    Integer quantity;
    String edit;
    EditText q;

    Boolean selected;
    String model, txtprice;
    Boolean bsam, bmi, basus, bhua, bapple;
    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Strings type- d/l  brand-l/h/a/d quantity- 1-50

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mob);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        bsam = false;
        bmi = false;
        basus = false;
        bapple = false;
        bhua = false;
        model = "ny";
        price = 0;

        quantity = 5;
        proceed = findViewById(R.id.material_buttonpr);
        if(getIntent()!=null){
            if (Build.VERSION.SDK_INT > 18){
                type = Objects.requireNonNull(getIntent().getExtras()).getString("ty");
            }
      }

        brandtype = findViewById(R.id.selecctbrand);
        brandtype.setText("Choose your " + type + " Brand");

        q = findViewById(R.id.quantity_edittext);
        total = findViewById(R.id.pricemob);
q.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View view, boolean b) {
        price = calculatePrice();

        txtprice = "$" + Integer.toString(price);


        total.setText(txtprice);
    }
});

        price = calculatePrice();

        txtprice = "$" + Integer.toString(price);


        total.setText(txtprice);


        add = findViewById(R.id.incr);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (q != null) {

                    edit = q.getText().toString();
                    quantity = Integer.parseInt(edit);
                    quantity = quantity + 1;
                    edit = Integer.toString(quantity);
                    q.setText(edit);
                    price = calculatePrice();

                    txtprice = "$" + Integer.toString(price);


                    total.setText(txtprice);
                }
            }
        });


        sub = findViewById(R.id.decr);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (q != null) {
                    edit = q.getText().toString();
                    quantity = Integer.parseInt(edit);
                    quantity = quantity - 1;
                    edit = Integer.toString(quantity);
                    q.setText(edit);
                    price = calculatePrice();

                    txtprice = "$" + Integer.toString(price);


                    total.setText(txtprice);
                }

            }
        });



//int totalPrice  = calculatePrice(addaccesor)


        selected = false;
        // Button button=(Button)findViewById(R.id.button);
        final Spinner spinner = findViewById(R.id.spinnermob);

        spinner.setOnItemSelectedListener(this);
        spinner.setEnabled(false);

        // Spinner Drop down elements
        List<String> msam = new ArrayList<>();
        msam.add("GN9");
        msam.add("GS10");
        msam.add("GT4");


        // Creating adapter for spinner
        final ArrayAdapter<String> datalen = new ArrayAdapter<>(this, R.layout.simple_spinner_item, msam);
        // Drop down layout style - list view with radio button
        datalen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        List<String> mmi = new ArrayList<>();
        mmi.add("RN6P");
        mmi.add("RN7");
        mmi.add("R6A");
        mmi.add("RN7P");


        // Creating adapter for spinner
        final ArrayAdapter<String> datahp = new ArrayAdapter<>(this, R.layout.simple_spinner_item, mmi);
        // Drop down layout style - list view with radio button
        datahp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> mapp = new ArrayList<>();
        mapp.add("I8");
        mapp.add("I8P");
        mapp.add("IX");
        mapp.add("IXR");
        mapp.add("IXS");


        // Creating adapter for spinner
        final ArrayAdapter<String> dataacer = new ArrayAdapter<>(this, R.layout.simple_spinner_item, mapp);
        // Drop down layout style - list view with radio button
        dataacer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> masus = new ArrayList<>();
        masus.add("ZM1");
        masus.add("ZM2");
        masus.add("ZMP");
        masus.add("ZML");


        final ArrayAdapter<String> datadell = new ArrayAdapter<>(this, R.layout.simple_spinner_item, masus);
        // Drop down layout style - list view with radio button
        datadell.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        List<String> mhua = new ArrayList<>();
        mhua.add("HN9");
        mhua.add("H20");
        mhua.add("H7S");
        mhua.add("H8C");


        final ArrayAdapter<String> datahua = new ArrayAdapter<>(this, R.layout.simple_spinner_item, mhua);
        // Drop down layout style - list view with radio button
        datadell.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> defaul = new ArrayList<>();
        defaul.add("Select " + type + " brand first");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item, defaul);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        // dataAdapter.notifyDataSetChanged();


        sam = findViewById(R.id.sam);
        sam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sam.setImageDrawable(getResources().getDrawable(R.drawable.layersam));
                brand = "Samsung";
                mi.setImageDrawable(getResources().getDrawable(R.drawable.mi));
                apple.setImageDrawable(getResources().getDrawable(R.drawable.apple_logo));
                asus.setImageDrawable(getResources().getDrawable(R.drawable.asus_logo));
                hua.setImageDrawable(getResources().getDrawable(R.drawable.huawei));
                spinner.setEnabled(true);
                spinner.setAdapter(datalen);
                price = calculatePrice();

                txtprice = "$" + Integer.toString(price);


                total.setText(txtprice);
            }
        });
        mi = findViewById(R.id.mi);
        mi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mi.setImageDrawable(getResources().getDrawable(R.drawable.layermi));
                brand = "Xiaomi";
                apple.setImageDrawable(getResources().getDrawable(R.drawable.apple_logo));
                asus.setImageDrawable(getResources().getDrawable(R.drawable.asus_logo));
                sam.setImageDrawable(getResources().getDrawable(R.drawable.samsung));
                hua.setImageDrawable(getResources().getDrawable(R.drawable.huawei));

                spinner.setEnabled(true);
                spinner.setAdapter(datahp);
                price = calculatePrice();

                txtprice = "$" + Integer.toString(price);


                total.setText(txtprice);

            }
        });
        apple = findViewById(R.id.apple);
        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apple.setImageDrawable(getResources().getDrawable(R.drawable.layerapple));
                brand = "Apple";
                mi.setImageDrawable(getResources().getDrawable(R.drawable.mi));
                asus.setImageDrawable(getResources().getDrawable(R.drawable.asus_logo));
                hua.setImageDrawable(getResources().getDrawable(R.drawable.huawei));
                sam.setImageDrawable(getResources().getDrawable(R.drawable.samsung));


                spinner.setEnabled(true);
                spinner.setAdapter(dataacer);
                price = calculatePrice();

                txtprice = "$" + Integer.toString(price);


                total.setText(txtprice);

                Toast.makeText(MobStore.this, "", Toast.LENGTH_SHORT).show();
            }
        });
        asus = findViewById(R.id.asus);
        asus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asus.setImageDrawable(getResources().getDrawable(R.drawable.layerasus));
                brand = "Asus";
                mi.setImageDrawable(getResources().getDrawable(R.drawable.mi));
                hua.setImageDrawable(getResources().getDrawable(R.drawable.huawei));
                apple.setImageDrawable(getResources().getDrawable(R.drawable.apple_logo));
                sam.setImageDrawable(getResources().getDrawable(R.drawable.samsung));

                spinner.setEnabled(true);
                spinner.setAdapter(datadell);
                price = calculatePrice();

                txtprice = "$" + Integer.toString(price);


                total.setText(txtprice);
            }
        });
        hua = findViewById(R.id.hua);
        hua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hua.setImageDrawable(getResources().getDrawable(R.drawable.layerhua));
                brand = "Huawei";
                mi.setImageDrawable(getResources().getDrawable(R.drawable.mi));
                asus.setImageDrawable(getResources().getDrawable(R.drawable.asus_logo));
                apple.setImageDrawable(getResources().getDrawable(R.drawable.apple_logo));
                sam.setImageDrawable(getResources().getDrawable(R.drawable.samsung));

                spinner.setEnabled(true);
                spinner.setAdapter(datahua);
                price = calculatePrice();

                txtprice = "$" + Integer.toString(price);


                total.setText(txtprice);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        model = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + model, Toast.LENGTH_LONG);

        price = calculatePrice();

        txtprice = "$" + Integer.toString(price);


        total.setText(txtprice);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void checkout(View v) {
        price = calculatePrice();

        txtprice = "$" + Integer.toString(price);


        total.setText(txtprice);
        if (quantity < 5 || q.getText().toString().isEmpty() || q.length()<1 ) {
            Snackbar snc = Snackbar.make(v, "Quantity is too less. Please add atleast 5", Snackbar.LENGTH_LONG);
            snc.getView().setBackgroundColor(getResources().getColor(R.color.red_primary));
            snc.show();
        } else if (model.contains("Select")){

            Snackbar snc = Snackbar.make(v, "Please select brand", Snackbar.LENGTH_LONG);
            snc.getView().setBackgroundColor(getResources().getColor(R.color.red_primary));
            snc.show();

        } else {

            Intent checkot = new Intent(this, MobCheckout.class);
            checkot.putExtra("typ", type);
            checkot.putExtra("bran", brand);
            checkot.putExtra("mod", model);
            checkot.putExtra("qua", quantity);

            checkot.putExtra("pri", price);
            startActivity(checkot);
        }


    }

    private int calculatePrice() {

        int base;


        if (model.contentEquals("ZML")) {

            base = 290;
        } else if (model.equals("ZMP")) {
            base = 200;
        } else if (model.equals("ZM2")) {
            base = 270;
        } else if (model.equals("ZM1")) {
            base = 210;
        } else if (model.equals("IXR")) {
            base = 1250;
        } else if (model.equals("IX")) {
            base = 1400;
        } else if (model.equals("I8P")) {
            base = 950;
        } else if (model.equals("I8")) {
            base = 870;
        } else if (model.equals("IXS")) {
            base = 1500;
        } else if (model.equals("RN6P")) {
            base = 200;
        } else if (model.equals("RN7")) {
            base = 230;
        } else if (model.equals("R6A")) {
            base = 180;
        } else if (model.equals("RN7P")) {
            base = 250;
        } else if (model.equals("GN9")) {
            base = 580;
        } else if (model.equals("GS10")) {
            base = 451;
        } else if (model.contains("GT4")) {
            base = 380;


        } else if (model.contains("HN9")) {
            base = 309;
        } else if (model.contains("H20")) {
            base = 330;
        } else if (model.contains("H7S")) {
            base = 390;
        } else if (model.contains("H8C")) {
            base = 420;
        } else {
            base = 0;

        }

        return quantity * base;
    }
}