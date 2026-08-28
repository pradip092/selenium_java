package customListeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import resources.Retry;

public class DependencyFixListener implements IInvokedMethodListener {
    
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Check if test is being skipped due to dependency failure
        if (testResult.getStatus() == ITestResult.SKIP) {
            Throwable throwable = testResult.getThrowable();
            
            if (throwable instanceof SkipException) {
                String message = throwable.getMessage();
                System.out.println("[SKIP DETECTED] " + method.getTestMethod().getMethodName());
                
                // Check if skip is due to dependency
                if (message != null && message.contains("depends on")) {
                    String dependencyMethod = extractDependencyMethod(message);
                    String className = method.getTestMethod().getTestClass().getRealClass().getSimpleName();
                    
                    System.out.println("[DEPENDENCY CHECK] Looking for: " + dependencyMethod);
                    
                    // SCENARIO 1: Dependency PASSED on retry → DON'T SKIP
                    if (dependencyMethod != null && 
                        Retry.isUltimatelyPassed(className, dependencyMethod)) {
                        
                        System.out.println("[FIX APPLIED] ✓ Re-enabling: " 
                            + method.getTestMethod().getMethodName());
                        
                        // Reset status to allow test to run
                        testResult.setStatus(ITestResult.STARTED);
                        testResult.setThrowable(null);
                    }
                    // SCENARIO 2: Dependency FAILED after retry → KEEP SKIP
                    else {
                        System.out.println("[FIX NOT APPLIED] ✗ Dependency failed. Keeping skip.");
                    }
                }
            }
        }
    }
    
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // No action needed
    }
    
    private String extractDependencyMethod(String skipMessage) {
        // Parse message like: "depends on not successfully finished methods: [[LoginTest.loginTest]]"
        if (skipMessage.contains("[[") && skipMessage.contains("]]")) {
            int start = skipMessage.indexOf("[[") + 2;
            int end = skipMessage.indexOf("]]");
            String fullMethod = skipMessage.substring(start, end);
            
            if (fullMethod.contains(".")) {
                return fullMethod.substring(fullMethod.lastIndexOf(".") + 1);
            }
            return fullMethod;
        }
        return null;
    }
}