package com.zegraber.deadpixels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ScriptParser
{
    public static boolean checkTrigger(ScriptedEvent e)
    {
        if (e.getTrigger().startsWith("frameMet "))
        {
            if (DeadPixels.frameCounter == Integer.parseInt(e.getTrigger().replace("frameMet ", ""))) { return true; }
        }
        else
        {
            switch (e.getTrigger())
            {
                case "keyPressed_Any":
                    if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) { return true; }
                    break;
                case "keyPressed_Esc":
                    if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) { return true; }
                    break;
            }
        }
        return false;
    }

    public static void executeEffects(ScriptedEvent e)
    {
        for (String effect : e.getEffects())
        {
            if (effect.startsWith("level "))
            {
                DeadPixels.changeLevel(effect.replace("level ", ""));
            }
            else if (effect.startsWith("setCameraPos "))
            {
                String[] pos = ((effect.replace("setCameraPos ", "")).replace(" ", "")).split(",");
                DeadPixels.camera.position.set(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), 0);
            }
            else if (effect.startsWith("addNewEventByDelay ")) // adds a new event that will execute [delay] frames from now. format: "addNewEventByDelay delay, eventID, {effect1; effect2; etc}"
            {
                String[] eventCode = effect.replace("addNewEventByDelay ", "").split(", ");
                int delay = Integer.parseInt(eventCode[0]);
                String id = eventCode[1];
                String[] effects = ((eventCode[2].replace("{","")).replace("}","")).split("; ");
                DeadPixels.currentLevel.addScriptedEventToBuffer(new ScriptedEvent(id, "frameMet " + (DeadPixels.frameCounter + delay), effects));
            }
            else if (effect.startsWith("disableEffect "))
            {
                DeadPixels.currentLevel.deactivateScriptedEventByID(effect.replace("disableEffect ", ""));
            }
            else if (effect.startsWith("enableEffect "))
            {
                DeadPixels.currentLevel.activateScriptedEventByID(effect.replace("enableEffect ", ""));
            }
            else if (effect.startsWith("setTint "))
            {
                String[] rgba = ((effect.replace("setTint ", "")).replace(" ", "")).split(",");
                DeadPixels.batch.setColor(Integer.parseInt(rgba[0]), Integer.parseInt(rgba[1]), Integer.parseInt(rgba[2]), Integer.parseInt(rgba[3]));
            }
            else
            {
                switch (effect)
                {
                    case "hidePlayer":
                        DeadPixels.playerVisible = false;
                        break;
                    case "showPlayer":
                        DeadPixels.playerVisible = true;
                        break;
                    case "setPlayerControlled":
                        DeadPixels.userControllingPlayer = true;
                        break;
                    case "removePlayerControl":
                        DeadPixels.userControllingPlayer = false;
                        break;
                    case "lockCamera":
                        DeadPixels.cameraLockedOnPlayer = true;
                        break;
                    case "unlockCamera":
                        DeadPixels.cameraLockedOnPlayer = false;
                        break;
                    case "exit":
                        Gdx.app.exit();
                        break;
                }
            }
        }
    }
}
