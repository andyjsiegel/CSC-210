public class Animal {
    private String species;
    private String genus;
    private String family;

    public Animal(String species, String genus, String family) {
        this.species = species;
        this.genus = genus;
        this.family = family;
    }
    public String toString() {
        return "Animal{" +
                "species='" + species + '\'' +
                ", genus='" + genus + '\'' +
                ", family='" + family + '\'' +
                '}';
    }
}
