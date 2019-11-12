package JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

    public static void parseJSON(String jsonString){

        try{

            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.has("a")) {

                int a = jsonObject.getInt("a");
                System.out.println("The object contains field with value "
                        + a);
            }
            if (jsonObject.has("b")) {

                int b = jsonObject.getInt("b");
                System.out.println("The object contains field with value "
                        + b);
            }
            if (jsonObject.has("c")) {

                int c = jsonObject.getInt("c");
                System.out.println("The object contains field with value "
                        + c);
            }
        } catch(JSONException e) {
            System.out.println("Exception in JSON parsing: " + e.getMessage());
        }

    }


    /**
     * Get all json objects in a json string and display the array
     * @param jsonArrayString
     */
    public static void parseJSONArray(String jsonArrayString){

        try{
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            System.out.println("The array has " + jsonArray.length() + " items:");

            for (int i = 0; i < jsonArray.length(); ++i) {
                System.out.println("  " + jsonArray.getInt(i));
            }

        } catch(JSONException e) {
            System.out.println("Exception in JSON parsing: " + e.getMessage());
        }

    }

}
