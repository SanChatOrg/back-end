package com.sanchat.api;

import com.sanchat.api.dto.DogDTO;
import com.sanchat.api.mapper.DogMapper;
import com.sanchat.api.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class DogTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DogMapper dogMapper;

//    @Test
//    void dogInsertTest() {
//        for (int i = 0; i < 10; i++) {
//            DogDTO dogDTO = DogDTO.builder()
//                    .dogName("test" + i)
//                    .dogBreed("Jindo")
//                    .dogGender('M')
//                    .birthDate(LocalDate.now())
//                    .isNeutered(true)
//                    .userNo(1L)
//                    .build();
//
//            System.out.println(dogDTO);
//            dogMapper.insertDog(dogDTO);
//        }
//    }

//    @Test
//    void testSelectDog() {
//        System.out.println(dogMapper.getDogs(1L));
//    }

}
