package com.akash.dhembare2000.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount=0;
    private static int maxRetryCount=3; // Set the max try count

    @Override
    public boolean retry(ITestResult result){
        if(retryCount<maxRetryCount) {
            retryCount++;
            return true; // Retry the test
        }
        return false;
    }
}
