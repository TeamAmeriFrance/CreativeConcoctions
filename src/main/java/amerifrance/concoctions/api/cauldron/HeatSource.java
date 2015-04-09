package amerifrance.concoctions.api.cauldron;

public class HeatSource {

    public int ticksToWait;

    public HeatSource(int ticksToWait) {
        this.ticksToWait = ticksToWait;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeatSource that = (HeatSource) o;
        if (ticksToWait != that.ticksToWait) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return ticksToWait;
    }

    @Override
    public String toString() {
        return "HeatSource{ticksToWait=" + ticksToWait + "}";
    }
}
