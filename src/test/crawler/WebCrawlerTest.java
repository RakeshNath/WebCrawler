package test.crawler;

import main.crawler.WebCrawler;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WebCrawlerTest {

    public WebCrawler crawlerObj;

    @Test
    public void returns_True_isValidUrl_withValidURLBeingPassed() {
        crawlerObj = new WebCrawler();
        assertTrue(crawlerObj.isValidUrl("http://www.validurl.com"));
    }

    @Test
    public void return_False_isValidUrl_withInvalidURLBeingPassed() {
        crawlerObj = new WebCrawler();
        assertFalse(crawlerObj.isValidUrl("someurl"));
    }

    @Test
    public void returns_True_canAddToList_withValidURLBeingPassed_All() {
        crawlerObj = new WebCrawler();
        crawlerObj.urlSet.add("www.presenturlinset.com");
        crawlerObj.domain = "www.testingdomain.com";
        assertEquals(crawlerObj.canAddToList("www.testingdomain.com/new.html"), true);
    }

    @Test
    public void returns_False_canAddToList_withValidURLBeingPassed_Url_Present() {
        crawlerObj = new WebCrawler();
        crawlerObj.urlSet.add("www.testingdomain.com/new.html");
        crawlerObj.domain = "www.testingdomain.com";
        assertEquals(crawlerObj.canAddToList("www.testingdomain.com/new.html"), false);
    }

    @Test
    public void returns_False_canAddToList_withValidURLBeingPassed_Wrong_Domain() {
        crawlerObj = new WebCrawler();
        crawlerObj.urlSet.add("www.testingdomain.com/new.html");
        crawlerObj.domain = "www.testingdomain.com";
        assertEquals(crawlerObj.canAddToList("www.testingdomain1.com/new.html"), false);
    }

    @Test
    public void returns_True_isNotInSameDomain_withValidURLBeingPassed() {
        crawlerObj = new WebCrawler();
        crawlerObj.domain = "www.testingdomain.com";
        assertEquals(crawlerObj.isNotInSameDomain("www.testingdomain1.com/new.html"), true);
    }

    @Test
    public void returns_False_isNotInSameDomain_withInValidURLBeingPassed() {
        crawlerObj = new WebCrawler();
        crawlerObj.domain = "www.testingdomain.com";
        assertEquals(crawlerObj.isNotInSameDomain("www.testingdomain.com/new.html"), false);
    }

    @Test
    public void returns_True_isNotInSameDomain_withValidContentTypeBeingPassed() {
        crawlerObj = new WebCrawler();
        crawlerObj.domain = "www.testingdomain.com";
        assertEquals(crawlerObj.isNotInSameDomain("www.testingdomain.com/new.html"), false);
    }

    @Test
    public void returns_EmptyList_forValidUrlProvided_with_No_Links_Inside() {
        crawlerObj = new WebCrawler();
        List<String> list = new ArrayList<String>();
        // Local node app runs with no hyperlinks inside
        assertEquals(crawlerObj.start("http://localhost:3001/"), list);
    }

    @Test
    public void returns_List_forValidUrlProvided_with_One_Link_Inside() {
        crawlerObj = new WebCrawler();
        List<String> list = new ArrayList<String>();
        list.add("http://localhost:3001/fileUpload");
        // Local node app runs with single hyperlink added inside
        assertEquals(crawlerObj.start("http://localhost:3001/fileUpload"), list);
    }

}