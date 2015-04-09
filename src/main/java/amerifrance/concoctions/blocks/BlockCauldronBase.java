package amerifrance.concoctions.blocks;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.ingredients.IngredientType;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import amerifrance.concoctions.tile.TileCauldronBase;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public abstract class BlockCauldronBase extends BlockContainer {

    public BlockCauldronBase(Material material) {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileCauldronBase cauldronBase = (TileCauldronBase) world.getTileEntity(x, y, z);
        if (cauldronBase.checkAndCraft(player, player.getHeldItem())) {
            return true;
        }
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        TileCauldronBase cauldronBase = (TileCauldronBase) world.getTileEntity(x, y, z);
        if (!world.isRemote && entity instanceof EntityItem) {
            EntityItem entityItem = (EntityItem) entity;
            ItemStack stack = entityItem.getEntityItem();
            int stacksize = stack.stackSize;
            Ingredient ingredient = IngredientsRegistry.getIngredient(new ItemStack(stack.getItem(), 1, stack.getItemDamage()));

            for (int i = 0; i < stacksize; i++) {
                if (ingredient != null && cauldronBase.canCraft()) {
                    cauldronBase.addIngredient(ingredient);
                    entityItem.getEntityItem().stackSize--;
                } else if (stack.getItem() instanceof IPropertiesContainer && cauldronBase.canCraft()) {
                    List<IngredientProperties> list = CreativeConcoctionsAPI.getIngredientProperties(stack);
                    cauldronBase.addIngredient(new Ingredient(IngredientType.NEUTRAL, 0F, CreativeConcoctionsAPI.getLevel(stack), list.toArray(new IngredientProperties[list.size()]), 200));
                    entityItem.getEntityItem().stackSize--;
                }
            }
        }
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

    public boolean isOpaqueCube() {
        return false;
    }

    //public int getRenderType()
    //{
    //    return 24;
    // }

    public boolean renderAsNormalBlock() {
        return false;
    }
}
