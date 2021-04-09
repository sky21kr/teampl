package study.templ.domain;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @NonNull
    private Integer comment_id;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @NonNull
    private LocalDateTime dateTime;

    @NonNull
    @Column(name = "comment")
    private String comment; //content(댓글내용) 의미함.


    @NonNull
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User writer;


    @NonNull
    @ManyToOne
    @JoinColumn(name = "subcomment")
    private Comment Comment1;

    @NonNull
    @OneToMany(mappedBy = "Comment1",cascade =CascadeType.ALL )
    private List <Comment> subComment =new ArrayList<>();

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_team")
    private Team target_team;

    private Integer level;

    private Boolean live;

}


