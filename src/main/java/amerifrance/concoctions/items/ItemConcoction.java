package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import amerifrance.concoctions.util.ConcoctionContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
        player.setItemInUse(stack, getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (getConcoctionContext(stack) != null) {
            ConcoctionsHelper.addConcoction(player, getConcoctionContext(stack));
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

    public static IConcoctionContext getConcoctionContext(ItemStack stack) {
        if (CreativeConcoctionsAPI.getConcoction(stack) != null)
            return new ConcoctionContext(CreativeConcoctionsAPI.getConcoction(stack), CreativeConcoctionsAPI.getLevel(stack), CreativeConcoctionsAPI.getDuration(stack));
        else
            return null;
    }
}
