package de.lessvoid.issues.issue417;

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

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.ScrollPanel;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.render.batch.BatchRenderDevice;
import de.lessvoid.nifty.renderer.lwjgl.input.LwjglInputSystem;
import de.lessvoid.nifty.renderer.lwjgl.render.LwjglBatchRenderBackendCoreProfileFactory;
import de.lessvoid.nifty.screen.DefaultScreenController;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;
import de.lessvoid.nifty.tools.SizeValue;

public class Main {
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
	
	public static void main(final String[] args) throws Exception {
		initLWJGL();
		initGL();
		LwjglInputSystem inputSystem = initInput();
		Nifty nifty = initNifty(inputSystem);
		nifty.loadStyleFile("nifty-default-styles.xml");
		nifty.loadControlFile("nifty-default-controls.xml");

		nifty.fromXml("layout.xsd", "start", new ExitButtonScreenController());
		Screen screen = nifty.getScreen("start");
		Element mainText = screen.findElementById("main_text");
		TextRenderer renderer = mainText.getRenderer(TextRenderer.class);
		/*renderer.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris suscipit quam sed blandit dictum. Duis ultrices "+
				"metus nunc, eget dignissim diam congue non. Suspendisse faucibus, ante id pulvinar laoreet, tellus erat convallis risus, ut "+
				"rutrum orci tortor vitae magna. Cras hendrerit ut nibh euismod luctus. Sed interdum, mauris sit amet fermentum malesuada, "+
				"arcu ligula venenatis odio, et vestibulum purus arcu a lacus. Ut pellentesque a ante a bibendum. Class aptent taciti "+
				"sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur mollis justo quis metus facilisis, "+
				"et viverra enim ullamcorper. Nullam consectetur massa et leo facilisis egestas. Nulla urna risus, aliquam a turpis "+
				"vitae, auctor suscipit ex. Integer venenatis nisi tortor, nec malesuada mi eleifend sit amet. Nam eu congue magna.\n\n\n"+
				"Curabitur consequat ante orci, vitae maximus justo tristique ullamcorper. Donec non scelerisque felis. Nullam luctus ex "+
				"nibh, sed fringilla nibh blandit in. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. "+
				"Donec suscipit vulputate dui, sit amet facilisis justo volutpat nec. Aliquam vel rhoncus lectus. Donec cursus, augue quis "+
				"eleifend molestie, est leo tempor leo, pellentesque faucibus libero mauris a mi. Duis dictum hendrerit elit, quis sagittis "+
				"elit. Suspendisse ornare eleifend orci, id commodo ligula cursus in. Pellentesque non tellus semper, euismod ante et, "+
				"feugiat velit. Suspendisse ex nulla, suscipit sed erat consequat, congue luctus diam. Sed id luctus sapien. Suspendisse "+
				"nec felis id orci pulvinar tincidunt. Phasellus pellentesque sem eu nisi lacinia, pulvinar vestibulum magna ullamcorper.\n\n\n\n"+
				"\n\n\n\n\n\n\n asdfasd asdg aergvsehert gsetrg\n\n\n\n\n\n"
				+ "\n\n\n\nfdgasfdgsrfgr\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n end\n\nanother one");
		*/
		
		
		Element mainTextPanel = screen.findElementById("main_text_panel");
		Element mainTextPanelContainer = screen.findElementById("main_text_panel_container");
		int mainTextPanelContainerTopPadding = mainTextPanelContainer.getPaddingTop().getValueAsInt(1)*4;
		System.out.println("mainTextPanelContainerTopPadding: "+mainTextPanelContainerTopPadding);
		
		ScrollPanel scrollPanel = screen.findNiftyControl("main_text_scrollbar", ScrollPanel.class);
		System.out.println("mainTextPanelContainer.getHeight(): "+mainTextPanelContainer.getHeight());
		mainTextPanel.setConstraintHeight(new SizeValue((mainTextPanelContainer.getHeight()+scrollPanel.getHeight()*2+mainTextPanelContainerTopPadding)+"px"));
		mainTextPanel.setConstraintWidth(new SizeValue((mainTextPanelContainer.getWidth())+"px"));
		
		int newTextPanelHeight = mainTextPanel.getConstraintHeight().getValueAsInt(1);
		System.out.println("newTextPanelHeight: "+newTextPanelHeight);
		scrollPanel.setHeight(new SizeValue(newTextPanelHeight+"px"));
		
		renderLoop(nifty);
		shutDown(inputSystem);
	}

	public static class ExitButtonScreenController extends DefaultScreenController {
		@NiftyEventSubscriber(id="exit")
		public void exit(final String id, final ButtonClickedEvent event) {
			nifty.exit();
		}
		
		@NiftyEventSubscriber(id="exit2")
		public void exit2(final String id, final ButtonClickedEvent event) {
			nifty.exit();
		}
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
