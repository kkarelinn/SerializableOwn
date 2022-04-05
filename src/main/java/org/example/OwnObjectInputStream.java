package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;

public class OwnObjectInputStream implements AutoCloseable {
    private final ByteArrayInputStream in;

    public OwnObjectInputStream(ByteArrayInputStream in) {
        this.in = in;
    }


    @Override
    public void close() throws Exception {
        in.close();
    }

    public Object readObject() throws ClassNotFoundException, JsonProcessingException {

        System.out.println("Deserialization...");
        byte[] line = in.readAllBytes();
        String lineS = new String(line);
        String[] elements = lineS.split("~#~");
//        System.out.println(lineS);
//        System.out.println(elements[0]);
//        System.out.println(elements[1]);
        String cl = elements[0].substring(elements[0].indexOf("<Class>") + 7, elements[0].indexOf("</Class>"));
//        System.out.println(cl);
        XmlMapper xmlMapper = new XmlMapper();
        Class<?> clazz = Class.forName(cl);
        Object object
                = xmlMapper.readValue(elements[1], clazz);

        return object;
    }
}
