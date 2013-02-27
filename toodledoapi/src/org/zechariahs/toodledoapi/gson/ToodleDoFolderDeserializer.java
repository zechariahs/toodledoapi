package org.zechariahs.toodledoapi.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;

import com.google.gson.JsonElement;

import com.google.gson.JsonObject;

import java.lang.reflect.Type;

import org.zechariahs.toodledoapi.pojo.ToodleDoFolder;

public class ToodleDoFolderDeserializer  implements JsonDeserializer<ToodleDoFolder>
{
     public ToodleDoFolderDeserializer()
     {
     }

     public ToodleDoFolder deserialize(JsonElement json, Type typeOfT, 
                                     JsonDeserializationContext context)
     {
          
          JsonObject jObject = (JsonObject)json;
          
          ToodleDoFolder oFolder = new ToodleDoFolder();
          
          oFolder.setId(jObject.get("id").getAsInt());   
          oFolder.setName(jObject.get("name").getAsString());
          oFolder.setPrivateFolder(jObject.get("private").getAsInt());
          oFolder.setOrd(jObject.get("ord").getAsInt());
          
          return oFolder;
     }
}
