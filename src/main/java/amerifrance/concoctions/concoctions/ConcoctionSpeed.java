package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.objects.Concoction;
import amerifrance.concoctions.objects.ConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.StatCollector;

import java.awt.*;
import java.util.UUID;

public class ConcoctionSpeed extends Concoction {

    public UUID uuid;

    public ConcoctionSpeed() {
        super(StatCollector.translateToLocal("concoction.speed"), 60, Color.CYAN);
        uuid = UUID.randomUUID();
    }

    @Override
    public void onEffectAdded(EntityLivingBase livingBase, ConcoctionContext ctx) {
        AttributeModifier r = livingBase.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getModifier(uuid);
        if (r != null) livingBase.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(r);

        AttributeModifier modifier = new AttributeModifier(uuid, name, 0.20000000298023224D * ctx.getConcoctionLevel(), 2);
        livingBase.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(modifier);
    }

    @Override
    public void onEffectRemoved(EntityLivingBase livingBase, ConcoctionContext ctx) {
        AttributeModifier r = livingBase.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getModifier(uuid);
        if (r != null) livingBase.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(r);
    }
}
