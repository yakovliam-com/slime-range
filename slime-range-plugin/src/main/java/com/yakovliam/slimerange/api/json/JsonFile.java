package com.yakovliam.slimerange.api.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonFile {

    /**
     * Base folder
     */
    private final Path baseFolder;

    /**
     * File name
     */
    private final String fileName;

    /**
     * Json file path
     */
    private final Path jsonFilePath;

    public JsonFile(Path baseFolder, String fileName) {
        this.baseFolder = baseFolder;
        this.fileName = fileName;

        this.jsonFilePath = resolveJsonFile(fileName);
    }

    /**
     * Resolves a json file
     *
     * @param fileName file name
     * @return configuration path
     */
    private Path resolveJsonFile(String fileName) {
        Path jsonFile = baseFolder.resolve(fileName);

        if (!Files.exists(jsonFile)) {
            try {
                Files.createDirectories(jsonFile.getParent());
            } catch (IOException ignored) {
            }

            try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
                Files.copy(is, jsonFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return jsonFile;
    }

    /**
     * Parses json
     *
     * @return json
     */
    public JsonElement parse() {
        String content;
        try {
            content = Files.readString(jsonFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new JsonParser().parse(content);
    }

    /**
     * Writes json
     *
     * @param element element
     */
    public void write(JsonElement element) {
        String json = new Gson().toJson(element);

        try {
            Files.writeString(jsonFilePath, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
