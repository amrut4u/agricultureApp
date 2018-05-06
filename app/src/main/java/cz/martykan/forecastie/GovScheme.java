package cz.martykan.forecastie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GovScheme extends AppCompatActivity {
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov_scheme);
        wv=(WebView)findViewById(R.id.demo);
        WebSettings ws=wv.getSettings();
        ws.setJavaScriptEnabled(true);
        wv.loadUrl("http://agriculture.gov.in/Home.aspx");
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