package de.lessvoid.issues.issue448;

import java.util.logging.Logger;

import com.jogamp.newt.opengl.GLWindow;

import de.lessvoid.issues.JOGLNiftyRunner;
import de.lessvoid.issues.JOGLNiftyRunner.Callback;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class Issue448Main implements ScreenController {
	private static final Logger LOGGER = Logger.getLogger(Issue448Main.class.getName());
	
	private Screen screen;
	
	private long time;
	
	@Override
	public void bind(Nifty arg0, Screen screen) {
		this.screen = screen;
	}

	@Override
	public void onEndScreen() {}

	@Override
	public void onStartScreen() {
		this.time = System.currentTimeMillis();
		LOGGER.info("setVisible(false)");
		this.screen.findElementById("panel").setVisible(false);
	}
	
	public void start() {
		long time = System.currentTimeMillis();
		LOGGER.info("Starting onHide (" + (time - this.time) + " ms after setVisible)");
		this.time = time;
	}
	
	public void end() {
		LOGGER.info("Ending onHide (" + (System.currentTimeMillis() - this.time) + " ms after start)");
	}
	
	public static void main(String[] args) throws Exception {
        JOGLNiftyRunner.run(args, new Callback() {
            @Override
            public void init(final Nifty nifty, final GLWindow glWindow) {
                nifty.fromXml("src/main/resources/de/lessvoid/issues/issue448/issue448.xml", "start");
            }
        });
	}
}
