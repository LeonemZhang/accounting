package com.leonem.accounting.common.convertor;

import cn.hutool.json.JSONUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

/**
 * @author 张宗涵
 * @date 2023/9/20
 */
@Converter
public class CvtListObject implements AttributeConverter<List<Object>/*java类型*/, String/*数据库类型*/> {
    @Override
    public String convertToDatabaseColumn(List<Object> javaValue) {
        return javaValue == null ? null : JSONUtil.toJsonStr(javaValue);
    }

    @Override
    public List<Object> convertToEntityAttribute(String dbValue) {
        return dbValue == null ? null : JSONUtil.toList(dbValue, Object.class);
    }
}
