package amerifrance.concoctions.tile;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.registry.ModConcoctions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class TileCauldron extends TileCauldronBase {

    public TileCauldron(int ingredientCapacity, float heatCapacity, float maxUnstability) {
        super(ingredientCapacity, heatCapacity, maxUnstability);
    }

    @Override
    public void meltCauldron() {
        if (getHeat() > getHeatCapacity() + getHeatCapacity() / 3) {
            List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 1, yCoord + 1, zCoord + 1));
            for (EntityLivingBase livingBase : list) livingBase.setFire(5);
        }

        if (worldObj.isRemote)
            CreativeConcoctions.proxy.cauldronMelt(worldObj, xCoord, yCoord + getLiquidHeightForRender(), zCoord, 0);

        if (worldObj.isAirBlock(xCoord, yCoord + 1, zCoord) && worldObj.rand.nextInt(150) == 0)
            worldObj.setBlock(xCoord, yCoord + 1, zCoord, Blocks.fire);

        if (getHeat() > getHeatCapacity() + getHeatCapacity() / 2) {
            worldObj.setBlockToAir(xCoord, yCoord, zCoord);

            if (worldObj.isRemote)
                CreativeConcoctions.proxy.cauldronMelt(worldObj, xCoord, yCoord + getLiquidHeightForRender(), zCoord, 1);
        }
    }

    @Override
    public void cauldronUnstable() {
        if (worldObj.isRemote)
            CreativeConcoctions.proxy.poisonousFume(worldObj, xCoord, yCoord + getLiquidHeightForRender(), zCoord);

        if (worldObj.getTotalWorldTime() % 60 == 0) {
            List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord - 6, yCoord - 6, zCoord - 6, xCoord + 6, yCoord + 6, zCoord + 6));
            for (EntityLivingBase livingBase : list) {
                ConcoctionsHelper.addConcoction(livingBase, ModConcoctions.poison, 2, 150);
            }
            instability = 5 * instability / 6;
        }
    }

    @Override
    public void cauldronOverflow() {
        if (worldObj.isAirBlock(xCoord, yCoord + 1, zCoord) && worldObj.rand.nextInt(150) == 0) {
            //TODO: Change the water to something else more "magic-y" -- Rapthera
            worldObj.setBlock(xCoord, yCoord + 1, zCoord, Blocks.water);
        } else {
            int overflow = cauldronContent.size() - getIngredientCapacity();

            if (worldObj.getTotalWorldTime() % 60 == 0)
                cauldronContent.remove(worldObj.rand.nextInt(cauldronContent.size()));

            if (worldObj.isRemote)
                CreativeConcoctions.proxy.cauldronSplash(worldObj, xCoord, yCoord + getLiquidHeightForRender(), zCoord, overflow);
        }
    }

    @Override
    public void invalidRecipe() {
        //TODO: Change this. Yes, I know, I didn't know what to do :p
        if (!worldObj.isRemote) {
            EntitySkeleton skeleton = new EntitySkeleton(worldObj);
            skeleton.setPosition(xCoord, yCoord + 1, zCoord);
            worldObj.spawnEntityInWorld(skeleton);
        }
    }

    public static class TileCauldronWood extends TileCauldron {
        public TileCauldronWood() {
            super(8, 15, 12);
        }
    }

    public static class TileCauldronStone extends TileCauldron {
        public TileCauldronStone() {
            super(12, 355, 18);
        }
    }

    public static class TileCauldronIron extends TileCauldron {
        public TileCauldronIron() {
            super(32, 235, 24);
        }
    }

    public static class TileCauldronGold extends TileCauldron {
        public TileCauldronGold() {
            super(48, 115, 32);
        }
    }

    public static class TileCauldronObsidian extends TileCauldron {
        public TileCauldronObsidian() {
            super(24, 475, 28);
        }
    }

    public static class TileCauldronPewter extends TileCauldron {
        public TileCauldronPewter() {
            super(18, 80, 40);
        }
    }
}
