package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class OwnObjectOutputStream implements AutoCloseable {
    private final ByteArrayOutputStream out;

    public OwnObjectOutputStream(ByteArrayOutputStream out) {
        this.out = out;
    }

    public void writeObject(Object object) throws JsonProcessingException {

        System.out.println("Serialization....");
        XmlMapper xmlMapper = new XmlMapper();
        String clazz = xmlMapper.writeValueAsString(object.getClass());
//        System.out.println(clazz);
        String value = xmlMapper.writeValueAsString(object);
        String totalObject = clazz+"~#~"+value;
//        System.out.println(totalObject);
        byte[] bytes = totalObject.getBytes(StandardCharsets.UTF_8);
//        System.out.println(Arrays.toString(bytes));
        out.writeBytes(bytes);
    }

    @Override
    public void close() throws Exception {
        out.flush();
        out.close();
    }


}
