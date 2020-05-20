package com.sszz.CBT.repository;

import com.sszz.CBT.domain.WrittenTestVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WrittenTestRepositoryTest {

    @Autowired
    WrittenTestRepository writtenTestRepository;

    @Test
    public void findAll() throws Exception {

        List<WrittenTestVO> WrittenTests = new ArrayList<>();
        writtenTestRepository.findAll().forEach(e -> WrittenTests.add(e));

        System.out.println("★★★★★★★"+WrittenTests.toString());

    }

}