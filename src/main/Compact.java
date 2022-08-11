package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

class Compact extends JFrame{
  private static final long serialVersionUID = 1L;
  //private List<JTextField> text = new ArrayList<>();
  private JTextField w = new JTextField(20);
  private JTextField s = new JTextField(20);
  private JTextField a = new JTextField(20);
  private JTextField d = new JTextField(20);
  private JTextField o = new JTextField(20);
  private JTextField p = new JTextField(20);
  Runnable closePhase = ()->{};
  Phase currentPhase;
  Compact(){
    assert SwingUtilities.isEventDispatchThread();
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    phaseZero();
    setVisible(true);
    addWindowListener(new WindowAdapter(){
      public void windowClosed(WindowEvent e){closePhase.run();}
    });
  }
  private void phaseZero() {
    //set the name for keys
    List<String> StringList = List.of("Up:","Down:","Left:","Right:","SwordLeft:","SwordRight:");
    for (int i = 0; i < StringList.size(); i++) {
      JLabel keyWorld = new JLabel(StringList.get(i));
      keyWorld.setBounds(10,10+25*i,120,20);
      add(keyWorld);
      //text.add(keyText);
    }
    phaseSetText();
    var ok=new JButton("confirm");
    ok.setBounds(240,80,80,20);
    add(ok);
    ok.addActionListener(e->phaseString());
    var welcome=new JLabel("Welcome to Compact. A compact Java game!");
    var start=new JButton("Start!");
    closePhase.run();
    closePhase=()->{
     remove(welcome);
     remove(start);
     };
    add(BorderLayout.CENTER,welcome);
    add(BorderLayout.SOUTH,start);
    start.addActionListener(e->phaseOne());
    setPreferredSize(new Dimension(800,400));
    pack();
  }
  /**
  *
  *
  * @Description: set TextArea for player to choose which world need to be used for control
  * @Params: []
  * @return: void
  * @Author: smx_Morgan
  * @Date: 2022/8/11-21:48
  */
  private void phaseSetText(){
    //keyText.addActionListener();
    w.setBounds(120,10,50,20);
    add(w);
    s.setBounds(120,10+25,50,20);
    add(s);
    a.setBounds(120,10+50,50,20);
    add(a);
    d.setBounds(120,10+75,50,20);
    add(d);
    o.setBounds(120,10+100,50,20);
    add(o);
    p.setBounds(120,10+125,50,20);
    add(p);
  }
  /**
  * @Description: this function we used to get the char that player system in and set it for controller.
  * @Params: []
  * @return: void
  * @Author: smx_Morgan
  * @Date: 2022/8/11-21:49
  */
  private void phaseString(){
    String Key_w = w.getText();
    int keyCode_w = KeyEvent.getExtendedKeyCodeForChar((int) Key_w.charAt(0));//(int) Key_w.charAt(0);
    Controller.kw=keyCode_w;
    String Key_s = s.getText();
    int keyCode_s = KeyEvent.getExtendedKeyCodeForChar((int) Key_s.charAt(0));//(int) Key_w.charAt(0);
    Controller.ks=keyCode_s;
    String Key_a = a.getText();
    int keyCode_a = KeyEvent.getExtendedKeyCodeForChar((int) Key_a.charAt(0));//(int) Key_w.charAt(0);
    Controller.ka=keyCode_a;
    String Key_d = d.getText();
    int keyCode_d = KeyEvent.getExtendedKeyCodeForChar((int) Key_d.charAt(0));//(int) Key_w.charAt(0);
    Controller.kd=keyCode_d;
    String Key_o = o.getText();
    int keyCode_o = KeyEvent.getExtendedKeyCodeForChar((int) Key_o.charAt(0));//(int) Key_w.charAt(0);
    Controller.ko=keyCode_o;
    String Key_p = p.getText();
    int keyCode_p = KeyEvent.getExtendedKeyCodeForChar((int) Key_p.charAt(0));//(int) Key_w.charAt(0);
    Controller.kp=keyCode_p;
  }
  private void phaseOne(){
    setPhase(Phase.level1(()->phaseTwo(),()->phaseLoss()));
  }
  private void phaseTwo(){
    setPhase(Phase.level2(()->phaseThree(),()->phaseLoss()));
  }
  private void phaseThree(){
    setPhase(Phase.level3(()->phaseWin(),()->phaseLoss()));
  }
  private void phaseWin() {
    var welcome=new JLabel("Victory!");
    var start=new JButton("Restart!");
    closePhase.run();
    closePhase=()->{
      remove(welcome);
      remove(start);
    };
    add(BorderLayout.CENTER,welcome);
    add(BorderLayout.SOUTH,start);
    start.addActionListener(e->phaseOne());
    setPreferredSize(new Dimension(800,400));
    pack();
  }
  private void phaseLoss() {
    var welcome=new JLabel("You lost the game!");
    var start=new JButton("Restart!");
    closePhase.run();
    closePhase=()->{
      remove(welcome);
      remove(start);
    };
    add(BorderLayout.CENTER,welcome);
    add(BorderLayout.SOUTH,start);
    start.addActionListener(e->phaseOne());
    setPreferredSize(new Dimension(800,400));
    pack();
  }
  void setPhase(Phase p){
    //set up the viewport and the timer
    Viewport v = new Viewport(p.model());
    v.addKeyListener(p.controller());
    v.setFocusable(true);
    Timer timer = new Timer(34,unused->{
      assert SwingUtilities.isEventDispatchThread();
      p.model().ping();
      v.repaint();
    });
    closePhase.run();//close phase before adding any element of the new phase
    closePhase=()->{ timer.stop(); remove(v); };
    add(BorderLayout.CENTER,v);//add the new phase viewport
    setPreferredSize(getSize());//to keep the current size
    pack();                     //after pack
    v.requestFocus();//need to be after pack
    timer.start();
  }
}