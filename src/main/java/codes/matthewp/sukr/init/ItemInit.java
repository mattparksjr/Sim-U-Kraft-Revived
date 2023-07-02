package codes.matthewp.sukr.init;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.item.ItemGamemodeSelect;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SimUKraft.MODID);

    public static final RegistryObject<Item> ITEM_CHEESE = ITEMS.register("item_cheese", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(6).saturationMod(2f).build())));
    public static final RegistryObject<Item> ITEM_CHEESEBURGER = ITEMS.register("item_cheeseburger", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(6).saturationMod(2f).build())));
    public static final RegistryObject<Item> ITEM_BURGER = ITEMS.register("item_burger", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(6).saturationMod(2f).build())));
    public static final RegistryObject<Item> ITEM_FRIES = ITEMS.register("item_fries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(6).saturationMod(2f).build())));
    public static final RegistryObject<Item> ITEM_GAMEMODE = ITEMS.register("item_gamemode", ItemGamemodeSelect::new);

}
