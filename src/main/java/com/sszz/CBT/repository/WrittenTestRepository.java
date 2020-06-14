package com.sszz.CBT.repository;

import com.sszz.CBT.domain.WrittenTestVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WrittenTestRepository extends JpaRepository<WrittenTestVO,Integer> {

    public List<WrittenTestVO> findBySubject(String subject);
    public List<WrittenTestVO> findByHoecha(String hoecha);
    //public WrittenTestVO findByQuestionId(String hoecha);

}
