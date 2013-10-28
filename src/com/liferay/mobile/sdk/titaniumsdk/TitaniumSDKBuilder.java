package com.liferay.mobile.sdk.titaniumsdk;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.generic.EscapeTool;

import com.liferay.mobile.sdk.BaseBuilder;
import com.liferay.mobile.sdk.http.Action;
import com.liferay.mobile.sdk.http.Discovery;
import com.liferay.mobile.sdk.http.PortalVersion;
import com.liferay.mobile.sdk.velocity.VelocityUtil;

public class TitaniumSDKBuilder extends BaseBuilder {

	public void build(String filter, PortalVersion version, Discovery discovery)
		throws Exception {

		VelocityContext context = getVelocityContext(
			filter, version, discovery);

		String templatePath = "com/liferay/mobile/sdk/titaniumsdk/service.vm";
		String filePath = getServiceFilePath(context);

		VelocityUtil.generate(context, templatePath, filePath);
	}

	protected String getServiceFilePath(VelocityContext context) {
		String className = (String)context.get(CLASS_NAME);

		String versionDir = (String)context.get(VERSION_DIR);

		StringBuilder sb = new StringBuilder();

		sb.append("titaniumsdk/gen/");
		sb.append(versionDir);

		File file = new File(sb.toString());
		file.mkdirs();

		sb.append(className);
		sb.append(".js");

		return sb.toString();
	}

	protected VelocityContext getVelocityContext(
		String filter, PortalVersion version, Discovery discovery) {

		VelocityContext context = new VelocityContext();

		JavaScriptUtil jsUtil = new JavaScriptUtil();

		String className = jsUtil.getServiceClassName(filter);

		String versionDir = BLANK;

		if (version != PortalVersion.UNKNOWN) {
			StringBuilder sb = new StringBuilder();

			sb.append("v");
			sb.append(version.getValue());
			sb.append("/");

			versionDir = sb.toString();
		}

		context.put(CLASS_NAME, className);
		context.put(DISCOVERY, discovery);
		context.put(ESCAPE_TOOL, new EscapeTool());
		context.put(JS_UTIL, jsUtil);
		context.put(VERSION, version.getValue());
		context.put(VERSION_DIR, versionDir);

		return context;
	}

	protected static final String BLANK = "";

	protected static final String CLASS_NAME = "className";

	protected static final String DISCOVERY = "discovery";

	protected static final String ESCAPE_TOOL = "esc";

	protected static final String JS_UTIL = "jsUtil";

	protected static final String VERSION = "version";

	protected static final String VERSION_DIR = "versionDir";
}
