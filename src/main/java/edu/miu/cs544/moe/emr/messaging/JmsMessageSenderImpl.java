package edu.miu.cs544.moe.emr.messaging;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JmsMessageSenderImpl implements JmsMessageSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(JmsMessageSenderImpl.class);
    @Value("${app.messaging.queue}")
    private String destination;
    private final JmsTemplate jmsTemplate;

    @Override
    public void send(MessageCreator messageCreator) {
        LOGGER.info("Sending message...");
        jmsTemplate.send(destination, messageCreator);
    }
}
