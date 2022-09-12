package helpers;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:src/test/resources/auth.properties"
})
public interface AuthConfig extends Config {
    String login();

    String password();
}