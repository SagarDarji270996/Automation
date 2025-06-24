package projects.trakzee.configurations.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class SoftAssertion {
    private static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

	public void setSoftAssert() {
        System.out.println("Initializing SoftAssert for Test: " + Thread.currentThread().getId());
        softAssert.set(new SoftAssert());
    }

    public static SoftAssert getSoftAssert() {
        return softAssert.get();
    }


    public void closeSoftAssert() {
		getSoftAssert().assertAll();
    }

    public void clearSoftAssert() {
        softAssert.remove();
    }
}
