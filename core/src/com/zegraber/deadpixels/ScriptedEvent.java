package com.zegraber.deadpixels;

public class ScriptedEvent
{
    private String eventID;
    private String trigger;
    private String[] effects;
    private boolean active;

    public ScriptedEvent()
    {
        this.trigger = "";
        this.eventID = "null";
        this.effects = new String[] {};
        this.active = true;
    }

    public ScriptedEvent(String[] effects)
    {
        this();
        this.effects = effects;
    }

    public ScriptedEvent(String eventID, String trigger, String[] effects)
    {
        this.eventID = eventID;
        this.trigger = trigger;
        this.effects = effects;
        this.active = true;
    }

    public String getTrigger()
    {
        return this.trigger;
    }

    public String[] getEffects()
    {
        return this.effects;
    }

    public String getEventID()
    {
        return this.eventID;
    }

    public void activate()
    {
        this.active = true;
    }

    public void deactivate()
    {
        this.active = false;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public ScriptedEvent deepCopy()
    {
        return new ScriptedEvent(this.eventID, this.trigger, this.effects.clone());
    }
}
