package fr.cours.coincoins_v1_0.entities;

public class Corner {

    private int id;
    private String title;
    private String resume;
    private String img;
    private double lat;
    private double lng;
    private int id_user;

    public Corner() {
    }
    public Corner(String title, String resume, String img, double lat, double lng, int id_user) {
        this.title = title;
        this.resume = resume;
        this.img = img;
        this.lat = lat;
        this.lng = lng;
        this.id_user = id_user;
    }
    public Corner(int id, String title, String resume, String img, double lat, double lng, int id_user) {
        this.id = id;
        this.title = title;
        this.resume = resume;
        this.img = img;
        this.lat = lat;
        this.lng = lng;
        this.id_user = id_user;
    }


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getid() {
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Corner{" +
                "id=" + id  +
                ", title='" + title + '\'' +
                ", resume='" + resume + '\'' +
                ", img='" + img + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", user_id=" + id_user +
                '}';
    }
}
