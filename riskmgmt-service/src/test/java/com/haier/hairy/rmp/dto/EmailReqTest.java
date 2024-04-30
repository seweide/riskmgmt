package com.haier.hairy.rmp.dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmailReqTest {

    @Test
    public void builder() {
        EmailReq emailReq = EmailReq.builder().build();
        assertEquals(emailReq.getAppId(), "RIS");
    }
}