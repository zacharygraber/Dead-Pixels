package com.zegraber.deadpixels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class SimpleTextureRegion implements Json.Serializable
{
    private transient TextureRegion textureRegion;
    private transient Texture texture;

    private int x;
    private int y;
    private int width;
    private int height;
    private String texturePath;

    public SimpleTextureRegion()
    {
        this("textures/default_texture.png", 0, 0, 16, 16);
    }

    public SimpleTextureRegion(String texturePath, int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.texturePath = texturePath;
        this.texture = new Texture(texturePath);
        this.textureRegion = new TextureRegion(this.texture, x, y, w, h);
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public TextureRegion getTextureRegion()
    {
        return this.textureRegion;
    }

    public void dispose()
    {
        this.texture.dispose();
        this.textureRegion.getTexture().dispose();
    }

    @Override
    public void write(Json json)
    {
        json.writeValue("texturePath", this.texturePath, String.class);
        json.writeValue("x", this.x, Integer.class);
        json.writeValue("y", this.y, Integer.class);
        json.writeValue("width", this.width, Integer.class);
        json.writeValue("height", this.height, Integer.class);
    }

    @Override
    public void read(Json json, JsonValue jsonData)
    {
        this.texturePath = jsonData.getString("texturePath");
        this.texture = new Texture(this.texturePath);
        this.x = jsonData.getInt("x");
        this.y = jsonData.getInt("y");
        this.width = jsonData.getInt("width");
        this.height = jsonData.getInt("height");
        this.textureRegion = new TextureRegion(this.texture, this.x, this.y, this.width, this.height);
    }
}
