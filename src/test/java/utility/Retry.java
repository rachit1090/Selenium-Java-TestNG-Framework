package utility;

import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import commontests.BaseTest;

public class Retry implements IRetryAnalyzer
{
	private static ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);
	private static final int maxCount = 2;
	@Override
	public boolean retry(ITestResult result) 
	{
		Throwable cause = result.getThrowable();
		 if (count.get() < maxCount  && (cause instanceof NoSuchElementException
		           || cause instanceof TimeoutException
		           || cause instanceof StaleElementReferenceException))
	        {
	            System.out.println("Retrying " + result.getName()
	                + " (Attempt " + (count.get() + 1) + " of " + maxCount + ")");
	            count.set(count.get() + 1);
	            return true;
	        }
	        count.set(0); // reset for next test method on this thread
	        return false;
	}

}
