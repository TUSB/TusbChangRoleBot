package jp.skyblock.Command;

import jp.skyblock.Core.Config;
import jp.skyblock.Core.Constant;
import jp.skyblock.Executer.CommandExecIf;
import jp.skyblock.Utility.Emoji;
import jp.skyblock.Utility.ExceptionIf;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.atomic.AtomicReference;
import static jp.skyblock.Core.EmojiType.*;
public class RoleCommand implements CommandExecIf {
	final Emoji emj = Constant.emj;
	final ExceptionIf exceptIf = Constant.exceptIf;

	@Override
	public void execute() throws ExceptionIf{
		MessageReceivedEvent event = CommandExecIf.CommandEvent.getEvent();

		Guild guild = event.getGuild();
		User author = event.getAuthor();
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();
		String msg = message.getContentDisplay();


		AtomicReference<String> msgId = new AtomicReference<>();
		Config conf = new Config();
		String propPath = Config.getDir() + Constant.CONFIG_DIR ;
		String propName = Constant.DISCORD_CONFIG_FILE;
		try{
			channel.sendTyping().queue();

			channel.sendMessage("embed").queue(m -> {
				m.addReaction(emj.getReaction(E1_DEMON_EYE_ICON.getName())).queue();
				m.addReaction(emj.getReaction(E2_POISON_EYE_ICON.getName())).queue();
				m.addReaction(emj.getReaction(E3_CRIMSON_EYE_ICON.getName())).queue();
//				conf.save(propPath,propName,"OBSERVER_REACTION_MESSAGE_ID",String.valueOf(m.getIdLong()));
			});
//			conf.save(propPath,propName,"OBSERVER_REACTION_CHANNEL_ID",String.valueOf(channel.getIdLong()));

		}catch(Exception e){
			Exception ex = exceptIf.commandException(e,event);
			throw new ExceptionIf(ex);
		}
	}
}
