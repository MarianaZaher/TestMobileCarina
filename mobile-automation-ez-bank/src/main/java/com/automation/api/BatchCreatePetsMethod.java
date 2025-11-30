package com.automation.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * POST /pet - Batch operation to create multiple pets
 * Demonstrates Carina's ability to handle batch/bulk operations
 */
@Endpoint(url = "https://petstore.swagger.io/v2/pet", methodType = HttpMethodType.POST)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class BatchCreatePetsMethod extends AbstractApiMethodV2 {

    public BatchCreatePetsMethod() {
        // No base_url placeholder needed - using absolute URL
    }

    /**
     * Creates a batch of pets
     * Carina function: setRequestBody() - sets request body with array of objects
     */
    public void buildBatchPetRequests(int numberOfPets) {
        List<Map<String, Object>> petsList = new ArrayList<>();
        
        for (int i = 0; i < numberOfPets; i++) {
            Map<String, Object> pet = new HashMap<>();
            pet.put("id", 2000 + i);
            pet.put("name", "Pet_" + (i + 1));
            pet.put("status", "available");
            
            Map<String, Object> category = new HashMap<>();
            category.put("id", 1);
            category.put("name", "Batch");
            pet.put("category", category);
            
            petsList.add(pet);
        }
        
        // For batch operations, we'll send the array directly
        setRequestBody(petsList);
    }

    /**
     * Builds default batch request with 3 pets
     */
    public void buildDefaultBatchRequest() {
        buildBatchPetRequests(3);
    }
}
