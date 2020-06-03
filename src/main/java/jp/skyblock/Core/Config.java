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
import java.util.Arrays;
import java.util.Properties;

public class Config {
	public static final String CONFIG_DIR = getDir() + File.separator + "config";
	public static final String DISCORD_CONFIG_FILE = File.separator + "Discord.properties";
	@Deprecated
	private static final Properties prop = new Properties();
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
	public static String getProperty(final String key) {
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
	public static String getProperty(final String key, final String defaultValue) {
		return config.getString(key, defaultValue);
	}

	public static String[] getPropertyList(final String key) {
		return StringUtils.split(getProperty(key, ""), ',');
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

	public void saveList(String propDir, String propName, String key, String value) {

		if ("".equals(getProperty(key))) {
			save(propDir, propName, key, getProperty(key) + value);
		} else {
			if (!value.equals(getProperty(key))) {
				String[] ls = getPropertyList(getProperty(key));
				Arrays.stream(ls)
						.filter(s -> !s.equals(value))
						.forEachOrdered(
								s -> save(propDir, propName, key, getProperty(key) + "," + value)
						);

				System.out.println(getProperty(key) + "," + value);
			}
		}
	}

	/**
	 * Config Save
	 *
	 * @param propDir  ConfigFile Directory
	 * @param propName ConfigFile Name
	 * @param key      SaveSetting Key
	 * @param value    SaveSetting Value
	 */
	public void save(String propDir, String propName, String key, String value) {

		String propPath = propDir + propName;
		Parameters params = new Parameters();
		PropertiesConfiguration conf;

		FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
				new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
						.configure(params.fileBased().setFileName(propPath));

		try {
			conf = builder.getConfiguration();
			if ("".equals(getProperty(key))) {
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
