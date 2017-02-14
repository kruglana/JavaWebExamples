package dbService.dataSets;

/**
*/
@SuppressWarnings("UnusedDeclaration")
public class UsersDataSet {
    private long id;
    private String name;
    private String password;

    public UsersDataSet(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password +
                '}';
    }
}
