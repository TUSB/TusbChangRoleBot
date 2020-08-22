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

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

import static org.apache.commons.configuration2.ConfigurationConverter.getConfiguration;

public class Config {
	@Deprecated
	private static PropertiesConfiguration config;
	private final Parameters params = new Parameters();
	private final Configurations configs = new Configurations();

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
	 * Properties File path から Properties を取得
	 *
	 * @return Properties
	 */
	public static Properties loadProperties(final String basePath) {
		try {
			try (InputStream inputStream = new FileInputStream(basePath)) {
				Properties properties = new Properties();
				properties.load(inputStream);
				return properties;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * プロパティ値取得
	 *
	 * @param key
	 * @return
	 */
	public String getValue(final String propFile, final String key) {
		return getValue(propFile, key, "");
	}

	/**
	 * プロパティ値を取得 デフォルト値あり
	 *
	 * @param key          キー
	 * @param defaultValue デフォルト値
	 * @return キーが存在しない場合、デフォルト値
	 * 存在する場合、値
	 */
	public String getValue(final String propFile, String key, final String defaultValue) {
		Properties conf = loadProperties(propFile);
		try {
			Configuration config = getConfiguration(conf);
			return config.getString(key, defaultValue).trim();
		} catch (NullPointerException e) {
			return "";
		}
	}

	/**
	 * @param key
	 * @return
	 */
	public ArrayList<String> getValueList(final String propFile, final String key) {
		return new ArrayList<>(Arrays.asList(StringUtils.split(getValue(propFile, key), ",")));
	}

	/**
	 * config Load
	 *
	 * @param filePath 読み込むファイルプロパティPath
	 */
	@Deprecated
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
		if ("".equals(getValue(propFile, key, ""))) {
			save(propFile, key, value);
		} else {
			ArrayList<String> in = getValueList(propFile, key);
			in.add(value);
			ArrayList<String> out = new ArrayList<>(new HashSet<>(in));
			save(propFile, key, String.valueOf(StringUtils.join(out, ',')));
		}
	}

	/**
	 * Config Save
	 *
	 * @param propFile
	 * @param key      SaveSetting Key
	 * @param value    SaveSetting Value
	 */
	public synchronized void save(String propFile, String key, String value) {
		FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
				new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
						.configure(params.fileBased().setFileName(propFile));
		try {
			PropertiesConfiguration conf = builder.getConfiguration();
			if (conf.containsKey(key)) {
				conf.setProperty(key, value);
			} else {
				conf.addProperty(key, value);
			}
			builder.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
