package com.app.dto.UserRelatedFeature;

import com.app.models.ProductOwner;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ProductOwner}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOwnerDTO implements Serializable {
    String firstName;
    String lastName;
    String email;
}