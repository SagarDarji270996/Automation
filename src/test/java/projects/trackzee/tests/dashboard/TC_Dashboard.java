package projects.trackzee.tests.dashboard;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.CustomTestListener;
import projects.trakzee.configurations.base.TestBase;
import projects.trakzee.configurations.config.ConfigLoader;
import projects.trakzee.web.pages.loginAndHome.POM_HomePage;
import projects.trakzee.web.pages.loginAndHome.POM_LoginPage;

@Listeners({ CustomTestListener.class })
public class TC_Dashboard extends TestBase {


}