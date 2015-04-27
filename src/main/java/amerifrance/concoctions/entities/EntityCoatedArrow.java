package amerifrance.concoctions.entities;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.entity.EntityProjectileBase;
import amerifrance.concoctions.util.event.ConcoctionHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCoatedArrow extends EntityProjectileBase {

    public ItemStack stack;
    public Concoction concoction;
    public int level;
    public int duration;
    public int knockbackStrength = 2;

    public EntityCoatedArrow(World world) {
        super(world);
    }

    public EntityCoatedArrow(World world, ItemStack stack, Concoction concoction, int level, int duration) {
        this(world);
        this.stack = stack;
        this.concoction = concoction;
        this.level = level;
        this.duration = duration;
    }

    public EntityCoatedArrow(World world, EntityLivingBase thrower, ItemStack stack, Concoction concoction, int level, int duration) {
        super(world, thrower);
        this.stack = stack;
        this.concoction = concoction;
        this.level = level;
        this.duration = duration;
    }

    public EntityCoatedArrow(World world, double x, double y, double z, ItemStack stack, Concoction concoction, int level, int duration) {
        super(world, x, y, z);
        this.stack = stack;
        this.concoction = concoction;
        this.level = level;
        this.duration = duration;
    }

    public EntityCoatedArrow(World world, EntityLivingBase thrower, EntityLivingBase target, float speed, float accuracy, ItemStack stack, Concoction concoction, int level, int duration) {
        super(world, thrower, target, speed, accuracy);
        this.stack = stack;
        this.concoction = concoction;
        this.level = level;
        this.duration = duration;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
        super.onCollideWithPlayer(player);

        if (!worldObj.isRemote && inGround && throwableShake == 0 && player.inventory.addItemStackToInventory(stack)) {
            this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            player.onItemPickup(this, 1);
            this.setDead();
        }
    }

    @Override
    public void onImpact(MovingObjectPosition mop) {
        if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            Entity entity = mop.entityHit;
            if (entity instanceof EntityLivingBase) {
                float multiplier = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                int damage = MathHelper.ceiling_double_int((double) multiplier * 2.0D);

                if (this.knockbackStrength > 0) {
                    float f4 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

                    if (f4 > 0.0F) {
                        entity.addVelocity(this.motionX * (double) this.knockbackStrength * 0.6000000238418579D / (double) f4, 0.1D, this.motionZ * (double) this.knockbackStrength * 0.6000000238418579D / (double) f4);
                    }
                }

                if (!worldObj.isRemote) {
                    entity.attackEntityFrom(new EntityDamageSourceIndirect("arrow", this, entity).setProjectile(), damage);
                    ConcoctionsHelper.addConcoction((EntityLivingBase) entity, concoction, level, duration);
                    if (throwingEntity instanceof EntityPlayer)
                        ConcoctionHandler.syncConcoctions((EntityLivingBase) entity, (EntityPlayerMP) throwingEntity);
                }
            }
            this.setDead();
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound) {
        super.readEntityFromNBT(tagCompound);

        this.stack = ItemStack.loadItemStackFromNBT(tagCompound.getCompoundTag("ArrowItemStack"));

        NBTTagCompound tag = tagCompound.getCompoundTag("ConcoctionTag");
        this.concoction = Concoction.readFromNBT(tag);
        this.level = tag.getInteger("Level");
        this.duration = tag.getInteger("Duration");
        this.knockbackStrength = tag.getInteger("KnockbackStrength");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        NBTTagCompound tag = new NBTTagCompound();
        concoction.writeToNBT(tag);
        tag.setInteger("Level", level);
        tag.setInteger("Duration", duration);

        NBTTagCompound arrow = new NBTTagCompound();
        stack.writeToNBT(arrow);

        tagCompound.setTag("ArrowItemStack", arrow);
        tagCompound.setTag("ConcoctionTag", tag);
        tagCompound.setInteger("KnockbackStrength", knockbackStrength);
    }
}
