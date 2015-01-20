package avvo.avvo;

import java.util.List;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.QBSettings;
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
    public void onError(List<String> strings) {
        Log.i("faillllll", "faill");
    }
});




    }


}


class RetrieveFeedTask extends AsyncTask {


    protected Object doInBackground(Object[] params)
    {
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
        }
        catch(Exception e){
            e.printStackTrace();

        }
        return null;
    }

}


