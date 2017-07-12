import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by aaa on 7/11/17.
 */
public class UnirestTest {
    public static void main(String[] args){
        try {
            HttpResponse<JsonNode> response = Unirest.get("http://www.wsj.com/xml/rss/3_7455.xml").asJson();
            System.out.println(response);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
