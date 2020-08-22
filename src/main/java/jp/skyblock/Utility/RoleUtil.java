/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 RoleUtil.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Utility;

import jp.skyblock.Core.Const.Enums.RoleType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

public class RoleUtil {

	/**
	 * ユーザに対して付与されたロールを検索
	 *
	 * @param member
	 * @param roleId
	 * @return Role
	 */
	public Role findRole(Member member, Long roleId) {
		List<Role> roles = member.getRoles();
		return roles.stream()
				.filter(r -> r.getIdLong() == (roleId))
				.findFirst()
				.orElse(null);
	}


	/**
	 * Role 存在確認
	 *
	 * @param member
	 * @param roleId
	 * @return
	 */
	public Boolean isRole(Member member, Long roleId) {
		try {
			Role role = this.findRole(member, roleId);
			return role != null || role.getIdLong() == roleId;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public boolean isAdmin(Member member) {
		return this.isRole(member, RoleType.ADMIN.getId());
	}

	public boolean isCreator(Member member) {
		return this.isRole(member, RoleType.CREATOR.getId());
	}

	public boolean isTwitterFollow(Member member) {
		return this.isRole(member, RoleType.TWITTER_FOLLOWER.getId());
	}

	public boolean isYoutubeFollow(Member member) {
		return this.isRole(member, RoleType.YOUTUBE_FOLLOWER.getId());
	}

	public boolean isMember(Member member) {
		return this.isRole(member, RoleType.MEMBER.getId());
	}


	/** メモ
	 *  everyoneは ポルノ画像スキャン、連投、文字数制限、過剰メンション対策を付与(よくある海外の荒らしBOTはここらへんを攻めてくる)
	 *
	 *  サーバログイン時 (everyoneに対してはMEE6を無効でレベルは上がらない) →
	 *  ルール同意のリアクション付与で認証済みロールを付与(ここからMEE6有効) →
	 *  MEE6 LEVEL 1 でPlayer ロールを付与(これで完全認証済みユーザとし 連投制限、文字数制限、他サバ宣伝削除制限 を解除)
	 *
	 *  英語翻訳メンションを復活（既存のTUSBちゃんが死んでいるので）
	 *
	 */

}
