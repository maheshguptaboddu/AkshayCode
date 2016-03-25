package mockapp.yashas.com.simplemockapp;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import mockapp.yashas.com.simplemockapp.ServiceResponse.ServiceResponse;

public class RequestHandler {

    public enum RequestType {
        LOGIN,
        RELOGIN,
        CHANGE_PWD,
    }


    public interface CallbackHandler {

        void onSuccess(RequestType requestType, JSONObject jsonObject);

        void onFailure(RequestType requestType, String error);
    }


    public void makeWebServiceCall(final RequestType requestType, final CallbackHandler listener) {
        if (AppConfig.IS_MOCK_BUILD) {
            listener.onSuccess(requestType, getMockResponse(requestType));
        } else {
            AsyncHttpClient client = new AsyncHttpClient();
            client.get(AppConfig.SERVER_URL + "/api/login", new AsyncHttpResponseHandler() {

                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                    try {
                        listener.onSuccess(requestType, new JSONObject(new String(response)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }

                @Override
                public void onRetry(int retryNo) {
                    // called when request is retried
                }
            });
        }
    }


    private JSONObject getMockResponse(RequestType type) {
        if (type == RequestType.LOGIN) {
            try {
                return new JSONObject("{key : value}");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
