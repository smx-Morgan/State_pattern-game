package main;

import imgs.Img;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @Description:this class is using for changing patterns for monster
 * @Author: smx_Morgan
 * @Date: 2022/8/8 19:11
 */
public abstract class MonsterState implements Entity{
  protected Monster monster;
  protected Point location;
  public void setMonster(Monster monster) {
    this.monster = monster;
  }
  public Point location(){ return location; }
  public void location(Point p){ location=p; }

  MonsterState(Point location){this.location=location;}

  public abstract void ping(Model m);

  public abstract void draw(Graphics g, Point center, Dimension size);
}
