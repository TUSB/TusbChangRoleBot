package jp.skyblock;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.PermissionException;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TusbChangRoleBot extends ListenerAdapter {
	/**
	 * This is the method where the program starts.
	 */
	public static void main(String[] args) {
		//We construct a builder for a BOT account. If we wanted to use a CLIENT account
		// we would use AccountType.CLIENT
		try {
			JDA jda = new JDABuilder("##################################################") // The token of the account that is logging in.
					.addEventListeners(new TusbChangRoleBot()) // An instance of a class that will handle events.
					.build();
			jda.awaitReady(); // Blocking guarantees that JDA will be completely loaded.
			System.out.println("Finished Building JDA!");
		} catch (LoginException e) {
			//If anything goes wrong in terms of authentication, this is the exception that will represent it
			e.printStackTrace();
		} catch (InterruptedException e) {
			//awaitReadyは、JDAが完全にロードされるまで待つブロッキングメソッドであるという事実に起因します。
			// 待機を中断することができます。これは、そのような状況で発生する例外です。
			//この非常に単純化された例では、この例外は決して発生しません。実際には、次のような場合を除いて、これは決して発生しません。
			// 割り込みが発生する可能性のあるスレッドで awaitReady を使用します (非同期スレッドの使用と割り込み)
			e.printStackTrace();
		}
	}

	/**
	 オーバーライドに注意してください！ * このメソッドは、実際には ListenerAdapter クラスのメソッドをオーバーライドしています。
	 * このメソッドは実際には ListenerAdapter クラスのメソッドをオーバーライドしています! このメソッドは、@Override アノテーションを配置しています。
	 * 他のメソッドをオーバーライドしているメソッドの前に、実際にオーバーライドしていることを保証するために
	 * スーパークラスのメソッドを適切にオーバーライドします。メソッドをオーバーライドするときは必ずこれを行う必要があります！
	 *
	 * 上記のように、このメソッドは
	 * {@link net.dv8tion.jda.api.hooks.ListenerAdapter ListenerAdapter} クラスです。これにはすべての JDA イベント用の便利なメソッドがあります!
	 * ListenerAdapter を使用する予定がある場合は、提供されるイベントに目を通してみてください。
	 *
	 * この例では、メッセージを受信するとコンソールに表示されます。
	 *
	 * @param event
	 * イベントには、{@link net.dv8tion.jda.api.entities.Message Message}の情報が含まれています。
	 * チャンネルで送信されたものです。
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
//これらは JDA のすべてのイベントで提供されます。
		JDA jda = event.getJDA();//JDA、APIのコアとなる。
		long responseNumber = event.getResponseNumber();//前回の再接続以降にJDAが受信したDiscordイベントの量。

		//イベント固有の情報
		User author = event.getAuthor(); //メッセージを送信したユーザー
		Message message = event.getMessage(); //受信したメッセージです。
		MessageChannel channel = event.getChannel(); //これはメッセージが送信されたMessageChannelです。
		// これはTextChannel、PrivateChannel、またはGroup!

		String msg = message.getContentDisplay(); //これは、人間が読めるバージョンのメッセージを返します。に似ています。
		// クライアントに表示されるものです。

		boolean bot = author.isBot(); //このブール値は
		// メッセージを送信したのがBOTかどうか!

		if (event.isFromType(ChannelType.TEXT)) //このメッセージがギルドの TextChannel に送信された場合
		{
			//このメッセージがギルドで送信されたことがわかったので、ギルド固有のことができるようになりました。
			// これらのメソッドを使用する前に ChannelType をチェックしないと、null を返す可能性があることに注意してください。
			// メッセージはギルドからのものではない可能性があります!

			Guild guild = event.getGuild(); //このメッセージが送信されたギルド。(APIでは、ギルドはサーバーであることに注意してください)
			TextChannel textChannel = event.getTextChannel(); //このメッセージが送信されたTextChannel。
			Member member = event.getMember(); //このメッセージを送信したメンバー。ユーザーに関するギルド固有の情報が含まれています!

			String name;
			if (message.isWebhookMessage()) {
				name = author.getName(); //これが Webhook メッセージである場合は、関連するメンバーはありません。
			} // Userを使用しているので、名前はデフォルトでauthorを使用しています。
			else {
				name = Objects.requireNonNull(member).getEffectiveName(); //これは、メンバーのニックネームがあればそれを使用します。
			} // それ以外の場合はデフォルトのユーザ名になります。(User#getName()

			System.out.printf("(%s)[%s]<%s>: %s\n", guild.getName(), textChannel.getName(), name, msg);
		} else if (event.isFromType(ChannelType.PRIVATE)) //このメッセージが PrivateChannel に送信された場合
		{
			//メッセージはPrivateChannelで送信されました。
			//この例では privateChannel を直接使用していませんが、用途はあります。
			PrivateChannel privateChannel = event.getPrivateChannel();

			System.out.printf("[PRIV]<%s>: %s\n", author.getName(), msg);
		}

		//Now that you have a grasp on the things that you might see in an event, specifically MessageReceivedEvent,
		// we will look at sending / responding to messages!
		//This will be an extremely simplified example of command processing.

		//Remember, in all of these .equals checks it is actually comparing
		// message.getContentDisplay().equals, which is comparing a string to a string.
		// If you did message.equals() it will fail because you would be comparing a Message to a String!
		if (msg.equals("!ping")) {
			//RestActionを構築し、Requesterでアクションを "キューイング "することで、"ポン！"というメッセージを送信します。
			// queue() を呼び出すことで、リクエストを Requester に送信し、そのリクエストを discord に送信します。queue() やその他の
			// その異なるフォームは自動的にあなたのためにratelimitingを処理します!

			channel.sendMessage("pong!").queue();
		} else if (msg.equals("!roll")) {
			//この場合、RestActionにflatMap演算子を使用する方法を示す例があります。演算子
			// RestActionを実行した後に結果のオブジェクトを提供します。注意点として、すべての RestActions
			 // これは、オブジェクトの戻り値を持ち、その代わりに Void の戻り値を持つことになります。まだ flatMap 演算子を使用して、別の RestAction をチェーン実行することができます!

			Random rand = ThreadLocalRandom.current();
			int roll = rand.nextInt(6) + 1; //This results in 1 - 6 (instead of 0 - 5)
			channel.sendMessage("Your roll: " + roll)
					.flatMap(
							(v) -> roll < 3, // これはラムダ式と呼ばれています。ラムダ式が何なのか、どのように動作するのかわからない場合は、googleで調べてみてください!
							// ロールが悪かった場合は別のメッセージを送信します(3以下)
							sentMessage -> channel.sendMessage("The roll for messageId: " + sentMessage.getId()
									+ " wasn't very good... Must be bad luck!\n"))
					.queue();
		} else if (msg.startsWith("!kick")) //Note, I used "startsWith, not equals.
		{
			//これは管理者コマンドです。つまり、それを使用するには特定の権限が必要で、この場合は
			// それにはPermission.KICK_MEMBERSが必要です。メンバーをキックしようとする前に
			// ログインしたアカウントが実際に権限を持っている場合、しかし、私たちの後に何かが変更される可能性があることを考慮して
			// 我々はまた、我々はもう許可を持っていない可能性を考慮に入れるべきであることを確認し、このようにDiscord
			//許可に失敗した場合のレスポンス!
			//キューの2番目のパラメータであるエラーコンシューマを使用します!

			//ギルド内で送られてきたメッセージだけを処理したい。
			if (message.isFromType(ChannelType.TEXT)) {
				//ユーザーが提供されないと誰も蹴れない!
				if (message.getMentionedUsers().isEmpty()) {
					channel.sendMessage("You must mention 1 or more Users to be kicked!").queue();
				} else {
					Guild guild = event.getGuild();
					Member selfMember = guild.getSelfMember(); //This is the currently logged in account's Member object.
					// Very similar to JDA#getSelfUser()!

					//Now, we the the logged in account doesn't have permission to kick members.. well.. we can't kick!
					if (!selfMember.hasPermission(Permission.KICK_MEMBERS)) {
						channel.sendMessage("Sorry! I don't have permission to kick members in this Guild!").queue();
						return; //We jump out of the method instead of using cascading if/else
					}

					//Loop over all mentioned users, kicking them one at a time. Mwauahahah!
					List<User> mentionedUsers = message.getMentionedUsers();
					for (User user : mentionedUsers) {
						Member member = guild.getMember(user); //We get the member object for each mentioned user to kick them!

						//We need to make sure that we can interact with them. Interacting with a Member means you are higher
						// in the Role hierarchy than they are. Remember, NO ONE is above the Guild's Owner. (Guild#getOwner())
						if (!selfMember.canInteract(member)) {
							// use the MessageAction to construct the content in StringBuilder syntax using append calls
							channel.sendMessage("Cannot kick member: ")
									.append(member.getEffectiveName())
									.append(", they are higher in the hierarchy than I am!")
									.queue();
							continue; //Continue to the next mentioned user to be kicked.
						}

						//Remember, due to the fact that we're using queue we will never have to deal with RateLimits.
						// JDA will do it all for you so long as you are using queue!
						guild.kick(member).queue(
								success -> channel.sendMessage("Kicked ").append(member.getEffectiveName())
										.append("! Cya!").queue(),
								error -> {
									//The failure consumer provides a throwable. In this case we want to check for a PermissionException.
									if (error instanceof PermissionException) {
										PermissionException pe = (PermissionException) error;
										Permission missingPermission = pe.getPermission(); //If you want to know exactly what permission is missing, this is how.
										//Note: some PermissionExceptions have no permission provided, only an error message!

										channel.sendMessage("PermissionError kicking [")
												.append(member.getEffectiveName()).append("]: ")
												.append(error.getMessage()).queue();
									} else {
										channel.sendMessage("Unknown error while kicking [")
												.append(member.getEffectiveName())
												.append("]: <").append(error.getClass().getSimpleName()).append(">: ")
												.append(error.getMessage()).queue();
									}
								});
					}
				}
			} else {
				channel.sendMessage("This is a Guild-Only command!").queue();
			}
		} else if (msg.equals("!block")) {
			//これは RestAction で complete() メソッドを使用する方法の例です。complete メソッドは
			// JDABuilder の awaitReady() は、リクエストが送信されるまで待ってから実行を継続します。
			//ほとんどの開発者はこれを必要とせず、ただキューを使うことができます。complete を使っても、JDA は ratelimit を処理します。
			// ただし、shouldQueueがfalseの場合は、時間経過後にratelimitリトライ後に送信するRequestをキューに入れません。これは
		 // 代わりに RateLimitException を発生させます!
			// complete() の大きな利点のひとつは、キューの成功時に消費者が持つであろうオブジェクトを返すことです。
			// しかし、これはリクエストがあったときと同じ実行コンテキストで実行されます。これはほとんどの開発者にとって重要なことかもしれません。
			// しかし、正直なところ、キューの方が高速なので、開発者が使いたいと思うのはキューの方でしょう。

			try {
				//Note the fact that complete returns the Message object!
				//The complete() overload queues the Message for execution and will return when the message was sent
				//It does handle rate limits automatically
				Message sentMessage = channel.sendMessage("I blocked and will return the message!").complete();
				//This should only be used if you are expecting to handle rate limits yourself
				//The completion will not succeed if a rate limit is breached and throw a RateLimitException
				Message sentRatelimitMessage = channel
						.sendMessage("I expect rate limitation and know how to handle it!").complete(false);

				System.out.println("Sent a message using blocking! Luckly I didn't get Ratelimited... MessageId: "
						+ sentMessage.getId());
			} catch (RateLimitedException e) {
				System.out.println(
						"Whoops! Got ratelimited when attempting to use a .complete() on a RestAction! RetryAfter: "
								+ e.getRetryAfter());
			}
			//Note that RateLimitException is the only checked-exception thrown by .complete()
			catch (RuntimeException e) {
				System.out.println(
						"Unfortunately something went wrong when we tried to send the Message and .complete() threw an Exception.");
				e.printStackTrace();
			}
		}
	}
}