package de.lessvoid.issues.issue449;

import com.jogamp.newt.opengl.GLWindow;

import de.lessvoid.issues.JOGLNiftyRunner;
import de.lessvoid.issues.JOGLNiftyRunner.Callback;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class Issue449Main1b implements ScreenController {
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
		Element parent = this.screen.findElementById("sp");
		for(int i=0; i < 25; i++) {
			TextBuilder text = new TextBuilder();
			text.text("Lorem ipsum dolor sit amet");
			text.style("base-font");
			text.color("#000f");
			text.build(this.nifty, this.screen, parent);
		}
		
		//WARNING: Attempted to render image with negative height (Scrollbar issue)
	}
	
	public static void main(String[] args) throws Exception {
        JOGLNiftyRunner.run(args, new Callback() {
            @Override
            public void init(final Nifty nifty, final GLWindow glWindow) {
                nifty.fromXml("src/main/resources/de/lessvoid/issues/issue449/issue449_1b.xml", "start");
            }
        });
	}
}
