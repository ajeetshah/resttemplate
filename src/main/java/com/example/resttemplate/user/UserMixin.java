package com.example.resttemplate.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class UserMixin {

    @JsonProperty("userName")
    abstract String getName();

    @JsonProperty("userAge")
    abstract int getAge();

}
