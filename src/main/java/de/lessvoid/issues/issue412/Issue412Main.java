package de.lessvoid.issues.issue412;

import com.jogamp.newt.opengl.GLWindow;
import de.lessvoid.issues.JOGLNiftyRunner;
import de.lessvoid.issues.JOGLNiftyRunner.Callback;
import de.lessvoid.nifty.Nifty;


/**
 * Example code for Issue 132 on github: https://github.com/void256/nifty-gui/issues/132
 * @author void
 */
public final class Issue412Main {

  public static void main(final String[] args) throws Exception {
    JOGLNiftyRunner.run(args, new Callback() {

      @Override
      public void init(final Nifty nifty, final GLWindow window) {
        nifty.fromXml("de/lessvoid/issues/issue412/issue412.xml", "start");
      }
    });
  }
}
