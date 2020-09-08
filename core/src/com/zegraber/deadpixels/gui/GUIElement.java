package com.zegraber.deadpixels.gui;

import com.zegraber.deadpixels.Rendered;

public abstract class GUIElement extends Rendered
{
    protected String elementID;
    protected boolean visible;

    public GUIElement()
    {
        super();
        this.elementID = null;
        this.visible = true;
    }

    public GUIElement(String elementID, int x, int y, int renderPriority)
    {
        super(x, y, renderPriority);
        this.elementID = elementID;
        this.visible = true;
    }

    public String getElementID()
    {
        return this.elementID;
    }

    public void hide()
    {
        this.visible = false;
    }

    public void show()
    {
        this.visible = true;
    }
}
