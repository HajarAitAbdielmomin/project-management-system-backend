package com.app.dto.UserRelatedFeature;

import com.app.models.ProductOwner;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ProductOwner}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class ProductOwnerDTO extends UserDTO{

}