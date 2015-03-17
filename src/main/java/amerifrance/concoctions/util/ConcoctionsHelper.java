package amerifrance.concoctions.util;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import amerifrance.concoctions.api.IConcoctionContext;
import amerifrance.concoctions.objects.Concoction;
import amerifrance.concoctions.objects.ConcoctionContext;

public class ConcoctionsHelper {

	public static void addConcoction(EntityLivingBase entityLivingBase, Concoction concoction, int level, int duration) {
		ConcoctionContext wrapper = new ConcoctionContext(concoction, level, duration);
		addConcoction(entityLivingBase, wrapper);
	}

	public static void addConcoction(EntityLivingBase entityLivingBase, ConcoctionContext wrapper) {
		if (LivingConcoctions.get(entityLivingBase) != null) {
			LivingConcoctions.getActiveConcotions(entityLivingBase).add(wrapper);
			wrapper.onAdded(entityLivingBase);
		}
	}

	public static void removeConcoction(EntityLivingBase entityLivingBase, Concoction concoction, int level, int duration) {
		ConcoctionContext wrapper = new ConcoctionContext(concoction, level, duration);
		removeConcoction(entityLivingBase, wrapper);
	}

	public static void removeConcoction(EntityLivingBase entityLivingBase, ConcoctionContext wrapper) {
		if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
			if (LivingConcoctions.getActiveConcotions(entityLivingBase).contains(wrapper)) {
				int index = LivingConcoctions.getActiveConcotions(entityLivingBase).indexOf(wrapper);
				IConcoctionContext remove = LivingConcoctions.getActiveConcotions(entityLivingBase).get(index);
				remove.setTicksLeft(0);
			}
		}
	}

	public static ConcoctionContext getActiveConcoctionWrapper(EntityLivingBase entityLivingBase, Concoction concoction) {
		if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
			Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(entityLivingBase).iterator();
			while (iterator.hasNext()) {
				ConcoctionContext wrapper = (ConcoctionContext) iterator.next();
				if (wrapper.getConcoction().equals(concoction))
					return wrapper;
			}
		}
		return null;
	}

	public static boolean isConcoctionActive(EntityLivingBase entityLivingBase, Concoction concoction) {
		return getActiveConcoctionWrapper(entityLivingBase, concoction) != null;
	}

	public static void clearActiveConcoctions(EntityLivingBase entityLivingBase) {
		if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
			LivingConcoctions.getActiveConcotions(entityLivingBase).clear();
		}
	}

	public static List<IConcoctionContext> getActiveConcotions(EntityLivingBase entityLivingBase) {
		if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
			return LivingConcoctions.getActiveConcotions(entityLivingBase);
		}
		return null;
	}
}
