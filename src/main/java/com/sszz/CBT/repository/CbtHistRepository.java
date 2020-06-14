package com.sszz.CBT.repository;

import com.sszz.CBT.domain.CbtHistVO;
import com.sszz.CBT.domain.WrittenTestVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CbtHistRepository extends JpaRepository<CbtHistVO,Integer> {

    @Query( value="SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'note_cbthist_tb' AND table_schema = DATABASE( )",nativeQuery = true)
    public Integer findNextQuestionId();

}
