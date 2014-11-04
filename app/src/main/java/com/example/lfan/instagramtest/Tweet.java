package com.example.lfan.instagramtest;

/**
 * Created by lfan on 11/3/14.
 */
public class Tweet {
    String urlThumbnail="";
    String urlLowResolution="";
    String urlStandardResolution="";

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public String getUrlLowResolution() {
        return urlLowResolution;
    }

    public void setUrlLowResolution(String urlLowResolution) {
        this.urlLowResolution = urlLowResolution;
    }

    public String getUrlStandardResolution() {
        return urlStandardResolution;
    }

    public void setUrlStandardResolution(String urlStandardResolution) {
        this.urlStandardResolution = urlStandardResolution;
    }
}
