package au.edu.unimelb.cis.dragons.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import au.edu.unimelb.cis.dragons.core.DragonsGame;

public class DragonsGameActivity extends GameActivity {

  @Override
  public void main(){
    PlayN.run(new DragonsGame());
  }
}
