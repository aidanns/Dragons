package au.edu.unimelb.cis.dragons.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import au.edu.unimelb.cis.dragons.core.DragonsGame;

public class DragonsGameJava {

  public static void main(String[] args) {
    JavaPlatform.Config config = new JavaPlatform.Config();
    // use config to customize the Java platform, if needed
    JavaPlatform.register(config);
    PlayN.run(new DragonsGame(new StubLocalPersistenceClient(), JavaPlatform.register()));
  }
}
