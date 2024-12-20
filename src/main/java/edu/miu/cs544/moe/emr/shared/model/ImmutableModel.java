package edu.miu.cs544.moe.emr.shared.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class ImmutableModel extends Model {
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;
}
