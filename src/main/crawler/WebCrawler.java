package main.crawler;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class WebCrawler {
    public HashSet<String> urlSet;
    public String domain;

    public WebCrawler() {
        urlSet = new HashSet<String>();
    }

    public List<String> start(String url){
        System.out.println("Starting WebCrawling with " + url);
        domain = url;
        getUrls(domain);
        List<String> urlList = new ArrayList<String>();
        urlList.addAll(urlSet);
        Collections.sort(urlList);
        return urlList;
    }

    public boolean isValidUrl(String url){
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean canAddToList(String url){
        return (urlSet.contains(url) || isNotInSameDomain(url)) ? false : true;
    }

    public boolean isNotInSameDomain(String url){
        return (!url.contains(domain)) ? true : false;
    }

    public void getUrls(String link) {
        if (canAddToList(link)) {
            try {
                Document doc = Jsoup.connect(link).get();
                Elements anchorList = doc.select("a[href]");
                if (anchorList.isEmpty()) {
                    return;
                }

                anchorList.stream().map((hLink) -> hLink.attr("abs:href")).forEachOrdered((thisUrl) -> {
                    urlSet.add(link);
                    getUrls(thisUrl);
                });
            } catch (UnsupportedMimeTypeException me) {
                System.out.println("URL cant be read : " + link);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}