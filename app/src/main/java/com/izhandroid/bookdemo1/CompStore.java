package com.izhandroid.bookdemo1;

import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;

public class CompStore extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView lenocvo, hp, acer, dell;
    TextView brandtype, total;
    Button proceed, add, sub;
    String type;
    String brand;
    Integer quantity;
    String edit;
    EditText q;
    boolean addaccesor;
    Boolean selected;
    String model, txtprice, accesory;
    Boolean blen, bhp, bacer, bdell;
    int price;
    int base;
    CheckBox accesor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Strings type- d/l  brand-l/h/a/d quantity- 1-50

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_store);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        blen = false;
        bhp = false;
        bacer = false;
        bdell = false;
        model = "ny";

price =0;
        quantity = 3;
        proceed = findViewById(R.id.material_buttonp);
        if(getIntent()!=null){
        type = getIntent().getExtras().getString("typ");}

        brandtype = findViewById(R.id.selctbrand);
        brandtype.setText("Choose your " + type + " Brand");

        q = findViewById(R.id.quantity_text_view);
        total = findViewById(R.id.ppricecomp);

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


        add = findViewById(R.id.inc);
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


        sub = findViewById(R.id.dec);

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


     accesor = findViewById(R.id.acce);

        accesor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (accesor.isChecked()) {
                    price = calculatePrice();

                    price = price + 6;

                    txtprice = "$" + Integer.toString(price);


                    total.setText(txtprice);

                    accesory="Yes";



                } else {
                    price = calculatePrice();

                    txtprice = "$" + Integer.toString(price);


                    total.setText(txtprice);
                    accesory= "No";
                }
            }
        });
//int totalPrice  = calculatePrice(addaccesor)


        selected = false;
        // Button button=(Button)findViewById(R.id.button);
        final Spinner spinner = findViewById(R.id.spinnercomp);

        spinner.setOnItemSelectedListener(this);
        spinner.setEnabled(false);

        // Spinner Drop down elements
        List<String> modelslen = new ArrayList<>();
        modelslen.add("G51");
        modelslen.add("G71");
        modelslen.add("G91");


        // Creating adapter for spinner
        final ArrayAdapter<String> datalen = new ArrayAdapter<>(this, R.layout.simple_spinner_item, modelslen);
        // Drop down layout style - list view with radio button
        datalen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        List<String> modelshp = new ArrayList<>();
        modelshp.add("Y20");
        modelshp.add("Y31");
        modelshp.add("Y51");
        modelshp.add("Y25");


        // Creating adapter for spinner
        final ArrayAdapter<String> datahp = new ArrayAdapter<>(this, R.layout.simple_spinner_item, modelshp);
        // Drop down layout style - list view with radio button
        datahp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> modelsacer = new ArrayList<>();
        modelsacer.add("E20");
        modelsacer.add("E5");
        modelsacer.add("E70");
        modelsacer.add("E90");


        // Creating adapter for spinner
        final ArrayAdapter<String> dataacer = new ArrayAdapter<>(this, R.layout.simple_spinner_item, modelsacer);
        // Drop down layout style - list view with radio button
        dataacer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> modelsdell = new ArrayList<>();
        modelsdell.add("P5");
        modelsdell.add("P9");
        modelsdell.add("P7");
        modelsdell.add("Pro");


        final ArrayAdapter<String> datadell = new ArrayAdapter<>(this, R.layout.simple_spinner_item, modelsdell);
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


        lenocvo = findViewById(R.id.len);
        lenocvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lenocvo.setImageDrawable(getResources().getDrawable(R.drawable.layerlen));
                brand = "Lenovo";
                hp.setImageDrawable(getResources().getDrawable(R.drawable.hp));
                acer.setImageDrawable(getResources().getDrawable(R.drawable.acer));
                dell.setImageDrawable(getResources().getDrawable(R.drawable.dell));
                spinner.setEnabled(true);
                spinner.setAdapter(datalen);
                price = calculatePrice();

                txtprice = "$" + Integer.toString(price);


                total.setText(txtprice);
            }
        });
        hp = findViewById(R.id.hp);
        hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hp.setImageDrawable(getResources().getDrawable(R.drawable.layerhp));
                brand = "HP";
                acer.setImageDrawable(getResources().getDrawable(R.drawable.acer));
                dell.setImageDrawable(getResources().getDrawable(R.drawable.dell));
                lenocvo.setImageDrawable(getResources().getDrawable(R.drawable.lenovo));

                spinner.setEnabled(true);
                spinner.setAdapter(datahp);
                price = calculatePrice();

                txtprice = "$" + Integer.toString(price);


                total.setText(txtprice);

            }
        });
        acer = findViewById(R.id.acer);
        acer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acer.setImageDrawable(getResources().getDrawable(R.drawable.layeracer));
                brand = "Acer";
                hp.setImageDrawable(getResources().getDrawable(R.drawable.hp));
                dell.setImageDrawable(getResources().getDrawable(R.drawable.dell));

                spinner.setEnabled(true);
                spinner.setAdapter(dataacer);
                price = calculatePrice();

                txtprice = "$" + Integer.toString(price);


                total.setText(txtprice);


            }
        });
        dell = findViewById(R.id.dell);
        dell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dell.setImageDrawable(getResources().getDrawable(R.drawable.layerdel));
                brand = "Dell";
                hp.setImageDrawable(getResources().getDrawable(R.drawable.hp));
                acer.setImageDrawable(getResources().getDrawable(R.drawable.acer));
                lenocvo.setImageDrawable(getResources().getDrawable(R.drawable.lenovo));

                spinner.setEnabled(true);
                spinner.setAdapter(datadell);
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

        if (quantity < 3 ) {
            Snackbar snc = Snackbar.make(v, "Quantity is too less. Please add atleast 3", Snackbar.LENGTH_LONG);
            snc.getView().setBackgroundColor(getResources().getColor(R.color.red_primary));
            snc.show();
        } else if (model.contains("Select")){

            Snackbar snc = Snackbar.make(v, "Please select brand", Snackbar.LENGTH_LONG);
            snc.getView().setBackgroundColor(getResources().getColor(R.color.red_primary));
            snc.show();

        } else {

            Intent checkot = new Intent(this, CompCheckout.class);
            checkot.putExtra("ty", type);
            checkot.putExtra("br", brand);
            checkot.putExtra("mo", model);
            checkot.putExtra("qu", quantity);
            checkot.putExtra("ac", accesory);
            checkot.putExtra("pr", price);
            startActivity(checkot);
        }
        if (accesor.isChecked()) {
            price = calculatePrice();

            price = price + 6;

            txtprice = "$" + Integer.toString(price);


            total.setText(txtprice);

            accesory="Yes";



        } else {
            price = calculatePrice();

            txtprice = "$" + Integer.toString(price);


            total.setText(txtprice);
            accesory= "No";
        }

    }

    private int calculatePrice() {




        if (model.contentEquals("Pro")) {

            base = 890;
        } else if (model.equals("P7")) {
            base = 720;
        } else if (model.equals("P9")) {
            base = 640;
        } else if (model.equals("P5")) {
            base = 510;
        } else if (model.equals("E90")) {
            base = 670;
        } else if (model.equals("E70")) {
            base = 600;
        } else if (model.equals("E5")) {
            base = 480;
        } else if (model.equals("E20")) {
            base = 390;
        } else if (model.equals("Y25")) {
            base = 870;
        } else if (model.equals("Y51")) {
            base = 580;
        } else if (model.equals("Y31")) {
            base = 450;
        } else if (model.equals("Y20")) {
            base = 370;
        } else if (model.equals("G91")) {
            base = 580;
        } else if (model.equals("G71")) {
            base = 451;
        } else if (model.contains("G51")) {
            base = 380;
        } else {
          base = 0;

        }


        return quantity * base;
    }
}

