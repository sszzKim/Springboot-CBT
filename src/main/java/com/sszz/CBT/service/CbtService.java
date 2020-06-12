package com.sszz.CBT.service;

import com.sszz.CBT.domain.CbtHistVO;
import com.sszz.CBT.domain.HoechaVO;
import com.sszz.CBT.domain.SubjectVO;
import com.sszz.CBT.domain.WrittenTestVO;

import java.util.List;

public interface CbtService {
    WrittenTestVO save(WrittenTestVO w);
    void delete(Integer questionId);
    WrittenTestVO getQuestion(Integer questionId);
    List<WrittenTestVO> getAllQuestion();

    Boolean checkLogin(String email, String pwd);

    List<SubjectVO> getAllSubject();
    List<HoechaVO> getAllHoecha();

    List<WrittenTestVO> getSCondiQuestion(String s);
    List<WrittenTestVO> getHCondiQuestion(String s);

    Boolean scoringSave(CbtHistVO cbtHistVO);

    //TEST
    public void saveHist();

}
