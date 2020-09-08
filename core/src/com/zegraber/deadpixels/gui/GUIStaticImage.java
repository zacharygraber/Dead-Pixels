package com.zegraber.deadpixels.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zegraber.deadpixels.DeadPixels;
import com.zegraber.deadpixels.SimpleTextureRegion;

public class GUIStaticImage extends GUIElement
{
    private String textureID;

    public GUIStaticImage()
    {
        super();
        this.textureID = "default";
    }

    public GUIStaticImage(String elementID, String textureID, int x, int y, int renderPriority)
    {
        super(elementID, x, y, renderPriority);
        this.textureID = textureID;
    }

    @Override
    public void render(SpriteBatch batch)
    {
        SimpleTextureRegion textureRegion = DeadPixels.textures.get(this.textureID);
        if (textureRegion != null)
        {
            batch.draw(textureRegion.getTextureRegion(), this.location[0], this.location[1], textureRegion.getWidth(), textureRegion.getHeight());
        }
        else
        {
            batch.draw((DeadPixels.textures.get("default")).getTextureRegion(), this.location[0], this.location[1], (DeadPixels.textures.get("default")).getWidth() * DeadPixels.renderScale, (DeadPixels.textures.get("default")).getHeight() * DeadPixels.renderScale);
        }
    }

    @Override
    public void dispose() { }
}
