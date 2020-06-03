/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 WelcomeMessage.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Utility;

import jp.skyblock.Core.Const.Constant;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

/**
 * WelcomeMessageを返すだけ
 */
public class WelcomeMessage {

	final EmojiUtil emj = Constant.emj;
	private EmbedBuilder welcomeMessage = new EmbedBuilder();

	public WelcomeMessage(MessageReceivedEvent event) {
		Guild guild = event.getGuild();
		welcomeMessage.setColor(Color.decode("#15A0ED"));
		welcomeMessage.setTitle(emj.ReplaceEmojiMessage(":TUSBCHANG: このサーバーについて :TUSBCHANG:"));
		welcomeMessage.setAuthor("ようこそ TUSB Discord へ！", "https://skyblock.jp", guild.getIconUrl());
		welcomeMessage.setThumbnail("https://skyblock.jp/uploads/2020/05/MAZE.png");
		welcomeMessage.setDescription(emj.ReplaceEmojiMessage("🔰はじめにお読みください\n\n"));
		welcomeMessage.addField(emj.ReplaceEmojiMessage(":TUSBCHANG_TALK:__カテゴリ__"), emj.ReplaceEmojiMessage("・通常の会話等は「**一般公開**」へ\n・二次創作などは「**クリエイティブ**」へ\n・当サークル制作の配布MAPについては「**:sparkles:TUSB/:game_die:TUSP**」へ\n・Minecraftに関する総合的な話題は「**MINECRAFT**」へ"), false);
		welcomeMessage.addField(emj.ReplaceEmojiMessage(":TUSBCHANG_TALK:__チャンネル__"), emj.ReplaceEmojiMessage("・それぞれのチャンネルの使用時には、\n　＊チャンネルトピック\n　＊ピン留めされたメッセージ を__必ず__お読み下さい。"), true);
		welcomeMessage.addField(emj.ReplaceEmojiMessage(":TUSBCHANG_TALK:__ボイスチャンネル__"), emj.ReplaceEmojiMessage("・対応したチャンネルでは、GoLiveを用いて画面共有を行うことができます。"), true);
		welcomeMessage.setFooter("(c) TUSB ~ 想像を超えた創造を ~", guild.getIconUrl());
	}

	public EmbedBuilder getWelcomeMessage() {
		return welcomeMessage;
	}

	/**
	 * 既存の設定はクリアされるので注意
	 *
	 * @param welcomeMessage
	 */
	public void setWelcomeMessage(EmbedBuilder welcomeMessage) {
		this.welcomeMessage.clear();
		this.welcomeMessage = welcomeMessage;
	}
}
