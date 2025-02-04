package com.beenz.logpipelinetest.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DefaultLogGenerator implements LogGenerator{

    private static final Random random = new Random();
    private static final String[] LOG_GROUP = {"bid", "lg", "win", "ck", "pass"};
    private static final String[] EXCHANGE_NAMES = {"Exchange1", "Exchange2", "Exchange3", "Exchange4"};
    private static final String[] USER_AGENTS = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Edge/91.0.864.59 Safari/537.36",
            "Mozilla/5.0 (Linux; Android 10; SM-G973N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.120 Mobile Safari/537.36"
    };
    private static final String[] REFERERS = {
            "https://www.google.com/",
            "https://www.example.com/",
            "https://www.example.org/"
    };

    public static String generateIpAddress() {
        return String.format("%d.%d.%d.%d", random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static String generateUserAgent() {
        return USER_AGENTS[random.nextInt(USER_AGENTS.length)];
    }

    public static String generateReferer() {
        return REFERERS[random.nextInt(REFERERS.length)];
    }

    public static String generateFQDN() {
        String[] domains = {"example.com", "test.org", "mywebsite.net", "platform.io"};
        return "www." + domains[random.nextInt(domains.length)];
    }

    public static String generateBidPrice() {
        return String.valueOf(1 + (100 * random.nextDouble())); // Random bid price between 1 and 100
    }

    public static String generateProcessingTime() {
        return String.valueOf(random.nextInt(1000)); // Random processing time between 0 and 1000 ms
    }

    public static String generateExchangeName() {
        return EXCHANGE_NAMES[random.nextInt(EXCHANGE_NAMES.length)];
    }

    public static String generateLogGroup() {
        return LOG_GROUP[random.nextInt(LOG_GROUP.length)];
    }

    @Override
    public Log generate() {

        String logGroup = generateLogGroup();
        String ip = generateIpAddress();
        String ua = generateUserAgent();
        String ref = generateReferer();
        String fqdn = generateFQDN();
        String bp = generateBidPrice();
        String pt = generateProcessingTime();
        String exchangeName = generateExchangeName();

        return new Log(logGroup, ip, ua, ref, fqdn, bp, pt, exchangeName, System.currentTimeMillis());
    }
}
