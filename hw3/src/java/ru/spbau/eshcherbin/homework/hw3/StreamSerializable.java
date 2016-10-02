package ru.spbau.eshcherbin.homework.hw3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * An interface with basic serialization methods
 */
public interface StreamSerializable {
    /**
     * Writes the object to given OutputStream
     */
    void serialize(OutputStream out) throws IOException;

    /**
     * Reads the object from given OutputStream
     */
    void deserialize(InputStream in) throws IOException, ClassNotFoundException;
}
