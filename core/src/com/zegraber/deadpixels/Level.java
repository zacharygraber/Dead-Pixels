package com.zegraber.deadpixels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zegraber.deadpixels.gui.GUIElement;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Level
{
    private PriorityQueue<Rendered> renderedObjects;
    private PriorityQueue<GUIElement> GUIElements;
    private ArrayList<ScriptedEvent> scriptedEvents;
    private ArrayList<ScriptedEvent> addedEventBuffer;
    private ScriptedEvent onLoadEvent;
    private int[] spawnPoint;

    public Level()
    {
        this.renderedObjects = new PriorityQueue<>();
        this.GUIElements = new PriorityQueue<>();
        this.scriptedEvents = new ArrayList<>();
        this.addedEventBuffer = new ArrayList<>();
        this.onLoadEvent = new ScriptedEvent();
        this.spawnPoint = new int[] {0, 0};
    }

    public Level(int x, int y)
    {
        this.renderedObjects = new PriorityQueue<>();
        this.GUIElements = new PriorityQueue<>();
        this.scriptedEvents = new ArrayList<>();
        this.addedEventBuffer = new ArrayList<>();
        this.onLoadEvent = new ScriptedEvent();
        this.spawnPoint = new int[] {x, y};
    }

    public PriorityQueue<Rendered> getRenderedObjects()
    {
        return this.renderedObjects;
    }

    public void addRenderedObject(Rendered o)
    {
        this.renderedObjects.add(o);
    }

    public void removeRenderedObject(Rendered o)
    {
        this.renderedObjects.remove(o);
    }

    public void addGUIElement(GUIElement e)
    {
        this.GUIElements.add(e);
    }

    public void addScriptedEvent(ScriptedEvent e)
    {
        this.scriptedEvents.add(e);
    }

    public void addScriptedEventToBuffer(ScriptedEvent e)
    {
        this.addedEventBuffer.add(e);
    }

    public void setOnLoadEvent(ScriptedEvent e)
    {
        this.onLoadEvent = e;
    }

    public void removeGUIElementByID(String elementID)
    {
        GUIElement element = null;
        for (GUIElement e : this.GUIElements)
        {
            if (e.getElementID().equals(elementID))
            {
                element = e;
            }
        }
        if (element != null)
        {
            this.GUIElements.remove(element);
        }
    }

    public void removeScriptedEventByID(String eventID)
    {
        ScriptedEvent event = null;
        for (ScriptedEvent e : this.scriptedEvents)
        {
            if (e.getEventID().equals(eventID))
            {
                event = e;
            }
        }
        if (event != null)
        {
            this.GUIElements.remove(event);
        }
    }

    public void activateScriptedEventByID(String eventID)
    {
        for (ScriptedEvent e : this.scriptedEvents)
        {
            if (e.getEventID().equals(eventID))
            {
                e.activate();
            }
        }
    }

    public void deactivateScriptedEventByID(String eventID)
    {
        for (ScriptedEvent e : this.scriptedEvents)
        {
            if (e.getEventID().equals(eventID))
            {
                e.deactivate();
            }
        }
    }

    public void onLoad()
    {
        ScriptParser.executeEffects(this.onLoadEvent);
    }

    public void renderObjects(SpriteBatch batch)
    {
        for (Rendered o : this.renderedObjects)
        {
            o.render(batch);
        }
    }

    public void handleScriptedEvents()
    {
        for (ScriptedEvent e : this.scriptedEvents)
        {
            if (e.isActive() && ScriptParser.checkTrigger(e)) { ScriptParser.executeEffects(e); }
        }
        for (ScriptedEvent e : this.addedEventBuffer)
        {
            this.addScriptedEvent(e.deepCopy());
        }
        this.addedEventBuffer.clear();
    }

    public void renderGUI(SpriteBatch batch)
    {
        for (GUIElement e : this.GUIElements)
        {
            e.render(batch);
        }
    }
}