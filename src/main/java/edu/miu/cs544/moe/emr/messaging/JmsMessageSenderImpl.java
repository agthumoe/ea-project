package edu.miu.cs544.moe.emr.messaging;

import edu.miu.cs544.moe.emr.domain.investigation.Investigation;
import edu.miu.cs544.moe.emr.domain.treatment.Treatment;
import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.domain.vital.Vital;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JmsMessageSenderImpl implements JmsMessageSender {
    @Value("${app.messaging.queue}")
    private String destination;
    private final JmsTemplate jmsTemplate;

    @Override
    public void send(Visit visit, MessageType type) {
        jmsTemplate.convertAndSend(destination, visit);
    }

    @Override
    public void send(Vital vital, MessageType type) {
        jmsTemplate.convertAndSend(destination, vital);
    }

    @Override
    public void send(Investigation investigation, MessageType type) {
        jmsTemplate.convertAndSend(destination, investigation);
    }

    @Override
    public void send(Treatment treatment, MessageType type) {
        jmsTemplate.convertAndSend(destination, treatment);
    }
}
