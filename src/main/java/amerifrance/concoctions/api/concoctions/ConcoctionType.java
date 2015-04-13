package amerifrance.concoctions.api.concoctions;

import net.minecraft.util.EnumChatFormatting;

public enum ConcoctionType {
    GOOD(EnumChatFormatting.GREEN),
    BAD(EnumChatFormatting.RED),
    NEUTRAL(EnumChatFormatting.GRAY);

    public final EnumChatFormatting prefix;

    private ConcoctionType(EnumChatFormatting prefix) {
        this.prefix = prefix;
    }
}
