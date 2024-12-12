package com.dust.monitoring.api.analytics.enums;

import lombok.Getter;

@Getter
public enum AllowedUrl {
    LOGIN("login", "/auth/login"),
    REGISTER("register", "/auth/register");

    private String name;
    private String uri;

    AllowedUrl(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public static boolean uriExist(String uri) {
        for (AllowedUrl url :
                AllowedUrl.values()) {
            if (uri.equalsIgnoreCase(url.getUri())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
