package com.example.madexperiments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    float font = 20;
    int count=1;
    String name, gender, dept, college, mail;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
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
        else {
        tv1.setTextColor(Color.BLACK);
        }
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
}
