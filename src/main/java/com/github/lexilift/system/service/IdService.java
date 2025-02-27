package com.github.lexilift.system.service;

import com.github.lexilift.system.mapper.SystemMapper;
import com.github.lexilift.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdService {

    @Autowired
    private SystemMapper systemMapper;

    public String generateId() {
        String seq = systemMapper.selectSequence("sys_id");
        return DateUtil.getCurrentForID() + StringUtils.leftPad(seq, 6, "0");

    }
}
