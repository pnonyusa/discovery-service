package com.sandabekery.customer_service.sandabakery.enums.messages;

import lombok.Getter;

@Getter
public enum MessageEnum {

    NOT_FOUND("Customer data not found");

    private final String message;

    MessageEnum(String message){
        this.message = message;
    }
}
