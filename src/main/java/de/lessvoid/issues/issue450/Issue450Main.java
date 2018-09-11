package de.lessvoid.issues.issue450;

import com.jogamp.newt.opengl.GLWindow;

import de.lessvoid.issues.JOGLNiftyRunner;
import de.lessvoid.issues.JOGLNiftyRunner.Callback;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.ScrollPanel;
import de.lessvoid.nifty.controls.ScrollPanel.AutoScroll;
import de.lessvoid.nifty.controls.scrollpanel.ScrollPanelControl;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class Issue450Main implements ScreenController {
	private Nifty nifty;
	private Screen screen;
	
	@Override
	public void bind(Nifty nifty, Screen screen) {
		this.nifty = nifty;
		this.screen = screen;
	}

	@Override
	public void onEndScreen() {}

	@Override
	public void onStartScreen() {
		Element button = this.screen.findElementById("button");
		button.setStyle("button2"); //there is a difference in backgroundImage when this call is skipped
		this.screen.layoutLayers(); //no visible change after calling this method
		System.out.println(button.getStyle()); //you can see that the style name is updated
	}
	
	public static void main(String[] args) throws Exception {
        JOGLNiftyRunner.run(args, new Callback() {
            @Override
            public void init(final Nifty nifty, final GLWindow glWindow) {
                nifty.fromXml("src/main/resources/de/lessvoid/issues/issue450/issue450.xml", "start");
            }
        });
	}
}
