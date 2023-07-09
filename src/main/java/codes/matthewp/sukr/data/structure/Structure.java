package codes.matthewp.sukr.data.structure;

public class Structure {

    private String id;

    private String name;

    private StructureCategory category;

    private String file;

    public Structure(String id, String name, StructureCategory category, String file) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public StructureCategory getCategory() {
        return category;
    }

    public String getFile() {
        return file;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(StructureCategory category) {
        this.category = category;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
