import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
 
//класс поточный, поэтому он должен наследовать интерфейс Runnable
public class Racer extends java.awt.Canvas implements Runnable {
    int position=0;
    String name;
    int stepsCount=300;
    static int place = 0;
    boolean isFinished; 
    Image img;
   
    //конструктор класса
    //экземпляр класса создаем по имени
    public Racer (String aName) {
        try {
            name=aName;
            img = ImageIO.read(new File("bmw.png"));
                    } catch (IOException ex) {
            Logger.getLogger(Racer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public synchronized void paint(Graphics g) {
   
        //рисуем дорожку
       // g.setColor(Color.black);
      //  g.drawLine(0,size().height/2,size().width-170,size().height/2);
       
        //рисуем гонщика в виде желтого овала
        if(isFinished) g.setColor(Color.green); else g.setColor(Color.yellow);
       // g.fillOval(position*(size().width-170)/stepsCount,0,15,size().height);
       
        g.drawImage(img, position*(size().width-170)/stepsCount, 5, this);
       
        //если гонщик пришел на финиш, выдадим об этом сообщение
        if(isFinished) {
            g.setColor(Color.red);
            place++;
            g.drawString("Racer '"+name+"' is finished"+" On a place "+ place,size().width-300,size().height);
        }
       
        g.setColor(Color.red);
        g.drawString(""+position,0,size().height);
    }
   
    public void run() {
        isFinished=false;
       
        //цикл до конца гонки
        while(position<stepsCount) {
            position++;
            repaint();
           
            //останавливаем поток, что бы началось отображение
            try {
                Thread.currentThread().sleep(10);
            } catch (Exception e) {System.out.println("Exception on sleep");}
        }
        isFinished=true;
        repaint();
    }
}