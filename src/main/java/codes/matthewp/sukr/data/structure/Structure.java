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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StructureCategory getCategory() {
        return category;
    }

    public void setCategory(StructureCategory category) {
        this.category = category;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
