package com.sszz.CBT.service;

import com.sszz.CBT.domain.LoginVO;
import com.sszz.CBT.domain.WrittenTestVO;
import com.sszz.CBT.repository.LoginRepository;
import com.sszz.CBT.repository.WrittenTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CbtServiceImpl implements CbtService{

    @Autowired
    WrittenTestRepository writtenTestRepository;

    @Autowired
    LoginRepository loginRepository;

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


}
