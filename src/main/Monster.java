package main;

import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @Description: abstract class for Monster
 * @Author: smx_Morgan
 * @Date: 2022/8/8 20:19
 */
public class Monster implements Entity{
  protected MonsterState state;//this is where using state pattern
  protected int time = 0;
  public Sword sword;//use to remove when boss dead

  public Sword getSword() {
    return sword;
  }
  public void setSword(Sword sword) {
    this.sword = sword;
  }
  public boolean containSword(){
    return sword != null;
  }
  public Monster(MonsterState state) {
    this.state = state;
    this.state.setMonster(this);
  }
  public MonsterState getState() {
    return state;
  }
  public void setState(MonsterState state) {
    this.state = state;
    this.state.setMonster(this);
  }

  public void ping(Model m){
    if(time!=0){
      time++;
    }
    this.state.ping(m);
  }

  public void draw(Graphics g, Point center, Dimension size){
    this.state.draw(g,center,size);
  }

  @Override
  public Point location() {
    return state.location();
  }

  public int getTime() {
    return time;
  }
  public void setTime(int time) {
    this.time = time+1;
  }
}
