package codes.matthewp.sukr.data.structure;

import net.minecraft.core.Vec3i;

/**
 * This is the data that is sent to the client, instead of all the unrelated structure data.
 */
public class StructurePacketData {

    private String id;
    private String name;

    private Vec3i size;

    private double cost;

    private String author;

    public StructurePacketData(String id, String name, Vec3i size, double cost, String author) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vec3i getSize() {
        return size;
    }

    public void setSize(Vec3i size) {
        this.size = size;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
