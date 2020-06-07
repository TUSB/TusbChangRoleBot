/*
 * *************************************************
 *     TusbChangRoleBot
 *      Copyright (c) 2020 ConfigTest.java
 *
 *  This software is released under the TUSB.
 *      see https://github.com/TUSB/TusbChangRoleBot
 * ************************************************
 */

package jp.skyblock.Core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.fail;

public class ConfigTest {
	private static final String TestDataDir = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "data" + File.separator;
	private static final String TestDiscordProp = TestDataDir + "discord.properties";
	Config config = new Config();

	@Before
	public void setUp() throws Exception {
		new File(TestDiscordProp).createNewFile();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDir() {
		Assert.assertEquals(System.getProperty("user.dir"), Config.getDir());
	}

	@Test
	public void getProperty() {
		Assert.assertEquals("Test1", config.getProperty("Test"));
	}

	@Test
	public void getPropertyList() {
		config.load(TestDiscordProp);
		config.getPropertyList("Test2");
	}


	@Test
	public void save() {
		try {
			config.save(TestDiscordProp, "Test", "Test1");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void saveList() {
		try {
			config.saveList(TestDiscordProp, "Test2", "Test1");
			config.saveList(TestDiscordProp, "Test2", "Test2");
			config.saveList(TestDiscordProp, "Test2", "Test3");
			config.saveList(TestDiscordProp, "Test2", "Test4");
			config.saveList(TestDiscordProp, "Test2", "Test5");
			config.saveList(TestDiscordProp, "Test2", "Test6");

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void load() {
		try {
			config.load(TestDiscordProp);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}