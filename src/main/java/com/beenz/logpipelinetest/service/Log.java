package com.beenz.logpipelinetest.service;

public record Log(String logGroup, String ip, String ua, String ref, String fqdn, String bp, String pt,
                  String exchangeName, long timestamp) {

    public String getLogText() {
        return String.join("\t", logGroup, ip, ua, ref, fqdn, bp, pt, exchangeName, String.valueOf(timestamp));
    }

}
