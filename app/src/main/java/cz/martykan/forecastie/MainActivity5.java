package cz.martykan.forecastie;



        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.app.ProgressDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemSelectedListener;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.Spinner;
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

public class MainActivity5 extends AppCompatActivity {
    EditText editid,editpass;
    String desg,id,password;
    Bundle bundle;
    Intent i;
    AlertDialog alertDialog;
    String result;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        editid = (EditText) findViewById(R.id.editTextId);
        editpass = (EditText) findViewById(R.id.editTextPassword);
    }
    public void insert(View view) {
        id = editid.getText().toString();
        password = editpass.getText().toString();
        if (id.equals("")) {
            //editTextId.setError("Please Enter your id");
            alertDialog = new AlertDialog.Builder(MainActivity5.this).create();
            alertDialog.setTitle("oops!");
            alertDialog.setMessage("Please Enter Your Id");
            alertDialog.setButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            alertDialog.show();
        }
        else if (password.equals("")) {
            //editTextPassword.setError("Please Enter Password");
            alertDialog = new AlertDialog.Builder(MainActivity5.this).create();
            alertDialog.setTitle("oops!");
            alertDialog.setMessage("Please Enter Your Password");
            alertDialog.setButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            alertDialog.show();
        } else {
            insertToDatabase(id, password);
        }
    }


    private void insertToDatabase(final String id, String password) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(MainActivity5.this, "Please wait", "Loading...");
            }




            @Override
            protected String doInBackground(String... params) {
                String paramId = params[0];
                String paramPassword = params[1];
               /* String  password= editTextPassword.getText().toString();
                String id= editTextId.getText().toString();
                String cellno= editTextCellno.getText().toString();
                */
                InputStream is=null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id", paramId));
                nameValuePairs.add(new BasicNameValuePair("password", paramPassword));
                result=null;


                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "https://umeshsakhare.000webhostapp.com/amrut/Amrut/login1.php");
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
                loadingDialog.dismiss();
                if (s.equalsIgnoreCase("success")) {
                    Intent intent = new Intent(MainActivity5.this, Success.class);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id, password);
    }
    public void mi(View vi)
    {
        Intent i=new Intent(MainActivity5.this,Registration.class);
        startActivity(i);
    }
}


