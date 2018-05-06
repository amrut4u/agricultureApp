package cz.martykan.forecastie;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class Login extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextMessage;
    String email,message;
    AlertDialog alertDialog;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.edit_email);
        editTextMessage = (EditText) findViewById(R.id.edit_message);


    }

    public void insert(View view) {
        email = editTextEmail.getText().toString();
        message = editTextMessage.getText().toString();

        if (email.equals("")) {
            //editTextPassword1.setError("Please Enter Password");
            alertDialog = new AlertDialog.Builder(Login.this).create();
            alertDialog.setTitle("oops!");
            alertDialog.setMessage("Passwords Enter Your Email Address");
            alertDialog.setButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            alertDialog.show();
        }
        else if (message.equals("")) {
            //editTextCellno.setError("Please Enter Password");
            alertDialog = new AlertDialog.Builder(Login.this).create();
            alertDialog.setTitle("oops!");
            alertDialog.setMessage("Please Enter Something");
            alertDialog.setButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            alertDialog.show();
        }
        else if (email.equals("")&&message.equals("")) {
            alertDialog = new AlertDialog.Builder(Login.this).create();
            alertDialog.setTitle("oops!");
            alertDialog.setMessage("Plese Fill Complete Information");
            alertDialog.setButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            alertDialog.show();
        }

        else{
            insertToDatabase(email,message);
        }
    }

    private void insertToDatabase(final  String email,String message) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Login.this, "Please wait", "Loading...");
            }




            @Override
            protected String doInBackground(String... params) {
                String paramEmail = params[0];
                String paramMessage = params[1];
               /* String  password= editTextPassword.getText().toString();
                String id= editTextId.getText().toString();
                String cellno= editTextCellno.getText().toString();
                */
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("email", paramEmail));
                nameValuePairs.add(new BasicNameValuePair("message", paramMessage));
                result = null;


                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "https://umeshsakhare.000webhostapp.com/amrut/Amrut/login.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
            @Override
            protected void onPostExecute(String result) {
                String s = result.trim();
                Toast.makeText(getApplicationContext(), "server respose="+s, Toast.LENGTH_LONG).show();
                loadingDialog.dismiss();
                if (s.equalsIgnoreCase("success")) {
                    Intent intent = new Intent(Login.this, Success.class);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Id", Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(email,message);
    }
    public void next(View vv)
    {
        Intent i=new Intent(Login.this,Registration.class);
        startActivity(i);
    }
}

