package com.sandabekery.customer_service.sandabakery.model.dto;

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
public class AddressDto {
    private Long uid;
    private String street;
    private String houseNumber;
    private String zipCode;
    private Long customerUid;
}
