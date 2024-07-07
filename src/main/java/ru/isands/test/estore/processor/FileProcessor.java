package ru.isands.test.estore.processor;

public interface FileProcessor {
    boolean process(byte[] content, String fileName);
    boolean canProcess(String fileName);
}