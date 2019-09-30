package com.jameel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;

  
@SpringBootApplication
@EnableConfigurationProperties
@EnableJms
@ComponentScan(basePackages = "com.jameel")
public class SpringactivemqApplication  {
 
 
    @Bean
    public JmsListenerContainerFactory<?> myFactory(
        ConnectionFactory connectionFactory,
        DefaultJmsListenerContainerFactoryConfigurer configurer) {
      DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

      // lambda function
      factory.setErrorHandler(t -> System.out.println("An error has occurred in the transaction now"));

      configurer.configure(factory, connectionFactory);
      return factory;
    }
     
    public static void main(String[] args) {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(SpringactivemqApplication.class, args);
		/*
		 * SpringApplication.run(SpringactivemqApplication.class, args);
		 * 
		 * JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		 * 
		 * System.out.println("Sending a JMS message.");
		 * jmsTemplate.convertAndSend("sampleQueue", "Hello world123!");
		 */
    }
    
    
    // Serialize message content to json using TextMessage
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
      MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
      converter.setTargetType(MessageType.TEXT);
      converter.setTypeIdPropertyName("_type");
      return converter;
    }
 
}