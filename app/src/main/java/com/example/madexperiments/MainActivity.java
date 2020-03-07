package com.example.madexperiments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.madexperiments.ui.experiment_5.ExperimentFive;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static androidx.core.content.ContextCompat.getSystemService;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    float font = 20;
    int count=1;
    String name, gender, dept, college, mail;
    private AppBarConfiguration mAppBarConfiguration;
    AppLocationService appLocationService;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_exp1, R.id.nav_exp2, R.id.nav_exp3,
                R.id.nav_exp4, R.id.nav_exp5, R.id.nav_exp6, R.id.nav_exp7)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //db.openOrCreateDatabase("StudentDB",null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS Student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");
        if(checkPermissions()){
            if(!isLocationEnabled()){
                showSettingsAlert();
            }
            else{
                updateLocation();
            }
        }
        else{
            requestPermissions();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void changeFColor(View view)
    {
        TextView tv1 = findViewById(R.id.text_home);

        if(tv1.getCurrentTextColor()== Color.BLACK){

        tv1.setTextColor(Color.RED);

        }
        else if(tv1.getCurrentTextColor()== Color.RED){
        tv1.setTextColor(Color.GREEN);
        }
        else if(tv1.getCurrentTextColor()== Color.GREEN)
            tv1.setTextColor(Color.BLACK);
    }
    public void changeFFace(View view) {
        TextView texthome = findViewById(R.id.text_home);
        switch(count){
            case 1:
                texthome.setTypeface(Typeface.DEFAULT,Typeface.ITALIC);
                break;
            case 2:
                texthome.setTypeface(Typeface.MONOSPACE,Typeface.NORMAL);
                break;
            case 3:
                texthome.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
                break;
            case 4:
                texthome.setTypeface(Typeface.SERIF,Typeface.ITALIC);
                break;
        }
        count++;
        if(count==5){
            count=1;
        }
    }

    public void changeFSize(View view) {
        TextView texthome = findViewById(R.id.text_home);
        texthome.setTextSize(font);
        font +=5;
        if(font==40){
            font = 20;
        }

    }

    public void ToastMe(View view) {

        final EditText etn = findViewById(R.id.editText);
        final EditText etc = findViewById(R.id.editText2);
        final EditText ete = findViewById(R.id.editText3);

        RadioGroup rg = findViewById(R.id.radioGroup1);
        final RadioButton r1 = findViewById(R.id.radioButton);
        final RadioButton r2 = findViewById(R.id.radioButton2);
        final Spinner s = findViewById(R.id.spinner1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(r1.isChecked() == true)
                    dept = "Male";
                if(r2.isChecked() == true)
                    dept = "Female";
            }
        });
        name = etn.getText().toString();
        college=etc.getText().toString();
        mail = ete.getText().toString();
        gender = s.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(),"Name:" + name +"\nCollege:"+ college+ "\nemail"+ mail + "\nDepartment:" + gender + " \nGender:" + dept , Toast.LENGTH_SHORT).show();

    }

    public void add(View view) {
        //add to database
        EditText editRollno = findViewById(R.id.editRollno);
        EditText editName = findViewById(R.id.editName);
        EditText editMarks = findViewById(R.id.editMarks);
    }

    public void delete(View view) {
        //delete from database
    }

    public void modify(View view) {
        //modify the data in database
    }

    public void view(View view) {
        //view one row in database
    }

    public void viewAll(View view) {
        //view all data in database
        StringBuffer buffer = new StringBuffer();

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("M_CH_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void notifyMe(View view) {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "M_CH_ID")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
        finish();
//        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
//        NotificationCompat.Builder noti = new NotificationCompat.Builder(this,"M_CH_ID");
//        noti.setContentTitle("1 New Message");
//        noti.setContentText("Hi click me to launch activity");
//        noti.setSmallIcon(android.R.drawable.ic_btn_speak_now);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//        }
//        else {
//            Intent i1 = new Intent(this, ExperimentFive.class);
//            PendingIntent pd = PendingIntent.getActivity(this, 1, i1, 0);
//            noti.setContentIntent(pd);
//            noti.setAutoCancel(true);
//            Toast.makeText(MainActivity.this, "Notify user", Toast.LENGTH_SHORT).show();
//
//            manager.notify(1, noti.build());
//            finish();
//
//        }

    }


    public void load(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bit = initImage(((EditText) findViewById(R.id.imageURL)).getText().toString());
                final ImageView v = findViewById(R.id.appCompatImageView);
                v.post(new Runnable() {
                    @Override
                    public void run() {
                        if(bit != null){
                            v.setImageBitmap(bit);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Error Loading Image" ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }
    public static Bitmap initImage(String src){
        try{
            URL url = new URL(src);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream inp = conn.getInputStream();
            Bitmap img = BitmapFactory.decodeStream(inp);
            return img;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID
        );
    }
    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Granted. Start getting the location information
            }
        }
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        MainActivity.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
    private class GeocoderHandler extends Handler {
        TextView tvAddress;
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            tvAddress.setText(locationAddress);
        }
    }
    public void updateLocation(){
        appLocationService = new AppLocationService(
                MainActivity.this);
        final TextView tvAddress = findViewById(R.id.tvAddress);
        Button btnGPSShowLocation = findViewById(R.id.btnGPSShowLocation);
        btnGPSShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Location gpsLocation = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);
                if (gpsLocation != null) {
                    double latitude = gpsLocation.getLatitude();
                    double longitude = gpsLocation.getLongitude();
                    String result = "Latitude: " + gpsLocation.getLatitude() +
                            " Longitude: " + gpsLocation.getLongitude();
                    tvAddress.setText(result);
                } else {
                    showSettingsAlert();
                }
            }
        });
        Button btnShowAddress;
        btnShowAddress = findViewById(R.id.btnShowAddress);
        btnShowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Location location = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);

                //you can hard-code the lat & long if you have issues with getting it
                //remove the below if-condition and use the following couple of lines
                //double latitude = 37.422005;
                //double longitude = -122.084095

                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new GeocoderHandler());
                } else {
                    showSettingsAlert();
                }

            }
        });

    }
}
