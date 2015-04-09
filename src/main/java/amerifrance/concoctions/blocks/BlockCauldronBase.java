package amerifrance.concoctions.blocks;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientType;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import amerifrance.concoctions.tile.TileCauldronBase;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BlockCauldronBase extends BlockContainer {

    public BlockCauldronBase(Material material) {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileCauldronBase cauldronBase = (TileCauldronBase) world.getTileEntity(x, y, z);
        if (cauldronBase != null && cauldronBase.checkAndCraft(player.getHeldItem())) {
            return true;
        }
        return false;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        TileCauldronBase cauldronBase = (TileCauldronBase) world.getTileEntity(x, y, z);
        if (cauldronBase != null && entity != null && entity instanceof EntityItem) {
            EntityItem entityItem = (EntityItem) entity;
            ItemStack stack = entityItem.getEntityItem();
            int stacksize = stack.stackSize;
            Ingredient ingredient = IngredientsRegistry.getIngredient(new ItemStack(stack.getItem(), 1, stack.getItemDamage()));

            for (int i = 0; i < stacksize; i++) {
                if (ingredient != null && cauldronBase.canCraft()) {
                    cauldronBase.ticksLeft += ingredient.ticksToBoil * (cauldronBase.getHeatCapacity() / cauldronBase.heat);
                    cauldronBase.stability += ingredient.stability * (cauldronBase.getHeatCapacity() / cauldronBase.heat);
                    cauldronBase.cauldronContent.add(ingredient);
                    entityItem.getEntityItem().stackSize--;
                } else if (stack.getItem() instanceof IPropertiesContainer) {
                    cauldronBase.ticksLeft += 200;
                    cauldronBase.cauldronContent.add(new Ingredient(IngredientType.NEUTRAL, 0F, CreativeConcoctionsAPI.getLevel(stack), CreativeConcoctionsAPI.getIngredientProperties(stack), 0));
                    entityItem.getEntityItem().stackSize--;
                }
            }
        }
    }
}
