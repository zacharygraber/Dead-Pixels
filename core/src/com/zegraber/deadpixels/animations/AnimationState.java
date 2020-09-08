package com.zegraber.deadpixels.animations;

import com.zegraber.deadpixels.DeadPixels;
import com.zegraber.deadpixels.SimpleTextureRegion;

public class AnimationState
{

    private int frameDuration;
    private int currentAnimationFrame;
    private String[] animationFrames;

    public AnimationState()
    {
        this.frameDuration = 60;
        this.currentAnimationFrame = 0;
        this.animationFrames = new String[] {"default"};
    }

    public AnimationState(String[] frames, int frameDuration)
    {
        this.animationFrames = frames;
        this.frameDuration = frameDuration;
        this.currentAnimationFrame = 0;
    }

    public int getFrameDuration()
    {
        return frameDuration;
    }

    public SimpleTextureRegion getCurrentAnimationFrame()
    {
        SimpleTextureRegion textureRegion = DeadPixels.textures.get(animationFrames[currentAnimationFrame]);
        if (textureRegion == null)
        {
            return DeadPixels.textures.get("default");
        }
        else
        {
            return textureRegion;
        }
    }

    public String[] getAnimationFrames()
    {
        return this.animationFrames;
    }

    public void setFrameDuration(int frameDuration)
    {
        this.frameDuration = frameDuration;
    }

    public void setCurrentAnimationFrame(int index)
    {
        this.currentAnimationFrame = index;
    }

    public void nextFrame()
    {
        if (this.currentAnimationFrame == this.animationFrames.length - 1)
        {
            this.currentAnimationFrame = 0;
        }
        else
        {
            this.currentAnimationFrame += 1;
        }
    }
}
