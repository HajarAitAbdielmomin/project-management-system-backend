package com.app.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@DiscriminatorValue("ADMIN")
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@NoArgsConstructor
public class Admin extends User {
}