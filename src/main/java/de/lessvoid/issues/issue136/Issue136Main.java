package de.lessvoid.issues.issue136;


import org.bushe.swing.event.EventTopicSubscriber;

import com.jogamp.newt.opengl.GLWindow;

import de.lessvoid.issues.JOGLNiftyRunner;
import de.lessvoid.issues.JOGLNiftyRunner.Callback;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.ControlDefinitionBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.AbstractController;
import de.lessvoid.nifty.controls.NiftyControl;
import de.lessvoid.nifty.controls.Parameters;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.events.NiftyMousePrimaryClickedEvent;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * Example code for Issue 132 on github: https://github.com/void256/nifty-gui/issues/136
 * @author void
 */
public final class Issue136Main implements Callback {
  @Override
  public void init(final Nifty nifty, final GLWindow window) {
    nifty.loadStyleFile("nifty-default-styles.xml");
    nifty.loadControlFile("nifty-default-controls.xml");

    new ControlDefinitionBuilder("imageSelector") {{
      controller(ImageSelector.class.getName());
      panel(new PanelBuilder() {{
        childLayoutVertical();
        panel(new PanelBuilder() {{
          childLayoutHorizontal();
          panel(new PanelBuilder("#isel_bg") {{
            width("532px");
            height("532px");
            childLayoutVertical();
            visibleToMouse(true);
            backgroundColor("#f00c");
            control(new ButtonBuilder("closeButton", "Close") {{
              interactOnClick("close()");
            }});
            image(new ImageBuilder("#isel_image") {{
              x("10");
              y("10");
              filename(controlParameter("image"));
              visibleToMouse(true);
            }});
          }});
        }});
      }});
    }}.registerControlDefintion(nifty);

    new PopupBuilder("popupItemFrameAreaSelect") {{
      childLayoutCenter();
      backgroundColor("#000a");
      panel(new PanelBuilder() {{
        width("600px");
        height("640px");
        childLayoutVertical();
        style("nifty-panel-bright");
        control(new ControlBuilder("imageSelector") {{
          parameter("image", "nifty-logo-150x150.png");
        }});
      }});
    }}.registerPopup(nifty);

    new ScreenBuilder("start") {{
      controller(new MyScreenController());
      layer(new LayerBuilder("background") {{
        backgroundColor("#f008");
        childLayoutCenter();
        control(new ButtonBuilder("showPopupButton", "SHOW") {{
          interactOnClick("showPopup()");
        }});
      }});
    }}.build(nifty);
    nifty.gotoScreen("start");
  }

  public static void main(final String[] args) throws Exception {
    JOGLNiftyRunner.run(args, new Issue136Main());
  }

  public static class ImageEventTopicSubscriber implements EventTopicSubscriber<NiftyMousePrimaryClickedEvent> {
    @Override
    public void onEvent(final String id, final NiftyMousePrimaryClickedEvent event) {
      System.out.println("EVENT: " + event.toString());
    }
  }

  public static class ImageSelector extends AbstractController implements NiftyControl {
    @Override
    public void bind(
        Nifty nifty,
        Screen screen,
        Element element,
        Parameters parameters) {
      System.out.println(element.findElementById("#isel_bg").getId());
      nifty.subscribe(screen, element.findElementById("#isel_bg").getId(), NiftyMousePrimaryClickedEvent.class, new ImageEventTopicSubscriber());
      super.init(parameters);
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public boolean inputEvent(NiftyInputEvent inputEvent) {
      return false;
    }
  }

  public static class MyScreenController implements ScreenController {
    private Nifty nifty;
    private Screen screen;

    @Override
    public void bind(final Nifty nifty, final Screen screen) {
      this.nifty = nifty;
      this.screen = screen;
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }

    public void showPopup() {
      nifty.createPopupWithId("popupItemFrameAreaSelect", "popupId");
      nifty.showPopup(screen, "popupId", null);
    }

    public void close() {
      nifty.closePopup("popupId");
    }
  }
}
