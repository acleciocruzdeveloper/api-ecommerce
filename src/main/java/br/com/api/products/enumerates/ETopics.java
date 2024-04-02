package br.com.api.products.enumerates;

public enum ETopics {

    EVENT_TOPIC("productEventTopic");
    private String value;

    ETopics(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
