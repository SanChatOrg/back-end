package com.sanchat.api;

import com.sanchat.api.dto.WalkDTO;
import com.sanchat.api.mapper.WalkMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalDate;

@SpringBootTest
public class WalkTest {
    @Autowired
    private WalkMapper walkMapper;

//    @Test
//    public void testWalkInsert() {
//        for (int i = 0; i < 20; i++) {
//            WalkDTO walkDTO = WalkDTO.builder()
//                    .walkDistance(1.1 + i)
//                    .walkDate(LocalDate.now())
//                    .walkTime(new Time(i))
//                    .build();
//
//            System.out.println(walkDTO);
//
//            walkMapper.insertWalk(walkDTO);
//        }
//    }
//
//    @Test
//    public void testWalkGetAll() {
//        System.out.println(walkMapper.getAllWalk());
//    }
//
//    @Test
//    public void testWalkDelete() {
//        walkMapper.deleteWalk(1L);
//    }


}
