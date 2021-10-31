/*
 * Copyright 2003-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DistributionTest {

    @Test
    public void distributionHasExpectedContents() throws IOException {
        File zipFile = new File("build/distributions/app.zip");
        assertTrue(zipFile.exists(), "Distribution file not found");
        try (FileSystem zipfs = FileSystems.newFileSystem(zipFile.toPath(), this.getClass().getClassLoader())) {
            Path path = zipfs.getPath("app/lib/guava-30.1.1-jre.jar");
            assertTrue(Files.exists(path), "Didn't find Guava");
        }
    }

}
