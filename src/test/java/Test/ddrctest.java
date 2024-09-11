package Test;

import utils.ExcelUtils;

import java.awt.AWTException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Page.Ddrcpage;

public class ddrctest extends Baseclassddrc {

    @DataProvider(name = "ddrcData")
    public Object[][] getData() {
        String excelPath = "C:\\Users\\HP\\OneDrive\\Desktop//gokuldata.xlsx";
        String sheetName = "Sheet1";
        ExcelUtils excelUtils = new ExcelUtils(excelPath, sheetName);
        return excelUtils.getData();
    }

    @Test(dataProvider = "ddrcData")
    public void test1(String name, String mobileNo, String email, String gender, String dob, String location, String filePath) throws InterruptedException, AWTException {
        Ddrcpage ddrc = new Ddrcpage(driver);

        ddrc.alertclose();
        ddrc.bookbtn();
        Thread.sleep(3000);
        // Start form submission
//        ddrc.uploadPrescription();
        ddrc.name(name);
        ddrc.mobileNumber(mobileNo);
        ddrc.email(email);
        ddrc.gendervalue(gender);
        ddrc.date(dob);
        Thread.sleep(2000);
        ddrc.uploadPrescriptionbtn(filePath);
        Thread.sleep(2000);
        ddrc.location(location);
        ddrc.checkbox();

        // Submit the form and navigate back to the previous page
        ddrc.submitAndNavigateBack();
    }
}
