package org.zechariahs.toodledoapi.test.cases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.zechariahs.toodledoapi.model.ToodleDoModel;
import org.zechariahs.toodledoapi.pojo.ToodleDoFolder;
import org.zechariahs.toodledoapi.pojo.ToodleDoUser;

public class Folders {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
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
		user.setToken(System.getProperty("toodledo.token"));
		user.setKey(System.getProperty("toodledo.key"));
		
		assertTrue("Token is empty.", user.getToken() != null && user.getToken().length() > 0);
		assertTrue("Key is empty.", user.getKey() != null && user.getKey().length() > 0);
		
		ToodleDoFolder folder = new ToodleDoFolder();
		folder.setName("ToodleDoTesting Folder -- For testing purposes only (JUnit)");
		
		ToodleDoFolder createdFolder = ToodleDoModel.addFolder(user, folder);
		assertTrue("Error creating folder - " + createdFolder.getErrorCode() + ": " + createdFolder.getErrorDesc()
				, createdFolder.getErrorDesc() == null || createdFolder.getErrorDesc().length() <= 0);
		
		createdFolder.setArchive(1);
		ToodleDoFolder updatedFolder = ToodleDoModel.editFolder(user, createdFolder);
		assertTrue("Error archiving folder - " + updatedFolder.getErrorCode() + ": " + updatedFolder.getErrorDesc()
				, updatedFolder.getErrorDesc() == null || updatedFolder.getErrorDesc().length() <= 0);
		
		createdFolder.setArchive(0);
		updatedFolder = ToodleDoModel.editFolder(user, createdFolder);
		assertTrue("Error un-archiving folder - " + updatedFolder.getErrorCode() + ": " + updatedFolder.getErrorDesc()
				, updatedFolder.getErrorDesc() == null || updatedFolder.getErrorDesc().length() <= 0);
		
		createdFolder.setName("!!! ToodleDoTesting Folder -- For testing purposes only (JUnit)");
		updatedFolder = ToodleDoModel.editFolder(user, createdFolder);
		assertTrue("Error updating folder name - " + updatedFolder.getErrorCode() + ": " + updatedFolder.getErrorDesc()
				, updatedFolder.getErrorDesc() == null || updatedFolder.getErrorDesc().length() <= 0);
		
		createdFolder.setPrivateFolder(1);
		updatedFolder = ToodleDoModel.editFolder(user, createdFolder);
		assertTrue("Error privatizing folder - " + updatedFolder.getErrorCode() + ": " + updatedFolder.getErrorDesc()
				, updatedFolder.getErrorDesc() == null || updatedFolder.getErrorDesc().length() <= 0);
		
		updatedFolder = ToodleDoModel.deleteFolder(user, createdFolder);
		assertTrue("Error deleting folder - " + updatedFolder.getErrorCode() + ": " + updatedFolder.getErrorDesc()
				, updatedFolder.getDeleted() != null && updatedFolder.getDeleted().length() > 0);
		
	}

}
