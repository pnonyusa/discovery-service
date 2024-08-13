package com.sandabekery.customer_service.api.client.response;

import com.sandabekery.customer_service.api.client.model.CustomerData;
import com.sandabekery.customer_service.api.client.model.SandaCustomerData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerResponse {

    private List<CustomerData> data;

    public CustomerResponse(CustomerData data){
        this.data = new ArrayList<>();
        this.data.add(data);
    }
}
