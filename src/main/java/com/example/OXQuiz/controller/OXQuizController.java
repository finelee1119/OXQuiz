package com.example.OXQuiz.controller;

import com.example.OXQuiz.dto.OXQuizDto;
import com.example.OXQuiz.service.OXQuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/oxquiz")
@Slf4j
public class OXQuizController {
    private final OXQuizService oxQuizService;

    public OXQuizController(OXQuizService oxQuizService) {
        this.oxQuizService = oxQuizService;
    }

    // 등록 : 퀴즈 등록
    // 갱신 : 퀴즈 갱신
    // 삭제 : 퀴즈 삭제
    
    // 목록 표시 : 퀴즈 목록 표시
    @GetMapping("show")
    public String showQuizList(Model model) {
        List<OXQuizDto> oxQuizDtoList = oxQuizService.showQuizList();
        model.addAttribute("oxQuizDto", oxQuizDtoList);
        return "showQuiz"; // html파일
    }

    // 게임 : 게임 실시
}
