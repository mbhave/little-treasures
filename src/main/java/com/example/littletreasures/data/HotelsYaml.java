/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.littletreasures.data;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.log.LogMessage;
import org.springframework.util.function.ThrowingSupplier;

/**
 * Utility used to load the YAML that describes the hotels.
 */
final class HotelsYaml {

	private static final Log logger = LogFactory.getLog(HotelsYaml.class);

	private HotelsYaml() {
	}

	static Properties loadAsProperties(Resource resource) {
		logger.debug(LogMessage.format("Loading hotels resource %s", resource));
		logger.trace(LogMessage.of(() -> "Loaded " + getContent(resource)));
		YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
		factory.setResources(resource);
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	private static String getContent(Resource resource) {
		return ThrowingSupplier.of(() -> resource.getContentAsString(StandardCharsets.UTF_8)).get();
	}

}
