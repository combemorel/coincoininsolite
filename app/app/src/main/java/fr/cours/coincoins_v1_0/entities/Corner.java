package fr.cours.coincoins_v1_0.entities;

import org.apache.log4j.Logger;


// *******  TEST 1   ****** //
//import org.json.JSONArray;
//import org.json.JSONObject;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import fr.cours.coincoins_v1_0.utils.InputStreamOperations;


public class Corner {
    private static final Logger log = Logger.getLogger(Corner.class);

    private int id;
    private String title;
    private String resume;
    private String img;
    private double lat;
    private double lng;

    public Corner() {
    }

    public Corner(int id, String title, String resume, String img, double lat, double lng) {
        this.id = id;
        this.title = title;
        this.resume = resume;
        this.img = img;
        this.lat = lat;
        this.lng = lng;
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
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", resume='" + resume + '\'' +
                ", img='" + img + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
//    *******  TEST 1   ****** //
//    public static ArrayList<Corner> getMarkers() {
//        ArrayList<Corner> corners = new ArrayList<Corner>();
//
//        try {
//            String myUrl = "http://192.168.1.18:6253/api/marker/find";
//            URL url = new URL(myUrl);
//
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection().;
//            connection.connect();
//
//            InputStream inputStream = connection.getInputStream();
//
//            String result = InputStreamOperations.InputStreamToString(inputStream);
//            JSONObject jsonObject = new JSONObject(result);
//            JSONArray array = new JSONArray(jsonObject.getString("coins"));
//
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject obj = new JSONObject(array.getString(i));
//                Corner corner = new Corner();
//                corner.setTitle(obj.getString("title"));
//                corner.setResume(obj.getString("resume"));
//                corner.setImg(obj.getString("img"));
//                corner.setLat(obj.getDouble("lat"));
//                corner.setLng(obj.getDouble("lng"));
//
//                corners.add(corner);
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return corners;
//    }
}
