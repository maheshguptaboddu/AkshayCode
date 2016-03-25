package mockapp.yashas.com.simplemockapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONObject;

public class LoginAcitivity extends AppCompatActivity implements RequestHandler.CallbackHandler{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivity);
    }

    public void doLogin(View view){
        new RequestHandler().makeWebServiceCall(RequestHandler.RequestType.LOGIN, this);
    }

    @Override
    public void onSuccess(RequestHandler.RequestType requestType, JSONObject jsonObject) {
        if (requestType == RequestHandler.RequestType.LOGIN){
            // Go next activity
        }
    }

    @Override
    public void onFailure(RequestHandler.RequestType requestType, String error) {
        if (requestType == RequestHandler.RequestType.LOGIN){
            //Show Error Dialog
        }

    }
}
