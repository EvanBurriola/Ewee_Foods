package ewee_foods.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import ewee_foods.Ewee_FoodsMain;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CrockpotScreen extends AbstractContainerScreen<CrockpotMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Ewee_FoodsMain.MOD_ID, "textures/gui/crockpot.png");
    public CrockpotScreen(CrockpotMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }
    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        //pX,pY,pUoffset, pVoffset, pUWidth
        if(menu.isCrafting()) {
            //Arrow (bottom left TOFILL pix: 80,52 | bottom left FILLED: 177,31 (w:24,h:17)
            this.blit(pPoseStack,  x+80, y+52-17, 177, 14, menu.getScaledProgressArrow(), 17);
        }
        if(menu.hasFuel()){
            //Flame (bottom left TOFILL pix: (x+)58,(y+)50 | bottom left FILLED: 177,14 (w:14,h:14)
            int flame = menu.getScaledProgressFlame();
            this.blit(pPoseStack, x+58, y+50-14+(14-flame), 177, 14-flame, 14, flame);
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
