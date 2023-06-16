package com.github.kothapet.SpringCloudStreamKafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.springframework.core.env.Environment;


@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
@ActiveProfiles("test")
public class EventControllerTests {

    @Autowired
    private InputDestination input;

    @Autowired
    private OutputDestination output;

    @Autowired
    private EventController eventController;

    @MockBean
    private StringService stringService;

    //@Autowired
    //private Environment environment;

    @Test
    public void testEventController() {
        // Configure the mock behavior
        when(stringService.upperService("test message")).thenReturn("TEST MESSAGE X");
        
        // Send a test message to the input destination
        input.send(new GenericMessage<>("test message"));

        // Process the message
        //eventController.upperController().apply("");

        // Retrieve the transformed message from the output destination
        String transformedMessage = new String(output.receive().getPayload());

        // Assert the transformed message
        assertThat(transformedMessage).isEqualTo("TEST MESSAGE X");
    }
 
    /*
    @Test
    public void upperControllerTest() {
    	try (ConfigurableApplicationContext context = new SpringApplicationBuilder(
    				TestChannelBinderConfiguration.getCompleteConfiguration(
    						DemoApplication.class))
    			.web(WebApplicationType.NONE)
    			.profiles("test")
    			.run("--spring.cloud.function.definition=upperController")) {
            // Configure the mock behavior
            when(stringService.upperService("test message")).thenReturn("TEST MESSAGE X");
            
    		InputDestination source = context.getBean(InputDestination.class);
    		OutputDestination target = context.getBean(OutputDestination.class);
    		source.send(new GenericMessage<String>("test message"));
    		assertThat(new String(target.receive().getPayload())).isEqualTo("TEST MESSAGE X");
    	}
    }
    */
}
