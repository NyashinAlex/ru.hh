package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})

public interface WebDriverConfig extends Config {

    @Key("phone")
    String getPhone();

    @Key("password")
    String getPassword();

    @Key("nameUser")
    String getNameUser();
}
