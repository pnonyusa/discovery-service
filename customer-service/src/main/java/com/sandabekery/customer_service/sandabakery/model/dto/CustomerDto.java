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
public class CustomerDto {
    private Long uid;
    private String firstName;
    private String surname;
    private String email;
}
