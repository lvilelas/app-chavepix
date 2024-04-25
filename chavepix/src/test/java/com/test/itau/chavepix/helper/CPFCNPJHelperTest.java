package com.test.itau.chavepix.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CPFCNPJHelperTest {

    @Test
    public void testIsCNPJ() {
        assertTrue(CPFCNPJHelper.isCNPJ("03562716000170"));
        assertFalse(CPFCNPJHelper.isCNPJ("00000000000000"));
        assertFalse(CPFCNPJHelper.isCNPJ("11111111111111"));
        assertFalse(CPFCNPJHelper.isCNPJ("22222222222222"));
        assertFalse(CPFCNPJHelper.isCNPJ("33333333333333"));
        assertFalse(CPFCNPJHelper.isCNPJ("44444444444444"));
        assertFalse(CPFCNPJHelper.isCNPJ("55555555555555"));
        assertFalse(CPFCNPJHelper.isCNPJ("66666666666666"));
        assertFalse(CPFCNPJHelper.isCNPJ("77777777777777"));
        assertFalse(CPFCNPJHelper.isCNPJ("88888888888888"));
        assertFalse(CPFCNPJHelper.isCNPJ("99999999999999"));
        assertFalse(CPFCNPJHelper.isCNPJ("04.252.011/0001-11"));
        assertFalse(CPFCNPJHelper.isCNPJ("12345678901234567890"));
        assertFalse(CPFCNPJHelper.isCNPJ("04.252.011/0001-1"));
    }

    @Test
    public void testIsCPF() {
        assertTrue(CPFCNPJHelper.isCPF("28844277085"));
        assertFalse(CPFCNPJHelper.isCPF("00000000000"));
        assertFalse(CPFCNPJHelper.isCPF("11111111111"));
        assertFalse(CPFCNPJHelper.isCPF("22222222222"));
        assertFalse(CPFCNPJHelper.isCPF("33333333333"));
        assertFalse(CPFCNPJHelper.isCPF("44444444444"));
        assertFalse(CPFCNPJHelper.isCPF("55555555555"));
        assertFalse(CPFCNPJHelper.isCPF("66666666666"));
        assertFalse(CPFCNPJHelper.isCPF("77777777777"));
        assertFalse(CPFCNPJHelper.isCPF("88888888888"));
        assertFalse(CPFCNPJHelper.isCPF("99999999999"));
        assertFalse(CPFCNPJHelper.isCPF("123.456.789-08"));
        assertFalse(CPFCNPJHelper.isCPF("123456789012345"));
    }
}
