package android.parkskocjanskejame;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Tracking extends Service {
    private static final String TAG = "parkskocjanskejame";
    int first = 1;
    int badge_one, badge_two, badge_three, badge_four, badge_five, badge_six, badge_seven, badge_eight,
    collection_one, collection_two, collection_three, collection_four, help, question_tabla3a_true,
            question_tabla3a_false, question_tabla7_true, question_tabla7_false, question_tabla10_true, question_tabla10_false,
            question_tabla16_true, question_tabla16_false,  question_tabla19_true, question_tabla19_false;
    String trackingURL = "http://www.studenti.famnit.upr.si/~89161009/increment.php", id = "";



    public Tracking() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {



        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // do your thing here, such as execute AsyncTask or send data to server
                trackButton();
                Log.d(TAG, "Working");
            }
        }, 1000, 60000);
        super.onCreate();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle bundle = intent.getExtras();
            if(id.equals("")){
                id = bundle.getString("id");
            }

            badge_one = badge_one + bundle.getInt("badge_one");
            badge_two = badge_two + bundle.getInt("badge_two");
            badge_three = badge_three + bundle.getInt("badge_three");
            badge_four = badge_four + bundle.getInt("badge_four");
            badge_five = badge_five + bundle.getInt("badge_five");
            badge_six = badge_six + bundle.getInt("badge_six");
            badge_seven = badge_seven + bundle.getInt("badge_seven");
            badge_eight = badge_eight + bundle.getInt("badge_eight");
            collection_one = collection_one + bundle.getInt("collection_one");
            collection_two = collection_two + bundle.getInt("collection_two");
            collection_three = collection_three + bundle.getInt("collection_three");
            collection_four = collection_four + bundle.getInt("collection_four");
            help = help + bundle.getInt("help");
            question_tabla3a_true = question_tabla3a_true + bundle.getInt("question_tabla3a_true");
            question_tabla3a_false = question_tabla3a_false + bundle.getInt("question_tabla3a_false");
            question_tabla7_true = question_tabla7_true + bundle.getInt("question_tabla7_true");
            question_tabla7_false = question_tabla7_false + bundle.getInt("question_tabla7_false");
            question_tabla10_true = question_tabla10_true + bundle.getInt("question_tabla10_true");
            question_tabla10_false = question_tabla10_false + bundle.getInt("question_tabla10_false");
            question_tabla16_true = question_tabla16_true + bundle.getInt("question_tabla16_true");
            question_tabla16_false = question_tabla16_false + bundle.getInt("question_tabla16_false");
            question_tabla19_true = question_tabla19_true + bundle.getInt("question_tabla19_true");
            question_tabla19_false = question_tabla19_false + bundle.getInt("question_tabla19_false");
            Log.d(TAG, "Updating");




        return super.onStartCommand(intent, flags, startId);
    }


    public void trackButton(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, trackingURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametar = new HashMap<>();
                parametar.put("id", id);
                parametar.put("badge_one", String.valueOf(badge_one));
                parametar.put("badge_two", String.valueOf(badge_two));
                parametar.put("badge_three", String.valueOf(badge_three));
                parametar.put("badge_four", String.valueOf(badge_four));
                parametar.put("badge_five", String.valueOf(badge_five));
                parametar.put("badge_six", String.valueOf(badge_six));
                parametar.put("badge_seven", String.valueOf(badge_seven));
                parametar.put("badge_eight", String.valueOf(badge_eight));
                parametar.put("collection_one", String.valueOf(collection_one));
                parametar.put("collection_two", String.valueOf(collection_two));
                parametar.put("collection_three", String.valueOf(collection_three));
                parametar.put("collection_four", String.valueOf(collection_four));
                parametar.put("help", String.valueOf(help));
                parametar.put("question_tabla3a_true", String.valueOf(question_tabla3a_true));
                parametar.put("question_tabla3a_false", String.valueOf(question_tabla3a_false));
                parametar.put("question_tabla7_true", String.valueOf(question_tabla7_true));
                parametar.put("question_tabla7_false", String.valueOf(question_tabla7_false));
                parametar.put("question_tabla10_true", String.valueOf(question_tabla10_true));
                parametar.put("question_tabla10_false", String.valueOf(question_tabla10_false));
                parametar.put("question_tabla16_true", String.valueOf(question_tabla16_true));
                parametar.put("question_tabla16_false", String.valueOf(question_tabla16_false));
                parametar.put("question_tabla19_true", String.valueOf(question_tabla19_true));
                parametar.put("question_tabla19_false", String.valueOf(question_tabla19_false));



                return parametar;
            }

        };
        requestQueue.add(request);

    }


}
