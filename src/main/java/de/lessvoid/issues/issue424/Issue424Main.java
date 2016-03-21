package de.lessvoid.issues.issue424;

import com.jogamp.newt.opengl.GLWindow;
import de.lessvoid.issues.JOGLNiftyRunner;
import de.lessvoid.issues.JOGLNiftyRunner.Callback;
import de.lessvoid.nifty.Nifty;

import javax.annotation.Nonnull;

/**
 * Example code for Issue 424 on github: https://github.com/nifty-gui/nifty-gui/issues/424
 * @author void
 */
public final class Issue424Main {

  public static void main(final String[] args) throws Exception {
    JOGLNiftyRunner.run(args, new Callback() {

      @Override
      public void init(@Nonnull final Nifty nifty, @Nonnull final GLWindow window) {
        nifty.addScreen(ScreenBuilder424.class.getSimpleName(), new ScreenBuilder424().build(nifty));
        nifty.gotoScreen(ScreenBuilder424.class.getSimpleName());
      }
    });
  }
}
