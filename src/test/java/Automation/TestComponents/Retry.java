package Automation.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//IRetryAnalyzeris interface

public class Retry implements IRetryAnalyzer{
	
	//fail test come here
	
	int count = 0;
	int maxTry = 1;
	

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
