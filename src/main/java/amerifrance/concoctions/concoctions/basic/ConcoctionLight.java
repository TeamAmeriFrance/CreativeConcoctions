package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import amerifrance.concoctions.registry.BlocksRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.awt.*;

public class ConcoctionLight extends Concoction {

    public ConcoctionLight() {
        super("concoction.luman.liquor", 1, Color.YELLOW);
    }

    public void updateEffect(EntityLivingBase livingBase, IConcoctionContext ctx) {
        World world = livingBase.worldObj;

        int x = (int) Math.floor(livingBase.posX);
        int y = (int) livingBase.posY + 1;
        int z = (int) Math.floor(livingBase.posZ);

        if (!world.isRemote && world.getBlock(x, y, z) == Blocks.air)
            world.setBlock(x, y, z, BlocksRegistry.invisiLight);
    }
}
