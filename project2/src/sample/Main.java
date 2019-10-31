package sample;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application
{
    Label title= new Label("Overall Score: 0");
    int overall=0;
    int bestScore=0;
    Integer scoreCurrent=0;
    Label score= new Label("Current Score: 0");;
    Label remOrBest= new Label("Rolls Remaining: 3");
    int count=3;
    Button btn = new Button("Roll");
    String hand="nothing";

    @Override
    public void start(Stage primaryStage)
    {
        DieImages images = new DieImages();
        Image[] imagesH=new Image[6];
        imagesH[0]=(new Image(getClass().getResourceAsStream("Dice1Held.png")));
        imagesH[1]=(new Image(getClass().getResourceAsStream("Dice2Held.png")));
        imagesH[2]=(new Image(getClass().getResourceAsStream("Dice3held.png")));
        imagesH[3]=(new Image(getClass().getResourceAsStream("Dice4held.png")));
        imagesH[4]=(new Image(getClass().getResourceAsStream("Dice5held.png")));

        Die[] dies= new Die[5];
        dies[0]=(new Die(images.getPictures()));
        dies[1]=(new Die(images.getPictures()));
        dies[2]=(new Die(images.getPictures()));
        dies[3]=(new Die(images.getPictures()));
        dies[4]=(new Die(images.getPictures()));

        HBox hbox= new HBox(dies[0].getdieFace(),dies[1].getdieFace(),dies[2].getdieFace(),dies[3].getdieFace(),dies[4].getdieFace());


        boolean[] isClicked=new boolean[6];


            btn.setText("Roll");
            int[] randFace= new int[5];
            btn.setOnAction((ActionEvent event) -> {
                Random random = new Random();
                for(int j=0;j<5;j++) {
                    if(count==3){
                        randFace[j] = random.nextInt(5) + 1;
                        dies[j].setFace(randFace[j]);
                        isClicked[j]=false;
                    }else if (!isClicked[j]) {

                            randFace[j] = random.nextInt(5) + 1;
                            dies[j].setFace(randFace[j]);
                        }



                }

                count--;

                scoreCurrent=calculate(randFace);
                score.setText("Current Score: "+scoreCurrent);
                if(scoreCurrent>bestScore){
                    bestScore=scoreCurrent;
                }
                overall+=scoreCurrent;
                title.setText("Overall Score: "+overall);
                if(count==0){
                    hand=calcHand(bestScore);
                    remOrBest.setText("Best Hand: "+hand);
                    btn.setText("Play Again");
                    count=3;
                    bestScore=0;
                }else{
                    btn.setText("Roll");
                    remOrBest.setText("Rolls Remaining: "+count);
                }

            });


                int[] jHold = new int[5];
                for (int j = 0; j < 5; j++) {
                    int finalJ = j;
                    dies[finalJ].getdieFace().setOnMouseClicked(event -> {
                        if (isClicked[finalJ]) {
                            dies[finalJ].setFace(jHold[finalJ]);
                            isClicked[finalJ] = false;
                        } else {
                            dies[finalJ].getdieFace().setImage((imagesH[(randFace[finalJ] - 1)]));
                            jHold[finalJ] = randFace[finalJ];
                            isClicked[finalJ] = true;
                        }
                    });
                }





        VBox root = new VBox(10,title, hbox, btn,score, remOrBest);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/sample/styles.css");
        primaryStage.setTitle("Project 2 4800");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public int calculate(int[] dieFace) {
        int one = 0, two = 0, three = 0, four = 0, five = 0;
        for (int i = 0; i < dieFace.length; i++) {
            if (dieFace[i] == 1) {
                one++;
            } else if (dieFace[i] == 2) {
                two++;
            } else if (dieFace[i] == 3) {
                three++;
            } else if (dieFace[i] == 4) {
                four++;
            } else {
                five++;
            }
        }
        if (one == 5 || two == 5 || three == 5 || four == 5 || five == 5) {
            return 10;

        } else if (one == 4 || two == 4 || three == 4 || four == 4 || five == 4) {
            return 7;
        }else if(one==3||two==3||three==3||four==3||five==3){
            return 5;
        } else if(one==2||two==2||three==2||four==2||five==2)
        {
            return 1;
        } else if((one==1&&two==1&&three==1&&four==1&&five==1)||(two==1&&three==1&&four==1&&five==1)){
            return 8;
        }else if((one==3||two==3||three==3||four==3||five==3)&&(one==2||two==2||three==2||four==2||five==2)) {
            return 6;
        }else if((one==2&&two==2)||(one==2&&three==2)||(one==2&&four==2)||(one==2&&five==2)||(two==2&&three==2)||(two==2&&four==2)
                ||(two==2&&five==2)||(three==2&&four==2)||(three==2&&five==2)||(four==2&&five==2))
        {
            return 2;
        }
            return 0;
    }
    public String calcHand(int s){
        if(s==10){
            return "5 of a Kind";
        }else if(s==8){
            return "Straight";
        }else if(s==7){
            return "Four of a Kind";
        }else if(s==6){
            return "Full House";
        }else if(s==5){
            return "3 of a Kind";
        }else if(s==4){
            return "2 pair";
        }else if(s==1){
            return "2 of a Kind";
        }

        return "nothing";
    }
    public static void main(String[] args)
    {
        launch(args);
    }

}