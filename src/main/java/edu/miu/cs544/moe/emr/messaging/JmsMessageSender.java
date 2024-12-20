package edu.miu.cs544.moe.emr.messaging;

import edu.miu.cs544.moe.emr.domain.investigation.Investigation;
import edu.miu.cs544.moe.emr.domain.treatment.Treatment;
import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.domain.vital.Vital;

public interface JmsMessageSender {
    void send(Visit visit, MessageType type);
    void send(Vital vital, MessageType type);
    void send(Investigation investigation, MessageType type);
    void send(Treatment treatment, MessageType type);
}
