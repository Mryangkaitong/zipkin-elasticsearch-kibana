package com.ytt.brave;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.HashMap;

/**
 * Hello world!
 *
 */

public class App 
{
	private static final String serverUrl = "http://localhost:9411/api/v2/spans";
	
	public static class MySpan {
		 private String traceId;
		    private String parentId;
		    private String id;
		    private String name;
		    private long timestamp;
		    private long duration;
		    private Map<String, String> tags;
		    String kind;
		    Endpoint localEndpoint, remoteEndpoint;

		    public static enum Kind {
		        CLIENT,
		        SERVER,
		        PRODUCER,
		        CONSUMER
		    }

		    public static class Endpoint {
		        String serviceName, ipv4, ipv6;
		        byte[] ipv4Bytes, ipv6Bytes;
		        int port; // zero means null

		        public Endpoint(String serviceName) {
		            this.serviceName = serviceName;
		        }
		    }
	}
	
	
    public static void main(String[] args) {
    	MySpan span = new MySpan();
        span.traceId = "5555ff9a74ca9ad2";
        span.parentId = "1ae1e4f435814744";
        span.id = "d1ab9cd2c50d13d1";
        span.kind = MySpan.Kind.SERVER.toString();
        span.name = "second test";
        span.timestamp = 1565933251470428L;
        span.duration = 8286;
        span.localEndpoint = new MySpan.Endpoint("ytt");
        Map<String, String> tags = new HashMap<String, String>();
        tags.put("name", "pioneeryi");
        tags.put("lover", "dandan");
        span.tags = tags;

        doPost(serverUrl, span);
        System.out.println("Hello World!");
    }

    public static void doPost(String url, MySpan span) {
        try {
        	//HttpClient httpClient = new HttpClient();
        	CloseableHttpClient httpClient = HttpClients.createDefault();
        	
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("charset", "UTF-8");

            String body = new Gson().toJson(span);
            body = "[" + body + "]";
            //System.out.print(body);

            StringEntity entity = new StringEntity(body);
            post.setEntity(entity);
            
            
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            System.out.print(httpResponse);
            //System.out.print("ddddddddddddddddd");
        } catch (Exception exception) {
            System.out.print("do post request fail");
        }
    }
}
