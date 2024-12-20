package edu.miu.cs544.moe.emr.messaging;

import edu.miu.cs544.moe.emr.domain.visit.Visit;
import org.springframework.jms.core.MessageCreator;

public interface JmsMessageSender {
    void send(MessageCreator messageCreator);
}
