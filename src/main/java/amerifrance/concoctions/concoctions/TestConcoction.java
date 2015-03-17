package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.objects.Concoction;
import amerifrance.concoctions.objects.ConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;

import java.awt.*;

public class TestConcoction extends Concoction {
    public TestConcoction() {
        super("TestConcoction", 1, Color.BLUE);
    }

    @Override
    public void updateEffect(EntityLivingBase entityLivingBase, ConcoctionContext wrapper) {
        entityLivingBase.worldObj.setBlock((int) entityLivingBase.posX, (int) entityLivingBase.posY + 4, (int) entityLivingBase.posZ, Blocks.diamond_block);
    }

    @Override
    public void onEffectAdded(EntityLivingBase entityLivingBase, ConcoctionContext wrapper) {
        System.out.println("Added");
    }

    @Override
    public void onEffectRemoved(EntityLivingBase entityLivingBase, ConcoctionContext wrapper) {
        System.out.println("Removed");
    }
}
