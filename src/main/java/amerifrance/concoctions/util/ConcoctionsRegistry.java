package amerifrance.concoctions.util;

import amerifrance.concoctions.objects.Concoction;

import java.util.HashMap;

public class ConcoctionsRegistry {

    private static HashMap<String, Class<? extends Concoction>> nameToClass = new HashMap<String, Class<? extends Concoction>>();
    private static HashMap<Class<? extends Concoction>, String> classToName = new HashMap<Class<? extends Concoction>, String>();

    public static void registerConcoction(Class<? extends Concoction> concoction, String id) {
        nameToClass.put(id, concoction);
        classToName.put(concoction, id);
    }

    public static Class getConcoctionForId(String id) {
        return nameToClass.get(id);
    }

    public static String getIdForConcoction(Class<? extends Concoction> concoction) {
        return classToName.get(concoction);
    }
}
