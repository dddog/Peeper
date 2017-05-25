package com.peepers.peeper;

import android.support.annotation.DrawableRes;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by dddog on 2017/05/23.
 */

public class GoogleTask {

    private SiteDto siteDto;
    private String htmlStr;
    private Document document;

//    final private String urlStr = "https://www.google.co.kr/search?q=";
    final private String urlStr = "https://www.google.com/search?q=";
    private String searchVal = "";

    final @DrawableRes int logoID = R.drawable.googlelogo_color_120x44dp;

    public GoogleTask(String searchVal) {
        this.searchVal = searchVal;
        try {
            document = Jsoup.connect(urlStr+this.searchVal).userAgent("Mozilla/5.0 (Windows; U; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //htmlStr = HttpUtil.getHtmlToString(urlStr+this.searchVal);
        //document = Jsoup.parse(htmlStr);
    }

    public String getHtmlStr() {
        return htmlStr;
    }

    public SiteDto getSiteDto() {
        String totalCount = "";
        String output = "";

        String selector = "div#resultStats";
        Elements totalCountElements = document.select(selector);

        if( totalCountElements != null && totalCountElements.size() > 0 ) {
            Log.d("totalCountElments",""+totalCountElements);
            output = totalCountElements.get(0).text().trim();
            Log.d("output", ""+output);
        }

        if( !output.equals("") && output.contains("검색결과") ) {
//            System.out.println("output>>>" + output);
//            Log.d("output", output);
            if( output.contains("약") ) {
                totalCount = output.split("검색결과 약 ")[1].split("개")[0].trim();
                Log.d("totalCount", ""+totalCount);
            } else {
                totalCount = output.split("검색결과 ")[1].split("개")[0].trim();
                Log.d("totalCount",""+totalCount);
            }
        } else {
            totalCount = "0";
        }

        siteDto = new SiteDto(logoID, totalCount);
        return siteDto;
    }

}
