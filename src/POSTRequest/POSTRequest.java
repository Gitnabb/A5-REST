package POSTRequest;

import GETRequest.GETRequest;
import JSONParser.JSONParser;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class POSTRequest {

    JSONParser jsonParser;
    JSONObject jsonObject;
    GETRequest getRequest;

    public static void main(String[] args) {
        POSTRequest postRequest = new POSTRequest("datakomm.work", 80);
        postRequest.authorize();

    }

    private String BASE_URL; // Base URL (address) of the server

    /**
     * Create an HTTP POST example
     *
     * @param host Will send request to this host: IP address or domain
     * @param port Will use this port
     */
    public POSTRequest(String host, int port) {
        BASE_URL = "http://" + host + ":" + port + "/";
    }

    /**
     * Send a POST with three random numbers to specific path on web server
     */

    public void postThreeRandomNumbers() {

        int num1 = (int) Math.round(Math.random() * 100);
        int num2 = (int) Math.round(Math.random() * 100);
        int num3 = (int) Math.round(Math.random() * 100);

        JSONObject jsonNum = new JSONObject();
        jsonNum.put("x", num1);
        jsonNum.put("y", num2);
        jsonNum.put("z", num3);

        System.out.println("Posting JSON data to server:");
        System.out.println(jsonNum.toString());

        sendPost("dkrest/auth", jsonNum);

    }

    private void authorize() {

        String email = "kjetilbh@stud.ntnu.no";
        String phone = "48899500";

        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("email", email);
        jsonInfo.put("phone", phone);

        System.out.println("Posting JSON data to server..... ");
        sendPost("dkrest/auth", jsonInfo);

    }

    private void requestTask(){




    }

    /**
     * Send HTTP POST
     *
     * @param path Relative path in API
     * @jsonData The data in JSON format that will be posted to the server
     */

    private void sendPost(String path, JSONObject jsonData) {
        try {
            String url = BASE_URL + path;
            URL urlObj = new URL(url);
            System.out.println("Sending HTTP POST to " + url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(jsonData.toString().getBytes());
            outputStream.flush();

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Server reached");

                // Response was OK, read the body (data)
                InputStream inputStream = con.getInputStream();
                String responseBody = convertStreamToString(inputStream);
                inputStream.close();
                System.out.println("Response from server: ");
                System.out.println(responseBody);


            } else {
                String responseDescription = con.getResponseMessage();
                System.out.println("Request failed, response code: " + responseCode + " (" + responseDescription + ")");

            }
        } catch (ProtocolException e) {
            System.out.println("Protocol not supported by server");

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Read content from inputstream and return it as String.
     *
     * @param inputStream
     * @return
     */

    public String convertStreamToString(InputStream inputStream) {

        BufferedReader in = new BufferedReader((new InputStreamReader(inputStream)));
        StringBuilder response = new StringBuilder();

        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append('\n');

            }
        } catch (IOException e) {
            System.out.println("Could not read data from HTTP response: " + e.getMessage());
        }

        return response.toString();

    }


}
