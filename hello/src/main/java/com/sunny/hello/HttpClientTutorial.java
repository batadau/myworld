package com.sunny.hello;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.RequestExpectContinue;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

public class HttpClientTutorial {
	
	private List<String> strs = new ArrayList<String>();
	
	private static Pattern p = Pattern.compile("<a href=\"(magnet.*)\">");
	
	public final static void abc(String str) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(str);

           // System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            /*System.out.println("----------------------------------------");
            System.out.println(responseBody);*/
            Matcher m = p.matcher(responseBody);
			while (m.find()) {
				System.out.println(m.group(1).trim());
			}
            
        } finally {
            httpclient.close();
        }
    }
	
	//<a href="magnet:?xt=urn:btih:da140bcee9702d395ca27b21ed1001de8f373dda ">
	  public static void main(String[] args) throws Exception {
		  for(int i=1;i<2;i++){
			  new Thread(new R(i)).start();
		  }
		  
		  
		  
	  }
	  
	  public static class R implements Runnable{

			private int i;
			
			R(int i){
				this.i = i;
			}
		  
			public void run() {
				try {
					abc("http://www.btsou.biz/list/包养/"+Integer.toString(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
	  }
}