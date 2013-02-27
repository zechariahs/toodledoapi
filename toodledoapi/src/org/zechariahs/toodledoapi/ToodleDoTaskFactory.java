package org.zechariahs.toodledoapi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.StringTokenizer;

import org.zechariahs.toodledoapi.pojo.ToodleDoContext;
import org.zechariahs.toodledoapi.pojo.ToodleDoFolder;
import org.zechariahs.toodledoapi.pojo.ToodleDoTask;

public class ToodleDoTaskFactory
{
     
     private List<ToodleDoFolder> m_lstToodleDoFolders = new ArrayList<ToodleDoFolder>();
     private List<ToodleDoContext> m_lstToodleDoContexts = new ArrayList<ToodleDoContext>();
     
     public ToodleDoTaskFactory(List<ToodleDoFolder> a_lstToodleDoFolders, List<ToodleDoContext> a_lstContexts)
     {
          setToodleDoFolders(a_lstToodleDoFolders);
          setToodleDoContexts(a_lstContexts);
     }
     
     public String getFolderName(int iId)
     {
          ToodleDoFolder oTempFolder = new ToodleDoFolder();
          
          oTempFolder.setId(iId);
          
          int iFolderIndex = m_lstToodleDoFolders.indexOf(oTempFolder);
          
          if(iFolderIndex > 0 && m_lstToodleDoFolders != null)
          {
               return m_lstToodleDoFolders.get(iFolderIndex).getName();
          }
          else
          {
               return null;
          }
          
     }
     
     public int getFolderID(String sName)
     {
          ToodleDoFolder oTempFolder = new ToodleDoFolder();
          
          oTempFolder.setName(sName);
          
          int iFolderIndex = m_lstToodleDoFolders.indexOf(oTempFolder);
          
          if(iFolderIndex > 0 && m_lstToodleDoFolders != null)
          {
               return m_lstToodleDoFolders.get(iFolderIndex).getId();
          }
          else
          {
               return 0;
          }
          
     }
     
     public int getContextID(String a_sName)
     {
          ToodleDoContext oTemp = new ToodleDoContext();
          
          oTemp.setName(a_sName);
          
          int iIndex = m_lstToodleDoContexts.indexOf(oTemp);
          
          if(iIndex > 0 && m_lstToodleDoContexts != null)
          {
               return m_lstToodleDoContexts.get(iIndex).getId();
          }
          else
          {
               return 0;
          }     
     }
     
     public String getContextName(int iId)
     {
          ToodleDoContext oTemp = new ToodleDoContext();
          
          oTemp.setId(iId);
          
          int iIndex = m_lstToodleDoContexts.indexOf(oTemp);
          
          if(iIndex > 0 && m_lstToodleDoFolders != null)
          {
               return m_lstToodleDoContexts.get(iIndex).getName();
          }
          else
          {
               return null;
          }
          
     }
     
     public static String getPriorityName(int a_iId)
     {
          
          if(a_iId == ToodleDoTask.PRIORITY_NEGATIVE)
          {
               return "Future Release";
          }
          else if(a_iId == ToodleDoTask.PRIORITY_LOW)
          {
               return "Low";     
          }
          else if(a_iId == ToodleDoTask.PRIORITY_MEDIUM)
          {
               return "Medium";     
          }
          else if(a_iId == ToodleDoTask.PRIORITY_HIGH)
          {
               return "High";     
          }
          else if(a_iId == ToodleDoTask.PRIORITY_TOP)
          {
               return "Flyup";     
          }
          else
          {
               return "Low";
          }
          
              
     }
     
     public static int getPriorityId(String a_sName)
     {
          if(a_sName.equalsIgnoreCase("Future Release"))
          {
               return ToodleDoTask.PRIORITY_LOW;     
          }
          else if(a_sName.equalsIgnoreCase("Critical"))
          {
               return ToodleDoTask.PRIORITY_TOP;     
          }
          else if(a_sName.equalsIgnoreCase("Flyup"))
          {
               return ToodleDoTask.PRIORITY_TOP;     
          }
          else if(a_sName.equalsIgnoreCase("High"))
          {
               return ToodleDoTask.PRIORITY_HIGH;     
          }
          else if(a_sName.equalsIgnoreCase("Medium"))
          {
               return ToodleDoTask.PRIORITY_MEDIUM;
          }
          else if(a_sName.equalsIgnoreCase("Low"))
          {
               return ToodleDoTask.PRIORITY_LOW;    
          }
          else 
          {
               return ToodleDoTask.PRIORITY_LOW;     
          }
     }
     
     public static ToodleDoTask cloneForSubTask(ToodleDoTask a_oTDParentTask)
     {
          ToodleDoTask oTDSubTask = a_oTDParentTask.clone();
          
          oTDSubTask.setId(null);
          oTDSubTask.setRef(null);
          oTDSubTask.setParent(a_oTDParentTask.getId());
                              
          return oTDSubTask;
     }
     
     public static String getMetaProject(ToodleDoTask a_oTDTask)
     {
          
          if(a_oTDTask.getMeta() == null || a_oTDTask.getMeta().length() == 0)
          {
               return null;
          }
          
          StringTokenizer tokenizer = new StringTokenizer(a_oTDTask.getMeta(), ",", false);
          
          String sProject = tokenizer.nextToken();
          
          return sProject;
          
     }
     
     public static int getMetaIssue(ToodleDoTask a_oTDTask)
     {
          
          if(a_oTDTask.getMeta() == null || a_oTDTask.getMeta().length() == 0)
          {
               return -1;
          }
          
          StringTokenizer tokenizer = new StringTokenizer(a_oTDTask.getMeta(), ",", false);
          
          String sIssue = tokenizer.nextToken();
          sIssue = tokenizer.nextToken();
          
          int iID = Integer.parseInt(sIssue);
          
          return iID;
          
     }
     
     public static int getMetaRelatedIssue(ToodleDoTask a_oTDTask)
     {
          if(a_oTDTask.getMeta() == null || a_oTDTask.getMeta().length() == 0)
          {
               return -1;
          }
          
          StringTokenizer tokenizer = new StringTokenizer(a_oTDTask.getMeta(), ",", false);
          
          String sRelatedIssue = tokenizer.nextToken();
          sRelatedIssue = tokenizer.nextToken();
          sRelatedIssue = tokenizer.nextToken();
          
          int iID = Integer.parseInt(sRelatedIssue);
          
          return iID;
          
     }

     public void setToodleDoFolders(List<ToodleDoFolder> toodleDoFolders)
     {
          this.m_lstToodleDoFolders = toodleDoFolders;
     }

     public List<ToodleDoFolder> getToodleDoFolders()
     {
          return m_lstToodleDoFolders;
     }

     public void setToodleDoContexts(List<ToodleDoContext> toodleDoContexts)
     {
          this.m_lstToodleDoContexts = toodleDoContexts;
     }

     public List<ToodleDoContext> getToodleDoContexts()
     {
          return m_lstToodleDoContexts;
     }
}
