package amerifrance.concoctions.tile;

import net.minecraft.item.ItemStack;

public class TileCauldronTest extends TileCauldronBase{

    public TileCauldronTest() {
        super(50, 50, 50);
    }

    @Override
    public void meltCauldron() {
        System.out.println("Melt");
    }

    @Override
    public void cauldronUnstable() {
        System.out.println("Unstable");
    }

    @Override
    public void cauldronOverflow() {
        System.out.println("Overflow");
    }

    @Override
    public void invalidRecipe(ItemStack stack) {
        System.out.println("Invalid Recipe");
    }
}
