import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class turtle {

 public static void main(String[] args) {
  RaceFrame21 frame = new RaceFrame21();
  
  frame.setLocation(300,100);
  frame.setSize(800,700);
  frame.setVisible(true);
  frame.addWindowListener(new WindowAdapter(){
   public void windowClosing (WindowEvent  e) {
    System.exit(1);
   }
  });
 }

}

@SuppressWarnings("serial")
class RaceFrame21 extends Frame implements ActionListener {
 RaceThreadx thread1;
 RaceThreadx thread2;
 RaceThreadx thread3;
 RaceThreadx thread4;
 RaceThreadx thread5;
 
 Button startButton = new Button("시작");
 Button clearButton = new Button("초기화");
 Button endButton = new Button("다시 하기");
 Dimension d;
 
 int i[] = {0,0,0,0,0};
 boolean re1=true,re2=true,re3=true,re4=true,re5=true;
 private Image img,img1,img2,img3,img4;
 
 public RaceFrame21(){
  super("거북이 경주 게임");
  
  //버튼 만들어 이벤 처리
  setLayout(new BorderLayout());
  startButton.addActionListener(this);
  clearButton.addActionListener(this);
  endButton.addActionListener(this);
  
  Panel p = new Panel();
  add("North",p);
  p.add(startButton);
  p.add(clearButton);
  p.add(endButton);
  //쓰레드 생성
  thread1 = new RaceThreadx(this, 10, 150, 10, 50 );
  thread2 = new RaceThreadx(this, 10, 250, 10, 50 );
  thread3 = new RaceThreadx(this, 10, 350, 10, 50 );
  thread4 = new RaceThreadx(this, 10, 450, 10, 50 );
  thread5 = new RaceThreadx(this, 10, 550, 10, 50 );
  
  repaint();

  img = Toolkit.getDefaultToolkit().getImage("c:/Users/Sunrin/Desktop/경주 게임/image/turtle_image.png");
  img1 = Toolkit.getDefaultToolkit().getImage("c:/Users/Sunrin/Desktop/경주 게임/image/turtle_image.png");
  img2 = Toolkit.getDefaultToolkit().getImage("c:/Users/Sunrin/Desktop/경주 게임/image/turtle_image.png");
  img3 = Toolkit.getDefaultToolkit().getImage("c:/Users/Sunrin/Desktop/경주 게임/image/turtle_image.png");
  img4 = Toolkit.getDefaultToolkit().getImage("c:/Users/Sunrin/Desktop/경주 게임/image/turtle_image.png");
 }
 
 public void actionPerformed(ActionEvent e){
  String str = e.getActionCommand();
  System.out.println(str);
  if(str.equals("시작")){
   /*
   Random r = new Random();
   int no = r.nextInt(5);
   switch(no){
   case 0 : thread1.setPriority(Thread.MAX_PRIORITY);break;
   case 1 : thread2.setPriority(Thread.MAX_PRIORITY);break;
   case 2 : thread3.setPriority(Thread.MAX_PRIORITY);break;
   case 3 : thread4.setPriority(Thread.MAX_PRIORITY);break;
   case 4 : thread5.setPriority(Thread.MAX_PRIORITY);break;  
   }
   */
   thread1.start();
   thread2.start();
   thread3.start();
   thread4.start();
   thread5.start();
  }
  else if(str.equals("초기화")){
   thread1.x=10;
   thread2.x=10;
   thread3.x=10;
   thread4.x=10;
   thread5.x=10;
   for(int k=0;k<i.length;k++){
    i[k]=0;
   }
  }
  else if(str.equals("다시 하기")){
	  thread1.x = 0;
  }
 }
 public void update(Graphics g){
  super.update(g);
  //System.out.println("update");
 }
 
 public void paint(Graphics g){
  //System.out.println("paint");
  g.drawLine(750, 0, 750, 700);
  
  //움직일 네모칸을 그린다.
  g.setColor(Color.blue);
  g.fillRect(thread1.x, thread1.y, thread1.w, thread1.h);
  g.fillRect(thread2.x, thread2.y, thread2.w, thread2.h);
  g.fillRect(thread3.x, thread3.y, thread3.w, thread3.h);
  g.fillRect(thread4.x, thread4.y, thread4.w, thread4.h);
  g.fillRect(thread5.x, thread5.y, thread5.w, thread5.h); 

  //1등~5등 을 판별해서 출력 처리
  for(int k=0;k<i.length;k++){
   if(thread1.x == 750 && i[k]==0&&re1){i[k]=1;re1=false;break;}
   else if(thread2.x == 750 && i[k]==0&&re2){i[k]=2;re2=false;break;}
   else if(thread3.x == 750 && i[k]==0&&re3){i[k]=3;re3=false;break;}
   else if(thread4.x == 750 && i[k]==0&&re4){i[k]=4;re4=false;break;}
   else if(thread5.x == 750 && i[k]==0&&re5){i[k]=5;re5=false;break;}
  }
  if(i[0]>0)  g.drawString("1등"+i[0]+"번 거북이 ",260,350);   
  if(i[1]>0) g.drawString("2등"+i[1]+"번 거북이 ",260,350+20);
  if(i[2]>0) g.drawString("3등"+i[2]+"번 거북이 ",260,350+40);
  if(i[3]>0) g.drawString("4등"+i[3]+"번 거북이 ",260,350+60);
  if(i[4]>0) g.drawString("5등"+i[4]+"번 거북이 ",260,350+80);

 

  /*
  //시간 출력 처리
  String date = new Date().toString();
  g.drawString(date, 20, 350);
  */
  
  //네모칸을 그림으로 할때
  /*
  g.drawImage(img, thread1.x, 100, 38, 36, this);
  g.drawImage(img1, thread2.x, 200, 38, 36, this);
  g.drawImage(img2, thread3.x, 300, 38, 36, this);
  g.drawImage(img3, thread4.x, 400, 38, 36, this);
  g.drawImage(img4, thread5.x, 500, 38, 36, this);
  */
 }
}
//쓰레드
class RaceThreadx extends Thread{
 RaceFrame21 frame;
 
 int x,y,w,h;
 
 public RaceThreadx(RaceFrame21 frame, int x, int y, int w, int h){
  this.frame = frame;
  this.x = x;
  this.y = y;
  this.w = w; //이 내용만 바뀐다.
  this.h = h;
 }
 
 public void run(){
  Random r = new Random(); //각 말마다 랜덤값에 의해 이동하는 거리를 다르게 주기 위해 사용
  Dimension d = frame.getSize();

  int widthLast = (int)(d.getWidth() - (2*x));
  for( ; x <= widthLast-10; x += 10){   
   frame.repaint();
   
   try{
    Thread.sleep(r.nextInt(300)+30);
   }catch(Exception e){}
  }
  frame.repaint();
 } 
}