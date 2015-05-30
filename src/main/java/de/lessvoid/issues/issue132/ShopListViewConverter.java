package de.lessvoid.issues.issue132;


import de.lessvoid.nifty.controls.ListBox.ListBoxViewConverter;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.render.NiftyImage;

public class ShopListViewConverter implements ListBoxViewConverter<ShopItem> {
    private static final String NAME = "#name";
    private static final String DESCRIPTION = "#description";
    private static final String PRICE = "#price";
    private static final String ICON = "#icon";

    @Override
    public final void display(final Element listBoxItem, final ShopItem item) {
      changeText(listBoxItem, NAME, item.getName());
      changeText(listBoxItem, DESCRIPTION, item.getDescription());
      changeText(listBoxItem, PRICE, item.getPrice());
      changeIcon(listBoxItem, ICON, item.getIcon());
    }

    private void changeText(Element item, String name, String value) {
      TextRenderer textRenderer = item.findElementById(name).getRenderer(TextRenderer.class);
      textRenderer.setText(value);
    }

    private void changeIcon(Element item, String name, NiftyImage value) {
      ImageRenderer imageRenderer = item.findElementById(name).getRenderer(ImageRenderer.class);
      imageRenderer.setImage(value);
    }

    @Override
    public final int getWidth(final Element listBoxItem, final ShopItem item) {
      return 200;
    }
}
