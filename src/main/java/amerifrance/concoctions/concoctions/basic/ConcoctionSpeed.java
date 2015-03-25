package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import java.awt.*;
import java.util.UUID;

public class ConcoctionSpeed extends Concoction {

    public UUID uuid;

    public ConcoctionSpeed() {
        super("concoction.speed", 60, Color.CYAN, ConcoctionType.GOOD);
        uuid = UUID.randomUUID();
    }

    @Override
    public void onEffectAdded(EntityLivingBase livingBase, IConcoctionContext ctx) {
        AttributeModifier r = livingBase.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getModifier(uuid);
        if (r != null) livingBase.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(r);

        //This is the same modification as the Speed Potion one. We can change it
        AttributeModifier modifier = new AttributeModifier(uuid, name, 0.20000000298023224D * ctx.getConcoctionLevel(), 2);
        livingBase.getEntityAttribute(SharedMonsterAttributes.movementSpeed).applyModifier(modifier);
    }

    @Override
    public void onEffectRemoved(EntityLivingBase livingBase, IConcoctionContext ctx) {
        AttributeModifier r = livingBase.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getModifier(uuid);
        if (r != null) livingBase.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(r);
    }
}
