package com.zegraber.deadpixels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Rendered implements Comparable<Rendered>
{
    protected int[] location;
    protected int renderPriority;

    public Rendered()
    {
        this.location = new int [] {0, 0};
        this.renderPriority = 1;
    }

    public Rendered(int x, int y)
    {
        this.location = new int[] {x, y};
        this.renderPriority = 1;
    }

    public Rendered(int x, int y, int renderPriority)
    {
        this(x, y);
        this.renderPriority = renderPriority;
    }

    public abstract void render(SpriteBatch batch);

    public int compareTo(Rendered other)
    {
        return Integer.compare(this.renderPriority, other.getRenderPriority());
    }

    public int getRenderPriority()
    {
        return this.renderPriority;
    }

    public abstract void dispose();
}
