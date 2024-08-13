package com.sandabekery.customer_service.sandabakery.model.jpa;

import jakarta.persistence.*;
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
@Entity
@Table(name = "SANDA_CUSTOMER")
public class CustomerJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String firstName;
    private String surname;
    private String email;
}
