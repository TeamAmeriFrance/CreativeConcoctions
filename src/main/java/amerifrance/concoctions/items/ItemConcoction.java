package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.registry.ConcoctionRecipes;
import net.minecraft.entity.player.EntityPlayer;
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

    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
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
        if (CreativeConcoctionsAPI.getConcoctionContext(stack) != null) {
            ConcoctionsHelper.addConcoction(player, CreativeConcoctionsAPI.getConcoctionContext(stack));
            if (!player.capabilities.isCreativeMode) --stack.stackSize;
            if (stack.stackSize <= 0) return new ItemStack(this);
            player.inventory.addItemStackToInventory(new ItemStack(this));
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
    public IngredientProperties[] getIngredientProperties(ItemStack stack) {
        if (CreativeConcoctionsAPI.getConcoction(stack) != null)
            return ConcoctionRecipes.getIngredientsForConcoction(CreativeConcoctionsAPI.getConcoction(stack));
        else
            return null;
    }

    @Override
    public int getPotency(ItemStack stack) {
        return CreativeConcoctionsAPI.getLevel(stack);
    }
}
