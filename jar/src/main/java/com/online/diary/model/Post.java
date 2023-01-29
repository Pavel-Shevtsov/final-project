package com.online.diary.model;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "Post",uniqueConstraints = @UniqueConstraint(name = "user_post",columnNames = {"id","user_id"}))
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false,length = 30)
    private String tag;
    @DateTimeFormat
    @Column(nullable = false)
    private LocalDate publicationDate;
    @Column()
    private LocalDate lastUpdateDate;
    @Column(unique = true,nullable = false)
    private String text;
    @Column
    private Boolean isPrivate;
    @Column
    private Boolean isApprovedForPublication;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

}
