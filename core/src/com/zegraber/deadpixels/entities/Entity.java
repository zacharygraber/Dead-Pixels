package com.zegraber.deadpixels.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zegraber.deadpixels.DeadPixels;
import com.zegraber.deadpixels.Rendered;
import com.zegraber.deadpixels.SimpleTextureRegion;
import com.zegraber.deadpixels.animations.AnimationState;

public abstract class Entity extends Rendered
{
    protected AnimationState animationState;
    protected long animationEndFrame;

    public Entity()
    {
        super();
        this.animationState = new AnimationState();
    }

    public Entity(int x, int y)
    {
        super(x, y);
        this.animationState = new AnimationState();
    }

    public Entity(int x, int y, int renderPriority)
    {
        super(x, y, renderPriority);
        this.animationState = new AnimationState();
    }

    public Entity(int x, int y, int renderPriority, AnimationState startingState)
    {
        super(x, y, renderPriority);
        this.animationState = startingState;
    }

    public int getX()
    {
        return this.location[0];
    }

    public int getY()
    {
        return this.location[1];
    }

    public SimpleTextureRegion getAnimationFrame()
    {
        return this.animationState.getCurrentAnimationFrame();
    }

    public void render(SpriteBatch batch)
    {
        if (DeadPixels.frameCounter == this.animationEndFrame)
        {
            this.animationState.nextFrame();
            this.animationEndFrame = DeadPixels.frameCounter + this.animationState.getFrameDuration();
        }
        batch.draw(this.animationState.getCurrentAnimationFrame().getTextureRegion(), this.location[0], this.location[1], this.animationState.getCurrentAnimationFrame().getWidth() * DeadPixels.renderScale, this.animationState.getCurrentAnimationFrame().getHeight() * DeadPixels.renderScale);
    }

    public void dispose() { }
}
