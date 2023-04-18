package app.config;

import app.Dto.Person;
import io.restassured.response.Response;

public class UsersHttpRequest extends BaseHttpRequest {
    private static final String registerUrl = "api/register";
    private static final String loginUrl = "/api/login";

    public Response registrationSuccessful(Person person) {
        return getRequestSpecification()
                .when()
                .body(person)
                .post(registerUrl);


    }

    public Response registrationUnsuccessful(Person person) {
        return getRequestSpecification()
                .when()
                .body(person)
                .post(registerUrl);
    }

    public Response loginSuccessful(Person person) {
        return getRequestSpecification()
                .when()
                .body(person)
                .post(loginUrl);
    }
}
