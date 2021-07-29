package com.yakovliam.slimerange.storage.implementation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yakovliam.slimerange.SlimeRangePlugin;
import com.yakovliam.slimerange.api.json.JsonFile;
import com.yakovliam.slimerange.model.user.User;
import com.yakovliam.slimerange.storage.StorageImplementation;

import java.util.UUID;

public class JsonStorageImplementation extends StorageImplementation {

    /**
     * Json file
     */
    private JsonFile jsonFile;

    /**
     * Root object
     */
    private JsonObject rootObject;

    /**
     * Gson
     */
    private final Gson gson;


    /**
     * Json storage implementation
     *
     * @param plugin plugin
     */
    public JsonStorageImplementation(SlimeRangePlugin plugin) {
        super(plugin);
        this.gson = new Gson();
    }

    /**
     * Initializes the storage
     */
    @Override
    public void init() {
        jsonFile = new JsonFile(plugin.getDataFolder().toPath(), "users.json");
        this.rootObject = jsonFile.parse().getAsJsonObject();
    }

    /**
     * Returns a user
     *
     * @param uuid uuid
     * @return user
     */
    @Override
    public User getUser(UUID uuid) {
        JsonObject users = rootObject.get("users").getAsJsonObject();
        if (users.has(uuid.toString())) {
            return gson.fromJson(users.get(uuid.toString()).getAsJsonObject(), User.class);
        } else {
            // create and return
            User user = new User(uuid, 0D);
            // convert into json element
            JsonObject object = new JsonParser().parse(gson.toJson(user)).getAsJsonObject();

            // add to users
            users.add(uuid.toString(), object);

            // save in root
            rootObject.add("users", users);

            // write
            jsonFile.write(rootObject);

            return user;
        }
    }

    /**
     * Saves a user
     *
     * @param user user
     */
    @Override
    public void saveUser(User user) {
        JsonObject users = rootObject.get("users").getAsJsonObject();

        // convert into json element
        JsonObject object = new JsonParser().parse(gson.toJson(user)).getAsJsonObject();

        // add to users
        users.add(user.getUuid().toString(), object);

        // save in root
        rootObject.add("users", users);

        // write
        jsonFile.write(rootObject);
    }
}
