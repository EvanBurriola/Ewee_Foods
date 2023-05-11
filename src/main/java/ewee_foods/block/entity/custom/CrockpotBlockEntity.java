package ewee_foods.block.entity.custom;

import ewee_foods.block.entity.ModBlockEntities;
import ewee_foods.recipe.CrockpotRecipe;
import ewee_foods.screen.CrockpotMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;


public class CrockpotBlockEntity extends BlockEntity implements MenuProvider {
    //size: 4 -> slots in block entity
    private final ItemStackHandler itemHandler = new ItemStackHandler(5 ){
        @Override
        protected void onContentsChanged(int slot){
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;
    private int fuel = 0; //Current remaining fuel
    private int burnValue = 0;//The start value of the last fuel item consumed
    public CrockpotBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CROCKPOT_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return CrockpotBlockEntity.this.progress;
                    case 1: return CrockpotBlockEntity.this.maxProgress;
                    case 2: return CrockpotBlockEntity.this.fuel;
                    case 3: return CrockpotBlockEntity.this.burnValue;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: CrockpotBlockEntity.this.progress = value; break;
                    case 1: CrockpotBlockEntity.this.maxProgress = value; break;
                    case 2: CrockpotBlockEntity.this.fuel = value; break;
                    case 3: CrockpotBlockEntity.this.burnValue = value; break;
                }
            }

            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Crockpot");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new CrockpotMenu(pContainerId, pInventory, this, this.data);
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("crockpot.progress", progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("crockpot.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }



    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, CrockpotBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity)) {
            consumeFuel(pBlockEntity);
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.progress >= pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
        if(hasFuel(pBlockEntity)) {
            pBlockEntity.fuel--; //Fuel burns every tick!
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasRecipe(CrockpotBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<CrockpotRecipe> match = level.getRecipeManager()
                .getRecipeFor(CrockpotRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && (hasFuel(entity) || hasFuelInSlot(entity));
    }
    //Update this to check if there is fuel, give entity fuel value of getBurnTime. Only consume fuel in the slot if
    // entity fuel value = 0
    private static boolean hasFuelInSlot(CrockpotBlockEntity entity) {
        ItemStack stack = entity.itemHandler.getStackInSlot(0);
        int burnTime = ForgeHooks.getBurnTime(stack,RecipeType.SMELTING);
        return burnTime > 0; //Returns true if item in fuel slot has a burn time property
    }

    private static boolean hasFuel(CrockpotBlockEntity entity){
        return entity.fuel > 0;
    }

    private static void consumeFuel(CrockpotBlockEntity entity){
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<CrockpotRecipe> match = level.getRecipeManager()
                .getRecipeFor(CrockpotRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()){
            if(!hasFuel(entity)) {
                if (hasFuelInSlot(entity)){
                    ItemStack stack = entity.itemHandler.getStackInSlot(0);
                    entity.burnValue = ForgeHooks.getBurnTime(stack,RecipeType.SMELTING);
                    entity.fuel = entity.burnValue; //set fuel to fuel items burn value
                    entity.itemHandler.extractItem(0, 1, false); //Only consume fuel item if its burn value is depleted
                }
            }
        }
    }

    private static void craftItem(CrockpotBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<CrockpotRecipe> match = level.getRecipeManager()
                .getRecipeFor(CrockpotRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(1,1, false);
            entity.itemHandler.extractItem(2,1, false);
            entity.itemHandler.extractItem(3,1, false);
//            entity.itemHandler.getStackInSlot(2).hurt(1, new Random(), null); <- code for tool

            entity.itemHandler.setStackInSlot(4, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(4).getCount() + 1));

            entity.progress = 0;
        }
    }

    private void resetProgress() {
        if(this.progress != 0){
            this.progress--;
        } else {
            this.progress = 0;
        }
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(4).getItem() == output.getItem() || inventory.getItem(4).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(4).getMaxStackSize() > inventory.getItem(4).getCount();
    }
}
