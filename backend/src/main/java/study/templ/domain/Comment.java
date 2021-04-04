package study.templ.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comment {
    @Id
    @Column(name="comment_id")
    Long commentid;
}
