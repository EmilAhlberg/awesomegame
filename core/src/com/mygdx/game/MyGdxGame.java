package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controls.PlayerControllerSystem;
import com.mygdx.game.screens.StartScreen;

public class MyGdxGame extends Game {

	@Override
	public void create () {
		this.setScreen(new StartScreen(this));
	}
}
