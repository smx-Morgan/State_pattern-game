package main;

import java.security.Key;
import java.util.List;

record Phase(Model model, Controller controller){
  private static boolean level3 = false;
  static Phase level1(Runnable next, Runnable first) {
    Camera c = new Camera(new Point(5,5));
    Sword s = new Sword(c);
    Cells cells = new Cells();
    var m = new Model(){
      List<Entity> entities = List.of(c,s,new Monster(new SleepMonster(new Point(0,0))));
      public Camera camera(){ return c; }
      public List<Entity> entities(){ return entities; }
      public void remove(Entity e){ 
        entities = entities.stream()
          .filter(ei->!ei.equals(e))
          .toList();
      }
      public Cells cells(){ return cells; }
      public void onGameOver(){ first.run(); }
      public void onNextLevel(){next.run();}
    };
    return new Phase(m,new Controller(c,s));    
  }
  /**
  * @Description: this funciton is for level2
  * @Params: [java.lang.Runnable, java.lang.Runnable]
  * @return: main.Phase
  * @Author: smx_Morgan
  * @Date: 2022/8/11-21:41
  */
  static Phase level2(Runnable next, Runnable first) {
    Camera c = new Camera(new Point(5,5));
    Sword s = new Sword(c);
    Cells cells = new Cells();
    var m = new Model(){
      List<Entity> entities = List.of(c,s,
          new Monster(new SleepMonster(new Point(0,0))), new Monster(new SleepMonster(new Point(16,0))),
          new Monster(new SleepMonster(new Point(0,16))), new Monster(new RoamingMonster(new Point(16,16))));
      public Camera camera(){ return c; }
      public List<Entity> entities(){ return entities; }
      public void remove(Entity e){
        entities = entities.stream()
            .filter(ei->!ei.equals(e))
            .toList();
      }
      public Cells cells(){ return cells; }
      public void onGameOver(){ first.run(); }
      public void onNextLevel(){ next.run(); }
    };
    return new Phase(m,new Controller(c,s));
  }
  /**
  * @Description: this function is for level3
  * @Params: [java.lang.Runnable, java.lang.Runnable]
  * @return: main.Phase
  * @Author: smx_Morgan
  * @Date: 2022/8/11-21:41
  */
  static Phase level3(Runnable next, Runnable first) {
    Camera c = new Camera(new Point(5,5));
    Sword s = new Sword(c);
    Cells cells = new Cells();
    Monster mon = new Monster(new RoamingMonster(new Point(8,0)));
    Sword mon_s = new Sword(mon);
    mon.setSword(mon_s);
    mon_s.setMon(true);
    mon_s.set(Direction::left).run();
    var m = new Model(){
      List<Entity> entities = List.of(c,s,mon,mon_s);
      public Camera camera(){ return c; }
      public List<Entity> entities(){ return entities; }
      public void remove(Entity e){
        entities = entities.stream()
            .filter(ei->!ei.equals(e))
            .toList();
      }
      public Cells cells(){ return cells; }
      public void onGameOver(){ first.run(); }
      public void onNextLevel(){ next.run(); }
    };
    return new Phase(m,new Controller(c,s));
  }
}