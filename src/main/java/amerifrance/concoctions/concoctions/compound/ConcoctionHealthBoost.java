package amerifrance.concoctions.concoctions.compound;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import amerifrance.concoctions.registry.ModConcoctions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;

import java.awt.*;
import java.util.UUID;

public class ConcoctionHealthBoost extends Concoction {

    public UUID uuid;

    public ConcoctionHealthBoost() {
        super("concoction.health.boost", 20, Color.PINK, ConcoctionType.GOOD, ModConcoctions.heal, ModConcoctions.regeneration);
        uuid = UUID.randomUUID();
    }

    @Override
    public void onEffectAdded(EntityLivingBase livingBase, IConcoctionContext ctx) {
        AttributeModifier r = livingBase.getEntityAttribute(SharedMonsterAttributes.maxHealth).getModifier(uuid);
        if (r != null) livingBase.getEntityAttribute(SharedMonsterAttributes.maxHealth).removeModifier(r);

        AttributeModifier modifier = new AttributeModifier(uuid, name, 0.05 * ctx.getConcoctionLevel(), 2);
        livingBase.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(modifier);
    }

    @Override
    public void onEffectRemoved(EntityLivingBase livingBase, IConcoctionContext ctx) {
        AttributeModifier r = livingBase.getEntityAttribute(SharedMonsterAttributes.maxHealth).getModifier(uuid);
        if (r != null) livingBase.getEntityAttribute(SharedMonsterAttributes.maxHealth).removeModifier(r);
    }
}
