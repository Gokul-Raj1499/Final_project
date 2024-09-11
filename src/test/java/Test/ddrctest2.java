package Test;

import org.testng.annotations.Test;

import Page.Ddrcpage;

public class ddrctest2 extends Baseclassddrc{

    @Test
    public void test2() throws InterruptedException {
        // Create an instance of the Ddrcpage class
       Ddrcpage ddrc1=new Ddrcpage(driver);
        // Close the alert
        ddrc1.alertclose();

        // Perform a search
        ddrc1.search("fever");
         Thread.sleep(3000);
        // Apply the price filter
       ddrc1.pricefilter("Low to High");
    }


}
