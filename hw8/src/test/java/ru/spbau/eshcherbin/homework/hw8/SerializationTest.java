package ru.spbau.eshcherbin.homework.hw8;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class SerializationTest {

    @Test
    public void serializeAndDeserializeTest() throws Exception {
        DummyClass dummy = new DummyClass(false, 225, 1. / 117, "test string\nwith two lines\n");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Serialization.serialize(dummy, outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        DummyClass deserializedDummy = Serialization.deserialize(inputStream, DummyClass.class);
        assertNotNull(deserializedDummy);
        assertEquals(dummy.aBoolean, deserializedDummy.aBoolean);
        assertEquals(dummy.anInt, deserializedDummy.anInt);
        assertEquals(dummy.aDouble, deserializedDummy.aDouble, 1e-9);
        assertEquals(dummy.string, deserializedDummy.string);
    }

    private static class DummyClass {
        private boolean aBoolean;
        private int anInt;
        private double aDouble;
        private String string;

        public DummyClass() {
        }

        public DummyClass(boolean aBoolean, int anInt, double aDouble, String string) {
            this.aBoolean = aBoolean;
            this.anInt = anInt;
            this.aDouble = aDouble;
            this.string = string;
        }
    }
}