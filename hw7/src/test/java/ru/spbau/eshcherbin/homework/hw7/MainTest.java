package ru.spbau.eshcherbin.homework.hw7;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {

    private final static String testDirectoryName = "test/test_dir";
    private final static String expectedDirectoryName = "test/expected_dir";

    private static Path testDirectory;
    private static Path expectedDirectory;

    @Before
    public void setUp() throws Exception {
        testDirectory = Paths.get("").toAbsolutePath().resolve(testDirectoryName);
        expectedDirectory = Paths.get("").toAbsolutePath().resolve(expectedDirectoryName);
    }

    @Test
    public void mainTest() throws Exception {
        Main.main(new String[]{testDirectory.toString(), "ab[c|d]"});
        Set<Path> expectedFiles = Files.walk(expectedDirectory).collect(Collectors.toCollection(HashSet::new));
        Set<Path> testFiles = Files.walk(testDirectory).collect(Collectors.toCollection(HashSet::new));
        Set<Path> testFilesToExpected = testFiles.stream().map(MainTest::testToExpectedPath)
                                                 .collect(Collectors.toCollection(HashSet::new));
        assertTrue("File sets are not equal", expectedFiles.equals(testFilesToExpected));
        for (Path file : testFiles) {
            if (!Files.isDirectory(file)) {
                byte[] expectedContent = Files.readAllBytes(testToExpectedPath(file));
                byte[] resultContent = Files.readAllBytes(file);
                assertArrayEquals(expectedContent, resultContent);
            }
        }
    }

    /**
     * Converts a path from test directory to the corresponding path in expected directory
     */
    private static Path testToExpectedPath(Path testPath) {
        return expectedDirectory.resolve(testDirectory.relativize(testPath.toAbsolutePath()));
    }
}