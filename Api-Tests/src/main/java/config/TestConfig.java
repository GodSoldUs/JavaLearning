package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Config.Key;

@LoadPolicy(LoadType.MERGE)
@Sources(
        {
                "classpath:config.properties"
        }
)

public interface TestConfig extends Config {

    @Key("URL")
    String Url();

    @Key("TIMEOUT")
    long timeout();

    @Key("LOGGING_MODE")
    String loggingMode();

    @Key("LOGIN")
    String login();

    @Key("PASSWORD")
    String password();

    @Key("START_NAME")
    String startName();

    @Key("START_PRICE")
    double startPrice();
}
