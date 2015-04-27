package amerifrance.concoctions.entities;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.entity.EntityProjectileBase;
import amerifrance.concoctions.util.event.ConcoctionHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class EntityConcoction extends EntityProjectileBase {

    public Concoction concoction;
    public int level;
    public int duration;
    private float gravity;

    public EntityConcoction(World world) {
        super(world);
        gravity = 0.06F;
    }

    public EntityConcoction(World world, Concoction concoction, int level, int duration) {
        this(world);
        this.concoction = concoction;
        this.level = level;
        this.duration = duration;
        gravity = 0.06F;
    }

    public EntityConcoction(World world, EntityLivingBase thrower, Concoction concoction, int level, int duration) {
        super(world, thrower);
        this.concoction = concoction;
        this.level = level;
        this.duration = duration;
        gravity = 0.06F;
    }

    public EntityConcoction(World world, double x, double y, double z, Concoction concoction, int level, int duration) {
        super(world, x, y, z);
        this.concoction = concoction;
        this.level = level;
        this.duration = duration;
        gravity = 0.06F;
    }

    public EntityConcoction(World world, EntityLivingBase thrower, EntityLivingBase target, float speed, float accuracy, Concoction concoction, int level, int duration) {
        super(world, thrower, target, speed, accuracy);
        this.concoction = concoction;
        this.level = level;
        this.duration = duration;
        gravity = 0.06F;
    }

    @Override
    public void onImpact(MovingObjectPosition mop) {
        if (!worldObj.isRemote) {
            AxisAlignedBB axisalignedbb = this.boundingBox.expand(4.0D, 4.0D, 4.0D);
            List<EntityLivingBase> entities = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
            if (entities != null && !entities.isEmpty()) {
                Iterator<EntityLivingBase> iterator = entities.iterator();
                while (iterator.hasNext()) {
                    EntityLivingBase hitEntity = iterator.next();
                    double distanceToHit = this.getDistanceSqToEntity(hitEntity);
                    if (distanceToHit < 16.0D) {
                        double durationMultiplier = 1.0D - Math.sqrt(distanceToHit) / 4.0D;
                        if (hitEntity == mop.entityHit) durationMultiplier = 1.0D;
                        ConcoctionsHelper.addConcoction(hitEntity, concoction, level, (int) (duration * durationMultiplier));
                        if (throwingEntity instanceof EntityPlayer)
                            ConcoctionHandler.syncConcoctions(hitEntity, (EntityPlayerMP) throwingEntity);
                    }
                }
            }
            this.setDead();
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        super.readEntityFromNBT(tagCompound);

        NBTTagCompound tag = tagCompound.getCompoundTag("ConcoctionTag");
        this.concoction = Concoction.readFromNBT(tag);
        this.level = tag.getInteger("Level");
        this.duration = tag.getInteger("Duration");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        NBTTagCompound tag = new NBTTagCompound();
        concoction.writeToNBT(tag);
        tag.setInteger("Level", level);
        tag.setInteger("Duration", duration);

        tagCompound.setTag("ConcoctionTag", tag);
    }

    @Override
    public float getGravityVelocity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
}
