package com.sandabekery.customer_service.api.client.command;

import com.sandabekery.customer_service.api.client.model.SandaCustomerData;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SandaCustomerCommand {
    private SandaCustomerData data;
}
