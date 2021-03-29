/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApiAliment;

/**
 *
 * @author nizar.bouchedakh
 */

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.AbstractHttpMessage;



import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import org.json.JSONObject;


public class httpClient {

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();;

    
    private void close() throws IOException {
        httpClient.close();
    }
    

    public JSONObject sendGet(String url) throws Exception {

        HttpGet request = new HttpGet(url);

        // add request headers
        //request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");
        CloseableHttpResponse response = httpClient.execute(request);
        
        System.out.println(url);

        try {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());
            
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != 200)
            {
                throw new RuntimeException("Failed to get data. Status code:"+statusCode);
            }

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                
                // use org.apache.http.util.EntityUtils to read json as string
                String json = EntityUtils.toString(entity);

                JSONObject o = new JSONObject(json);
                
                response.close();
                return o;
            }
        }
        finally 
        {
            response.close();
        }
        
        return null;

    }


}
