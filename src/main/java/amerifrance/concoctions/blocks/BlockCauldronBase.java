package amerifrance.concoctions.blocks;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.ingredients.IngredientType;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import amerifrance.concoctions.tile.TileCauldronBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public abstract class BlockCauldronBase extends BlockContainer {

    public static int renderID = 1912;
    public IIcon innerSide;
    public IIcon topSide;
    public IIcon bottomSide;
    public IIcon itemTopSide;

    public BlockCauldronBase(Material material) {
        super(material);
        setBlockTextureName("cauldron");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (side == 1) return topSide;
        else if (side == 0) return bottomSide;
        else if (side == 7) return itemTopSide;
        else return blockIcon;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileCauldronBase cauldronBase = (TileCauldronBase) world.getTileEntity(x, y, z);
        if (cauldronBase.checkAndCraft(player, player.getHeldItem())) {
            cauldronBase.markForUpdate();
            return true;
        }
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        TileCauldronBase cauldronBase = (TileCauldronBase) world.getTileEntity(x, y, z);
        if (entity instanceof EntityItem) {
            EntityItem entityItem = (EntityItem) entity;
            ItemStack stack = entityItem.getEntityItem();
            int stacksize = stack.stackSize;
            Ingredient ingredient = IngredientsRegistry.getIngredient(new ItemStack(stack.getItem(), 1, stack.getItemDamage()));

            if (ingredient != null && cauldronBase.canCraft()) {
                cauldronBase.addIngredient(ingredient, stacksize);
                entityItem.setDead();
            } else if (stack.getItem() instanceof IPropertiesContainer && cauldronBase.canCraft()) {
                IPropertiesContainer propertiesContainer = (IPropertiesContainer) stack.getItem();
                List<IngredientProperties> list = propertiesContainer.getIngredientProperties(stack);
                if (list != null) {
                    cauldronBase.addIngredient(new Ingredient(IngredientType.NEUTRAL, 0F, CreativeConcoctionsAPI.getLevel(stack), list.toArray(new IngredientProperties[list.size()]), 200), stacksize);
                    entityItem.setDead();
                }
            }
        }
        cauldronBase.markForUpdate();
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        float f = 0.125F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        this.setBlockBoundsForItemRender();
    }

    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return renderID;
    }
}
