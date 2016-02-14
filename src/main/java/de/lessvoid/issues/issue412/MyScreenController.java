/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lessvoid.issues.issue412;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.Color;

/**
 *
 * @author Carl
 */
public class MyScreenController implements ScreenController {

    private Nifty nifty;
    
    @Override
    public void bind(Nifty nifty, Screen screen){
        this.nifty = nifty;
      this.screen = screen;
    }

    @Override
    public void onStartScreen(){
    }

    @Override
    public void onEndScreen(){
        
    }
    
    public void show(String indexString){
        int index = Integer.parseInt(indexString);
        showColor(getPanelRenderer("button_" + index).getBackgroundColor());
    }
    
    public void hide(){
        showColor(Color.WHITE);
    }
    
    private void showColor(Color color){
        getPanelRenderer("display").setBackgroundColor(color);
    }
    
    private PanelRenderer getPanelRenderer(String id){
        return nifty.getCurrentScreen().findElementById(id).getRenderer(PanelRenderer.class);
    }

  private Screen screen;
}
