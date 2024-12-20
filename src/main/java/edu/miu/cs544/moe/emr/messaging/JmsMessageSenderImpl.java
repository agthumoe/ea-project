package edu.miu.cs544.moe.emr.messaging;

import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.helper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JmsMessageSenderImpl implements JmsMessageSender {
    @Value("${app.messaging.queue}")
    private String destination;
    private final JmsTemplate jmsTemplate;
    private final Mapper mapper;

    @Override
    public void send(MessageCreator messageCreator) {
        jmsTemplate.send(destination, messageCreator);
    }
}
