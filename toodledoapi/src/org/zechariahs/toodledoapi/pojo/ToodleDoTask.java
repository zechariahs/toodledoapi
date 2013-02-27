package org.zechariahs.toodledoapi.pojo;

public class ToodleDoTask extends ToodleDoBase
{
     private static final int DUE_BY = 0;
     private static final int DUE_ON = 1;
     private static final int DUE_AFTER = 2;
     private static final int DUE_OPTIONAL = 3;
     
     private static final int STATUS_NONE = 0;
     private static final int STATUS_NEXT_ACTION = 1;
     private static final int STATUS_ACTIVE = 2;
     private static final int STATUS_PLANNING = 3;
     private static final int STATUS_DELEGATED = 4;
     private static final int STATUS_WAITING = 5;
     private static final int STATUS_HOLD = 6;
     private static final int STATUS_POSTPONED = 7;
     private static final int STATUS_SOMEDAY = 8;
     private static final int STATUS_CANCELED = 9;
     private static final int STATUS_REFERENCED = 10;
     
     public static final int PRIORITY_NEGATIVE = -1;
     public static final int PRIORITY_LOW = 0;
     public static final int PRIORITY_MEDIUM = 1;
     public static final int PRIORITY_HIGH = 2;
     public static final int PRIORITY_TOP = 3;
     
     private String id = "";
     private String title = "";
     private String tag = "";
     private int folder;
     private int context;
     private String goal = "";
     private String location = "";
     private String parent = "";
     private String children = "";
     private String order = "";
     private String duedate = "";
     private String duedatemod = "";
     private String startdate = "";
     private String duetime = "";
     private String starttime = "";
     private String remind = "";
     private String repeat = "";
     private String repeatfrom = "";
     private String status = "";
     private String length = "";
     private int priority;
     private String star = "";
     private String modified = "";
     private String completed = "";
     private String added = "";
     private String timer = "";
     private String timeron = "";
     private String note = "";
     private String meta = "";
     private String ref = "";
     
     
     public ToodleDoTask()
     {
     }
     
     public ToodleDoTask clone()
     {
          
          ToodleDoTask clonedTask = new ToodleDoTask();
          
          try
          {
               clonedTask = (ToodleDoTask)super.clone();
          }
          catch (CloneNotSupportedException e)
          {
               // TODO
          }
          
          return clonedTask;
          
     }
     
     public void setId(String id)
     {
          this.id = id;
     }

     public String getId()
     {
          return id;
     }

     public void setTitle(String title)
     {
          this.title = title;
     }

     public String getTitle()
     {
          return title;
     }

     public void setTag(String tag)
     {
          this.tag = tag;
     }

     public String getTag()
     {
          return tag;
     }

     public void setFolder(int folder)
     {
          this.folder = folder;
     }

     public int getFolder()
     {
          return folder;
     }

     public void setContext(int context)
     {
          this.context = context;
     }

     public int getContext()
     {
          return context;
     }

     public void setGoal(String goal)
     {
          this.goal = goal;
     }

     public String getGoal()
     {
          return goal;
     }

     public void setLocation(String location)
     {
          this.location = location;
     }

     public String getLocation()
     {
          return location;
     }

     public void setParent(String parent)
     {
          this.parent = parent;
     }

     public String getParent()
     {
          return parent;
     }

     public void setChildren(String children)
     {
          this.children = children;
     }

     public String getChildren()
     {
          return children;
     }

     public void setOrder(String order)
     {
          this.order = order;
     }

     public String getOrder()
     {
          return order;
     }

     public void setDuedate(String duedate)
     {
          this.duedate = duedate;
     }

     public String getDuedate()
     {
          return duedate;
     }

     public void setDuedatemod(String duedatemod)
     {
          this.duedatemod = duedatemod;
     }

     public String getDuedatemod()
     {
          return duedatemod;
     }

     public void setStartdate(String startdate)
     {
          this.startdate = startdate;
     }

     public String getStartdate()
     {
          return startdate;
     }

     public void setDuetime(String duetime)
     {
          this.duetime = duetime;
     }

     public String getDuetime()
     {
          return duetime;
     }

     public void setStarttime(String starttime)
     {
          this.starttime = starttime;
     }

     public String getStarttime()
     {
          return starttime;
     }

     public void setRemind(String remind)
     {
          this.remind = remind;
     }

     public String getRemind()
     {
          return remind;
     }

     public void setRepeat(String repeat)
     {
          this.repeat = repeat;
     }

     public String getRepeat()
     {
          return repeat;
     }

     public void setRepeatfrom(String repeatfrom)
     {
          this.repeatfrom = repeatfrom;
     }

     public String getRepeatfrom()
     {
          return repeatfrom;
     }

     public void setStatus(String status)
     {
          this.status = status;
     }

     public String getStatus()
     {
          return status;
     }

     public void setLength(String length)
     {
          this.length = length;
     }

     public String getLength()
     {
          return length;
     }

     public void setPriority(int priority)
     {
          this.priority = priority;
     }

     public int getPriority()
     {
          return priority;
     }

     public void setStar(String star)
     {
          this.star = star;
     }

     public String getStar()
     {
          return star;
     }

     public void setModified(String modified)
     {
          this.modified = modified;
     }

     public String getModified()
     {
          return modified;
     }

     public void setCompleted(String completed)
     {
          this.completed = completed;
     }

     public String getCompleted()
     {
          return completed;
     }

     public void setAdded(String added)
     {
          this.added = added;
     }

     public String getAdded()
     {
          return added;
     }

     public void setTimer(String timer)
     {
          this.timer = timer;
     }

     public String getTimer()
     {
          return timer;
     }

     public void setTimeron(String timeron)
     {
          this.timeron = timeron;
     }

     public String getTimeron()
     {
          return timeron;
     }

     public void setNote(String note)
     {
          this.note = note;
     }

     public String getNote()
     {
          return note;
     }

     public void setMeta(String meta)
     {
          this.meta = meta;
     }

     public String getMeta()
     {
          return meta;
     }

     public void setRef(String ref)
     {
          this.ref = ref;
     }

     public String getRef()
     {
          return ref;
     }
     
     public boolean isCompleted()
     {
          if(getCompleted() != null && getCompleted().length() > 0)
          {
               return true;
          }
          else
          {
               return false;
          }
     }
}
