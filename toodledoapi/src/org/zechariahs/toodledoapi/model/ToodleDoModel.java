package org.zechariahs.toodledoapi.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import org.zechariahs.toodledoapi.Utility;
import org.zechariahs.toodledoapi.gson.ToodleDoContextDeserializer;
import org.zechariahs.toodledoapi.gson.ToodleDoFolderDeserializer;
import org.zechariahs.toodledoapi.gson.ToodleDoTaskDeserializer;
import org.zechariahs.toodledoapi.pojo.ToodleDoContext;
import org.zechariahs.toodledoapi.pojo.ToodleDoFolder;
import org.zechariahs.toodledoapi.pojo.ToodleDoTask;
import org.zechariahs.toodledoapi.pojo.ToodleDoUser;

public class ToodleDoModel
{

     protected final String TOKEN = "api4f3137c6167e8";
     protected final String APPID = "SeapineSync";
     
     protected String m_sUserId = "td4d5294b296004";
     
     protected ToodleDoUser m_oToodleDoUser = new ToodleDoUser();
     
     public ToodleDoModel(String a_sUsername, String a_sPassword)
     {
          ToodleDoUser oTDUser = new ToodleDoUser(a_sUsername, a_sPassword);
          setToodleDoUser(oTDUser);
          authenticateUser();
     }
     
     public ToodleDoModel(ToodleDoUser a_oTDUser)
     {
          setToodleDoUser(a_oTDUser);
          authenticateUser();
     }
     
     protected void authenticateUser()
     {
          setToodleDoUser(authenticateUser(getToodleDoUser()));
     }
     
     public ToodleDoUser authenticateUser(String a_sUsername, String a_sPassword)
     {
          ToodleDoUser oTDUser = new ToodleDoUser(a_sUsername, a_sPassword);
          return authenticateUser(oTDUser);
     }
     
     public ToodleDoUser authenticateUser(ToodleDoUser a_oTDUser)
     {
          String sPassword = a_oTDUser.getPassword();
          
          if(a_oTDUser.getUserid() == null || a_oTDUser.getUserid().length() == 0)
          {
               a_oTDUser = accountLookup(a_oTDUser);
          }
          
          System.out.println("userid = " + a_oTDUser.getUserid());     
          
          if(a_oTDUser.getToken() == null || a_oTDUser.getToken().length() == 0)
          {
               a_oTDUser = getToken(a_oTDUser);
          }
          
          System.out.println("token = " + a_oTDUser.getToken());
          
          // Setting this again for safety since something seems to be wiping it out.
          a_oTDUser.setPassword(sPassword);
          
          String sKey = Utility.createMD5Hash(a_oTDUser.getPassword()) + TOKEN + a_oTDUser.getToken();
          sKey = Utility.createMD5Hash(sKey);
          a_oTDUser.setKey(sKey);
          
          return a_oTDUser;
     }
     
     public StringBuffer makeRequest(String a_sURL, Hashtable a_htParameters)
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
          
          return sbResp;
          
     }
     
     public ToodleDoTask addTask(ToodleDoTask a_oTask)
     {
          String sURL = "http://api.toodledo.com/2/tasks/add.php?";
          
          Gson gson = new Gson();
          String sJSON = gson.toJson(a_oTask);
          
          System.out.println("JSON: " + sJSON);
          
          Hashtable htParameters = new Hashtable();     
          
          htParameters.put("key", getToodleDoUser().getKey());
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
     
     public List<ToodleDoTask> addTasks(List<ToodleDoTask> a_lstTasks)
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
                    
                    htParameters.put("key", getToodleDoUser().getKey());
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
                    
                    htParameters.put("key", getToodleDoUser().getKey());
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
     
     public ToodleDoTask updateTask(ToodleDoTask a_oTask)
     {
          String sURL = "http://api.toodledo.com/2/tasks/edit.php?";
          
          Gson gson = new Gson();
          String sJSON = gson.toJson(a_oTask);
          
          
          
          System.out.println("JSON: " + sJSON);
          
          Hashtable htParameters = new Hashtable();     
          
          htParameters.put("key", getToodleDoUser().getKey());
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
     
     public List<ToodleDoTask> getAllTasks()
     {
          String sURL = "http://api.toodledo.com/2/tasks/get.php";
          
          Hashtable htParameters = new Hashtable();
          
          htParameters.put("key", getToodleDoUser().getKey());
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
     
     public List<ToodleDoContext> getContexts()
     {
          String sURL = "http://api.toodledo.com/2/contexts/get.php?";
          
          Hashtable htParameters = new Hashtable();
          
          htParameters.put("key", getToodleDoUser().getKey());
          
          StringBuffer sbResp = makeRequest(sURL, htParameters);
          
          Gson gson = new GsonBuilder()
               .registerTypeAdapter(ToodleDoFolder.class, new ToodleDoContextDeserializer())
               .create();
               
          Type collectionType = new TypeToken<List<ToodleDoContext>>(){}.getType();
          List<ToodleDoContext> lstContexts = (List<ToodleDoContext>)gson.fromJson(sbResp.toString(), collectionType);
          
          System.out.println("Total Contexts:" + lstContexts.size());

          return lstContexts;

     }
     
     public List<ToodleDoFolder> getFolders()
     {
          String sURL = "http://api.toodledo.com/2/folders/get.php?";
          
          Hashtable htParameters = new Hashtable();
          
          htParameters.put("key", getToodleDoUser().getKey());
          
          StringBuffer sbResp = makeRequest(sURL, htParameters);
          
          Gson gson = new GsonBuilder()
               .registerTypeAdapter(ToodleDoFolder.class, new ToodleDoFolderDeserializer())
               .create();
               
          Type collectionType = new TypeToken<List<ToodleDoFolder>>(){}.getType();
          List<ToodleDoFolder> lstFolders = (List<ToodleDoFolder>)gson.fromJson(sbResp.toString(), collectionType);
          
          System.out.println("Total Folders:" + lstFolders.size());

          return lstFolders;

     }
     
     public ToodleDoUser getToken(ToodleDoUser a_oUser)
     {
          String sURL = "http://api.toodledo.com/2/account/token.php?";
          
          Hashtable htParameters = new Hashtable();
          
          htParameters.put("userid", a_oUser.getUserid());
          htParameters.put("appid", APPID);
          htParameters.put("sig", Utility.createMD5Hash(a_oUser.getUserid() + TOKEN));   
          
          StringBuffer sbResp = makeRequest(sURL, htParameters);
          
          Gson gson = new Gson();
          a_oUser = gson.fromJson(sbResp.toString(), org.zechariahs.toodledoapi.pojo.ToodleDoUser.class);
          
          return a_oUser;
          
     }
     
     public ToodleDoUser accountLookup(ToodleDoUser a_oUser)
     {
          System.out.println("Looking up account...");
          
          String sURL = "http://api.toodledo.com/2/account/lookup.php?";
          
          Hashtable htParameters = new Hashtable();
          
          htParameters.put("appid", APPID);
          htParameters.put("email", a_oUser.getEmail());
          htParameters.put("pass", a_oUser.getPassword());
          htParameters.put("sig", Utility.createMD5Hash(a_oUser.getEmail() + TOKEN));
          
          StringBuffer sbResp = makeRequest(sURL, htParameters);
          
          Gson gson = new Gson();
          a_oUser = gson.fromJson(sbResp.toString(), org.zechariahs.toodledoapi.pojo.ToodleDoUser.class);
          
          return a_oUser;
          
     }

     public void setUserId(String userId)
     {
          this.m_sUserId = userId;
     }

     public String getUserId()
     {
          return m_sUserId;
     }

     public void setToodleDoUser(ToodleDoUser toodleDoUser)
     {
          this.m_oToodleDoUser = toodleDoUser;
     }

     public ToodleDoUser getToodleDoUser()
     {
          return m_oToodleDoUser;
     }
}
