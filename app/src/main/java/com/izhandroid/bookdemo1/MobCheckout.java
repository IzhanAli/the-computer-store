package com.izhandroid.bookdemo1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MobCheckout extends AppCompatActivity {
    String typem, brandm, modelm, tmepd, lappa, totastr, aa, cc, ss, acs, orderidm;
    int quantitym, pricem, finaltotal;
    Button btn;
    EditText name, adstre, adcity, adstat, pin, no, email;
    TextView txthead, txtq, ds, pr, tot;
    Cursor cur;
    SQLiteAdaptor sqlite;
    String pp;
    RequestQueue queue;
    View view;
    AlertDialog progressDialog;
    String qun;
    String nxo;
    String maill, nammo;
    private SharedPreferences mPref;
     String aaa;
     String bbb;
     String ccc;
     String ddd;
     String eee;
     String ggg;
     String fff;
     private static final String P_N = "txt_utils";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mob_checkout);
        txthead = findViewById(R.id.headtxm);
        txtq = findViewById(R.id.quahead);
        ds = findViewById(R.id.desmob);
        pr = findViewById(R.id.desmobprice);
        tot = findViewById(R.id.finaltotalmob);
        btn = findViewById(R.id.bookm);

        name = findViewById(R.id.namemo);
        adstre = findViewById(R.id.addrmo);
        adcity = findViewById(R.id.citymo);
        adstat = findViewById(R.id.statemo);
        pin = findViewById(R.id.pinmo);
        no = findViewById(R.id.numbrmo);
        email = findViewById(R.id.emailmo);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        queue = Volley.newRequestQueue(MobCheckout.this);
        progressDialog = new AlertDialog.Builder(this).create();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Hang on while we finish your booking");
        progressDialog.setTitle("Please wait...");
        progressDialog.setIcon(R.drawable.ic_access_time_black_24dp);
        mPref = getSharedPreferences(P_N, MODE_PRIVATE);

        String asa = mPref.getString("a", "");
        name.setText(asa);
        String bb = mPref.getString("b", "");
        adstre.setText(bb);
        String csc = mPref.getString("c", "");
        adcity.setText(csc);
        String dd = mPref.getString("d", "");
        adstat.setText(dd);
        String ee = mPref.getString("e", "");
        pin.setText(ee);
        String ff = mPref.getString("f", "");
        no.setText(ff);
        String gg = mPref.getString("g", "");
        email.setText(gg);

        if (getIntent() != null) {
            typem = getIntent().getExtras().getString("typ");
            brandm = getIntent().getExtras().getString("bran");
            modelm = getIntent().getExtras().getString("mod");
            quantitym = getIntent().getExtras().getInt("qua");
            pricem = getIntent().getExtras().getInt("pri");
        }





        qun = String.valueOf(quantitym);

        txthead.setText(brandm + " " + modelm + " " + typem);
        tmepd = Integer.toString(quantitym);
        txtq.setText(tmepd);

        ds.setText(brandm + " " + modelm + "  x " + quantitym+"u");
        lappa = Integer.toString(pricem);
        pr.setText("$ " + lappa);

        finaltotal = pricem + 3;
        totastr = Integer.toString(finaltotal);
        tot.setText("$ " + totastr);
        maill = email.getText().toString();
        nammo = name.getText().toString();
        nxo = no.getText().toString();


        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                aa = adstre.getText().toString();
                cc = adcity.getText().toString();
                ss = adstat.getText().toString();
                pp = pin.getText().toString();

                acs = aa + " " + cc + " " + ss + " - " + pp;
                generateId();
                verifyFields();
                if (name.getError() != null && adstre.getError() != null && adcity.getError() != null && adstat.getError() != null || no.getError() != null && pin.getError() != null && email.getError() != null) {
                    verifyFields();

                } else {
                    generateId();
                    verifyFields();


                    postData(orderidm, modelm, qun, name.getText().toString(), acs, typem, brandm, no.getText().toString(), email.getText().toString());



                }


            }


        });


    }

    public void postData(final String forderid, final String fmodel, final String fquant, final String fname, final String faddr, final String ftype, final String fbrand, final String fphone, final String femail) {


        progressDialog.show();

        StringRequest request = new StringRequest(
                Request.Method.POST,
                Constants.urlm,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "Response: " + response);
                        if (response.length() > 0) {

                            add();

                        } else {
                            Snackbar.make(view, "An error occured. Try again later", Snackbar.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Snackbar.make(view, "Error while Posting Data", Snackbar.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Constants.morderfield, forderid);
                params.put(Constants.mnamefield, fname);
                params.put(Constants.maddresfield, faddr);
                params.put(Constants.mnofield, fphone);
                params.put(Constants.mmailfield, femail);
                params.put(Constants.mtypefield, ftype);
                params.put(Constants.mbrandfield, fbrand);
                params.put(Constants.mmodelfield, fmodel);
                params.put(Constants.mquanfield, fquant);

                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }


    private void add() {
        String currentTime = DateFormat.getDateTimeInstance()
                .format(new Date());

        String item = brandm + " " + modelm + " " + typem + "\n";
        String price = "$ " + totastr;
        String quantity = tmepd;
        String nname = name.getText().toString();
        String address = acs;
        String mail = email.getText().toString();
        String orderid = orderidm;
        String time = currentTime;


        SQLiteAdaptor dbHandler = new SQLiteAdaptor(MobCheckout.this);
        dbHandler.insertUserDetails(item, price, quantity, nname, address, mail, orderid, time);


        final Dialog fullscreenDialog = new Dialog(this, R.style.DialogFullscreen);
        fullscreenDialog.setContentView(R.layout.orderdonwe);
        ImageView img_full_screen_dialog = fullscreenDialog.findViewById(R.id.tick);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.d);
        img_full_screen_dialog.startAnimation(animation);

        ImageView img_dialog_fullscreen_close = fullscreenDialog.findViewById(R.id.img_n_closea);
        img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullscreenDialog.dismiss();
                finish();
                Intent intent = new Intent(MobCheckout.this, MainActivity.class);
                startActivity(intent);
            }
        });
        fullscreenDialog.show();

    }

    private void verifyFields() {


        if (name.getText().toString().length() == 0) {
            name.setError("Name can't be empty");
        } else if (name.getText().toString().length() <= 4) {
            // do async task
            name.setError("Name is too short. Enter full name");

        } else {
            name.setError(null);

        }

        if (adstre.getText().toString().isEmpty())
            adstre.setError("Address can't be empty");
        else if (adstre.getText().toString().length() <= 4) {
            // do async task

            adstre.setError("Too short address");
        } else {
            adstre.setError(null);
        }

        if (adstat.getText().toString().length() == 0)
            adstat.setError("State name can't be empty");
        else {
            // do async task
            adstat.setError(null);
        }
        if (adcity.getText().toString().length() == 0)
            adcity.setError("City can't be empty");
        else {
            // do async task
            adcity.setError(null);
        }
        if (no.getText().toString().length() <= 9)
            no.setError("Invalid phone number");
        else if (no.getText().toString().isEmpty()) {
            no.setError("Phone Number can't be empty");
        } else {
            no.setError(null);
        }
        if (pin.getText().toString().length() <= 5)
            pin.setError("Invalid Pincode");
        else {
            // do async task
            pin.setError(null);
        }
        if (email.getText().toString().length() <= 4 && !android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
            email.setError("Invalid Email");
        else {
            // do async task
            email.setError(null);
        }


        SharedPreferences.Editor editor = mPref.edit();
        editor.putString("a", nammo);
        editor.putString("b", aa);
        editor.putString("c", cc);
        editor.putString("d", ss);
        editor.putString("e",pp );
        editor.putString("f", nxo);
        editor.putString("g", maill);
        editor.apply();


    }
    /*boolean iEmpty(EditText t){
        CharSequence str = t.getText().toString();
        return (!TextUtils.isEmpty(str));
    }*/

    private void generateId() {

        Date yr = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
        String year = format1.format(yr);

        Date ddmm = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("ddMM");
        String daymont = format2.format(ddmm);

        Date hm = new Date();
        SimpleDateFormat format3 = new SimpleDateFormat("hhmm");
        String hrmin = format3.format(hm);

        Date ss = new Date();
        SimpleDateFormat format4 = new SimpleDateFormat("ss");
        String sec = format4.format(ss);

        String[] chars = {"A", "B", "C", "D", "E","F","G","H", "P", "Q", "R", "S", "T", "U", "V", "Z"};
        String rndm = chars[(int) (Math.random() * 10)];

        SharedPreferences s = getSharedPreferences("count", 0);
        int count = s.getInt("counts", 001);

        count++;
        final SharedPreferences.Editor editor = s.edit();
        editor.putInt("counts", count);
        editor.commit();

        orderidm = year + rndm + daymont + hrmin + count + sec;


    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString("a", aaa);
        editor.putString("b", bbb);
        editor.putString("c", ccc);
        editor.putString("d", ddd);
        editor.putString("e", eee);
        editor.putString("f", fff);
        editor.putString("g", ggg);
        editor.apply();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString("a", aaa);
        editor.putString("b", bbb);
        editor.putString("c", ccc);
        editor.putString("d", ddd);
        editor.putString("e", eee);
        editor.putString("f", fff);
        editor.putString("g", ggg);
        editor.apply();
        super.onPause();
    }


}
