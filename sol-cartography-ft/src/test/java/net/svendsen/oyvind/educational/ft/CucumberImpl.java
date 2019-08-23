package net.svendsen.oyvind.educational.ft;

import cucumber.api.junit.Cucumber;
import org.junit.runners.model.InitializationError;

public class CucumberImpl extends Cucumber {

    public CucumberImpl(Class clazz) throws InitializationError {
        super(clazz);
        System.setProperty("spring.profiles.active", "ft");
    }
}
