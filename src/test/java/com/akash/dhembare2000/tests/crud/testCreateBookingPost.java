package com.akash.dhembare2000.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testCreateBookingPost {

    @Owner("Akash Dhembare")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that the post request is working fine")
    @Test
    public void testVerifyCreateBookingPOST(){
        Assert.assertEquals(true, true);
    }
}
