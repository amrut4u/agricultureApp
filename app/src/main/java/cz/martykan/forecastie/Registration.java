package cz.martykan.forecastie;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
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


public class Registration extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPassword;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private EditText editTextAddress;
    String name,phone,email,address,password;
    AlertDialog alertDialog;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextName= (EditText) findViewById(R.id.edit_name);
        editTextPassword= (EditText) findViewById(R.id.edit_password);
        editTextPhone = (EditText) findViewById(R.id.edit_phone);
        editTextEmail = (EditText) findViewById(R.id.edit_email);
        editTextAddress = (EditText) findViewById(R.id.edit_message);


    }

    public void insert(View view) {
        name = editTextName.getText().toString();
        phone = editTextPhone.getText().toString();
        email = editTextEmail.getText().toString();
        address = editTextAddress.getText().toString();
        password = editTextPassword.getText().toString();
        if (name.equals("")) {
            //editTextId.setError("Please Enter your id");
            alertDialog = new AlertDialog.Builder(Registration.this).create();
            alertDialog.setTitle("oops!");
            alertDialog.setMessage("Please Enter Your Name");
            alertDialog.setButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            alertDialog.show();

        } else if (phone.equals("")) {
            //editTextPassword.setError("Please Enter Password");
            alertDialog = new AlertDialog.Builder(Registration.this).create();
            alertDialog.setTitle("oops!");
            alertDialog.setMessage("Please Enter Your Mobile Number");
            alertDialog.setButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            alertDialog.show();
        }
        else if (email.equals("")) {
            //editTextPassword1.setError("Please Enter Password");
            alertDialog = new AlertDialog.Builder(Registration.this).create();
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
        else if (address.equals("")) {
            //editTextCellno.setError("Please Enter Password");
            alertDialog = new AlertDialog.Builder(Registration.this).create();
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
        else if (password.equals("")) {
            //editTextCellno.setError("Please Enter Password");
            alertDialog = new AlertDialog.Builder(Registration.this).create();
            alertDialog.setTitle("oops!");
            alertDialog.setMessage("Please Enter Password");
            alertDialog.setButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            alertDialog.show();
        }
        else if (name.equals("")&&email.equals("")&&phone.equals("")&password.equals("")) {
            alertDialog = new AlertDialog.Builder(Registration.this).create();
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
            insertToDatabase(name,phone,email,address,password);
        }
    }

    private void insertToDatabase(final String name, String phone, String email,String address,String password) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Registration.this, "Please wait", "Loading...");
            }




            @Override
            protected String doInBackground(String... params) {
                String paramName = params[0];
                String paramPhone = params[1];
                String paramEmail = params[2];
                String paramAddress = params[3];
                String paramPassword = params[4];
               /* String  password= editTextPassword.getText().toString();
                String id= editTextId.getText().toString();
                String cellno= editTextCellno.getText().toString();
                */
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", paramName));
                nameValuePairs.add(new BasicNameValuePair("phone", paramPhone));
                nameValuePairs.add(new BasicNameValuePair("email", paramEmail));
                nameValuePairs.add(new BasicNameValuePair("address", paramAddress));
                nameValuePairs.add(new BasicNameValuePair("password", paramPassword));
                result = null;


                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "https://umeshsakhare.000webhostapp.com/amrut/Amrut/reg.php");
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
                    Intent intent = new Intent(Registration.this, Success.class);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Id", Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name,phone,email,address,password);
    }
}

