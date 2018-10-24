package spring;

import models.dto.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateWithMappingTest {

    RestTemplate app = new RestTemplate();

    @DisplayName("Example with Java POJO object mapping and basic assertion.")
    @Test
    void userInfoResponse() {

        User test_user = app.getForObject("https://api.github.com/users/varomir", User.class);

        assertAll("User under test",
                () -> assertEquals(test_user.getLogin(), "Varomir"),
                () -> assertEquals(test_user.getId(), new Integer(9216215)),
                () -> assertEquals(test_user.getNode_id(), "MDQ6VXNlcjkyMTYyMTU="),
                () -> assertEquals(test_user.getAvatar_url(), "https://avatars3.githubusercontent.com/u/9216215?v=4"),
                () -> assertEquals(test_user.getGravatar_id(), ""),
                () -> assertEquals(test_user.getUrl(), "https://api.github.com/users/Varomir")
        );
    }
}
