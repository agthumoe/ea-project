package edu.miu.cs544.moe.emr.messaging;

import edu.miu.cs544.moe.emr.domain.visit.Visit;
import jakarta.jms.MapMessage;
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

    public static MessageCreator map(Visit visit, MessageType type) {
        return (session) -> {
            MapMessage message = session.createMapMessage();
            message.setString("type", type.toString());
            message.setString("patient.uuid", visit.getPatient().getUuid());
            message.setString("patient.name", visit.getPatient().getName());
            message.setString("patient.age", visit.getPatient().getYears());
            message.setString("doctor.uuid", visit.getDoctor().getUuid());
            message.setString("doctor.name", visit.getDoctor().getName());
            message.setString("doctor.speciality", visit.getDoctor().getSpeciality());
            message.setString("visit.uuid", visit.getUuid());
            message.setString("visit.date", visit.getVisitDate().toString());
            message.setString("visit.complaint", visit.getComplaint());
            message.setString("visit.provisionalDiagnosis", visit.getProvisionalDiagnosis());
            message.setString("visit.finalDiagnosis", visit.getFinalDiagnosis());
            return message;
        };
    }
}
