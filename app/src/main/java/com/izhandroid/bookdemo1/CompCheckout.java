package com.izhandroid.bookdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CompCheckout extends AppCompatActivity {
    String typec, brandc, modelc, accesoryc, totastr, maill, nammo, nxo, aa, cc, ss, pp, acs, orderidc;
    int quantityc, pricec;
   TextView txtheadc, txtqc, dsc, prc, totc, btnc;
    EditText namec, adstrec, adcityc, adstatc, pinc, noc, emailc;
    RequestQueue queuec;
    AlertDialog progressDialog;
    private SharedPreferences mPrefe;
    String aaa;
    String bbb;
    String ccc;
    String ddd;
    String eee;
    String ggg;
    String fff;
    View view;
    private static final String P_NN = "txt_util";
     String quan;
     String pric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_checkout);
        txtheadc = findViewById(R.id.headerc);
        txtqc = findViewById(R.id.quaheadc);
        dsc = findViewById(R.id.desco);
        prc = findViewById(R.id.descomprice);
        totc = findViewById(R.id.finaltotalco);
        btnc = findViewById(R.id.bookc);

        namec = findViewById(R.id.nameco);
        adstrec = findViewById(R.id.addrco);
        adcityc = findViewById(R.id.cityco);
        adstatc = findViewById(R.id.stateco);
        pinc = findViewById(R.id.pinco);
        noc = findViewById(R.id.numbrco);
        emailc = findViewById(R.id.emailco);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        queuec = Volley.newRequestQueue(CompCheckout.this);
        progressDialog = new AlertDialog.Builder(this).create();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Hang on while we place your booking");
        progressDialog.setTitle("Please wait...");
        progressDialog.setIcon(R.drawable.ic_access_time_black_24dp);
        mPrefe = getSharedPreferences(P_NN, MODE_PRIVATE);

        String asa = mPrefe.getString("a", "");
        namec.setText(asa);
        String bb = mPrefe.getString("b", "");
        adstrec.setText(bb);
        String csc = mPrefe.getString("c", "");
        adcityc.setText(csc);
        String dd = mPrefe.getString("d", "");
        adstatc.setText(dd);
        String ee = mPrefe.getString("e", "");
        pinc.setText(ee);
        String ff = mPrefe.getString("f", "");
        noc.setText(ff);
        String gg = mPrefe.getString("g", "");
        emailc.setText(gg);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
if(getIntent()!=null ){
        typec = getIntent().getExtras().getString("ty");
        brandc = getIntent().getExtras().getString("br");
        modelc =getIntent().getExtras().getString("mo");
        quantityc=getIntent().getExtras().getInt("qu");
        accesoryc=getIntent().getExtras().getString("ac");
        pricec = getIntent().getExtras().getInt("pr");}



        quan = String.valueOf(quantityc);

        txtheadc.setText(brandc + " " + modelc + " " + typec);

        txtqc.setText(quan);

        dsc.setText(brandc + " " + modelc + " x " + quantityc);
        pric = Integer.toString(pricec);
        prc.setText("$ " + pric);

       int finaltotal = pricec + 5;
        totastr = Integer.toString(finaltotal);
        totc.setText("$ " + totastr);
        maill = emailc.getText().toString();
        nammo = namec.getText().toString();
        nxo = noc.getText().toString();


        btnc.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                aa = adstrec.getText().toString();
                cc = adcityc.getText().toString();
                ss = adstatc.getText().toString();
                pp = pinc.getText().toString();


                acs = aa + " " + cc + " " + ss + " - " + pp;
                generateId();
                verifyFields();
                if (namec.getError() != null && adstrec.getError() != null && adcityc.getError() != null && adstatc.getError() != null || noc.getError() != null && pinc.getError() != null && emailc.getError() != null) {
                    verifyFields();

                } else {
                    generateId();
                    verifyFields();


                    postData(orderidc, modelc, quan, namec.getText().toString(), acs, typec, brandc, noc.getText().toString(), emailc.getText().toString(), accesoryc );



                }


            }


        });


    }

    public void postData(final String forderid, final String fmodel, final String fquant, final String fname, final String faddr, final String ftype, final String fbrand, final String fphone, final String femail, final String face) {


        progressDialog.show();

        StringRequest request = new StringRequest(
                Request.Method.POST,
                Constants.urlc,
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
                params.put(Constants.corderfield, forderid);
                params.put(Constants.cnamefield, fname);
                params.put(Constants.caddresfield, faddr);
                params.put(Constants.cnofield, fphone);
                params.put(Constants.cmailfield, femail);
                params.put(Constants.ctypefield, ftype);
                params.put(Constants.cbrandfield, fbrand);
                params.put(Constants.cmodelfield, fmodel);
                params.put(Constants.cquanfield, fquant);
                params.put(Constants.cacce, face);

                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queuec.add(request);
    }


    private void add() {
        String currentTime = Calendar.getInstance().getTime().toString();

        String item = brandc + " " + modelc + " " + typec;
        String price = "$" + totastr;
        String quantity = quan+" units";
        String nname = namec.getText().toString();
        String address = acs;
        String mail = emailc.getText().toString();
        String orderid = orderidc;
        String time = currentTime;


        SQLiteAdaptor dbHandler = new SQLiteAdaptor(CompCheckout.this);
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
                Intent intent = new Intent(CompCheckout.this, MainActivity.class);
                startActivity(intent);
            }
        });
        fullscreenDialog.show();

    }

    private void verifyFields() {


        if (namec.getText().toString().length() == 0) {
            namec.setError("Name can't be empty");
        } else if (namec.getText().toString().length() <= 4) {
            // do async task
            namec.setError("Name is too short. Enter full name");

        } else {
            namec.setError(null);

        }

        if (adstrec.getText().toString().isEmpty())
            adstrec.setError("Address can't be empty");
        else if (adstrec.getText().toString().length() <= 4) {
            // do async task

            adstrec.setError("Too short address");
        } else {
            adstrec.setError(null);
        }

        if (adstatc.getText().toString().length() == 0)
            adstatc.setError("State name can't be empty");
        else {
            // do async task
            adstatc.setError(null);
        }
        if (adcityc.getText().toString().length() == 0)
            adcityc.setError("City can't be empty");
        else {
            // do async task
            adcityc.setError(null);
        }
        if (noc.getText().toString().length() <= 9)
            noc.setError("Invalid phone number");
        else if (noc.getText().toString().isEmpty()) {
            noc.setError("Phone Number can't be empty");
        } else {
            noc.setError(null);
        }
        if (pinc.getText().toString().length() <= 5)
            pinc.setError("Invalid Pincode");
        else {
            // do async task
            pinc.setError(null);
        }
        if (emailc.getText().toString().length() <= 4 && !android.util.Patterns.EMAIL_ADDRESS.matcher(emailc.getText().toString()).matches())
            emailc.setError("Invalid Email");
        else {
            // do async task
            emailc.setError(null);
        }


        SharedPreferences.Editor editor = mPrefe.edit();
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

        orderidc = year + rndm + daymont + hrmin + count + sec;


    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = mPrefe.edit();
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
        SharedPreferences.Editor editor = mPrefe.edit();
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

