package ewee_foods.block.entity.custom;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class CrockpotContainer extends SimpleContainer {
    public boolean canPlaceItem(int pIndex, ItemStack pStack){ //don't allow automation to place items in the results slot
        return pIndex != 4;
    }
}
