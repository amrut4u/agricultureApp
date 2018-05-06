package cz.martykan.forecastie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.content.Intent;
import cz.martykan.forecastie.activities.MainActivity;

import cz.martykan.forecastie.activities.SplashActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.content.Intent;


public class GridViewImageTextActivity extends AppCompatActivity {

    GridView androidGridView;

    String[] gridViewString = {
            "Weather", "Auction", "Govt Scheme",
            "Crop Info", "Instruments", "Sell", "Videos", "Contact Us",

    } ;
    int[] gridViewImageId = {
            R.drawable.weather2, R.drawable.auction1,  R.drawable.government, R.drawable.info1, R.drawable.instru,
            R.drawable.sell, R.drawable.video,
            R.drawable.contact,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_image_text_example);

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(GridViewImageTextActivity.this, gridViewString, gridViewImageId);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(GridViewImageTextActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
                switch(i)
                {
                    case 0:
                        Intent b=new Intent(GridViewImageTextActivity.this,MainActivity.class);
                        startActivity(b);
                        break;
                    case 1:
                        Intent d=new Intent(GridViewImageTextActivity.this,ProductDisplay.class);
                        startActivity(d);
                        break;
                    case 2:
                        Intent e=new Intent(GridViewImageTextActivity.this,SchemeInfo.class);
                        startActivity(e);
                        break;
                    case 3:
                        Intent f=new Intent(GridViewImageTextActivity.this,CropInfo.class);
                        startActivity(f);
                        break;
                    case 4:
                        Intent g=new Intent(GridViewImageTextActivity.this,Display_Instrument.class);
                        startActivity(g);
                        break;
                    case 5:
                        Intent h=new Intent(GridViewImageTextActivity.this,MainActivity5.class);
                        startActivity(h);
                        break;
                    case 6:
                        Intent z=new Intent(GridViewImageTextActivity.this,MainActivity1.class);
                        startActivity(z);
                        break;
                    default:
                        Intent l=new Intent(GridViewImageTextActivity.this,ContactUs.class);
                        startActivity(l);
                        break;
                }
            }
        });

    }
}