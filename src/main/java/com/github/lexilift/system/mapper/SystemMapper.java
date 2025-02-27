package com.github.lexilift.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SystemMapper {

    @Select("SELECT nextval('sys_id')")
    String selectSequence(String sequenceName);
}
