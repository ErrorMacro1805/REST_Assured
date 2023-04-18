import app.Dto.Person;
import app.config.UsersHttpRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class TestRegistration {
    private static UsersHttpRequest usersHttpRequest;

    @BeforeAll
    public static void init() {
        usersHttpRequest = new UsersHttpRequest();
    }

    @Test
    public void testRegistrationSuccessful() {
        Person person = new Person("eve.holt@reqres.in", "pistol");
        Response response = new UsersHttpRequest().registrationSuccessful(person);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("token", notNullValue())
                .body("id", notNullValue());
    }

    @Test
    public void testRegistrationUnsuccessful() {
        String expectedErrorMessage = "Missing password";
        Response response = new UsersHttpRequest().registrationUnsuccessful(new Person("eve.holt@reqres.in"));
        response.then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo(expectedErrorMessage));
    }

    @Test
    public void testLoginSuccessful() {
        Person person = new Person("eve.holt@reqres.in", "cityslicka");
        Response response = usersHttpRequest.loginSuccessful(person);
        response.then().
                assertThat()
                .statusCode(200)
                .body("token", notNullValue());
    }
}


