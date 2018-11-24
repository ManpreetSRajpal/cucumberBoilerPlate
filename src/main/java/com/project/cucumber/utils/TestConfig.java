package com.project.cucumber.utils;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TestConfig {
    private String name;
    private String loginUrl;
    private int pageTimeOut;
}
