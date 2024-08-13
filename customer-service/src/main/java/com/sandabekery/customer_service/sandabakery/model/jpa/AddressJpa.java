package com.sandabekery.customer_service.sandabakery.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;


@AllArgsConstructor
@Setter
@Getter
@FieldNameConstants
@Entity
@Table(name = "SANDA_ADDRESS")
public class AddressJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String street;
    private String houseNumber;
    private String zipCode;
    private Long customerUid;
}
