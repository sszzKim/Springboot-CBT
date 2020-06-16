package com.sszz.CBT.repository;

import com.sszz.CBT.domain.CommentVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentVO, Integer> {
}
