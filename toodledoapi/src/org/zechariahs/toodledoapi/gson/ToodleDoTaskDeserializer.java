package org.zechariahs.toodledoapi.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;

import java.lang.reflect.Type;

import org.zechariahs.toodledoapi.pojo.ToodleDoTask;

public class ToodleDoTaskDeserializer implements JsonDeserializer<ToodleDoTask>
{
     public ToodleDoTaskDeserializer()
     {
     }

     public ToodleDoTask deserialize(JsonElement json, Type typeOfT, 
                                     JsonDeserializationContext context)
     {
          
          JsonObject jObject = (JsonObject)json;

          ToodleDoTask oTask = new ToodleDoTask();
          
          try
          {
               oTask.setAdded(jObject.get("added").getAsString());     
          }
          catch(NullPointerException npe)
          {
               // Intentionally Left Blank
          }
          
          
          try
          {
               oTask.setChildren(jObject.get("children").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setCompleted(jObject.get("completed").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setContext(jObject.get("context").getAsInt());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setDuedate(jObject.get("duedate").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setDuedatemod(jObject.get("duedatemod").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setDuetime(jObject.get("duetime").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setFolder(jObject.get("folder").getAsInt());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setGoal(jObject.get("goal").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setId(jObject.get("id").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setLength(jObject.get("length").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setLocation(jObject.get("location").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setMeta(jObject.get("meta").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setModified(jObject.get("modified").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setNote(jObject.get("note").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setParent(jObject.get("parent").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setPriority(jObject.get("priority").getAsInt());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setRemind(jObject.get("remind").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setRepeat(jObject.get("repeat").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setRepeatfrom(jObject.get("repeatfrom").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setStar(jObject.get("star").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setStartdate(jObject.get("startdate").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setStarttime(jObject.get("starttime").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setStatus(jObject.get("status").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setTag(jObject.get("tag").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setTimer(jObject.get("timer").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setTimeron(jObject.get("timeron").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
          try
          {
               oTask.setTitle(jObject.get("title").getAsString());
          }
          catch(NullPointerException npe)
          {
          // Intentionally Left Blank
          }
          
           try
           {
                oTask.setRef(jObject.get("ref").getAsString());
           }
           catch(NullPointerException npe)
           {
           // Intentionally Left Blank
           }
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          return oTask;
     }
}
