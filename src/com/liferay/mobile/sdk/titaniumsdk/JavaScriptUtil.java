package com.liferay.mobile.sdk.titaniumsdk;

import org.apache.commons.lang.WordUtils;

import com.liferay.mobile.sdk.android.JavaUtil;

public class JavaScriptUtil extends JavaUtil {

	@Override
	public String getReturnType(String type) {
		// TODO
		return type;
	}

	@Override
	public String getServiceClassName(String serviceContext) {
		String className = CLASS_NAMES.get(serviceContext);

		if (className == null) {
			className = WordUtils.capitalize(serviceContext);
		}

		return className;
	}

	@Override
	public String getType(String type) {
		// TODO
		return type;
	}

}
