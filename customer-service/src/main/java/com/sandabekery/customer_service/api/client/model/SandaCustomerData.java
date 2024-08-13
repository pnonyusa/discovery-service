package com.sandabekery.customer_service.api.client.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldNameConstants
public class SandaCustomerData {
    private CustomerData customerData;
    private List<AddressData> addressesList;
}
