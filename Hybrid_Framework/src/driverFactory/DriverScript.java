package driverFactory;

import org.junit.Test;
import org.testng.Reporter;

import commonFunctions.FunctionLibrary;
import utilies.ExcelFileUtil;

public class DriverScript extends FunctionLibrary {
	String inputpath ="./FileInput/DataEngine.xlsx";
	String outputpath ="./FileOutput/HybridResults.xlsx";
	String TCSheet ="TestCases";
	String TSSheet ="TestSteps";
	@Test
	public void startTest() throws Throwable
	{
		boolean res = false;
		String tcres ="";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//Row count in TCSheet,TSSheet
		int TCCount = xl.rowCount(TCSheet);
		int TSCount = xl.rowCount(TSSheet);
		Reporter.log(TCCount+"  "+TSCount,true);
		//Itrate all rows in TCSheet
		for(int i=1;i<=TCCount;i++)
		{
			//Read moudle status cell
			String moudle_status= xl.getCellData(TCSheet, i, 2);
			if (moudle_status.equalsIgnoreCase("Y"))
			{
			// read tcid cell
				String tcid = xl.getCellData(TCSheet, i, 0);
				//Itrate all rows in TSSheet
				for(int j=i;j<=TSCount;j++)
				{
					String tsid = xl.getCellData(TSSheet, j, 0);
					if (tcid.equalsIgnoreCase(tsid))
					{
						String keyword = xl.getCellData(TSSheet, j, 3);
						if (keyword.equalsIgnoreCase("adminLogin"))
						{
							String para1 = xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
							res= FunctionLibrary.pbLogin(para1, para2);
						}
						else if (keyword.equalsIgnoreCase("branchCreation"))
						{
							String para1 = xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
							String para3 = xl.getCellData(TSSheet, j, 7);
							String para4 = xl.getCellData(TSSheet, j, 8);
							String para5 = xl.getCellData(TSSheet, j, 9);
							String para6 = xl.getCellData(TSSheet, j, 10);
							String para7 = xl.getCellData(TSSheet, j, 11);
							String para8 = xl.getCellData(TSSheet, j, 12);
							String para9 = xl.getCellData(TSSheet, j, 13);
							FunctionLibrary.PbBranches();
							res=FunctionLibrary.PbBranchCreation(para1, para2, para3, para4, para5, para6, para7, para8, para9);
							
						}
						else if(keyword.equalsIgnoreCase(" BranchUpdate"))
						{
							String para1 = xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
							String para5 = xl.getCellData(TSSheet, j, 9);
							String para6 = xl.getCellData(TSSheet, j, 10);
							FunctionLibrary.PbBranches();
							res= FunctionLibrary.pbBranchUpdate(para1, para2, para5, para6);
						}
						else if (keyword.equalsIgnoreCase("adminLogout"))
						{
							res= FunctionLibrary.Pblogout();	
						}
						String tsres = "";
						if(res)
						{
							tsres ="Pass";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						}
						else
						{
							tsres = "Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						}
						tcres=tsres;
					}
				}
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			}
			else
			{
				//write as blocked into TCSheet which test case flag to N
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
				
			}
		}
		
	}

}






