package sample;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Die
{
    ImageView face;
    Image[] images;

    public Die(Image[] images)
    {
        this.images = images;
        face = new ImageView(this.images[0]);
    }

    public Die(Image[] images, int dieFaceValue)
    {
        this.images = images;
        face = new ImageView(this.images[dieFaceValue - 1]);
    }

    public ImageView getdieFace()
    {
        return face;
    }

    public void setFace(int dieFaceValue)
    {
        face.setImage(this.images[dieFaceValue-1]);
    }


}