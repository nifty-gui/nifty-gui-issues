package de.lessvoid.issues.issue435;


import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ElementBuilder.Align;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.elements.Element;


public class CharInfoManager {
	
	public static Element charBox;
	
	public static void registerCharBox(Nifty nifty){
		 charBox = nifty.createPopupWithId("charBox", "charBox");
	}
	
	public static void showCharBox(Nifty nifty){
		nifty.showPopup(nifty.getCurrentScreen(), "charBox", null);
	}
		
	//Adds to the scrollpanel
	public static void createInventoryGUI(Nifty nifty){			
			PanelBuilder pb = new PanelBuilder();
			pb.id("test1");
			pb.childLayoutHorizontal();
			pb.width("100px");
			pb.height("30");
			pb.paddingLeft("5px");			
			Element e = charBox.findElementById("myScrollStuff");
			
			Element itemPanel = pb.build(nifty, nifty.getCurrentScreen(), e);
						
			PanelBuilder pb2 = new PanelBuilder();
			pb2.childLayoutAbsoluteInside();
			pb2.backgroundColor("#0f0a"); //not shown either
			pb2.width("32px");
			pb2.height("32px");		
			pb2.imageMode("sprite:32,32,6");
			
			ImageBuilder ib = new ImageBuilder("src/main/resources/de/lessvoid/issues/issue435/items.png");
			ib.imageMode("sprite:32,32,6");
			ib.childLayoutAbsolute();
			ib.width("32px");
			ib.height("32px");
			ib.id(ib.hashCode()+""+"test".toString());
						
			pb2.image(ib);
			pb2.build(nifty, nifty.getCurrentScreen(), itemPanel);
		
			
			LabelBuilder lb2 = new LabelBuilder();
			lb2.label("test_via_java");
			lb2.align(Align.Center);
			lb2.build(nifty, nifty.getCurrentScreen(), itemPanel);	
			
			e.layoutElements();
		}
				
		
	
}
