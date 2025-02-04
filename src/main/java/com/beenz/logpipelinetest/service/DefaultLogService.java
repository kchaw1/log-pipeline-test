package com.beenz.logpipelinetest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.beenz.logpipelinetest.utils.JsonUtils.OBJECT_MAPPER;

@Slf4j
@Service
public class DefaultLogService implements LogService {

    private final LogGenerator defaultLogGenerator;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DefaultLogService(LogGenerator defaultLogGenerator, KafkaTemplate<String, String> kafkaTemplate) {
        this.defaultLogGenerator = defaultLogGenerator;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Async
    @Scheduled(fixedRate = 1)
    public void log() {
        extracted();
    }

    private void extracted() {
        Log requestLog = defaultLogGenerator.generate();
        log.info("log text: {}", requestLog.getLogText());

        String logGroup = requestLog.logGroup();

        try {
            String message = OBJECT_MAPPER.writeValueAsString(requestLog);
            kafkaTemplate.send(logGroup, message);

            log.info("message: {}", message);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

}
