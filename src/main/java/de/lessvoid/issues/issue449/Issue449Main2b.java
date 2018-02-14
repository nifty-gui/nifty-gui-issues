package de.lessvoid.issues.issue449;

import com.jogamp.newt.opengl.GLWindow;

import de.lessvoid.issues.JOGLNiftyRunner;
import de.lessvoid.issues.JOGLNiftyRunner.Callback;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ElementBuilder.ChildLayoutType;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class Issue449Main2b implements ScreenController {
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
		Element parent = this.screen.findElementById("cpanel");
		String s[] = new String[] {"Lorem", "ipsum", "dolor", "sit", "amet"};
		for(int i=0; i < 5; i++) {
			TextBuilder text = new TextBuilder();
			text.text(s[i]);
			text.style("base-font");
			text.color("#000f");
			text.build(this.nifty, this.screen, parent);
			
			PanelBuilder panel = new PanelBuilder();
			panel.childLayout(ChildLayoutType.AbsoluteInside);
			Element p = panel.build(this.nifty, this.screen, parent);
			
			ImageBuilder image = new ImageBuilder();
			image.filename("src/main/resources/icon.png");
			image.build(this.nifty, this.screen, p);
		}
		
		//WARNING: Attempted to render image with negative height
		//created image won't render; it works fine when the panel does not have AbsoluteInside layout
	}
	
	public static void main(String[] args) throws Exception {
        JOGLNiftyRunner.run(args, new Callback() {
            @Override
            public void init(final Nifty nifty, final GLWindow glWindow) {
                nifty.fromXml("src/main/resources/de/lessvoid/issues/issue449/issue449_2b.xml", "start");
            }
        });
	}
}
