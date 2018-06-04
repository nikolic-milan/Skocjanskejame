package android.parkskocjanskejame;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Info extends AppCompatActivity {
    Button next;
    String uniqueID;
    String insertUserURL = "http://www.studenti.famnit.upr.si/~89161009/insertUser.php";
    String insertGroupURL = "http://www.studenti.famnit.upr.si/~89161009/insertGroupUser.php";
    EditText e_mail, age, group_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        e_mail = (EditText) findViewById(R.id.e_mail);
        age = (EditText) findViewById(R.id.age);
        group_size = (EditText) findViewById(R.id.group_size);
        Intent intent = getIntent();
        uniqueID = intent.getStringExtra("id");
        int x = uniqueID.length();





        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(group_size.getText().toString().equals("")){
                    if(isValidEmail(e_mail.getText().toString()) == false){
                        Toast.makeText(getApplicationContext(),"Please insert a vilid e-mail.",Toast.LENGTH_LONG).show();
                    }else {
                        insertUser();
                        Intent i = new Intent(getApplicationContext(), LocationSearch.class);
                        startActivity(i);
                    }
                }else{
                    insertGroup();
                    Intent i = new Intent(getApplicationContext(), LocationSearch.class);
                    startActivity(i);
                }


            }
        });
    }
    public void insertUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, insertUserURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> parametar = new HashMap<>();
                parametar.put("user_id", uniqueID);
                parametar.put("email", String.valueOf(e_mail.getText()));
                parametar.put("age", String.valueOf(age.getText()));

                return parametar;
            }

        };
        requestQueue.add(request);
    }
    public void insertGroup() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, insertGroupURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> parametar = new HashMap<>();
                parametar.put("group_id", uniqueID);
                parametar.put("group_size", group_size.getText().toString());

                return parametar;
            }

        };
        requestQueue.add(request);
    }


    private boolean isValidEmail(String email) {
        String emailRegex ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(email.matches(emailRegex))
        {
            return true;
        }
        return false;
    }
}
