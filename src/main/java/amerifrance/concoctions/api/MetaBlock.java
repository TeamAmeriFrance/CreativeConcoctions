package amerifrance.concoctions.api;

import net.minecraft.block.Block;

public class MetaBlock {

    public Block block;
    public int metadata;

    public MetaBlock(Block block) {
        this.block = block;
        this.metadata = 0;
    }

    public MetaBlock(Block block, int metadata) {
        this.block = block;
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetaBlock that = (MetaBlock) o;

        if (metadata != that.metadata) return false;
        if (block != null ? !block.equals(that.block) : that.block != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = block != null ? block.hashCode() : 0;
        result = 31 * result + metadata;
        return result;
    }
}
