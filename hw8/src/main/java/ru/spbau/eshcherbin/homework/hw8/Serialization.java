package ru.spbau.eshcherbin.homework.hw8;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * The Serialization class allows to serialize and deserialize objects of classes
 * which contain only primitive or String fields.
 */
public class Serialization {
    /**
     * Serializes given object to the given outputStream
     */
    public static void serialize(@NotNull Object object, @NotNull OutputStream outputStream) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        // hash codes are documented to be stable across runs
        Arrays.sort(fields, Comparator.comparingInt(Field::hashCode));
        try (PrintWriter printWriter = new PrintWriter(outputStream)) {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType().equals(String.class)) {
                    byte[] bytes = ((String) field.get(object)).getBytes();
                    printWriter.println(bytes.length);
                    for (byte b : bytes) {
                        printWriter.println(b);
                    }
                } else {
                    printWriter.println(field.get(object));
                }
            }
            printWriter.flush();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Serialization problem");
        }
    }

    /**
     * Deserializes an object of given class from given inputStream.
     * The object should have been serialized using the serialize method.
     * @return the deserialized object
     */
    @Nullable
    public static <T> T deserialize(@NotNull InputStream inputStream, @NotNull Class<T> clazz) {
        T result;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException();
        }
        Field[] fields = clazz.getDeclaredFields();
        // hash codes are documented to be stable across runs
        Arrays.sort(fields, Comparator.comparingInt(Field::hashCode));
        try (Scanner scanner = new Scanner(inputStream)) {
            for (Field field : fields) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                String value = scanner.nextLine();
                try {
                    if (fieldType.equals(Boolean.TYPE)) {
                        field.setBoolean(result, Boolean.parseBoolean(value));
                    } else if (fieldType.equals(Byte.TYPE)) {
                        field.setByte(result, Byte.parseByte(value));
                    } else if (fieldType.equals(Character.TYPE)) {
                        field.setChar(result, value.charAt(0));
                    } else if (fieldType.equals(Short.TYPE)) {
                        field.setShort(result, Short.parseShort(value));
                    } else if (fieldType.equals(Integer.TYPE)) {
                        field.setInt(result, Integer.parseInt(value));
                    } else if (fieldType.equals(Long.TYPE)) {
                        field.setLong(result, Long.parseLong(value));
                    } else if (fieldType.equals(Float.TYPE)) {
                        field.setFloat(result, Float.parseFloat(value));
                    } else if (fieldType.equals(Double.TYPE)) {
                        field.setDouble(result, Double.parseDouble(value));
                    } else if (fieldType.equals(String.class)) {
                        byte[] bytes = new byte[Integer.parseInt(value)];
                        for (int i = 0; i < bytes.length; i++) {
                            bytes[i] = Byte.parseByte(scanner.nextLine());
                        }
                        field.set(result, new String(bytes));
                    } else {
                        throw new IllegalArgumentException("Given class contains non-primitive and non-String field");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Deserialization problem");
                }
            }
        }
        return result;
    }
}
