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
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.fail;

public class ConfigTest {
	private static final String TestDataDir = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "data" + File.separator;
	private static final String TestDiscordProp = TestDataDir + "TEST.properties";
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
		config.save(TestDiscordProp, "getProperty", "Test");
		Assert.assertEquals("Test", config.getValue(TestDiscordProp, "getProperty"));
	}

	@Test
	public void getValueList() {
		config.saveList(TestDiscordProp, "getValueList", "Test1");
		config.saveList(TestDiscordProp, "getValueList", "Test2");
		config.saveList(TestDiscordProp, "getValueList", "Test3");
		config.saveList(TestDiscordProp, "getValueList", "Test4");
		config.saveList(TestDiscordProp, "getValueList", "Test5");
		config.saveList(TestDiscordProp, "getValueList", "Test6");

		ArrayList<String> expected = config.getValueList(TestDiscordProp, "getValueList");
		ArrayList<String> actual = new ArrayList<>(Arrays.asList("Test1", "Test6", "Test5", "Test4", "Test3", "Test2"));
		Assert.assertEquals(expected, actual);
	}


	@Test
	public void save() {
		try {
			config.save(TestDiscordProp, "save", "AAA");
			Assert.assertEquals("AAA", config.getValue(TestDiscordProp, "save"));

			config.save(TestDiscordProp, "save", "BBB");
			Assert.assertEquals("BBB", config.getValue(TestDiscordProp, "save"));

			config.save(TestDiscordProp, "save", "CCC");
			Assert.assertEquals("CCC", config.getValue(TestDiscordProp, "save"));

		} catch (Exception e) {
			fail(e.getMessage());
		}


	}

	@Test
	public void saveList() {
		try {
			config.saveList(TestDiscordProp, "saveList", "Test1");
			config.saveList(TestDiscordProp, "saveList", "Test2");
			config.saveList(TestDiscordProp, "saveList", "Test3");
			config.saveList(TestDiscordProp, "saveList", "Test4");
			config.saveList(TestDiscordProp, "saveList", "Test5");
			config.saveList(TestDiscordProp, "saveList", "Test6");
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