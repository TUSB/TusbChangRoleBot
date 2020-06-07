/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 EmojiType.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Core.Const.Enums;


public enum EmojiType {

	TUSB(290388758971285514L, "tusb"),
	TUSBCHANG_TALK(321283110270664704L, "TUSBCHANG_TALK"),
	TUSBCHANG_SAD(321280905224257537L, "TUSBCHANG_SAD"),
	TUSBCHANG_DOUBT(321281996728958977L, "TUSBCHANG_DOUBT"),
	TUSBCHANG_ANGRY(321269696479363073L, "TUSBCHANG_ANGRY"),
	TUSBCHANG(321270021126881282L, "TUSBCHANG"),
	S1_SECURITY_POLE_SINGLE(660817748871479345L, "s1_security_pole_single"),
	S2_SECURITY_POLE_DUAL(660817775375417356L, "s2_security_pole_dual"),
	S3_SECURITY_POLE_TRIPLE(660817796032102400L, "s3_security_pole_triple"),
	E1_DEMON_EYE_ICON(660817340778151936L, "e1_demon_eye_icon"),
	E2_POISON_EYE_ICON(660817390719729674L, "e2_poison_eye_icon"),
	E3_CRIMSON_EYE_ICON(660817422940504064L, "e3_crimson_eye_icon"),
	E4_LUNATIC_EYE_ICON(660817453470711819L, "e4_lunatic_eye_icon"),
	E5_FROZEN_EYE_ICON(660817483292344345L, "e5_frozen_eye_icon"),
	E6_NIGHTMARE_EYE_ICON(660817506906406922L, "e6_nightmare_eye_icon"),
	E7_AURORA_MAKER_ICON(660817527655366666L, "e7_aurora_maker_icon"),
	GENOCIDE_BOMB_ICON(660819692427935764L, "genocide_bomb_icon"),
	DEMON_ARCHER_ICON(660817556860305408L, "demon_archer_icon"),
	ABYSS_MAN_JR(319934198804774912L, "abyss_man_jr");


	private Long Id;
	private String Name;

	EmojiType() {
	}

	EmojiType(Long id, String name) {
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

	@Override
	public String toString() {
		return "ReactionEmojiType{" +
				"Id=" + Id +
				", Name='" + Name + '\'' +
				'}';
	}
}
