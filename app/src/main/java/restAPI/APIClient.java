package restAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by drag me to hell on 4/17/2017.
 */

public class APIClient {
//    public static final String BASE_URL = "http://192.168.1.16:5500"; (lokal)
    public static final String BASE_URL = "http://101.50.1.2:49485"; // (vps)
    private static Retrofit retrofit = null;


    public static Retrofit getURL() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APIInterface getApiService() {
        return getURL().create(APIInterface.class);
    }
}
