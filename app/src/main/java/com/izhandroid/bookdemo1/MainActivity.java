package com.izhandroid.bookdemo1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;

import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;


import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private AccountHeader headerResult;
    private Drawer result;
    private Toolbar toolbar;
    Button btncomp;
    Button btnmob;
    ImageView imgban;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        new DrawerBuilder().withActivity(this).build();
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.get().load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.get().cancelRequest(imageView);
            }


        });

        imgban = findViewById(R.id.imageView);
        btncomp = findViewById(R.id.but1);
        btncomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompDia();
            }
        });
        btnmob = findViewById(R.id.but2);
        btnmob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MobDia();
            }
        });
        Picasso.get()
                .load("https://izhandroid.files.wordpress.com/2019/04/img_20190403_164911.jpg")
                .placeholder(R.drawable.ic_crop_original_grey_600_24dp)
                .error(R.drawable.ic_broken_image_grey_500_18dp)

                .into(imgban);


        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        final IProfile profile = new ProfileDrawerItem().withName("user@test.com").withIcon(R.drawable.ic_account_circle_grey_900_18dp).withIdentifier(100);
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(android.R.color.holo_orange_light)
                .addProfiles(
// Adding profiles to header view
                        profile,

                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
// Adding setting drawer item other than profile drawer item
                        //new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus).actionBar().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(android.R.drawable.ic_menu_edit).withIdentifier(100001)
                )

                .build();
//      result.setSelection(1);

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withItemAnimator(new AlphaCrossFadeAnimator())
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(R.drawable.ic_home_black_18dp).withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("The Computer Store").withIcon(R.drawable.ic_computer_black_18dp).withIdentifier(2).withSelectable(false),
                        new PrimaryDrawerItem().withName("The Mobile Store").withIcon(R.drawable.ic_phone_android_black_18dp).withIdentifier(3).withSelectable(false),
                        new PrimaryDrawerItem().withName("Order History").withIcon(R.drawable.ic_shopping_cart_black_18dp).withIdentifier(4).withSelectable(false),
                        new SectionDrawerItem().withName("Assistance"),
                        new PrimaryDrawerItem().withName("Email").withIcon(R.drawable.ic_email_black_18dp).withIdentifier(5).withSelectable(false),
                        new PrimaryDrawerItem().withName("WhatsApp").withDescription("24/7 Support").withIcon(R.drawable.ic_message_black_18dp).withIdentifier(7).withSelectable(false),
                        new PrimaryDrawerItem().withName("Call").withIcon(R.drawable.ic_call_black_18dp).withIdentifier(8).withSelectable(false),
                        new SectionDrawerItem().withName("Follow us"),
                        new PrimaryDrawerItem().withName("Rate us").withDescription("Support us by giving 5-star rating").withIcon(R.drawable.ic_star_black_18dp).withIdentifier(9).withSelectable(false),
                        new PrimaryDrawerItem().withName("Facebook").withIcon(R.drawable.web_18dp).withIdentifier(10).withSelectable(false),
                        new PrimaryDrawerItem().withName("Instagram").withIcon(R.drawable.web_18dp).withIdentifier(11).withSelectable(false),
                        new PrimaryDrawerItem().withName("Website").withIcon(R.drawable.web_18dp).withIdentifier(12).withSelectable(false))
                // new PrimaryDrawerItem().withName(R.string.drawer_item_collapsing_toolbar_drawer).withDescription(R.string.drawer_item_collapsing_toolbar_drawer_desc).withIcon(GoogleMaterial.Icon.gmd_camera_rear).withIdentifier(13).withSelectable(false),


                // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 2) {
                                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                                CompDia();
                            } else if (drawerItem.getIdentifier() == 3) {
                                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                                //intent = new Intent(MainActivity.this, MainActivity.class);
                                MobDia();
                            } else if (drawerItem.getIdentifier() == 4) {
                                intent = new Intent(MainActivity.this, OrderHistory.class);
                            } else if (drawerItem.getIdentifier() == 5) {
                                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                                //intent = new Intent(MainActivity.this, AdvancedActivity.class);
                                Intent i = new Intent(Intent.ACTION_SEND);


                                i.putExtra(Intent.EXTRA_SUBJECT,"Via Computer Store");

                                i.setDataAndType(Uri.parse("idappshelp@gmail.com"), "message/rfc822");
                                Intent chooser = Intent.createChooser(i, "Select Email App");

                                startActivity(chooser);
                            } else if (drawerItem.getIdentifier() == 7) {
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("https://wa.me/919948918505"));

                                startActivity(i);
                            } else if (drawerItem.getIdentifier() == 8) {
                                Intent i = new Intent(Intent.ACTION_CALL);
                                i.setData(Uri.parse("tel:9948918505"));
                                if(Build.VERSION.SDK_INT>22){
                                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        Toast.makeText(MainActivity.this, "Please Grant Permission", Toast.LENGTH_SHORT).show();
                                        reqest();
                                        //    Activity#requestPermissions
                                        // here to request the missing permissions, and then overriding
                                        //  public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                        //                                          int[] grantResults)
                                        // to handle the case where the user grants the permission. See the documentation
                                        // for Activity#requestPermissions for more details.

                                    }
                                }

                                startActivity(i);
                            } else if (drawerItem.getIdentifier() == 9) {
                                //intent = new Intent(MainActivity.this, CustomContainerActivity.class);
                                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                            } else if (drawerItem.getIdentifier() == 10) {
                                //intent = new Intent(MainActivity.this, MainActivity.class);
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("https://facebook.com/izhandroid"));

                                startActivity(i);
                                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 11) {
                                //intent = new Intent(MainActivity.this, MainActivity.class);
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("https://instagram.com/izhandroid"));

                                startActivity(i);
                                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 12) {
                                //intent = new Intent(MainActivity.this, Fra
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("https://izhandroid.blogspot.com"));

                                startActivity(i);
                                // gmnentActivity.class);
                                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT);
                            }

                            if (intent != null) {
                                MainActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();


        //if you have many different types of DrawerItems you can magically pre-cache those items to get a better scroll performance
        //make sure to init the cache after the DrawerBuilder was created as this will first clear the cache to make sure no old elements are in
        //RecyclerViewCacheUtil.getInstance().withCacheSize(2).init(result);
        // new RecyclerViewCacheUtil<IDrawerItem>().withCacheSize(2).apply(result.getRecyclerView(), result.getDrawerItems());

        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 11
            result.setSelection(21, false);

            //set the active profile
            headerResult.setActiveProfile(profile);
        }

        //result.updateBadge(4, new StringHolder(10 + ""));


    }

    private void CompDia() {
        final Dialog fullscreenDialoga = new Dialog(MainActivity.this, R.style.DialogFullscreen);
        fullscreenDialoga.setContentView(R.layout.comp_dialog);
        ImageView img_dialog_fullscreen_close = fullscreenDialoga.findViewById(R.id.img_dialog_fullscreen_closea);
        img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullscreenDialoga.dismiss();
            }
        });

        CardView cardd = fullscreenDialoga.findViewById(R.id.card_d);
        cardd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CompStore.class);
                i.putExtra("typ", "Desktop");
                startActivity(i);
            }
        });
        CardView cardl = fullscreenDialoga.findViewById(R.id.card_l);
        cardl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CompStore.class);
                i.putExtra("typ", "Laptop");
                startActivity(i);
            }
        });
        fullscreenDialoga.show();
    }

    private void MobDia() {

        final Dialog fullscreenDialogb = new Dialog(MainActivity.this, R.style.DialogFullscreen);
        fullscreenDialogb.setContentView(R.layout.mob_dia);
        ImageView img_dialog_fullscreen_close = fullscreenDialogb.findViewById(R.id.img_dialog_fullscreen_closeb);
        img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullscreenDialogb.dismiss();
            }
        });

        CardView cardd = fullscreenDialogb.findViewById(R.id.card_s);
        cardd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MobStore.class);
                i.putExtra("ty", "Phone");
                startActivity(i);
            }
        });
        CardView cardl = fullscreenDialogb.findViewById(R.id.card_t);
        cardl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MobStore.class);
                i.putExtra("ty", "Tablet");
                startActivity(i);
            }
        });
        fullscreenDialogb.show();


    }

    public void Rewards(View v) {

        final Dialog fullscreenDialogb = new Dialog(MainActivity.this, R.style.DialogFullscreen);
        fullscreenDialogb.setContentView(R.layout.rew);
        ImageView img_dialog_fullscreen_close = fullscreenDialogb.findViewById(R.id.img_dialog_fullscreen_closed);
        img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullscreenDialogb.dismiss();
            }
        });


        fullscreenDialogb.show();


    }
    private void reqest() {
        ActivityCompat.requestPermissions(MainActivity.this, new  String[]{Manifest.permission.CALL_PHONE},1);
    }
    public void Assist(View v) {

        final Dialog fullscreenDialogb = new Dialog(MainActivity.this, R.style.DialogFullscreen);
        fullscreenDialogb.setContentView(R.layout.assist);
        ImageView img_dialog_fullscreen_close = fullscreenDialogb.findViewById(R.id.img_dialog_fullscreen_closee);
        img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullscreenDialogb.dismiss();
            }
        });
        Button a = fullscreenDialogb.findViewById(R.id.call);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:9948918505"));
                if(Build.VERSION.SDK_INT>22){
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "Please Grant Permission", Toast.LENGTH_SHORT).show();
                        reqest();
                        //    Activity#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //  public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.

                    }else {
startActivity(i);
                }
                }else {
                    startActivity(i);
                }


    }


        });
        Button b = fullscreenDialogb.findViewById(R.id.suportmail);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);


                i.putExtra(Intent.EXTRA_SUBJECT,"Via Computer Store");

                i.setDataAndType(Uri.parse("idappshelp@gmail.com"), "message/rfc822");
                Intent chooser = Intent.createChooser(i, "Select Email App");

                startActivity(chooser);
            }
        });
        fullscreenDialogb.show();

        Button c = fullscreenDialogb.findViewById(R.id.wa);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://wa.me/919948918505"));

                startActivity(i);
            }
        });
    }

    public void OH(View v){
       Intent intent = new Intent(MainActivity.this, OrderHistory.class);
       startActivity(intent);
    }

}