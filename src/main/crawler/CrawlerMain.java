package main.crawler;

public class CrawlerMain {
    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler();
        String url = "https://wiprodigital.com/";
        if(crawler.isValidUrl(url)){
            crawler.start(url);
        }
    }
}
