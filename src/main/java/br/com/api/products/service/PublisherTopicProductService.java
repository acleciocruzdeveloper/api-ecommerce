package br.com.api.products.service;

import br.com.api.products.domain.ProductEvent;
import br.com.api.products.domain.ProductTopic;
import br.com.api.products.domain.Products;
import br.com.api.products.enumerates.EProductTopic;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublisherTopicProductService {

    private AmazonSNS client;

    private Topic topicEvent;

    private ObjectMapper mapper;

    public PublisherTopicProductService(AmazonSNS client, @Qualifier("productEventTopic") Topic topicEvent, ObjectMapper mapper) {
        this.client = client;
        this.topicEvent = topicEvent;
        this.mapper = mapper;
    }

    public void publisherProductEvent(Products data, EProductTopic topic, String username) {

        ProductEvent productEvent = ProductEvent.builder()
                .productId(data.getId())
                .code(data.getProductCode())
                .username(username)
                .build();

        try {
            ProductTopic productTopic = ProductTopic.builder().eventType(topic)
                    .data(mapper.writeValueAsString(productEvent))
                    .build();

            client.publish(topicEvent.getTopicArn(), mapper.writeValueAsString(productTopic));

        } catch (JsonProcessingException e) {
            log.error("CREATED TOPIC FILED::{}", e.getMessage());
            throw new RuntimeException("::ERROR CREATED TOPIC::");
        }


    }
}
