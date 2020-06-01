package jp.skyblock.Utility;

import jp.skyblock.Core.EmojiType;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static jp.skyblock.Core.Constant.EmojiSeparator;

public class EmojiUtil {

	private final static BidiMap<Long, String> EmojiMap = new DualHashBidiMap<>(Collections.unmodifiableMap(new HashMap<Long, String>() {
		{
			for (EmojiType emj : EmojiType.values()) {
				put(emj.getId(), emj.getName());
			}
		}
	}));

	public String getEmojiName(Long id) {
		return EmojiMap.get(id);
	}

	public Long getEmojiId(String name) {
		return EmojiMap.getKey(name);
	}

	/**
	 * Message の中にサーバ絵文字があった場合置換します。
	 *
	 * @param msg
	 * @return
	 */
	public String ReplaceEmojiMessage(String msg) {
		for (Map.Entry<Long, String> emoji : EmojiMap.entrySet()) {
			msg = StringUtils.replace(msg,
					EmojiSeparator + emoji.getValue() + EmojiSeparator,
					getEmojiMessage(emoji.getValue()));
		}
		return msg;
	}

	/**
	 * :emoji: 形式の絵文字を返す
	 *
	 * @param emojiName
	 * @return
	 */
	public String emoji(String emojiName) {
		return EmojiSeparator + emojiName + EmojiSeparator;
	}

	/**
	 * <:emoji:id> 形式の絵文字を返す
	 *
	 * @param emojiName
	 * @return
	 */
	public String getEmojiMessage(String emojiName) {
		return "<" + emoji(emojiName) + getEmojiId(emojiName) + ">";
	}

	/**
	 * emoji:id 形式の絵文字(リアクション用)を返す
	 *
	 * @param emojiName
	 * @return
	 */
	public String getReaction(String emojiName) {
		return emojiName + EmojiSeparator + getEmojiId(emojiName);
	}

}
