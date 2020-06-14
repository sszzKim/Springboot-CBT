package com.sszz.CBT.service;

import com.sszz.CBT.domain.*;
import com.sszz.CBT.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

@Service
public class CbtServiceImpl implements CbtService{

    @Autowired
    WrittenTestRepository writtenTestRepository;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    HoechaRepository hoechaRepository;

    @Autowired
    CbtHistRepository cbtHistRepository;

    @Autowired
    QuesDabRepository quesDabRepository;

    //Date today = new Date("2020-06-09");
    //SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");

    public WrittenTestVO save(WrittenTestVO w) {
        return writtenTestRepository.save(w);
    }

    public void delete(Integer questionId) {
        writtenTestRepository.deleteById(questionId);
    }

    public WrittenTestVO getQuestion(Integer questionId) {
        return writtenTestRepository.findById(questionId).get();
        //findbyId()는 리턴타입이 Optional이기 때문에 get() 함수로 조회하시면 됩니다.
    }

    public List<WrittenTestVO> getAllQuestion() {
        return writtenTestRepository.findAll();
    }

    @Override
    public Boolean checkLogin(String email, String pwd) {

       //LoginVO loginVO = loginRepository.findById(email).orElseGet(LoginVO::new);

        Optional<LoginVO> loginVO = loginRepository.findById(email);

        if(!loginVO.isPresent()) return false; //찾는 email이 없으면
        else if(loginVO.get().getPwd().equals(pwd)) return true; //찾는 email이 있으면 비번 확인
             else return false;

    }

    @Override
    public List<SubjectVO> getAllSubject() { return subjectRepository.findAll(); }

    @Override
    public List<HoechaVO> getAllHoecha() { return hoechaRepository.findAll(); }

    @Override
    public List<WrittenTestVO> getSCondiQuestion(String s) { return writtenTestRepository.findBySubject(s); }

    @Override
    public List<WrittenTestVO> getHCondiQuestion(String s) { return writtenTestRepository.findByHoecha(s); }

    @Override
    public String getSCondiName(String s) {
        return subjectRepository.findById(s).get().getName();
    }

    @Override
    public String getHCondiName(String s) {
        return hoechaRepository.findById(s).get().getName();
    }

    @Override
    public Boolean scoringSave(CbtHistVO cbtHistVO) {
        System.out.println("냐옹_____222_____"+cbtHistVO.toString());
        cbtHistRepository.save(cbtHistVO);
        return true;
    }

    @Override
    public String getScore(CbtHistVO cbtHistVO) {

        int count = 0;

        //cbtHistId에 맞는 note_quesdab_tb 가져오기 / 문제ID,답
        Map<Integer, String> user = new HashMap<>();
        List<QuesDabVO>  quesDabVOS = quesDabRepository.findByCbtHistId(cbtHistVO);
        for(QuesDabVO quesDabVO :quesDabVOS){
            user.put(Integer.parseInt(quesDabVO.getQuestionId()),quesDabVO.getDap());
        }

        //questionId에 맞는 답가져와서 비교하기
        for(Integer questionId : user.keySet() ){
            Optional<WrittenTestVO>  writtenTestVO = writtenTestRepository.findById(questionId);
            if(writtenTestVO.get().getAnswer().equals(user.get(questionId))){ //답이 맞다면
                count++;
            }
        }

        return String.valueOf(count);
    }

    @Override
    public Long getQuestionCnt(CbtHistVO cbtHistVO) {
        return quesDabRepository.countByCbtHistId(cbtHistVO);
    }

    @Override
    public List<CbtHistVO> getCbtHistList(String email) {
        return cbtHistRepository.findByEmailOrderByCbtHistIdDesc(email);
    }

    @Override
    public CbtHistVO getCbtHistVO(String cbtHistId) {
        return cbtHistRepository.findById(Integer.parseInt(cbtHistId)).get();
    }

    @Override
    public List<WrittenTestVO> getICQuestionList(CbtHistVO cbtHistVO) {

        List<WrittenTestVO> list = new ArrayList<>();

        //cbtHistId에 맞는 note_quesdab_tb 가져오기 / 문제ID,답
        Map<Integer, String> user = new HashMap<>();
        List<QuesDabVO>  quesDabVOS = quesDabRepository.findByCbtHistId(cbtHistVO);
        for(QuesDabVO quesDabVO :quesDabVOS){
            user.put(Integer.parseInt(quesDabVO.getQuestionId()),quesDabVO.getDap());
        }

        //questionId에 맞는 답가져와서 비교해서 틀린문제면 list 추가
        for(Integer questionId : user.keySet() ){
            Optional<WrittenTestVO>  writtenTestVO = writtenTestRepository.findById(questionId);
            if(!(writtenTestVO.get().getAnswer().equals(user.get(questionId)))){ //답이 틀리다면
                list.add(writtenTestVO.get());
            }
        }

        return list;
    }

    @Override
    public List<QuesDabVO> getICUserDapList(CbtHistVO cbtHistVO, List<WrittenTestVO> icQuestionList) {

        //문제 ID만 추출
        List<String> questionIdList = new ArrayList<>();

        //리턴할 QuesDabVO List
        List<QuesDabVO> quesDabVOList = new ArrayList<>();

        for(WrittenTestVO question :icQuestionList){
            questionIdList.add(Integer.toString(question.getQuestionid()));
            quesDabVOList.add(quesDabRepository.findByCbtHistIdAndQuestionId(cbtHistVO,Integer.toString(question.getQuestionid())));
        }
        return quesDabVOList;
    }

    /*@Override
    public Integer getNextQuestionId() {
        return cbtHistRepository.findNextQuestionId();
    }*/

    //TEST
    @Override
    public void saveHist() {

        String  day = "2016-11-22"; // 형식을 지켜야 함
        java.sql.Date d = java.sql.Date.valueOf(day);

        CbtHistVO frist = new CbtHistVO("suji20th@naver.com", d);

        QuesDabVO quesDabVO1 = new QuesDabVO("1","5",frist);
        QuesDabVO quesDabVO2 = new QuesDabVO("2","5",frist);
        QuesDabVO quesDabVO3 = new QuesDabVO("3","5",frist);

        System.out.println("냐옹__________"+frist.toString());

        cbtHistRepository.save(frist);
        quesDabRepository.save(quesDabVO1);
        quesDabRepository.save(quesDabVO2);
        quesDabRepository.save(quesDabVO3);

    }

}
