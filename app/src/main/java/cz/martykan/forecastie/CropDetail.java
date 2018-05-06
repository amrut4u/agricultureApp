package cz.martykan.forecastie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CropDetail extends AppCompatActivity {
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_detail);
        wv=(WebView)findViewById(R.id.demo);
        WebSettings ws=wv.getSettings();
        ws.setJavaScriptEnabled(true);
        wv.loadUrl("http://cropinfo.in/agriinfo/crop-information/");
        wv.setWebViewClient(new WebViewClient());
    }
    public void onBackPressed()
    {
        if(wv.canGoBack())
        {
            wv.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}