/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
 import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JTable;

public class Race extends Applet implements Runnable {
   
    Racer theRacers[]; 
    static int count=5;
    Thread theThreads[]; 
    Thread thisThread; 
    static boolean inApplet=true;
    int numberOfTheradAtStart; 
                               
   
    public void init() {
        
        numberOfTheradAtStart=Thread.activeCount();
       
        //определеяем расположение гонщиков в окне,
        //они будут добавлятся один за друим
        setLayout(new GridLayout(count,1));
       
        //создаем массивы гонщиков и потоков
        theRacers=new Racer[count];
        theThreads=new Thread[count];
       
        //в цикле создаем каждый элемент массивов
        for(int i=0;i<count;i++) {
            theRacers[i]=new Racer("Racer "+i);
            theRacers[i].resize(size().width,size().height/count);
            add(theRacers[i]);
            theThreads[i]=new Thread(theRacers[i]);
        }
    }
   
    //этой процедурой мы запускаем все потоки
    public void start() {
        for(int i=0;i<count;i++) theThreads[i].start();
        thisThread=new Thread(this);
    }
   
    public void stop() {
        thisThread.stop();
    }
   
    public void run() {
   
        // цикл, пока все гонщики не закончат гонку
        while(Thread.activeCount()>numberOfTheradAtStart) {
            try {
                thisThread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("thisTread was interruped");
            }
        }
       
     
    }
   
  //  public static void main(String argv[]) {
      //  inApplet=false;
      //   g.drawString("Hello applet!", 50, 25);
      //  Race theRace=new Race();
      //  theRace.init();
      //  theRace.start();
      // System.out.println("d");
  //}
      
}
   