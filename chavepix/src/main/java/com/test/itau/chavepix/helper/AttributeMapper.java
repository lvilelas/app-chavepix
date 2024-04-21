package com.test.itau.chavepix.helper;



public interface AttributeMapper {

    Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException;

}
