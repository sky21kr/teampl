package study.templ.domain;



import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "comment_id")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @NonNull
    private Integer comment_id;

    @NonNull
    @CreatedDate
    private LocalDateTime datetime;

    @NonNull
    @Column(name = "comment")
    private String comment; //content(댓글내용) 의미함.

    @JsonManagedReference
    @NonNull
    @JoinColumn(name = "writer")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private User writer;

    @JsonIgnore
    @NonNull
    @ManyToOne
    @JoinColumn(name = "subcomment")
    private Comment Comment1;

    @NonNull
    @OneToMany(mappedBy = "Comment1",cascade =CascadeType.ALL )
    private List <Comment> subComment =new ArrayList<>();


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "target_team")
    private Team target_team;

    private Integer level;

    private Boolean live;

}


