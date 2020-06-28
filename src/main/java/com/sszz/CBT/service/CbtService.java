package com.sszz.CBT.service;

import com.sszz.CBT.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    String getSCondiName(String s);
    String getHCondiName(String s);

    Boolean scoringSave(CbtHistVO cbtHistVO);

    String getScore(CbtHistVO cbtHistVO);

    Long getQuestionCnt(CbtHistVO cbtHistVO);

    List<CbtHistVO> getCbtHistList(String email);

    CbtHistVO getCbtHistVO(String cbtHistId);

    List<WrittenTestVO> getICQuestionList(CbtHistVO cbtHistVO);

    List<QuesDabVO> getICUserDapList(CbtHistVO cbtHistVO, List<WrittenTestVO> icQuestionList);

    Boolean commentSave(CommentVO commentVO);

    ArrayList<ArrayList<CommentVO>> getCommentByQuesDabVO(List<QuesDabVO> quesDabList);

    //Integer getNextQuestionId();

    //TEST
    public void saveHist();

}
