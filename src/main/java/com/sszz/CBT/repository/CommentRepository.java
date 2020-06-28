package com.sszz.CBT.repository;

import com.sszz.CBT.domain.CbtHistVO;
import com.sszz.CBT.domain.CommentVO;
import com.sszz.CBT.domain.QuesDabVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentVO, Integer> {

    public ArrayList<CommentVO> findByQuesDabId(QuesDabVO quesDabVO); //객체를 줘야해요ㅜ^ㅜ QuesDabVO이걸로 주세요

}
