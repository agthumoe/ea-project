package edu.miu.cs544.moe.emr.domain.note;

import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.shared.model.MutableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notes")
@Setter
@Getter
@NoArgsConstructor
public class Note extends MutableModel {
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private NoteType type;
    @Column(name = "content", nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Visit visit;
}
