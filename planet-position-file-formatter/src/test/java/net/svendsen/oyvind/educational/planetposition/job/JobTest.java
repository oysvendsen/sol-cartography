package net.svendsen.oyvind.educational.planetposition.job;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JobTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testBeans() {
        Job bean = context.getBean(Job.class);
        assertThat(bean).isNotNull();
    }

}