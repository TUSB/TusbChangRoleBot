/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 RoleType.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Core.Const.Enums;

public enum RoleType {
	ADMIN(290015220519403520L, "管理者"),                // ErrorLogを飛ばす先
	CREATOR(290039249149886464L, "製作者"),                // 管理者権限利用可能用ロール
	YOUTUBE_FOLLOWER(695706490371047526L, "Youtube"),    // Youtube 通知を飛ばすロール
	TWITTER_FOLLOWER(716591043457581067L, "Twitter"),    // Twitter 通知を飛ばすロール
	MEMBER(716604975144239115L, "メンバー"),                // 認証済みメンバーロール Confirmed
	EVERYONE(290014442748379137L, "everyone");            // 未認証メンバーロール (everyone)


	private Long Id;
	private String Name;

	RoleType() {
	}

	RoleType(Long id, String name) {
		Id = id;
		Name = name;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
