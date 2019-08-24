package net.svendsen.oyvind.educational;

import cucumber.api.CucumberOptions;
import net.svendsen.oyvind.educational.ft.CucumberImpl;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(CucumberImpl.class)
@CucumberOptions(
        features = "src/test/resources/features/planet_position_convert_job.feature",
        glue = "net.svendsen.oyvind.educational.ft"
)
@ActiveProfiles("ft")
@Ignore
public class CucumberTest {

}