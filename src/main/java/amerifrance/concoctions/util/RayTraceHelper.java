package amerifrance.concoctions.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

import java.util.ArrayList;
import java.util.List;

public class RayTraceHelper {

    //This method was taken from the RayTracer class in AsieLib, with the permission of Vexatos. I only adaptated it a bit.
    public static Entity rayTrace(EntityLivingBase entity, double distance, float par3) {
        final Vec3 position = entity.getPosition(par3);
        if (entity.getEyeHeight() != 0.12F) position.yCoord += entity.getEyeHeight();
        Vec3 look = entity.getLook(par3);
        for (double i = 1.0; i < distance; i += 0.2) {
            Vec3 search = position.addVector(look.xCoord * i, look.yCoord * i, look.zCoord * i);
            AxisAlignedBB searchBox = AxisAlignedBB.getBoundingBox(search.xCoord - 0.1, search.yCoord - 0.1, search.zCoord - 0.1, search.xCoord + 0.1, search.yCoord + 0.1, search.zCoord + 0.1);
            MovingObjectPosition blockCheck = entity.worldObj.rayTraceBlocks(Vec3.createVectorHelper(position.xCoord, position.yCoord, position.zCoord), search, false);
            if (blockCheck != null && blockCheck.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                if (position.distanceTo(blockCheck.hitVec) < position.distanceTo(search)) return null;
            }
            Entity target = getLivingBase(entity, position, search, look, searchBox, 0.1);
            if (target != null) return target;
        }
        return null;
    }

    //This method was taken from the RayTracer class in AsieLib, with the permission of Vexatos. I only adaptated it a bit.
    private static Entity getLivingBase(EntityLivingBase base, Vec3 position, Vec3 search, Vec3 look, AxisAlignedBB searchBox, double v) {
        ArrayList<Entity> entityList = new ArrayList<Entity>();
        List<Entity> entityObjects = (List<Entity>) base.worldObj.getEntitiesWithinAABB(Entity.class, searchBox);
        for (Entity e : entityObjects) if (e != base && e.canBeCollidedWith()) entityList.add(e);
        if (entityList.size() <= 0) return null;

        Entity entity = null;
        if (entityList.size() > 1) {
            for (Entity e : entityList) {
                if (entity == null || position.distanceTo(Vec3.createVectorHelper(e.posX, e.posY, e.posZ)) < position.distanceTo(Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ))) {
                    entity = e;
                }
            }
        } else {
            entity = entityList.get(0);
        }
        return entity;
    }
}
