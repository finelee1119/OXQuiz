package com.example.OXQuiz.service;

import com.example.OXQuiz.dto.OXQuizDto;
import com.example.OXQuiz.entity.OXQuiz;
import com.example.OXQuiz.repository.OXQuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OXQuizService {
    private final OXQuizRepository oxQuizRepository;

    public OXQuizService(OXQuizRepository oxQuizRepository) {
        this.oxQuizRepository = oxQuizRepository;
    }

    // 등록 : 퀴즈 등록
    public void insertOXQuiz(OXQuizDto dto) {
        OXQuiz oxQuiz = dto.fromOXQuizDto(dto);
        oxQuizRepository.save(oxQuiz);
    }

    public OXQuizDto getOneQuiz(Long id) {
        return oxQuizRepository.findById(id)
                .map(OXQuizDto::fromOXQuizEntity)
                .orElse(null);
    }

    //목록 표시 : 퀴즈 목록 표시
    public List<OXQuizDto> showQuizList() {
        return oxQuizRepository.findAll()
                .stream()
                .map(OXQuizDto::fromOXQuizEntity)
                .toList();
    }

    // 갱신 : 퀴즈 갱신
    public void update(OXQuizDto dto) {
        OXQuiz oxQuiz = dto.fromOXQuizDto(dto);
        oxQuizRepository.save(oxQuiz);
    }

    // 삭제 : 퀴즈 삭제
    public void delete(Long id) {
        oxQuizRepository.deleteById(id);
    }

    // 게임 : 게임 실시
    public OXQuizDto play(Long id) {
        return oxQuizRepository.findById(id)
                .map(OXQuizDto::fromOXQuizEntity)
                .orElse(null);
    }

    // 사용자에게 랜덤 퀴즈를 제공하는 메소드
    public OXQuizDto getRandomQuiz() {
        List<OXQuiz> quizList = oxQuizRepository.findAll();
        if (!quizList.isEmpty()) {
            int randomIndex = new Random().nextInt(quizList.size());
            OXQuiz randomQuiz = quizList.get(randomIndex);
            return OXQuizDto.fromOXQuizEntity(randomQuiz);
        } else {
            // 등록된 퀴즈가 없는 경우
            return null;
        }
    }

    // 사용자가 선택한 정답을 확인하여 정답 여부를 반환하는 메소드
    public boolean check(Long id, String userAnswer) {
        Optional<OXQuiz> optionalQuiz = oxQuizRepository.findById(id);
        if (optionalQuiz.isPresent()) {
            OXQuiz quiz = optionalQuiz.get();
            String correctAnswer = quiz.getAnswer();
            return correctAnswer.equalsIgnoreCase(userAnswer);
        } else {
            // ID에 해당하는 퀴즈가 없는 경우에 대한 처리
            return false;
        }
    }
}
