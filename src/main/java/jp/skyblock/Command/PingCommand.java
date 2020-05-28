package jp.skyblock.Command;

import jp.skyblock.Core.Constant;
import jp.skyblock.Executer.CommandExecIf;
import jp.skyblock.Utility.Emoji;
import jp.skyblock.Utility.ExceptionIf;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

import static jp.skyblock.Core.EmojiType.TUSB;


public class PingCommand implements CommandExecIf {

	final Emoji emj = Constant.emj;
	final ExceptionIf exceptIf = Constant.exceptIf;
	@Override
	public void execute() {
		MessageReceivedEvent event = CommandExecIf.CommandEvent.getEvent();

		Guild guild = event.getGuild();
		User author = event.getAuthor();
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();
		String msg = message.getContentDisplay();

		EmbedBuilder eb = new EmbedBuilder();
		try{
			channel.sendTyping().queue();
			eb.setColor(Color.decode("#15A0ED"));
			eb.setTitle(emj.ReplaceEmojiMessage(":TUSBCHANG: このサーバーについて :TUSBCHANG:"));
			eb.setAuthor("ようこそ TUSB Discord へ！","https://skyblock.jp",guild.getIconUrl());
			eb.setThumbnail("https://skyblock.jp/uploads/2020/05/MAZE.png");
			eb.setDescription(emj.ReplaceEmojiMessage("🔰はじめにお読みください\n\n"));
			eb.addField(emj.ReplaceEmojiMessage(":TUSBCHANG_TALK:__カテゴリ__"),emj.ReplaceEmojiMessage("・通常の会話等は「**一般公開**」へ\n・二次創作などは「**クリエイティブ**」へ\n・当サークル制作の配布MAPについては「**:sparkles:TUSB/:game_die:TUSP**」へ\n・Minecraftに関する総合的な話題は「**MINECRAFT**」へ"),false);
			eb.addField(emj.ReplaceEmojiMessage(":TUSBCHANG_TALK:__チャンネル__"),emj.ReplaceEmojiMessage("・それぞれのチャンネルの使用時には、\n　＊チャンネルトピック\n　＊ピン留めされたメッセージ を__必ず__お読み下さい。"),true);
			eb.addField(emj.ReplaceEmojiMessage(":TUSBCHANG_TALK:__ボイスチャンネル__"),emj.ReplaceEmojiMessage("・対応したチャンネルでは、GoLiveを用いて画面共有を行うことができます。"),true);
			eb.setFooter("(c) TUSB ~ 想像を超えた創造を ~",guild.getIconUrl());

			channel.sendMessage(eb.build())
					.complete()
					.addReaction(emj.getReaction(TUSB.getName()))
					.queue();
			eb.clear();

		}catch(Exception e){
			Exception ex = exceptIf.commandException(e,event);
			throw new RuntimeException(ex);
		}
	}
}
