package devapp.com.bakingappudacity.utils;

import org.json.JSONArray;
import org.json.JSONException;
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

    public static int STEP_CHOSEN = 0;

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

    public static void getRecipeDetails(int position){

        try {
            JSONArray resultArray = new JSONArray(result);

            JSONObject selectedRecipe = resultArray.getJSONObject(position);

            JSONArray ingredients = selectedRecipe.getJSONArray("ingredients");
            JSONArray steps = selectedRecipe.getJSONArray("steps");

            for(int i=0; i<ingredients.length(); i++){

                INGREDIENTS_QUANTITY.add(ingredients.getJSONObject(i).getString("quantity"));
                INGREDIENTS_MEASURE.add(ingredients.getJSONObject(i).getString("measure"));
                INGREDIENTS_NAME.add(ingredients.getJSONObject(i).getString("ingredient"));

            }

            for(int i =0; i<steps.length(); i++){

                STEP_SHORT_DESCRIPTION.add(steps.getJSONObject(i).getString("shortDescription"));
                STEP_DESCRIPTION.add(steps.getJSONObject(i).getString("description"));
                STEP_VIDEO_URL.add(steps.getJSONObject(i).getString("videoURL"));
                STEP_THUMBNAIL_URL.add(steps.getJSONObject(i).getString("thumbnailURL"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void clearRecipeDetails(){

        INGREDIENTS_NAME.clear();
        INGREDIENTS_MEASURE.clear();
        INGREDIENTS_QUANTITY.clear();

        STEP_SHORT_DESCRIPTION.clear();
        STEP_THUMBNAIL_URL.clear();
        STEP_VIDEO_URL.clear();
        STEP_DESCRIPTION.clear();

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
