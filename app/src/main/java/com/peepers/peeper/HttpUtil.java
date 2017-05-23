package com.peepers.peeper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dddog on 2017/05/23.
 */

public class HttpUtil {

    public static String getHtmlToString(String urlStr) {

        String output = "";
        try {
            URL url = new URL(urlStr);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 4.4.2; sdk Build/KK) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36");
//            httpURLConnection.setRequestProperty("Content-Type", "text/html;");
//            httpURLConnection.setRequestMethod("GET");
//            httpURLConnection.setUseCaches(false);
//            httpURLConnection.setDoInput(true);
//            httpURLConnection.setDoOutput(true);

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuffer.append(temp + "\n");
            }

            output = stringBuffer.toString().trim();
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("HttpUtilOutput>>>" + output);
//        Log.d("HttpUtilOutput", output);
        return output;
    }
}
