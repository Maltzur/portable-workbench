package com.mitchellmarsden.portableworkbench;

import com.mitchellmarsden.portableworkbench.items.PortableWorkbenchItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {

    private static final Item PORTABLE_WORKBENCH_ITEM = new PortableWorkbenchItem(new Item.Settings().group(ItemGroup.MISC).maxCount(1));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("portableworkbench", "portable_workbench"), PORTABLE_WORKBENCH_ITEM);
    }
}
