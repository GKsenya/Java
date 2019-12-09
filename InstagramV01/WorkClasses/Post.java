package InstagramV01.WorkClasses;

import java.io.FileOutputStream;
import java.sql.Blob;

public class Post {
    private String date;
    private String comment;
    private byte[] img;
    private String imgIn;

    public Post(String date, byte[] img, String comment) {
        this.date = date;
        this.comment = comment;
        this.img = img;
    }

    public Post(byte[] img, String comment) {
        this.img = img;
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public byte[] getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "Post{" +
                "date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public static byte[] conversion(String filename){
        
    }

}
