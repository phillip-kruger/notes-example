package com.github.phillipkruger.notes.socket;

import java.io.IOException;
import java.io.Writer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * JSON-P 1.0
 * 
 * Encoder that can encode JsonObject to be streamed over WebSockets
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
public class JsonEncoder implements Encoder.TextStream<JsonObject> {

    @Override
    public void init(EndpointConfig config) {}

    @Override
    public void encode(JsonObject payload, Writer writer) throws EncodeException, IOException {
        try (JsonWriter jsonWriter = Json.createWriter(writer)) {
            jsonWriter.writeObject(payload);
        }
    }

    @Override
    public void destroy() {}
}