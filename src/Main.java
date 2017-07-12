/**
 * Created by Hanjun Chen on 7/11/17.
 */

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.*;

public class Main {
    public static void main(String[] args){
        /************* 1. get user input *************/
        Scanner scanner = new Scanner(System.in);

        System.out.println("How can I help you?");
        String userInput = scanner.next();
        scanner.close();

        System.out.println("one moment ...");

        /************* 2. Make request *************/

        /* 2A. You need a sessionID to avoid 202 status */
        String sessionID = String.valueOf(
                new Random(1999999999).nextInt());

        /* 2B. Create the request.*/
        try {
            HttpResponse<JsonNode> response
                    = Unirest.get(Consts.API_AI_URL)
                    .header("authorization",
                            "Bearer "+Consts.API_AI_ACCESS_TOKEN.trim())
                    // required, otherwise request will be failed
                    .header("accept", "application/json")
                    /* All query strings (e.g. &query=) should be chained
                    to the Unirest.get() method*/
                    .queryString("query",userInput)
                    .queryString("lang","en")
                    .queryString("v","20150910")
                    .queryString("sessionId",sessionID)
                    .asJson();

            /* 2C. get the response JSON body */
            JSONObject responseBody = response.getBody().getObject();

            /* 2D. get the message to display */
            String message = responseBody.getJSONObject("result")
                    .getJSONObject("fulfillment")
                    .getString("speech");

            /* 2E. Display message */
            System.out.println(message);

        } catch (UnirestException e) {
            System.err.println(e.getMessage());
        }

    }
}
