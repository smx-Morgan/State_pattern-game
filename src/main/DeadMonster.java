package main;

import imgs.Img;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @Description: DeadMonster action
 * @Author: smx_Morgan
 * @Date: 2022/8/8 18:16
 */
public class DeadMonster extends MonsterState{
  @Override
  public Point location(){
    return super.location();
  }

  public double speed(){ return 0; }
  DeadMonster(Point location) {
    super(location);
  }

  @Override
  public void ping(Model m) {
    if (monster.time == 0){
       monster.setTime(0);
    }
    if(monster.getTime() > 100){
      m.remove(this.monster);
    }
  }

  @Override
  public void draw(Graphics g, Point center, Dimension size) {
    drawImg(Img.DeadMonster.image, g, center, size);
  }
  
}
