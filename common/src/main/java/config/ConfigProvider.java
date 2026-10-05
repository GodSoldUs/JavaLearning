package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {
    public static TestConfig CONFIG =
            ConfigFactory.create(TestConfig.class);

    private ConfigProvider() {}

    public static void printConfigParams() {
        System.out.println("CONFIGURATION");
        System.out.println("URL: " + CONFIG.Url());
        System.out.println("TIMEOUT: " + CONFIG.timeout());
        System.out.println("LOGGING_MODE: " + CONFIG.loggingMode());
        System.out.println("START_NAME: " + CONFIG.startName());
        System.out.println("START_PRICE " + CONFIG.startPrice());

    }
}
