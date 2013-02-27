package org.zechariahs.toodledoapi.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;

import com.google.gson.JsonElement;

import com.google.gson.JsonObject;

import java.lang.reflect.Type;

import org.zechariahs.toodledoapi.pojo.ToodleDoContext;

public class ToodleDoContextDeserializer  implements JsonDeserializer<ToodleDoContext>
{
     public ToodleDoContextDeserializer()
     {
     }

     public ToodleDoContext deserialize(JsonElement json, Type typeOfT, 
                                        JsonDeserializationContext context)
     {
          JsonObject jObject = (JsonObject)json;
          
          ToodleDoContext oContext = new ToodleDoContext();
          
          oContext.setId(jObject.get("id").getAsInt());   
          oContext.setName(jObject.get("name").getAsString());
          
          return oContext;
     }
}
