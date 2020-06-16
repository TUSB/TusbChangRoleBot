/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 Config.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Core;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Config {
	private static final Parameters params = new Parameters();
	private static final Configurations configs = new Configurations();
	private static PropertiesConfiguration config;

	public Config() {
	}

	/**
	 * 現在のカレントディレクトリを絶対パスで取得
	 *
	 * @return
	 */
	public static String getDir() {
		return System.getProperty("user.dir");
	}

	/**
	 * プロパティ値取得
	 *
	 * @param key
	 * @return
	 */
	public String getProperty(final String key) {
		return getProperty(key, "");
	}

	/**
	 * プロパティ値を取得 デフォルト値あり
	 *
	 * @param key          キー
	 * @param defaultValue デフォルト値
	 * @return キーが存在しない場合、デフォルト値
	 * 存在する場合、値
	 */
	public String getProperty(final String key, final String defaultValue) {
		try {
			return config.getString(key, defaultValue);
		} catch (NullPointerException e) {
			return "";
		}
	}

	/**
	 * @param key
	 * @return
	 */
	public ArrayList<String> getPropertyList(final String key) {
		return new ArrayList<>(Arrays.asList(StringUtils.split(getProperty(key), ",")));
	}

	/**
	 * config Load
	 *
	 * @param filePath 読み込むファイルプロパティPath
	 */
	public void load(String filePath) {
		try {
			config = configs.properties(new File(filePath));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @param propFile
	 * @param key
	 * @param value
	 */
	public void saveList(String propFile, String key, String value) {
		load(propFile);
		if ("".equals(getProperty(key))) {
			save(propFile, key, value);
		} else {
			ArrayList<String> in = getPropertyList(key);
			in.add(value);
			ArrayList<String> out = new ArrayList<>(new HashSet<>(in));
			save(propFile, key, String.valueOf(StringUtils.join(out, ',')));
			System.out.println(out);
		}
	}

	/**
	 * Config Save
	 *
	 * @param propFile
	 * @param key      SaveSetting Key
	 * @param value    SaveSetting Value
	 */
	public void save(String propFile, String key, String value) {
		PropertiesConfiguration conf;

		FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
				new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
						.configure(params.fileBased().setFileName(propFile));

		try {
			conf = builder.getConfiguration();
			if (!conf.containsKey(key)) {
				conf.addProperty(key, value);
			} else {
				conf.setProperty(key, value);
			}
			builder.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
