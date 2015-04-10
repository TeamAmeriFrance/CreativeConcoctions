package amerifrance.concoctions.api.cauldron;

public class HeatSource {

    public int ticksToWait;
    public int maxHeat;

    public HeatSource(int ticksToWait, int maxHeat) {
        this.ticksToWait = ticksToWait;
        this.maxHeat = maxHeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeatSource that = (HeatSource) o;
        if (maxHeat != that.maxHeat) return false;
        if (ticksToWait != that.ticksToWait) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = ticksToWait;
        result = 31 * result + maxHeat;
        return result;
    }

    @Override
    public String toString() {
        return "HeatSource{ticksToWait=" + ticksToWait + ", maxHeat=" + maxHeat + "}";
    }
}
