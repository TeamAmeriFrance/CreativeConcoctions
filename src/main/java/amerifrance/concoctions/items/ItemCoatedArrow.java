package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.registry.ConcoctionsRegistry;
import amerifrance.concoctions.api.util.NBTTags;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemCoatedArrow extends Item {

    public ItemCoatedArrow() {
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".coated.arrow");
        setTextureName("minecraft:arrow");
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.itemIcon = ir.registerIcon("minecraft:arrow");
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (getConcoction(stack) != null)
            return super.getItemStackDisplayName(stack) + " (" + getConcoction(stack).getConcotionType().prefix + getConcoction(stack).name + EnumChatFormatting.RESET + ")";
        else
            return super.getItemStackDisplayName(stack);
    }

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean simulate) {
        if (!GuiScreen.isShiftKeyDown()) return;

        if (getConcoction(stack) != null) {
            list.add(getConcoction(stack).getConcotionType().prefix + getConcoction(stack).name);
            list.add(String.format(StatCollector.translateToLocal("gui.text.level"), getLevel(stack)));
            if (!getConcoction(stack).isConcoctionInstant())
                list.add(String.format(StatCollector.translateToLocal("gui.text.duration"), (double) getDuration(stack) / 20 + "s"));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        if (!ConcoctionsRegistry.isMapEmtpy()) {
            for (Concoction concoction : ConcoctionsRegistry.getConcoctions()) {
                ItemStack stack = new ItemStack(this);
                CreativeConcoctionsAPI.checkAndSetCompound(stack);
                setConcoction(stack, concoction);
                setLevel(stack, 2);
                setDuration(stack, 1000);
                list.add(stack);
            }
        }
    }

    public Concoction getConcoction(ItemStack stack) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        return ConcoctionsRegistry.getConcoctionForId(stack.stackTagCompound.getString(NBTTags.ID_TAG));
    }

    public int getDuration(ItemStack stack) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        return stack.stackTagCompound.getInteger(NBTTags.DURATION_TAG);
    }

    public int getLevel(ItemStack stack) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        return stack.stackTagCompound.getInteger(NBTTags.LEVEL_TAG);
    }

    public void setConcoction(ItemStack stack, Concoction concoction) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        stack.stackTagCompound.setString(NBTTags.ID_TAG, ConcoctionsRegistry.getIdForConcoction(concoction));
    }

    public void setDuration(ItemStack stack, int duration) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        stack.stackTagCompound.setInteger(NBTTags.DURATION_TAG, duration);
    }

    public void setLevel(ItemStack stack, int level) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        stack.stackTagCompound.setInteger(NBTTags.LEVEL_TAG, level);
    }
}
