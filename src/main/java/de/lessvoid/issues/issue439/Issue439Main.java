package de.lessvoid.issues.issue439;

import com.jogamp.newt.opengl.GLWindow;
import de.lessvoid.issues.JOGLNiftyRunner;
import de.lessvoid.issues.JOGLNiftyRunner.Callback;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.RadioButton;
import de.lessvoid.nifty.controls.Window;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * Example code for Issue 439
 */
public final class Issue439Main implements ScreenController {

    Screen screen;

    public static void main(final String[] args) throws Exception {
        JOGLNiftyRunner.run(args, new Callback() {
            @Override
            public void init(final Nifty nifty, final GLWindow glWindow) {
                nifty.fromXml("src/main/resources/de/lessvoid/issues/issue439/issue439.xml", "start");
            }
        });
    }

    @Override
    public void onStartScreen() {
        Window window = screen.findNiftyControl("window", Window.class);
        Element windowE = window.getElement();
        windowE.setVisible(false);
        RadioButton button = screen.findNiftyControl("bottom", RadioButton.class);
        button.select();
        windowE.setVisible(true);
    }

    @Override
    public void bind(Nifty nifty, Screen screen) {
        this.screen = screen;
    }

    @Override
    public void onEndScreen() {
    }
}
