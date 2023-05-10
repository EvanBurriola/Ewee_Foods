package ewee_foods.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ModFuelSlot extends SlotItemHandler {
    public ModFuelSlot(IItemHandler itemHandler, int index, int x, int y){
        super(itemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack){
        return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0;
    }
}
