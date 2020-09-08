package com.zegraber.deadpixels.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zegraber.deadpixels.DeadPixels;

public class Player extends Character
{
    public Player()
    {
        super();
        this.playerConstructorHelper();
    }

    public Player(int x, int y)
    {
        super(x, y);
        this.playerConstructorHelper();
    }

    public Player(int x, int y, int renderPriority)
    {
        super(x, y, renderPriority);
        this.playerConstructorHelper();
    }

    private void playerConstructorHelper()
    {
        this.animationState = DeadPixels.animations.get("player_idle_down");
        this.animationEndFrame = 0;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.moveSpeed = 4;
        this.health = 100;
        this.maxHealth = 100;
    }

    /* ************************************
     *  MOVEMENT                          *
     *************************************/
    public void render(SpriteBatch batch)
    {
        this.xVelocity = 0;
        this.yVelocity = 0;
        if (DeadPixels.userControllingPlayer && Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
        {
            if (Gdx.input.isKeyPressed(Input.Keys.A))
            {
                this.xVelocity -= this.moveSpeed;
                if (this.animationState != DeadPixels.animations.get("player_walk_left"))
                {
                    this.changeAnimationState(DeadPixels.animations.get("player_walk_left"));
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D))
            {
                this.xVelocity += this.moveSpeed;
                if (this.animationState != DeadPixels.animations.get("player_walk_right"))
                {
                    this.changeAnimationState(DeadPixels.animations.get("player_walk_right"));
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W))
            {
                this.yVelocity += this.moveSpeed;
                if (this.animationState != DeadPixels.animations.get("player_walk_up") && this.xVelocity == 0)
                {
                    this.changeAnimationState(DeadPixels.animations.get("player_walk_up"));
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S))
            {
                this.yVelocity -= this.moveSpeed;
                if (this.animationState != DeadPixels.animations.get("player_walk_down") && this.xVelocity == 0)
                {
                    this.changeAnimationState(DeadPixels.animations.get("player_walk_down"));
                }
            }
        }
        else
        {
            this.animationState = DeadPixels.animations.get("player_idle_down");
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)) && (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S)))
        {
            this.location[0] += this.xVelocity * 0.75;
            this.location[1] += this.yVelocity * 0.75;
        }
        else
        {
            this.location[0] += this.xVelocity;
            this.location[1] += this.yVelocity;
        }

        super.render(batch);
    }
}
