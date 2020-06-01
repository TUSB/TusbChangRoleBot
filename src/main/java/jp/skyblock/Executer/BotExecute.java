package jp.skyblock.Executer;

import jp.skyblock.Core.BotListener;
import jp.skyblock.Core.Constant;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class BotExecute {
	public BotExecute() {
		try {
			JDA jda = new JDABuilder(AccountType.BOT)
					.setToken(Constant.DISCORD_TOKEN)
					.addEventListeners(new BotListener())
					.build();
			jda.awaitReady();
			System.out.println("Finished Building JDA!");
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
