package cz.martykan.forecastie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Aai on 30/04/2018.
 */

public class SchemeInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govscheme);
    }

    protected void onClickSchemeInfo(View v) {

        Intent intent = new Intent(SchemeInfo.this, GovScheme.class);

        startActivity(intent);

    }
}