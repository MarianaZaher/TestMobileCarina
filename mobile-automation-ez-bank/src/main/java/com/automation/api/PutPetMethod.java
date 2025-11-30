package com.automation.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import java.util.HashMap;
import java.util.Map;

/**
 * PUT /pet - Updates an existing pet in the Petstore
 * Carina function: AbstractApiMethodV2 - provides HTTP request/response handling
 */
@Endpoint(url = "https://petstore.swagger.io/v2/pet", methodType = HttpMethodType.PUT)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PutPetMethod extends AbstractApiMethodV2 {

    public PutPetMethod() {
        // No base_url placeholder needed - using absolute URL
    }

    /**
     * Builds and sets request body to update pet data
     * Carina function: setRequestBody() - sets request body directly without template files
     */
    public void buildUpdatePetRequest(long petId, String petName, String newStatus) {
        Map<String, Object> petRequest = new HashMap<>();
        petRequest.put("id", petId);
        petRequest.put("name", petName);
        petRequest.put("status", newStatus);
        
        Map<String, Object> category = new HashMap<>();
        category.put("id", 1);
        category.put("name", "Dogs");
        petRequest.put("category", category);
        
        setRequestBody(petRequest);
    }

    /**
     * Builds default update pet request
     */
    public void buildDefaultUpdateRequest() {
        buildUpdatePetRequest(1001, "Fluffy Updated", "sold");
    }
}
