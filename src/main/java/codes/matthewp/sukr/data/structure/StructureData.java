package codes.matthewp.sukr.data.structure;

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

public class StructureData extends SimpleJsonResourceReloadListener {
    private static final Gson GSON_INSTANCE = Deserializers.createFunctionSerializer().create();
    private final static String FOLDER = "structure";

    private static List<Structure> structures = new ArrayList<>();

    public StructureData() {
        super(GSON_INSTANCE, FOLDER);
    }

    @Override
    protected void apply(@NotNull Map<ResourceLocation, JsonElement> resourceList, ResourceManager resourceManagerIn, @NotNull ProfilerFiller profilerIn) {
        ResourceLocation resourcelocation = new ResourceLocation(SimUKraft.MODID, FOLDER + "/structures.json");
        List<Structure> structureList = new ArrayList<>();
        for (Resource res : resourceManagerIn.getResourceStack(resourcelocation)) {
            try (InputStream is = res.open(); Reader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                JsonObject json = GsonHelper.fromJson(GSON_INSTANCE, reader, JsonObject.class);
                JsonArray structures = json.get("structures").getAsJsonArray();
                boolean replace = json.get("replace").getAsBoolean();

                if (replace) {
                    SimUKraft.LOGGER.info("Data pack {} has replaced all buildings.", res.sourcePackId());
                    StructureData.structures.clear();
                }

                for (JsonElement structure : structures) {
                    JsonObject structureData = structure.getAsJsonObject();
                    Structure finalStruct = new Structure(
                            structureData.get("id").getAsString(),
                            structureData.get("name").getAsString(),
                            StructureCategory.valueOf(structureData.get("category").getAsString().toUpperCase()),
                            structureData.get("file").getAsString());
                    structureList.add(finalStruct);
                }

            } catch (RuntimeException | IOException ex) {
                SimUKraft.LOGGER.error("Couldn't read structure list {} in data pack {}", resourcelocation, res.sourcePackId(), ex);
            }
        }
        StructureData.structures = structureList;
        SimUKraft.LOGGER.debug("Loaded {} structures", structureList.size());
    }

    public static List<Structure> getStructures() {
        return structures;
    }
}
