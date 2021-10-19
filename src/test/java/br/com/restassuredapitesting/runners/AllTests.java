package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.booking.tests.GetBookingTest;
import br.com.restassuredapitesting.tests.ping.tests.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.AllTests.class)
@Suite.SuiteClasses({
        GetBookingTest.class,
        GetPingTest.class
})
public class AllTests {
}
