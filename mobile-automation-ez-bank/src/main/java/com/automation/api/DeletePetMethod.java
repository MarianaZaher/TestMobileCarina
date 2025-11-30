package com.automation.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

/**
 * DELETE /pet/{petId} - Deletes a pet from the Petstore
 * Carina function: AbstractApiMethodV2 - provides HTTP request/response handling
 */
@Endpoint(url = "https://petstore.swagger.io/v2/pet/${pet_id}", methodType = HttpMethodType.DELETE)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class DeletePetMethod extends AbstractApiMethodV2 {

    public DeletePetMethod() {
        // No base_url placeholder needed - using absolute URL
    }

    /**
     * Sets the pet ID for deletion
     * Carina function: replaceUrlPlaceholder() - dynamically replaces URL placeholders
     */
    public void setPetIdToDelete(long petId) {
        replaceUrlPlaceholder("pet_id", String.valueOf(petId));
    }
}
