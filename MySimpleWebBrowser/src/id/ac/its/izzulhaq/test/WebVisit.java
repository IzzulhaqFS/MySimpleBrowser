package id.ac.its.izzulhaq.test;

import java.util.Date;

public class WebVisit {

    private String url;
    private WebVisit previousNode;
    private Date timestamp;
    private WebVisit nextNode;

    public WebVisit(String url, WebVisit previousVisit) {
        this.url = url;
        this.previousNode = previousVisit;
        timestamp = new Date();
    }

    public WebVisit getPreviousNode() {
        return previousNode;
    }

    public WebVisit getNextNode() {
        return nextNode;
    }

    public void setNextNode(WebVisit nextNode) {
        this.nextNode = nextNode;
    }

    public String toString() {
        return "Web visit: " + url + " at " + timestamp;
    }
}
