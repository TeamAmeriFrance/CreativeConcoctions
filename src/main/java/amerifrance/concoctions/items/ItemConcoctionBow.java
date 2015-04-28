package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.entities.EntityCoatedArrow;
import amerifrance.concoctions.registry.ItemsRegistry;
import amerifrance.concoctions.util.InventoryHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemConcoctionBow extends ItemBow {

    private IIcon[] icons;

    public ItemConcoctionBow() {
        setMaxStackSize(1);
        setMaxDamage(768);
        setTextureName("minecraft:bow");
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".concoction.bow");
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int itemInUseCount) {
        int j = this.getMaxItemUseDuration(stack) - itemInUseCount;
        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) return;
        j = event.charge;
        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        if (flag || player.inventory.hasItem(ItemsRegistry.coatedArrow)) {
            InventoryHelper.ItemStackAndSlot arrow = InventoryHelper.getItemFromInventory(ItemsRegistry.coatedArrow, player.inventory.mainInventory);
            if (arrow != null) {
                ItemStack arrowStack = arrow.stack;
                float f = (float) j / 20.0F;
                f = (f * f + f * 2.0F) / 3.0F;

                if ((double) f < 0.1D) return;

                if (f > 1.0F) f = 1.0F;

                EntityCoatedArrow entityCoatedArrow = new EntityCoatedArrow(world, player, f * 2.0F);
                entityCoatedArrow.concoction = ItemsRegistry.coatedArrow.getConcoction(arrowStack);
                entityCoatedArrow.level = ItemsRegistry.coatedArrow.getLevel(arrowStack);
                entityCoatedArrow.duration = ItemsRegistry.coatedArrow.getDuration(arrowStack);
                ItemStack copyStack = arrowStack.copy();
                copyStack.stackSize = 1;
                entityCoatedArrow.stack = copyStack;

                if (f == 1.0F) entityCoatedArrow.setIsCritical(true);
                int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
                if (k > 0) entityCoatedArrow.setDamage(entityCoatedArrow.getDamage() + (double) k * 0.5D + 0.5D);
                int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
                if (l > 0) entityCoatedArrow.setKnockbackStrength(l);
                if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
                    entityCoatedArrow.setFire(100);

                stack.damageItem(1, player);
                world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                if (flag) {
                    entityCoatedArrow.canBePickedUp = 2;
                } else {
                    arrowStack.stackSize--;
                    if (arrowStack.stackSize <= 0) arrowStack = null;
                    player.inventory.mainInventory[arrow.slot] = arrowStack;
                    player.inventoryContainer.detectAndSendChanges();
                }

                if (!world.isRemote) world.spawnEntityInWorld(entityCoatedArrow);
                return;
            }
        }

        if (flag || player.inventory.hasItem(Items.arrow)) {
            float f = (float) j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;
            if ((double) f < 0.1D) return;
            if (f > 1.0F) f = 1.0F;

            EntityArrow entityarrow = new EntityArrow(world, player, f * 2.0F);

            if (f == 1.0F) entityarrow.setIsCritical(true);
            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
            if (k > 0) entityarrow.setDamage(entityarrow.getDamage() + (double) k * 0.5D + 0.5D);
            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
            if (l > 0) entityarrow.setKnockbackStrength(l);
            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0) entityarrow.setFire(100);
            stack.damageItem(1, player);
            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag) entityarrow.canBePickedUp = 2;
            else player.inventory.consumeInventoryItem(Items.arrow);

            if (!world.isRemote) world.spawnEntityInWorld(entityarrow);
            return;
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        ArrowNockEvent event = new ArrowNockEvent(player, stack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) return event.result;

        if (player.capabilities.isCreativeMode || player.inventory.hasItem(ItemsRegistry.coatedArrow))
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));

        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.itemIcon = ir.registerIcon(this.getIconString() + "_standby");
        this.icons = new IIcon[bowPullIconNameArray.length];

        for (int i = 0; i < this.icons.length; ++i)
            this.icons[i] = ir.registerIcon(this.getIconString() + "_" + bowPullIconNameArray[i]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int i) {
        return this.icons[i];
    }

    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int itemInUseCount) {
        int j = stack.getMaxItemUseDuration() - itemInUseCount;
        if (j >= 18) return getItemIconForUseDuration(2);
        if (j > 13) return getItemIconForUseDuration(1);
        if (j > 0) return getItemIconForUseDuration(0);

        return super.getIcon(stack, renderPass, player, usingItem, itemInUseCount);
    }
}
