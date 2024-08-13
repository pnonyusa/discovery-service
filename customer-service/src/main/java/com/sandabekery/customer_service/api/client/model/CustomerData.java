package com.sandabekery.customer_service.api.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldNameConstants
public class CustomerData {
    private Long uid;
    private String firstName;
    private String surname;
    private String email;
}
