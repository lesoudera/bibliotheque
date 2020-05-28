package entites;

public class CTest {

    protected int id;
    protected String nom;
    protected int age;

    public CTest(int id, String nom, int age) {
        this.id = id;
        this.nom = nom;
        this.age = age;
    }

    @Override
    public String toString() {
        String str = "init";
        str = this.id + "\n nom : " + this.nom + "\n age : " + this.age;
        return str;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
}
