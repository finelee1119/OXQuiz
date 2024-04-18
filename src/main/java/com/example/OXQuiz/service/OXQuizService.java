package com.example.OXQuiz.service;

import com.example.OXQuiz.dto.OXQuizDto;
import com.example.OXQuiz.repository.OXQuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OXQuizService {
    private final OXQuizRepository oxQuizRepository;

    public OXQuizService(OXQuizRepository oxQuizRepository) {
        this.oxQuizRepository = oxQuizRepository;
    }

    public List<OXQuizDto> showQuizList() {

    }

    // 등록 : 퀴즈 등록
    // 갱신 : 퀴즈 갱신
    // 삭제 : 퀴즈 삭제
    // 목록 표시 : 퀴즈 목록 표시
    // 게임 : 게임 실시
}
