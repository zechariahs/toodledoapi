package org.zechariahs.toodledoapi.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.zechariahs.toodledoapi.test.cases.Authentication;
import org.zechariahs.toodledoapi.test.cases.Folders;

@RunWith(Suite.class)
@SuiteClasses({ Authentication.class, Folders.class })
public class AllTests {

}
