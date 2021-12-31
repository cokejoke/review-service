package eu.cokejoke.review.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime date;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String artist;
    private String reviewer;
    private String smartlink;
    private String hashtags;

}
