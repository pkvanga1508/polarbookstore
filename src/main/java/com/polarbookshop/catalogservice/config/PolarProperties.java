package com.polarbookshop.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

//Listening to RefreshScopeRefreshedEvent by Default
//No Need for adding @RefreshScope annotation
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {

    /**
     * Greeting message to be displayed to the users.
     */
    private String greeting; //custom polar.greeting property

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
