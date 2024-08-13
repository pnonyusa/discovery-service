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
public class AddressData {
    private Long uid;
    private String street;
    private String houseNumber;
    private String zipCode;
    private Long customerUid;
}
