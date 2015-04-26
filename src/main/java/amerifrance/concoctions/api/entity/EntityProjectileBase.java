package amerifrance.concoctions.api.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityProjectileBase extends EntityThrowable {

    public EntityLivingBase throwingEntity;

    public EntityProjectileBase(World world) {
        super(world);
    }

    public EntityProjectileBase(World world, EntityLivingBase thrower) {
        super(world, thrower);
        this.throwingEntity = thrower;
    }

    public EntityProjectileBase(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityProjectileBase(World world, EntityLivingBase thrower, EntityLivingBase target, float speed, float accuracy) {
        this(world);
        this.renderDistanceWeight = 10.0D;
        this.throwingEntity = thrower;

        if (speed > getMaxSpeed()) speed = getMaxSpeed();

        this.posY = thrower.posY + (double) thrower.getEyeHeight() - 0.10000000149011612D;
        double d0 = target.posX - thrower.posX;
        double d1 = target.boundingBox.minY + (double) (target.height / 3.0F) - this.posY;
        double d2 = target.posZ - thrower.posZ;
        double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D) {
            float f2 = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(thrower.posX + d4, this.posY, thrower.posZ + d5, f2, f3);
            this.yOffset = 0.0F;
            float f4 = (float) d3 * 0.2F;
            this.setThrowableHeading(d0, d1 + (double) f4, d2, speed, accuracy);
        }
    }

    @Override
    public void onImpact(MovingObjectPosition mop) {
    }

    @Override
    public float getGravityVelocity() {
        return super.getGravityVelocity();
    }

    public float getMaxSpeed() {
        return 3.9F;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        tagCompound.setInteger("ThrowingEntity", throwingEntity.getEntityId());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        super.readEntityFromNBT(tagCompound);

        this.throwingEntity = (EntityLivingBase) worldObj.getEntityByID(tagCompound.getInteger("ThrowingEntity"));
    }
}
