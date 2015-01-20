package avvo.avvo;

import java.util.List;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.QBSettings;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


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

