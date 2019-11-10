package POSTRequest;

import org.json.JSONObject;

public class POSTRequest {

    JSONObject jsonObject;

    public static void main(String[] args) {
        POSTRequest postRequest = new POSTRequest("datakomm.work", 80);
        postRequest.postThreeRandomNumbers();



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
     *  Send a POST with three random numbers to specific path on web server
     */

    public void postThreeRandomNumbers(){

        int num1 = (int) Math.round(Math.random() * 100);
        int num2 = (int) Math.round(Math.random() * 100);
        int num3 = (int) Math.round(Math.random() * 100);

    }


}
