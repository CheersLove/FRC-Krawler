package com.team2052.frckrawler.tba.types;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.team2052.frckrawler.db.Event;
import com.team2052.frckrawler.tba.JSON;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Adam
 */
public class EventDeserializer implements JsonDeserializer<Event> {
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Event deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject object = json.getAsJsonObject();
        final Event event = new Event();
        if (object.has("key")) {
            event.setFmsid(object.get("key").getAsString());
        }

        if (object.has("name")) {
            event.setName(object.get("name").getAsString());
        }


        JsonObject data = new JsonObject();
        if (object.get("location").isJsonNull()) {
            data.addProperty("location", "Location Unknown");
        } else {
            data.addProperty("location", object.get("location").getAsString());
        }

        event.setData(JSON.getGson().toJson(data));

        //Parse the date
        if (!object.get("start_date").isJsonNull()) {
            try {
                event.setDate(format.parse(object.get("start_date").toString().replace("\"", "")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            event.setDate(new Date(0, 0, 0));
        }

        return event;
    }
}
