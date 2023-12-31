package codes.matthewp.sukr.data.structure;

/**
 * Simple representation of any kind of structure
 */
public class Structure {

    private String id;
    private String name;
    private StructureCategory category;
    private String file;

    private String author;

    public Structure(String id, String name, StructureCategory category, String file, String author) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.file = file;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
