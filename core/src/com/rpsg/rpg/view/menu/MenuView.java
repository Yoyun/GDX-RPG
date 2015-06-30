package com.rpsg.rpg.view.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.rpsg.gdxQuery.$;
import com.rpsg.gdxQuery.GdxFrame;
import com.rpsg.gdxQuery.GdxQuery;
import com.rpsg.gdxQuery.GdxQueryRunnable;
import com.rpsg.rpg.core.Setting;
import com.rpsg.rpg.io.Music;
import com.rpsg.rpg.object.base.IOMode;
import com.rpsg.rpg.object.rpg.Hero;
import com.rpsg.rpg.system.base.Res;
import com.rpsg.rpg.system.controller.HeroController;
import com.rpsg.rpg.system.ui.ImageButton;
import com.rpsg.rpg.system.ui.Label;
import com.rpsg.rpg.system.ui.MenuHeroBox;
import com.rpsg.rpg.system.ui.StackView;
import com.rpsg.rpg.system.ui.View;
import com.rpsg.rpg.utils.display.ColorUtil;
import com.rpsg.rpg.utils.display.WeatherUtil;
import com.rpsg.rpg.utils.game.GameUtil;
import com.rpsg.rpg.utils.game.TimeUtil;
import com.rpsg.rpg.view.GameViews;

public class MenuView extends StackView{
	
	public static Stage stage;
	static GdxFrame frames;
	WidgetGroup leftBar;
	@Override
	public void init() {
		stage=new Stage(new ScalingViewport(Scaling.stretch, GameUtil.screen_width, GameUtil.screen_height, new OrthographicCamera()));
		$.add(leftBar=new WidgetGroup()).appendTo(stage).setPosition(-500, 0).addAction(Actions.moveTo(0, 0,0.3f,Interpolation.pow2Out));
		$.add(Res.get(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"bg.png")).setHeight(1024).setPosition(0, 0).appendTo(leftBar);
		$.add(Res.get(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"ico_pos.png")).setPosition(-100, 330).appendTo(leftBar).addAction(Actions.moveTo(35, 330,0.55f,Interpolation.pow2Out));
		$.add(Res.get(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"ico_gold.png")).setPosition(-100, 275).appendTo(leftBar).addAction(Actions.moveTo(35, 275,0.55f,Interpolation.pow2Out));
		$.add(Res.get(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"ico_flag.png")).setPosition(-100, 212).appendTo(leftBar).addAction(Actions.moveTo(35, 212,0.55f,Interpolation.pow2Out));
		frames=$.add($.add(new Label("",24).setWidth(1000).setPos(0, 558).setPad(-6)).appendTo(leftBar).setColor(1,1,1,0).addAction(Actions.parallel(Actions.fadeIn(0.3f),Actions.moveTo(90,558,0.5f))),new GdxQueryRunnable() {public void run(GdxQuery self) {
			((Label)self.getItem()).setText(GameViews.global.tyear+"/"+GameViews.global.tmonth+"/"+GameViews.global.tday);
		}});
		frames.add($.add(new Label("",24).setWidth(1000).right(true).setPos(0, 558)).appendTo(leftBar).setColor(1,1,1,0).addAction(Actions.parallel(Actions.fadeIn(0.3f),Actions.moveTo(380,558,0.5f,Interpolation.pow2Out))),new GdxQueryRunnable() {public void run(GdxQuery self) {
			((Label)self.getItem()).setText(((GameViews.global.mapColor==ColorUtil.DAY?"上午":(GameViews.global.mapColor==ColorUtil.NIGHT?"夜晚":"黄昏"))+" "+WeatherUtil.getName()));
		}}); 
		frames.add($.add(new Label("",18).setWidth(1000).right(true).setPos(383, 525)).appendTo(leftBar).setColor(1,1,1,0).addAction(Actions.fadeIn(0.7f)),new GdxQueryRunnable() {public void run(GdxQuery self) {
			((Label)self.getItem()).setText("游戏已进行"+TimeUtil.getGameRunningTime());
		}});
		$.add(Res.get(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"hr.png")).setPosition(-200, 490).appendTo(leftBar).setColor(1,1,1,0).addAction(Actions.delay(0.2f, Actions.parallel(Actions.fadeIn(0.1f),Actions.moveTo(20, 490,0.1f))));
		frames.add($.add(new Label("",24).setWidth(1000)).setPosition(-300, 357).appendTo(leftBar).setColor(1,1,1,0).addAction(Actions.parallel(Actions.fadeIn(0.3f),Actions.moveTo(75,357,0.5f,Interpolation.pow2Out))),new GdxQueryRunnable() {public void run(GdxQuery self) {
			((Label)self.getItem()).setText((String)GameViews.gameview.map.getProperties().get("name")+"["+HeroController.getHeadHero().mapx+","+HeroController.getHeadHero().mapy+"]");
		}});
		frames.add($.add(new Label("",24).setWidth(1000)).setPosition(-300,302).appendTo(leftBar).setColor(1,1,1,0).addAction(Actions.parallel(Actions.fadeIn(0.3f),Actions.moveTo(75,302,0.5f,Interpolation.pow2Out))),new GdxQueryRunnable() {public void run(GdxQuery self) {
			((Label)self.getItem()).setText("持有"+GameViews.global.gold+"金币");
		}});
		frames.add($.add(new Label("",24).setWidth(1000)).setPosition(-300, 245).appendTo(leftBar).setColor(1,1,1,0).addAction(Actions.parallel(Actions.fadeIn(0.3f),Actions.moveTo(75,245,0.5f,Interpolation.pow2Out))),new GdxQueryRunnable() {public void run(GdxQuery self) {
			((Label)self.getItem()).setText("任务模块制作中");
		}});
		frames.add($.add(new Label("",16).setWidth(1000)).setPosition(-300, 215).appendTo(leftBar).setColor(1,1,1,0).addAction(Actions.parallel(Actions.fadeIn(0.3f),Actions.moveTo(80,215,0.5f,Interpolation.pow2Out))),new GdxQueryRunnable() {public void run(GdxQuery self) {
			((Label)self.getItem()).setText("任务模块制作中");
		}});
		$.add(new ImageButton(Res.getDrawable(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"ico_exit.png"),Res.getDrawable(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"ico_exit_p.png"))).setPosition(-100, 510).fadeOut().addAction(Actions.parallel(Actions.fadeIn(0.5f),Actions.moveTo(20, 510,0.6f,Interpolation.pow2Out))).onClick(new Runnable() {
			public void run() {
				Music.playSE("snd210");
				if(viewStack.size()!=0)
					disposes();
				else
					onkeyDown(Keys.ESCAPE);
			}
		}).appendTo(leftBar);
		$.add(new ImageButton(Res.getDrawable(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"button.png"),Setting.UI_BUTTON).setFg(Res.get(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"btn_more.png"))).onClick(new Runnable() {public void run() {
			
		}}).appendTo(leftBar).setSize(370, 50).setPosition(-100, 20).addAction(Actions.moveTo(23, 20, .5f,Interpolation.pow2Out)).getCell().prefSize(370,50);
		$.add(new ImageButton(Res.getDrawable(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"button.png"),Setting.UI_BUTTON).setFg(Res.get(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"btn_save.png"))).onClick(new Runnable() {public void run() {
			
		}}).appendTo(leftBar).setSize(172, 76).setPosition(-100, 90).addAction(Actions.moveTo(23, 90, 0.5f,Interpolation.pow2Out)).getCell().prefSize(172,76);
		$.add(new ImageButton(Res.getDrawable(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"button.png"),Setting.UI_BUTTON).setFg(Res.get(Setting.GAME_RES_IMAGE_MENU_NEW_GLOBAL+"btn_load.png"))).onClick(new Runnable() {public void run() {
			
		}}).appendTo(leftBar).setSize(172, 76).setPosition(0, 90).addAction(Actions.moveTo(221, 90, 0.5f,Interpolation.pow2Out)).getCell().prefSize(172,76);
		
		List<MenuHeroBox> boxs=new ArrayList<MenuHeroBox>();
		WidgetGroup fgGroup=(WidgetGroup) $.add(new WidgetGroup()).appendTo(stage).getItem();
		for(int i=0;i<HeroController.heros.size();i++)
			boxs.add((MenuHeroBox) $.add(new MenuHeroBox(HeroController.heros.get(i))).appendTo(leftBar).setPosition(-i*100, 400).addAction(Actions.moveTo(i*100+25, 400,0.7f,Interpolation.pow2Out)).run(new GdxQueryRunnable() {public void run(GdxQuery self) {self.onClick(new Runnable() {public void run() {
				for(MenuHeroBox _box:boxs)
					_box.setSelect(false);
				((MenuHeroBox) self.getItem()).setSelect(true);
				$.add(fgGroup).children().removeAll();
				Hero hero=((MenuHeroBox) self.getItem()).hero;
				$.add(Res.get(Setting.GAME_RES_IMAGE_FG+hero.fgname+"/Normal.png")).appendTo(fgGroup).setScaleX(-0.33f).setScaleY(0.33f).setOrigin(Align.bottomLeft).setPosition(1200, 0).setColor(0,0,0,0).addAction(Actions.parallel(Actions.color(new Color(0,0,0,0.3f),1f),Actions.moveTo(980, 0,0.8f,Interpolation.pow2Out)));
				$.add(Res.get(Setting.GAME_RES_IMAGE_FG+hero.fgname+"/Normal.png")).appendTo(fgGroup).setScaleX(-0.33f).setScaleY(0.33f).setOrigin(Align.bottomLeft).setPosition(1200, 0).setColor(1,1,1,0).addAction(Actions.parallel(Actions.fadeIn(0.4f),Actions.moveTo(960, 0,0.7f,Interpolation.pow2Out)));
			}});}}).click(i==0).getItem());
		
		$.add(leftBar).children().find(ImageButton.class,MenuHeroBox.class).onClick(new Runnable() {public void run() {
			Music.playSE("snd210");
		}});
	}

	@Override
	public void draw(SpriteBatch batch) {
		frames.logic();
		stage.act();
		stage.draw();
		for(View view:viewStack)
			view.draw(batch);
	}

	@Override
	public void logic() {
		
	}

	public void onkeyTyped(char character) {
		if(viewStack.size()!=0)
			viewStack.get(viewStack.size()-1).onkeyTyped(character);
	}

	public void onkeyDown(int keyCode) {
		if(/** viewStack.size()==1 && **/(Keys.ESCAPE==keyCode || keyCode==Keys.X)){
			this.dispose();
			com.rpsg.rpg.system.controller.InputController.currentIOMode=IOMode.MAP_INPUT_NORMAL;
			frames=null;
			stage=null;
			GameViews.gameview.stackView=null;
		}else{
			if(viewStack.size()!=0)
				viewStack.get(viewStack.size()-1).onkeyDown(keyCode);
		}
	}
	
	

	public void onkeyUp(int keyCode) {
		if(viewStack.size()!=0)
			viewStack.get(viewStack.size()-1).onkeyUp(keyCode);
	}

	public void dispose() {
//		System.gc();
		stage.dispose();
		for(View view:viewStack)
			view.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		stage.touchDown(screenX, screenY, pointer, button);
		if(viewStack.size()!=0)
			viewStack.get(viewStack.size()-1).touchDown(screenX, screenY, pointer, button);
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		stage.touchUp(screenX, screenY, pointer, button);
		if(viewStack.size()!=0)
			viewStack.get(viewStack.size()-1).touchUp(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		stage.touchDragged(screenX, screenY, pointer);
		if(viewStack.size()!=0)
			viewStack.get(viewStack.size()-1).touchDragged(screenX, screenY, pointer);
		return false;
	}
	
	public void tryToAdd(Class<? extends View> iv){
		boolean inc=false;
		for(int i=0;i<viewStack.size();i++){
			Class<? extends View> view=viewStack.get(i).getClass();
			if(view.equals(iv)){
				Collections.swap(viewStack, i, viewStack.size()-1);
				inc=true;
				break;
			}
		}
		if(!inc)
			try {
				viewStack.add(iv.newInstance());
				viewStack.get(viewStack.size()-1).init();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	}

	@Override
	public boolean scrolled(int amount) {
		stage.scrolled(amount);
		if(viewStack.size()!=0)
			viewStack.get(viewStack.size()-1).scrolled(amount);
		return false;
	}
	
	@Override
	public void disposes() {
		for(int size=GameViews.gameview.stackView.viewStack.size();size>0;size--){
			onkeyDown(Keys.ESCAPE);
			if(viewStack.size()!=1)
				viewStack.remove(viewStack.size()-1);
		}
	}

}