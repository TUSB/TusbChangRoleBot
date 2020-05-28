package jp.skyblock.Core;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Config {
	public Config(){}

	private static final String CONFIG_DIR = File.separator + "config";
	private static final String DISCORD_CONFIG_FILE = File.separator + "Discord.properties";
	private static final Properties prop = new Properties();


	/**
	 *  Config Load
	 */
	public void load(){
		PropertyConfigurator.configure("config/log4j.properties");
		getDiscordConfigProperty();
	}

	/**
	 *  Config Save
	 */
	public void save(String propDir, String propName, String key , String value) {
		Properties p = new Properties();
		OutputStream os;
		try {
			os = new FileOutputStream(propDir + File.separator + propName );
			p.setProperty(key, value);
			p.store(os, propName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * プロパティ値取得
	 * @param key
	 * @return
	 */
	public static String getProperty(final String key) {
		return getProperty(key, "");
	}

	/**
	 * プロパティ値を取得
	 *
	 * @param key キー
	 * @param defaultValue デフォルト値
	 * @return キーが存在しない場合、デフォルト値
	 *          存在する場合、値
	 */
	public static String getProperty(final String key, final String defaultValue) {
		return prop.getProperty(key, defaultValue);
	}

	/**
	 * 現在のカレントディレクトリを絶対パスで取得
	 * @return
	 */
	public static String getDir(){
		return System.getProperty("user.dir");
	}

	/**
	 * Discord 用ConfigファイルをLoad
	 */
	private void getDiscordConfigProperty(){
		try {
			prop.load(new InputStreamReader(
					new FileInputStream(getDir() + CONFIG_DIR + DISCORD_CONFIG_FILE),
					StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
