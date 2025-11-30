package com.automation.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;
import java.util.HashMap;
import java.util.Map;

@Endpoint(url = "${base_url}/users", methodType = HttpMethodType.POST)
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class PostUserMethod extends AbstractApiMethodV2 {

    public PostUserMethod() {
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
    }

    /**
     * Builds and sets request body with user data from feature inputs
     * Carina function: setRequestBody() - sets request body directly without template files
     */
    public void buildUserRequest(String firstName, String lastName, String email) {
        Map<String, Object> userRequest = new HashMap<>();
        userRequest.put("firstName", firstName);
        userRequest.put("lastName", lastName);
        userRequest.put("email", email);
        userRequest.put("active", true);
        userRequest.put("createdDate", System.currentTimeMillis());
        
        setRequestBody(userRequest);
    }

    /**
     * Builds default user request
     */
    public void buildDefaultUserRequest() {
        buildUserRequest("John", "Doe", "john.doe@example.com");
    }
}
