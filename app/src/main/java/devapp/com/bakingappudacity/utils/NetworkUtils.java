package devapp.com.bakingappudacity.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NetworkUtils {

    public static String JSON_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public static ArrayList<String> RECIPE_NAMES = new ArrayList<>();

    public static ArrayList<String> INGREDIENTS_QUANTITY = new ArrayList<>();
    public static ArrayList<String> INGREDIENTS_MEASURE =  new ArrayList<>();
    public static ArrayList<String> INGREDIENTS_NAME =  new ArrayList<>();

    public static ArrayList<String> STEP_SHORT_DESCRIPTION = new ArrayList<>();
    public static ArrayList<String> STEP_DESCRIPTION =  new ArrayList<>();
    public static ArrayList<String> STEP_VIDEO_URL = new ArrayList<>();
    public static ArrayList<String> STEP_THUMBNAIL_URL = new ArrayList<>();

    public static String result = "";

    public static void getRecipesNames(){

        RECIPE_NAMES.clear();

        try {

            result = getResponseFromHttpUrl(new URL(JSON_URL));

            JSONArray resultArray = new JSONArray(result);

            for(int i=0; i<resultArray.length(); i++){

                RECIPE_NAMES.add(resultArray.getJSONObject(i).getString("name"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
