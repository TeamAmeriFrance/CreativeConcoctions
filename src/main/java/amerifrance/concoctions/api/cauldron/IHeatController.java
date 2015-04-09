package amerifrance.concoctions.api.cauldron;

import net.minecraft.world.World;

public interface IHeatController {

    public void handleCauldronHeat(ICauldron iCauldron, World world, int cauldronX, int cauldronY, int cauldronZ);
}
