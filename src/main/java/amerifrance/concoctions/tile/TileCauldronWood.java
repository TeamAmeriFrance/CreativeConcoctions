package amerifrance.concoctions.tile;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.registry.ModConcoctions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class TileCauldronWood extends TileCauldronBase {

    public TileCauldronWood() {
        super(8, 20, 12);
    }

    @Override
    public void meltCauldron() {
    }

    @Override
    public void cauldronUnstable() {
        if (worldObj.isRemote)
            CreativeConcoctions.proxy.poisonousFume(worldObj, xCoord, yCoord + getLiquidHeightForRender(), zCoord);

        List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord - 6, yCoord - 6, zCoord - 6, xCoord + 6, yCoord + 6, zCoord + 6));
        for (EntityLivingBase livingBase : list) {
            ConcoctionsHelper.addConcoction(livingBase, ModConcoctions.poison, 2, 150);
        }
        unstability /= 2;
    }

    @Override
    public void cauldronOverflow() {
    }

    @Override
    public void invalidRecipe() {
    }
}
