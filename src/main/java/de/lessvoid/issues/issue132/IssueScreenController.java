package de.lessvoid.issues.issue132;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class IssueScreenController implements ScreenController {
  private Nifty nifty;

  @Override
  public void bind(Nifty newNifty, Screen newScreen) {
    this.nifty = newNifty;
  }

  @Override
  public void onStartScreen() {
    NiftyImage icon = nifty.createImage("icon.png", false);

    ListBox<ShopItem> listBox = nifty.getCurrentScreen().findNiftyControl("shopBuyList", ListBox.class);
    listBox.addItem(new ShopItem("name 1", "description 1", "price 1", icon));
    listBox.addItem(new ShopItem("name 2", "description 2", "price 2", icon));
    listBox.addItem(new ShopItem("name 3", "description 3", "price 3", icon));
  }

  @Override
  public void onEndScreen() {
  }
}
