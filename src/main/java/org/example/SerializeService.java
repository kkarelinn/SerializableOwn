package org.example;

import java.io.*;

public class SerializeService {

    public static byte[] serialize(Object object) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (OwnObjectOutputStream outputStream = new OwnObjectOutputStream(out)) {
            outputStream.writeObject(object);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }

    public static byte[] serializeSTD(Object object) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(out)) {
            outputStream.writeObject(object);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }

    public static Object deserialize(byte[] array) throws IOException {
        Object object=null;
        ByteArrayInputStream in = new ByteArrayInputStream(array);

        try (OwnObjectInputStream is = new OwnObjectInputStream(in)) {
            object = is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

    public static Object deserializeSTD(byte[] array) throws IOException {
        Object object=null;
        ByteArrayInputStream in = new ByteArrayInputStream(array);

        try (ObjectInputStream is = new ObjectInputStream(in)) {
            object = is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

}
