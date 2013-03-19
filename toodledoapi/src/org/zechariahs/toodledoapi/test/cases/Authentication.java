package org.zechariahs.toodledoapi.test.cases;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.zechariahs.toodledoapi.model.ToodleDoModel;
import org.zechariahs.toodledoapi.pojo.ToodleDoUser;

/**
 * Tests the authentication mechanism provided from ToodleDoAPI.
 * 
 * Preparation:
 * 1. Read toodledotest.properties file from user's home directory
 * 2. Read toodledo.email and toodledo.password from properties file
 * 3. Store properties in System Properties
 * 
 * Testing:
 * 1. accountLookup - Retrieve user's userId.
 * 2. getToken - Retrieve user's session token.  Note - This test stores the user's
 * session token in the System Properties under.  Retrieve it using the following 
 * key: toodledo.token.  Also, toodledo.key.
 * 
 * @author zechariahs
 *
 */
public class Authentication {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		String path = System.getProperty("user.home") + File.separator + "toodledotest.properties";
		Properties toodledoTestProperties = new Properties();
		
		try
		{
			FileInputStream propertiesFile = new FileInputStream(path);
			toodledoTestProperties.load(propertiesFile);
			propertiesFile.close();
		}
		catch(Exception e)
		{
			fail("Error reading properties file from " + path + ". Error: " + e.getLocalizedMessage());
		}
		
		System.getProperties().put("toodledo.email", toodledoTestProperties.getProperty("toodledo.email"));
		System.getProperties().put("toodledo.password", toodledoTestProperties.getProperty("toodledo.password"));
		
		// This is only here for development of the test code.  Typically the property would never
		// be in a *real* version of the properties file.
		System.getProperties().put("toodledo.token", toodledoTestProperties.getProperty("toodledo.token"));
		
		assertTrue("Email address not defined in properties file.", System.getProperty("toodledo.email") != null && System.getProperty("toodledo.email").length() > 0);
		assertTrue("Password not defined in properties file.", System.getProperty("toodledo.password") != null && System.getProperty("toodledo.password").length() > 0);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() 
	{
		ToodleDoUser user = ToodleDoModel.accountLookup(System.getProperty("toodledo.email"), System.getProperty("toodledo.password"));
		assertTrue("UserId not retrieved.", user.getUserid() != null && user.getUserid().length() > 0);
		
		if(System.getProperty("toodledo.token") == null || System.getProperty("toodledo.token").length() <= 0)
		{
			user = ToodleDoModel.getToken(user);
			assertTrue("Token not retrieved.", user.getToken() != null && user.getToken().length() > 0);
			System.getProperties().put("toodledo.token", user.getToken());
		}
		else
		{
			user.setPassword(System.getProperty("toodledo.password"));
			user.setToken(System.getProperty("toodledo.token"));
			user = ToodleDoModel.populateKey(user);
		}
		
		
		System.getProperties().put("toodledo.key", user.getKey());
		
		
	}

}
