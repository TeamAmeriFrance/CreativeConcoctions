package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;

import java.awt.*;

public class ConcoctionKnowledge extends Concoction {

    public ConcoctionKnowledge() {
        super("concoction.knowledge", 100, Color.GRAY);
    }

    @Override
    public boolean isConcoctionInstant() {
        return true;
    }
}
