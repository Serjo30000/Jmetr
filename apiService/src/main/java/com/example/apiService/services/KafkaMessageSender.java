package com.example.apiService.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.apiService.dto.MessageRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageSender {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    @Async
    public CompletableFuture<MessageRes> send(String topic, String key, Object obj) {
        try {
            var s = mapper.writeValueAsString(obj);
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, s); 
            future.thenAccept(result -> {
                log.info("Message sent successfully to topic: {}, partition: {}, offset: {}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }).exceptionally(ex -> {
                log.error("Failed to send message to topic: {}", topic, ex);
                return null;
            });
            return future.thenApply(result -> new MessageRes("Accepted"));
        } catch (JsonProcessingException e) {
            log.error("Json error", e.getMessage());
            return CompletableFuture.completedFuture(new MessageRes("Unprocessable Content"));
        } catch (Exception e) {
            log.error("Server error", e.getMessage());
            return CompletableFuture.completedFuture(new MessageRes("Internal server error"));
        }
    }
}
