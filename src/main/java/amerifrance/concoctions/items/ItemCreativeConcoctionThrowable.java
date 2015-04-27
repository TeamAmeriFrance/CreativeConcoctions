package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.registry.ConcoctionsRegistry;
import amerifrance.concoctions.entities.EntityConcoction;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemCreativeConcoctionThrowable extends ItemCreativeConcoction {

    public ItemCreativeConcoctionThrowable() {
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".creativeConcoction.throwable");
        setMaxDamage(0);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.itemIcon = ir.registerIcon("minecraft:potion_bottle_splash");
        this.overlayIcon = ir.registerIcon("minecraft:potion_overlay");
    }

    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.none;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote && !ConcoctionsRegistry.isMapEmtpy() && ConcoctionsRegistry.getMapSize() > stack.getItemDamage()) {
            Concoction concoction = ConcoctionsRegistry.getConcoctions().get(stack.getItemDamage());
            world.spawnEntityInWorld(new EntityConcoction(world, player, concoction, 2, 1000));
        }
        return stack;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        return stack;
    }

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean simulate) {
        list.add(EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocal("tooltip.creative.only"));
        if (GuiScreen.isShiftKeyDown()) {
            list.add("Adds the effect level 2, for 1000 ticks (50 secs)");
        }
    }
}
