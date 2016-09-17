package ru.spbau.eshcherbin.java.homework.hw1.task1;

/**
 * An auxiliary class that represents a (key, value) pair
 */
public class KeyValuePair {
    private String key;
    private String value;

    public KeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        KeyValuePair that = (KeyValuePair) o;

        if (key != null ? !key.equals(that.key) : that.key != null)
            return false;
        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
