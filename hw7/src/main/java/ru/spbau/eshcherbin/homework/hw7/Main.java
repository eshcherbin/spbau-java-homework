package ru.spbau.eshcherbin.homework.hw7;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main {
    public static void main(String[] args) throws IOException {
        unzipFilesSatisfyingPattern(Paths.get(args[0]), Pattern.compile(args[1]));
    }

    /**
     * Finds all zip archives in the directory tree with given root and extracts all files satisfying pattern from them.
     * @throws IOException if an error opening given root occurred
     */
    private static void unzipFilesSatisfyingPattern(Path root, Pattern pattern) throws IOException {
        Files.walk(root).filter(path -> Files.isRegularFile(path) && path.getFileName().toString().endsWith(".zip"))
                .forEach(path -> {
                    try {
                        Path foundDirectory = path.toAbsolutePath().getParent()
                                .resolve(path.getFileName().toString() + "_found");
                        if (!Files.exists(foundDirectory) || !Files.isDirectory(foundDirectory)) {
                            Files.createDirectory(foundDirectory);
                        }
                        try {
                            ZipFile zipFile = new ZipFile(path.toString());
                            Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
                            while (zipEntries.hasMoreElements()) {
                                ZipEntry currentEntry = zipEntries.nextElement();
                                Path currentEntryPath = Paths.get(currentEntry.getName());
                                String currentEntryFileName = currentEntryPath.getFileName().toString();
                                String currentEntryFolders = currentEntryPath.getParent() == null ? "" :
                                                                currentEntryPath.getParent().toString();
                                if (!currentEntry.isDirectory() && pattern.matcher(currentEntryFileName).matches()) {
                                    try (InputStream inputStream = zipFile.getInputStream(currentEntry)) {
                                        Files.createDirectories(foundDirectory.resolve(currentEntryFolders));
                                        Files.copy(inputStream, foundDirectory.resolve(currentEntry.getName()),
                                                StandardCopyOption.REPLACE_EXISTING);
                                    } catch (IOException e) {
                                        System.out.printf("Unable to copy file: %s\n", currentEntry.getName());
                                    }
                                }
                            }
                        } catch (IOException e) {
                            System.out.printf("Unable to read file: %s\n", path);
                        }
                    } catch (IOException e) {
                        System.out.printf("Unable to create a found files directory for %s\n", path);
                    }
                });
    }
}
