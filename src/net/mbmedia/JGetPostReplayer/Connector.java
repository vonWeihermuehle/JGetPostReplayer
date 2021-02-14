package net.mbmedia.JGetPostReplayer;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connector {

    //private static final int Timeout = 5000;

    public void sendRequest(Request r, int Timeout) throws IOException {

        if(r.isHTTPS()){
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                        public void checkServerTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // Install the all-trusting trust manager
            try {
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            } catch (Exception e) {
            }
        }


        URL url = new URL(r.getFullUrl());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(r.getMethod().toUpperCase());
        con.setConnectTimeout(Timeout);
        con.setReadTimeout(Timeout);
        con.setInstanceFollowRedirects(true);

        con.disconnect();

        generateLog(r, String.valueOf(con.getResponseCode()));
        return;
    }

    private void generateLog(Request r, String response){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("send " + r.getMethod().toUpperCase() + " - Request to " + r.getFullUrl()
                        + " with ResponseCode: " + response);
            }
        });

        thread.start();

        return;

    }
}
