package study.templ.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import study.templ.domain.Comment;
import study.templ.domain.Team;
import study.templ.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@WebAppConfiguration
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository ;



    @Test
    public void createComment(){

        //given

        //when

        //then

    }



    @Test
    public void deleteComment(){

        //given



        //when

        //then

    }

}