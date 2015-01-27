package avvo.avvo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.customobjects.QBCustomObjects;
import com.quickblox.customobjects.model.QBCustomObject;
import com.quickblox.customobjects.result.QBCustomObjectResult;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    Button log;
    EditText user;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log=(Button)findViewById(R.id.login);
       user=(EditText) findViewById(R.id.name);
        password=(EditText) findViewById(R.id.pass);

        QBSettings.getInstance().fastConfigInit("18664","5SuXg2M-6BLKMdb","vVk2PdRBAyJpUSf");




QBAuth.createSession(new QBEntityCallbackImpl<QBSession>() {
    @Override
    public void onSuccess(QBSession qbSession, Bundle bundle) {
        Log.i("sucess", "sucesssssss");
        log.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                QBUser qbUser = new QBUser();
                qbUser.setLogin(user.getText().toString());
                qbUser.setPassword(password.getText().toString());
                QBUsers.signIn(qbUser, new QBEntityCallbackImpl<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle args) {
                        Log.i("SUUUUUUUUUUUUCCCCCCCCCCCCEEE", "GGGGGGGGGGGGGGGG");
                        new RetrieveFeedTask().execute();


                    }

                    @Override
                    public void onError(List<String> errors) {
                        // error
                        Log.i("NOOOOOOOTTTTTTTTTTTT", "NOOOOOOTTTTTTTT");
                    }


                });

            }
        });

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(List<String> strings)
    {
        Log.i("faillllll", "faill");
    }
});




    }


}


class RetrieveFeedTask extends AsyncTask <Void, Void, Wrapper>{
  ArrayList<String> list = new ArrayList();
    HashMap<String,Object> fields = new HashMap<String, Object>();
    String s_name;


    ArrayList<String> valSetOne = new ArrayList<String>();
    ArrayList<String> valSetTwo = new ArrayList<String>();
    ArrayList<String> v1 = new ArrayList<String>();
    ArrayList<String> v2 = new ArrayList<String>();
    Set myset;

    int k = 17001;
   // private Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    protected Wrapper doInBackground(Void... params)
    {

        Wrapper w = new Wrapper();
try {
    //  for(k=1;k<=153258;k++)  {
    while (k <= 17500 ) {

        try {


            String str1 = "https://api.avvo.com/api/1/lawyers/search.json?page=" + k;

            Log.d("Value of k:",Integer.toString(k));
            k = k + 1;
            String credentials = "joshua.cleetus@gmail.com" + ":" + "gotnerds";

            HttpUriRequest request = new HttpGet(str1); // Or HttpPost(), depends on your needs
            String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
            request.addHeader("Authorization", "Basic " + base64EncodedCredentials);

            HttpClient httpclient = new DefaultHttpClient();


            HttpResponse response = httpclient.execute(request);
//this is the login response, logged so you can see it - just use the second part of the log for anything you want to do with the data
            String resp = EntityUtils.toString(response.getEntity());

            Log.d("Avvo Data:", resp);

            JSONObject jObj = new JSONObject(resp);

            JSONArray result = jObj.getJSONArray("results");

            if (result != null) {
                Integer length = result.length();

                for (int i = 0; i < length; i++) {

                    try {
                        JSONObject c = result.getJSONObject(i);
                        // String name=c.getString("website");
                        //Log.d("Hanna",name);
                        //String name=c.getString("website");
                        //list.put("website",name);
                        // List<List<String>> l = new ArrayList<List<String>>();

                        // LinkedHashMap list= new LinkedHashMap();

                        if (c.isNull("id")) {
                            list.add("null");
                        } else {
                            list.add(c.getString("id"));
                        }

                        if (c.isNull("name")) {
                            list.add("null");
                        } else {
                            list.add(c.getString("name"));
                        }

                       if (c.isNull("phone")) {
                            list.add("null");
                        } else {
                            list.add(c.getString("phone"));
                        }

                        if (c.isNull("avvo_rating")) {
                            list.add("null");
                        } else {
                            list.add(c.getString("avvo_rating"));
                        }

                        if (c.isNull("client_rating")) {
                            list.add("null");
                        } else {
                            list.add(c.getString("client_rating"));
                        }

                        if (c.isNull("client_rating_count")) {
                            list.add("null");
                        } else {
                            list.add(c.getString("client_rating_count"));
                        }

                        if (c.isNull("sponsored")) {
                            list.add("null");
                        } else {
                            list.add(c.getString("sponsored"));
                        }

                        if (c.isNull("address")) {
                            list.add("null");
                        } else {
                            list.add(c.getString("address"));
                        }

                        if (c.isNull("website")) {
                            list.add("null");

                        } else {
                            list.add(c.getString("website"));
                        }

                        if (c.isNull("tiny_image_url")) {
                            list.add("null");

                        } else {
                            list.add(c.getString("tiny_image_url"));
                        }

                        if (c.isNull("tiny_image_url_secure")) {
                            list.add("null");

                        } else {
                            list.add(c.getString("tiny_image_url_secure"));
                        }

                        if (c.isNull("image_url")) {
                            list.add("null");

                        } else {
                            list.add(c.getString("image_url"));
                        }

                        if (c.isNull("image_url_secure")) {
                            list.add("null");

                        } else {
                            list.add(c.getString("image_url_secure"));
                        }

                        if (c.isNull("profile_url")) {
                            list.add("null");

                        } else {
                            list.add(c.getString("profile_url"));
                        }

                        if (c.isNull("client_reviews_url")) {
                            list.add("null");

                        } else {
                            list.add(c.getString("client_reviews_url"));
                        }



                        HashMap<String, List<String>> map = new HashMap<String, List<String>>();

                     JSONArray b = c.getJSONArray("specialties");

                        for (int j = 0; j < b.length(); j++) {


                            JSONObject a = b.getJSONObject(j);

                            v1.add(String.valueOf(a));


                        }
                        list.add(String.valueOf(v1));
                        v1.clear();
                        w.nm = list;



                           /*    valSetOne.add(a.getString("name"));
                               valSetTwo.add(a.getString("percent"));


                               map.put("special_name", valSetOne);
                               map.put("special_percent", valSetTwo);*/



                               // list.add(a.getString("percent"));
                              /*
                               v1.add(String.valueOf(map));*/
                               // v2.add(String.valueOf(map));
                               // Log.d("SPECIALITY",String.valueOf(map));
                               // Log.d("PERCENT",String.valueOf(map));


                     /*   myset = map.keySet();
                        myset = Collections.emptySet();*/



                       /* map.remove("special_name");
                        map.remove("special_percent");*/
                        //map.clear();
                      //  map=Collections.<String, List<String>>emptyMap();



                        //map.clear();


                       /* map.remove("special_name");
                        map.remove("special_percent");*/





                      //  Log.d("AVVOLIST", String.valueOf(w.nm) + "\n");

                           /* w.nm=valSetOne;
                            w.nm=valSetTwo;*/

                    }catch(ArrayIndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}catch(IndexOutOfBoundsException e){
    e.printStackTrace();
}
catch (Exception e) {
    e.printStackTrace();
}
       return w;
    }



    protected void onPostExecute(Wrapper w) {
        // TODO: check this.exception
        // TODO: do something with the feed





try {

    for (int j = 0; j <= list.size(); j++) {


        fields.put("id_lawyers", list.get(j));
        fields.put("name",list.get(j+1));
        fields.put("phone", list.get(j + 2));
        fields.put("avvo_rating", list.get(j + 3));
        fields.put("client_rating", list.get(j + 4));
        fields.put("client_rating_count", list.get(j + 5));
        fields.put("sponsored", list.get(j + 6));
        fields.put("address", list.get(j + 7));
        fields.put("website",list.get(j + 8));
        fields.put("tiny_image_url", list.get(j + 9));
        fields.put("tiny_image_url_secure", list.get(j + 10));
        fields.put("image_url", list.get(j + 11));
        fields.put("image_url_secure", list.get(j + 12));
        fields.put("profile_url", list.get(j + 13));
        fields.put("client_reviews_url", list.get(j + 14));
        fields.put("specialties",list.get(j + 15));
        // fields.put("special_name",list.get(i+2));
        //fields.put("special_percent", list.get(i+3));


        j = j + 15;
        QBCustomObject qbCustomObject = new QBCustomObject();
        qbCustomObject.setClassName("Final_avvo2");
        qbCustomObject.setFields(fields);

        QBCustomObjects.createObject(qbCustomObject, new QBCallbackImpl() {
            @Override
            public void onComplete(Result result1) {
                if (result1.isSuccess()) {
                    QBCustomObjectResult qbCustomObjectResult = (QBCustomObjectResult) result1;
                    QBCustomObject qbCustomObject = qbCustomObjectResult.getCustomObject();
                    Log.d("New record", qbCustomObject.toString());
                } else {
                    Log.e("Errors", result1.getErrors().toString());
                }
            }
        });

    }

}
catch(Exception e){
    e.printStackTrace();
}




    }


}


