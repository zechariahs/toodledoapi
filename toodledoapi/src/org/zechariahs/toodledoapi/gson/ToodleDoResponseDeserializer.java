package org.zechariahs.toodledoapi.gson;

import java.lang.reflect.Type;

import org.zechariahs.toodledoapi.pojo.ToodleDoContext;
import org.zechariahs.toodledoapi.pojo.ToodleDoResponse;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ToodleDoResponseDeserializer implements JsonDeserializer<ToodleDoResponse> 
{

	@Override
	public ToodleDoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException 
	{
		JsonObject jObject = (JsonObject)json;
        
		ToodleDoResponse oResponse = new ToodleDoResponse();
        
		oResponse.setId(jObject.get("id").getAsInt());   
		oResponse.setMessage(jObject.get("error").getAsString());
        
        return oResponse;
	}

}
