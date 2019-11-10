package GetRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Controller for sending GET request
 *
 */

public class GetRequest {


    public static void main(String[] args){
        GetRequest getRequest = new GetRequest("datakomm.work", 80);
        getRequest.doSendGet();
    }

    private String BASE_URL; // URL of the server

    public GetRequest(String host, int port){

        BASE_URL = "http://" + host + ":" + port +"/";

    }

    /**
     * Send http GET to a specific path on web server
     */

    public void doSendGet(){

        sendGet("dkrest/test/get2");
    }

    /**
     * Send HTTP GET
     * @param path Relative path in the API
     */

    private void sendGet(String path) {

        try {

            String url = BASE_URL + path;
            URL urlObject = new URL(url);
            System.out.println("Sending HTTP GET REQUEST to: " + url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Server reached"); // Response OK
                InputStream inputStream = connection.getInputStream();
                String responseBody = convertStreamToString(inputStream);
                inputStream.close();
                System.out.println("Response from server: " + responseBody);
            } else {
                String responseDescription = connection.getResponseMessage();
                System.out.println("Request failed, response code: " + responseCode + " (" + responseDescription + ") ");
            }


        } catch (ProtocolException e) {
            System.out.println("Protocol not supported by the server");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }


        /**
         * Read content from an InputStream, convert and return as String
         * @param InputStream to read body
         * @return body as string
         */
    }
    public String convertStreamToString(InputStream inputStream){

        BufferedReader in = new BufferedReader((new InputStreamReader(inputStream)));
        StringBuilder response = new StringBuilder();

        try{
            String inputLine;
            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
                response.append('\n');

            }
        } catch (IOException e){
            System.out.println("Could not read data from HTTP response: " + e.getMessage());
        }

        return response.toString();

    }

    public static void convertToJSON(InputStream inputStream){


    }


}
