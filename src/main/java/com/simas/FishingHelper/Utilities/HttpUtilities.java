package com.simas.FishingHelper.Utilities;

import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtilities {
    public static URL createUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
