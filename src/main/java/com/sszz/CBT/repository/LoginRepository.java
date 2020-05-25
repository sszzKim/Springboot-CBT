package com.sszz.CBT.repository;

import com.sszz.CBT.domain.LoginVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginVO,String> {
}
