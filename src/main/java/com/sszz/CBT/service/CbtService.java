package com.sszz.CBT.service;

import com.sszz.CBT.domain.WrittenTestVO;

import java.util.List;

public interface CbtService {
    WrittenTestVO save(WrittenTestVO w);
    void delete(Integer questionId);
    WrittenTestVO getQuestion(Integer questionId);
    List<WrittenTestVO> getAllQuestion();
}
