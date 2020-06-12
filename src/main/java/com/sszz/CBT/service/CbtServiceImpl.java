package com.sszz.CBT.service;

import com.sszz.CBT.domain.*;
import com.sszz.CBT.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

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
    public Boolean scoringSave(CbtHistVO cbtHistVO) {
        cbtHistRepository.save(cbtHistVO);
        return true;
    }

    //TEST
    @Override
    public void saveHist() {

        String  day = "2016-11-22"; // 형식을 지켜야 함
        java.sql.Date d = java.sql.Date.valueOf(day);

        CbtHistVO frist = new CbtHistVO("suji20th@naver.com", d);

        QuesDabVO quesDabVO1 = new QuesDabVO("1","5",frist);
        QuesDabVO quesDabVO2 = new QuesDabVO("2","5",frist);
        QuesDabVO quesDabVO3 = new QuesDabVO("3","5",frist);

        cbtHistRepository.save(frist);
        quesDabRepository.save(quesDabVO1);
        quesDabRepository.save(quesDabVO2);
        quesDabRepository.save(quesDabVO3);

    }

}
