package com.test.itau.chavepix.helper;

import java.lang.reflect.Field;


public class ReflectionAttributeMapper implements AttributeMapper{
    @Override
    public Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
