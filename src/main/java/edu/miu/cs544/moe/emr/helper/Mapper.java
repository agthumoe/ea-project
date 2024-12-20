package edu.miu.cs544.moe.emr.helper;

import edu.miu.cs544.moe.emr.domain.user.User;
import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.messaging.MessageType;
import edu.miu.cs544.moe.emr.security.UserPrincipal;
import jakarta.jms.MapMessage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.jms.core.MessageCreator;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper extends ModelMapper {
    public <T> List<T> map(List<?> list, Class<T> clazz) {
        return list.stream().map(d -> this.map(d, clazz)).collect(Collectors.toList());
    }

    public <T> Page<T> map(Page<?> page, Class<T> clazz) {
        List<T> list = this.map(page.getContent(), clazz);
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    public UserPrincipal map(User user) {
        return new UserPrincipal(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().toString())));
    }

    public MessageCreator map(Visit visit, MessageType type) {
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
