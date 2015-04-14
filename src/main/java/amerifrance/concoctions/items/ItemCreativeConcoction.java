package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import amerifrance.concoctions.api.registry.ConcoctionsRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemCreativeConcoction extends Item {

    public ItemCreativeConcoction() {
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".creativeConcoction");
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
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!ConcoctionsRegistry.isMapEmtpy() && ConcoctionsRegistry.getMapSize() > stack.getItemDamage()) {
            Concoction concoction = ConcoctionsRegistry.getConcoctions().get(stack.getItemDamage());
            if (ConcoctionsHelper.isConcoctionActive(player, concoction)) {
                IConcoctionContext ctx = ConcoctionsHelper.getActiveConcoction(player, concoction);
                if (ctx != null && ctx.getConcoctionLevel() + 1 <= ctx.getConcoction().maxLevel) {
                    ctx.setLevel(ctx.getConcoctionLevel() + 1);
                    ctx.setTicksLeft(ctx.getInitialDuration());
                    if (!world.isRemote) ctx.onAdded(player);
                }
            } else {
                ConcoctionsHelper.addConcoction(player, concoction, 1, 1000);
            }
        }
        return stack;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (!ConcoctionsRegistry.isMapEmtpy() && ConcoctionsRegistry.getMapSize() > stack.getItemDamage()) {
            return ConcoctionsRegistry.getConcoctions().get(stack.getItemDamage()).name;
        } else {
            return super.getItemStackDisplayName(stack);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        if (!ConcoctionsRegistry.isMapEmtpy()) {
            for (int i = 0; i < ConcoctionsRegistry.getMapSize(); i++) {
                list.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (!ConcoctionsRegistry.isMapEmtpy() && ConcoctionsRegistry.getMapSize() > stack.getItemDamage()) {
            String name = String.valueOf(stack.getItemDamage());
            return getUnlocalizedName() + "." + name;
        } else {
            return super.getUnlocalizedName(stack);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int pass) {
        if (!ConcoctionsRegistry.isMapEmtpy() && ConcoctionsRegistry.getMapSize() > stack.getItemDamage()) {
            return ConcoctionsRegistry.getConcoctions().get(stack.getItemDamage()).color.getRGB();
        } else {
            return super.getColorFromItemStack(stack, pass);
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean simulate) {
        if (!GuiScreen.isShiftKeyDown()) return;

        list.add(StatCollector.translateToLocal("tooltip.creative.only"));
        list.add("Adds the effect with its max level, for 1000 ticks (50 secs)");
        list.add("Drink to make the concoction level go up");
    }
}
