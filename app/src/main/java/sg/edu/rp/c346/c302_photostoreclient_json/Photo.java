package sg.edu.rp.c346.c302_photostoreclient_json;

public class Photo {
    private int photo_id;
    private String title, desc, image, created_by;

    public Photo(int photo_id, String title, String desc, String image, String created_by) {
        this.photo_id = photo_id;
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.created_by = created_by;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
