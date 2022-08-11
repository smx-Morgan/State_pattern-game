package main;

import java.awt.event.KeyEvent;

class Controller extends Keys{
  public static int kw = KeyEvent.VK_W;
  public static int ks = KeyEvent.VK_S;
  public static int ka = KeyEvent.VK_A;
  public static int kd = KeyEvent.VK_D;
  public static int ko = KeyEvent.VK_O;
  public static int kp = KeyEvent.VK_P;
  Controller(Camera c,Sword s){
    setAction(kw,c.set(Direction::up),c.set(Direction::unUp));
    setAction(ks,c.set(Direction::down),c.set(Direction::unDown));
    setAction(ka,c.set(Direction::left),c.set(Direction::unLeft));
    setAction(kd,c.set(Direction::right),c.set(Direction::unRight));
    setAction(ko,s.set(Direction::left),s.set(Direction::unLeft));
    setAction(kp,s.set(Direction::right),s.set(Direction::unRight));
  }
}