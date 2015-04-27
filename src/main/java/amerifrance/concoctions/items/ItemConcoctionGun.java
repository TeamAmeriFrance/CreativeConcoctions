package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.entities.EntityConcoction;
import amerifrance.concoctions.registry.ItemsRegistry;
import amerifrance.concoctions.util.InventoryHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemConcoctionGun extends Item {

    public ItemConcoctionGun() {
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".concoction.gun");
        setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.inventory.hasItem(ItemsRegistry.concoctionItem)) {
            InventoryHelper.ItemStackAndSlot stackAndSlot = InventoryHelper.getItemFromInventory(ItemsRegistry.concoctionItem, player.inventory.mainInventory);
            ItemStack concoctionStack = stackAndSlot.stack;
            if (concoctionStack != null) {
                EntityConcoction entityConcoction = new EntityConcoction(world, player, ItemConcoction.getConcoction(concoctionStack), ItemsRegistry.concoctionItem.getIngredientPotency(concoctionStack), ItemsRegistry.concoctionItem.getDuration(concoctionStack));
                entityConcoction.setGravity(0.03F);
                world.spawnEntityInWorld(entityConcoction);
                if (!player.capabilities.isCreativeMode)
                    player.inventory.mainInventory[stackAndSlot.slot] = null;
            }
        } else if (player.inventory.hasItem(ItemsRegistry.concoctionThrowable)) {
            InventoryHelper.ItemStackAndSlot stackAndSlot = InventoryHelper.getItemFromInventory(ItemsRegistry.concoctionThrowable, player.inventory.mainInventory);
            ItemStack concoctionStack = stackAndSlot.stack;
            if (concoctionStack != null) {
                EntityConcoction entityConcoction = new EntityConcoction(world, player, ItemConcoctionThrowable.getConcoction(concoctionStack), ItemsRegistry.concoctionThrowable.getIngredientPotency(concoctionStack), ItemsRegistry.concoctionThrowable.getDuration(concoctionStack));
                entityConcoction.setGravity(0.02F);
                world.spawnEntityInWorld(entityConcoction);
                if (!player.capabilities.isCreativeMode)
                    player.inventory.mainInventory[stackAndSlot.slot] = null;
            }
        }
        return stack;
    }

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean simulate) {
        if(!GuiScreen.isShiftKeyDown()) return;

        list.add(StatCollector.translateToLocal("gui.text.concoction.gun.1"));
        list.add(StatCollector.translateToLocal("gui.text.concoction.gun.2"));
    }
}
