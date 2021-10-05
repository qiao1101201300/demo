package com.qs.demo.desig.flyweight;

import java.util.HashMap;
import java.util.Map;

public class WebSiteFactory {
    private Map<String, WebSite> pool = new HashMap<>();

    public WebSite getWebSite(String type) {
        if (!pool.containsKey(type)) {
            pool.put(type, new ConcreateWebSite(type));
        }
        return pool.get(type);
    }

    public int getWebSiteCount() {
        return pool.keySet().size();
    }
}
