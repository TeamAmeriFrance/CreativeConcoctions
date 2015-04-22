package amerifrance.concoctions.blocks;

import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class BlockCauldronBase extends BlockContainer {

    public static int renderID = 1912;
    public IIcon[] innerSide = new IIcon[16];
    public IIcon[] topSide = new IIcon[16];
    public IIcon[] bottomSide = new IIcon[16];
    public IIcon[] outSide = new IIcon[16];

    public BlockCauldronBase(Material material) {
        super(material);
        setBlockTextureName("cauldron");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (side == -1) return innerSide[metadata];
        else if (side == 0) return bottomSide[metadata];
        else if (side == 1) return topSide[metadata];
        else return outSide[metadata];
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
        if (entity != null && entity instanceof EntityItem) {
            EntityItem entityItem = (EntityItem) entity;
            ItemStack stack = entityItem.getEntityItem();
            int stacksize = stack.stackSize;
            Ingredient ingredient = IngredientsRegistry.getIngredient(new ItemStack(stack.getItem(), 1, stack.getItemDamage()));

            if (ingredient != null && cauldronBase.canCraft()) {
                cauldronBase.addIngredient(ingredient, stacksize);
                if (entityItem.getEntityItem().getItem() == Items.water_bucket) {
                    entityItem.setEntityItemStack(new ItemStack(Items.bucket));
                } else {
                    entityItem.setDead();
                }
            } else if (stack.getItem() instanceof IPropertiesContainer && cauldronBase.canCraft()) {
                IPropertiesContainer propertiesContainer = (IPropertiesContainer) stack.getItem();
                List<IngredientProperty> list = propertiesContainer.getIngredientProperties(stack);
                if (list != null) {
                    cauldronBase.addIngredient(new Ingredient(IngredientType.NEUTRAL, 0F, propertiesContainer.getIngredientPotency(stack), 200, list.toArray(new IngredientProperty[list.size()])), stacksize);
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

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return null;
    }
}
