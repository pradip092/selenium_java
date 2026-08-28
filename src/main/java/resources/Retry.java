package resources;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Retry extends Base implements IRetryAnalyzer {
    
    // Thread-safe storage for retry counts and final results
    private static final Map<String, Integer> retryCounts = new ConcurrentHashMap<>();
    private static final Map<String, Boolean> finalResults = new ConcurrentHashMap<>();
    private static final int MAX_RETRY = 1;
    
    @Override
    public boolean retry(ITestResult result) {
        String testKey = getTestKey(result);
        int count = retryCounts.getOrDefault(testKey, 0);
        
        if (count < MAX_RETRY) {
            retryCounts.put(testKey, count + 1);
            System.out.println("[RETRY] " + result.getName() + " | Attempt: " + (count + 1));
            
            // Your existing refresh logic
            try {
                if (getDriver() != null) {
                    getDriver().navigate().refresh();
                }
            } catch (Exception e) {
                System.out.println("Refresh failed: " + e.getMessage());
            }
            return true;
        }
        
        // Store final result after all retries exhausted
        finalResults.put(testKey, result.isSuccess());
        System.out.println("[FINAL] " + result.getName() + " | Result: " 
            + (result.isSuccess() ? "PASSED" : "FAILED"));
        
        return false;
    }
    
    private String getTestKey(ITestResult result) {
        return result.getTestClass().getRealClass().getName() + "." 
               + result.getMethod().getMethodName();
    }
    
    // CRITICAL: This method checks if a test ultimately passed (used by DependencyFixListener)
    public static boolean isUltimatelyPassed(String className, String methodName) {
        String searchKey = className + "." + methodName;
        for (String key : finalResults.keySet()) {
            if (key.contains(searchKey) && finalResults.get(key)) {
                System.out.println("[CHECK] " + methodName + " ultimately PASSED");
                return true;
            }
        }
        return false;
    }
}