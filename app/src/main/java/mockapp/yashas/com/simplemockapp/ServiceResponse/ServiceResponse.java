package mockapp.yashas.com.simplemockapp.ServiceResponse;

import org.json.JSONObject;

/**
 * Created by mahesh on 3/24/16.
 */
public abstract class ServiceResponse {


    protected abstract void parse(JSONObject jsonObject);

}
