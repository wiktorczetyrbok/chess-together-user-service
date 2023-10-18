package com.myApp.web.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@EnableConfigurationProperties
@Profile("unit-test")
public class UnitTestConfig {

}
