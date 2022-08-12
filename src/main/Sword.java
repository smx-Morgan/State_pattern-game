package main;

import java.awt.Dimension;
import java.awt.Graphics;

import imgs.Img;

class Sword extends ControllableDirection implements Entity{
  private Entity wielder;
  private double weaponRadiant = 0;
  boolean isMon = false;
  public double distance(){//if it is monster, the distance should be 1.5d but for player it only need to be 0.8d
    if(isMon){
      return 1.5;
    }
    return 0.8d;
  }
  public double speed(){
    if(isMon){
      return 0.4d;
    }
    return 0.2d;
  }
  Sword(Entity wielder){ this.wielder=wielder; }
  public Point location(){ 
    var dir = new Point(Math.sin(weaponRadiant),Math.cos(weaponRadiant));
    return dir.times(distance()).add(wielder.location());
  }
  public void onHit(Model m, Entity e){
    if(e instanceof Monster){

      //m.remove(e);
      Point p = ((Monster) e).state.location;
      DeadMonster  dd = new DeadMonster(p);
      ((Monster) e).setState(dd);
      //m.remove(new Sword(dd));
      if(((Monster) e).containSword()){//if this is boss when it be killed, we should remove sword
        m.remove(((Monster) e).getSword());
      }
    }
    if(e instanceof Camera){//when sword hit player, it will also kill it.
      m.onGameOver();
    }
  }
  public double effectRange(){ return 0.3d; }
  
  public void ping(Model m){
    weaponRadiant+=direction().arrow(speed()).x();
    weaponRadiant%=Math.PI*2d;
    var l = this.location();
    m.entities().stream()
      .filter(e->e!=this)
      .filter(e->e.location().distance(l).size()<effectRange())
      .forEach(e->onHit(m,e));
  }
  public void draw(Graphics g, Point center, Dimension size) {
    drawImg(Img.Sword.image, g, center, size);
  }
  public void setMon(boolean mon) {
    isMon = mon;
  }
}