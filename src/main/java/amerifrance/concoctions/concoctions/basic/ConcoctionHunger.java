package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;

public class ConcoctionHunger extends Concoction {

    public ConcoctionHunger() {
        super("concoction.hunger", 10, Color.YELLOW, ConcoctionType.BAD);
    }

    @Override
    public void updateEffect(EntityLivingBase livingBase, IConcoctionContext ctx) {
        if (livingBase instanceof EntityPlayer) {
            ((EntityPlayer) livingBase).addExhaustion(0.01F * ctx.getConcoctionLevel());
        }
    }
}
