import java.time.LocalDate;

public class News {
    private int id;
    private String title;
    private String text;
    private int user_id;
    private boolean privat;
    private LocalDate data_time;

    public News(int id, String title, String text, int user_id, boolean privat, LocalDate data_time) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.user_id = user_id;
        this.privat = privat;
        this.data_time = data_time;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isPrivat() {
        return privat;
    }

    public void setPrivat(boolean privat) {
        this.privat = privat;
    }

    public LocalDate getData_time() {
        return data_time;
    }

    public void setData_time(LocalDate data_time) {
        this.data_time = data_time;
    }
}
