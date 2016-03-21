package de.lessvoid.issues.issue424;

import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.tools.SizeValue;

public class ScreenBuilder424 extends ScreenBuilder {

  public ScreenBuilder424() {
    super(ScreenBuilder424.class.getSimpleName());

    layer(new LayerBuilder("TestLayer") {{
      panel(new PanelBuilder("TestPanel") {{
        control(new LabelBuilder("TestLabel", "TestText") {{
          height(SizeValue.max(50));
        }});
      }});
    }});
  }
}
