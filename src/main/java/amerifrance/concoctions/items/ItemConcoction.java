package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemConcoction extends Item implements IPropertiesContainer {

    public ItemConcoction() {
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".concoction");
        setTextureName("minecraft:potion_bottle_empty");
        setMaxDamage(0);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.drink;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (CreativeConcoctionsAPI.getConcoction(stack) != null) {
            player.setItemInUse(stack, getMaxItemUseDuration(stack));
        }
        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (CreativeConcoctionsAPI.getConcoction(stack) != null) {
            ConcoctionsHelper.addConcoction(player, CreativeConcoctionsAPI.getConcoction(stack), CreativeConcoctionsAPI.getLevel(stack), CreativeConcoctionsAPI.getDuration(stack));
            if (!player.capabilities.isCreativeMode) --stack.stackSize;
            if (stack.stackSize <= 0) return new ItemStack(Items.glass_bottle);
            player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }
        return stack;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (CreativeConcoctionsAPI.getConcoction(stack) != null) {
            return super.getItemStackDisplayName(stack) + " (" + CreativeConcoctionsAPI.getConcoction(stack).name + ")";
        } else {
            return super.getItemStackDisplayName(stack);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean simulate) {
        if (!GuiScreen.isShiftKeyDown()) return;

        list.add(CreativeConcoctionsAPI.getConcoction(stack).name);
        list.add(String.valueOf(CreativeConcoctionsAPI.getLevel(stack)));
        list.add(String.valueOf(CreativeConcoctionsAPI.getDuration(stack)));
    }
}
