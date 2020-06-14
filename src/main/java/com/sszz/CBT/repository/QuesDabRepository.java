package com.sszz.CBT.repository;

import com.sszz.CBT.domain.CbtHistVO;
import com.sszz.CBT.domain.QuesDabVO;
import com.sszz.CBT.domain.WrittenTestVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuesDabRepository extends JpaRepository<QuesDabVO,Integer> {

    public List<QuesDabVO> findByCbtHistId(CbtHistVO cbtHistVO);
    public Long countByCbtHistId(CbtHistVO cbtHistVO);

}
