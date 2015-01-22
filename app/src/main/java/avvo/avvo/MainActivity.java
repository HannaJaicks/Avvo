package avvo.avvo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.customobjects.QBCustomObjects;
import com.quickblox.customobjects.model.QBCustomObject;
import com.quickblox.customobjects.result.QBCustomObjectResult;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
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
   // ArrayList<String> list = new ArrayList();

    HashMap<String, String> list=new HashMap();

ArrayList<String> list1=new ArrayList();
    HashMap<String,Object> fields = new HashMap<String, Object>();

    @Override
    protected Wrapper doInBackground(Void... params)
    {


        Wrapper w = new Wrapper();
        String str1 = "https://api.avvo.com/api/1/lawyers/search.json?page=1";

        String credentials = "joshua.cleetus@gmail.com" + ":" + "gotnerds";
        try {
            HttpUriRequest request = new HttpGet(str1); // Or HttpPost(), depends on your needs
            String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
            request.addHeader("Authorization", "Basic " + base64EncodedCredentials);

            HttpClient httpclient = new DefaultHttpClient();


            HttpResponse response = httpclient.execute(request);
//this is the login response, logged so you can see it - just use the second part of the log for anything you want to do with the data
            String resp = EntityUtils.toString(response.getEntity());

            Log.d("Avvo Data:",resp);

            JSONObject jObj =new JSONObject(resp);

            JSONArray result=jObj.getJSONArray("results");

            if (result != null) {


                for (int i = 0; i < result.length(); i++) {
                    try {
                       JSONObject c=result.getJSONObject(i);
                    if(!list.containsKey("website"))
                    {
                        list.put("website",c.getString("website"));
                    }
                        else {
                        list.put("website","nil");
                    }
                    /*
                        list.add(c.getString("name"));
                        list.add(c.getString("phone"));
                        list.add(c.getString("avvo_rating"));
                        list.add(c.getString("client_rating"));
                        list.add(c.getString("client_rating_count"));
                        list.add(c.getString("sponsored"));
                        list.add(c.getString("address"));
                       //list.add(c.getString("website"));
                        list.add(c.getString("tiny_image_url"));
                        list.add(c.getString("tiny_image_url_secure"));
                       list.add(c.getString("image_url"));
                        list.add(c.getString("image_url_secure"));
                        list.add(c.getString("profile_url"));
                        list.add(c.getString("client_reviews_url"));*/
                      /*  Log.d("ID",id);
                        //Log.d("NAME",name);
                        Log.d("AVVORATING",avvorating);
                        Log.d("CLIENTRATING",clientrating);
                        Log.d("CLIENTRATINGCOUNT",clientratingcount);
                        Log.d("SPONSORED",sponsored);
                       // Log.d("PHONE",phone);
                        Log.d("ADDRESS",address);
                        Log.d("WEBSITE",website);
                        Log.d("TINYIMAGEURL",tinyimageurl);
                        Log.d("TINYIMAGEURLSECURE",tinyimageurlsecure);
                        Log.d("IMAGEURL",imageurl);
                        Log.d("IMAGEURLSECURE",imageurlsecure);
                        Log.d("PROFILEURL",profileurl);
                        Log.d("CLIENTVIEWURL",clientreviewsurl);*/



                        w.nm=list;




                       /* QBCustomObjects.createObject(qbCustomObject, new QBEntityCallbackImpl() {
                        @Override
                        public void onComplete(Result result1){
                            if(result1.isSuccess()){
                                QBCustomObjectResult qbCustomObjectResult=(QBCustomObjectResult) result1;
                                QBCustomObject qbCustomObject=qbCustomObjectResult.getCustomObject();
                                Log.d("New record",qbCustomObject.toString());
                            }

                        }

                        });*/


                JSONArray b=c.getJSONArray("specialties");

                        for(int j=0;j<b.length();j++)
                        {

                        JSONObject a=b.getJSONObject(j);
                            list1.add(a.getString("name"));
                            list1.add(a.getString("percent"));
                          /*  Log.d("SPECIALITY",specialname);
                            Log.d("PERCENT",percent);*/
                        }

                        w.nm1=list1;

                    }

                    catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
        return w;
    }



    protected void onPostExecute(Wrapper w) {
        // TODO: check this.exception
        // TODO: do something with the feed

        //Log.d("Names",w.nm.toString());
       // String[] lines=w.nm.toString();

        //  String lines=w.nm.toString();
       /* try {
            BufferedReader br=new BufferedReader(new StringReader(lines));
            String line=br.readLine().toString();
            while (line!=null)
            {*/

try {

    for (int i = 0; i <= list.size(); i++) {
        for (int j = 0; j <= list1.size(); j++) {

            fields.put("website",list.get(i));
        // Log.d("list",list.get(i));
       // fields.put("id_lawyers", list.get(i));
       /* fields.put("name", list.get(i + 1));
        fields.put("phone", list.get(i + 2));
        fields.put("avvo_rating", list.get(i + 3));
        fields.put("client_rating", list.get(i + 4));
        fields.put("client_rating_count", list.get(i + 5));
        fields.put("sponsored", list.get(i + 6));
        fields.put("address", list.get(i + 7));
        // fields.put("website",list.get(i+8));
        fields.put("tiny_image_url", list.get(i + 8));
        fields.put("tiny_image_url_secure", list.get(i + 9));
        fields.put("image_url", list.get(i + 10));
        fields.put("image_url_secure", list.get(i + 11));
        fields.put("profile_url", list.get(i + 12));
        fields.put("client_reviews_url", list.get(i + 13));*/


           // i = i + 13;

             /*   fields.put("specialties_name", list1.get(j));
                fields.put("specialties_percent", list1.get(j + 1));
                j = j + 1;*/




                QBCustomObject qbCustomObject = new QBCustomObject();
                qbCustomObject.setClassName("Lawyers");
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

 /*    for (int j = 0; j <= list1.size(); j++) {
            fields.put("specialties_name", list1.get(j));
            fields.put("specialties_percent", list1.get(j + 1));
            j = j + 1;
            //Log.d("i value", Integer.toString(i));
            QBCustomObject qbCustomObject1 = new QBCustomObject();
            qbCustomObject1.setClassName("Lawyers");
            qbCustomObject1.setFields(fields);
            QBCustomObjects.createObject(qbCustomObject, new QBCallbackImpl() {
                @Override
                public void onComplete(Result result2) {
                    if (result2.isSuccess()) {
                        QBCustomObjectResult qbCustomObjectResult1 = (QBCustomObjectResult) result2;
                        QBCustomObject qbCustomObject1 = qbCustomObjectResult1.getCustomObject();
                        Log.d("New record", qbCustomObject1.toString());
                    } else {
                        Log.e("Errors", result2.getErrors().toString());
                    }
                }
            });




    }










 for(int j=0;j<=list1.size();j++) {
        fields.put("specialties_name", list1.get(j));
        fields.put("specialties_percent", list1.get(j + 1));
        j = j + 1;

        Log.d("sn", list1.get(j));

        Log.d("sp", list1.get(j + 1));
        QBCustomObject qbCustomObject1 = new QBCustomObject();
        qbCustomObject1.setClassName("Lawyers");
        qbCustomObject1.setFields(fields);
        QBCustomObjects.createObject(qbCustomObject1, new QBCallbackImpl() {
            @Override
            public void onComplete(Result result2) {
                if (result2.isSuccess()) {
                    QBCustomObjectResult qbCustomObjectResult1 = (QBCustomObjectResult) result2;
                    QBCustomObject qbCustomObject1 = qbCustomObjectResult1.getCustomObject();
                    Log.d("New record", qbCustomObject1.toString());
                } else {
                    Log.e("Errors", result2.getErrors().toString());
                }
            }
        });

    }
*/





}
catch(Exception e){
    e.printStackTrace();
}



      /*  try{


        }   catch(Exception e){
            e.printStackTrace();
        }*/




    }


}


