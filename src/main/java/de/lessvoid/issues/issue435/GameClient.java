package de.lessvoid.issues.issue435;
import com.jogamp.newt.opengl.GLWindow;

import de.lessvoid.issues.JOGLNiftyRunner;
import de.lessvoid.issues.JOGLNiftyRunner.Callback;
import de.lessvoid.issues.issue417.Main.ExitButtonScreenController;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.render.batch.BatchRenderDevice;
import de.lessvoid.nifty.renderer.lwjgl.input.LwjglInputSystem;
import de.lessvoid.nifty.renderer.lwjgl.render.LwjglBatchRenderBackendCoreProfileFactory;
import de.lessvoid.nifty.renderer.lwjgl.render.LwjglRenderDevice;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;

import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;



/**
 * This is supposed to be a short demo of this issue: https://github.com/nifty-gui/nifty-gui/issues/435
 * 
 * When nifty has been loaded simply click on the "C" button on the panel in the middle of the screen.
 * A popup should open which contains a ScrollPanel on the right.
 * I added some values via XML and via Java, the label is getting rendered but no images or backgroundcolours of attached panels.
 * 
 * 
 * Using the JOGLNiftyRunner and and creating the screen directly as non popup, results in a working version.
 * Using the LWJGL/GL example of issue 417 and starting the popup directly works as well.
 * 
 * Issue seems to be Slick2D related?
 * 
 * 
 * @author raLa
 * 
 */

public class GameClient{
	
	public static Nifty nifty;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	public static void main(String[] args) throws Exception {
			    	initLWJGL();
			  		initGL();
			  		LwjglInputSystem inputSystem = initInput();
			  		nifty = initNifty(inputSystem);
			  		nifty.loadStyleFile("nifty-default-styles.xml");
			  		nifty.loadControlFile("nifty-default-controls.xml");

			    	  
				  	CharInfoManager.registerCharBox(nifty); 	    	  
			  		nifty.fromXml("src/main/resources/de/lessvoid/issues/issue435/issue435.xml", "overlay", new CoreGameStateGUIController());	
			  		
			  		
			  		
			  		CharInfoManager.registerCharBox(nifty);
			  		CoreGameStateGUIController.C();
			  		
			  		
			  		renderLoop(nifty);
					shutDown(inputSystem);


	}
	
	private static LwjglInputSystem initInput() throws Exception {
		LwjglInputSystem inputSystem = new LwjglInputSystem();
		inputSystem.startup();
		return inputSystem;
	}
	
	private static void initLWJGL() throws Exception {
		DisplayMode currentMode = Display.getDisplayMode();
		DisplayMode[] modes = Display.getAvailableDisplayModes();
		List<DisplayMode> matching = new ArrayList<DisplayMode>();
		for (int i=0; i<modes.length; i++) {
			DisplayMode mode = modes[i];
			if (mode.getWidth() == WIDTH &&
					mode.getHeight() == HEIGHT &&
					mode.getBitsPerPixel() == 32 ) {
				matching.add(mode);
			}
		}
		
		DisplayMode[] matchingModes = matching.toArray(new DisplayMode[0]);
		boolean found = false;
		for (int i=0; i<matchingModes.length; i++) {
			if (matchingModes[i].getFrequency() == currentMode.getFrequency()) {
				Display.setDisplayMode(matchingModes[i]);
				found = true;
				break;
			}
		}
		
		if (!found) {
			Arrays.sort(matchingModes, new Comparator < DisplayMode >() {
				public int compare(final DisplayMode o1, final DisplayMode o2) {
					if (o1.getFrequency() > o2.getFrequency()) {
						return 1;
					} else if (o1.getFrequency() < o2.getFrequency()) {
						return -1;
					} else {
						return 0;
					}
				}
			});
			
			if (matchingModes.length > 0) {
				Display.setDisplayMode(matchingModes[0]);
			}
		}
		
		int x = (currentMode.getWidth() - Display.getDisplayMode().getWidth()) / 2;
		int y = (currentMode.getHeight() - Display.getDisplayMode().getHeight()) / 2;
		Display.setLocation(x, y);
		Display.setFullscreen(false);
		Display.create(new PixelFormat(), new ContextAttribs(3, 2).withProfileCore(true));
		Display.setVSyncEnabled(false);
		Display.setTitle("Hello Nifty");
	}
	
	private static void initGL() {
		glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClear(GL11.GL_COLOR_BUFFER_BIT);
		glEnable(GL11.GL_BLEND);
		glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private static Nifty initNifty(final LwjglInputSystem inputSystem) throws Exception {
		return new Nifty(
				new BatchRenderDevice(LwjglBatchRenderBackendCoreProfileFactory.create()),
				new NullSoundDevice(),
				inputSystem,
				new AccurateTimeProvider());
	}

	
	private static void renderLoop(final Nifty nifty) {
		boolean done = false;
		while (!Display.isCloseRequested() && !done) {
			Display.update();
			if (nifty.update()) {
				done = true;
			}
			nifty.render(true);
			int error = GL11.glGetError();
			if (error != GL11.GL_NO_ERROR) {
				String glerrmsg = GLU.gluErrorString(error);
				System.err.println(glerrmsg);
			}
		}
	}
	
	private static void shutDown(final LwjglInputSystem inputSystem) {
		inputSystem.shutdown();
		Display.destroy();
		System.exit(0);
	}

}
