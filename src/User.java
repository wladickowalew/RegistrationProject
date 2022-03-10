import java.util.Locale;

public class User {
    private String login;
    private String password;
    private String name;
    private String profession;
    private int age;

    public User(String login, String password, String name, String profession, int age) {
        this.login = login.toLowerCase();
        this.password = password;
        this.name = name;
        this.profession = profession;
        this.age = age;
    }

    public String getInsertQuery(){
        String query = "INSERT INTO 'users' ('login', 'password', 'name', 'age', 'profession') " +
                        "VALUES ('" + this.login + "', '" + this.password + "', '" + this.name +
                        "', " + this.age + ", '" + this.profession + "');";
        System.out.println(query);
        return query;
    }

    // getters and setters

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
