package amerifrance.concoctions.api.ingredients;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.HashMap;

public class IngredientKnowledge implements IExtendedEntityProperties {

    public static String ID = "ingredientKnowledge";
    public HashMap<IngredientProperty, Boolean> knowledge;

    public IngredientKnowledge() {
        knowledge = new HashMap<IngredientProperty, Boolean>();
        populateMap();
    }

    public static void create(EntityPlayer player) {
        player.registerExtendedProperties(ID, new IngredientKnowledge());
    }

    public static IngredientKnowledge get(EntityPlayer player) {
        return (IngredientKnowledge) player.getExtendedProperties(ID);
    }

    public static HashMap<IngredientProperty, Boolean> getKnowledge(EntityPlayer player) {
        return get(player).getKnowledge();
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        if (!knowledge.isEmpty()) {
            NBTTagCompound tag = new NBTTagCompound();
            for (IngredientProperty ingredientProperty : knowledge.keySet()) {
                tag.setBoolean(ingredientProperty.name(), knowledge.get(ingredientProperty));
            }
            compound.setTag(ID, tag);
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound tag = compound.getCompoundTag(ID);
        if (tag != null) {
            knowledge.clear();
            for (IngredientProperty ingredientProperty : IngredientProperty.values()) {
                knowledge.put(ingredientProperty, tag.getBoolean(ingredientProperty.name()));
            }
        }
    }

    @Override
    public void init(Entity entity, World world) {
        knowledge = new HashMap<IngredientProperty, Boolean>();
        populateMap();
    }

    public HashMap<IngredientProperty, Boolean> getKnowledge() {
        return knowledge;
    }

    public void populateMap() {
        for (IngredientProperty ingredientProperty : IngredientProperty.values()) {
            knowledge.put(ingredientProperty, false);
        }
    }
}
