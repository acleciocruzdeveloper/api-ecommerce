package br.com.api.products.configs.local;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Slf4j
@Configuration
@Profile("local")
public class SnsCreateTopicLocal {

    private final String productEventTopic;

    private final AmazonSNS clientSNS;

    public SnsCreateTopicLocal() {
        this.clientSNS = AmazonSNSClient.builder()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "http://localhost:4566", Regions.US_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        CreateTopicRequest createTopicRequest = new CreateTopicRequest("product-event");
        this.productEventTopic = this.clientSNS.createTopic(createTopicRequest).getTopicArn();
        log.info("SNS::TOPIC::ARN::{}", this.productEventTopic);
    }
    @Bean
    public AmazonSNS snsClient(){
        return this.clientSNS;
    }

    @Bean(name = "productEventTopic")
    public Topic snsProductEventTopic(){
        return new Topic().withTopicArn(productEventTopic);
    }

}
