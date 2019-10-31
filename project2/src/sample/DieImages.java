package sample;

import javafx.scene.image.Image;

public class DieImages
{
    final Image die1 = new Image(getClass().getResourceAsStream("Dice1.png"));
    final Image die2 = new Image(getClass().getResourceAsStream("Dice2.png"));
    final Image die3 = new Image(getClass().getResourceAsStream("Dice3.png"));
    final Image die4 = new Image(getClass().getResourceAsStream("Dice4.png"));
    final Image die5 = new Image(getClass().getResourceAsStream("Dice5.png"));


    final Image[] pictures = new Image[5];

    public DieImages()
    {
        pictures[0] = die1;
        pictures[1] = die2;
        pictures[2] = die3;
        pictures[3] = die4;
        pictures[4] = die5;
    }

    public Image[] getPictures()
    {
        return pictures;
    }
}
