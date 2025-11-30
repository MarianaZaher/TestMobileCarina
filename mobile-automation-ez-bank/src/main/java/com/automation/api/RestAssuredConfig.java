package com.automation.api;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * REST Assured configuration for handling SSL certificate issues
 * Disables SSL verification for testing environments
 */
public class RestAssuredSSLConfig {

    public static void configure() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create a hostname verifier that does not validate hostname
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

            System.out.println("[RestAssuredSSLConfig] SSL verification disabled for testing");
        } catch (Exception e) {
            System.err.println("[RestAssuredSSLConfig] Failed to configure SSL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
