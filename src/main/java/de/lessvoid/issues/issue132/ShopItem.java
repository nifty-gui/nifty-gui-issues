package de.lessvoid.issues.issue132;

import de.lessvoid.nifty.render.NiftyImage;

public class ShopItem {
  private String name;
  private String description;
  private String price;
  private NiftyImage icon;

  public ShopItem(String name, String description, String price, NiftyImage icon) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.icon = icon;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getPrice() {
    return price;
  }

  public NiftyImage getIcon() {
    return icon;
  }
}
