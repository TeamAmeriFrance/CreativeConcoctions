package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import amerifrance.concoctions.api.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.StatCollector;

import java.awt.*;
import java.util.UUID;

public class ConcoctionStrength extends Concoction {

    public UUID uuid;

    public ConcoctionStrength() {
        super(StatCollector.translateToLocal("concoction.strength"), 60, Color.GREEN, ConcoctionType.GOOD);
        uuid = UUID.randomUUID();
    }

    @Override
    public void onEffectAdded(EntityLivingBase livingBase, IConcoctionContext ctx) {
        AttributeModifier r = livingBase.getEntityAttribute(SharedMonsterAttributes.attackDamage).getModifier(uuid);
        if (r != null) livingBase.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(r);

        AttributeModifier modifier = new AttributeModifier(uuid, name, 1.0D * ctx.getConcoctionLevel(), 2);
        livingBase.getEntityAttribute(SharedMonsterAttributes.attackDamage).applyModifier(modifier);
    }

    @Override
    public void onEffectRemoved(EntityLivingBase livingBase, IConcoctionContext ctx) {
        AttributeModifier r = livingBase.getEntityAttribute(SharedMonsterAttributes.attackDamage).getModifier(uuid);
        if (r != null) livingBase.getEntityAttribute(SharedMonsterAttributes.attackDamage).removeModifier(r);
    }
}