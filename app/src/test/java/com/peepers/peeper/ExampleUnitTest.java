package com.peepers.peeper;

import junit.framework.Assert;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void htmlParseTest() {
        System.out.println("start...");
        String target = "https://www.google.co.kr/search?q=azskw1101@naver.com";
        try {
            URL url = new URL(target);
            System.out.println("URL : " + url.toString());

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            httpURLConnection.setRequestProperty("Content-Type", "text/html;");
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

//            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
//            output.write("q=dddog");
//            output.flush();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((temp = bufferedReader.readLine()) != null) {
                if( temp.contains("id=\"resultStats\"") ) {
                    System.out.println("resultStats>>>"+temp.split("<div id=\"resultStats\">검색결과")[1].split("개")[0]);
                }
                stringBuffer.append(temp + "\n");
            }

//            System.out.println(stringBuffer.toString());
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull("");
    }
}