package com.example.OXQuiz.controller;

import com.example.OXQuiz.dto.OXQuizDto;
import com.example.OXQuiz.service.OXQuizService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/quiz")
@Slf4j
public class OXQuizController {
    private final OXQuizService oxQuizService;

    public OXQuizController(OXQuizService oxQuizService) {
        this.oxQuizService = oxQuizService;
    }

    // 목록 표시 기능 : 등록된 퀴즈의 목록을 표시합니다.
    @GetMapping()
    public String show(Model model) {
        List<OXQuizDto> oxQuizDtoList = oxQuizService.showQuizList();
        model.addAttribute("oxQuizDto", oxQuizDtoList);
        return "quiz"; // html파일
    }

    // 등록 기능 : 퀴즈를 등록합니다.
    @GetMapping("insert")
    public String insertOXQuiz(Model model) {
        model.addAttribute("oxQuizDto", new OXQuizDto());
        return "insertForm";
    }

    @PostMapping("insert")
    public String insert(@Valid @ModelAttribute("oxQuizDto")OXQuizDto dto,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("========Validation Error");
            return "insertForm";
        }

        oxQuizService.insertOXQuiz(dto);
        return "redirect:/quiz";
    }

    // 갱신 기능 : 등록된 퀴즈를 갱신합니다.
    @GetMapping("update")
    public String updateOXQuiz(@RequestParam("updateId")Long id, Model model) {
        OXQuizDto oxQuizDto = oxQuizService.getOneQuiz(id);
        model.addAttribute("oxQuizDto", oxQuizDto);
        return "updateForm";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute("oxQuizDto")OXQuizDto dto,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("========Validation Error");
            return "updateForm";
        }

        oxQuizService.update(dto);
        return "redirect:/quiz";
    }

    // 삭제 기능 : 등록된 퀴즈를 삭제합니다.
    @PostMapping("delete/{deleteId}")
    public String delete(@PathVariable("deleteId")Long id) {
        oxQuizService.delete(id);
        return "redirect:/quiz";
    }

    // 게임 기능 : 퀴즈 게임을 실시합니다.
    @GetMapping("/play")
    public String play(Model model) {
        // 랜덤으로 퀴즈를 선택하여 가져옴
        OXQuizDto randomQuiz = oxQuizService.getRandomQuiz();
        model.addAttribute("quizContent", randomQuiz.getQuiz());
        model.addAttribute("quizId", randomQuiz.getId());
        return "play"; // 퀴즈 플레이 템플릿으로 이동
    }

    // 게임 기능 : 퀴즈 답을 체크합니다.
    @PostMapping("/check")
    public String check(@RequestParam("playId") Long id,
                        @RequestParam("userAnswer") String userAnswer,
                        Model model) {
        boolean isCorrect = oxQuizService.check(id, userAnswer);
        model.addAttribute("isCorrect", isCorrect);
        return "check"; // 정답을 표시하는 check.html 페이지로 이동
    }
}
