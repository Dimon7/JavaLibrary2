import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
 
//класс поточный, поэтому он должен наследовать интерфейс Runnable
public class Racer extends java.awt.Canvas implements Runnable {
    int position=0;
    String name;
    int stepsCount=700;
    static int place = 0;
    boolean isFinished; 
    Image img;
    final Random random = new Random();
   
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
   
             
        
       
        g.drawImage(img, position*(size().width-50)/stepsCount, 5, this);
       
        
        if(isFinished) {
        
            place++;
            g.drawString("Racer '"+name+"' is finished"+" On a place "+ place,size().width-300,size().height);
        }
       
        
    }
   
    public void run() {
        isFinished=false;
       
        //цикл до конца гонки
        while(position<stepsCount) {
            position += random.nextInt(30);
            repaint();
           
            
            try {
                Thread.currentThread().sleep(100);
            } catch (Exception e) {System.out.println("Exception on sleep");}
        }
        isFinished=true;
        repaint();
    }
}