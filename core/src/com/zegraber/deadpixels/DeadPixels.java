package com.zegraber.deadpixels;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.zegraber.deadpixels.animations.AnimationState;
import com.zegraber.deadpixels.entities.Player;
import com.zegraber.deadpixels.environments.StaticElement;
import com.zegraber.deadpixels.gui.GUIStaticImage;

import java.util.HashMap;

public class DeadPixels extends ApplicationAdapter {
	public static long frameCounter;
	public static final int renderScale = 4;

	public static HashMap<String, SimpleTextureRegion> textures;
	public static HashMap<String, AnimationState> animations;
	public static HashMap<String, Level> levels;
	public static Player player;
	public static Level currentLevel;
	public static OrthographicCamera camera;
	public static SpriteBatch batch;

	public static boolean userControllingPlayer;
	public static boolean playerVisible;
	public static boolean cameraLockedOnPlayer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		textures = readTextures();
		animations = readAnimations();

		Level level = new Level(0,0);
		level.addGUIElement(new GUIStaticImage("menu_logo", "title_screen_2", 0, 0, 1));
		level.setOnLoadEvent(new ScriptedEvent(new String[] {"hidePlayer", "removePlayerControl", "unlockCamera", "setCameraPos 960, 540"}));
		level.addScriptedEvent(new ScriptedEvent("quit_on_escape", "keyPressed_Esc", new String[] {"exit"}));
		level.addScriptedEvent(new ScriptedEvent("press_any_key", "keyPressed_Any", new String[] {"addNewEventByDelay 15, level_change, {level testLevel}"}));
		Json json = new Json();
		FileHandle file = Gdx.files.local("levels/mainMenu.json");
		file.writeString(json.prettyPrint(level), false);

		levels = readLevels();

		player = new Player(0, 0);
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		frameCounter = 0;

		changeLevel("mainMenu");
	}

	@Override
	public void render () {

		updateFrameCounter();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen, apparently.

		currentLevel.handleScriptedEvents();

		batch.begin();

		currentLevel.renderObjects(batch);
		if (playerVisible) { player.render(batch); } // Only render the player if they're visible. Not visible means the player is also not controllable.
		currentLevel.renderGUI(batch);

		batch.end();

		if (cameraLockedOnPlayer) { camera.position.set(player.getX() + (player.getAnimationFrame().getWidth() / 2), player.getY() + (player.getAnimationFrame().getHeight() / 2), 0); } // Lock the camera to the player
		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (String key : textures.keySet())
		{
			textures.get(key).dispose();
		}
	}

	public static void changeLevel(String levelID)
	{
		if (levels.containsKey(levelID))
		{
			currentLevel = levels.get(levelID);
			currentLevel.onLoad();
		}
	}

	private void updateFrameCounter()
	{
		frameCounter += 1;
	}

	private HashMap<String, Level> readLevels()
	{
		HashMap<String, Level> levelsMap = new HashMap<>();
		FileHandle[] files = Gdx.files.local("levels/").list();
		Json json = new Json();
		for (FileHandle file: files) {
			if (file.name().endsWith(".json"))
			{
				levelsMap.put(file.name().replace(".json", ""), (Level) json.fromJson(Level.class, file.readString()));
			}
		}
		return levelsMap;
	}

	private HashMap<String, SimpleTextureRegion> readTextures()
	{
		HashMap<String, SimpleTextureRegion> texturesMap = new HashMap<>();
		FileHandle[] files = Gdx.files.local("textures/").list();
		Json json = new Json();
		for (FileHandle file: files) {
			if (file.name().endsWith(".json"))
			{
				texturesMap.put(file.name().replace(".json", ""), (SimpleTextureRegion) json.fromJson(SimpleTextureRegion.class, file.readString()));
			}
		}
		return texturesMap;
	}

	private HashMap<String, AnimationState> readAnimations()
	{
		HashMap<String, AnimationState> animationsMap = new HashMap<>();
		FileHandle[] files = Gdx.files.local("animations/").list();
		Json json = new Json();
		for (FileHandle file: files) {
			if (file.name().endsWith(".json"))
			{
				animationsMap.put(file.name().replace(".json", ""), (AnimationState) json.fromJson(AnimationState.class, file.readString()));
			}
		}
		return animationsMap;
	}
}
