package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.entities.EntityConcoction;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemConcoctionThrowable extends ItemConcoction {

    public ItemConcoctionThrowable() {
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".concoction.throwable");
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

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.none;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote && getConcoction(stack) != null) {
            world.spawnEntityInWorld(new EntityConcoction(world, player, getConcoction(stack), getIngredientPotency(stack), getDuration(stack)));
            if (!player.capabilities.isCreativeMode) --stack.stackSize;
            if (stack.stackSize <= 0) return new ItemStack(Items.glass_bottle);
            player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }
        return stack;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        return stack;
    }
}
