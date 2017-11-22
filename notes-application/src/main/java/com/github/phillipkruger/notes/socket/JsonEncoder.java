package com.github.phillipkruger.notes.socket;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
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
public class JsonEncoder implements Encoder.Text<JsonObject> {

    @Override
    public void init(EndpointConfig config) {}

    @Override
    public String encode(JsonObject payload) throws EncodeException {
        
        try (StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.writeObject(payload);
            return stringWriter.toString();
        } catch (IOException ex) {
            throw new EncodeException(payload,"JsonEncoder could not encode JsonObject",ex);
        }
    }

    
    
    @Override
    public void destroy() {}

}