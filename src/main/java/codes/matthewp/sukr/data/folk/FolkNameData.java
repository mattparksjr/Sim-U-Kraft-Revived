package codes.matthewp.sukr.data.folk;

import codes.matthewp.sukr.SimUKraft;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.storage.loot.Deserializers;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FolkNameData extends SimpleJsonResourceReloadListener {

    public static final Gson GSON_INSTANCE = Deserializers.createFunctionSerializer().create();
    private final static String FOLDER = "folk";

    private static List<String> maleNames = new ArrayList<>();
    private static List<String> femaleNames = new ArrayList<>();
    private static List<String> lastNames = new ArrayList<>();

    public FolkNameData() {
        super(GSON_INSTANCE, FOLDER);
    }

    @Override
    protected void apply(@NotNull Map<ResourceLocation, JsonElement> resourceList, ResourceManager resourceManagerIn, @NotNull ProfilerFiller profilerIn) {
        ResourceLocation resourcelocation = new ResourceLocation(SimUKraft.MODID, FOLDER + "/names.json");
        List<String> maleNames = new ArrayList<>();
        List<String> femaleNames = new ArrayList<>();
        List<String> lastNames = new ArrayList<>();
        for (Resource res : resourceManagerIn.getResourceStack(resourcelocation)) {
            try (InputStream is = res.open(); Reader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                JsonObject jsonobject = GsonHelper.fromJson(GSON_INSTANCE, reader, JsonObject.class);

                boolean replace = jsonobject.get("replace").getAsBoolean();
                if (replace) {
                    maleNames.clear();
                    femaleNames.clear();
                    lastNames.clear();
                }

                JsonArray maleANameIn = jsonobject.get("maleNames").getAsJsonArray();
                for(JsonElement entry : maleANameIn) {
                    maleNames.add(entry.getAsString());
                }

                JsonArray femaleANameIn = jsonobject.get("femaleNames").getAsJsonArray();
                for(JsonElement entry : femaleANameIn) {
                    femaleNames.add(entry.getAsString());
                }

                JsonArray lastNameIn = jsonobject.get("lastNames").getAsJsonArray();
                for(JsonElement entry : lastNameIn) {
                    lastNames.add(entry.getAsString());
                }
            } catch (RuntimeException | IOException ex) {
                SimUKraft.LOGGER.error("Couldn't read sim name list {} in data pack {}", resourcelocation, res.sourcePackId(), ex);
            }
        }

        FolkNameData.maleNames = maleNames;
        FolkNameData.femaleNames = femaleNames;
        FolkNameData.lastNames = lastNames;
        SimUKraft.LOGGER.debug("Loaded {} male names, {} female names, and {} last names", maleNames.size(), femaleNames.size(), lastNames.size());
    }

    public static List<String> getFemaleNames() {
        return femaleNames;
    }

    public static List<String> getMaleNames() {
        return maleNames;
    }

    public static List<String> getLastNames() {
        return lastNames;
    }
}
