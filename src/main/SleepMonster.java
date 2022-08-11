package main;

import imgs.Img;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @Description:
 * @Author: smx_Morgan
 * @Date: 2022/8/8 18:03
 */
public class SleepMonster extends MonsterState {
  @Override
  public Point location(){ return super.location(); }

  public double speed(){ return 0; }


  public SleepMonster(Point location) {
    super(location);
  }

  @Override
  public void ping(Model m){
    var arrow = m.camera().location().distance(super.location());
    double size = arrow.size();
    if(size<6d){super.monster.setState(new WakingMonster(this.location()));}
    //if(size<0.6d){ m.onGameOver(); }
  }

  @Override
  public void draw(Graphics g, Point center, Dimension size) {
    drawImg(Img.SleepMonster.image, g, center, size);
  }

}
