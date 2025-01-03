package com.sanchat.api.mapper;

import com.sanchat.api.dto.DogDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DogMapper {
    @Insert("insert into dog(dog_name, dog_breed, dog_gender, birth_date, is_neutered, user_no)" +
            "values(" +
            "#{dogDTO.dogName}, " +
            "#{dogDTO.dogBreed}, " +
            "#{dogDTO.dogGender}, " +
            "#{dogDTO.birthDate}, " +
            "#{dogDTO.isNeutered}, " +
            "#{dogDTO.userNo})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "dogNo", keyColumn = "dog_no")
    void insertDog(@Param("dogDTO") DogDTO dogDTO);

//    List<DogDTO> getDogs(Long userNo);
}
