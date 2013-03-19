package org.zechariahs.toodledoapi.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.lang.reflect.Type;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.hamcrest.core.IsAnything;
import org.zechariahs.toodledoapi.Utility;
import org.zechariahs.toodledoapi.gson.ToodleDoContextDeserializer;
import org.zechariahs.toodledoapi.gson.ToodleDoFolderDeserializer;
import org.zechariahs.toodledoapi.gson.ToodleDoResponseDeserializer;
import org.zechariahs.toodledoapi.gson.ToodleDoTaskDeserializer;
import org.zechariahs.toodledoapi.pojo.ToodleDoContext;
import org.zechariahs.toodledoapi.pojo.ToodleDoFolder;
import org.zechariahs.toodledoapi.pojo.ToodleDoResponse;
import org.zechariahs.toodledoapi.pojo.ToodleDoTask;
import org.zechariahs.toodledoapi.pojo.ToodleDoUser;

public class ToodleDoModel
{

     protected final static String TOKEN = "api51340d2d1e980";
     protected final static String APPID = "JToodledo";
     
     protected ToodleDoUser m_oToodleDoUser = new ToodleDoUser();
     
     public static ToodleDoFolder addFolder(ToodleDoUser a_oUser, ToodleDoFolder a_oFolder)
     {
    	 String sURL = "http://api.toodledo.com/2/folders/add.php?";
         
    	 ToodleDoFolder newFolder = new ToodleDoFolder();
    	 
         Gson gson = new Gson();
         
         Hashtable<String, String> htParameters = new Hashtable<String, String>();     
         
         htParameters.put("key", a_oUser.getKey());
         htParameters.put("name", a_oFolder.getName());
         
         StringBuffer sbResp = makeRequest(sURL, htParameters);
         System.out.println(sbResp.toString());
         
         gson = new GsonBuilder()
              .registerTypeAdapter(ToodleDoTask.class, new ToodleDoTaskDeserializer())
              .create();
              
         try
         {
        	 Type collectionType = new TypeToken<List<ToodleDoFolder>>(){}.getType();
        	 List<ToodleDoFolder> lstFolders = (List<ToodleDoFolder>)gson.fromJson(sbResp.toString(), collectionType);
        	 newFolder = lstFolders.get(0);
         }
         catch(JsonSyntaxException e)
         {
        	 // Probably caused by an error so only one object is returned instead of an array.
        	 newFolder = gson.fromJson(sbResp.toString(), org.zechariahs.toodledoapi.pojo.ToodleDoFolder.class);
         }
         
         return newFolder;
     }
     
     public static ToodleDoFolder deleteFolder(ToodleDoUser a_oUser, ToodleDoFolder a_oFolder)
     {
    	 String sURL = "http://api.toodledo.com/2/folders/delete.php?";
         
    	 ToodleDoFolder deletedFolder = new ToodleDoFolder();
    	 
         Gson gson = new Gson();
         
         Hashtable htParameters = new Hashtable();     
         
         htParameters.put("key", a_oUser.getKey());
         htParameters.put("id", "" + a_oFolder.getId());
         
         StringBuffer sbResp = makeRequest(sURL, htParameters);
         System.out.println(sbResp.toString());
         
         gson = new GsonBuilder()
              .registerTypeAdapter(ToodleDoTask.class, new ToodleDoTaskDeserializer())
              .create();
              
         try
         {
        	 Type collectionType = new TypeToken<List<ToodleDoFolder>>(){}.getType();
        	 List<ToodleDoFolder> lstFolders = (List<ToodleDoFolder>)gson.fromJson(sbResp.toString(), collectionType);
        	 deletedFolder = lstFolders.get(0);
         }
         catch(JsonSyntaxException e)
         {
        	 // Probably caused by an error so only one object is returned instead of an array.
        	 deletedFolder = gson.fromJson(sbResp.toString(), org.zechariahs.toodledoapi.pojo.ToodleDoFolder.class);
         }
         
         return deletedFolder;
     }
     
     /**
      * Uses the ToodleDo API to update the given folder.  The API only provides
      * the ability to update a folder's name, private flag or archived flag.
      * 
      * @param a_oUser
      * @param a_oFolder
      * @return the updated folder.
      */
     public static ToodleDoFolder editFolder(ToodleDoUser a_oUser, ToodleDoFolder a_oFolder)
     {
    	 System.out.println("Editing folder...");
    	 
    	 String sURL = "http://api.toodledo.com/2/folders/edit.php?";
         
    	 ToodleDoFolder editedFolder = new ToodleDoFolder();
    	 
         Gson gson = new Gson();
         
         Hashtable<String, String> htParameters = new Hashtable<String, String>();     
         
         htParameters.put("key", a_oUser.getKey());
         htParameters.put("id", "" + a_oFolder.getId());
         
         if(a_oFolder.isNameDirty())
         {
        	 htParameters.put("name", a_oFolder.getName());
         }
         
         if(a_oFolder.isPrivateFolderDirty())
         {
        	 htParameters.put("private", "" + a_oFolder.getPrivateFolder());
         }
         
         if(a_oFolder.isArchiveDirty())
         {
        	 htParameters.put("archived", "" + a_oFolder.getArchive());
         }
         
         StringBuffer sbResp = makeRequest(sURL, htParameters);
         //System.out.println(sbResp.toString());
         
         gson = new GsonBuilder()
              .registerTypeAdapter(ToodleDoTask.class, new ToodleDoTaskDeserializer())
              .create();
              
         try
         {
        	 Type collectionType = new TypeToken<List<ToodleDoFolder>>(){}.getType();
        	 List<ToodleDoFolder> lstFolders = (List<ToodleDoFolder>)gson.fromJson(sbResp.toString(), collectionType);
        	 editedFolder = lstFolders.get(0);
         }
         catch(JsonSyntaxException e)
         {
        	 // Probably caused by an error so only one object is returned instead of an array.
        	 editedFolder = gson.fromJson(sbResp.toString(), org.zechariahs.toodledoapi.pojo.ToodleDoFolder.class);
         }
         
         return editedFolder;
     }
     
     
     public static ToodleDoTask addTask(ToodleDoUser a_oUser, ToodleDoTask a_oTask)
     {
          String sURL = "http://api.toodledo.com/2/tasks/add.php?";
          
          Gson gson = new Gson();
          String sJSON = gson.toJson(a_oTask);
          
          System.out.println("JSON: " + sJSON);
          
          Hashtable htParameters = new Hashtable();     
          
          htParameters.put("key", a_oUser.getKey());
          htParameters.put("tasks", sJSON);
          
          StringBuffer sbResp = makeRequest(sURL, htParameters);
          System.out.println(sbResp.toString());
          
          gson = new GsonBuilder()
               .registerTypeAdapter(ToodleDoTask.class, new ToodleDoTaskDeserializer())
               .create();
               
          Type collectionType = new TypeToken<List<ToodleDoTask>>(){}.getType();
          List<ToodleDoTask> lstTasks = (List<ToodleDoTask>)gson.fromJson(sbResp.toString(), collectionType);
          
          return lstTasks.get(0);
          
     }
     
     public static List<ToodleDoResponse> deleteTasks(ToodleDoUser a_oUser, List<ToodleDoTask> a_lstTasks)
     {
    	 
    	 String sURL = "http://api.toodledo.com/2/tasks/delete.php?";
         
    	 int[] arrTaskId = new int[a_lstTasks.size()];
    	 
    	 for(int x = 0; x < a_lstTasks.size(); x++)
    	 {
    		 arrTaskId[x] = Integer.parseInt(a_lstTasks.get(x).getId());
    	 }
    	 
         Gson gson = new Gson();
         String sJSON = gson.toJson(arrTaskId);
         
         System.out.println("JSON: " + sJSON);
         
         Hashtable htParameters = new Hashtable();     
         
         htParameters.put("key", a_oUser.getKey());
         htParameters.put("tasks", sJSON);
         
         StringBuffer sbResp = makeRequest(sURL, htParameters);
         System.out.println(sbResp.toString());     
         
         gson = new GsonBuilder()
              .registerTypeAdapter(ToodleDoResponse.class, new ToodleDoResponseDeserializer())
              .create();
              
         Type collectionType = new TypeToken<List<ToodleDoResponse>>(){}.getType();
         List<ToodleDoResponse> lstResponses = (List<ToodleDoResponse>)gson.fromJson(sbResp.toString(), collectionType);
         
         return lstResponses;
    	 
     }
     
     public static List<ToodleDoTask> addTasks(ToodleDoUser a_oUser, List<ToodleDoTask> a_lstTasks)
     {
          String sURL = "http://api.toodledo.com/2/tasks/add.php?";
          
          List<ToodleDoTask> lstToReturn = new ArrayList<ToodleDoTask>();
          
          int iEnd = 0;
          
          System.out.println("Adding " + a_lstTasks.size() + " tasks.");
          
          if(a_lstTasks != null && a_lstTasks.size() > 49)
          {
               for(int iBegin = 0; iBegin < a_lstTasks.size(); iBegin += 49)
               {
                    
                    iEnd = iBegin + 49;
                    
                    if(iEnd > a_lstTasks.size())
                    {
                         iEnd = a_lstTasks.size() - 1;
                    }
                    
                    System.out.println("Adding tasks " + iBegin + " through " + iEnd);
                    
                    List<ToodleDoTask> lstSplit = a_lstTasks.subList(iBegin, iEnd);
                    
                    Gson gson = new Gson();
                    String sJSON = gson.toJson(lstSplit);
                    
                    System.out.println("JSON: " + sJSON);
                    
                    Hashtable htParameters = new Hashtable();     
                    
                    htParameters.put("key", a_oUser.getKey());
                    htParameters.put("tasks", sJSON);
                    
                    StringBuffer sbResp = makeRequest(sURL, htParameters);
                    System.out.println(sbResp.toString());
                    
                    gson = new GsonBuilder()
                         .registerTypeAdapter(ToodleDoTask.class, new ToodleDoTaskDeserializer())
                         .create();
                         
                    Type collectionType = new TypeToken<List<ToodleDoTask>>(){}.getType();
                    
                    try
                    {
                         lstToReturn.addAll((List<ToodleDoTask>)gson.fromJson(sbResp.toString(), collectionType));
                    }
                    catch(NullPointerException npe)
                    {
                         
                    }
                    
               }
          }
          else
          {
          
               if(a_lstTasks != null && a_lstTasks.size() > 0)
               {
                    Gson gson = new Gson();
                    String sJSON = gson.toJson(a_lstTasks);
                    
                    System.out.println("JSON: " + sJSON);
                    
                    Hashtable htParameters = new Hashtable();     
                    
                    htParameters.put("key", a_oUser.getKey());
                    htParameters.put("tasks", sJSON);
                    
                    StringBuffer sbResp = makeRequest(sURL, htParameters);
                    System.out.println(sbResp.toString());
                    
                    gson = new GsonBuilder()
                         .registerTypeAdapter(ToodleDoTask.class, new ToodleDoTaskDeserializer())
                         .create();
                         
                    Type collectionType = new TypeToken<List<ToodleDoTask>>(){}.getType();
                    
                    lstToReturn = (List<ToodleDoTask>)gson.fromJson(sbResp.toString(), collectionType);
               }
          }
          
          return lstToReturn;
          
     }
     
     public static ToodleDoTask editTask(ToodleDoUser a_oUser, ToodleDoTask a_oTask)
     {
          String sURL = "http://api.toodledo.com/2/tasks/edit.php?";
          
          Gson gson = new Gson();
          String sJSON = gson.toJson(a_oTask);
          
          System.out.println("JSON: " + sJSON);
          
          Hashtable htParameters = new Hashtable();     
          
          htParameters.put("key", a_oUser.getKey());
          htParameters.put("tasks", sJSON);
          
          StringBuffer sbResp = makeRequest(sURL, htParameters);
          System.out.println(sbResp.toString());     
          
          gson = new GsonBuilder()
               .registerTypeAdapter(ToodleDoTask.class, new ToodleDoTaskDeserializer())
               .create();
               
          Type collectionType = new TypeToken<List<ToodleDoTask>>(){}.getType();
          List<ToodleDoTask> lstTasks = (List<ToodleDoTask>)gson.fromJson(sbResp.toString(), collectionType);
          
          return lstTasks.get(0);
          
     }
     
     public static List<ToodleDoTask> getAllTasks(ToodleDoUser a_oUser)
     {
          String sURL = "http://api.toodledo.com/2/tasks/get.php";
          
          Hashtable htParameters = new Hashtable();
          
          htParameters.put("key", a_oUser.getKey());
          htParameters.put("fields", "folder,context,duedate,priority");
          
          StringBuffer sbResp = makeRequest(sURL, htParameters);
          
          Gson gson = new GsonBuilder()
               .registerTypeAdapter(ToodleDoTask.class, new ToodleDoTaskDeserializer())
               .create();
               
          Type collectionType = new TypeToken<List<ToodleDoTask>>(){}.getType();
          List<ToodleDoTask> lstTasks = (List<ToodleDoTask>)gson.fromJson(sbResp.toString(), collectionType);
          
          System.out.println("Total Tasks:" + lstTasks.size());

          return lstTasks;

     }
     
     public static List<ToodleDoContext> getContexts(ToodleDoUser a_oUser)
     {
          String sURL = "http://api.toodledo.com/2/contexts/get.php?";
          
          Hashtable htParameters = new Hashtable();
          
          htParameters.put("key", a_oUser.getKey());
          
          StringBuffer sbResp = makeRequest(sURL, htParameters);
          
          Gson gson = new GsonBuilder()
               .registerTypeAdapter(ToodleDoFolder.class, new ToodleDoContextDeserializer())
               .create();
               
          Type collectionType = new TypeToken<List<ToodleDoContext>>(){}.getType();
          List<ToodleDoContext> lstContexts = (List<ToodleDoContext>)gson.fromJson(sbResp.toString(), collectionType);
          
          System.out.println("Total Contexts:" + lstContexts.size());

          return lstContexts;

     }
     
     public static List<ToodleDoFolder> getFolders(ToodleDoUser a_oUser)
     {
          String sURL = "http://api.toodledo.com/2/folders/get.php?";
          
          Hashtable htParameters = new Hashtable();
          
          htParameters.put("key", a_oUser.getKey());
          
          StringBuffer sbResp = makeRequest(sURL, htParameters);
          
          Gson gson = new GsonBuilder()
               .registerTypeAdapter(ToodleDoFolder.class, new ToodleDoFolderDeserializer())
               .create();
               
          Type collectionType = new TypeToken<List<ToodleDoFolder>>(){}.getType();
          List<ToodleDoFolder> lstFolders = (List<ToodleDoFolder>)gson.fromJson(sbResp.toString(), collectionType);
          
          System.out.println("Total Folders:" + lstFolders.size());

          return lstFolders;

     }
     
     /**
      * Used to initialize a user's session.  As of 2013-03-03, a session is good for 4 hours.
      * 
      * @param a_oUser
      * @return
      */
     public static ToodleDoUser getToken(ToodleDoUser a_oUser)
     {
    	 System.out.println("Looking up token...");
         
          
    	 
    	 
    	 String sURL = "http://api.toodledo.com/2/account/token.php?";
          
          Hashtable htParameters = new Hashtable();
          
          htParameters.put("userid", a_oUser.getUserid());
          htParameters.put("appid", APPID);
          htParameters.put("sig", Utility.createMD5Hash(a_oUser.getUserid() + TOKEN));   
          
          StringBuffer sbResp = makeRequest(sURL, htParameters);
          
          Gson gson = new Gson();
          a_oUser = gson.fromJson(sbResp.toString(), org.zechariahs.toodledoapi.pojo.ToodleDoUser.class);
          
          if(a_oUser.getToken() != null && a_oUser.getToken().length() > 0)
          {
        	  
        	  System.out.println("Token:" + a_oUser.getToken());
        	  
        	  // Set token to expire in 4 hours. (4 hours = 1000 milliseconds * 60 seconds * 60 minutes * 4 hours)
        	  a_oUser.setTokenExpiration(System.currentTimeMillis() + 1000 * 60 * 60 * 4);
        	  
        	  a_oUser = populateKey(a_oUser);
        	  
          }
          
          return a_oUser;
          
     }
     
     /**
      * Retrieves a user's UserId and stores it in the ToodleDoUser object
      * returned by this function.  Note - The user's UserId never changes
      * so it should be stored in perpetuity.
      * 
      * @param a_oUser
      * @return
      */
     public static ToodleDoUser accountLookup(String a_sEmail, String a_sPassword)
     {
          System.out.println("Looking up account...");
          
          String sURL = "http://api.toodledo.com/2/account/lookup.php?";
          
          Hashtable htParameters = new Hashtable();
          
          htParameters.put("appid", APPID);
          htParameters.put("email", a_sEmail);
          htParameters.put("pass", a_sPassword);
          htParameters.put("sig", Utility.createMD5Hash(a_sEmail + TOKEN));
          
          StringBuffer sbResp = makeRequest(sURL, htParameters);
          
          Gson gson = new Gson();
          ToodleDoUser a_oUser = gson.fromJson(sbResp.toString(), org.zechariahs.toodledoapi.pojo.ToodleDoUser.class);
          
          return a_oUser;
          
     }
     
     public static ToodleDoUser populateKey(ToodleDoUser a_oUser)
     {
    	 
    	 System.out.println(a_oUser.getPassword() + " - " + TOKEN + " - " + a_oUser.getToken());
    	 
    	 String sKey = Utility.createMD5Hash(a_oUser.getPassword()) + TOKEN + a_oUser.getToken();
         sKey = Utility.createMD5Hash(sKey);
         
         System.out.println(sKey);
         
         a_oUser.setKey(sKey);
         
         return a_oUser;
     }
     
     public static StringBuffer makeRequest(String a_sURL, Hashtable<?, ?> a_htParameters)
     {
          
          String sURL = a_sURL;
          String sParameters = "";
          boolean bFirstParameter = true;
          StringBuffer sbResp = new StringBuffer();
          
          Enumeration enumKeys = a_htParameters.keys();
          
          while(enumKeys.hasMoreElements())
          {
               String sParameterName = (String)enumKeys.nextElement();
               String sParameter = (String)a_htParameters.get(sParameterName);
               
               if(bFirstParameter)
               {
                    bFirstParameter = false;
               }
               else
               {
                    sParameters += "&";
               }
               
               try
               {
                    //sURL += sParameterName + "=" + URLEncoder.encode(sParameter, "UTF-8");
                    sParameters += sParameterName + "=" + URLEncoder.encode(sParameter, "UTF-8");
               }
               catch (UnsupportedEncodingException e)
               {
                    System.out.println(e.getLocalizedMessage());
               }
               
          }

          System.out.println("Calling URL: " + sURL);
          System.out.println("With Parameters: " + sParameters);

          try
          {
               URL oURL = new URL(sURL);
               URLConnection urlCon = oURL.openConnection();
               
               urlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
               
               urlCon.setDoOutput(true);
               urlCon.setDoInput(true);
               OutputStreamWriter wr = new OutputStreamWriter(urlCon.getOutputStream());
               wr.write(sParameters);
               wr.flush();
               wr.close();
               
               BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
               
               String inputLine;

               while ((inputLine = in.readLine()) != null) 
               {
                    sbResp.append(inputLine);
               }
               
               in.close();
               
               System.out.println("Response: " + sbResp.toString());
               
          }
          catch (MalformedURLException e)
          {
                System.out.println(e.getLocalizedMessage());
          }
          catch (IOException e)
          {
                System.out.println(e.getLocalizedMessage());
          }    
          
          System.out.println("\n");
          
          return sbResp;
          
     }
     
}
