package main;

import imgs.Img;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @Description: roaming monster
 * @Author: smx_Morgan
 * @Date: 2022/8/9 15:43
 */
public class RoamingMonster extends MonsterState{
  public static int change =0;
  private double x  = Math.random()*16;
  private double y  = Math.random()*16;
  public Point location(){ return location; }
  public void location(Point p){ location=p; }

  RoamingMonster(Point location){
    super(location);
  }
  public double speed(){ return 0.05d; }
/**
* @Description: this ping is used to find random place for monster and it will change per 50 ping
* @Params: [main.Model]
* @return: void
* @Author: smx_Morgan
* @Date: 2022/8/11-21:46
*/
  public void ping(Model m){
    change++;
    if(change>50){
      x  = Math.random()*16;
      y  = Math.random()*16;
      double distance = Math.abs(x - location.x()) + Math.abs(y - location.y());
      while (distance < 3){
        x  = Math.random()*16;
        y  = Math.random()*16;
        distance = Math.abs(x - location.x()) + Math.abs(y - location.y());
      }
      change = 0;
    }
    Point roaming = new Point(x,y);
    var arrow = roaming.distance(location);
    double size = arrow.size();
    arrow = arrow.times(speed()/size);
    location = location.add(arrow);
    var arrow_p = m.camera().location().distance(location);
    double size_p = arrow_p.size();
    if(size_p<0.6d){ m.onGameOver(); }
  }

  /*  public double chaseTarget(Monster outer, Point target){
      var arrow = target.distance(outer.location());
      double size = arrow.size();
      arrow = arrow.times(speed()/size);
      outer.location(outer.location().add(arrow));
      return size;
    }*/
  public void draw(Graphics g, Point center, Dimension size) {
    drawImg(Img.AwakeMonster.image, g, center, size);
  }
}
