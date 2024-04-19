package com.example.OXQuiz.repository;

import com.example.OXQuiz.entity.OXQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OXQuizRepository extends JpaRepository<OXQuiz, Long> {
    // 퀴즈 플레이 시작을 누르면 랜덤으로 퀴즈가 뜨게
    // 쿼리를 잘 만들어보자 - 랜덤 함수

    @Query(value = "SELECT * FROM oxquiz ORDER BY RAND() LIMIT 1", nativeQuery = true)
    OXQuiz findRandomQuiz();
}
