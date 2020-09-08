package com.zegraber.deadpixels.entities;

import com.zegraber.deadpixels.DeadPixels;
import com.zegraber.deadpixels.animations.AnimationState;

public abstract class Character extends Entity
{
    protected int moveSpeed;
    protected int health;
    protected int maxHealth;

    protected int xVelocity;
    protected int yVelocity;

    public Character()
    {
        super();
    }

    public Character(int x, int y)
    {
        super(x, y);
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    public Character(int x, int y, int renderPriority)
    {
        super(x, y, renderPriority);
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.renderPriority = renderPriority;
    }

    public void changeAnimationState(AnimationState s)
    {
        this.animationState = s;
        this.animationEndFrame = DeadPixels.frameCounter + this.animationState.getFrameDuration();
    }
}
