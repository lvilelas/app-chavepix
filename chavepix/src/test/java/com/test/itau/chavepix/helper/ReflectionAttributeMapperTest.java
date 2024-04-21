package com.test.itau.chavepix.helper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectionAttributeMapperTest {

    @Test
    void testGetFieldValue() throws NoSuchFieldException, IllegalAccessException {
        // Given
        TestObject testObject = new TestObject();
        testObject.setName("John");
        testObject.setAge(30);

        ReflectionAttributeMapper mapper = new ReflectionAttributeMapper();

        // When
        Object nameValue = mapper.getFieldValue(testObject, "name");
        Object ageValue = mapper.getFieldValue(testObject, "age");

        // Then
        assertEquals("John", nameValue);
        assertEquals(30, ageValue);
    }

    // Classe de teste para simular um objeto com campos
    static class TestObject {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
